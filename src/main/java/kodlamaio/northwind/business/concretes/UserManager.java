package kodlamaio.northwind.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.dataAccess.UserDao;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.ErrorResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;

@Service
public class UserManager implements UserService{

	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public Result add(User user) {
		// TODO Auto-generated method stub
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı eklendi.");
	}

	@Override
	public DataResult<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return new SuccessDataResult<User>(this.userDao.findByEmail(email), "Kullanıcı bulundu.");
	}

	@Override
	public Result registration(User user) {
		if(checkUserWithMail(user.getEmail()).isSuccess()) {
					add(user);
					return new SuccessResult("Başarıyla kayıt olundu.");
				}else {
					return new ErrorResult("Kaydolma başarısız.");
				}
	}

	@Override
	public Result checkUserWithMail(String email) {
		if(findByEmail(email).isSuccess()) {
			return new ErrorResult("Bu mail zaten kayıtlı.");
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<List<User>> getAll() {
		
		return new SuccessDataResult<List<User>>
		(this.userDao.findAll(), "Data listelendi." );
	}
	
	
	
	

}
