package com.simulador.votacion;

public class CandidatoPartido3 extends Candidato {

    public CandidatoPartido3 (String nombre) {
        super(nombre);
    }

    @Override
    public void registrarVoto() {
        votos++;  // Incrementa el contador de votos
    }

    @Override
    public String mostrarResultado() {
        return nombre + ": " + votos + " votos.";  // Muestra el nombre y el total de votos
    }
}
