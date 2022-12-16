package RacingManagerLN.SubGestaoCP.Carro;

public class C2 extends Carro {
    private double fiabilidadeTeorica = 0.8;
    private Afinacao afinacao;

    public String toString(C2 c2) {
        return c2.toString() +
                ", fiabilidadeTeorica=" + fiabilidadeTeorica +
                ", afinacao=" + afinacao;
    }

    public enum Afinacao {
        Freio,
        Motor,
        Suspensao,
        Corpo,
        Salao;
    }

    public C2(String idCarro, String marca, String modelo, int cilindrada, int potenciaCombustao, float pac, tipoPneu tipoPneu, float downforce, modoMotor modoMotor) {
        super(idCarro, marca, modelo, cilindrada, potenciaCombustao, pac, tipoPneu, downforce, modoMotor);
    }

    public C2(C2 c2){
        super(c2);
        this.afinacao = c2.getAfinacao();
    }

    public double getFiabilidadeTeorica() {
        return this.fiabilidadeTeorica;
    }

    public double getFiabilidade(){
        double extra=-1;
        if(this.afinacao==null) extra =0;
        else if(this.afinacao.equals(Afinacao.Salao)) extra=0.10;
        else if(this.afinacao.equals(Afinacao.Corpo)) extra=0.12;
        else if(this.afinacao.equals(Afinacao.Suspensao)) extra=0.13;
        else if(this.afinacao.equals(Afinacao.Motor)) extra=0.14;
        else if(this.afinacao.equals(Afinacao.Freio)) extra=0.15;
        double d = (double)getCilindrada()/40000;
        double fiab = this.fiabilidadeTeorica+ d +extra;
        return fiab;
    }

    public void setFiabilidadeTeorica(double aFiabilidadeTeorica) {
        this.fiabilidadeTeorica = aFiabilidadeTeorica;
    }

    public double calculaFiabilidade(Afinacao aAfinacao, int aClima) {
        throw new UnsupportedOperationException();
    }

    public void setAfinacao(Afinacao a){
        this.afinacao=a;
    }
    public void alteraAfinacao(String aAfinacao) {
        Afinacao a = converteStringAfinacao(aAfinacao);
        setAfinacao(a);
    }
    public Afinacao getAfinacao() {
        return this.afinacao;
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

    public C2 clone(){;
        return new C2(this);
    }

    public boolean equals(Object aO) { // not sure se funciona
        if (this == aO) {
            return true;
        }
        if (aO == null || getClass() != aO.getClass()) {
            return false;
        }
        C2 other = (C2) aO;
        return  fiabilidadeTeorica == other.getFiabilidade();
    }
}