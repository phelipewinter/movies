package br.com.movies.studio.mapper;

import br.com.movies.studio.dto.StudioDTO;
import br.com.movies.studio.dto.StudioResponseDTO;
import br.com.movies.studio.model.Studio;
import org.springframework.stereotype.Component;

@Component
public class StudioMapper {

	public StudioResponseDTO toResponseDTO(Studio studio) {
		StudioResponseDTO dto = new StudioResponseDTO();
		dto.setId(studio.getId());
		dto.setName(studio.getName());
		return dto;
	}

	public Studio toEntity(StudioDTO dto) {
		Studio studio = new Studio();
		studio.setName(dto.getName());
		return studio;
	}

	public void toEntityUpdate(Studio studio, StudioDTO dto) {
		studio.setName(dto.getName());
	}

	//	public static void movieFromDto(StudoDDTO dto, Studio Studio) {
//		Studio.setYear(dto.getYear());
//		Studio.setTitle(dto.getTitle());
//		for (String studioName : dto.getStudios()) {
//			Studio.getStudios().add(studioRepository.findByName(studioName));
//		}
//		Studio.setStudios(dto.getStudios());
//		Studio.setProducers(dto.getProducers());
//		Studio.setWinner(dto.isWinner());
//	}

}
