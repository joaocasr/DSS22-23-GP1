package RacingManagerLN.SubGestaoJogos.Simulacao;

import RacingManagerLN.SubGestaoCP.Carro.C2;
import RacingManagerLN.SubGestaoCP.Carro.Carro;

public class Configuracao {
        private String username;
        private int downforce;
        private Carro.tipoPneu tipopneu;
        private Carro.modoMotor modomotor;
        private C2.Afinacao afinacao;

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
