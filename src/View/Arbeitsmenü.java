package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Arbeitsmenü extends JFrame {

    private JButton sammler;
    private JFrame window;
    private JButton jäger;
    private JButton bauarbeiter;
    private JButton handwerker;

    public Arbeitsmenü(){
        this.setSize(900,700);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window = this;

        bildschirmFüllen();

        this.setVisible(true);
    }

    private void bildschirmFüllen() {
        BorderLayout hauptlayout = new BorderLayout();
        this.setLayout(hauptlayout);
        JLabel überschrift = new JLabel("Arbeitsmenü");
        überschrift.setFont(new Font("Calibri",Font.BOLD, 50));
        überschrift.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel menüauswahl = new JPanel();
        JPanel menüpunkte = new JPanel();

        menüauswahl.setLayout(new BoxLayout(menüpunkte,BoxLayout.Y_AXIS));
        Dimension preferredSizeOfButtons = new Dimension( 600, 50);
        sammler = new JButton("Sammler");
        sammler.setPreferredSize(preferredSizeOfButtons);
        menüpunkte.add(sammler);
        jäger = new JButton("Jäger");
        jäger.setPreferredSize(preferredSizeOfButtons);
        menüpunkte.add(jäger);
        bauarbeiter = new JButton("Bauarbeiter");
        bauarbeiter.setPreferredSize(preferredSizeOfButtons);
        menüpunkte.add(bauarbeiter);
        handwerker = new JButton("Handwerker");
        handwerker.setPreferredSize(preferredSizeOfButtons);
        menüpunkte.add(handwerker);

        JButton beenden = new JButton("Beenden");
        beenden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
            }
        });

        this.add(überschrift, BorderLayout.NORTH);
        this.add(menüpunkte,BorderLayout.CENTER);
        this.add(beenden, BorderLayout.SOUTH);
    }

    public void setSammelnActionListener(ActionListener listener){sammler.addActionListener(listener);}
    public void setJagenActionListener(ActionListener listener){jäger.addActionListener(listener);}
    public void setBauenActionListener(ActionListener listener){bauarbeiter.addActionListener(listener);}
    public void setHandwerkenActionListener(ActionListener listener){handwerker.addActionListener(listener);}



}
