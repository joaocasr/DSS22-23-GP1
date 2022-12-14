package RacingManagerLN.SubGestaoCC;

import RacingManagerLN.SubGestaoCC.Circuito.Circuito;

import java.util.*;
import java.util.stream.Collectors;

public class Campeonato {
    private String nomeCampeonato;
    private int participantes;
    private Map<String, Integer> classificacaoCampeonato;
    private List<Circuito> circuitosCampeonato;

    public Campeonato(String nomeCampeonato, int participantes, List<Circuito> circuitosCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
        this.participantes = participantes;
        this.classificacaoCampeonato=new HashMap<>();
        setCircuitos(circuitosCampeonato);
    }

    public Campeonato(Campeonato c){
        this.nomeCampeonato = c.getNomeCampeonato();
        this.circuitosCampeonato = c.getCircuitos();
        this.participantes = c.getParticipantes();
        this.classificacaoCampeonato = new HashMap<>();
        //this.pontuacoes = c.pontuacoes;
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

    public Map<String, Integer> getClasssificacaoCamp() {
        return this.classificacaoCampeonato;
    }

    public String getNomeCampeonato() {
        return this.nomeCampeonato;
    }

    public int getNumeroCorridas() {
        return this.circuitosCampeonato.size();
    }

    public void setNomeCampeonato(String aNomeCampeonato) {
        this.nomeCampeonato = aNomeCampeonato;
    }

    public void atualizaClassificacao(Map<String,Integer> pontuacoes) {
        if(this.classificacaoCampeonato.size()==0){
            pontuacoes.forEach((String,Integer)->this.classificacaoCampeonato.put(String,Integer));
        }else{
            for(Map.Entry<String,Integer> p: pontuacoes.entrySet()){
                for(Map.Entry<String,Integer> c: this.classificacaoCampeonato.entrySet()){
                    if(c.getKey().equals(p.getKey())){
                        int oldScore = this.classificacaoCampeonato.get(c.getKey());
                        int newScore = oldScore + p.getValue();
                        this.classificacaoCampeonato.put(c.getKey(),newScore);
                    }
                }
            }
        }
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