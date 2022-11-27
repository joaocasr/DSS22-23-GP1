package RacingManagerLN.SubGestaoJogos;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoUsers.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubGestaoJogosFacade {
    private Map<String, List<Inscricao>> allInscricoes;

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
        throw new UnsupportedOperationException();
    }

    public void removeInscricoesCampeonato(String aNomeCampeonato) {
        throw new UnsupportedOperationException();
    }

    public int getTipoCarro(String aNomeCampeonato, String aUsername) {
        throw new UnsupportedOperationException();
    }

    public boolean validaNumeroInscricoes(String aNomeCampeonato) {
        throw new UnsupportedOperationException();
    }
}
