package RacingManagerLN.SubGestaoUsers;
public class User {
    private String username;
    private String password;
    private boolean isAdmin;
    private int score;
    private String versao;

    public User(String username,String password,boolean isAdmin,int score,String versao){
        this.username=username;
        this.password=password;
        this.isAdmin=isAdmin;
        this.score=score;
        this.versao=versao;
    }

    public User(User user) {
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.isAdmin=user.getIsAdmin();
        this.score=user.getScore();
        this.versao=user.getVersao();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String aUsername) {
        this.username = aUsername;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String aPassword) {
        this.password=aPassword;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean aIsAdmin) {
        this.isAdmin=aIsAdmin;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int aScore) {
        this.score=aScore;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(String aVersao) {
        this.versao=aVersao;
    }

    public String nameNScore() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.username).append("-").append(this.score);
        return sb.toString();
    }
    public User clone(){
        return new User(this);
    }
}