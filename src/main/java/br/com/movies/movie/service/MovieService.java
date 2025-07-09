package br.com.movies.movie.service;

import br.com.movies.exception.NotFoundException;
import br.com.movies.movie.dto.MovieDTO;
import br.com.movies.movie.dto.MovieFilterDTO;
import br.com.movies.movie.dto.MovieResponseDTO;
import br.com.movies.movie.dto.ProducerIntervalsDto;
import br.com.movies.movie.dto.ProducerWinIntervalDTO;
import br.com.movies.movie.mapper.MovieMapper;
import br.com.movies.movie.model.Movie;
import br.com.movies.movie.repository.MovieRepository;
import br.com.movies.producer.model.Producer;
import br.com.movies.producer.repository.ProducerRepository;
import br.com.movies.studio.model.Studio;
import br.com.movies.studio.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

	@Autowired
	private final MovieRepository movieRepository;

	@Autowired
	private final StudioRepository studioRepository;

	@Autowired
	private final ProducerRepository producerRepository;

	@Autowired
	private final MovieMapper movieMapper;

	public MovieService(MovieRepository movieRepository, StudioRepository studioRepository, ProducerRepository producerRepository, MovieMapper movieMapper) {
		this.movieRepository = movieRepository;
		this.studioRepository = studioRepository;
		this.producerRepository = producerRepository;
		this.movieMapper = movieMapper;
	}

	public List<MovieResponseDTO> getMovies(MovieFilterDTO movieFilterDTO) {
		return movieRepository.findMoviesByFilters(movieFilterDTO.getTitle(), movieFilterDTO.getStudiosId(), movieFilterDTO.getProducersId(), movieFilterDTO.getWinner()).stream()
				.map(movieMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	public MovieResponseDTO getMovieById(Long id) {
		return movieRepository.findById(id)
				.map(movieMapper::toResponseDTO)
				.orElseThrow(() -> new NotFoundException("Movie with ID " + id + " not found."));
	}

	public MovieResponseDTO createMovie(MovieDTO movieDTO) {
		List<Studio> studios = movieDTO.getStudios().stream()
				.map(studioId -> studioRepository.findById(studioId)
						.orElseThrow(() -> new NotFoundException("Studio with ID " + studioId + " not found.")))
				.toList();

		List<Producer> producers = movieDTO.getProducers().stream()
				.map(producerId -> producerRepository.findById(producerId)
						.orElseThrow(() -> new NotFoundException("Producer with ID " + producerId + " not found.")))
				.toList();

		Movie movie = movieMapper.toEntity(movieDTO, studios, producers);

		return movieMapper.toResponseDTO(movieRepository.save(movie));
	}

	public MovieResponseDTO updateMovie(Long id, MovieDTO movieDTO) {
		List<Studio> studios = movieDTO.getStudios().stream()
				.map(studioId -> studioRepository.findById(studioId)
						.orElseThrow(() -> new NotFoundException("Studio with ID " + studioId + " not found.")))
				.toList();

		List<Producer> producers = movieDTO.getProducers().stream()
				.map(producerId -> producerRepository.findById(producerId)
						.orElseThrow(() -> new NotFoundException("Producer with ID " + producerId + " not found.")))
				.toList();

		Movie movie = movieRepository.findById(id).orElseThrow(() -> new NotFoundException("Movie with ID " + id + " not found."));

		movieMapper.toEntityUpdate(movie, movieDTO, studios, producers);

		return movieMapper.toResponseDTO(movieRepository.save(movie));
	}

	public void deleteMovie(Long id) {
		movieRepository.deleteById(id);
	}

	public ProducerIntervalsDto getProducerWinIntervals() {
		List<Movie> winningMovies = movieRepository.findByWinner(true);

		Map<String, List<Integer>> producerWins = new HashMap<>();

		for (Movie movie : winningMovies) {
			List<String> producers = movie.getMovieProducers().stream().map(p -> p.getProducer().getName().trim()).toList();

			for (String producer : producers) {
				producerWins.computeIfAbsent(producer, k -> new ArrayList<>()).add(movie.getYear());
			}
		}

		List<ProducerWinIntervalDTO> allIntervals = new ArrayList<>();

		Set<String> producerWin = producerWins.keySet();
		for (String producer : producerWin) {
			List<Integer> years = producerWins.get(producer);
			if (years != null && !years.isEmpty() && years.size() >= 2) {
				years.sort(Comparator.naturalOrder());

				for (int i = 0; i < years.size() - 1; i++) {
					Integer previousWin = years.get(i);
					Integer followingWin = years.get(i + 1);
					Integer interval = followingWin - previousWin;
					allIntervals.add(new ProducerWinIntervalDTO(producer, interval, previousWin, followingWin));
				}
			}
		}

		List<ProducerWinIntervalDTO> minIntervals = new ArrayList<>();
		List<ProducerWinIntervalDTO> maxIntervals = new ArrayList<>();

		if (!allIntervals.isEmpty()) {
			Integer minIntervalValue = allIntervals.stream()
					.mapToInt(ProducerWinIntervalDTO::getInterval)
					.min()
					.orElse(Integer.MAX_VALUE);

			minIntervals = allIntervals.stream()
					.filter(interval -> interval.getInterval().equals(minIntervalValue))
					.collect(Collectors.toList());

			Integer maxIntervalValue = allIntervals.stream()
					.mapToInt(ProducerWinIntervalDTO::getInterval)
					.max()
					.orElse(Integer.MIN_VALUE);

			maxIntervals = allIntervals.stream()
					.filter(interval -> interval.getInterval().equals(maxIntervalValue))
					.collect(Collectors.toList());
		}

		return new ProducerIntervalsDto(minIntervals, maxIntervals);
	}
}