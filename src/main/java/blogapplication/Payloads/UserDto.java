package blogapplication.Payloads;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


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
	
	private Date created_at;
	
	private String bio;

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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public UserDto(int id,
			@NotEmpty @Size(min = 3, message = "name must be minimum gerater than 4 character") String name,
			@Email(message = "this email not found or this email not valid") String email, @NotEmpty String password,
			Date created_at, String bio) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.created_at = created_at;
		this.bio = bio;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
