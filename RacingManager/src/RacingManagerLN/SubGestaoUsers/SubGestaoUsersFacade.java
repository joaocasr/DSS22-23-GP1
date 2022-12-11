package RacingManagerLN.SubGestaoUsers;

import data.UserDAO;

import java.util.*;

public class SubGestaoUsersFacade implements ISubGestaoUsersFacade {
    private String currentUser;
    private static final List<Integer> pontuacoes = null;
    private Map<String,User> allUsers;

    public SubGestaoUsersFacade(){
        this.allUsers = UserDAO.getInstance();
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
    public void atualizaScore(Map<String, Integer> classificacao) {
        List<Map.Entry<String,Integer>> aJogadoresOrdenados= this.orderByScore(classificacao);
        List<Integer> pontuacoes = Arrays.asList(12,10,8,7,6,5,4,3,2,1,0);
        int i=0;
        for(Map.Entry<String,Integer> m : aJogadoresOrdenados){
            if(i>=pontuacoes.size()) this.allUsers.get(m.getKey()).setScore(0);
            this.allUsers.get(m.getKey()).setScore(pontuacoes.get(i));
            i++;
        }
    }

    @Override
    public List<Map.Entry<String,Integer>> orderByScore(Map<String, Integer> s) {
        List<Map.Entry<String, Integer>> entradas = new ArrayList<>(s.entrySet());
        Collections.sort(entradas, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> j1, Map.Entry<String, Integer> j2)
            {
                return j2.getValue()-j1.getValue();
            }
        });
        return entradas;
    }
}