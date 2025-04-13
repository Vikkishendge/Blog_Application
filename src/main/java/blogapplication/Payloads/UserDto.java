package blogapplication.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserDto {

    private int id;
	
	@NotEmpty
	@Size(min=3,message = "name must be minimum gerater than 4 character")
	private String name;
	
	@Email(message = "this email not found or this email not valid")
	private String email;
	
	@NotEmpty
	//@Size(min=4,max = 10,message = "password must be minimum 4 characters and garater 10 characters")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(int id,
			@NotEmpty @Size(min = 3, message = "name must be minimum gerater than 4 character") String name,
			@Email(message = "this email not found or this email not valid") String email,
		//	@NotEmpty @Size(min = 4, max = 10, message = "password must be minimum 4 characters and garater 10 characters")
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
}
