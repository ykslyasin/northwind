package kodlamaio.northwind.business.abstracts;


import kodlamaio.northwind.core.utilities.results.*;
import kodlamaio.northwind.core.entities.*;
import kodlamaio.northwind.entities.concretes.Question;

public interface AdministratorService {

	Result add(Administrator administrator);
	
	Result add(Question Question);
	
	Result add(User user);
	
}
