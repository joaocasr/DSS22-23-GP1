package RacingManagerLN.SubGestaoCC;

import RacingManagerLN.SubGestaoCC.Circuito.Circuito;

import java.util.*;
import java.util.stream.Collectors;

public class Campeonato {
    private String nomeCampeonato;
    private int participantes;
    private List<Circuito> circuitosCampeonato;

    //Before adding circuits
    public Campeonato(String aNomeCampeonato, int aParticipantes) {
        this.nomeCampeonato = aNomeCampeonato;
        this.participantes = aParticipantes;
        this.circuitosCampeonato = null;
    }

    //When we have all the information
    public Campeonato(String nomeCampeonato, int participantes, List<Circuito> circuitosCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
        this.participantes = participantes;
        setCircuitos(circuitosCampeonato);
    }

    //Cloning?
    public Campeonato(Campeonato c){
        this.nomeCampeonato = c.nomeCampeonato;
        this.participantes = c.participantes;
        this.circuitosCampeonato = c.circuitosCampeonato;
    }


    public List <Circuito> getCircuitos() {
        return this.circuitosCampeonato;
    }

    //ACRESCENTEI ESTA
    public boolean circuitoExiste(String aNomeCirc){
        return this.circuitosCampeonato.stream().anyMatch(circ -> Objects.equals(circ.getNomeCircuito(), aNomeCirc));
    }

    public void setCircuitos(List<Circuito> aCircuitos) {this.circuitosCampeonato = aCircuitos;}

    public void addCircuito(Circuito aCircuito) {
        this.circuitosCampeonato.add(aCircuito);
    }

    //Retorna o circuito removido
    public void removeCircuito(String aCircNome) {
        this.circuitosCampeonato.stream()
                .filter(circ -> !Objects.equals(circ.getNomeCircuito(), aCircNome))
                .collect(Collectors.<Circuito>toList());
    }

    public int getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(int aParticipantes) {
        this.participantes = aParticipantes;
    }

    public String getNomeCampeonato() {
        return this.nomeCampeonato;
    }

    public void setNomeCampeonato(String aNomeCampeonato) {
        this.nomeCampeonato = aNomeCampeonato;
    }

    @Override
    public boolean equals(Object obj) {
        // check if the "addresses" of o and this object are the same
        if (this == obj)
            return true;
            // check if o is of instance Campeonato
        else if (obj instanceof Campeonato camp)
        {
            // compare fields of o with fields of this instance
            return (this.nomeCampeonato.equals(camp.nomeCampeonato))
                    && (this.circuitosCampeonato.equals(camp.circuitosCampeonato))
                    && (this.participantes == camp.participantes);
        }
        return false;
    }

    @Override
    public Campeonato clone() {
        final Campeonato clone;
        try {
            clone = (Campeonato) super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new RuntimeException("superclass messed up", ex);
        }
        clone.nomeCampeonato = this.nomeCampeonato;
        clone.participantes = this.participantes;
        clone.circuitosCampeonato = this.circuitosCampeonato;
        return clone;
    }

    @Override
    public String toString() {
        return "Campeonato{\n" +
                "    nomeCampeonato= '" + nomeCampeonato + "',\n" +
                "    participantes= " + participantes + "',\n" +
                "    circuitosCampeonato= " + circuitosCampeonato +",\n"+
                '}';
    }
}