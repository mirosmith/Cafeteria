package mainBootApp.model;

import javax.persistence.*;

@Entity
public class Note {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	@Lob
	private String coffeeNote;
	
	@OneToOne
	private Coffee coffee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Coffee getCoffee() {
		return coffee;
	}

	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}

	public String getCoffeeNote() {
		return coffeeNote;
	}

	public void setCoffeeNote(String coffeeNote) {
		this.coffeeNote = coffeeNote;
	}
	

}
