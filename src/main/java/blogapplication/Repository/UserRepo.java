package blogapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogapplication.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

}
