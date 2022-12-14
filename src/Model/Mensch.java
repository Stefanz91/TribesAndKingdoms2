package Model;

import java.util.ArrayList;
import java.util.Random;

public class Mensch {
    private String name;
    Random random = new Random();

    public String getArbeit() {
        return arbeit;
    }

    public void setArbeit(String arbeit) {
        this.arbeit = arbeit;
    }

    private String arbeit;
    private double alter;

    private ArrayList<Fähigkeit>fähigkeiten = new ArrayList<>();

    public Mensch(String name){
        fähigkeiten = new ArrayList<>();
        this.name =name;
        this.alter = 0;
        this.arbeit ="";
        addPrimitiveFähigkeiten();
    }

    public Mensch(String name,double alter, double sammeln, double jagen, double bauen, double handwerken){
        this.name = name;
        this.alter = alter;
        this.arbeit = "";
        fähigkeiten.add(new Fähigkeit("sammeln",sammeln));
        fähigkeiten.add(new Fähigkeit("jagen",jagen));
        fähigkeiten.add(new Fähigkeit("bauen",bauen));
        fähigkeiten.add(new Fähigkeit("handwerken", handwerken));
    }



    private void addPrimitiveFähigkeiten(){
        addFähigkeit("sammeln");
        addFähigkeit("jagen");
        addFähigkeit("bauen");
        addFähigkeit("handwerken");
    }

    private void addFähigkeit(String bezeichnung){
        fähigkeiten.add(new Fähigkeit(bezeichnung,0));
    }

    public void altern(){
        alter += 1;
    }

    public String getName() {
        return name;
    }

    public double getAlter() {
        return alter;
    }

    public double getSammeln() {
        return fähigkeiten.get(0).getFähigkeitsstärke();
    }

    public double getJagen() {
        return fähigkeiten.get(1).getFähigkeitsstärke();
    }

    public double getBauen() {
        return fähigkeiten.get(2).getFähigkeitsstärke();
    }

    public double getHandwerken() {
        return fähigkeiten.get(3).getFähigkeitsstärke();
    }

    public int arbeiten(){
        double fähigkeit = fähigkeitAuswählen(arbeit);
        int erzeugnis = (int)fähigkeit;
        fähigkeitVerbessern();
        return erzeugnis;
    }

    private void fähigkeitVerbessern(){
        switch (arbeit){
            case "sammeln": fähigkeiten.get(0).fähigkeitVerbessern();
            break;
            case "jagen": fähigkeiten.get(1).fähigkeitVerbessern();
            break;
            case "bauen": fähigkeiten.get(2).fähigkeitVerbessern();
            break;
            case "handwerken": fähigkeiten.get(3).fähigkeitVerbessern();
            break;
            default:
        }
    }



    public String besteFähigkeit(){
        Fähigkeit besteFähigkeit = fähigkeiten.get(0);
        for (int i = 1; i<fähigkeiten.size();i++){
            if (fähigkeiten.get(i).getFähigkeitsstärke() >besteFähigkeit.getFähigkeitsstärke() )besteFähigkeit = fähigkeiten.get(i);
        }

        return besteFähigkeit.getBezeichnung();
    }

    public double fähigkeitAuswählen(String arbeit){
        switch (arbeit){
            case "sammeln":return fähigkeiten.get(0).getFähigkeitsstärke();
            case "jagen": return fähigkeiten.get(1).getFähigkeitsstärke();
            case "bauen": return fähigkeiten.get(2).getFähigkeitsstärke();
            case "handwerken": return fähigkeiten.get(3).getFähigkeitsstärke();
            default: return 0.0;
        }
    }


}
