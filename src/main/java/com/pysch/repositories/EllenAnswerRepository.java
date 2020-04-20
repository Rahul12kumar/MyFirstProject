package com.pysch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pysch.model.EllenAnswer;
import com.pysch.model.Question;

@Repository
public interface EllenAnswerRepository extends JpaRepository<EllenAnswer,Long>{

	@Query(value="",nativeQuery = true)
	void getRandomEllenAnswer(Question questions);

}
