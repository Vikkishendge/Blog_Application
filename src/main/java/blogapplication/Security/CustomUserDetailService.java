package blogapplication.Security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import blogapplication.Exceptions.ResourceNotFoundException;
import blogapplication.Model.User;
import blogapplication.Repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
		@Autowired
		private UserRepo userRepo;

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
			System.out.println("loaduser by username");
			User user = userRepo.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

// Check if user is null
if (user == null) {
    throw new UsernameNotFoundException("User not found with email: " + username);
}

System.out.println("User Email: " + user.getEmail());
System.out.println("User Name: " + user.getName());

		    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
		}


	}

