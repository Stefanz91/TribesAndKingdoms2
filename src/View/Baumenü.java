package View;

import Model.Bauplatz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Baumenü extends JFrame {

    private JFrame window;
    private Bauplatz[] bauplätze;
    private JPanel gebäudeauswahl;

    public Baumenü(Bauplatz[] bauplätze){
        this.bauplätze = bauplätze;
        this.setSize(900,700);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        grid();
        this.setVisible(true);
        window = this;
    }

    public void grid(){

        JPanel plBildschirm = new JPanel();
        plBildschirm.setBackground(Color.BLUE);
        this.add(plBildschirm);
        BorderLayout äußeresLayout = new BorderLayout();
        äußeresLayout.setHgap(10);
        plBildschirm.setLayout(äußeresLayout);
        JPanel plGrid = new JPanel();
        plGrid.setLayout(new GridLayout(3,3));
        plBildschirm.add(plGrid,BorderLayout.CENTER);


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

        JButton btnZurück = new JButton("zurück");
        plBildschirm.add(btnZurück,BorderLayout.SOUTH);
        plBildschirm.add(gebäudeauswahl,BorderLayout.EAST);
        btnZurück.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
            }
        });

    }

    public void setBauplatzActionListener(ActionListener listener, int index){
        bauplätze[index].addActionListener(listener);
    }

    public void makeVisible(){
        gebäudeauswahl.setVisible(true);
    }
}
