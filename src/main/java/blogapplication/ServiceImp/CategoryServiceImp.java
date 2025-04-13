package blogapplication.ServiceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogapplication.Exceptions.ResourceNotFoundException;
import blogapplication.Model.Category;
import blogapplication.Payloads.CategoryDto;
import blogapplication.Repository.CategoryRepo;
import blogapplication.Service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{

	@Autowired
	private ModelMapper modelmapper;
	  
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
		 System.out.println("Received DTO: " + categorydto.toString()); // Debug log
	  
		 Category category = this.modelmapper.map(categorydto,Category.class);
         System.out.println("Mapped User: " + category.toString()); // Debug log
	   
   	     Category saveCatagory=this.categoryRepo.save(category);
   	  System.out.println("Saved User: " + saveCatagory.toString()); // Debug log
	   
		return this.modelmapper.map(saveCatagory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto, int category_id) {
		Category category=this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotFoundException("Category","id",category_id));
       
		category.setTitle(categorydto.getTitle());
		category.setDescription(categorydto.getDescription());
		
		Category updateCategory=this.categoryRepo.save(category);
		
		return this.modelmapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> category=this.categoryRepo.findAll();
		
		List<CategoryDto> categorydto=category.stream().map(categ->this.modelmapper.map(categ, CategoryDto.class)).collect(Collectors.toList());
		return categorydto;
	}

	@Override
	public CategoryDto getSingleCategory(int category_id) {
		
		Category category = this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotFoundException("Category","id",category_id));
		
		CategoryDto categorydto=this.modelmapper.map(category, CategoryDto.class);
		
		return categorydto;
	}

	@Override
	public void deleteCategory(int category_id) {
	
		Category category = this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotFoundException("Category","id",category_id));
		
       this.categoryRepo.delete(category);		
	}


}
