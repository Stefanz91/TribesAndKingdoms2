package Model;

public class Gebäude {

    private String typ;
    private int baufortschritt;
    private double bauzeit;
    private boolean isBuildt;

    public Gebäude(String typ, int baufortschritt, double bauzeit, boolean isBuildt) {
        this.typ = typ;
        this.baufortschritt = 0;
        this.bauzeit = bauzeit;
        this.isBuildt = false;
    }

    public String getTyp() {
        return typ;
    }

    public int getBaufortschritt() {
        return baufortschritt;
    }


    public boolean isBuildt() {
        return isBuildt;
    }

    public void setBaufortschritt(int baufortschritt) {
        this.baufortschritt += baufortschritt;
        if (baufortschritt >= bauzeit)isBuildt=true;
    }


}
