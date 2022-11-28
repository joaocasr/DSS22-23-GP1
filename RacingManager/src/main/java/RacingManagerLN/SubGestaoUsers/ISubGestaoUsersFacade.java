package RacingManagerLN.SubGestaoUsers;

import java.util.Map;

public interface ISubGestaoUsersFacade {

    public boolean existeUser(String aUsername);

    public boolean efetuaLogin(String aUsername, String aPassword);

    public boolean registaUser(String aUsername, String aPassword, boolean aIsAdmin);

    public void logout();

    public String getRankingJogador(String aUsername);

    public String getRanking();

    public User getUser(String aUsername);

    public void setCurrentUser(String aCurrentUser);

    public String getCurrentUser();

    public void atualizaScore(Map<String, Integer> aJogadoresOrdenados, String aPassword);

    public Map<String, Integer> orderByScore();
}