import java.io.IOException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
/**
 * PIŠKVORKY HRÁČ X a HRÁČ O, HRÁČ X Začína!
 * 
 * Trieda Main spúšťa celý program cez statickú metódu triedy.
 * 
 * Pri spustení statickej metódy sa načíta menu kde sa vyskytujú tlačítka
 * 
 * HRAJ, ULOZ, NACITAJ HRU, RESETUJ STATISTIKU.
 * 
 * Tlačítko ULOŽ je na začiatku disabled kvôli tomu že ešte neexistuje hra na uloženie.
 * 
 * Tlačítko Hraj!
 * 
 * Na začiatku si program vypýta veľkosť hracej plochy 3 - 20
 * 
 * a pokračuje v hre Piškvoriek až kým sa nestane výťaz X alebo O.
 * 
 * Tlačítko ULOZ!
 * 
 * Hra sa uloží v momentálnom stave do súboru ktorého názov zadá používateľ.
 * 
 * Tlačítko NACITAJ HRU!
 * 
 * Po úspešnom uložení hry toto tlačítko umožňuje danú hru znovu nahrať a pokračovať v nej.
 * 
 * Tlačítko RESETUJ STATISTIKU!
 * 
 * Resetuje štatistiku výhier v zapisovacom súbore statistikatxt.
 * @TOMÁŠ KOTRÍK
 * @14.12.2020
 */
public class Main {
    private static GUI game;
    private static Pocitadlo pocitadlo;

    public static void main(String[] args) throws IOException {
        ImageIcon ikonaUloz = new ImageIcon("C:\\Users\\ASUS-PC\\Desktop\\ŠKOLA\\Zimný semester-20200928T170222Z-001\\Zimný semester\\Informatika 1\\projekty BlueJ\\Prace\\Semestralna Praca Kotrik FINAL\\Piskvorky\\icons\\save.png");
        ImageIcon ikonaHraj = new ImageIcon("C:\\Users\\ASUS-PC\\Desktop\\ŠKOLA\\Zimný semester-20200928T170222Z-001\\Zimný semester\\Informatika 1\\projekty BlueJ\\Prace\\Semestralna Praca Kotrik FINAL\\Piskvorky\\icons\\tictactoe.png");
        ImageIcon ikonaResetuj = new ImageIcon("C:\\Users\\ASUS-PC\\Desktop\\ŠKOLA\\Zimný semester-20200928T170222Z-001\\Zimný semester\\Informatika 1\\projekty BlueJ\\Prace\\Semestralna Praca Kotrik FINAL\\Piskvorky\\icons\\table.png");
        ImageIcon ikonaNacitaj = new ImageIcon("C:\\Users\\ASUS-PC\\Desktop\\ŠKOLA\\Zimný semester-20200928T170222Z-001\\Zimný semester\\Informatika 1\\projekty BlueJ\\Prace\\Semestralna Praca Kotrik FINAL\\Piskvorky\\icons\\load.png");
        GridLayout lay = new GridLayout(1, 3);
        JPanel panel = new JPanel();
        JFrame okno = new JFrame("PISKVORKY");
        JButton hra = new JButton("HRAJ", ikonaHraj);
        JButton nacitaj = new JButton("NACITAJ HRU", ikonaNacitaj);
        JButton uloz = new JButton("ULOZ HRU", ikonaUloz);
        JButton reset = new JButton("RESETUJ STATISTIKU", ikonaResetuj);
        uloz.setEnabled(false);
        panel.setLayout(lay);
        panel.add(hra);
        panel.add(uloz);
        panel.add(nacitaj);
        panel.add(reset);
        okno.add(panel);
        okno.setBounds(0, 0, 1000, 200);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
        JOptionPane.showMessageDialog(null, "PRED HRANIM ODPORUCAM RESETOVAT STATISTIKU !", "PISKVORKY", JOptionPane.INFORMATION_MESSAGE);
        hra.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        game = new GUI();
                        uloz.setEnabled(true);
                        uloz.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        String subor = (JOptionPane.showInputDialog(null, "Zadaj subor pre ulozenie hry !", "PISKVORKY", JOptionPane.INFORMATION_MESSAGE));
                                        game.ulozHru(subor);
                                        JOptionPane.showMessageDialog(null, "HRA BOLA USPESNE ULOZENA !", "PISKVORKY", JOptionPane.INFORMATION_MESSAGE);
                                        System.exit(0);
                                    } catch (IOException ex) {
                                        

                                    }

                                }
                            });
                    } catch (IOException ex) { 
                    
                    } catch (Exception a) {

                    }

                }
            } );
        nacitaj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String subor = (JOptionPane.showInputDialog(null, "Zadaj subor minulej hry !", "PISKVORKY", JOptionPane.INFORMATION_MESSAGE));
                        game = new GUI(subor);
                    } catch (Exception a) {

                    }

                }
            });
        reset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        pocitadlo = new Pocitadlo();
                        pocitadlo.resetuj();
                        JOptionPane.showMessageDialog(null, "STATISTIKA BOLA RESETOVANA !", "PISKVORKY", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {

                    }
                }
            });
    }
}
