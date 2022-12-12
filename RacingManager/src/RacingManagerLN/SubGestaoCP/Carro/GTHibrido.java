package RacingManagerLN.SubGestaoCP.Carro;

public class GTHibrido extends GT {
    private int _potenciaEletrica;

    public GTHibrido(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, float pac, tipoPneu tipoPneu, int downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public int getPotenciaEletrica() {
        return this._potenciaEletrica;
    }

    public void setPotenciaEletrica(int aPotenciaEletrica) {
        this._potenciaEletrica = aPotenciaEletrica;
    }

    public void operation() {
        throw new UnsupportedOperationException();
    }
}
