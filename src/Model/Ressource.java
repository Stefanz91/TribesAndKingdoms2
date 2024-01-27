package Model;

public class Ressource {
    private String name;
    private int menge;

    public Ressource(String name, int menge) {
        this.name = name;
        this.menge = menge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public void ändereMenge(int mengenänderung){
        this.menge += mengenänderung;
    }
}
