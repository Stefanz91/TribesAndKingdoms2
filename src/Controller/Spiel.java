package Controller;

import Model.*;
import View.Arbeitsmenü;
import View.Arbeitszuweisungsmenü;
import View.Ausbildungszuweisungsmenü;
import View.Hauptbildschirm;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Spiel {

    private int bevölkerung;
    private RessourcenListe ressourcenListe;
    private  int wohnraum;
    private Hauptbildschirm bildschirm;
    private ArrayList<String> namen;

    private ArrayList<Mensch> Menschen;
    private Bauplatz[] bauplätze = new Bauplatz[9];
    private Bauplatz ausgewählterBauplatz;
    private int aktiverBauplatz = -1;
    private ArrayList<Gebäude> verfügbareGebäudeArten;
    private Gebäude ausgewähltesGebäude;
    private Random random = new Random();


    public Spiel() {
        this.bevölkerung = 0;
        ressourcenListe = new RessourcenListe();
        this.wohnraum = 0;
        ausgewählterBauplatz = null;
        ausgewähltesGebäude = null;

        namen = new ArrayList<>();
        for (int i = 0; i<9;i++){
            bauplätze[i] = new Bauplatz();
        }

        gebäudeartenDefinieren();
        System.out.println(verfügbareGebäudeArten.size());

        bildschirm = new Hauptbildschirm(bauplätze);
        bildschirm.setBtnArbeitsmenüActionListener(this::performArbeitsmenü);
        bildschirm.setBtnAusbildungsmenüActionListener(this::performAusbildungsmenü);
        bildschirm.setBtnNächsteRunde(this::performNächsteRunde);
        bildschirm.setBtnBaumenüActionListener(this::performGebäudeBauen);
        bildschirm.setGebäudetypActionListener(this::performGebäudetypZuweisen);
        for (int i = 0; i < bauplätze.length;i++){
            bildschirm.setBauplatzActionListener(this::performGebäudeBauen,i);
        }
        Menschen = new ArrayList<>();
        Mensch Adam = new Mensch("Adam",10, 9, 1, 1, 1);
        Menschen.add(Adam);
        Adam.setArbeit("sammeln");
        Mensch Eva = new Mensch("Eva",9, 5, 5, 2, 2);
        Menschen.add(Eva);
        Eva.setArbeit("jagen");
        Menschen.add(new Mensch("Kain",5,2,3,7,5));
        System.out.println("Menschen"+ Menschen.size());

        bevölkerung = Menschen.size();
        namesArrayFüllen();
    }





    private void gebäudeartenDefinieren(){
        verfügbareGebäudeArten = new ArrayList<>();
        Gebäude zelt = new Gebäude("Zelt",10.0,2);
        verfügbareGebäudeArten.add(zelt);
        Gebäude großesZelt = new Gebäude("Zelt",15.0,4);
        verfügbareGebäudeArten.add(großesZelt);
        Gebäude holzHütte = new Gebäude("Hütte",25.0,6);
        verfügbareGebäudeArten.add(holzHütte);
        Gebäude lehmHütte = new Gebäude("Hütte",30.0,8);
        verfügbareGebäudeArten.add(lehmHütte);
        System.out.println(verfügbareGebäudeArten.size());

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
        for (Mensch mensch: Menschen) {
            int arbeit = mensch.arbeiten();
            if (mensch.getArbeit().equalsIgnoreCase("sammeln")) {
                ressourcenListe.increaseRessource("Nahrung",arbeit);
            } else if (mensch.getArbeit().equalsIgnoreCase("jagen")) {
                ressourcenListe.increaseRessource("Nahrung",arbeit / 2);
                ressourcenListe.increaseRessource("Felle",arbeit / 2);
            } else if (mensch.getArbeit().equalsIgnoreCase("handwerken")) {
                if (mensch.getHandwerken() < ressourcenListe.getRessourcenMenge("Felle")) {
                    ressourcenListe.increaseRessource("Felle",arbeit*(-1));
                    ressourcenListe.increaseRessource("Kleidung",arbeit);
                } else {
                    ressourcenListe.increaseRessource("Kleidung",(int)ressourcenListe.getRessourcenMenge("Felle"));
                    ressourcenListe.setRessource("Felle",0);
                }
            }
            if(mensch.getArbeit().equalsIgnoreCase("bauen")){
                if (aktiverBauplatz>-1){
                    bauplätze[aktiverBauplatz].getGebäude().setBaufortschritt(arbeit);
                    System.out.println(bauplätze[aktiverBauplatz].getGebäude().isBuildt());
                    if (bauplätze[aktiverBauplatz].getGebäude().isBuildt()){
                        bauplätze[aktiverBauplatz].setBackground(bauplätze[aktiverBauplatz].getGebäude().getFarbe());
                        wohnraum += bauplätze[aktiverBauplatz].getGebäude().getWohnraum();
                        aktiverBauplatz = -1;
                    }
                }
            }
            ausbilden();
        }

        int randomNumber;
        if (ressourcenListe.getRessourcenMenge("Nahrung") > bevölkerung){
           ressourcenListe.decreaseRessource("Nahrung" ,bevölkerung);
            randomNumber = random.nextInt(0,namen.size());
            Menschen.add(new Mensch(namen.get(randomNumber)));
        }else {
            randomNumber = random.nextInt(0,Menschen.size());
            Menschen.remove(randomNumber);
        }
        bevölkerung = Menschen.size();
        altern();
        zeigeDaten();
        ausgewähltesGebäude = null;
        ausgewählterBauplatz = null;
    }

    private void ausbilden(){
        Menschen.stream()
                .filter(mensch -> mensch.getWirdAusgebildetVon() != null)
                .forEach(Mensch::ausbildenLassen);
    }

    private void altern(){
        for (Mensch mensch: Menschen) {
            mensch.altern();
        }
    }

    private void performAusbildungsmenü(ActionEvent actionEvent) {
        kinderZeigen();
    }

    private void kinderZeigen(){
        Ausbildungszuweisungsmenü ausbildungszuweisung = new Ausbildungszuweisungsmenü(zähleKinder(),erwachsene());
        for (int i = 0; i < Menschen.size(); i++){
            if (Menschen.get(i).getAlter()<5){
                ausbildungszuweisung.ausbilden(Menschen.get(i),erwachsene());
            }
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



    private void performGebäudeBauen(ActionEvent actionEvent) {

       bildschirm.makeVisible();
       if (actionEvent.getActionCommand().equals("Bauaufträge"))
           return;
       int id = Integer.valueOf(actionEvent.getActionCommand());
       if (ausgewählterBauplatz != null){
           if (ausgewählterBauplatz.getGebäude() != null || !ausgewählterBauplatz.getGebäude().isBuildt()){
               ausgewählterBauplatz.setGebäude(null);
               ausgewählterBauplatz.setBackground(ausgewählterBauplatz.getStartFarbe());
           }
       }
        ausgewählterBauplatz = bauplätze[id-1];
        System.out.println("Bauplatz"+ausgewählterBauplatz.getNummer());
        if (ausgewähltesGebäude != null){
            System.out.println("Gebäude wird gebaut!");
        }
    }

    private void performGebäudetypZuweisen(ActionEvent actionEvent) {
        //System.out.println(actionEvent.getActionCommand());
        String gebäude = actionEvent.getActionCommand();
        switch (gebäude){
            case "Gebäude 1":
                System.out.println("1");
                //ausgewähltesGebäude = verfügbareGebäudeArten.get(0);
                ausgewähltesGebäude = new Gebäude("Zelt",10.0,2);
                break;
            case "Gebäude 2":
                System.out.println("2");
                ausgewähltesGebäude = new Gebäude("Zelt",15.0,4);
                break;
            case "Gebäude 3":
                System.out.println("3");
                ausgewähltesGebäude = new Gebäude("Hütte",25.0,6);
                break;
            case "Gebäude 4":
                System.out.println("4");
                ausgewähltesGebäude = new Gebäude("Hütte",30.0,8);
                break;
            default:
                System.out.println("None");
        }

        if (ausgewählterBauplatz != null) {
            ausgewählterBauplatz.setGebäude(ausgewähltesGebäude);
            ausgewählterBauplatz.setBackground(ausgewähltesGebäude.getFarbe());
            aktiverBauplatz = ausgewählterBauplatz.getNummer() - 1;
        }

    }

    private void arbeiterZeigen(String beruf){
       Arbeitszuweisungsmenü arbeitszuweisung = new Arbeitszuweisungsmenü(beruf, zähleErwachsene());

        Menschen.stream()
                .filter(mensch -> mensch.getAlter() > 4)
                .forEach(arbeitszuweisung::arbeiter);

    }



    private Mensch[] erwachsene(){

        Mensch[] erwachseneMenschen = Menschen.stream()
                .filter(mensch -> mensch.getAlter() > 4)
                .toArray(Mensch[]::new);

        return erwachseneMenschen;
    }

    private int zähleKinder(){

        long kinder = Menschen.stream()
                .filter(mensch -> mensch.getAlter() < 5)
                .count();
        System.out.println("Kinder: " + kinder);
        return (int)kinder;
    }


    private int zähleErwachsene(){

        long erwachsene = Menschen.stream()
                .filter(mensch -> mensch.getAlter()>4)
                .count();

        return (int)erwachsene;
    }


    private void zeigeDaten() {
        bildschirm.setBevölkerung(bevölkerung);
        bildschirm.setNahrung((int)ressourcenListe.getRessourcenMenge("Nahrung"));
        bildschirm.setFelle((int)ressourcenListe.getRessourcenMenge("Felle"));
        bildschirm.setKleidung((int)ressourcenListe.getRessourcenMenge("Kleidung"));
        bildschirm.setWohnraum(wohnraum);
    }


}
