package tacos;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {

	@NotNull
	@Size(min=5, message="이름은 최소 5글자 이상 입력해주시기 바랍니다.")
	private String name;
	
	@Size(min=1, message="재료는 최소 1개 이상 선택해주시기 바랍니다.")
	private List<String> ingredients;
}
