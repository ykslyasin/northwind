package kodlamaio.northwind.business.abstracts;

import java.util.List;

import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Question;



public interface UserService {

	Result add(User user);
	
	/*Result findByEmail(String email);*/
	
	Result findByUsernameAndPassword(String username, String password);
	
	Result registration(User user);
	
	//Result checkQuestionWithQuestionId(int questionId);
	
	DataResult<List<User>> getAll();
	
	DataResult<User> loginAuth(String username, String password);
	
    Result addSolvedQuestionToUser(int userId, int questionId);

    Result incrementUserPoints(int userId, int questionLevel);
	
	User getById(int userId);
}
