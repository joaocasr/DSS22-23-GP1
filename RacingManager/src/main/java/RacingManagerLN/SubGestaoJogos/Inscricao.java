package RacingManagerLN.SubGestaoJogos;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoUsers.User;

public class Inscricao {
    private User user;
    private Campeonato campeonato;
    private Carro carro;
    private Piloto piloto;

    public Inscricao(User user, Campeonato campeonato, Carro carro, Piloto piloto) {
        this.user = user;
        this.campeonato = campeonato;
        this.carro = carro;
        this.piloto = piloto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }
}
