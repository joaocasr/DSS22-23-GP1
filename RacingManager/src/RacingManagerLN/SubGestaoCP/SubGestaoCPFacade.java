package RacingManagerLN.SubGestaoCP;


import RacingManagerLN.SubGestaoCP.Carro.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubGestaoCPFacade implements ISubGestaoCPFacade {
    private Map<String,Carro> allCarros;
    private Map<String,Piloto> allPilotos;

    public SubGestaoCPFacade() {
        this.allCarros = new HashMap<>();
        this.allPilotos = new HashMap<>();
    }

    public boolean existeCarro(String aIdCarro) {

       if(allCarros.get(aIdCarro) == null) return false;
       else return true;
    }

    public void removerCarro(String aIdCarro) {

        allCarros.remove(aIdCarro);
    }

    public Boolean validaNomePiloto(String aNome) {

        if(allPilotos.get(aNome) == null) return true;
        else return false;
    }

    public Boolean validarPericia(Float aCts, Float aSva) {
        return aCts + aSva == 1;
    }

    public void registarPiloto(String aNomePiloto, Float aSVA, Float aCTS) {
        Piloto p = new Piloto(aNomePiloto,aSVA,aCTS);
        allPilotos.put(aNomePiloto,p);

    }

    public void removePiloto(String aNome) {
        allPilotos.remove(aNome);
    }

    public void adicionarC1Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, int downforce, int acilndrada, String tipoPneu, String modoMotor) {
        C1Hibrido c1h = new C1Hibrido(aIdCarro,aMarca,aModelo,acilndrada,aPotenciaCombustao,aPac,Carro.converteStringPneu(tipoPneu),downforce,Carro.converteStringMotor(modoMotor));
        allCarros.put(aIdCarro,c1h);
    }

    public void adicionarC1(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int acilndrada, String tipoPneu, int downforce, String modoMotor) {
        C1 c1 = new C1(aIdCarro,aMarca,aModelo,acilndrada,aPotenciaCombustao,aPac,Carro.converteStringPneu(tipoPneu),downforce,Carro.converteStringMotor(modoMotor));
        allCarros.put(aIdCarro,c1);
    }

    public boolean validaPac(float aPac) {

        if(0<=aPac && aPac <= 1) return true;
        else return false;
    }

    public void adicionarC2(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, String tipoPneu, float aPac, int aCilindrada, int downforce, String modoMotor) {
        C2 c2 = new C2(aIdCarro, aMarca, aModelo,aCilindrada, aPotenciaCombustao, aPac, Carro.converteStringPneu(tipoPneu), downforce,Carro.converteStringMotor(modoMotor));

        allCarros.put(aIdCarro,c2);
    }

    public void adicionarC2Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, String tipoPneu, int aCilindrada, String modoMotor, int downforce) {
        C2Hibrido c2h = new C2Hibrido(aIdCarro, aMarca, aModelo,aCilindrada, aPotenciaCombustao, aPac, Carro.converteStringPneu(tipoPneu), downforce,Carro.converteStringMotor(modoMotor));
        c2h.setPotenciaEletrica(aPotenciaEletrica);
        allCarros.put(aIdCarro,c2h);
    }

    public boolean validaCilindradaC2(int aCilindrada) {

        if(3000<=aCilindrada && aCilindrada<=5000) return true;
        else return false;
    }

    public void adicionarGT(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aCilindrada, String tipoPneu, int downforce, String modoMotor) {
        GT gt = new GT(aIdCarro,aMarca,aModelo,aCilindrada,aPotenciaCombustao,aPac,Carro.converteStringPneu(tipoPneu),downforce,Carro.converteStringMotor(modoMotor));
        allCarros.put(aIdCarro,gt);
    }

    public void adicionarGTHibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, int aCilindrada, String tipoPneu, int downforce, String modoMotor) {
        GTHibrido gth = new GTHibrido(aIdCarro,aMarca,aModelo,aCilindrada,aPotenciaCombustao,aPac,Carro.converteStringPneu(tipoPneu),downforce,Carro.converteStringMotor(modoMotor));
        allCarros.put(aIdCarro,gth);
    }

    public boolean validaCilindradaGT(int aCilindrada) {

        if(2000<=aCilindrada && aCilindrada<=4000) return true;
        else return false;
    }

    public void adicionarSC(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, String tipoPneu, int downforce, String modoMotor, int cilidrada) {
        SC sc = new SC(aIdCarro,aMarca,aModelo,cilidrada, aPotenciaCombustao,aPac,Carro.converteStringPneu(tipoPneu), downforce,Carro.converteStringMotor(modoMotor));
        allCarros.put(aIdCarro,sc);
    }

    public List<Carro> getAllCarros() {

        return this.allCarros.values().stream().toList();
    }

    public Carro getCarro(Object aIdCarro) {

        return allCarros.get(aIdCarro);
    }

    public List<Piloto> getAllPilotos() {
        return this.allPilotos.values().stream().toList();
    }

    public List<String> getNomePilotos() {

       return allPilotos.keySet().stream().toList();
    }
}