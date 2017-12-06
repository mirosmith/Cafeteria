package mainBootApp.services;

import java.util.List;
import java.util.Optional;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;
import mainBootApp.model.UnitOfMeasure;

public interface MyService {
	
	List<Coffee> allCoffees();
	List<Category> allCategories();
	List<UnitOfMeasure> allUnits();
	Coffee findCoffeeById(Long id);
	

}
