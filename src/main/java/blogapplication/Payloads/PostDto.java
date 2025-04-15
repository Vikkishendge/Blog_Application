package blogapplication.Payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import blogapplication.Model.Category;
import blogapplication.Model.Comment;
import blogapplication.Model.User;

public class PostDto {


	int id ;
	
	String postTitle;
	String content;
	String image;
	Date upload_date;
	User user;
	Category category;
	
	Set<Comment> comment= new HashSet<>();

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

	public PostDto(int id, String postTitle, String content, String image, Date upload_date, User user,
			Category category, Set<Comment> comment) {
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

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
