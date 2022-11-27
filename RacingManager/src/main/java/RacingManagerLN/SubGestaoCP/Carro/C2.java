package RacingManagerLN.SubGestaoCP.Carro;

public class C2 extends Carro {
    private double fiabilidadeTeorica;
    private Afinacao afinacao;

    public enum Afinacao {
        Freio,
        Motor,
        Suspensao,
        Corpo,
        Salao;
    }

    public C2(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, int pac, tipoPneu tipoPneu, int downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }


    public double getFiabilidadeTeorica() {
        return this.fiabilidadeTeorica;
    }

    public void setFiabilidadeTeorica(double aFiabilidadeTeorica) {
        throw new UnsupportedOperationException();
    }

    public double calculaFiabilidade(Afinacao aAfinacao, int aClima) {
        throw new UnsupportedOperationException();
    }

    public void alteraAfinacao(String aAfinacao) {
        throw new UnsupportedOperationException();
    }

    public Afinacao converteStringAfinacao(String aAfinacao) {
        throw new UnsupportedOperationException();
    }

    public C2 clone() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object aO) {
        throw new UnsupportedOperationException();
    }
}