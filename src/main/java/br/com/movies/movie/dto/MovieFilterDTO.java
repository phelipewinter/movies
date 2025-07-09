package br.com.movies.movie.dto;

import java.util.List;

public class MovieFilterDTO {

	private String title;
	private List<Long> studiosId;
	private List<Long> producersId;
	private Boolean winner;

	public MovieFilterDTO(String title, List<Long> studiosId, List<Long> producersId, Boolean winner) {
		this.title = title;
		this.studiosId = studiosId;
		this.producersId = producersId;
		this.winner = winner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Long> getStudiosId() {
		return studiosId;
	}

	public void setStudiosId(List<Long> studiosId) {
		this.studiosId = studiosId;
	}

	public List<Long> getProducersId() {
		return producersId;
	}

	public void setProducersId(List<Long> producersId) {
		this.producersId = producersId;
	}

	public Boolean getWinner() {
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}
}
