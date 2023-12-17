package kodlamaio.northwind.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import kodlamaio.northwind.entities.concretes.Question;

public interface QuestionDao extends JpaRepository<Question, Integer>{

	Question getByQuestionGroupAndQuestionLevel(int questionGroup, int questionLevel);
	
	Question getByQuestionLevel(int questionLevel);
	
	Question getById(int questionId);
}
