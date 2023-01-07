package RacingManagerLN.SubGestaoCP.Carro;

import java.util.Objects;

public class Carro {
    private String idCarro;
    private String marca;
    private String modelo;
    private int cilindrada;
    private int potenciaCombustao;
    private float pac;
    private tipoPneu tipopneu;
    private float downforce;
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

    public Carro(String idCarro,String marca,String modelo,int cilindrada,int potenciaCombustao,float pac,tipoPneu tipoPneu,float downforce,modoMotor modoMotor){
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

    public float getPac() {
        return this.pac;
    }

    public void setPac(int aPac) {
        this.pac=aPac;
    }

    public float getDownforce() {
        return this.downforce;
    }

    public void setDownforce(float aDownforce) {
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
        Carro.modoMotor m = converteStringMotor(aModo);
        setModoMotor(m);
    }

    public void alteraTipoPneu(String aTipo) {
        Carro.tipoPneu p = converteStringPneu(aTipo);
        setTipopneu(p);
    }

    public void setTipopneu(tipoPneu t){
        this.tipopneu=t;
    }

    public void setModoMotor(modoMotor m){
        this.motor=m;
    }

    public String getCategoria(){
        String categoria="";
        if(this instanceof C1Hibrido){
            categoria="C1Hibrido";
        }
        else if(this instanceof C2Hibrido){
            categoria="C2Hibrido";
        }
        else if(this instanceof GTHibrido){
            categoria="GTHibrido";
        }
        else if(this instanceof C1){
            categoria="C1";
        }
        else if(this instanceof C2){
            categoria="C2";
        }
        else if(this instanceof GT){
            categoria="GT";
        }
        else if(this instanceof SC){
            categoria="SC";
        }
        return categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return cilindrada == carro.cilindrada && potenciaCombustao == carro.potenciaCombustao && Float.compare(carro.pac, pac) == 0 &&
                Float.compare(carro.downforce, downforce) == 0 && carro.getIdCarro().equals(idCarro) && carro.getMarca().equals(marca) &&
                carro.getModelo().equals(modelo) && carro.getTipoPneu().equals(tipopneu) && carro.getMotor().equals(motor);
    }

    @Override
    public String toString() {
        return "Carro -> " +
                "idCarro= " + idCarro + ",\n" +
                " marca= " + marca + ",\n" +
                " modelo= " + modelo + ",\n" +
                " categoria= " + this.getCategoria() + ",\n" +
                " cilindrada= " + cilindrada + "cm3 ,\n" +
                " Potência Combustão= " + potenciaCombustao + "cv\n" +
                " pac= " + pac + ",\n" +
                " tipopneu= " + tipopneu + ",\n" +
                " downforce= " + downforce + ",\n" +
                " motor= " + motor;
    }

    public Carro clone() {
        return new Carro(this);
    }
}