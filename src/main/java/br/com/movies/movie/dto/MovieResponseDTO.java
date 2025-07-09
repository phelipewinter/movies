package br.com.movies.movie.dto;

import br.com.movies.producer.dto.ProducerResponseDTO;
import br.com.movies.studio.dto.StudioResponseDTO;

import java.util.Set;

public class MovieResponseDTO {

	private Long id;
	private int year;
	private String title;
	private Set<StudioResponseDTO> studios;
	private Set<ProducerResponseDTO> producers;
	private boolean winner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<StudioResponseDTO> getStudios() {
		return studios;
	}

	public void setStudios(Set<StudioResponseDTO> studios) {
		this.studios = studios;
	}

	public Set<ProducerResponseDTO> getProducers() {
		return producers;
	}

	public void setProducers(Set<ProducerResponseDTO> producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
