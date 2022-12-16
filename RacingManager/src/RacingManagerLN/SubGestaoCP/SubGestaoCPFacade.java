package RacingManagerLN.SubGestaoCP;


import RacingManagerLN.SubGestaoCP.Carro.*;
import data.CarroDAO;
import data.PilotoDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubGestaoCPFacade implements ISubGestaoCPFacade {
    private Map<String,Carro> allCarros;
    private Map<String,Piloto> allPilotos;

    public SubGestaoCPFacade() {
        this.allCarros = CarroDAO.getInstance();
        this.allPilotos = PilotoDAO.getInstance();
    }

    public boolean existeCarro(String aIdCarro) {
        return allCarros.get(aIdCarro) != null;
    }

    public void removerCarro(String aIdCarro) {

        allCarros.remove(aIdCarro);
    }

    public boolean validaNomePiloto(String aNome) {
        return allPilotos.containsKey(aNome);
    }

    public boolean validarPericia(float aCts, float aSva) {
        return (aCts + aSva) == 1;
    }

    public void registarPiloto(String aNomePiloto, Float aSVA, Float aCTS) {
        Piloto p = new Piloto(aNomePiloto,aSVA,aCTS);
        allPilotos.put(aNomePiloto,p.clone());
    }

    public void removePiloto(String aNome) {
        allPilotos.remove(aNome);
    }

    public void adicionarC1Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, float downforce, int acilndrada, String tipoPneu, String modoMotor) {
        C1Hibrido c1h = new C1Hibrido(aIdCarro,aMarca,aModelo,acilndrada,aPotenciaCombustao,aPac,Carro.converteStringPneu(tipoPneu),downforce,Carro.converteStringMotor(modoMotor));
        allCarros.put(aIdCarro,c1h.clone());
    }

    public void adicionarC1(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int acilndrada, String tipoPneu, float downforce, String modoMotor) {
        C1 c1 = new C1(aIdCarro,aMarca,aModelo,acilndrada,aPotenciaCombustao,aPac,Carro.converteStringPneu(tipoPneu),downforce,Carro.converteStringMotor(modoMotor));
        allCarros.put(aIdCarro,c1.clone());
    }

    public boolean validaPac(float aPac) {
        return 0 <= aPac && aPac <= 1;
    }

    public void adicionarC2(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, String tipoPneu, float aPac, int aCilindrada, float downforce, String modoMotor) {
        C2 c2 = new C2(aIdCarro, aMarca, aModelo,aCilindrada, aPotenciaCombustao, aPac, Carro.converteStringPneu(tipoPneu), downforce,Carro.converteStringMotor(modoMotor));

        allCarros.put(aIdCarro,c2.clone());
    }

    public void adicionarC2Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, String tipoPneu, int aCilindrada, String modoMotor, float downforce) {
        C2Hibrido c2h = new C2Hibrido(aIdCarro, aMarca, aModelo,aCilindrada, aPotenciaCombustao, aPac, Carro.converteStringPneu(tipoPneu), downforce,Carro.converteStringMotor(modoMotor));
        c2h.setPotenciaEletrica(aPotenciaEletrica);
        allCarros.put(aIdCarro,c2h.clone());
    }

    public boolean validaCilindradaC2(int aCilindrada) {
        return 3000 <= aCilindrada && aCilindrada <= 5000;
    }

    public void adicionarGT(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aCilindrada, String tipoPneu, float downforce, String modoMotor) {
        GT gt = new GT(aIdCarro,aMarca,aModelo,aCilindrada,aPotenciaCombustao,aPac,Carro.converteStringPneu(tipoPneu),downforce,Carro.converteStringMotor(modoMotor));
        allCarros.put(aIdCarro,gt.clone());
    }

    public void adicionarGTHibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aPotenciaEletrica, int aCilindrada, String tipoPneu, float downforce, String modoMotor) {
        GTHibrido gth = new GTHibrido(aIdCarro,aMarca,aModelo,aCilindrada,aPotenciaCombustao,aPac,Carro.converteStringPneu(tipoPneu),downforce,Carro.converteStringMotor(modoMotor));
        allCarros.put(aIdCarro,gth.clone());
    }

    public boolean validaCilindradaGT(int aCilindrada) {
        return 2000 <= aCilindrada && aCilindrada <= 4000;
    }

    public void adicionarSC(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, String tipoPneu, float downforce, String modoMotor, int cilidrada) {
        SC sc = new SC(aIdCarro,aMarca,aModelo,cilidrada, aPotenciaCombustao,aPac,Carro.converteStringPneu(tipoPneu), downforce,Carro.converteStringMotor(modoMotor));
        allCarros.put(aIdCarro,sc.clone());
    }

    public List<Carro> getAllCarros() {
        return this.allCarros.values().stream().toList();
    }

    public Carro getCarro(String aIdCarro) {
        return allCarros.get(aIdCarro);
    }

    public Piloto getPiloto(String nome){
        return this.allPilotos.get(nome);
    }

    public List<Piloto> getAllPilotos() {
        return this.allPilotos.values().stream().toList();
    }

    public List<String> getNomePilotos() {
       return allPilotos.keySet().stream().toList();
    }
}