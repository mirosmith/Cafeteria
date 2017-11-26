package mainBootApp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy="category")
	private Set<Coffee> coffee= new HashSet<>();

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

	public Set<Coffee> getCoffee() {
		return coffee;
	}

	public void setCoffee(Set<Coffee> coffee) {
		this.coffee = coffee;
	}

	
}
