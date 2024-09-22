package cl.prueba.ilis.masterchef;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


interface RecetaRepository extends CrudRepository<Receta, Long>{	
	List<Receta> findAll();
}
