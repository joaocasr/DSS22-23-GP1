package RacingManagerLN.SubGestaoCC.Circuito;

public class Curva {
    private String _idCurva;
    private int _gdu;

    public String getId() {
        return this._idCurva;
    }

    public void setId(String aId) {
        this._idCurva = aId;
    }

    public int getGdu() {
        return this._gdu;
    }

    public void setGdu(int aGdu) {
        this._gdu=aGdu;
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
        return "Curva" +"    idCurva= '" + _idCurva + "'," + "    gdu= " + _gdu+";";
    }
}