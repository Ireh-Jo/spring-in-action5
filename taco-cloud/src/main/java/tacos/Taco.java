package tacos;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date createdAt;

	@NotNull
	@Size(min=5, message="이름은 최소 5글자 이상 입력해주시기 바랍니다.")
	private String name;
	
	@ManyToMany(targetEntity=Ingredient.class)
	@Size(min=1, message="재료는 최소 1개 이상 선택해주시기 바랍니다.")
	private List<Ingredient> ingredients;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
