package mainBootApp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mainBootApp.model.Category;
import mainBootApp.model.Coffee;
import mainBootApp.model.Ingredient;
import mainBootApp.repositories.CategoryRepository;
import mainBootApp.repositories.CoffeeRepository;
import mainBootApp.services.MyService;
import mainBootApp.services.MyServiceImpl;
/**
 * 
 * Unit Test Class 
 * using Mockito to test dependency injection logic
 *
 */
public class MyServiceImplTest {	
	
	MyService service;
	
	@Mock
	CoffeeRepository coffeeRepo;
	
	@Mock
	CategoryRepository categoryRepo;	
	
	@Before
	public void setUp() {		
		MockitoAnnotations.initMocks(this);		
		
		service = new MyServiceImpl(coffeeRepo, categoryRepo);
	}
	
	// Unit tests
	@Test
	public void allCoffeesTest() throws IOException {		
		
		Coffee coffee = new Coffee();
		List<Coffee> list = new ArrayList<>();
		list.add(coffee);		
		
		when(coffeeRepo.findAll()).thenReturn(list);
		
		List<Coffee> listData = service.allCoffees();
		
		assertEquals(1, listData.size());
		
		verify(coffeeRepo, times(1)).findAll();
		
	}
	
	@Test
	public void allCategoriesTest() throws IOException {			
		
		List<Category> listData = service.allCategories();
		
		assertEquals(0, listData.size());
		
		verify(categoryRepo, times(1)).findAll();
		
	}
	
	
	@Test
	public void findCoffeeByIdTest() throws IOException {
		
		Coffee coffee = new Coffee();
		coffee.setId(3L);
		
		Optional<Coffee> coffeeO = Optional.of(coffee);
		
		when(coffeeRepo.findById(ArgumentMatchers.anyLong())).thenReturn(coffeeO);
		
		Coffee returned = service.findCoffeeById(ArgumentMatchers.anyLong());
		
		assertEquals(coffee.getId(), returned.getId());
		
		verify(coffeeRepo, times(1)).findById(ArgumentMatchers.anyLong());
		
	}
	
	public void saveCoffeeTest() {
		
		Coffee coffee = new Coffee();
		coffee.setId(4L);
		coffee.setIngredients(Arrays.asList(new Ingredient(), new Ingredient()));
		
		Optional<Coffee> c1 = Optional.of(coffee);	
		
		when(coffeeRepo.save(ArgumentMatchers.any(Coffee.class))).thenReturn(c1.get());
		
		List<Ingredient> list = service.saveCoffee(ArgumentMatchers.any(Coffee.class)).getIngredients();
		
		assertEquals(coffee.getId(), service.saveCoffee(ArgumentMatchers.any(Coffee.class)));
		assertEquals(2, list.size());
		
	}
	
	@Test
	public void findIngredientsByIdTest() throws IOException {
		
		Coffee cf = new Coffee();
		cf.setIngredients(Arrays.asList(new Ingredient(), new Ingredient()));
		
		Optional<Coffee> optional = Optional.of(cf);
		
		when(coffeeRepo.findById(ArgumentMatchers.anyLong())).thenReturn(optional);
		
		List<Ingredient> list = service.findIngredientsById(1L);
		
		verify(coffeeRepo, only()).findById(ArgumentMatchers.anyLong());		
		
		assertEquals(2, list.size());
	}
	
	
}
