package RacingManagerLN.SubGestaoCP;


import RacingManagerLN.SubGestaoCP.Carro.Carro;

import java.util.List;
import java.util.Map;

public class SubGestaoCPFacade implements ISubGestaoCPFacade {
    private Map<String,Carro> allCarros;
    private Map<String,Piloto> allPilotos;

    public SubGestaoCPFacade() {
    }

    public boolean existeCarro(String aIdCarro) {
        throw new UnsupportedOperationException();
    }

    public void removerCarro(String aIdCarro) {
        throw new UnsupportedOperationException();
    }

    public Boolean validaNomePiloto(String aNome) {
        throw new UnsupportedOperationException();
    }

    public Boolean validarPericia(Float aCts, Float aSva) {
        throw new UnsupportedOperationException();
    }

    public void registarPiloto(String aNomePiloto, int aSVA, int aCTS) {
        throw new UnsupportedOperationException();
    }

    public void removePiloto(String aNome) {
        throw new UnsupportedOperationException();
    }

    public void adicionarC1Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica) {
        throw new UnsupportedOperationException();
    }

    public void adicionarC1(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac) {
        throw new UnsupportedOperationException();
    }

    public boolean validaPac(int aPac) {
        throw new UnsupportedOperationException();
    }

    public void adicionarC2(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, String aTipoAfinacao, int aPac, int aCilindrada) {
        throw new UnsupportedOperationException();
    }

    public void adicionarC2Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica, String aTipoAfinacao, int aCilindrada) {
        throw new UnsupportedOperationException();
    }

    public boolean validaCilindradaC2(int aCilindrada) {
        throw new UnsupportedOperationException();
    }

    public void adicionarGT(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aCilindrada) {
        throw new UnsupportedOperationException();
    }

    public void adicionarGTHibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica, int aCilindrada) {
        throw new UnsupportedOperationException();
    }

    public boolean validaCilindradaGT(int aCilindrada) {
        throw new UnsupportedOperationException();
    }

    public void adicionarSC(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac) {
        throw new UnsupportedOperationException();
    }

    public List<Carro> getAllCarros() {
        throw new UnsupportedOperationException();
    }

    public Carro getCarro(Object aIdCarro) {
        throw new UnsupportedOperationException();
    }

    public List<Piloto> getAllPilotos() {
        throw new UnsupportedOperationException();
    }

    public List<String> getNomePilotos() {
        throw new UnsupportedOperationException();
    }
}