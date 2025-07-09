package br.com.movies.producer.mapper;

import br.com.movies.producer.dto.ProducerDTO;
import br.com.movies.producer.dto.ProducerResponseDTO;
import br.com.movies.producer.model.Producer;
import org.springframework.stereotype.Component;

@Component
public class ProducerMapper {

	public ProducerResponseDTO toResponseDTO(Producer producer) {
		ProducerResponseDTO dto = new ProducerResponseDTO();
		dto.setId(producer.getId());
		dto.setName(producer.getName());
		return dto;
	}

	public Producer toEntity(ProducerDTO dto) {
		Producer producer = new Producer();
		producer.setName(dto.getName());
		return producer;
	}

	public void toEntityUpdate(Producer producer, ProducerDTO dto) {
		producer.setName(dto.getName());
	}

}
