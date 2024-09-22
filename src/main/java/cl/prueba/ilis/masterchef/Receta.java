package cl.prueba.ilis.masterchef;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "receta")
@Schema(name="Receta", description="Modelo de la tabla Receta")
public class Receta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="nombre")
	@Size(min=3, max=100, message = "El nombre de la receta debe tener entre 3 y 100 caracteres.")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	
	@Column(name="ingredientes")
	@Convert(converter = StringListConverter.class)
	private List<@NotBlank(message = "La lista de ingredientes no puede estar vacía.") String> ingredientes;
	
	@Column(name="instrucciones")
	private String instrucciones;
	
	@Min(1)
	@Column(name="tiempo_preparacion")
	private int tiempoPreparacion;
	
	@Column(name="dificultad")
	private String dificultad;
	
	@Column(name="participante")
	private String participante;
	
	@Column(name="votos")
	private int votos;
	
	public Receta() {
		
	}	

	public Receta(Long id, String nombre, String descripcion, List<String>  ingredientes, String instrucciones,
			int tiempoPreparacion, String dificultad, String participante, int votos) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ingredientes = ingredientes;
		this.instrucciones = instrucciones;
		this.tiempoPreparacion = tiempoPreparacion;
		this.dificultad = dificultad;
		this.participante = participante;
		this.votos = votos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<String>  getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<String>  ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getInstrucciones() {
		return instrucciones;
	}

	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}

	public int getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	public void setTiempoPreparacion(int tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getParticipante() {
		return participante;
	}

	public void setParticipante(String participante) {
		this.participante = participante;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", ingredientes="
				+ ingredientes + ", instrucciones=" + instrucciones + ", tiempoPreparacion=" + tiempoPreparacion
				+ ", dificultad=" + dificultad + ", participante=" + participante + ", votos=" + votos + "]";
	}	

}
