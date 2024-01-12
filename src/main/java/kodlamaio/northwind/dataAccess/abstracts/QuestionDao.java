package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import kodlamaio.northwind.entities.concretes.Question;

public interface QuestionDao extends JpaRepository<Question, Integer>{
	
	List<Question> getByQuestionLevel(int questionLevel);
	
	Question getById(int questionId);
}
