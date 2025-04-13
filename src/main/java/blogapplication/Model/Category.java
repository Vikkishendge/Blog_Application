package blogapplication.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;


//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int category_id;
	
	String title;
	String description;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	List<Posts> post=new ArrayList<>();

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Posts> getPost() {
		return post;
	}

	public void setPost(List<Posts> post) {
		this.post = post;
	}

	public Category(int category_id, String title, String description, List<Posts> post) {
		super();
		this.category_id = category_id;
		this.title = title;
		this.description = description;
		this.post = post;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
