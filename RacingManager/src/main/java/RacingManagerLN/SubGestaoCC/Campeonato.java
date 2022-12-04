package RacingManagerLN.SubGestaoCC;

import RacingManagerLN.SubGestaoCC.Circuito.Circuito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Campeonato {
    private String nomeCampeonato;
    private int participantes;
    private Map<String, Integer> classificacaoCampeonato;
    private List<Integer> pontuacoes;
    private List<Circuito> circuitosCampeonato;

    public Campeonato(String nomeCampeonato, int participantes, Map<String, Integer> classificacaoCampeonato, List<Integer> pontuacoes, List<Circuito> circuitosCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
        this.participantes = participantes;
        this.classificacaoCampeonato = classificacaoCampeonato;
        this.pontuacoes = pontuacoes;
        this.circuitosCampeonato = circuitosCampeonato;
    }

    public Campeonato(String nomeCampeonato, int participantes, List<Circuito> circuitosCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
        this.participantes = participantes;
        setCircuitos(circuitosCampeonato);
    }

    public Campeonato(Campeonato c){
        this.nomeCampeonato = c.getNomeCampeonato();
        this.participantes = c.getParticipantes();
        this.classificacaoCampeonato = c.getClasssificacaoCamp();
        this.circuitosCampeonato = c.getCircuitos();
    }

    public List<Circuito> getCircuitos() {
        return this.circuitosCampeonato.stream().map(Circuito::clone).collect(Collectors.toList());
    }

    public void setCircuitos(List<Circuito> aCircuitos) {
        this.circuitosCampeonato = aCircuitos.stream().map(Circuito::clone).collect(Collectors.toList());
    }

    public void addCircuito(Circuito aCircuito) {
        this.circuitosCampeonato.add(aCircuito);
    }

    public void removeCircuito(String aCircNome) {
        this.circuitosCampeonato=this.circuitosCampeonato.stream().filter(x->!x.getNomeCircuito().equals(aCircNome)).collect(Collectors.toList());
    }

    public int getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(int aParticipantes) {
        this.participantes = aParticipantes;
    }


    public Campeonato(String aNomeCampeonato, int aParticipantes) {
        this.nomeCampeonato = aNomeCampeonato;
        this.participantes = aParticipantes;
        this.circuitosCampeonato = new ArrayList<>();
    }

    public Campeonato(Map<String, Integer> aClassificacao) {
        this.classificacaoCampeonato = new HashMap<>();
        aClassificacao.forEach((String,Integer)->this.classificacaoCampeonato.put(String,Integer));
    }

    public void atualizaClassificacao() {

    }

    public void atualizaClassificacaoCategoria() {
        throw new UnsupportedOperationException();
    }

    public Map<String, Integer> getClasssificacaoCamp() {
        return this.classificacaoCampeonato;
    }

    public String getNomeCampeonato() {
        return this.nomeCampeonato;
    }

    public void setNomeCampeonato(String aNomeCampeonato) {
        this.nomeCampeonato = aNomeCampeonato;
    }

    public void adicionaPontuacoes(Map<String, Integer> aPontuacoesJogo) {
        //Não sei se vai adicionar pela ordem certa??
        this.pontuacoes = aPontuacoesJogo.values().stream().toList();
    }

    @Override
    public boolean equals(Object obj) {
        // check if the "addresses" of o and this object are the same
        if (this == obj)
            return true;
            // check if o is of instance Campeonato
        else if (obj instanceof Campeonato)
        {
            Campeonato camp = (Campeonato)obj;
            // compare fields of o with fields of this instance
            if (    (this.nomeCampeonato.equals(camp.nomeCampeonato))
                &&  (this.circuitosCampeonato.equals(camp.circuitosCampeonato))
                &&  (this.classificacaoCampeonato.equals(camp.classificacaoCampeonato))
                &&  (this.participantes == camp.participantes)
                &&  (this.pontuacoes.equals(camp.pontuacoes))
            )
            return true;
        }
        return false;
    }

    public Campeonato clone() {
        return new Campeonato(this);
    }

    @Override
    public String toString() {
        return "Campeonato{" +
                "nomeCampeonato='" + nomeCampeonato + '\'' +
                ",\n participantes=" + participantes +
                ",\n circuitosCampeonato=" + circuitosCampeonato +",\n"+
                '}';
    }
}