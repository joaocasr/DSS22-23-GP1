package RacingManagerLN.SubGestaoJogos.Simulacao;

import RacingManagerLN.SubGestaoCP.Carro.C2;
import RacingManagerLN.SubGestaoCP.Carro.Carro;

public class Configuracao {
    private String username;
    private double downforce;
    private Carro.tipoPneu tipopneu; // Carro
    private Carro.modoMotor modomotor;// Carro
    private C2.Afinacao afinacao;// categoria C2

    public Configuracao(String username, double downforce, String tipopneu, String modomotor, String afinacao) {
        this.username = username;
        this.downforce = downforce;
        this.tipopneu = Carro.converteStringPneu(tipopneu);
        this.modomotor = Carro.converteStringMotor(modomotor);
        this.afinacao = C2.converteStringAfinacao(afinacao);
    }

    public Configuracao(String username, double downforce, String tipopneu, String modomotor) {
        this.username = username;
        this.downforce = downforce;
        this.tipopneu = Carro.converteStringPneu(tipopneu);
        this.modomotor = Carro.converteStringMotor(modomotor);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getDownforce() {
        return downforce;
    }

    public void setDownforce(int downforce) {
        this.downforce = downforce;
    }

    public Carro.tipoPneu getTipopneu() {
        return tipopneu;
    }

    public void setTipopneu(Carro.tipoPneu tipopneu) {
        this.tipopneu = tipopneu;
    }

    public Carro.modoMotor getModomotor() {
        return modomotor;
    }

    public void setModomotor(Carro.modoMotor modomotor) {
        this.modomotor = modomotor;
    }

    public C2.Afinacao getAfinacao() {
        return afinacao;
    }

    public void setAfinacao(C2.Afinacao afinacao) {
        this.afinacao = afinacao;
    }
}
