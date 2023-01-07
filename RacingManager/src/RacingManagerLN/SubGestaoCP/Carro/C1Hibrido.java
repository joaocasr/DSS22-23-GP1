package RacingManagerLN.SubGestaoCP.Carro;

public class C1Hibrido extends C1 implements MotorHibrido {
    private int potenciaEletrica;

    public C1Hibrido(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao,int potenciaEletrica, float pac, tipoPneu tipoPneu, float downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
        this.potenciaEletrica = potenciaEletrica;
    }

    public C1Hibrido(C1Hibrido c1Hibrido){
            super(c1Hibrido);
            this.potenciaEletrica=c1Hibrido.getPotenciaEletrica();
    }

    public int getPotenciaEletrica() {
        return this.potenciaEletrica;
    }

    public void setPotenciaEletrica(int aPotenciaEletrica) {

        this.potenciaEletrica = aPotenciaEletrica;
    }

    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (aO == null || getClass() != aO.getClass()) return false;
        return super.equals(aO);
    }

    public String toString(){
        return super.toString() +" ; Potência Elétrica="+ this.potenciaEletrica;
    }

    public C1Hibrido clone(){
        return new C1Hibrido(this);
    }
}