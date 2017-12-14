package mainBootApp.model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Coffee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Lob
	private byte[] image;	
	
	@OneToOne(cascade=CascadeType.ALL)	
	private Note note;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="coffee")
	private List<Ingredient> ingredients = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="Coffe_Category", joinColumns=@JoinColumn(name="Coffee_id"), inverseJoinColumns=@JoinColumn(name="Category_id"))
	private Set<Category> category = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(String s) {
		this.image = loadImage(s);
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note description) {
		this.note = description;
		description.setCoffee(this);
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}	
	
	public byte[] loadImage(String s) {
		
		if (s.isEmpty() || s == null) {
			return null;
		}
		try {
		
		Path path = Paths.get(s);
		
		if (Files.notExists(path) || Files.size(path) == 0) {
			return null;			
		}
		byte[] array = new byte[(int)Files.size(path)];
		
		InputStream input = Files.newInputStream(path);
		input.read(array);
		
		return array;
		
		}
		catch (IOException e) {
			System.err.println(e);			
		}
		return null;
		
	}
	

}
