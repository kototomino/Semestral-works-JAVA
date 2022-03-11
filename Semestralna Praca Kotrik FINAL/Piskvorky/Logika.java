
/**
 * V triede Logika sa zapisujú ťahy, určuje sa výherný počet znakov a kontrolujú sa výhercovia X O alebo Remíza.
 * 
 * 
 * @author Tomáš Kotrík
 * @version 14.12.2020
 */
public class Logika {
    private int vyhernyPocet;
    private char[][]hraciaPlocha;
    private int tah;
    private Hrac hracX;
    private Hrac hracO;
    private final int cislo;
    /**
     * Constructor for objects of class Logika
     */
    public Logika(int n) {
        this.cislo = n;
        //this.vyhernyPocet = n;
        this.tah = 0;
        this.vyhernyPocet = 0;
        this.hraciaPlocha = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0 ; j < n; j++) {
                this.hraciaPlocha[i][j] = '-';
            }
        }
        this.hracX = new Hrac('X');
        this.hracO = new Hrac('O');
    }

    public int setPocetVyhernychZnakov(int cislo) {
        if (this.cislo <= 8) {
            this.vyhernyPocet = 3;
            return 3;
        } else if (this.cislo <= 15) {
            this.vyhernyPocet = 4;
            return 4;
        } else {
            this.vyhernyPocet = 5;
            return 5;
        }
    }

    public int getVyhernyPocetZnakov() {
        return this.vyhernyPocet;
    }

    public int getTah() {
        return this.tah;
    }
   
    public char getAktualnyZnak() {
        if (this.tah % 2 == 0) {
            return this.hracX.getZnak();
        } else {
            return this.hracO.getZnak();
        }

    }
    
    public void setTah(int t) {
        this.tah = t;
    }
    
    public void setPolicko(int i, int j, char znak) {
        this.hraciaPlocha[i][j] = znak;
    }
    
    public void zapisTah(int i, int j) {
        this.hraciaPlocha[i][j] = this.getAktualnyZnak();
        this.tah++;
    }
    
    public boolean kontrolaVyhercu(int x, int y, char znak) {
        int zhoda = 0;
        int hlavnaDiagonala = 0;
        int vedlajsiaDiagonala = 0;
        int plus = x + y;
        int minus = y - x;
        for ( int i = 0; i < this.cislo; i++) {
            if (this.hraciaPlocha[i][y] == znak) {
                zhoda++;
            } else {
                zhoda = 0;
            }
            if (zhoda == this.vyhernyPocet) {
                return true;    
            }
        }
        zhoda = 0;
        for ( int j = 0; j < this.cislo; j++) {
            if (this.hraciaPlocha[x][j] == znak) {
                zhoda++;
            } else {
                zhoda = 0;
            }
            if (zhoda == this.vyhernyPocet) {
                return true;
            }
        }
        zhoda = 0;
        for (int i = 0; i < this.cislo; i++) {
            for (int j = 0; j < this.cislo; j++) {
                if (i + j == plus) {
                    if (this.hraciaPlocha[i][j] == znak) {
                        vedlajsiaDiagonala++;
                    } else {
                        vedlajsiaDiagonala = 0;
                    }
                    if (vedlajsiaDiagonala == this.vyhernyPocet) {
                        return true;
                    }
                }
                if (j - i == minus) {
                    if (this.hraciaPlocha[i][j] == znak) {
                        hlavnaDiagonala++;
                    } else {
                        hlavnaDiagonala = 0;
                    }
                    if (hlavnaDiagonala == this.vyhernyPocet) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getVyherca(int x, int y) {
        if (this.kontrolaVyhercu(x, y , 'X')) {
            //this.pocitaj.pocitajX();
            return 1;
        } else if (this.kontrolaVyhercu(x, y, 'O')) {
            //this.pocitaj.pocitajO();
            return 2;
        } else if (this.tah == (this.cislo) * (this.cislo)) {
            //this.pocitaj.pocitajR();
            return 0;
        }
        return -1;
    }
}

