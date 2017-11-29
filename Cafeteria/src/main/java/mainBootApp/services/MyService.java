package mainBootApp.services;

import java.util.List;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;
import mainBootApp.model.UnitOfMeasure;

public interface MyService {
	
	List<Coffee> allCoffees();
	List<Category> allCategories();
	List<UnitOfMeasure> allUnits();

}
