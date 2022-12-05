package RacingManagerLN.SubGestaoCP.Carro;

public class Carro {
    private String idCarro;
    private String marca;
    private String modelo;
    private int cilindrada;
    private int potenciaCombustao;
    private int pac;
    private tipoPneu tipopneu;
    private double downforce;
    private modoMotor motor;

    public enum modoMotor {
        Conservador,
        Normal,
        Agressivo
    }

    public enum tipoPneu {
        Macio,
        Duro,
        Chuva;
    }

    public Carro(String idCarro,String marca,String modelo,int cilindrada,int potenciaCombustao,int pac,tipoPneu tipoPneu,double downforce,modoMotor modoMotor){
        this.idCarro=idCarro;
        this.marca=marca;
        this.modelo=modelo;
        this.cilindrada=cilindrada;
        this.potenciaCombustao=potenciaCombustao;
        this.pac=pac;
        this.tipopneu=tipoPneu;
        this.downforce=downforce;
        this.motor=modoMotor;
    }

    public Carro(Carro c){
        this.idCarro=c.getIdCarro();
        this.marca=c.getMarca();
        this.modelo=c.getModelo();
        this.cilindrada=c.getCilindrada();
        this.potenciaCombustao=c.getPotenciaCombustao();
        this.pac=c.getPac();
        this.tipopneu=c.getTipoPneu();
        this.downforce=c.getDownforce();
        this.motor=c.getMotor();
    }

    public String getIdCarro() {
        return this.idCarro;
    }

    public void setIdCarro(String aIdCarro) {
        this.idCarro=aIdCarro;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String aMarca) {
        this.marca=aMarca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String aModelo) {
        this.modelo=aModelo;
    }

    public int getCilindrada() {
        return this.cilindrada;
    }

    public void setCilindrada(int aCilindrada) {
        this.cilindrada=aCilindrada;
    }

    public int getPotenciaCombustao() {
        return this.potenciaCombustao;
    }

    public void setPotenciaCombustao(int aPotenciaCombustao) {
        this.potenciaCombustao=aPotenciaCombustao;
    }

    public int getPac() {
        return this.pac;
    }

    public void setPac(int aPac) {
        this.pac=aPac;
    }

    public double getDownforce() {
        return this.downforce;
    }

    public void setDownforce(double aDownforce) {
        this.downforce=aDownforce;
    }

    public static tipoPneu converteStringPneu(String aTipo) {
        Carro.tipoPneu t = null;
        if(aTipo.equalsIgnoreCase("macio")){
            t=tipoPneu.Macio;
        }
        if(aTipo.equalsIgnoreCase("duro")){
            t=tipoPneu.Duro;
        }
        if(aTipo.equalsIgnoreCase("chuva")){
            t= tipoPneu.Chuva;
        }
        return t;
    }

    public static modoMotor converteStringMotor(String aModo) {
        Carro.modoMotor m = null;
        if(aModo.equalsIgnoreCase("conservador")){
            m=modoMotor.Conservador;
        }
        if(aModo.equalsIgnoreCase("normal")){
            m=modoMotor.Normal;
        }
        if(aModo.equalsIgnoreCase("agressivo")){
            m=modoMotor.Agressivo;
        }
        return m;
    }

    public tipoPneu getTipoPneu() {
        return this.tipopneu;
    }

    public modoMotor getMotor() {
        return this.motor;
    }

    public void alteraModo(String aModo) {

    }

    public void alteraTipoPneu(String aTipo) {
    }

    public void setTipopneu(tipoPneu t){
        this.tipopneu=t;
    }

    public void setModoMotor(modoMotor m){
        this.motor=m;
    }


    @Override
    public String toString() {
        return "Carro{" +
                "idCarro='" + idCarro + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cilindrada=" + cilindrada +
                ", potenciaCombustao=" + potenciaCombustao +
                ", pac=" + pac +
                ", tipopneu=" + tipopneu +
                ", downforce=" + downforce +
                ", motor=" + motor +
                '}';
    }

    public Carro clone() {
        return new Carro(this);
    }
}