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
    private Map<String, Circuito> circuitosCampeonato;

    public Campeonato(String nomeCampeonato, int participantes, Map<String, Circuito> circuitosCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
        this.participantes = participantes;
        this.classificacaoCampeonato = new HashMap<>();
        this.circuitosCampeonato = circuitosCampeonato;
    }

    public Campeonato(String nomeCampeonato, int participantes, List<Circuito> circuitosCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
        this.participantes = participantes;
        this.classificacaoCampeonato=new HashMap<>();
        setCircuitos(circuitosCampeonato);
    }

    public Campeonato(Campeonato c){
        this.nomeCampeonato = c.nomeCampeonato;
        this.circuitosCampeonato = c.circuitosCampeonato;
        this.participantes = c.participantes;
        this.classificacaoCampeonato = new HashMap<>();
        //this.pontuacoes = c.pontuacoes;
    }

    public List <Circuito> getCircuitos() {
        return this.circuitosCampeonato.values().stream().toList();
    }

    //ACRESCENTEI ESTA
    public boolean circuitoExiste(String aNomeCirc){
        return this.circuitosCampeonato.containsKey(aNomeCirc);
    }

    public void setCircuitos(List<Circuito> aCircuitos) {
        this.circuitosCampeonato =
                aCircuitos.stream().collect(Collectors.toMap(Circuito::getNomeCircuito, item -> item));
    }

    public void addCircuito(Circuito aCircuito) {
        this.circuitosCampeonato.put(aCircuito.getNomeCircuito(), aCircuito);
    }

    //Retorna o circuito removido
    public Circuito removeCircuito(String aCircNome) {
        return this.circuitosCampeonato.remove(aCircNome);
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
        this.circuitosCampeonato = new HashMap<String, Circuito>();
    }

    public Campeonato(Map<String, Integer> aClassificacao) {
        this.classificacaoCampeonato = new HashMap<>();
        aClassificacao.forEach((String,Integer)->this.classificacaoCampeonato.put(String,Integer));
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

    //public void atualizaClassificacaoCategoria() {throw new UnsupportedOperationException();}

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
                    && (this.classificacaoCampeonato.equals(camp.classificacaoCampeonato))
                    && (this.participantes == camp.participantes);
                    //&& (this.pontuacoes.equals(camp.pontuacoes));
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