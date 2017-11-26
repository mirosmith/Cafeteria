package mainBootApp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mainBootApp.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	public Optional<Category> findByName(String s);

}
