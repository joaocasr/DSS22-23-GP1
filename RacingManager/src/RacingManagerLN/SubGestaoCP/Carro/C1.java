package RacingManagerLN.SubGestaoCP.Carro;

public class C1 extends Carro {
    private static final double fiabilidadeTeorica = 0.95;

    public C1(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, float pac, tipoPneu tipoPneu, float downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public C1(C1 c) {
        super(c);
    }

    public double getFiabilidade() {
        return fiabilidadeTeorica;
    }

    public C1 clone() {
        return new C1(this);
    }

    public String toString() {
        return super.toString() + "\nfiabilidade= " + fiabilidadeTeorica;
    }

    public boolean equals(Object o) {
        if(o==this) return true;
        if(o==null || this.getClass()!=o.getClass()) return false;
        C1 c1 = (C1) o;
        return super.equals(o) && c1.getFiabilidade()==fiabilidadeTeorica;
    }
}

