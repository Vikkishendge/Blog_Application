package blogapplication.Model;

import java.sql.Date;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class Posts {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column(nullable=false, length=100)
	String post_title;
	
	String content;
	String image;	
	Date upload_date;
	
	@ManyToOne
	User user;
	
	@ManyToOne
    @JoinColumn(name="category_id")
	Category category;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	Set<Comment> comment=new HashSet<>();
	
	

	
}
