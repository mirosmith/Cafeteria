package mainBootApp;

import static org.junit.Assert.*;


import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import mainBootApp.model.Coffee;

public class CoffeeTest {
	
	String name;
	Coffee coffee;
	
	@Before
	public void setUp() {
		name = "src/main/resources/static/espresso.jpg";
		coffee = new Coffee();
	}
	
	@Test
	public void loadImageTestExists() throws IOException {		
		
		assertNotNull(coffee.loadImage(name));	
		
	}
	
	@Test
	public void loadImageTestNotEmpty() throws IOException {		
		
		assertTrue(coffee.loadImage(name).length > 0);	
		
	}

}
