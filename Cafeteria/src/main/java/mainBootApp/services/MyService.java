package mainBootApp.services;

import java.util.List;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;
import mainBootApp.model.Ingredient;


public interface MyService {
	
	List<Coffee> allCoffees();
	List<Category> allCategories();	
	Coffee findCoffeeById(Long id);
	Coffee saveCoffee(Coffee coffee);
	List<Ingredient> findIngredientsById(Long id);
	void saveIngredientsById(Long id, List<Ingredient> list);
	void deleteCoffeeById(Long long1);		

}
