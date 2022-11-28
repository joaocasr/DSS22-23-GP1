package RacingManagerLN;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCC.Circuito.Chicane;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import RacingManagerLN.SubGestaoCC.Circuito.Curva;
import RacingManagerLN.SubGestaoCC.Circuito.Reta;
import RacingManagerLN.SubGestaoCC.SubGestaoCCFacade;
import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoCP.SubGestaoCPFacade;
import RacingManagerLN.SubGestaoJogos.SubGestaoJogosFacade;
import RacingManagerLN.SubGestaoUsers.SubGestaoUsersFacade;
import RacingManagerLN.SubGestaoUsers.User;

import java.util.List;

public class RacingManagerLN implements IRacingManagerLN{
    private SubGestaoUsersFacade subGestaoUsersFacade;
    private SubGestaoCPFacade subGestaoCPFacade;
    private SubGestaoCCFacade subGestaoCCFacade;
    private SubGestaoJogosFacade subGestaoJogosFacade;

    public RacingManagerLN(){
        this.subGestaoUsersFacade = new SubGestaoUsersFacade();
        this.subGestaoCPFacade = new SubGestaoCPFacade();
        this.subGestaoCCFacade= new SubGestaoCCFacade();
        this.subGestaoJogosFacade = new SubGestaoJogosFacade();
    }

    @Override
    public boolean efetuaLogin(String aUsername, String aPassword) {
        return this.subGestaoUsersFacade.efetuaLogin(aUsername,aPassword);
    }

    public boolean existeUser(String username){
        return this.subGestaoUsersFacade.existeUser(username);
    }

    @Override
    public boolean registaUser(String aUsername, String aPassword,boolean isAdmin) {
        return this.subGestaoUsersFacade.registaUser(aUsername,aPassword,isAdmin);
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
    public List<String> getCircuitos() {
        return this.subGestaoCCFacade.allCircuitosNome();
    }

    @Override
    public Circuito getCircuito(String nomeCircuito) {
        return subGestaoCCFacade.getCircuito(nomeCircuito);
    }

    @Override
    public boolean adicionarCampeonato(String aNomeCampeonato, int aNjogadores,List<Circuito> l) {
        return this.subGestaoCCFacade.guardaCampeonato(aNomeCampeonato,aNjogadores,l);
    }

    public boolean adicionaCircuito(String nomeCircuito, double distancia, int voltas, int numRetas, int numCurvas, int numChicanes, List<Reta> allRetas, List<Curva> allCurvas, List<Chicane> allChicanes){
        return this.subGestaoCCFacade.registarCircuito(nomeCircuito,distancia,voltas,numRetas,numChicanes,numCurvas,allCurvas,allRetas,allChicanes);
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
        this.subGestaoUsersFacade.setCurrentUser(aUsername);
    }

    public String getCurrentUser(){
        return this.subGestaoUsersFacade.getCurrentUser();
    }
    public User getUser(String username){
        return this.subGestaoUsersFacade.getUser(username);
    }

    @Override
    public void removerCarro(String aIdCarro) {

    }

    @Override
    public void adicionaInscricao(User aUser, Campeonato aCampeonato, Carro aCarro, Piloto aPiloto) {

    }

    public String consultaCampeonato(String nomeCampeonato){
        return this.subGestaoCCFacade.consultaCampeonato(nomeCampeonato);
    }
}