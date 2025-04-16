package blogapplication.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor

@Entity
@Table(name="posts")
public class Posts {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column(nullable=false, length=100)
	String postTitle;
	
	String content;
	String image;	
	Date upload_date;
	
	@ManyToOne
	@JsonBackReference("user-post")
	User user;
	
	@ManyToOne
    @JoinColumn(name="category_id")
	@JsonBackReference("category-post")
	Category category;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	@JsonManagedReference("comment-post")
	Set<Comment> comment=new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	public Posts(int id, String postTitle, String content, String image, Date upload_date, User user, Category category,
			Set<Comment> comment) {
		super();
		this.id = id;
		this.postTitle = postTitle;
		this.content = content;
		this.image = image;
		this.upload_date = upload_date;
		this.user = user;
		this.category = category;
		this.comment = comment;
	}

	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	
}
