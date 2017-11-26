package mainBootApp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Coffee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Lob
	private Byte[] image;	
	
	@OneToOne(cascade=CascadeType.ALL)
	private Note note;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="coffee")
	private Set<Ingredient> ingredients = new HashSet<>();
	
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

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Note getDescription() {
		return note;
	}

	public void setDescription(Note description) {
		this.note = description;
		description.setCoffee(this);
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}	
	

}
