package RacingManagerLN.SubGestaoCP.Carro;

public class C1Hibrido extends C1 {
    private int potenciaEletrica;

    public C1Hibrido(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, int pac, tipoPneu tipoPneu, int downforce, modoMotor modoMotor, int fiabilidade) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor, fiabilidade);
    }

    public int getPotenciaEletrica() {
        return this.potenciaEletrica;
    }

    public void setPotenciaEletrica(int aPotenciaEletrica) {
        throw new UnsupportedOperationException();
    }
}