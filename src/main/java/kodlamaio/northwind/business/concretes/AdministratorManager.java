package kodlamaio.northwind.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.AdministratorService;
import kodlamaio.northwind.core.dataAccess.AdministratorDao;
import kodlamaio.northwind.core.dataAccess.UserDao;
import kodlamaio.northwind.core.entities.Administrator;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.QuestionDao;
import kodlamaio.northwind.entities.concretes.Question;


@Service
public class AdministratorManager implements AdministratorService{

	private AdministratorDao administratorDao;
	private QuestionDao questionDao;
	private UserDao userDao;
	
	@Autowired
	public AdministratorManager(AdministratorDao administratorDao, QuestionDao questionDao, UserDao userDao) {
		super();
		this.administratorDao = administratorDao;
		this.questionDao = questionDao;
		this.userDao = userDao;
	}
	
	@Override
	public Result add(Administrator administrator) {
		// TODO Auto-generated method stub
		this.administratorDao.save(administrator);
		return new SuccessResult("Administrator eklendi.");
	}

	@Override
	public Result add(Question question) {
		this.questionDao.save(question);
		return new SuccessResult("Soru eklendi.");
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı eklendi");
	}
	
}
