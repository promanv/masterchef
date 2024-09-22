package cl.prueba.ilis.masterchef;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MasterchefApplicationTests {

	@Autowired
    TestRestTemplate restTemplate;
	
	/*
	 * devuelve not found cuando se busca una receta que no existe.
	 */
	@Test
    void buscaRecetaIdNoValido() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/recetas/9999", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

	/*
	 * crea una nueva receta y devuelve el objeto con su id
	 */
	@Test
    @DirtiesContext
    void creaNuevaReceta() {
        
		List<String> listaIngredientes = List.of("porotos",
		                             "cebolla",
		                             "pimenton",
		                             "comino",
		                             "aji color",
		                             "spaghetti",
		                             "zapallo camote");									
		
		Receta nuevaReceta = recetaDummy();
        ResponseEntity<Void> createResponse = restTemplate
                .postForEntity("/api/recetas", nuevaReceta, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI ubicacionNuevaReceta = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(ubicacionNuevaReceta, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        int id = documentContext.read("$.id");
        String nombre = documentContext.read("$.nombre");
        String descripcion = documentContext.read("$.descripcion");
        List<String> ingredientes = documentContext.read("$.ingredientes");
        String instrucciones = documentContext.read("$.instrucciones");
        int tiempoPreparacion = documentContext.read("$.tiempoPreparacion");
        String dificultad = documentContext.read("$.dificultad");
        String participante = documentContext.read("$.participante");
        int votos = documentContext.read("$.votos");

        assertThat(id).isNotNull();
        assertThat(nombre).isEqualTo("Porotos con Riendas");
        assertThat(descripcion).isEqualTo("Ricos porotos a la chilena con spaghetti");
        assertThat(listaIngredientes).isEqualTo(ingredientes);
        assertThat(instrucciones).isEqualTo("hacer el pino, cocer los porotos, poner spaghetti, juntar, revolver, condimentar y listo!");
        assertThat(tiempoPreparacion).isEqualTo(50);
        assertThat(dificultad).isEqualTo("media");
        assertThat(participante).isEqualTo("Pablo");
        assertThat(votos).isEqualTo(0);
	}
	
	/*
	 * devuelve not found al actualizar una receta que no existe
	 */
	@Test
    void noActualizaRecetaInexistente() {
						
        Receta updReceta = recetaDummy();
        HttpEntity<Receta> request = new HttpEntity<>(updReceta);
        ResponseEntity<Void> response = restTemplate
                .exchange("/api/recetas/99999", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
	
	
	/*
	 * No elimina Receta que no existe
	 */
	@Test
    void noEliminaRecetaInexistente() {
            ResponseEntity<Void> deleteResponse = restTemplate
                            .exchange("/api/recetas/9999", HttpMethod.DELETE, null, Void.class);
            assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
	
	/*
	 * Voto me gusta, suma 1 voto a la receta
	 */
	@Test
	void votoMeGusta() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/recetas/4", String.class);		
		DocumentContext doc = JsonPath.parse(response.getBody());
		int votos = doc.read("$.votos");

		String responseMeGusta = restTemplate.patchForObject("/api/recetas/4/1", null, String.class);
		DocumentContext docMG = JsonPath.parse(responseMeGusta);
		int votosMG = docMG.read("$.votos");		
		
		assertThat(votosMG-votos).isEqualTo(1);			
	}
	
	/*
	 * Voto no me gusta, resta 2 votos a la receta
	 */
	@Test
	void votoNoGusta() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/recetas/4", String.class);		
		DocumentContext doc = JsonPath.parse(response.getBody());
		int votos = doc.read("$.votos");
		
		String responseNoGusta = restTemplate.patchForObject("/api/recetas/4/0", null, String.class);
		DocumentContext docNG = JsonPath.parse(responseNoGusta);
		int votosNG = docNG.read("$.votos");		
		
		assertThat(votosNG-votos).isEqualTo(-2);		
	}
	
	
	/*
	 * Objeto dummy de una Receta
	 */
	public Receta recetaDummy() {
		
		List<String> listaIngredientes = List.of("porotos",
                "cebolla",
                "pimenton",
                "comino",
                "aji color",
                "spaghetti",
                "zapallo camote");
		
        Receta receta = new Receta(null, 
    			"Porotos con Riendas", 
    			"Ricos porotos a la chilena con spaghetti", 
    			listaIngredientes, 
    			"hacer el pino, cocer los porotos, poner spaghetti, juntar, revolver, condimentar y listo!", 
    			50, 
    			"media", 
    			"Pablo", 
    			0);
        return receta;		
	}
}
