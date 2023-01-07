package RacingManagerLN.SubGestaoCP.Carro;

public class SC extends Carro {

    public SC(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, float pac, tipoPneu tipoPneu, float downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public SC(SC sc){
        super(sc);
    }

    public double getfiabilidade(double sva,double cts) {
        double d =0.5;
        int m = -1;
        if(sva<0.5) m=1;
        return ((d*0.75*(sva+cts))+d*0.25*getCilindrada())/10;
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

    public String toString() {
        return super.toString();
    }

    public SC clone() {
        return new SC(this);
    }
}