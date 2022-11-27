package RacingManagerLN;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoUsers.User;

import java.util.List;

public interface IRacingManagerLN {

    public boolean efetuaLogin(String aUsername, String aPassword);

    public void logout();

    public void registaUser(String aUsername, String aPassword);

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

    public void adicionarCampeonato(String aNomeCampeonato, int aNjogadores);

    public int getTipoCarro(String aNomeCampeonato, String aUsername);

    public void adicionaConfiguracao(String aUsername, int aDownforce, String aTipo, String modo);

    public void adicionaConfiguracaoC2(String aUsername, String aAfinacao, int aDownforce, String aTipo, String modo);

    public boolean numConfiguracoesValido(String aUsername, int aNumCorridas);

    public void registaInscricao();

    public void setUserAtual(String aUsername);

    public void removerCarro(String aIdCarro);

    public void adicionaInscricao(User aUser, Campeonato aCampeonato, Carro aCarro, Piloto aPiloto);
}