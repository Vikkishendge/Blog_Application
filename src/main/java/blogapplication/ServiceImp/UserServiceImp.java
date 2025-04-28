package blogapplication.ServiceImp;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blogapplication.Exceptions.ResourceNotFoundException;
import blogapplication.Model.User;
import blogapplication.Payloads.UserDto;
import blogapplication.Repository.UserRepo;
import blogapplication.Service.UserService;

@Service
class UserServiceImp implements UserService{
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	 public UserDto createUser(UserDto userDto) {
		
        System.out.println("Received DTO: " + userDto.toString()); // Debug log
        
        User user = this.modelmapper.map(userDto, User.class);
        System.out.println("Mapped User: " + user.toString()); // Debug log
        
        
        user.setCreated_at(new Date());
        
        User savedUser = this.userRepo.save(user);
        System.out.println("Saved User: " + savedUser.toString()); // Debug log
        
        UserDto responseDto=this.modelmapper.map(savedUser, UserDto.class);
        responseDto.setPassword(null);
        
        return responseDto;
    }

	@Override
	public UserDto updateUser(UserDto userdto, int userId ) {
		User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
      	user.setCreated_at(new Date());
		user.setBio(userdto.getBio());
		
		User updateUser=this.userRepo.save(user);
		
		return this.modelmapper.map(updateUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(int userId) {
        User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		UserDto userdto1=this.modelmapper.map(user, UserDto.class);	
		return userdto1;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> user=this.userRepo.findAll();
		
		List<UserDto> userdto=user.stream().map(users->this.modelmapper.map(users, UserDto.class)).collect(Collectors.toList());
		return userdto;
	}

	@Override
	public void deleteUser(int userId) {
        User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		this.userRepo.delete(user);   
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelmapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setCreated_at(new Date());
		
		
		User newUser = this.userRepo.save(user);

		UserDto responseDto=this.modelmapper.map(newUser, UserDto.class);
        responseDto.setPassword(null);
        
		return responseDto;
	}


}
