package View;

import Model.Mensch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Arbeitszuweisungsmenü extends JFrame {
    private String arbeit;
    private JPanel arbeiterliste;
    private int anzahl;
    private JButton fensterBeenden;
    private JFrame window;

    public Arbeitszuweisungsmenü(String arbeit, int anzahl){
        this.arbeit = arbeit;
        this.anzahl = anzahl;
        window = this;
        this.setSize(900,700);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        bildschirmFüllen();

        this.setVisible(true);

    }

    private void bildschirmFüllen() {
        JPanel überschrift = new JPanel();
        überschrift.setLayout(new BorderLayout());

        JLabel lblÜberschrift = new JLabel(arbeit);
        lblÜberschrift.setFont(new Font("Calibri",Font.BOLD, 50));
        überschrift.add(lblÜberschrift, BorderLayout.NORTH);

        arbeiterliste = new JPanel();
        überschrift.add(arbeiterliste,BorderLayout.CENTER);
        arbeiterliste.setLayout(new GridLayout(anzahl+1,6));
        JLabel name = new JLabel("Name");
        arbeiterliste.add(name);
        JLabel aktuellerBeruf = new JLabel("Aktueller Beruf");
        arbeiterliste.add(aktuellerBeruf);
        JLabel fähigkeit = new JLabel("Fähigkeit: "+ arbeit);
        arbeiterliste.add(fähigkeit);
        JLabel besteFähigkeit = new JLabel("Beste Fähigkeit");
        arbeiterliste.add(besteFähigkeit);
        JLabel fähigkeitspunkte = new JLabel("Fähigkeitspunkte");
        arbeiterliste.add(fähigkeitspunkte);
        JLabel auswählen = new JLabel("");
        arbeiterliste.add(auswählen);
        JLabel abwählen = new JLabel("");
        arbeiterliste.add(abwählen);

        fensterBeenden = new JButton("Beenden");
        fensterBeenden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
            }
        });
        überschrift.add(fensterBeenden,BorderLayout.SOUTH);
        this.add(überschrift);

    }


    public void arbeiter(Mensch arbeiter){
        JLabel name = new JLabel(arbeiter.getName());
        name.setText(arbeiter.getName());
        arbeiterliste.add(name);
        JLabel aktuellerBeruf = new JLabel(arbeiter.getArbeit());
        arbeiterliste.add(aktuellerBeruf);
        JLabel fähigkeit = new JLabel();
        fähigkeit.setText(" "+ arbeiter.fähigkeitAuswählen(arbeit));
        arbeiterliste.add(fähigkeit);
        JLabel besteFähigkeit = new JLabel();
        besteFähigkeit.setText(arbeiter.besteFähigkeit());
        arbeiterliste.add(besteFähigkeit);
        JLabel punkteBesteFähigkeit = new JLabel();
        arbeiterliste.add(punkteBesteFähigkeit);
        punkteBesteFähigkeit.setText(""+arbeiter.fähigkeitAuswählen(arbeiter.besteFähigkeit()));
        JButton auswählen = new JButton("auswählen");
        auswählen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arbeiter.setArbeit(arbeit);
                aktuellerBeruf.setText(arbeiter.getArbeit());
            }
        });
        JButton abwählen = new JButton("abwählen");
        abwählen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arbeiter.setArbeit("");
                aktuellerBeruf.setText(arbeiter.getArbeit());
            }
        });
        arbeiterliste.add(auswählen);
        arbeiterliste.add(abwählen);
    }





}
