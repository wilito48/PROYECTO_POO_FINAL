package com.simulador.votacion;

public class CandidatoPartido1 extends Candidato {

    public CandidatoPartido1(String nombre) {
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
