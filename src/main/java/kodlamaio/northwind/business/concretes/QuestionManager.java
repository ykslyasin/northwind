package kodlamaio.northwind.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.northwind.business.abstracts.QuestionService;
import kodlamaio.northwind.core.dataAccess.UserDao;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.QuestionDao;
import kodlamaio.northwind.entities.concretes.Question;

@Service
public class QuestionManager implements QuestionService{

	private QuestionDao questionDao;
	private UserDao userDao;
	
	@Autowired
	public QuestionManager(QuestionDao questionDao, UserDao userDao) {
		super();
		this.questionDao = questionDao;
		this.userDao = userDao;
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
	public DataResult<Question> getById(int questionId) {
		return new SuccessDataResult<Question>
		(this.questionDao.getById(questionId), "Data getirildi.");
	}

	@Override
	public DataResult<List<Question>> getByQuestionLevel(int questionLevel, int userId) {
		
	    List<Question> allQuestions = questionDao.getByQuestionLevel(questionLevel);
	    
	    Optional<User> optionalUser = userDao.findById(userId);
	    
	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        
	        // Kullanıcının çözdüğü soruları filtrele
	        List<Integer> solvedQuestionIds = user.getSolvedQuestions();
	        allQuestions = allQuestions.stream()
	                .filter(question -> !solvedQuestionIds.contains(question.getQuestionId()))
	                .collect(Collectors.toList());
	    }
	    
	    return new SuccessDataResult<List<Question>>(allQuestions, "Data getirildi.");
	}



	
}
