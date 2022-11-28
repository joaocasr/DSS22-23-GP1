package RacingManagerLN.SubGestaoUsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubGestaoUsersFacade implements ISubGestaoUsersFacade {
    private String currentUser;
    private static final List<Integer> pontuacoes = null;
    private Map<String,User> allUsers;

    public SubGestaoUsersFacade(){
        this.allUsers = new HashMap<>();
    }

    @Override
    public boolean existeUser(String aUsername) {
        return this.allUsers.containsKey(aUsername);
    }

    @Override
    public boolean efetuaLogin(String aUsername, String aPassword) {
        boolean b = existeUser(aUsername) && this.allUsers.get(aUsername).getPassword().equals(aPassword);
        return b;
    }

    @Override
    public boolean registaUser(String aUsername, String aPassword, boolean aIsAdmin) {
        boolean b = false;
        if(!existeUser(aUsername)){
            User u = new User(aUsername,aPassword,aIsAdmin,0,"B");
            this.allUsers.put(aUsername,u.clone());
            b=true;
        }
        return b;
    }

    @Override
    public void logout() {
        this.currentUser=null;
    }


    @Override
    public String getRankingJogador(String aUsername) {
        StringBuilder sb = new StringBuilder();
        this.allUsers.values().stream().map(x->sb.append(x.nameNScore()).append("\n"));
        return sb.toString();
    }

    @Override
    public String getRanking() {
        StringBuilder sb = new StringBuilder();
        this.allUsers.values().stream().map(x->sb.append(x.nameNScore()).append("\n"));
        return sb.toString();
    }

    @Override
    public User getUser(String aUsername) {
        return this.allUsers.get(aUsername);
    }

    @Override
    public void setCurrentUser(String aCurrentUser) {
        this.currentUser=aCurrentUser;
    }

    @Override
    public String getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void atualizaScore(Map<String, Integer> aJogadoresOrdenados, String aPassword) {

    }

    @Override
    public Map<String, Integer> orderByScore() {
        return null;
    }
}
