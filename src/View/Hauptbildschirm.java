package View;

import Model.Bauplatz;

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
    private JPanel plGrid;
    private Bauplatz[] bauplätze;
    private JPanel gebäudeauswahl;

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

    public Hauptbildschirm(Bauplatz[] bauplätze){
        this.bauplätze = bauplätze;
        this.setSize(1200,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        bildschirmFüllen();
        this.setVisible(true);
    }

    private void bildschirmFüllen() {
        BorderLayout bigLayout = new BorderLayout();

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

        Dimension preferredSizeOfButtons = new Dimension( 300, 50);
        JPanel auswahlpunkte = new JPanel();

        auswahlpunkte.setPreferredSize(new Dimension(300,300));


        arbeitsmenü = new JButton("Arbeit zuweisen");
        arbeitsmenü.setPreferredSize(preferredSizeOfButtons);
        baumenü = new JButton("Bauaufträge");
        baumenü.setPreferredSize(preferredSizeOfButtons);

        auswahlpunkte.add(arbeitsmenü);
        auswahlpunkte.add(baumenü);


        nächsteRunde = new JButton("nächste Runde");
        nächsteRunde.setPreferredSize(preferredSizeOfButtons);

        grid();

        GridLayout äußeresGrid = new GridLayout(2,1);
        JPanel rechtesPanel = new JPanel();
        rechtesPanel.setLayout(äußeresGrid);
        rechtesPanel.add(auswahlpunkte);
        rechtesPanel.add(gebäudeauswahl);
        this.add(nächsteRunde, BorderLayout.SOUTH);

        this.add(rechtesPanel, BorderLayout.EAST);
    }

    public void grid(){


        plGrid = new JPanel();
        plGrid.setLayout(new GridLayout(3,3));
        plGrid.setPreferredSize(new Dimension(1100,900));


        for (int i = 0; i<9;i++){
            plGrid.add(bauplätze[i]);
            bauplätze[i].setBackground(new Color(53,104,45));
        }

        gebäudeauswahl = new JPanel();
        GridLayout gridLayout = new GridLayout(4,1);
        gridLayout.setVgap(10);

        gebäudeauswahl.setLayout(gridLayout);
        JButton gebäude1 = new JButton("Gebäude 1");
        JButton gebäude2 = new JButton("Gebäude 2");
        JButton gebäude3 = new JButton("Gebäude 3");
        JButton gebäude4 = new JButton("Gebäude 4");
        gebäudeauswahl.add(gebäude1);
        gebäudeauswahl.add(gebäude2);
        gebäudeauswahl.add(gebäude3);
        gebäudeauswahl.add(gebäude4);
        gebäudeauswahl.setVisible(false);

        this.add(plGrid,BorderLayout.CENTER);




    }

    public void setBauplatzActionListener(ActionListener listener, int index){
        bauplätze[index].addActionListener(listener);
    }

    public void makeVisible(){
        gebäudeauswahl.setVisible(true);
    }
    public void setBtnArbeitsmenüActionListener(ActionListener listener){arbeitsmenü.addActionListener(listener);}
    public void setBtnNächsteRunde(ActionListener listener){nächsteRunde.addActionListener(listener);}
    public void setBtnBaumenüActionListener(ActionListener listener){baumenü.addActionListener(listener);}
}
