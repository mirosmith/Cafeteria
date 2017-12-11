package mainBootApp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import mainBootApp.model.Category;
import mainBootApp.repositories.CategoryRepository;


/**
 * 
 * Integration test
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest()
@ActiveProfiles("test")
public class CategoryRepositoryTestIT {
	
	@Autowired
	CategoryRepository catRepo;

	@Test
	public void findByNameTest() {
		
		Category category = catRepo.findByName("American");
		
		assertEquals("American", category.getName());
		
	}
}
