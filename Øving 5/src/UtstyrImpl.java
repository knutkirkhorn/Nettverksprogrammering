import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Knut on 19.03.2017.
 *
 * Klassen UtstyrImpl er mutabel. Antall på lager og nedre grense for
 * bestilling kan endres.
 */
public class UtstyrImpl extends UnicastRemoteObject implements Utstyr {
    public static final int bestillingsfaktor = 5;
    private int nr;//entydig identifikasjon
    private String betegnelse;
    private String leverandør;
    private int påLager;//mengde på lager
    private int nedreGrense;

    public UtstyrImpl(int startNr, String startBetegnelse, String startLeverandør, int startPåLager, int startNedreGrense) throws RemoteException {
        nr = startNr;
        betegnelse = startBetegnelse;
        leverandør = startLeverandør;
        påLager = startPåLager;
        nedreGrense = startNedreGrense;
    }

    public synchronized int finnNr() throws RemoteException{
        return nr;
    }

    public synchronized String finnBetegnelse() throws RemoteException{
        return betegnelse;
    }

    public synchronized String finnLeverandør() throws RemoteException{
        return leverandør;
    }

    public synchronized int finnPåLager() throws RemoteException{
        return påLager;
    }

    public synchronized int finnNedreGrense() throws RemoteException{
        return nedreGrense;
    }

    public synchronized int finnBestKvantum() throws RemoteException{
        if (påLager < nedreGrense) {
            return bestillingsfaktor * nedreGrense;
        }
        return 0;
    }

    /*
     * Endringen kan være positiv eller negativ. Men det er ikke
     * mulig å ta ut mer enn det som fins på lager. Hvis klienten
     * prøver på det, vil metoden returnere false, og intet uttak gjøres.
     */
    public synchronized boolean endreLagerbeholdning(int endring) throws RemoteException{
        System.out.println("Endrer lagerbeholdning, utstyr nr " + nr + ", endring: " + endring);
        if (påLager + endring < 0) {
            return false;
        } else {
            påLager += endring;
            return true;
        }
    }

    public synchronized void settNedreGrense(int nyNedreGrense) throws RemoteException{
        nedreGrense = nyNedreGrense;
    }
}
