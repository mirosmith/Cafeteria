package mainBootApp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import mainBootApp.cotrollers.MyController;
import mainBootApp.services.MyService;

/**
 * 
 * Unit Test Class 
 * using Mockito to test dependency injection logic
 *
 */
public class MyControllerTest {
	
	MyController controller;
	
	@Mock
	MyService service;
	
	@Mock
	Model model;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		controller = new MyController(service);		
	}
	
	@Test
	public void showCoffeesMvcTest() throws Exception {
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		mockMvc.perform(get("/"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("coffees"))
			   .andExpect(MockMvcResultMatchers.model().attributeExists("coffeeList"));
	}
	
	@Test
	public void showCoffeesTest() throws IOException {
		
		String actual = controller.showCoffees(model);		
				
		assertEquals("coffees", actual);
		
		verify(service, times(1)).allCoffees();
		verify(model, times(1)).addAttribute("coffeeList", new ArrayList<>());
	}

}
