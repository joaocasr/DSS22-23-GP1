package RacingManagerLN;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoUsers.User;

import java.util.List;

public class RacingManagerLN implements IRacingManagerLN{
    @Override
    public boolean efetuaLogin(String aUsername, String aPassword) {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public void registaUser(String aUsername, String aPassword) {

    }

    @Override
    public void adicionarC1(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac) {

    }

    @Override
    public void adicionarC1Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica) {

    }

    @Override
    public void adicionarC2(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, String aTipoAfinacao, int aPac, int aCilindrada) {

    }

    @Override
    public void adicionarC2Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica, String aTipoAfinacao, int aCilindrada) {

    }

    @Override
    public void adicionarGT(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aCilindrada) {

    }

    @Override
    public void adicionarGTHibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica, int aCilindrada) {

    }

    @Override
    public void adicionarSC(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac) {

    }

    @Override
    public void adicionarPiloto(String aNomePiloto, int aSVA, int aCTS) {

    }

    @Override
    public void removerPiloto(String aNomePiloto) {

    }

    @Override
    public List<String> getCarros() {
        return null;
    }

    @Override
    public List<String> getPilotos() {
        return null;
    }

    @Override
    public List<String> getCampeonatos() {
        return null;
    }

    @Override
    public void adicionarCampeonato(String aNomeCampeonato, int aNjogadores) {

    }

    @Override
    public int getTipoCarro(String aNomeCampeonato, String aUsername) {
        return 0;
    }

    @Override
    public void adicionaConfiguracao(String aUsername, int aDownforce, String aTipo, String modo) {

    }

    @Override
    public void adicionaConfiguracaoC2(String aUsername, String aAfinacao, int aDownforce, String aTipo, String modo) {

    }

    @Override
    public boolean numConfiguracoesValido(String aUsername, int aNumCorridas) {
        return false;
    }

    @Override
    public void registaInscricao() {

    }

    @Override
    public void setUserAtual(String aUsername) {

    }

    @Override
    public void removerCarro(String aIdCarro) {

    }

    @Override
    public void adicionaInscricao(User aUser, Campeonato aCampeonato, Carro aCarro, Piloto aPiloto) {

    }
}