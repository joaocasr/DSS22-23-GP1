package RacingManagerLN.SubGestaoCP;

import RacingManagerLN.SubGestaoCP.Carro.Carro;

import java.util.List;

public interface ISubGestaoCPFacade {

    public boolean existeCarro(String aIdCarro);

    public void removerCarro(String aIdCarro);

    public boolean validaNomePiloto(String aNome);

    public boolean validarPericia(float aCts, float aSva);

    public void registarPiloto(String aNomePiloto, Float aSVA, Float aCTS);

    public void removePiloto(String aNome);

    public void adicionarC1Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao,int aPotenciaEletrica, float aPac, float downforce, int acilndrada, String tipoPneu, String modoMotor);

    public void adicionarC1(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int acilndrada, String tipoPneu, float downforce, String modoMotor);

    public boolean validaPac(float aPac);

    public void adicionarC2(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, String tipoPneu, float aPac, int aCilindrada, float downforce, String modoMotor);

    public void adicionarC2Hibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao,int aPotenciaEletrica ,float aPac, String tipoPneu, int aCilindrada, String modoMotor, float downforce);

    public boolean validaCilindradaC2(int aCilindrada);

    public void adicionarGT(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, int aCilindrada, String tipoPneu, float downforce, String modoMotor);

    public void adicionarGTHibrido(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao,int aPotenciaEletrica ,float aPac, int aCilindrada, String tipoPneu, float downforce, String modoMotor);

    public boolean validaCilindradaGT(int aCilindrada);

    public void adicionarSC(String aIdCarro, String aMarca, String aModelo, int aPotenciaCombustao, float aPac, String tipoPneu, float downforce, String modoMotor, int cilidrada);

    public List<Carro> getAllCarros();

    public Carro getCarro(String aIdCarro);

    public List<Piloto> getAllPilotos();

    public List<String> getNomePilotos();

    public Piloto getPiloto(String pilotoname);

}
