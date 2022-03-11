import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.IOException;
/**
 * Jednoduchá trieda ktorá po vyhodnotení hry ukáže pomocou JOptionPane možnosti pre
 * pokračovanie hry alebo jej ukončenie.
 * 
 * @author Tomáš Kotrík
 * @version 14.12.2020
 */
public class Moznosti {
    private int vysledok;
    private GUI hra;
    /**
     * Constructor for objects of class Moznosti
     */
    public Moznosti () {

    }

    public void showMoznosti(int cislo)throws IOException {
        this.vysledok = cislo;
        if (this.vysledok == 1) {
            this.vysledok = JOptionPane.showConfirmDialog(null, "Vyhral hrac so znakom X\nAk chces pokracovat stlac YES");
            if (this.vysledok == JOptionPane.YES_OPTION) {
                this.hra = new GUI();
            } else {
                System.exit(0);
            }
        } else if (this.vysledok == 2) {
            this.vysledok = JOptionPane.showConfirmDialog(null, "Vyhral hrac so znakom O\nAk chces pokracovat stlac YES");
            if (this.vysledok == JOptionPane.YES_OPTION) {
                this.hra = new GUI();
            } else {
                System.exit(0);
            }
        } else if (this.vysledok == 0) {
            this.vysledok = JOptionPane.showConfirmDialog(null, "Remiza\nAk chces pokracovat stlac YES");
            if (this.vysledok == JOptionPane.YES_OPTION) {
                this.hra = new GUI();
            } else {
                System.exit(0);
            }
        }
    }
}

