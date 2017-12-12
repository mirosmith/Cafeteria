package mainBootApp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import mainBootApp.cotrollers.MyController;
import mainBootApp.model.Category;
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
	
	@Test
	public void updateCoffeeByIdTest() throws Exception {
		
		Coffee cf = new Coffee();
		cf.setId(3L);
		
		when(service.findCoffeeById(ArgumentMatchers.anyLong())).thenReturn(cf);
		
		ArgumentCaptor<Coffee> captor = ArgumentCaptor.forClass(Coffee.class);

		
		mockMvc.perform(get("/coffees/1/update"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("coffeeForm"))
			   .andExpect(model().attributeExists("coffee"))
			   .andExpect(model().attributeExists("allCategories"));
		
		
		//check if model contains correct Coffee object with ID = 3
		controller.updateCoffeeById("50", model);
		
		//verify(service, times(1)).findCoffeeById(ArgumentMatchers.anyLong());
		verify(model, times(1)).addAttribute(ArgumentMatchers.eq("coffee"), captor.capture());
		
		assertEquals(cf.getId(), captor.getValue().getId());		
		
	}
	
	@Test
	public void updatePostCoffeeByIdTest() throws Exception {
		
		Coffee cf = new Coffee();
		cf.setId(2L);
		
		when(service.saveCoffee(ArgumentMatchers.any(Coffee.class))).thenReturn(cf);		
		
		mockMvc.perform(post("/coffees")
			   .contentType(MediaType.APPLICATION_FORM_URLENCODED)
			   .param("id", "")
			   .param("name", "some name")
			   )
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/coffees/2"));		
		
	}
	
	@Test
	public void newIngredientGetTest() throws Exception {
		
		Coffee cf = new Coffee();
		cf.setId(3L);
		
		when(service.findCoffeeById(ArgumentMatchers.anyLong())).thenReturn(cf);
		when(service.allCategories()).thenReturn(Arrays.asList(new Category()));
		
		//controller.newIngredientGet("2", model);
		
		mockMvc.perform(get("/coffees/2/ingredients/new"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("coffeeForm"))
			   .andExpect(model().attributeExists("coffee"))
			   .andExpect(model().attributeExists("allCategories"))
			   .andExpect(model().attributeExists("newIngr"));
		
		verify(service, times(1)).findCoffeeById(ArgumentMatchers.anyLong());
		verify(service, times(1)).allCategories();
		//verify(model, times(1)).addAttribute(ArgumentMatchers.eq("newIngr"), ArgumentMatchers.any(Ingredient.class));
		
		assertEquals(1, service.allCategories().size());
	}
	
	
	

}
