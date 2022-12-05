package RacingManagerLN;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCC.Circuito.Chicane;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import RacingManagerLN.SubGestaoCC.Circuito.Curva;
import RacingManagerLN.SubGestaoCC.Circuito.Reta;
import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoJogos.Inscricao;
import RacingManagerLN.SubGestaoUsers.User;

import java.util.List;

public interface IRacingManagerLN {

    public boolean efetuaLogin(String aUsername, String aPassword);

    public boolean existeUser(String username);

    public boolean registaUser(String aUsername, String aPassword, boolean isAdmin);

    public void adicionarC1(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac);

    public void adicionarC1Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica);

    public void adicionarC2(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, String aTipoAfinacao, int aPac, int aCilindrada);

    public void adicionarC2Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica, String aTipoAfinacao, int aCilindrada);

    public void adicionarGT(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aCilindrada);

    public void adicionarGTHibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica, int aCilindrada);

    public void adicionarSC(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac);

    public void adicionarPiloto(String aNomePiloto, int aSVA, int aCTS);

    public void removerPiloto(String aNomePiloto);

    public List<String> getCarros();

    public List<String> getPilotos();

    public List<String> getCampeonatos();

    public List<String> getCircuitos();

    public Circuito getCircuito(String nomeCircuito);

    public boolean adicionarCampeonato(String aNomeCampeonato, int aNjogadores, List<Circuito> l);

    public boolean adicionaCircuito(String nomeCircuito, double distancia, int voltas, int numRetas, int numCurvas, int numChicanes, List<Reta> allRetas, List<Curva> allCurvas, List<Chicane> allChicanes);

    public int getTipoCarro(String aNomeCampeonato, String aUsername);

    public void adicionaConfiguracao(String aUsername, int aDownforce, String aTipo, String modo);

    public void adicionaConfiguracaoC2(String aUsername, String aAfinacao, int aDownforce, String aTipo, String modo);

    public boolean numConfiguracoesValido(String aUsername, int aNumCorridas);

    public void registaInscricao();

    public void setUserAtual(String aUsername);

    public String getCurrentUser();

    public User getUser(String username);

    public void removerCarro(String aIdCarro);

    public void adicionaInscricao(User aUser, Campeonato aCampeonato, Carro aCarro, Piloto aPiloto);

    public String consultaCampeonato(String nomeCampeonato);

    public Campeonato getCampeonato(String nomeCampeonato);

    public List<Inscricao> getInscricoes(String nomeCampeonato);

    public List<String> getJogadoresASimular(String nomeCampeonato);
}