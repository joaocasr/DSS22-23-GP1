package RacingManagerLN.SubGestaoCP.Carro;

public class GT extends Carro {

    public GT(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, float pac, tipoPneu tipoPneu, float downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public GT(GT gt){
        super(gt);
    }

    public double getFiabilidade(int voltas){
        double d =(double) (getCilindrada()/1000);
        return (1/d)- 0.2*voltas;
    }

    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (aO == null || getClass() != aO.getClass()) return false;
        return super.equals(aO);
    }

    public String toString() {
        return super.toString();
    }

    public GT clone() {
        return new GT(this);
    }
}