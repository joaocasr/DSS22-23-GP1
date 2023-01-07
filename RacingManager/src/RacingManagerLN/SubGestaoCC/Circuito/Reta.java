package RacingManagerLN.SubGestaoCC.Circuito;

public class Reta {
    private String idReta;
    private int gdu;

    public String getId() {
        return this.idReta;
    }

    public void setId(String aId) {
        this.idReta=aId;
    }

    public int getGdu() {
        return this.gdu;
    }

    public void setGdu(int aGdu) {
        this.gdu=aGdu;
    }

    public Reta(String aId,int gdu) {
        this.idReta=aId;
        this.gdu = gdu;
    }

    @Override
    public boolean equals(Object obj) {
        // check if the "addresses" of o and this object are the same
        if (this == obj)
            return true;
            // check if o is of instance Reta
        else if (obj instanceof Reta ret)
        {
            // compare fields of o with fields of this instance
            return (this.idReta.equals(ret.idReta))
                    && (this.gdu == ret.gdu);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Reta" + "    idReta= '" + idReta + "'," + "    gdu= " + gdu+";";
    }
}