package br.com.movies.producer.service;

import br.com.movies.exception.NotFoundException;
import br.com.movies.producer.dto.ProducerDTO;
import br.com.movies.producer.dto.ProducerResponseDTO;
import br.com.movies.producer.mapper.ProducerMapper;
import br.com.movies.producer.model.Producer;
import br.com.movies.producer.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerService {

	@Autowired
	private final ProducerRepository producerRepository;

	@Autowired
	private final ProducerMapper producerMapper;

	public ProducerService(ProducerRepository producerRepository, ProducerMapper producerMapper) {
		this.producerRepository = producerRepository;
		this.producerMapper = producerMapper;
	}

	public List<ProducerResponseDTO> getAllProducers() {
		return producerRepository.findAll().stream()
				.map(producerMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	public ProducerResponseDTO getProducerById(Long id) {
		return producerRepository.findById(id)
				.map(producerMapper::toResponseDTO)
				.orElseThrow(() -> new NotFoundException("Producer with ID " + id + " not found."));
	}

	public ProducerResponseDTO createProducer(ProducerDTO producerDTO) {
		Producer producer = producerMapper.toEntity(producerDTO);

		return producerMapper.toResponseDTO(producerRepository.save(producer));
	}

	public ProducerResponseDTO updateProducer(Long id, ProducerDTO producerDTO) {
		Producer producer = producerRepository.findById(id).orElseThrow(() -> new NotFoundException("Producer with ID " + id + " not found."));
		producerMapper.toEntityUpdate(producer, producerDTO);

		return producerMapper.toResponseDTO(producerRepository.save(producer));
	}

	public void deleteProducer(Long id) {
		producerRepository.deleteById(id);
	}
}