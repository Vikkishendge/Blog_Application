package blogapplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blogapplication.Payloads.CategoryDto;
import blogapplication.Service.CategoryService;
////////
@RestController
@RequestMapping("home/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	     
	@PostMapping("category")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categorydto)
	{
		System.out.println("description"+categorydto.getDescription());
		CategoryDto category = this.categoryService.createCategory(categorydto);
		
		return new ResponseEntity<>(category,HttpStatus.CREATED);
	}
	
	@GetMapping("category")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> category=this.categoryService.getAllCategory();
	    
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
	
	@GetMapping("category/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable int categoryId)
	{
		return ResponseEntity.ok(this.categoryService.getSingleCategory(categoryId));
	}
	
	@PutMapping("category/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categorydto,@PathVariable int categoryId){
		
		CategoryDto category=this.categoryService.updateCategory(categorydto, categoryId);
		return ResponseEntity.ok(category);
	}
	 
	@DeleteMapping("category/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable int categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		
		return ResponseEntity.ok("Category Delete Successfully...");
	}
	
}
