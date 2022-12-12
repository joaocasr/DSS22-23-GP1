package RacingManagerLN.SubGestaoCP.Carro;

public class C1Hibrido extends C1 {
    private int potenciaEletrica;

    public C1Hibrido(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, float pac, tipoPneu tipoPneu, float downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public int getPotenciaEletrica() {
        return this.potenciaEletrica;
    }

    public void setPotenciaEletrica(int aPotenciaEletrica) {

        this.potenciaEletrica = aPotenciaEletrica;
    }
}