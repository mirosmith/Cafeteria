package mainBootApp.model;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	private BigDecimal amount;
	
	@ManyToOne
	private Coffee coffee;
	
	@OneToOne
	private UnitOfMeasure unit;
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure unit, Coffee coffee) {		
		this.description = description;
		this.amount = amount;		
		this.unit = unit;
		this.coffee = coffee;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Coffee getCoffeeIngredient() {
		return coffee;
	}

	public void setCoffeeIngredient(Coffee coffeeIngredient) {
		this.coffee = coffeeIngredient;
	}

	public UnitOfMeasure getUnit() {
		return unit;
	}

	public void setUnit(UnitOfMeasure unit) {
		this.unit = unit;
	}	
	

}
