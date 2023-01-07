package RacingManagerLN.SubGestaoCP.Carro;

public class GTHibrido extends GT implements MotorHibrido{
    private int potenciaEletrica;

    public GTHibrido(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao,int potenciaEletrica, float pac, tipoPneu tipoPneu, float downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
        this.potenciaEletrica = potenciaEletrica;
    }

    public GTHibrido(GTHibrido gtHibrido){
        super(gtHibrido);
        this.potenciaEletrica=gtHibrido.getPotenciaEletrica();
    }

    public int getPotenciaEletrica() {
        return this.potenciaEletrica;
    }

    public void setPotenciaEletrica(int aPotenciaEletrica) {
        this.potenciaEletrica = aPotenciaEletrica;
    }

    public boolean equals(Object aO) {
        boolean b = false;
        if (this == aO) {
            b= true;
        }
        if (aO == null || getClass() != aO.getClass()) {
            b= false;
        }
        return super.equals(aO);
    }

    public String toString(){
        return super.toString() +",\n Potência Elétrica= "+ this.potenciaEletrica+" cv";
    }

    public GTHibrido clone(){
        return new GTHibrido(this);
    }

}
