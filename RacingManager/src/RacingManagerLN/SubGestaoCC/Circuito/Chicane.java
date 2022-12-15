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
    public boolean equals(Object obj) {
        // check if the "addresses" of o and this object are the same
        if (this == obj)
            return true;
            // check if o is of instance Chicane
        else if (obj instanceof Chicane chi)
        {
            // compare fields of o with fields of this instance
            return (this.idChicane.equals(chi.idChicane))
                    && (this.gdu == chi.gdu);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Chicane{\n" +
                "idChicane= '" + idChicane + "'," +
                "gdu= " + gdu +
                '}';
    }
}