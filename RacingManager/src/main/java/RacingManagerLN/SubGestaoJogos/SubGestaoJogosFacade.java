package RacingManagerLN.SubGestaoJogos;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoUsers.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubGestaoJogosFacade {
    private Map<String, List<Inscricao>> allInscricoes;

    public SubGestaoJogosFacade(){
        this.allInscricoes = new HashMap<>();
    }

    public void guardaEscolhasUser(User aUser, Campeonato aCampeonato, Carro aCarro, Piloto aPiloto) {
        Inscricao inscricao = new Inscricao(aUser,aCampeonato,aCarro,aPiloto);
        String nomeCampeonato= aCampeonato.getNomeCampeonato();
        if(getInscricaoCampeonato(nomeCampeonato)!=null) {
            List<Inscricao> l = new ArrayList<>();
            l.add(inscricao);
            allInscricoes.put(nomeCampeonato, l);
        }else{
            List<Inscricao> l = allInscricoes.get(nomeCampeonato);
            l.add(inscricao);
            allInscricoes.put(nomeCampeonato, l);
        }
    }

    public List<Inscricao> getInscricaoCampeonato(String aNomeCampeonato) {
        return allInscricoes.get(aNomeCampeonato);
    }

    public void removeInscricoesCampeonato(String aNomeCampeonato) {
        allInscricoes.remove(aNomeCampeonato);

    }

    public String getTipoCarro(String aNomeCampeonato, String aUsername) {
        List<Inscricao> inscricoes = allInscricoes.get(aNomeCampeonato);
        String s="";
        for (Inscricao insc:inscricoes) {
            if(insc.getUser().getUsername().equals(aUsername)){
                s=insc.getCarro().getModelo();
            }

        }
        return  s;

    }

    public boolean validaNumeroInscricoes(String aNomeCampeonato) {
        return allInscricoes.get(aNomeCampeonato).size() >= 2;
    }
}
