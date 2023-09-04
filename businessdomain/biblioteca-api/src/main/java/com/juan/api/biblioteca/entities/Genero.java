package com.juan.api.biblioteca.entities;

public enum Genero {
	
	CIENCIA_FICCION("Ciencia ficción"), NOVELA("Novela"), POESIA("Poesía"), VIAJE("Viaje"), HISTORIA("Historia");
	
	private String nombreGenero;
	
	private Genero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

}
