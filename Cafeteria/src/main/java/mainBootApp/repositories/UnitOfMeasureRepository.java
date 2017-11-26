package mainBootApp.repositories;

import org.springframework.data.repository.CrudRepository;

import mainBootApp.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{
		
	public UnitOfMeasure findByDescription(String s);

}
