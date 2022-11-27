package RacingManagerLN.SubGestaoJogos.Simulacao;

import RacingManagerLN.SubGestaoJogos.Inscricao;

import java.util.List;
import java.util.Map;

public class Simulacao implements Clima {
    private Map<String, Integer> numConf;
    private Map<String, Integer> pontuacoes;
    private Configuracao allConfiguracoes;
    private List<Inscricao> inscricoesCampeonato;

    @Override
    public double probabilidadePrecipitacao() {
        return 0;
    }

    @Override
    public int clima() {
        return 0;
    }

    public Map<String, Integer> getnumConf() {
        return numConf;
    }

    public void setnumConf(Map<String, Integer> numConf) {
        this.numConf = numConf;
    }

    public Map<String, Integer> getpontuacoes() {
        return pontuacoes;
    }

    public void setpontuacoes(Map<String, Integer> pontuacoes) {
        this.pontuacoes = pontuacoes;
    }

    public Configuracao getallConfiguracoes() {
        return allConfiguracoes;
    }

    public void setallConfiguracoes(Configuracao allConfiguracoes) {
        this.allConfiguracoes = allConfiguracoes;
    }

    public List<Inscricao> getinscricoesCampeonato() {
        return inscricoesCampeonato;
    }

    public void setinscricoesCampeonato(List<Inscricao> inscricoesCampeonato) {
        this.inscricoesCampeonato = inscricoesCampeonato;
    }
}
