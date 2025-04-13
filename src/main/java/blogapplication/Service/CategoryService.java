package blogapplication.Service;

import java.util.List;

import blogapplication.Payloads.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categorydto);
	
	public CategoryDto updateCategory(CategoryDto categorydto,int category_id);
	
	public List<CategoryDto> getAllCategory();
	
	public CategoryDto getSingleCategory(int category_id);
	
	public void deleteCategory(int category_id);

}
