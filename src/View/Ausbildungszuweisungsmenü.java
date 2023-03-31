package View;

import Model.Mensch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ausbildungszuweisungsmenü extends JFrame {

    private JLabel nameDesKindes;
    private JLabel alterDesKindes;
    private JComboBox ausbildungsberufe;
    private int anzahlKinder;
    private Mensch[] erwachsene;
    private JPanel ausbildungsliste;
    private Mensch ausbilder;
    private JLabel ausbildungsberuf;

    public Ausbildungszuweisungsmenü(int anzahlKinder, Mensch[] erwachsene){
        this.anzahlKinder = anzahlKinder;
        this.erwachsene = erwachsene;
        this.setSize(900,700);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Ausbildungszuweisungsmenü window = this;

        bildschirmFüllen(anzahlKinder);

        this.setVisible(true);
    }

    private void bildschirmFüllen(int anzahlKinder) {
        JPanel hauptpanel = new JPanel();
        this.add(hauptpanel);
        JLabel überschrift = new JLabel("Ausbildungszuweisungsmenü");
        hauptpanel.add(überschrift);

        ausbildungsliste = new JPanel();
        this.add(ausbildungsliste);
        ausbildungsliste.setLayout(new GridLayout(anzahlKinder+1, 4));
        nameDesKindes = new JLabel("Name");
        ausbildungsliste.add(nameDesKindes);
        alterDesKindes = new JLabel("Alter");
        ausbildungsliste.add(alterDesKindes);
        JLabel ausbilder = new JLabel("Ausbilder");
        ausbildungsliste.add(ausbilder);
        JLabel ausbildungsberuf = new JLabel("Ausbildungsberuf");
        ausbildungsliste.add(ausbildungsberuf);
    }

    public void ausbilden(Mensch kind, Mensch[] erwachsene){
        nameDesKindes = new JLabel(kind.getName());
        ausbildungsliste.add(nameDesKindes);
        alterDesKindes = new JLabel();
        alterDesKindes.setText(" " + kind.getAlter());
        ausbildungsliste.add(alterDesKindes);

        String comboBoxListeErwachsene[] = new String[erwachsene.length+1];
        comboBoxListeErwachsene[0] = "kein Ausbilder";
        for (int i = 1; i< erwachsene.length+1; i++){
            comboBoxListeErwachsene[i] = erwachsene[i-1].getName();
        }
        JComboBox comboBoxErwachsene = new JComboBox(comboBoxListeErwachsene);
        ausbildungsberuf = new JLabel("kein Ausbildungsberuf");
        ausbilder = null;
        if (kind.getWirdAusgebildetVon() != null){
            comboBoxErwachsene.setSelectedItem(kind.getWirdAusgebildetVon().getName());
            ausbilder = kind.getWirdAusgebildetVon();
        }
        comboBoxErwachsene.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //erwachsene[comboBoxErwachsene.getSelectedIndex()].setBildetaus(true);
                if (comboBoxErwachsene.getSelectedIndex()>0){
                    kind.setWirdAusgebildetVon(erwachsene[comboBoxErwachsene.getSelectedIndex()-1]);
                    ausbilder = erwachsene[comboBoxErwachsene.getSelectedIndex()-1];
                    ausbildungsberuf.setText(ausbilder.getArbeit());
                }
            }
        });
        if (ausbilder == null){
            ausbildungsberuf = new JLabel("kein Ausbildungsberuf");
        }else {
            ausbildungsberuf.setText(ausbilder.getArbeit());
        }

        ausbildungsliste.add(comboBoxErwachsene);
        ausbildungsliste.add(ausbildungsberuf);
    }
}
