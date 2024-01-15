package kodlamaio.northwind.business.concretes;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.dataAccess.UserDao;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;
import kodlamaio.northwind.core.utilities.results.ErrorResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.entities.concretes.Question;



@Service
public class UserManager implements UserService{

	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	/*@Override
	public Result findByEmail(String email) {

		if(this.userDao.getByEmail(email).equals(email)) {
			
			return new ErrorResult("Kullanıcı bulunamadı.");
		}else {
			
			return new SuccessResult("Kullanıcı bulundu.");

		}
		
		
	}*/

	@Override
	public Result registration(User user) {
        User existingUser = userDao.getByEmail(user.getEmail());

        if (existingUser != null) {
            return new ErrorResult("Bu mail zaten kayıtlı.");
        } else {
            add(user);
            return new SuccessResult("Başarıyla kayıt olundu.");
        }
	}

	/*@Override
	public Result checkUserWithMail(String email) {
		if(findByEmail(email).isSuccess()) {
			return new ErrorResult("Bu mail zaten kayıtlı.");
		}else {
		return new SuccessResult();
		}
	}*/

	@Override
	public DataResult<List<User>> getAll() {
		
		return new SuccessDataResult<List<User>>
		(this.userDao.findAll(), "Data listelendi." );
	}

	@Override
	public Result add(User user) {
		// TODO Auto-generated method stub
		this.userDao.save(user);
		return new SuccessResult("Admin eklendi.");
	}

	@Override
	public DataResult<User> loginAuth(String username, String password) {
		
		if(findByUsernameAndPassword(username, password).isSuccess()) {
			return new SuccessDataResult<User>(this.userDao.findByuserNameAndPassword(username, password), "Giriş işlemi başarılı");
		}else {
		return new ErrorDataResult<User>(this.userDao.findByuserNameAndPassword(username, password),"Hatalı kullanıcı adı veya şifre");
	}
	}

	@Override
	public Result findByUsernameAndPassword(String username, String password) {
		
		if(this.userDao.getByuserName(username).isEmpty() || this.userDao.getByPassword(password).isEmpty()) {
			
			return new ErrorResult("Kullanıcı bulunamadı.");
		}else {
			
			return new SuccessResult("Kullanıcı bulundu.");

		}
	}
	
    @Override
    public Result addSolvedQuestionToUser(int userId, int questionId) {
    	
        Optional<User> optionalUser = userDao.findById(userId);

        if (optionalUser.isEmpty()) {
            return new ErrorResult("Kullanıcı bulunamadı.");
        }

        User user = optionalUser.get();

        if (user.getSolvedQuestions() != null && user.getSolvedQuestions().contains(questionId)) {
            return new ErrorResult("Bu soru zaten çözülmüş.");
        }
        
        user.addSolvedQuestion(questionId);
        userDao.save(user);
        return new SuccessResult("Soru başarıyla eklenmiştir.");

    }
   @Override
    public Result incrementUserPoints(int userId, int questionLevel) {
       Optional<User> optionalUser = userDao.findById(userId);

       if (optionalUser.isPresent()) {
           User user = optionalUser.get();
           int pointsToAdd;

           if (questionLevel == 0) {
               pointsToAdd = 25;
           } else {
               pointsToAdd = questionLevel * 10;
           }

           user.incrementUserPoints(pointsToAdd);
           userDao.save(user);
           return new SuccessResult("Kullanıcı puanı başarıyla artırıldı.");
       } else {
           return new ErrorResult("Kullanıcı bulunamaz.");
       }
   }

   @Override
   public User getById(int userId) {
	   	
       Optional<User> optionalUser = userDao.findById(userId);
       return optionalUser.orElse(null);
   }
}
