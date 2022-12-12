package RacingManagerLN.SubGestaoCP.Carro;

public class GT extends Carro {

    public GT(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, float pac, tipoPneu tipoPneu, float downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public double getFiabilidade(int voltas){
        double d =(double) (getCilindrada()/1000);
        return (0.85/d)- 0.2*voltas;
    }

    public boolean equals(Object aO) { // not sure se funciona
        boolean b = false;
        if (this == aO) {
            b= true;
        }
        if (aO == null || getClass() != aO.getClass()) {
            b= false;
        }
        return b;
    }

    public String toString() {
        throw new UnsupportedOperationException();
    }

    public GT clone() {
        throw new UnsupportedOperationException();
    }
}