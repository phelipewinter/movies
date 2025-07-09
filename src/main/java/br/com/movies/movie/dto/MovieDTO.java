package br.com.movies.movie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class MovieDTO {

	private int year;

	@NotBlank(message = "Title is required")
	private String title;

	@NotEmpty(message = "Studio is mandatory")
	private Set<Long> studios;

	@NotEmpty(message = "The producer is mandatory")
	private Set<Long> producers;

	@NotNull(message = "The winner is mandatory")
	private boolean winner;

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

	public Set<Long> getStudios() {
		return studios;
	}

	public void setStudios(Set<Long> studios) {
		this.studios = studios;
	}

	public Set<Long> getProducers() {
		return producers;
	}

	public void setProducers(Set<Long> producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
