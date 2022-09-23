package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Bauplatz extends JButton {
    private int nummer;
    private static int zähler = 0;
    private boolean bebaut = false;
    private Gebäude gebäude = null;

    public Bauplatz(){
        zähler++;
        this.nummer = zähler;
        this.setText(String.valueOf(nummer));
    }

    public int getNummer() {
        return nummer;
    }

    public boolean isBebaut() {
        return bebaut;
    }

    public void setBebaut(boolean bebaut) {
        this.bebaut = bebaut;
    }

    public Gebäude getGebäude() {
        return gebäude;
    }

    public void setGebäude(Gebäude gebäude) {
        this.gebäude = gebäude;

    }
}
