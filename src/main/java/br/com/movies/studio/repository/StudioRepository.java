package br.com.movies.studio.repository;

import br.com.movies.studio.model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio, Long> {

}
