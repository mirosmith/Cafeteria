package mainBootApp.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
		
		//coffee.getIngredients().forEach(ingredient -> ingredient.setCoffeeIngredient(coffee));
		
		
		
		Coffee saved = coffeeRepo.save(coffee);
		
		return saved;
	}
/*
	@Override
	public Set<Ingredient> findIngredientsById(Long id) {
		
		Optional<Coffee> coffee = coffeeRepo.findById(id);		
		
		return coffee.get().getIngredients();
	}
*/	
	
	
	

}
