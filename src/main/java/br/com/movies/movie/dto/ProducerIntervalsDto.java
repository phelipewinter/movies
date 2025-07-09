package br.com.movies.movie.dto;

import java.util.List;

public class ProducerIntervalsDto {

	private List<ProducerWinIntervalDTO> min;
	private List<ProducerWinIntervalDTO> max;

	public ProducerIntervalsDto(List<ProducerWinIntervalDTO> min, List<ProducerWinIntervalDTO> max) {
		this.min = min;
		this.max = max;
	}

	public List<ProducerWinIntervalDTO> getMin() {
		return min;
	}

	public void setMin(List<ProducerWinIntervalDTO> min) {
		this.min = min;
	}

	public List<ProducerWinIntervalDTO> getMax() {
		return max;
	}

	public void setMax(List<ProducerWinIntervalDTO> max) {
		this.max = max;
	}
}
