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
import mainBootApp.repositories.CategoryRepository;
import mainBootApp.repositories.CoffeeRepository;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private CoffeeRepository CoffeeRepo;
	private CategoryRepository CategoryRepo;		

	public Bootstrap(CoffeeRepository coffeeRepo, CategoryRepository categoryRepo) {		
		CoffeeRepo = coffeeRepo;
		CategoryRepo = categoryRepo;		
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
		
		Category asia = CategoryRepo.findByName("SouthEast Asia");		
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
		
		// Espresso
		Coffee espresso = new Coffee();
		
		espresso.setName("Espresso");
		
		espresso.setImage("src/main/resources/static/espresso.jpg");
		
		Note espressoNote = new Note();		
		String prepareEspresso = "The espresso (aka “short black”) is the foundation and the most important part to every espresso based drink. "
								+ "There’s nothing better in the  world than drinking a perfectly made coffee, and it all begins with a great shot of espresso. "
								+ "A well extracted shot will bring out the subtle traits and earthy undertones of a coffee blend. "
								+ "The coffee would not be bitter or acidic, rather balanced in its flavour.";
		espressoNote.setCoffeeNote(prepareEspresso);		
	
		espresso.setNote(espressoNote);		
		
		espresso.getCategory().add(american);
		
		espresso.getIngredients().add(new Ingredient("grams of coffee", new BigDecimal(18), espresso));
		
		list.add(espresso);
		
		// Macchiato
		Coffee macchiato = new Coffee();
		
		macchiato.setName("Macchiato");
		
		macchiato.setImage("src/main/resources/static/macchiato.jpg");
		
		Note macchiatoNote = new Note();
		String prepareMacchiato = "A short macchiato is similar to an espresso but with a dollop of steamed milk and foam to mellow the harsh taste of an espresso. "
								+ "The key to the perfect short macchiato is the rule of thirds. That is you want three different colored layers in the macchiato. "
								+ "A bottom dark layer to represent the espresso, a middle layer that mixes the espresso and the milk, and a top layer of predominantly steamed milk. "
								+ "Refer to the picture above as an example.";
		macchiatoNote.setCoffeeNote(prepareMacchiato);
		
		macchiato.setNote(macchiatoNote);
		
		macchiato.getCategory().add(africa);
		macchiato.getCategory().add(india);
		
		macchiato.getIngredients().add(new Ingredient("grams of espresso coffee", new BigDecimal(18), macchiato));
		macchiato.getIngredients().add(new Ingredient("dollop of steamed milk", new BigDecimal(1.5), macchiato));
		macchiato.getIngredients().add(new Ingredient("foam placed on top", new BigDecimal(0.5), macchiato));
		
		list.add(macchiato);
		
		
		// Caffe Latte
		
		Coffee latte = new Coffee();
		latte.setName("Cafe Latte");
		latte.setImage("src/main/resources/static/latte.jpg");
		
		Note latteNote = new Note();
		String prepareLatte = "A café latte, or “latte” for short, is an espresso based drink with steamed milk and micro-foam added to the coffee. "
				+ "This coffee is much sweeter compared to an espresso due to the steamed milk. It is made as follows";
		latteNote.setCoffeeNote(prepareLatte);
		latte.setNote(latteNote);
		
		latte.getCategory().add(american);
		latte.getCategory().add(asia);
		latte.getCategory().add(india);
		latte.getCategory().add(africa);
		
		latte.getIngredients().add(new Ingredient("grams of espresso coffee", new BigDecimal(18), latte));
		latte.getIngredients().add(new Ingredient("ml of steamed milk", new BigDecimal(10), latte));		
		latte.getIngredients().add(new Ingredient("ml micro-foam on top", new BigDecimal(5), latte));
		
		list.add(latte);
		
		return list;
		
	}	

	

}
