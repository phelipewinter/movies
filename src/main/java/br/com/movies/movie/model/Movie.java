package br.com.movies.movie.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {

	private Long id;
	private int year;
	private String title;
	private Set<MovieStudio> movieStudios = new HashSet<>();
	private Set<MovieProducer> movieProducers = new HashSet<>();
	private boolean winner;

	public Movie() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "movie_year")
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(length = 4000)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<MovieStudio> getMovieStudios() {
		return movieStudios;
	}

	public void setMovieStudios(Set<MovieStudio> movieStudios) {
		this.movieStudios = movieStudios;
	}

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<MovieProducer> getMovieProducers() {
		return movieProducers;
	}

	public void setMovieProducers(Set<MovieProducer> movieProducers) {
		this.movieProducers = movieProducers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}