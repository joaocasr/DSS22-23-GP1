package RacingManagerLN.SubGestaoCP.Carro;

public class C1 extends Carro {
    private static final double fiabilidadeTeorica = 0.95;

    public C1(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, int pac, tipoPneu tipoPneu, int downforce, modoMotor modoMotor,int fiabilidade) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public C1(C1 c) {
        super(c);
    }

    public double getFiabilidade() {
        return fiabilidadeTeorica;
    }

    public C1 clone() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object aO) {
        throw new UnsupportedOperationException();
    }
}
