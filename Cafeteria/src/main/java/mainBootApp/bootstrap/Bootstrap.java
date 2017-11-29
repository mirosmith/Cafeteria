package mainBootApp.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;
import mainBootApp.model.Ingredient;
import mainBootApp.model.Note;
import mainBootApp.model.UnitOfMeasure;
import mainBootApp.repositories.CategoryRepository;
import mainBootApp.repositories.CoffeeRepository;
import mainBootApp.repositories.UnitOfMeasureRepository;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private CoffeeRepository CoffeeRepo;
	private CategoryRepository CategoryRepo;
	private UnitOfMeasureRepository UnitRepo;	

	public Bootstrap(CoffeeRepository coffeeRepo, CategoryRepository categoryRepo, UnitOfMeasureRepository unitRepo) {		
		CoffeeRepo = coffeeRepo;
		CategoryRepo = categoryRepo;
		UnitRepo = unitRepo;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		CoffeeRepo.saveAll(getCoffees());			
	}

	public List<Coffee> getCoffees() {
		
		List<Coffee> list = new ArrayList<>();
		
		// Categories
		Category american = CategoryRepo.findByName("American");		
		if (american == null) {
			throw new RuntimeException("Expected Category Not Found");			
		}
		
		Category asia = CategoryRepo.findByName("Southeast Asia");		
		if (asia == null) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		Category india = CategoryRepo.findByName("India");		
		if (india == null) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		Category africa = CategoryRepo.findByName("Africa");		
		if (africa == null) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		// Unit of measures
		UnitOfMeasure spoon = UnitRepo.findByDescription("Teaspoon");	
		if (spoon == null) {
			throw new RuntimeException("Expected Unit Not Found");
		}
		UnitOfMeasure cup = UnitRepo.findByDescription("Cup");	
		if (cup == null) {
			throw new RuntimeException("Expected Unit Not Found");
		}
		UnitOfMeasure cm = UnitRepo.findByDescription("Cm");	
		if (cm == null) {
			throw new RuntimeException("Expected Unit Not Found");
		}
		UnitOfMeasure dollop = UnitRepo.findByDescription("Dollop");	
		if (dollop == null) {
			throw new RuntimeException("Expected Unit Not Found");
		}
			
		
		// Espresso
		Coffee espresso = new Coffee();
		
		espresso.setName("Espresso");
		
		espresso.setImage("src/main/resources/static/espresso.jpg");
		
		Note espressoNote = new Note();		
		String prepareEspresso = "The espresso (aka “short black”) is the foundation and the most important part to every espresso based drink. "
								+ "So much so that we’ve written a guide on how to make the perfect espresso shot. "
				                + "But for the purposes of this post an espresso consists of:";
		espressoNote.setCoffeeNote(prepareEspresso);		
	
		espresso.setDescription(espressoNote);		
		
		espresso.getCategory().add(american);
		
		espresso.getIngredients().add(new Ingredient("1 Shot of espresso in an espresso cup", new BigDecimal(1), cup, espresso));
		
		list.add(espresso);
		
		
		Coffee macchiato = new Coffee();
		
		macchiato.setName("Macchiato");
		
		Note macchiatoNote = new Note();
		String prepareMacchiato = "A short macchiato is similar to an espresso but with a dollop of steamed milk and foam to mellow the harsh taste of an espresso. "
								+ "You will find that baristas in different countries make short macchiatos differently. "
								+ "However the traditional way of making a short macchiato is as follows:";
		macchiatoNote.setCoffeeNote(prepareMacchiato);
		
		macchiato.setDescription(macchiatoNote);
		
		macchiato.getCategory().add(africa);
		macchiato.getCategory().add(india);
		
		macchiato.getIngredients().add(new Ingredient("Shot of espresso in a short glass or espresso cup", new BigDecimal(1), cup, macchiato));
		macchiato.getIngredients().add(new Ingredient("A dollop of steamed milk", new BigDecimal(1.5), dollop, macchiato));
		macchiato.getIngredients().add(new Ingredient("A foam placed on top of the espresso", new BigDecimal(0.5), dollop, macchiato));
		
		list.add(macchiato);
		
		return list;
		
	}	

	

}
