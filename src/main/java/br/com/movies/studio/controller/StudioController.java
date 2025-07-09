package br.com.movies.studio.controller;

import br.com.movies.studio.dto.StudioDTO;
import br.com.movies.studio.dto.StudioResponseDTO;
import br.com.movies.studio.service.StudioService;
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

@Tag(name = "Studios")
@RestController
@RequestMapping("/api/studios")
public class StudioController {

	private final StudioService studioService;

	public StudioController(StudioService studioService) {
		this.studioService = studioService;
	}

	@GetMapping
	public List<StudioResponseDTO> getAllStudios() {
		return studioService.getAllStudios();
	}

	@GetMapping("/{id}")
	public StudioResponseDTO getStudioById(@PathVariable Long id) {
		return studioService.getStudioById(id);
	}

	@PostMapping()
	public StudioResponseDTO createStudio(@Valid @RequestBody StudioDTO studioDTO) {
		return studioService.createStudio(studioDTO);
	}

	@PutMapping("/{id}")
	public StudioResponseDTO updateStudio(@PathVariable Long id, @Valid @RequestBody StudioDTO studioDTO) {
		return studioService.updateStudio(id, studioDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteStudio(@PathVariable Long id) {
		studioService.deleteStudio(id);
	}
}