import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by Knut on 01.03.2017.
 */

@Entity

public class Konto {

    @Id
    @Column(name="konto_nummer")
    private int kontonummer;

    @Version
    @Column(name="lock_field")
    private int lock; //Oppgave 4 - Bruke optimistisk låsing

    private double saldo;
    private String eier;

    public Konto() {

    }

    public Konto(int kontonummer, double saldo, String eier) {
        this.kontonummer = kontonummer;
        this.lock = 0;
        this.saldo = saldo;
        this.eier = eier;
    }

    public Konto(int kontonummer, int lock, double saldo, String eier) {
        this.kontonummer = kontonummer;
        this.lock = lock;
        this.saldo = saldo;
        this.eier = eier;
    }

    public void setKontonummer(int kontonummer) {
        this.kontonummer = kontonummer;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setEier(String eier) {
        this.eier = eier;
    }

    public int getKontonummer() {
        return kontonummer;
    }

    public int getLock() {
        return lock;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getEier() {
        return eier;
    }

    public void trekk(double belop) {
        this.saldo -= belop;
    }
}
