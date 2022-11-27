package RacingManagerLN.SubGestaoCP.Carro;

public class Carro {
    private String idCarro;
    private String marca;
    private String modelo;
    private int cilindrada;
    private int potenciaCombustao;
    private int pac;
    private tipoPneu tipopneu;
    private int downforce;
    private modoMotor motor;

    public enum modoMotor {
        Normal,
        Macio,
        Chuva;
    }

    public enum tipoPneu {
        Macio,
        Duro,
        Chuva;
    }

    public Carro(String idCarro,String marca,String modelo,int cilindrada,int potenciaCombustao,int pac,tipoPneu tipoPneu,int downforce,modoMotor modoMotor){
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

    public int getDownforce() {
        return this.downforce;
    }

    public void setDownforce(int aDownforce) {
        this.downforce=aDownforce;
    }

    public tipoPneu converteStringPneu(String aTipo) {
        Carro.tipoPneu t = null;
        if(aTipo.equals("macio")||aTipo.equals("Macio")){
            t=tipoPneu.Macio;
        }
        if(aTipo.equals("duro")||aTipo.equals("Duro")){
            t=tipoPneu.Duro;
        }
        if(aTipo.equals("chuva")||aTipo.equals("Chuva")){
            t= tipoPneu.Chuva;
        }
        return t;
    }

    public modoMotor converteStringMotor(String aModo) {
        return this.motor;
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