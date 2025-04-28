package blogapplication.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(nullable=false, length=100)
    private String name;
    
    @Column(unique=true, nullable=false)
    private String email;

    private String password;
    
    private Date created_at;
    
    private String bio;
    
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    @JsonManagedReference("user-post")
    private List<Posts> post = new ArrayList<>();

    // Constructors, Getters, Setters
    public User() {
        super();
    }

    public User(int id, String name, String email, String password, Date created_at, String bio, List<Posts> post) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.bio = bio;
        this.post = post;
    }

    // Getters and Setters for fields
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

    public String getPassword1() {
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

    public List<Posts> getPost() {
        return post;
    }

    public void setPost(List<Posts> post) {
        this.post = post;
    }

    // Implementing UserDetails methods

    @Override
    public String getUsername() {
        return this.email; // Return email as the username
    }

    @Override
    public String getPassword() {
        return this.password; // Return the password
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming accounts don't expire
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming accounts aren't locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assuming credentials don't expire
    }

    @Override
    public boolean isEnabled() {
        return true; // Assuming the user is always enabled, you can modify this if needed
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        // Since you don't want roles, return an empty list
        return new ArrayList<>();
    }
}
