package BlogApplication.BlogApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import blogapplication.Payloads.UserDto;
import blogapplication.Service.UserService;
import jakarta.validation.Valid;

@SpringBootTest
class BlogApplicationTests {


	@Autowired
	private UserService userservice;

	@Test
	@PostMapping("/user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user)
	{
		System.out.println("Userdto "+user.toString());
	
		UserDto userdto=userservice.createUser(user);
		return new ResponseEntity<>(userdto,HttpStatus.CREATED);
	}
}
