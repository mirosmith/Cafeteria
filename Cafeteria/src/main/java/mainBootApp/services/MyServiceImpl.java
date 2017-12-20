package mainBootApp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;
import mainBootApp.model.Ingredient;
import mainBootApp.model.Note;
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

	@Transactional
	@Override	
	public Coffee saveCoffee(Coffee coffee) {		
		
		Coffee saved;
		Optional<Coffee> foundCoffee;
		Coffee updatedCoffee;
		
		if (coffee.getId() == null) {
			
			List<Ingredient> list = coffee.getIngredients();
			
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCoffeeIngredient(coffee);
			}		
			
			saved = coffeeRepo.save(coffee);
			return saved;
		}
		else {
			foundCoffee = coffeeRepo.findById(coffee.getId());
			
			if (!foundCoffee.isPresent()) {
				throw new RuntimeException("Coffee Not Found!");
			}
			
			updatedCoffee = foundCoffee.get();
			if (coffee.getName() != null) { updatedCoffee.setName(coffee.getName()); }
			if (coffee.getImage() != null) { updatedCoffee.setImage(coffee.getImage()); }
			if (coffee.getCategory() != null) { updatedCoffee.setCategory(coffee.getCategory());}			
			
			Note noteToUpdate = updatedCoffee.getNote();
			if (coffee.getNote() != null) {
				noteToUpdate.setCoffeeNote(coffee.getNote().getCoffeeNote());
			}
			
		}
		
		return updatedCoffee;
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
	
	@Override
	public void saveIngredientsById(Long id, List<Ingredient> list) {
		
		Optional<Coffee> coffee = coffeeRepo.findById(id);	
		
		if (!coffee.isPresent()) {
			throw new RuntimeException("Coffee Not Found!");
		}
		
		Coffee saved = coffee.get();
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCoffeeIngredient(saved);
		}
		
		saved.setIngredients(list);		
		
		coffeeRepo.save(saved);		
		
	}	
	

}
