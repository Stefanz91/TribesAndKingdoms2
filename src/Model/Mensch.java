package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Mensch {
    private String name;
    Random random = new Random();

    public String getArbeit() {
        return arbeit;
    }

    public void setArbeit(String arbeit) {
        if (wirdAusgebildetVon != null){
            wirdAusgebildetVon.bildetAus = false;
            wirdAusgebildetIn = null;
            wirdAusgebildetVon = null;
        }
        this.arbeit = arbeit;
    }

    private String arbeit;
    private double alter;

    private boolean bildetAus;
    private Mensch wirdAusgebildetVon;
    private String wirdAusgebildetIn;

    private ArrayList<Fähigkeit>fähigkeiten = new ArrayList<>();

    public Mensch(String name){
        fähigkeiten = new ArrayList<>();
        this.name =name;
        this.alter = 0;
        this.arbeit ="";
        this.bildetAus = false;
        this.wirdAusgebildetVon = null;
        this.wirdAusgebildetIn = null;
        addPrimitiveFähigkeiten();
    }

    public void setWirdAusgebildetVon(Mensch meister){
        if (wirdAusgebildetVon != null){
            wirdAusgebildetVon.bildetAus = false;
        }
        wirdAusgebildetVon = meister;
        wirdAusgebildetIn = meister.getArbeit();
        meister.bildetAus = true;
    }

    public Mensch getWirdAusgebildetVon(){
        return wirdAusgebildetVon;
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
        int erzeugnis;
        if (bildetAus){
            erzeugnis = (int)fähigkeit/2;
        }else {
            erzeugnis = (int)fähigkeit;
        }
        fähigkeitVerbessern(arbeit);
        return erzeugnis;
    }

    public void ausbildenLassen(){
        fähigkeitVerbessern(wirdAusgebildetIn);
        if (fähigkeitAuswählen(wirdAusgebildetIn) < wirdAusgebildetVon.fähigkeitAuswählen(getArbeit()))
            fähigkeitVerbessern(wirdAusgebildetIn);
    }

    private void fähigkeitVerbessern(String fachbezeichnung){
        switch (fachbezeichnung){
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
//        for(Fähigkeit f : fähigkeiten){
//           if(f.getBezeichnung().equals(fachbezeichnung)){
//               f.fähigkeitVerbessern();
//               break;}
//           }

        fähigkeiten.stream()
                .filter(f-> f.getBezeichnung().equals(fachbezeichnung))
                .forEach(Fähigkeit::fähigkeitVerbessern);
    }



    public String besteFähigkeit(){
//        Fähigkeit besteFähigkeit = fähigkeiten.get(0);
//        for (int i = 1; i<fähigkeiten.size();i++){
//            if (fähigkeiten.get(i).getFähigkeitsstärke() >besteFähigkeit.getFähigkeitsstärke() )besteFähigkeit = fähigkeiten.get(i);
//        }


        Fähigkeit besteFähigkeit = fähigkeiten.stream()
                .sorted(Comparator.comparing(Fähigkeit::getFähigkeitsstärke).reversed())
                .findFirst()
                .orElse(null);

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
