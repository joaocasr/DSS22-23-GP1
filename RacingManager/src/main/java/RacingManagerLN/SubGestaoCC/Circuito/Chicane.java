package RacingManagerLN.SubGestaoCC.Circuito;

public class Chicane {
    private String idChicane;
    private int gdu;

    public Chicane(String idChicane, int gdu) {
        this.idChicane = idChicane;
        this.gdu = gdu;
    }

    public String getIdChicane() {
        return idChicane;
    }

    public void setIdChicane(String idChicane) {
        this.idChicane = idChicane;
    }

    public int getGdu() {
        return gdu;
    }

    public void setGdu(int gdu) {
        this.gdu = gdu;
    }

    @Override
    public String toString() {
        return "Chicane{" +
                "idChicane='" + idChicane + '\'' +
                ", gdu=" + gdu +
                '}';
    }
}