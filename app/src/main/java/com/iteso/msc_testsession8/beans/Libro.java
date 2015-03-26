package com.iteso.msc_testsession8.beans;

public class Libro {
	private int id;
	private String nombre;
	private int idAutor;
	private int idEditorial;
	private int anioPublicacion;
	private String pais;
	private String lenguaje;
	
	public Libro(){
		
	}

	public Libro(int id, String nombre, int idAutor, int idEditorial,
			int anioPublicacion, String pais, String lenguaje) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idAutor = idAutor;
		this.idEditorial = idEditorial;
		this.anioPublicacion = anioPublicacion;
		this.pais = pais;
		this.lenguaje = lenguaje;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the idAutor
	 */
	public int getIdAutor() {
		return idAutor;
	}

	/**
	 * @param idAutor the idAutor to set
	 */
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	/**
	 * @return the idEditorial
	 */
	public int getIdEditorial() {
		return idEditorial;
	}

	/**
	 * @param idEditorial the idEditorial to set
	 */
	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}

	/**
	 * @return the anioPublicacion
	 */
	public int getAnioPublicacion() {
		return anioPublicacion;
	}

	/**
	 * @param anioPublicacion the anioPublicacion to set
	 */
	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the lenguaje
	 */
	public String getLenguaje() {
		return lenguaje;
	}

	/**
	 * @param lenguaje the lenguaje to set
	 */
	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
	
	
	
}
