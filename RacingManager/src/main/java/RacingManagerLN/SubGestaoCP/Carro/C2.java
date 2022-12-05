package RacingManagerLN.SubGestaoCP.Carro;

public class C2 extends Carro {
    private final double fiabilidadeTeorica = 0.8;
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

    public double getFiabilidade(){
        double extra=0;
        if(this.afinacao.equals(Afinacao.Salao)) extra=1;
        if(this.afinacao.equals(Afinacao.Corpo)) extra=2;
        if(this.afinacao.equals(Afinacao.Suspensao)) extra=3;
        if(this.afinacao.equals(Afinacao.Motor)) extra=4;
        if(this.afinacao.equals(Afinacao.Freio)) extra=5;
        return this.fiabilidadeTeorica+ (double)(getCilindrada()/1000) +extra;
    }

    public void setFiabilidadeTeorica(double aFiabilidadeTeorica) {
        throw new UnsupportedOperationException();
    }

    public double calculaFiabilidade(Afinacao aAfinacao, int aClima) {
        throw new UnsupportedOperationException();
    }

    public void setAfinacao(Afinacao a){
        this.afinacao=a;
    }
    public void alteraAfinacao(String aAfinacao) {
        throw new UnsupportedOperationException();
    }

    public static Afinacao converteStringAfinacao(String aAfinacao) {
        C2.Afinacao a = null;
        if(aAfinacao.equalsIgnoreCase("freio")){
            a = Afinacao.Freio;
        }
        if(aAfinacao.equalsIgnoreCase("motor")){
            a = Afinacao.Motor;
        }
        if(aAfinacao.equalsIgnoreCase("suspensão") || aAfinacao.equalsIgnoreCase("suspensao") ){
            a = Afinacao.Suspensao;
        }
        if(aAfinacao.equalsIgnoreCase("corpo")){
            a = Afinacao.Corpo;
        }
        if(aAfinacao.equalsIgnoreCase("salao") || aAfinacao.equalsIgnoreCase("salão") ){
            a = Afinacao.Salao;
        }
        return a;
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