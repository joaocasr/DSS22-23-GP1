package RacingManagerLN.SubGestaoCC;

import RacingManagerLN.SubGestaoCC.Circuito.Chicane;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import RacingManagerLN.SubGestaoCC.Circuito.Curva;
import RacingManagerLN.SubGestaoCC.Circuito.Reta;

import java.util.List;

public interface ISubGestaoCCFacade {

    public boolean validaNomeCampeonato(String aNomeCampeonato);

    public List<Circuito> getAllCircuitos();
    public List<String> allCircuitosNome();


    public boolean guardaCampeonato(String aNomeCampeonato, int aNjogadores,List<Circuito> l);

    public List<Circuito> getCircuitosDoCampeonato(String aCampNome);

    public boolean updateCircuitoCampeonato(String aNomeCamp, String aCircNomeAntigo, String aCircNomeNovo);

    public boolean apagaCircuitoDoCampeonato(String aCampNome, String aCircNome);

    public void apagaCampeonato(String aCampNome);

    public Campeonato getCampeonato(String aNomeCampeonato);

    public List<Campeonato> getCampeonatos();

    public boolean existeCircuitoemCampeonato(String aNomeCircuito);

    public boolean existeCircuito(String aNomeCircuito);

    public int setPercurso(int aNumCurva, int aNumChicane);

    public boolean registarCircuito(String aNomeCircuito, double aDistancia, int aVoltas, int aNumRetas, int aNumChicanes, int aNumCurvas, List<Curva> aCurvas,List<Reta> aRetas,List<Chicane> aChicanes);

    public Circuito getCircuito(String aNomeCircuito);

    public void modificaCircuito(String aAntigoCircuito, Circuito aCircuito);

    public void removeCIrcuito(String aNomeCircuito);
}
