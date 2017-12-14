package mainBootApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;
import mainBootApp.model.Ingredient;
import mainBootApp.repositories.CategoryRepository;
import mainBootApp.repositories.CoffeeRepository;


@Service
public class MyServiceImpl implements MyService {
	
	private CoffeeRepository coffeeRepo;
	private CategoryRepository categoryRepo;	
	
	public MyServiceImpl(CoffeeRepository coffeeRepo, CategoryRepository categoryRepo) {		
		this.coffeeRepo = coffeeRepo;
		this.categoryRepo = categoryRepo;		
	}

	@Override
	public List<Coffee> allCoffees() {		
		return (List<Coffee>) coffeeRepo.findAll();
	}
	@Override
	public List<Category> allCategories() {		
		return (List<Category>) categoryRepo.findAll();
	}	

	@Override
	public Coffee findCoffeeById(Long id) {	
		
		Optional<Coffee> coffee = coffeeRepo.findById(id);
		
		if (!coffee.isPresent()) {
			throw new RuntimeException("Coffee Not Found!");
		}
		
		return coffee.get();
	}

	@Override	
	public Coffee saveCoffee(Coffee coffee) {		
		
		List<Ingredient> list = coffee.getIngredients();
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCoffeeIngredient(coffee);
		}		
		
		Coffee saved = coffeeRepo.save(coffee);
		
		return saved;
	}	
	
	@Override
	public void deleteCoffeeById(Long long1) {		
		coffeeRepo.deleteById(long1);
	}		

	@Override
	public List<Ingredient> findIngredientsById(Long id) {
		
		Optional<Coffee> coffee = coffeeRepo.findById(id);	
		
		if (!coffee.isPresent()) {
			throw new RuntimeException("Coffee Not Found!");
		}
		
		return coffee.get().getIngredients();
	}	
	

}
