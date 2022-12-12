package RacingManagerLN.SubGestaoCP;

import java.util.Objects;

public class Piloto {
    private String nomePiloto;
    private Float sVA;
    private Float cTS;

    public String getNome() {
        return this.nomePiloto;
    }

    public double getSVA() {
        return this.sVA;
    }

    public double getCTS() {
        return this.cTS;
    }

    public void setNome(String aNome) {
        this.nomePiloto=aNome;
    }

    public void setSVA(Float aSva) {
        this.sVA = aSva;
    }

    public void setCTS(Float aCts) {
        this.cTS =aCts;
    }

    public Piloto clone() {
        return new Piloto(this);
    }


    @Override
    public String toString() {
        return "Piloto{" +
                "nomePiloto='" + nomePiloto + '\'' +
                ", sVA=" + sVA +
                ", cTS=" + cTS +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piloto piloto = (Piloto) o;
        return nomePiloto.equals(piloto.nomePiloto) && sVA.equals(piloto.sVA) && cTS.equals(piloto.cTS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomePiloto, sVA, cTS);
    }

    public Piloto(String nomePiloto, Float sVA, Float cTS) {
        this.nomePiloto = nomePiloto;
        this.sVA = sVA;
        this.cTS = cTS;
    }

    public Piloto(Piloto aP) {
        this.nomePiloto = aP.nomePiloto;
        this.sVA = aP.sVA;
        this.cTS = aP.cTS;
    }
}