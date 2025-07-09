package br.com.movies.movie.mapper;

import br.com.movies.movie.dto.MovieDTO;
import br.com.movies.movie.dto.MovieResponseDTO;
import br.com.movies.movie.model.Movie;
import br.com.movies.movie.model.MovieProducer;
import br.com.movies.movie.model.MovieStudio;
import br.com.movies.producer.mapper.ProducerMapper;
import br.com.movies.producer.model.Producer;
import br.com.movies.studio.mapper.StudioMapper;
import br.com.movies.studio.model.Studio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

	private final StudioMapper studioMapper;
	private final ProducerMapper producerMapper;

	public MovieMapper(StudioMapper studioMapper, ProducerMapper producerMapper) {
		this.studioMapper = studioMapper;
		this.producerMapper = producerMapper;
	}

	public MovieResponseDTO toResponseDTO(Movie movie) {
		MovieResponseDTO dto = new MovieResponseDTO();
		dto.setId(movie.getId());
		dto.setYear(movie.getYear());
		dto.setTitle(movie.getTitle());
		dto.setStudios(movie.getMovieStudios().stream().map(MovieStudio::getStudio).map(studioMapper::toResponseDTO).collect(Collectors.toSet()));
		dto.setProducers(movie.getMovieProducers().stream().map(MovieProducer::getProducer).map(producerMapper::toResponseDTO).collect(Collectors.toSet()));
		dto.setWinner(movie.isWinner());
		return dto;
	}

	public Movie toEntity(MovieDTO dto, List<Studio> studios, List<Producer> producers) {
		Movie movie = new Movie();
		movie.setYear(dto.getYear());
		movie.setTitle(dto.getTitle());
		movie.getMovieStudios().addAll(studios.stream().map(studio -> new MovieStudio(movie, studio)).collect(Collectors.toSet()));
		movie.getMovieProducers().addAll(producers.stream().map(producer -> new MovieProducer(movie, producer)).collect(Collectors.toSet()));
		movie.setWinner(dto.isWinner());

		return movie;
	}

	public void toEntityUpdate(Movie movie, MovieDTO dto, List<Studio> studios, List<Producer> producers) {
		movie.setYear(dto.getYear());
		movie.setTitle(dto.getTitle());
		movie.getMovieStudios().clear();
		movie.getMovieStudios().addAll(studios.stream().map(studio -> new MovieStudio(movie, studio)).collect(Collectors.toSet()));
		movie.getMovieProducers().clear();
		movie.getMovieProducers().addAll(producers.stream().map(producer -> new MovieProducer(movie, producer)).collect(Collectors.toSet()));
		movie.setWinner(dto.isWinner());
	}
}
