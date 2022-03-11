import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Trieda v ktorej sa vytvorí piškvorkové pole pomocou JButton(ov).
 * V tejto triede okrem načítania daného rozmerového poľa je možné aj zapisovať jednotlivé ťahy pomocou metódy setTlacitko().
 * Metóda ulozHru(String nazovSuboru) funguje na princípe uloženie danej rozohratej hry do textového súboru.
 * V súbore sa nachádzajú informácie o uloženom stave hry t.j rozmiestnenie X O koľko bolo vykonaných ťahov a kto je na ťahu.
 * Metóda nacitajMinuluHru(String nazovSuboru) je metóda v ktorej z uloženej hry v textovom súbore prevedie do aktuálnej hry Piškvoriek.
 * 
 * @author Tomáš Kotrík
 * @version 14.12.2020
 */
public class GUI {
    private Pocitadlo pocitadlo;
    private JFrame okno;
    private JButton[][] tlacitka;
    private GridLayout pole;
    private int cislo;
    private Logika piskvorky;
    private Moznosti moznost;
    private int znaky;
    /**
     * Constructor for objects of class GUI
     */

    public GUI() throws IOException {
        this.pocitadlo = new Pocitadlo();
        this.moznost = new Moznosti();
        this.vytvorGUI();
    }

    public GUI(String nazovSuboru) throws IOException {
        this.pocitadlo = new Pocitadlo();
        this.moznost = new Moznosti();
        this.nacitajMinuluHru(nazovSuboru);
    }

    private void vytvorGUI() throws IOException {
        try {
            int start = Integer.parseInt(JOptionPane.showInputDialog(null, "Zadaj rozmer piskvoriek v rozmedzi 3-20", "PISKVORKY",
                        JOptionPane.INFORMATION_MESSAGE));
            if (start >= 3 && start <= 20) {
                this.cislo = start;
                this.piskvorky = new Logika(this.cislo);
                this.pole = new GridLayout(this.cislo, this.cislo);
                this.okno = new JFrame();
                JPanel panel = new JPanel();
                this.tlacitka = new JButton[this.cislo][this.cislo];
                panel.setLayout(this.pole);

                for (int i = 0; i < this.cislo; i++) {
                    for (int j = 0; j < this.cislo; j++) {
                        this.tlacitka[i][j] = new JButton();
                        this.tlacitka[i][j].setBackground(Color.pink);
                        this.tlacitka[i][j].setText("-");
                        panel.add(this.tlacitka[i][j]);
                    }
                }

                this.okno.add(panel);
                this.okno.setTitle("Piskvorky");
                this.okno.setExtendedState(JFrame.MAXIMIZED_BOTH);
                this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.okno.setVisible(true);
                this.setTlacitko();
            } else {
                JOptionPane.showMessageDialog(null, "ZLE ZADANY VSTUP", "PISKVORKY", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "VYTVORIL TOMAS KOTRIK", "PISKVORKY", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void nacitajMinuluHru(String nazovSuboru) throws IOException {
        File subor = new File(nazovSuboru);
        Scanner skener = new Scanner(subor);
        int rozmer = skener.nextInt();
        skener.nextLine();
        int tah = skener.nextInt();
        skener.nextLine();
        String znak = skener.nextLine();
        String[] matica = new String [rozmer];

        for (int i = 0; i < rozmer; i++) {
            matica[i] = skener.nextLine();
        }

        skener.close();

        this.cislo = rozmer;
        this.piskvorky = new Logika(this.cislo);
        if (znak.equals("X")) {
            this.piskvorky.setTah(tah);
        } else {
            this.piskvorky.setTah(tah);
        }

        this.pole = new GridLayout(this.cislo, this.cislo);
        this.okno = new JFrame();
        JPanel panel = new JPanel();
        this.tlacitka = new JButton[this.cislo][this.cislo];
        panel.setLayout(this.pole);

        for (int i = 0; i < this.cislo; i++) {
            for (int j = 0; j < this.cislo; j++) {
                this.tlacitka[i][j] = new JButton();
                this.tlacitka[i][j].setBackground(Color.pink);
                this.tlacitka[i][j].setText("" + matica[i].charAt(j));
                this.piskvorky.setPolicko(i, j, matica[i].charAt(j));
                if (matica[i].charAt(j) == 'X' || matica[i].charAt(j) == 'O' ) {
                    JButton zakliknute = this.tlacitka[i][j];
                    zakliknute.setFont(new Font("Gothic", Font.BOLD, 50));
                    zakliknute.setBackground(Color.white);
                    this.tlacitka[i][j].setEnabled(false);
                }
                
                panel.add(this.tlacitka[i][j]);
            }
        }

        this.okno.add(panel);
        this.okno.setTitle("Piskvorky");
        this.okno.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.okno.setVisible(true);
        this.setTlacitko();

    }
    public void ulozHru(String nazovSuboru) throws IOException {
        File subor = new File(nazovSuboru);
        PrintWriter zapisovac = new PrintWriter(subor);
        zapisovac.println(this.tlacitka.length);
        zapisovac.println(this.piskvorky.getTah());
        zapisovac.println(this.piskvorky.getAktualnyZnak());
        for (int i = 0; i < this.tlacitka.length; i++) {
            for (int j = 0; j < this.tlacitka[i].length; j++) {
                zapisovac.print(this.tlacitka[i][j].getText());
            }
            zapisovac.println();
        }
        zapisovac.close();
    }

    private void setTlacitko() throws IOException {
        this.znaky = this.piskvorky.setPocetVyhernychZnakov(this.cislo);
        for (int i = 0; i < this.cislo; i++) {
            for (int j = 0; j < this.cislo; j++) {
                int x = i;
                int y = j;
                JButton zakliknute = this.tlacitka[i][j];
                zakliknute.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                zakliknute.setText(String.valueOf(GUI.this.piskvorky.getAktualnyZnak()));
                                zakliknute.setFont(new Font("Gothic", Font.BOLD, 50));
                                zakliknute.setBackground(Color.white);
                                GUI.this.piskvorky.zapisTah(x, y);
                                zakliknute.setEnabled(false);
                                int vysledok = GUI.this.piskvorky.getVyherca(x, y);
                                if (vysledok == 1) {
                                    GUI.this.pocitadlo.pocitajX();
                                    GUI.this.moznost.showMoznosti(1);
                                    GUI.this.okno.dispose();
                                } else if (vysledok == 2) {
                                    GUI.this.pocitadlo.pocitajO();
                                    GUI.this.moznost.showMoznosti(2);
                                    GUI.this.okno.dispose();
                                } else if (vysledok == 0) {
                                    GUI.this.pocitadlo.pocitajR();
                                    GUI.this.moznost.showMoznosti(0);
                                    GUI.this.okno.dispose();
                                }
                            } catch (IOException ex) {

                            }
                        }
                    });
            }
        }
    }
}

