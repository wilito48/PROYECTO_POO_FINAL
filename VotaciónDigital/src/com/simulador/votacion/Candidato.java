package com.simulador.votacion;

public abstract class Candidato {
    protected String nombre;
    protected int votos;

    public Candidato(String nombre) {
        this.nombre = nombre;
        this.votos = 0;
    }

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	// Método abstracto que debe ser implementado por las subclases
    public abstract void registrarVoto();

    // Método abstracto para mostrar resultados
    public abstract String mostrarResultado();
}
