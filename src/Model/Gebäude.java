package Model;

import java.awt.*;

public class Gebäude {

    private String typ;
    private int baufortschritt;
    private double bauzeit;
    private boolean isBuildt;
    private int wohnraum;

    private Color farbe;

    public Gebäude(String typ, double bauzeit, int wohnraum) {
        this.typ = typ;
        this.baufortschritt = 0;
        this.bauzeit = bauzeit;
        this.isBuildt = false;
        this.farbe = Color.yellow;
        this.wohnraum = wohnraum;
    }

    public int getWohnraum(){
        return wohnraum;
    }

    public String getTyp() {
        return typ;
    }

    public int getBaufortschritt() {
        return baufortschritt;
    }

    public Color getFarbe(){
        return farbe;
    }


    public boolean isBuildt() {
        return isBuildt;
    }

    public void setBaufortschritt(int baufortschritt) {
        this.baufortschritt += baufortschritt;
        if (this.baufortschritt >= bauzeit)isBuildt=true;
        System.out.print(this.baufortschritt);
        farbe = Color.black;
    }


}
