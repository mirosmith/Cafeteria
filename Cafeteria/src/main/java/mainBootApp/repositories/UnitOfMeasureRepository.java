package mainBootApp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mainBootApp.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{
	
	public Optional<UnitOfMeasure> findByDescription(String s);
	//public UnitOfMeasure findByDescription(String s);

}
