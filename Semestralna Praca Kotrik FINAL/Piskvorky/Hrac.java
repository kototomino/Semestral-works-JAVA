
/**
 * Trieda v ktorej sa inicializuje daný hráč X , O.
 * 
 * @author Tomáš Kotrík
 * @version 14.12.2020
 */
public class Hrac {
    private char znak;
    private int tah;
    /**
     * Constructor for objects of class Hrac
     */
    public Hrac(char paZnak) {
        this.znak = paZnak;
    }
    public char getZnak() {
        return this.znak;
    }
}
