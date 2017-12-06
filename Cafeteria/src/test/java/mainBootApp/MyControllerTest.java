package mainBootApp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import mainBootApp.cotrollers.MyController;
import mainBootApp.model.Coffee;
import mainBootApp.services.MyService;

/**
 * 
 * Unit Test Class 
 * using Mockito to test dependency injection logic
 *
 */
public class MyControllerTest {
	
	MyController controller;
	MockMvc mockMvc;
	
	@Mock
	MyService service;
	
	@Mock
	Model model;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		controller = new MyController(service);
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void showCoffeesMvcTest() throws Exception {	
		
		mockMvc.perform(get("/"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("coffees"))
			   .andExpect(model().attributeExists("coffeeList"));
	}
	
	@Test
	public void showCoffeesTest() throws Exception {
		
		String actual = controller.showCoffees(model);		
				
		assertEquals("coffees", actual);
		
		verify(service, times(1)).allCoffees();
		verify(model, times(1)).addAttribute("coffeeList", new ArrayList<>());
	}
	
	@Test
	public void showCoffeeByIdTest() throws Exception {	
		
		Coffee coffee = new Coffee();
		coffee.setId(2L);
		
		when(service.findCoffeeById(ArgumentMatchers.anyLong())).thenReturn(coffee);
		
		mockMvc.perform(get("/coffees/1"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("show"))
			   .andExpect(model().attributeExists("coffee"));
	}		   	
	

}
