package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RessourcenListe {
    private List<Ressource> ressourceListe;

    public RessourcenListe(){
        ressourceListe = baueRessourcenListe();
    }

    public void addRessource(String name){
        ressourceListe.add(new Ressource(name, 0));
    }

    public Ressource getRessource(String ressourcenName){
        return ressourceListe.stream()
                .filter(ressource -> ressource.getName() == ressourcenName)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    public void increaseRessource(String ressourcenName, int menge){
        ressourceListe.stream()
                .filter(ressource -> ressource.getName() == ressourcenName)
                .forEach(ressource -> ressource.ändereMenge(menge));
    }

    private List<Ressource> baueRessourcenListe(){
        return ressourcenString().stream()
                .map( ressource -> new Ressource(ressource,0))
                .collect(Collectors.toList());
    }

    private List<String> ressourcenString(){
        List<String> ressourcen = new ArrayList<>();
        ressourcen.add("Nahrung");
        ressourcen.add("Felle");
        ressourcen.add("Kleidung");

        return ressourcen;
    }

    public double getRessourcenMenge(String ressourcenName) {
        return ressourceListe.stream()
                .filter(ressource -> ressource.getName() == ressourcenName)
                .filter(Objects::nonNull)
                .map(ressource -> ressource.getMenge())
                .findFirst()
                .orElse(null);
    }

    public void setRessource(String ressourceName, int menge) {
        ressourceListe.stream()
                .filter(ressource -> ressource.getName() == ressourceName)
                .filter(Objects::nonNull)
                .forEach(ressource -> ressource.setMenge(menge));
    }

    public void decreaseRessource(String ressourcenName, int menge) {
        ressourceListe.stream()
                .filter(ressource -> ressource.getName() == ressourcenName)
                .forEach(ressource -> ressource.ändereMenge(menge*(-1)));
    }
}
