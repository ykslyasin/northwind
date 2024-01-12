package kodlamaio.northwind.api.controllers;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.northwind.business.abstracts.QuestionService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Question;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin
public class QuestionsController {

	private QuestionService questionService;
	
	@Autowired
	public QuestionsController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}

	@GetMapping("/getall")
	public DataResult<List<Question>> getAll() {
		return this.questionService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Question question) {
		
		return this.questionService.add(question);
	}
	
	@GetMapping("/getById")
	public DataResult<Question> getById(@RequestParam int questionId){
		
		return this.questionService.getById(questionId);
	}
	
	@GetMapping("/getByLevel")
	public DataResult<List<Question>> getByQuestionLevel(@RequestParam int questionLevel){
		
		return this.questionService.getByQuestionLevel(questionLevel);
	}
	
	/*@ExceptionHandler(MethodArgumentNotValidException.class)
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
	}*/
	
	

	
}