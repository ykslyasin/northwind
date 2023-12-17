package kodlamaio.northwind.business.abstracts;

import java.util.List;

import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Question;

public interface QuestionService {

	DataResult<List<Question>> getAll();
	
	Result add(Question question);
	
	DataResult<Question> getByQuestionGroupAndQuestionLevel(int questionGroup, int questionLevel);
	
	DataResult<Question> getById(int questionId);
	
	DataResult<Question> getByQuestionLevel(int questionLevel);
}
