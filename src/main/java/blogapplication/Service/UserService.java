package blogapplication.Service;

import java.util.List;

import blogapplication.Payloads.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto user);
	
	public UserDto updateUser(UserDto user,int userId);
	
	public UserDto getUserById(int userId);
	
	public List<UserDto> getAllUser();
	
	public void deleteUser(int userId);
	
	UserDto registerNewUser(UserDto user);
}
