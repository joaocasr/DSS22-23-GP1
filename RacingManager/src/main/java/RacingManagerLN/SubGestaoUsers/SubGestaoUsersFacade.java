package RacingManagerLN.SubGestaoUsers;

import java.util.List;
import java.util.Map;

public class SubGestaoUsersFacade implements ISubGestaoUsersFacade {
    private String currentUser;
    private List<Integer> pontuacoes;
    private Map<String,User> allUsers;

    @Override
    public boolean existeUser(String aUsername) {
        return false;
    }

    @Override
    public boolean efetuaLogin(String aUsername, String aPassword) {
        return false;
    }

    @Override
    public void registaUser(String aUsername, String aPassword, boolean aIsAdmin) {

    }

    @Override
    public void logout() {

    }

    @Override
    public String getRankingJogador(String aUsername) {
        return null;
    }

    @Override
    public String getRanking() {
        return null;
    }

    @Override
    public User getUser(String aUsername) {
        return null;
    }

    @Override
    public void setCurrentUser(String aCurrentUser) {

    }

    @Override
    public String getCurrentUser() {
        return null;
    }

    @Override
    public void atualizaScore(Map<String, Integer> aJogadoresOrdenados, String aPassword) {

    }

    @Override
    public Map<String, Integer> orderByScore() {
        return null;
    }
}
