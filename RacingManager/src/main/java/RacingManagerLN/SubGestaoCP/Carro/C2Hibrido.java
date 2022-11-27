package RacingManagerLN.SubGestaoCP.Carro;

public class C2Hibrido extends C2 {
    private int _potenciaEletrica;

    public C2Hibrido(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, int pac, tipoPneu tipoPneu, int downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public int getPotenciaEletrica() {
        return this._potenciaEletrica;
    }

    public void setPotenciaEletrica(int aPotenciaEletrica) {
        throw new UnsupportedOperationException();
    }
}