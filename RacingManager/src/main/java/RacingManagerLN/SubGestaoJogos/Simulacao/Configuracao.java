package RacingManagerLN.SubGestaoJogos.Simulacao;

import RacingManagerLN.SubGestaoCP.Carro.C2;
import RacingManagerLN.SubGestaoCP.Carro.Carro;

public class Configuracao {
    private String username;
    private int downforce;
    private Carro.tipoPneu tipopneu; // Carro
    private Carro.modoMotor modomotor;// Carro
    private C2.Afinacao afinacao;// categoria C2

    public Configuracao(String username, int downforce, Carro.tipoPneu tipopneu, Carro.modoMotor modomotor, C2.Afinacao afinacao) {
        this.username = username;
        this.downforce = downforce;
        this.tipopneu = tipopneu;
        this.modomotor = modomotor;
        this.afinacao = afinacao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDownforce() {
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
