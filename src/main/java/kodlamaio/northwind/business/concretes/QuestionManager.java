package kodlamaio.northwind.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.northwind.business.abstracts.QuestionService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.QuestionDao;
import kodlamaio.northwind.entities.concretes.Question;

@Service
public class QuestionManager implements QuestionService{

	private QuestionDao questionDao;
	
	@Autowired
	public QuestionManager(QuestionDao questionDao) {
		super();
		this.questionDao = questionDao;
	}

	@Override
	public DataResult<List<Question>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Question>>
		(this.questionDao.findAll(), "Data listelendi." );
				
	}

	@Override
	public Result add(Question question) {
		this.questionDao.save(question);
		return new SuccessResult("Soru eklendi.");
	}

	@Override
	public DataResult<Question> getByQuestionGroupAndQuestionLevel(int questionGroup, int questionLevel) {
		
		return new SuccessDataResult<Question>
		(this.questionDao.getByQuestionGroupAndQuestionLevel(questionGroup, questionLevel), "Data getirildi.");
	}

	@Override
	public DataResult<Question> getById(int questionId) {
		return new SuccessDataResult<Question>
		(this.questionDao.getById(questionId));
	}



	
}
