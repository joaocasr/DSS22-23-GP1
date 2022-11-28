package RacingManagerLN.SubGestaoCC.Circuito;

public class Curva {
    private String _idCurva;
    private int _gdu;

    public String getId() {
        throw new UnsupportedOperationException();
    }

    public void setId(String aId) {
        throw new UnsupportedOperationException();
    }

    public int getGdu() {
        return this._gdu;
    }

    public void setGdu(int aGdu) {
        throw new UnsupportedOperationException();
    }

    public Curva(String aId,int gdu) {
        this._idCurva=aId;
        this._gdu=gdu;
    }

    @Override
    public String toString() {
        return "Curva{" +
                "_idCurva='" + _idCurva + '\'' +
                ", _gdu=" + _gdu +
                '}';
    }
}