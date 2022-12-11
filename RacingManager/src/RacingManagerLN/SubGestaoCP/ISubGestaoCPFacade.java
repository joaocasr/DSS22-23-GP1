package RacingManagerLN.SubGestaoCP;

import RacingManagerLN.SubGestaoCP.Carro.Carro;

import java.util.List;

public interface ISubGestaoCPFacade {

    public boolean existeCarro(String aIdCarro);

    public void removerCarro(String aIdCarro);

    public Boolean validaNomePiloto(String aNome);

    public Boolean validarPericia(Float aCts, Float aSva);

    public void registarPiloto(String aNomePiloto, int aSVA, int aCTS);

    public void removePiloto(String aNome);

    public void adicionarC1Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica);

    public void adicionarC1(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac);

    public boolean validaPac(int aPac);

    public void adicionarC2(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, String aTipoAfinacao, int aPac, int aCilindrada);

    public void adicionarC2Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica, String aTipoAfinacao, int aCilindrada);

    public boolean validaCilindradaC2(int aCilindrada);

    public void adicionarGT(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aCilindrada);

    public void adicionarGTHibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac, int aPotenciaEletrica, int aCilindrada);

    public boolean validaCilindradaGT(int aCilindrada);

    public void adicionarSC(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, int aPac);

    public List<Carro> getAllCarros();

    public Carro getCarro(Object aIdCarro);

    public List<Piloto> getAllPilotos();

    public List<String> getNomePilotos();
}
