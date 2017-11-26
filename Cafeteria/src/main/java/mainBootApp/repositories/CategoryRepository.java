package mainBootApp.repositories;

import org.springframework.data.repository.CrudRepository;

import mainBootApp.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	public Category findByName(String s);

}
