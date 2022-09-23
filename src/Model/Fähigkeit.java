package Model;

import java.util.Random;

public class Fähigkeit {
    private final String bezeichnung;
    private double fähigkeitsstärke;
    private double talent;

    public Fähigkeit(String bezeichnung, double fähigkeitsstärke){
        this.bezeichnung = bezeichnung;
        this.fähigkeitsstärke = fähigkeitsstärke;
        Random random = new Random();
        talent = random.nextInt(0,3);
    }

    public String getBezeichnung(){
        return bezeichnung;
    }
    public double getFähigkeitsstärke(){
        return fähigkeitsstärke;
    }

    public void fähigkeitVerbessern(){
        fähigkeitsstärke += 0.2;
        fähigkeitsstärke *= 100;
        fähigkeitsstärke = Math.round(fähigkeitsstärke);
        fähigkeitsstärke /= 100;
    }
}
