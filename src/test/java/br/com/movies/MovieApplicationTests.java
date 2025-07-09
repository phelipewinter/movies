package br.com.movies;

import br.com.movies.movie.dto.ProducerIntervalsDto;
import br.com.movies.movie.dto.ProducerWinIntervalDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testGetProducerWinIntervals() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/producers/intervals")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();

		ProducerIntervalsDto dto = objectMapper.readValue(jsonResponse, ProducerIntervalsDto.class);

		assertNotNull(dto.getMin(), "Min intervals list should not be null");
		assertFalse(dto.getMin().isEmpty(), "Min intervals list should not be empty");

		List<ProducerWinIntervalDTO> minIntervals = dto.getMin();
		minIntervals.sort(Comparator.comparing(ProducerWinIntervalDTO::getProducer));

		boolean foundJoelSilverMin = minIntervals.stream().anyMatch(p ->
				"Joel Silver".equals(p.getProducer()) &&
						p.getInterval().equals(1) &&
						p.getPreviousWin().equals(1990) &&
						p.getFollowingWin().equals(1991)
		);
		assertTrue(foundJoelSilverMin, "Joel Silver with 1-year interval should be in min list");

		assertNotNull(dto.getMax(), "Max intervals list should not be null");
		assertFalse(dto.getMax().isEmpty(), "Max intervals list should not be empty");

		List<ProducerWinIntervalDTO> maxIntervals = dto.getMax();
		maxIntervals.sort(Comparator.comparing(ProducerWinIntervalDTO::getProducer));

		boolean foundMatthewVaughnMax = maxIntervals.stream().anyMatch(p ->
				"Matthew Vaughn".equals(p.getProducer()) &&
						p.getInterval().equals(13) &&
						p.getPreviousWin().equals(2002) &&
						p.getFollowingWin().equals(2015)
		);
		assertTrue(foundMatthewVaughnMax, "Matthew Vaughn with 13-year interval should be in max list");
	}
}
