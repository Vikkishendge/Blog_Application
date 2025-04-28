package blogapplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blogapplication.Payloads.Responseapi;
import blogapplication.Payloads.UserDto;
import blogapplication.Service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("home/api")
public class UserController {
	
	@Autowired
	private UserService userservice;

	@PostMapping("user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
	    System.out.println("Request received: " + user.toString()); // Debug log
	    UserDto userDto = userservice.createUser(user);
	    System.out.println("Service returned: " + userDto.toString()); // Debug log
	    return new ResponseEntity<>(userDto, HttpStatus.CREATED);
	}
	
	@GetMapping("user/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") int userId)
	{
		UserDto user=this.userservice.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PutMapping("user/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto,@PathVariable int userId)
	{
		UserDto user=this.userservice.updateUser(userdto, userId);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("user")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		return ResponseEntity.ok(this.userservice.getAllUser());
	}
	
	@DeleteMapping("user/{userId}")
	public ResponseEntity<Responseapi> deleteUser(@PathVariable int userId)
	{
		this.userservice.deleteUser(userId);
		return new ResponseEntity(new Responseapi("User deleted successfully",true),HttpStatus.OK);
	}

}
