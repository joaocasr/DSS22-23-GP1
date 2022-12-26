package RacingManagerLN;

import RacingManagerLN.Exceptions.CampeonatoInexistenteException;
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
import java.util.Map;

public interface IRacingManagerLN {

    public boolean efetuaLogin(String aUsername, String aPassword);

    public boolean existeUser(String username);

    public boolean registaUser(String aUsername, String aPassword, boolean isAdmin);

    Boolean validaNomePiloto(String aNome);

    boolean existeCarro(String aIdCarro);

    void adicionarC1(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int acilndrada, String tipoPneu, float downforce, String modoMotor);

    void adicionarC1Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, float downforce, int acilndrada, String tipoPneu, String modoMotor);

    void adicionarC2(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, String tipoPneu, float aPac, int aCilindrada, float downforce, String modoMotor);

    void adicionarC2Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, String tipoPneu, int aCilindrada, String modoMotor, float downforce);

    void adicionarGT(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aCilindrada, String tipoPneu, float downforce, String modoMotor);

    void adicionarGTHibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, int aCilindrada, String tipoPneu, float downforce, String modoMotor);

    void adicionarSC(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, String tipoPneu, float downforce, String modoMotor, int cilidrada);

    void adicionarPiloto(String aNomePiloto, float aSVA, float aCTS);

    public void removerPiloto(String aNomePiloto);

    Boolean validarPericia(float aCts, float aSva);

    boolean validaPac(float aPac);

    public List<Carro> getCarros();

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

    public Campeonato getCampeonato(String nomeCampeonato) throws CampeonatoInexistenteException;

    public List<Inscricao> getInscricoes(String nomeCampeonato) throws CampeonatoInexistenteException;

    public List<String> getJogadoresASimular(String nomeCampeonato);

    public boolean hasCarroC2(String campeonato,String jogador);

    public void atualizaScore(Map<String,Integer> classificacao);

    public boolean existeCircuito(String circuito);

    public boolean removeCircuito(String circuito);

    public boolean removeCampeonato(String campeonato);

    public Carro getCarro(String carro);

    public Piloto getPiloto(String pilotoname);

    public List<User> getAllUsers();

    public void mudaVersao(String versao,String username);

    public boolean validaNumInscricoes(String campeonato);
}