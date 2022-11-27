package RacingManagerLN.SubGestaoCP.Carro;

public class SC extends Carro {

    public SC(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, int pac, tipoPneu tipoPneu, int downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public double calculaFiabilidade(int aCilindrada, int aClima) {
        throw new UnsupportedOperationException();
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