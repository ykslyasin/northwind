package kodlamaio.northwind.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;
import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;
import kodlamaio.northwind.core.utilities.results.Result;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value="/api/users")
@CrossOrigin
public class UsersController {

	private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<User>> getAll() {
		return this.userService.getAll();
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<?> add(@Valid @RequestBody User user) {
		
		return ResponseEntity.ok(userService.add(user));
	}
	
	@PostMapping("/registration")
	public Result registration(@RequestBody User user) {
		return this.userService.registration(user);
	}
	
	@PostMapping("/incrementUserPoints")
	public Result incrementUserPoints(@RequestParam int userId, @RequestParam int questionLevel) {
		return this.userService.incrementUserPoints(userId, questionLevel);
	}
	
	/*@GetMapping("/findByEmail")
	public Result findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.userService.findByEmail(email);
	}*/
	
	/*@GetMapping("/checkUserWithMail")
	public Result checkUserWithMail(String email) {
		// TODO Auto-generated method stub
		return this.userService.checkUserWithMail(email);
	}*/
	
	@GetMapping("/loginAuth")
	public DataResult<User> loginAuth(String username, String password){
		
		return this.userService.loginAuth(username, password);
	}
	
	@PostMapping("/addSolvedQuestion")
	public Result addSolvedQuestionToUser(@RequestParam int userId, @RequestParam int questionId) {

		    //Result result = userService.addSolvedQuestionToUser(userId, questionId);

		    return this.userService.addSolvedQuestionToUser(userId, questionId);
		}
	
	@GetMapping("/getById")
	public User getById(@RequestParam int userId){
		
		return this.userService.getById(userId);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException
	(MethodArgumentNotValidException exceptions){
		
		Map<String,String> validationErrors = new HashMap<String,String>();
		
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());	
		}
		
		ErrorDataResult<Object> errors 
		= new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
		return errors;
	}
	
}
