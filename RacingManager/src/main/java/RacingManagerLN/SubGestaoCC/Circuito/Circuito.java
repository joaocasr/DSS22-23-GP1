package RacingManagerLN.SubGestaoCC.Circuito;

import RacingManagerLN.SubGestaoCC.Campeonato;

import java.util.List;

public class Circuito {
    private String nomeCircuito;
    private double distancia;
    private int voltas;
    private int numRetas;
    private int numCurvas;
    private int numChicanes;
    private List<Reta> allRetas;
    private List<Curva> allCurvas;
    private List<Chicane> allChicanes;

    public Circuito(String nomeCircuito, double distancia, int voltas, int numRetas, int numCurvas, int numChicanes, List<Reta> allRetas, List<Curva> allCurvas, List<Chicane> allChicanes) {
        this.nomeCircuito = nomeCircuito;
        this.distancia = distancia;
        this.voltas = voltas;
        this.numRetas = numRetas;
        this.numCurvas = numCurvas;
        this.numChicanes = numChicanes;
        this.allRetas = allRetas;
        this.allCurvas = allCurvas;
        this.allChicanes = allChicanes;
    }

    public Circuito(Circuito c){
        this.nomeCircuito = c.getNomeCircuito();
        this.distancia = c.getDistancia();
        this.voltas = c.getVoltas();
        this.numRetas = c.numRetas;
        this.numCurvas = c.numCurvas;
        this.numChicanes = c.numChicanes;
        this.allRetas = c.allRetas;
        this.allCurvas = c.allCurvas;
        this.allChicanes = c.allChicanes;
    }

    public String getNomeCircuito() {
        return nomeCircuito;
    }

    public void setNomeCircuito(String nomeCircuito) {
        this.nomeCircuito = nomeCircuito;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
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


    @Override
    public boolean equals(Object obj) {
        // check if the "addresses" of o and this object are the same
        if (this == obj)
            return true;
            // check if o is of instance Circuito
        else if (obj instanceof Circuito cir)
        {
            // compare fields of o with fields of this instance
            return (this.nomeCircuito.equals(cir.nomeCircuito))
                    && (this.distancia == cir.distancia)
                    && (this.voltas == cir.voltas)
                    && (this.numChicanes == cir.numChicanes)
                    && (this.numCurvas == cir.numCurvas)
                    && (this.numRetas == cir.numRetas)
                    && (this.allChicanes.equals(cir.allChicanes))
                    && (this.allCurvas.equals(cir.allCurvas))
                    && (this.allRetas.equals(cir.allRetas));
        }
        return false;
    }

    @Override
    public Circuito clone() {
        final Circuito clone;
        try {
            clone = (Circuito) super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new RuntimeException("superclass messed up", ex);
        }
        clone.nomeCircuito = this.nomeCircuito;
        clone.distancia = this.distancia ;
        clone.voltas = this.voltas;
        clone.numRetas = this.numRetas;
        clone.numCurvas = this.numCurvas;
        clone.numChicanes = this.numChicanes;
        clone.allRetas = this.allRetas;
        clone.allCurvas = this.allCurvas;
        clone.allChicanes = this.allChicanes;
        return clone;
    }

    @Override
    public String toString() {
        return "Circuito{\n" +
                "    nomeCircuito= '" + nomeCircuito + "',\n" +
                "    distancia= " + distancia + ",\n" +
                "    voltas= " + voltas + ",\n" +
                "    numRetas= " + numRetas + ",\n" +
                "    numCurvas= " + numCurvas + ",\n" +
                "    numChicanes= " + numChicanes + ",\n" +
                "    allRetas= " + allRetas + ",\n" +
                "    allCurvas= " + allCurvas + ",\n" +
                "    allChicanes= " + allChicanes + "\n" +
                '}';
    }
}
