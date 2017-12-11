package mainBootApp.services;

import java.util.List;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;


public interface MyService {
	
	List<Coffee> allCoffees();
	List<Category> allCategories();	
	Coffee findCoffeeById(Long id);
	Coffee saveCoffee(Coffee coffee);
	//Set<Ingredient> findIngredientsById(Long id);
	

}
