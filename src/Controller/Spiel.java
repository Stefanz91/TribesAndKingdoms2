package Controller;

import Model.Bauplatz;
import Model.Mensch;
import View.Arbeitsmenü;
import View.Arbeitszuweisungsmenü;
import View.Baumenü;
import View.Hauptbildschirm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Spiel {
    private int bevölkerung;
    private int nahrung;
    private int felle;
    private int kleidung;
    private Hauptbildschirm bildschirm;
    private ArrayList<String> namen;

    private ArrayList<Mensch> Menschen;
    private Bauplatz[] bauplätze = new Bauplatz[9];
    private Random random = new Random();
    private Baumenü bau;

    public Spiel() {
        this.bevölkerung = 0;
        this.nahrung = 0;
        this.felle = 0;
        this.kleidung = 0;

        namen = new ArrayList<>();
        for (int i = 0; i<9;i++){
            bauplätze[i] = new Bauplatz();
        }

        bildschirm = new Hauptbildschirm();
        bildschirm.setBtnArbeitsmenüActionListener(this::performArbeitsmenü);
        bildschirm.setBtnNächsteRunde(this::performNächsteRunde);
        bildschirm.setBtnBaumenüActionListener(this::performBaumenü);
        Menschen = new ArrayList<>();
        Mensch Adam = new Mensch("Adam",10, 9, 1, 1, 1);
        Menschen.add(Adam);
        Adam.setArbeit("sammeln");
        Mensch Eva = new Mensch("Eva",9, 5, 5, 2, 2);
        Menschen.add(Eva);
        Eva.setArbeit("jagen");
        Menschen.add(new Mensch("Kain",5,2,3,7,5));

        bevölkerung = Menschen.size();
        namesArrayFüllen();
    }

    private void namesArrayFüllen(){
        namen.add("Abel");
        namen.add("Noah");
        namen.add("Mara");
        namen.add("David");
        namen.add("Goliad");
        namen.add("Luna");
    }


    private void performNächsteRunde(ActionEvent actionEvent) {
        for (Mensch mensch:
                Menschen) {
            int arbeit = mensch.arbeiten();
            if (mensch.getArbeit().equalsIgnoreCase("sammeln")) {
                nahrung += arbeit;
            } else if (mensch.getArbeit().equalsIgnoreCase("jagen")) {
                nahrung += arbeit / 2;
                felle += arbeit / 2;
            } else if (mensch.getArbeit().equalsIgnoreCase("handwerken")) {
                if (mensch.getHandwerken() < felle) {
                    felle -= arbeit;
                    kleidung += arbeit;
                } else {
                    kleidung += felle;
                    felle = 0;
                }
            }

        }
        int randomNumber;
        if (nahrung > bevölkerung){
            nahrung -= bevölkerung;
            randomNumber = random.nextInt(0,namen.size());
            Menschen.add(new Mensch(namen.get(randomNumber)));
        }else {
            randomNumber = random.nextInt(0,Menschen.size());
            Menschen.remove(randomNumber);
        }
        bevölkerung = Menschen.size();
        altern();
        zeigeDaten();
    }

    private void altern(){
        for (Mensch mensch:
             Menschen) {
            mensch.altern();
        }
    }

    private void performArbeitsmenü(ActionEvent actionEvent) {
        Arbeitsmenü arbeitsmenü = new Arbeitsmenü();

        arbeitsmenü.setSammelnActionListener(this::performSammlerZeigen);
        arbeitsmenü.setJagenActionListener(this::performJägerZeigen);
        arbeitsmenü.setBauenActionListener(this::performBauarbeiterZeigen);
        arbeitsmenü.setHandwerkenActionListener(this::performHandwerkerZeigen);
    }

    private void performHandwerkerZeigen(ActionEvent actionEvent) {
        arbeiterZeigen("handwerken");
    }

    private void performBauarbeiterZeigen(ActionEvent actionEvent) {
        arbeiterZeigen("bauen");
    }

    private void performJägerZeigen(ActionEvent actionEvent) {
        arbeiterZeigen("jagen");
    }

    private void performSammlerZeigen(ActionEvent actionEvent) {
        arbeiterZeigen("sammeln");
    }

    private void performBaumenü(ActionEvent actionEvent){
        bau = new Baumenü(bauplätze);

        for (int i = 0; i < bauplätze.length;i++){
          bau.setBauplatzActionListener(this::performGebäudeBauen,i);
        }

    }

    private void performGebäudeBauen(ActionEvent actionEvent) {
       bau.makeVisible();
    }

    private void arbeiterZeigen(String beruf){
        Arbeitszuweisungsmenü arbeitszuweisung = new Arbeitszuweisungsmenü(beruf,countAdults());
        for (int i = 0; i < Menschen.size(); i++){
            if (Menschen.get(i).getAlter()>4){
                arbeitszuweisung.arbeiter(Menschen.get(i));
            }

        }
    }

    private int countAdults(){
        int erwachsene = 0;
        for (int i = 0; i<Menschen.size();i++){
            if (Menschen.get(i).getAlter() > 4)erwachsene++;
        }
        return erwachsene;
    }


    private void zeigeDaten() {
        bildschirm.setBevölkerung(bevölkerung);
        bildschirm.setNahrung(nahrung);
        bildschirm.setFelle(felle);
        bildschirm.setKleidung(kleidung);
    }


}
