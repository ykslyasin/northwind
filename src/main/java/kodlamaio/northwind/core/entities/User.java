package kodlamaio.northwind.core.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	@Email
	@NotBlank
	@NotNull
	private String email;
	
	@Column(name="password")
	@NotBlank
	@NotNull
	private String password;
	
	@NotNull
	@NotBlank
	@Column(name="username")
	private String userName;
	
    @ElementCollection
    @CollectionTable(name = "user_solved_questions", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "question_id")
    private List<Integer> solvedQuestions;
    
    /*@Column(name="user_points")
    private int userPoints;*/
    
    public void addSolvedQuestion(int questionId) {
        if (solvedQuestions == null) {
            solvedQuestions = new ArrayList<>();
        }
        solvedQuestions.add(questionId);
    }

	
	
}
