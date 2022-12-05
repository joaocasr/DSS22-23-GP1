package RacingManagerLN.SubGestaoCP.Carro;

public class GT extends Carro {

    public GT(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, int pac, tipoPneu tipoPneu, int downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public double getFiabilidade(int voltas){
        double d =(double) (getCilindrada()/1000);
        return (0.85/d)- 0.2*voltas;
    }

    public boolean equals(Object aO) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        throw new UnsupportedOperationException();
    }

    public C2 clone() {
        throw new UnsupportedOperationException();
    }
}