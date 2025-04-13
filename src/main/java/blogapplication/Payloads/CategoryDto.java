package blogapplication.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryDto {

   private int category_id;
   
   @NotNull
   @Size(min=4)
   private String title;
   @NotNull
   private String description;

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

public CategoryDto(int category_id, @NotEmpty @Size(min = 4) String title, String description) {
	super();
	this.category_id = category_id;
	this.title = title;
	this.description = description;
}

public CategoryDto() {
	super();
	// TODO Auto-generated constructor stub
}
   
   

}
