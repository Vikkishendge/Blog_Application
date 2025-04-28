package blogapplication.Controller;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.api.exceptions.ApiException;

import blogapplication.Model.User;
import blogapplication.Payloads.JwtAuthRequest;
import blogapplication.Payloads.JwtAuthResponse;
import blogapplication.Payloads.UserDto;
import blogapplication.Repository.UserRepo;
import blogapplication.Security.JwtTokenHelper;
import blogapplication.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController	
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173"}) // Additional safety
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request, HttpServletResponse response) throws Exception {
	    System.out.println("Login attempt with: " + request.getUsername() + " and " + request.getPassword());
	    this.authenticate(request.getUsername(), request.getPassword());
	    UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
	    System.out.println("Successfully loaded user details");
	    String token = this.jwtTokenHelper.generateToken(userDetails);
	    System.out.println("Generated token: " + token);
	    
	   
	    Cookie cookie = new Cookie("jwt_token", token);
	    cookie.setHttpOnly(true); 
	    cookie.setSecure(true);    
	    cookie.setPath("/");       
	    cookie.setMaxAge(60 * 60 * 24); //1 day
	    
	    // Add cookie to the response
	    response.addCookie(cookie);
	    
	    // Prepare response data
	    JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
	    jwtAuthResponse.setToken(token);

	    UserDto userDto = new UserDto();
	    userDto.setEmail(userDetails.getUsername());
	    userDto.setName(((org.springframework.security.core.userdetails.User) userDetails).getUsername());

	    // Set user data in response
	    jwtAuthResponse.setUser(userDto);

	    return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
	    // Log the incoming request for debugging
	    System.out.println("Authenticating user: " + username);

	    // Create the authentication token from username and password
	    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
	    System.out.println("Authenticating user 11: " + username);

	    try {
	        // Attempt to authenticate the token with the authentication manager
	        this.authenticationManager.authenticate(authenticationToken);
	        System.out.println("Authentication successful for: " + username); // Log on success
	    } catch (BadCredentialsException e) {
	        System.out.println("Invalid credentials for username: " + username); // Log on failure
	        throw new ApiException("Invalid username or password !!");
	    } catch (Exception e) {
	        // Catch all other exceptions and log them
	        System.out.println("Error during authentication: " + e.getMessage());
	        throw new ApiException("Authentication failed due to unexpected error.");
	    }
	}



	// register new user api

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		System.out.println("call register method");
		System.out.println(userDto.getName());
		UserDto registeredUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}

	// get loggedin user data
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;

	@GetMapping("/current-user/")
	public ResponseEntity<UserDto> getUser(Principal principal) {
		User user = this.userRepo.findByEmail(principal.getName()).get();
		return new ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
	}

}
