package br.com.movies.movie.controller;

import br.com.movies.movie.dto.MovieDTO;
import br.com.movies.movie.dto.MovieFilterDTO;
import br.com.movies.movie.dto.MovieResponseDTO;
import br.com.movies.movie.dto.ProducerIntervalsDto;
import br.com.movies.movie.service.MovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Movies")
@RestController
@RequestMapping("/api/movies")
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping
	public List<MovieResponseDTO> getMovies(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) List<Long> studiosId,
			@RequestParam(required = false) List<Long> producersId,
			@RequestParam(required = false) Boolean winner) {
		MovieFilterDTO movieFilterDTO = new MovieFilterDTO(title, studiosId, producersId, winner);
		return movieService.getMovies(movieFilterDTO);
	}

	@GetMapping("/{id}")
	public MovieResponseDTO getMovieById(@PathVariable Long id) {
		return movieService.getMovieById(id);
	}

	@PostMapping()
	public MovieResponseDTO createMovie(@Valid @RequestBody MovieDTO movieDTO) {
		return movieService.createMovie(movieDTO);
	}

	@PutMapping("/{id}")
	public MovieResponseDTO updateMovie(@PathVariable Long id, @Valid @RequestBody MovieDTO movieDTO) {
		return movieService.updateMovie(id, movieDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteMovie(@PathVariable Long id) {
		movieService.deleteMovie(id);
	}

	@GetMapping("/producers/intervals")
	public ProducerIntervalsDto getProducerWinIntervals() {
		return movieService.getProducerWinIntervals();
	}
}