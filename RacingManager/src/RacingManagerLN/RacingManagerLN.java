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
import RacingManagerLN.SubGestaoJogos.Inscricao;
import RacingManagerLN.SubGestaoJogos.SubGestaoJogosFacade;
import RacingManagerLN.SubGestaoUsers.SubGestaoUsersFacade;
import RacingManagerLN.SubGestaoUsers.User;

import java.util.List;
import java.util.Map;

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
    public Boolean validaNomePiloto(String aNome){
        return this.subGestaoCPFacade.validaNomePiloto(aNome);
    }

    @Override
    public boolean existeCarro(String aIdCarro){
        return this.subGestaoCPFacade.existeCarro(aIdCarro);
    }
    @Override
    public void adicionarC1(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int acilndrada, String tipoPneu, float downforce, String modoMotor) {

        this.subGestaoCPFacade.adicionarC1(aIdCarro,aMarca,aModelo,aPotenciaCombustao,aPac,acilndrada,tipoPneu,downforce,modoMotor);

    }

    @Override
    public void adicionarC1Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, float downforce, int acilndrada, String tipoPneu, String modoMotor) {
        this.subGestaoCPFacade.adicionarC1Hibrido(aIdCarro,aMarca,aModelo,aPotenciaCombustao,aPac,aPotenciaEletrica,downforce,acilndrada,tipoPneu,modoMotor);
    }

    @Override
    public void adicionarC2(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, String tipoPneu, float aPac, int aCilindrada, float downforce, String modoMotor) {
        this.subGestaoCPFacade.adicionarC2(aIdCarro,aMarca,aModelo,aPotenciaCombustao,tipoPneu,aPac,aCilindrada,downforce,modoMotor);

    }

    @Override
    public void adicionarC2Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, String tipoPneu, int aCilindrada, String modoMotor, float downforce) {
        this.subGestaoCPFacade.adicionarC2Hibrido(aIdCarro,aMarca,aModelo,aPotenciaCombustao,aPac,aPotenciaEletrica,tipoPneu,aCilindrada,modoMotor,downforce);
    }

    @Override
    public void adicionarGT(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aCilindrada, String tipoPneu, float downforce, String modoMotor) {
        this.subGestaoCPFacade.adicionarGT(aIdCarro,aMarca,aModelo,aPotenciaCombustao,aPac,aCilindrada,tipoPneu,downforce,modoMotor);

    }

    @Override
    public void adicionarGTHibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, int aCilindrada, String tipoPneu, float downforce, String modoMotor) {
        this.subGestaoCPFacade.adicionarGTHibrido(aIdCarro,aMarca,aModelo,aPotenciaCombustao,aPac,aPotenciaEletrica,aCilindrada,tipoPneu,downforce,modoMotor);
    }

    @Override
    public void adicionarSC(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, String tipoPneu, float downforce, String modoMotor, int cilidrada) {
        this.subGestaoCPFacade.adicionarSC(aIdCarro,aMarca,aModelo,aPotenciaCombustao,aPac,tipoPneu,downforce,modoMotor,cilidrada);

    }

    @Override
    public void adicionarPiloto(String aNomePiloto, float aSVA, float aCTS) {
        this.subGestaoCPFacade.registarPiloto(aNomePiloto,aSVA,aCTS);

    }

    @Override
    public void removerPiloto(String aNomePiloto) {
        this.subGestaoCPFacade.removePiloto(aNomePiloto);

    }
    @Override
    public Boolean validarPericia(float aCts, float aSva) {
       return this.subGestaoCPFacade.validarPericia(aCts,aSva);
    }

    @Override
    public boolean validaPac(float aPac){
        return this.subGestaoCPFacade.validaPac(aPac);
    }

    @Override
    public List<Carro> getCarros() {
        return this.subGestaoCPFacade.getAllCarros();
    }

    @Override
    public List<String> getPilotos() {
        return this.subGestaoCPFacade.getNomePilotos();
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
        this.subGestaoCPFacade.removerCarro(aIdCarro);

    }

    @Override
    public void adicionaInscricao(User aUser, Campeonato aCampeonato, Carro aCarro, Piloto aPiloto) {

    }

    public String consultaCampeonato(String nomeCampeonato){
        return this.subGestaoCCFacade.consultaCampeonato(nomeCampeonato);
    }

    @Override
    public Campeonato getCampeonato(String nomeCampeonato) {
        return this.subGestaoCCFacade.getCampeonato(nomeCampeonato);
    }

    public List<Inscricao> getInscricoes(String nomeCampeonato){
        return this.subGestaoJogosFacade.getInscricoesCampeonato(nomeCampeonato);
    }

    public List<String> getJogadoresASimular(String nomeCampeonato){
        return this.subGestaoJogosFacade.getJogadoresASimular(nomeCampeonato);
    }

    public boolean hasCarroC2(String campeonato,String jogador){
        return this.subGestaoJogosFacade.hasCarroC2(campeonato,jogador);
    }

    public void atualizaScore(Map<String,Integer> classificacao){
        this.subGestaoUsersFacade.atualizaScore(classificacao);
    }

    public boolean existeCircuito(String circuito){
        return this.subGestaoCCFacade.existeCircuito(circuito);
    }

    public boolean removeCircuito(String circuito){
        return this.subGestaoCCFacade.removeCIrcuito(circuito);
    }

}