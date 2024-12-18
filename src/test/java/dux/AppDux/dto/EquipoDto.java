package dux.AppDux.dto;



public class EquipoDto {


	private Long id;
	private String nombre;
	private String liga;
	private String pais;
	
	public EquipoDto() {
		super();
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
	public String getLiga() {
		return liga;
	}
	public void setLiga(String liga) {
		this.liga = liga;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "EquipoDto [nombre=" + nombre + ", liga=" + liga + ", pais=" + pais + "]";
	}
	
}
