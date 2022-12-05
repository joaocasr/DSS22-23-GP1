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
    public boolean equals(Object obj) {
        // check if the "addresses" of o and this object are the same
        if (this == obj)
            return true;
            // check if o is of instance Curva
        else if (obj instanceof Curva cur)
        {
            // compare fields of o with fields of this instance
            return (this._idCurva.equals(cur._idCurva))
                    && (this._gdu == cur._gdu);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Curva{\n" +
                "    _idCurva= '" + _idCurva + "',\n" +
                "    _gdu= " + _gdu + "\n" +
                '}';
    }
}