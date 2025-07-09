package br.com.movies.movie.repository;

import br.com.movies.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(
			"SELECT DISTINCT m" +
					"  FROM Movie m" +
					"  LEFT JOIN m.movieStudios ms" +
					"  LEFT JOIN m.movieProducers mp" +
					" WHERE (:title IS NULL OR UPPER(m.title) LIKE UPPER('%' || :title || '%'))" +
					"   AND (:studiosId IS NULL OR ms.studio.id IN (:studiosId)) " +
					"   AND (:producersId IS NULL OR mp.producer.id IN (:producersId)) " +
					"   AND (:winner IS NULL OR m.winner = :winner)" +
					" ORDER BY m.id")
	List<Movie> findMoviesByFilters(@Param("title") String title, @Param("studiosId") List<Long> studiosId, @Param("producersId") List<Long> producersId, @Param("winner") Boolean winner);

	List<Movie> findByWinner(Boolean winner);

}
