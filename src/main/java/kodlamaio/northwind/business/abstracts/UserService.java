package kodlamaio.northwind.business.abstracts;

import java.util.List;

import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;



public interface UserService {

	Result add(User user);
	
	/*Result findByEmail(String email);*/
	
	Result findByUsernameAndPassword(String username, String password);
	
	Result registration(User user);
	
	/*Result checkUserWithMail(String email);*/
	
	DataResult<List<User>> getAll();
	
	DataResult<User> loginAuth(String username, String password);
	
}
