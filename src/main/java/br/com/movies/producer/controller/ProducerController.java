package br.com.movies.producer.controller;

import br.com.movies.producer.dto.ProducerDTO;
import br.com.movies.producer.dto.ProducerResponseDTO;
import br.com.movies.producer.service.ProducerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Producers")
@RestController
@RequestMapping("/api/producers")
public class ProducerController {

	private final ProducerService producerService;

	public ProducerController(ProducerService producerService) {
		this.producerService = producerService;
	}

	@GetMapping
	public List<ProducerResponseDTO> getAllProducers() {
		return producerService.getAllProducers();
	}

	@GetMapping("/{id}")
	public ProducerResponseDTO getProducerById(@PathVariable Long id) {
		return producerService.getProducerById(id);
	}

	@PostMapping()
	public ProducerResponseDTO createProducer(@Valid @RequestBody ProducerDTO producerDTO) {
		return producerService.createProducer(producerDTO);
	}

	@PutMapping("/{id}")
	public ProducerResponseDTO updateProducer(@PathVariable Long id, @Valid @RequestBody ProducerDTO producerDTO) {
		return producerService.updateProducer(id, producerDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteProducer(@PathVariable Long id) {
		producerService.deleteProducer(id);
	}
}