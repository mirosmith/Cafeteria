package mainBootApp.cotrollers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mainBootApp.model.Coffee;
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
	

}
