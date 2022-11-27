package RacingManagerLN.SubGestaoCC.Circuito;

import java.util.List;

public class Circuito {
    private String nomeCircuito;
    private Double distancia;
    private int voltas;
    private int numRetas;
    private int numCurvas;
    private int numChicanes;
    private List<Reta> allRetas;
    private List<Curva> allCurvas;
    private List<Chicane> allChicanes;

    public String getNomeCircuito() {
        return nomeCircuito;
    }

    public void setNomeCircuito(String nomeCircuito) {
        this.nomeCircuito = nomeCircuito;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public int getVoltas() {
        return voltas;
    }

    public void setVoltas(int voltas) {
        this.voltas = voltas;
    }

    public int getNumRetas() {
        return numRetas;
    }

    public void setNumRetas(int numRetas) {
        this.numRetas = numRetas;
    }

    public int getNumCurvas() {
        return numCurvas;
    }

    public void setNumCurvas(int numCurvas) {
        this.numCurvas = numCurvas;
    }

    public int getNumChicanes() {
        return numChicanes;
    }

    public void setNumChicanes(int numChicanes) {
        this.numChicanes = numChicanes;
    }

    public List<Reta> getAllRetas() {
        return allRetas;
    }

    public void setAllRetas(List<Reta> allRetas) {
        this.allRetas = allRetas;
    }

    public List<Curva> getAllCurvas() {
        return allCurvas;
    }

    public void setAllCurvas(List<Curva> allCurvas) {
        this.allCurvas = allCurvas;
    }

    public List<Chicane> getAllChicanes() {
        return allChicanes;
    }

    public void setAllChicanes(List<Chicane> allChicanes) {
        this.allChicanes = allChicanes;
    }

    public boolean equals(Object aO) {
        throw new UnsupportedOperationException();
    }

    public Circuito clone() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        throw new UnsupportedOperationException();
    }


    public Circuito(String aNomeCircuito, int aDistancia, int aVoltas, int aNumRetas, int aNumChicanes, int aNumCurvas, List<Curva> aCurvas) {
        throw new UnsupportedOperationException();
    }
}
