package RacingManagerLN.SubGestaoCP.Carro;

public class SC extends Carro {

    public SC(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, float pac, tipoPneu tipoPneu, float downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public double getfiabilidade(double sva,double cts) {
        double d =0.5;
        int m = -1;
        if(sva<0.5) m=1;
        return ((d*0.75*(sva+cts))+d*0.25*getCilindrada())/10;
    }

    public boolean equals(Object aO) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        throw new UnsupportedOperationException();
    }

    public SC clone() {
        throw new UnsupportedOperationException();
    }
}