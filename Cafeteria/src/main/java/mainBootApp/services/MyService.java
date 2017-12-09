package mainBootApp.services;

import java.util.List;
import java.util.Set;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;
import mainBootApp.model.Ingredient;

public interface MyService {
	
	List<Coffee> allCoffees();
	List<Category> allCategories();	
	Coffee findCoffeeById(Long id);
	Coffee saveCoffee(Coffee coffee);
	//Set<Ingredient> findIngredientsById(Long id);
	

}
