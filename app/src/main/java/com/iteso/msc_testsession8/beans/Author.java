package com.iteso.msc_testsession8.beans;

import java.util.ArrayList;

public class Author {
	private int id;
	private String nombre;
	private String pais;
	private String extra;
	private ArrayList<Libro> libros;
	
	public Author(){
		
	}
	
	public Author(int id, String nombre, String pais, String extra) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.extra = extra;
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
	 * @return the extra
	 */
	public String getExtra() {
		return extra;
	}

	/**
	 * @param extra the extra to set
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	/**
	 * @return the libros
	 */
	public ArrayList<Libro> getLibros() {
		return libros;
	}

	/**
	 * @param libros the libros to set
	 */
	public void setLibros(ArrayList<Libro> libros) {
		this.libros = libros;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id + ". " + nombre;
	}
	
	
}
