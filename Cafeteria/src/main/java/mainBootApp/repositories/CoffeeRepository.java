package mainBootApp.repositories;

import org.springframework.data.repository.CrudRepository;

import mainBootApp.model.Coffee;

public interface CoffeeRepository extends CrudRepository<Coffee, Long>{

}
