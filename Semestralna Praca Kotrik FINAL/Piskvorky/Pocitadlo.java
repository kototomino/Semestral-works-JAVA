import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * TRIEDA POČÍTADLO!
 * Jednoduchá trieda na zapisovanie štatistiky do súboru statistika.txt.
 * 
 * 
 * @TOMÁŠ KOTRÍK
 * @14.12.2020
 */
public class Pocitadlo {
    private int x;
    private int o;
    private int r;
    /**
     * Constructor for objects of class Pocitadlo
     */

    public Pocitadlo() throws IOException {
        File subor = new File("statistika.txt");
        Scanner skener = new Scanner(subor);

        skener.next();
        skener.next();
        skener.next();
        this.x = skener.nextInt();
        skener.nextLine();

        skener.next();
        skener.next();
        skener.next();
        this.o = skener.nextInt();
        skener.nextLine();

        skener.next();
        skener.next();
        this.r = skener.nextInt();

        skener.close();

    }

    /**
     * Trieda ktorá ukladá do súboru daný stav hry!
     */
    public void uloz() throws IOException {
        File subor = new File("statistika.txt");
        PrintWriter zapisovac = new PrintWriter(subor);
        zapisovac.println("Vyhra X = " + this.x);
        zapisovac.println("Vyhra O = " + this.o);
        zapisovac.println("Remiza = " + this.r);
        zapisovac.close();
    }

    /**
     * Resetovanie štatistík na 0!
     */
    public void resetuj() throws IOException {
        this.x = 0;
        this.o = 0;
        this.r = 0;
        this.uloz();
    }

    public void pocitajX()throws IOException {
        this.x++;
        this.uloz();
    }

    public void pocitajO()throws IOException {
        this.o++;
        this.uloz();
    }

    public void pocitajR()throws IOException {
        this.r++;
        this.uloz();
    }
}
