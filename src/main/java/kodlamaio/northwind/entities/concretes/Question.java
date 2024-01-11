package kodlamaio.northwind.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="questions")
@AllArgsConstructor
@NoArgsConstructor
public class Question {

	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="question_id")
		private int questionId;
		
		@Column(name="movie_name")
		private String movieName;
		
		@NotNull
		@NotBlank
		@Column(name="question_level")
		private int questionLevel;
		
		@NotNull
		@NotBlank
		@Column(name="question_answer")
		private String answer;
		
		@NotNull
		@NotBlank
		@Column(name="wrong_answer")
		private String wrongAnswer;
		
		@NotNull
		@NotBlank
		@Column(name="question_url")
		private String questionUrl;
		

}
