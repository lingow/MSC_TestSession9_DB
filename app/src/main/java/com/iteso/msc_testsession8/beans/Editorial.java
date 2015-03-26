package com.iteso.msc_testsession8.beans;

public class Editorial {
	private int id;
	private String nombre;
	
	public Editorial(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public Editorial() {
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
	 * @return the name
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id + ". " + nombre;
	}
	
	
	
}
