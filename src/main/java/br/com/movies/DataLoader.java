package br.com.movies;

import br.com.movies.movie.model.Movie;
import br.com.movies.movie.model.MovieProducer;
import br.com.movies.movie.model.MovieStudio;
import br.com.movies.producer.model.Producer;
import br.com.movies.studio.model.Studio;
import br.com.movies.movie.repository.MovieRepository;
import br.com.movies.producer.repository.ProducerRepository;
import br.com.movies.studio.repository.StudioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataLoader {

	private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
	@Value("${data.loader.csv-file:}")
	private String csvFile;

	private final MovieRepository movieRepository;
	private final StudioRepository studioRepository;
	private final ProducerRepository producerRepository;

	public DataLoader(MovieRepository movieRepository, StudioRepository studioRepository, ProducerRepository producerRepository) {
		this.movieRepository = movieRepository;
		this.studioRepository = studioRepository;
		this.producerRepository = producerRepository;
	}

	@Bean
	CommandLineRunner loadData(JdbcTemplate jdbcTemplate) {
		return args -> {
            log.info("CSV file uploaded: {}", csvFile);
			InputStream inputStream = csvFile != null && !csvFile.isEmpty() ? new FileInputStream(csvFile) : getClass().getClassLoader().getResourceAsStream("Movielist.csv");

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

				String line;
				reader.readLine();

				Map<String, Studio> studioMap = new HashMap<>();
				Map<String, Producer> producerMap = new HashMap<>();

				while ((line = reader.readLine()) != null) {
					String[] fields = line.split(";");

					int movieYear = Integer.parseInt(fields[0].trim());
					String title = fields[1].trim();
					String studios = fields[2].trim();
					String producers = fields[3].trim();
					boolean winner = fields.length > 4 && fields[4].trim().equalsIgnoreCase("yes");

					Movie movie = new Movie();
					movie.setYear(movieYear);
					movie.setTitle(title);
					movie.setWinner(winner);

					for (String studioName : studios.split(" and |, and |, ")) {
						studioName = studioName.trim();
						Studio studio = studioMap.get(studioName.toUpperCase());
						if (studio == null) {
							studio = studioRepository.save(new Studio(studioName));
						}
						studioMap.put(studio.getName().toUpperCase(), studio);
						movie.getMovieStudios().add(new MovieStudio(movie, studio));
					}

					//to solve the producer's problem "Roland Joffé" and "Brian Robbinsand Sharla Sumpter Bridgett"
					producers = producers.replaceAll("([a-z]{3,})and\\s", "$1 and ").replaceAll("\\s+", " ");
					for (String producerName : producers.split(" and |, and |, ")) {
						producerName = producerName.trim();
						Producer producer = producerMap.get(producerName.toUpperCase());
						if (producer == null) {
							producer = producerRepository.save(new Producer(producerName));
						}
						producerMap.put(producer.getName().toUpperCase(), producer);
						movie.getMovieProducers().add(new MovieProducer(movie, producer));
					}

					movieRepository.save(movie);
				}
			}
		};
	}
}