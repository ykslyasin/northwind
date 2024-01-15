package kodlamaio.northwind.business.concretes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import kodlamaio.northwind.core.dataAccess.UserDao;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessResult;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserManagerTest {
	
	@Mock
	private UserDao userDao;
	
	@InjectMocks
	UserManager userManager = new UserManager(userDao);
	
	/*@Test
	public void testGetAllUser() {
		
		List<Integer> mockQuestions = new ArrayList<>();
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User(0, "yasin@gmail.com", "yasin123", "yasin", mockQuestions));


        when(userDao.findAll()).thenReturn(mockUsers);


        DataResult<List<User>> result = userManager.getAll();


        assertEquals(mockUsers.size(), result.getData().size());
		
		
	}*/
	
    @Test
    public void testRegistration_Failure() {

        User existingUser = new User();
        existingUser.setEmail("existing@example.com");

        User newUser = new User();
        newUser.setEmail("existing@example.com");


        when(userDao.getByEmail(existingUser.getEmail())).thenReturn(existingUser);


        Result result = userManager.registration(newUser);

        assertEquals(false, result.isSuccess());
        assertEquals("Bu mail zaten kayıtlı.", ((ErrorResult) result).getMessage());
    }

    @Test
    public void testRegistration_Success() {

        User newUser = new User();
        newUser.setEmail("new@example.com"); 


        when(userDao.getByEmail(newUser.getEmail())).thenReturn(null);


        Result result = userManager.registration(newUser);


        assertEquals(true, result.isSuccess());
        assertEquals("Başarıyla kayıt olundu.", ((SuccessResult) result).getMessage());
    }


}
