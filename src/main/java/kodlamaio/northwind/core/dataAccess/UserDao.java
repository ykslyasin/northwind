package kodlamaio.northwind.core.dataAccess;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.entities.concretes.Question;


public interface UserDao extends JpaRepository<User, Integer>{

	User findByEmail(String email);
	
	User getByEmail(String email);
	
	List<User>getByuserName(String username);
	
	List<User> getByPassword(String password);
	
	//List<User> getById(int id);
	
	User findByuserNameAndPassword(String userName, String password);
	
    User getByIdAndSolvedQuestionsContaining(int userId, int questionId);
    
	User getById(int userId);


	
}

