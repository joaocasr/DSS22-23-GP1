package RacingManagerLN.SubGestaoCP.Carro;

public class C2Hibrido extends C2 implements MotorHibrido{
    private int pootenciaEletrica;

    public C2Hibrido(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao,int potenciaEletrica, float pac, tipoPneu tipoPneu, float downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
        this.pootenciaEletrica = potenciaEletrica;
    }

    public C2Hibrido(C2Hibrido c2Hibrido){
        super(c2Hibrido);
        this.pootenciaEletrica=c2Hibrido.getPotenciaEletrica();
    }

    public int getPotenciaEletrica() {
        return this.pootenciaEletrica;
    }

    public void setPotenciaEletrica(int aPotenciaEletrica) {
        this.pootenciaEletrica= aPotenciaEletrica;
    }

    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (aO == null || getClass() != aO.getClass()) return false;
        return super.equals(aO);
    }

    public String toString(){
        return super.toString() +",\n Potência Elétrica= "+ this.pootenciaEletrica+" cv";
    }

    public C2Hibrido clone(){
        return new C2Hibrido(this);
    }
}