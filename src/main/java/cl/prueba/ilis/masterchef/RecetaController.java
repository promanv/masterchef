package cl.prueba.ilis.masterchef;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Receta", description = "API MasterChef Ilis")
@RestController
@RequestMapping("/api")
class RecetaController {	
	
	private final RecetaRepository recetaRepository;
	
	private RecetaController(RecetaRepository recetaRepository) {
		this.recetaRepository = recetaRepository;
	}
	
	
	@Operation(
            summary = "Guardar recetas.",
            description = "Guarda los datos de una nueva receta.") 
	@PostMapping("/recetas")
	private ResponseEntity<Void> creaReceta(@Valid @RequestBody Receta newReceta, UriComponentsBuilder ucb){
		
		Receta recetaGuardada = recetaRepository.save(newReceta);
		URI ubicacionNuevaReceta = ucb
                .path("api/recetas/{id}")
                .buildAndExpand(recetaGuardada.getId())
                .toUri();
		
		return ResponseEntity.created(ubicacionNuevaReceta).build();
	}
	
	
	@Operation(
            summary = "Obtener recetas.",
            description = "Obtiene todas las recetas.")    
	@GetMapping("/recetas")
	private ResponseEntity<List<Receta>> getRecetas(){
		List<Receta> recetas = recetaRepository.findAll();
		return ResponseEntity.ok(recetas);
	}
	
	@Operation(
            summary = "Obtener receta",
            description = "Obtiene los datos de una receta segun el id.") 
	@GetMapping("/recetas/{id}")
	private ResponseEntity<Receta> getRecetaById(@PathVariable Long id){
		
		Optional<Receta> receta = buscaReceta(id);
		if(receta.isPresent()) {
			return ResponseEntity.ok(receta.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(
            summary = "Actualizar receta",
            description = "Actualiza los datos de una receta segun su id.")
	@PutMapping("/recetas/{id}")
	private ResponseEntity<Void> updateReceta(@PathVariable Long id, @RequestBody Receta updReceta) {
		Optional<Receta> receta = buscaReceta(id);
		if(receta.isPresent()) {
			Receta newReceta = updReceta;
			newReceta.setId(id);
			recetaRepository.save(newReceta);
            return ResponseEntity.noContent().build();
		}		
		return ResponseEntity.notFound().build();
	}
	
	@Operation(
            summary = "Eliminar receta",
            description = "Elimina una receta segun su id.")
	@DeleteMapping("/recetas/{id}")
	private ResponseEntity<Void> deleteReceta(@PathVariable Long id) {
		
		if(recetaRepository.existsById(id)) {
			recetaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}		
		return ResponseEntity.notFound().build();
	}
	
	@Operation(
            summary = "Votar receta",
            description = "Registra los votos positivos o negativos de una receta.")
	@PatchMapping("/recetas/{id}/{voto}")
	private ResponseEntity<Receta> votarReceta(@PathVariable Long id, @PathVariable Boolean voto) {
		
		Optional<Receta> receta = buscaReceta(id);		
		if(receta.isPresent()) {
			Receta updReceta = new Receta();
			updReceta = receta.get();
			int votos = updReceta.getVotos();
				if(voto) {
					votos = votos+1;
					updReceta.setVotos(votos);					
				}else {
					votos = votos-2;
					updReceta.setVotos(votos);					
				}			
			return ResponseEntity.ok(recetaRepository.save(updReceta));
		}		
		return ResponseEntity.notFound().build();
	}	
	
	/*
	 * obtiene una receta por su ID
	 */
	private Optional<Receta> buscaReceta(Long id) {		
        return recetaRepository.findById(id);
    }
	
	/*
	 * Controla los mensajes de error cuando no se cumple las validaciones
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
 
}
