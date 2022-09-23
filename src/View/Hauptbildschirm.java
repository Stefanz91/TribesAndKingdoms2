package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hauptbildschirm extends JFrame {
    private JLabel bevölkerung;
    private JLabel nahrung;
    private JLabel felle;
    private JLabel kleidung;
    private JButton arbeitsmenü;
    private JButton nächsteRunde;
    private JButton baumenü;

    public void setBevölkerung(int bevölkerung) {
        this.bevölkerung.setText("Bevölkerung: "+bevölkerung);
    }

    public void setNahrung(int nahrung) {
        this.nahrung.setText("Nahrung: "+nahrung);
    }

    public void setFelle(int felle) {
        this.felle.setText("Felle: "+felle);
    }

    public void setKleidung(int kleidung) {
        this.kleidung.setText("Kleidung: "+kleidung);
    }

    public Hauptbildschirm(){
        this.setSize(900,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        bildschirmFüllen();
        this.setVisible(true);
    }

    private void bildschirmFüllen() {
        this.setLayout(new BorderLayout());
        JPanel ressourchenleiste = new JPanel();
        ressourchenleiste.setLayout(new FlowLayout());
        bevölkerung = new JLabel("Bevölkerung: ");
        ressourchenleiste.add(bevölkerung);
        nahrung = new JLabel("Nahrung: ");
        ressourchenleiste.add(nahrung);
        felle = new JLabel("Felle: ");
        ressourchenleiste.add(felle);
        kleidung = new JLabel("Kleidung: ");
        ressourchenleiste.add(kleidung);
        this.add(ressourchenleiste, BorderLayout.NORTH);

        Dimension preferredSizeOfButtons = new Dimension( 600, 50);
        JPanel auswahlpunkte = new JPanel();
        JPanel auswahlmenü = new JPanel();
        auswahlmenü.setLayout(new BoxLayout(auswahlpunkte,BoxLayout.Y_AXIS));

        arbeitsmenü = new JButton("Arbeit zuweisen");
        arbeitsmenü.setPreferredSize(preferredSizeOfButtons);
        baumenü = new JButton("Bauaufträge");
        baumenü.setPreferredSize(preferredSizeOfButtons);

        auswahlpunkte.add(arbeitsmenü);
        auswahlpunkte.add(baumenü);

        nächsteRunde = new JButton("nächste Runde");
        nächsteRunde.setPreferredSize(preferredSizeOfButtons);
        this.add(nächsteRunde, BorderLayout.SOUTH);

        this.add(auswahlpunkte, BorderLayout.CENTER);
    }

    public void setBtnArbeitsmenüActionListener(ActionListener listener){arbeitsmenü.addActionListener(listener);}
    public void setBtnNächsteRunde(ActionListener listener){nächsteRunde.addActionListener(listener);}
    public void setBtnBaumenüActionListener(ActionListener listener){baumenü.addActionListener(listener);}
}
