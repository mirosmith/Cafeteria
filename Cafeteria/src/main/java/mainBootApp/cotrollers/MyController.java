package mainBootApp.cotrollers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;
import mainBootApp.model.Ingredient;
import mainBootApp.services.MyService;

@Controller
public class MyController {
	
	MyService service;

	public MyController(MyService service) {		
		this.service = service;
	}
	
	@GetMapping("/")
	public String showCoffees(Model model) {
		
		List<Coffee> list = service.allCoffees();
		model.addAttribute("coffeeList", list);
		
		
		return "coffees";
	}
	
	@GetMapping("/coffees/new")
	public String addCoffee(Model model) {
		
		Coffee coffee = new Coffee();
		List<Category> categories = service.allCategories();
		Ingredient ingredient = new Ingredient();
		coffee.getIngredients().add(ingredient);		
		
		model.addAttribute("coffee", coffee);
		model.addAttribute("allCategories", categories);
		model.addAttribute("newIngr", ingredient);
		
		return "coffeeForm";
	}
	
	@GetMapping("/coffees/{id}")
	public String showCoffeeById(@PathVariable String id, Model model) {
		
		Coffee searchedCoffee = service.findCoffeeById(new Long(id));
		model.addAttribute("coffee", searchedCoffee);
		
		return "show";
	}
	
	@GetMapping("/coffees/{id}/delete")
	public String deleteCoffee(@PathVariable String id) {
		
		service.deleteCoffeeById(new Long(id));
		
		return "redirect:/";
	}
	
	@GetMapping("/coffees/{id}/update")
	public String updateCoffeeById(@PathVariable String id, Model model) {
		
		Coffee foundCoffee = service.findCoffeeById(new Long(id));
		List<Category> categories = service.allCategories();		
		
		model.addAttribute("coffee", foundCoffee);
		model.addAttribute("allCategories", categories);		
		
		return "coffeeForm";
	}
	
	@PostMapping("/coffees")
	public String updatePostCoffeeById(@ModelAttribute Coffee coffee) {		
		
		Coffee savedCoffee = service.saveCoffee(coffee);		
		
		return "redirect:/coffees/" + savedCoffee.getId();
	}
	/*
	@GetMapping("/coffees/{id}/ingredients")
	public String findIngredientsById(@PathVariable String id, Model model) {
		
		List<Ingredient> ingr = service.findIngredientsById(new Long(id));
		model.addAttribute("ingredients", ingr);
		
		return "ingredients";
	}*/
	
	@GetMapping("/coffees/{id}/ingredients/new")
	public String newIngredientGet(@PathVariable String id, Model model) {
		
		Coffee foundCoffee = service.findCoffeeById(new Long(id));
		List<Category> categories = service.allCategories();
		Ingredient ingredient = new Ingredient();
		foundCoffee.getIngredients().add(ingredient);		
		
		model.addAttribute("coffee", foundCoffee);
		model.addAttribute("allCategories", categories);
		model.addAttribute("newIngr", ingredient);
		
		return "coffeeForm";
	}
	

}
