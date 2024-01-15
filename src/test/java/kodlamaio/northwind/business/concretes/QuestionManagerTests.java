package kodlamaio.northwind.business.concretes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import kodlamaio.northwind.business.concretes.QuestionManager;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.QuestionDao;
import kodlamaio.northwind.entities.concretes.Question;

@SpringBootTest
public class QuestionManagerTests {

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionManager questionManager;

    @Test
    public void getAll_WhenDataExists_ReturnsSuccessDataResult() {
        // Arrange
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        Mockito.when(questionDao.findAll()).thenReturn(questions);

        // Act
        DataResult<List<Question>> result = questionManager.getAll();

        // Assert
        assertTrue(result.isSuccess());
        assertEquals("Data listelendi.", result.getMessage());
        assertNotNull(result.getData());
        assertEquals(1, result.getData().size());
    }

    @Test
    public void add_ValidQuestion_ReturnsSuccessResult() {
        // Arrange
        Question questionToAdd = new Question();

        // Act
        Result result = questionManager.add(questionToAdd);

        // Assert
        assertTrue(result.isSuccess());
        assertEquals("Soru eklendi.", result.getMessage());
    }

    @Test
    public void getById_ExistingId_ReturnsSuccessDataResult() {
        // Arrange
        int existingQuestionId = 1;
        Mockito.when(questionDao.getById(existingQuestionId)).thenReturn(new Question());

        // Act
        DataResult<Question> result = questionManager.getById(existingQuestionId);

        // Assert
        assertTrue(result.isSuccess());
        assertEquals("Data getirildi.", result.getMessage());
        assertNotNull(result.getData());
    }

    @Test
    public void getByQuestionLevel_ExistingLevel_ReturnsSuccessDataResult() {
        // Arrange
        int existingQuestionLevel = 1;
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        Mockito.when(questionDao.getByQuestionLevel(existingQuestionLevel)).thenReturn(questions);

        // Act
        DataResult<List<Question>> result = questionManager.getByQuestionLevel(existingQuestionLevel);

        // Assert
        assertTrue(result.isSuccess());
        assertEquals("Data getirildi.", result.getMessage());
        assertNotNull(result.getData());
        assertEquals(1, result.getData().size());
    }
}
