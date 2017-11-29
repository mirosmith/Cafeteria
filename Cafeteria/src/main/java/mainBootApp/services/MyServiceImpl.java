package mainBootApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;
import mainBootApp.model.UnitOfMeasure;
import mainBootApp.repositories.CategoryRepository;
import mainBootApp.repositories.CoffeeRepository;
import mainBootApp.repositories.UnitOfMeasureRepository;

@Service
public class MyServiceImpl implements MyService {
	
	private CoffeeRepository coffeeRepo;
	private CategoryRepository categoryRepo;
	private UnitOfMeasureRepository unitRepo;
	
	public MyServiceImpl(CoffeeRepository coffeeRepo, CategoryRepository categoryRepo,
			UnitOfMeasureRepository unitRepo) {		
		this.coffeeRepo = coffeeRepo;
		this.categoryRepo = categoryRepo;
		this.unitRepo = unitRepo;
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
	public List<UnitOfMeasure> allUnits() {		
		return (List<UnitOfMeasure>) unitRepo.findAll();
	}
	

}
