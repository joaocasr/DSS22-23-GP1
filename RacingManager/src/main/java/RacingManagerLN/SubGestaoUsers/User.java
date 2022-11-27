package RacingManagerLN.SubGestaoUsers;
public class User {
    private String username;
    private String password;
    private boolean isAdmin;
    private int score;
    private String versao;

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
}