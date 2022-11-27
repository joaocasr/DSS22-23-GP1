package RacingManagerLN.SubGestaoCC;

import RacingManagerLN.SubGestaoCC.Circuito.Circuito;

import java.util.List;
import java.util.Map;

public class Campeonato {
    private String nomeCampeonato;
    private int participantes;
    private Map<String, Integer> classificacaoCampeonato;
    private List<Integer> pontuacoes;
    private List<Circuito> circuitosCampeonato;

    public List<Circuito> getCircuitos() {
        throw new UnsupportedOperationException();
    }

    public void setCircuitos(List<Circuito> aCircuitos) {
        throw new UnsupportedOperationException();
    }

    public void addCircuito(Circuito aCircuito) {
        throw new UnsupportedOperationException();
    }

    public void removeCircuito(String aCircNome) {
        throw new UnsupportedOperationException();
    }

    public int getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(int aParticipantes) {
        throw new UnsupportedOperationException();
    }

    public Campeonato() {
        throw new UnsupportedOperationException();
    }

    public Campeonato(String aNomeCampeonato, int aParticipantes) {
        throw new UnsupportedOperationException();
    }

    public Campeonato(Map<String, Integer> aClassificacao) {
        throw new UnsupportedOperationException();
    }

    public void atualizaClassificacao() {
        throw new UnsupportedOperationException();
    }

    public void atualizaClassificacaoCategoria() {
        throw new UnsupportedOperationException();
    }

    public Map<String, Integer> getClasssificacaoCamp() {
        throw new UnsupportedOperationException();
    }

    public String getNomeCampeonato() {
        return this.nomeCampeonato;
    }

    public void setNomeCampeonato(String aNomeCampeonato) {
        throw new UnsupportedOperationException();
    }

    public void adicionaPontuacoes(Map<String, Integer> aPontuacoesJogo) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object aO) {
        throw new UnsupportedOperationException();
    }

    public RacingManagerLN.SubGestaoCC.Campeonato clone() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        throw new UnsupportedOperationException();
    }
}