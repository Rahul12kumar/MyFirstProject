package com.pysch.repositories;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pysch.model.GameMode;
import com.pysch.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
 
	@Query(value = "",nativeQuery = true)
	Optional<Question> getRandomQuestion(@NotNull GameMode gameMode);

}
