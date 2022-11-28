package RacingManagerLN.SubGestaoCC.Circuito;

public class Reta {
    private String idReta;
    private int gdu;

    public String getId() {
        throw new UnsupportedOperationException();
    }

    public void setId(String aId) {
        throw new UnsupportedOperationException();
    }

    public void getGdu() {
        throw new UnsupportedOperationException();
    }

    public void setGdu(int aGdu) {
        throw new UnsupportedOperationException();
    }

    public Reta(String aId,int gdu) {
        this.idReta=aId;
        this.gdu = gdu;
    }

    @Override
    public String toString() {
        return "Reta{" +
                "idReta='" + idReta + '\'' +
                ", gdu=" + gdu +
                '}';
    }
}