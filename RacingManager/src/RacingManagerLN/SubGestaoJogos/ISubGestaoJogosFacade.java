package RacingManagerLN.SubGestaoJogos;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoUsers.User;

import java.util.List;

public interface ISubGestaoJogosFacade {

        public void guardaEscolhasUser(User aUsername, Campeonato aCampeonato, Carro aCarro, Piloto aPiloto);

        public List<Inscricao> getInscricoesCampeonato(String aNomeCampeonato);

        public void removeInscricoesCampeonato(String aNomeCampeonato);

        public String getTipoCarro(String aNomeCampeonato, String aUsername);

        public boolean validaNumeroInscricoes(String aNomeCampeonato);

        public List<String> getJogadoresASimular(String nomeCampeonato);

        public boolean hasCarroC2(String campeonato,String username);
}