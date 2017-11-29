package mainBootApp;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.assertj.core.util.Arrays;
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
