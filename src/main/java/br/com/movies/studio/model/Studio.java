package br.com.movies.studio.model;

import br.com.movies.movie.model.MovieStudio;
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
@Table(name = "studio")
public class Studio {

	private Long id;
	private String name;
	private Set<MovieStudio> movieStudios = new HashSet<>();

	public Studio() {
	}

	public Studio(String name) {
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

	@OneToMany(mappedBy = "studio")
	public Set<MovieStudio> getMovieStudios() {
		return movieStudios;
	}

	public void setMovieStudios(Set<MovieStudio> movieStudios) {
		this.movieStudios = movieStudios;
	}
}
