package br.com.movies.producer.model;

import br.com.movies.movie.model.MovieProducer;
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
@Table(name = "producer")
public class Producer {

	private Long id;

	private String name;
	private Set<MovieProducer> movieProducers = new HashSet<>();

	public Producer() {
	}

	public Producer(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "producer")
	public Set<MovieProducer> getMovieProducers() {
		return movieProducers;
	}

	public void setMovieProducers(Set<MovieProducer> movieProducers) {
		this.movieProducers = movieProducers;
	}
}
