package br.com.movies.studio.service;

import br.com.movies.exception.NotFoundException;
import br.com.movies.studio.dto.StudioDTO;
import br.com.movies.studio.dto.StudioResponseDTO;
import br.com.movies.studio.mapper.StudioMapper;
import br.com.movies.studio.model.Studio;
import br.com.movies.studio.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioService {

	@Autowired
	private final StudioRepository studioRepository;

	@Autowired
	private final StudioMapper studioMapper;

	public StudioService(StudioRepository studioRepository, StudioMapper studioMapper) {
		this.studioRepository = studioRepository;
		this.studioMapper = studioMapper;
	}

	public List<StudioResponseDTO> getAllStudios() {
		return studioRepository.findAll().stream()
				.map(studioMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	public StudioResponseDTO getStudioById(Long id) {
		return studioRepository.findById(id)
				.map(studioMapper::toResponseDTO)
				.orElseThrow(() -> new NotFoundException("Studio with ID " + id + " not found."));
	}

	public StudioResponseDTO createStudio(StudioDTO studioDTO) {
		Studio studio = studioMapper.toEntity(studioDTO);

		return studioMapper.toResponseDTO(studioRepository.save(studio));
	}

	public StudioResponseDTO updateStudio(Long id, StudioDTO studioDTO) {
		Studio studio = studioRepository.findById(id).orElseThrow(() -> new NotFoundException("Studio with ID " + id + " not found."));
		studioMapper.toEntityUpdate(studio, studioDTO);

		return studioMapper.toResponseDTO(studioRepository.save(studio));
	}

	public void deleteStudio(Long id) {
		studioRepository.deleteById(id);
	}
}