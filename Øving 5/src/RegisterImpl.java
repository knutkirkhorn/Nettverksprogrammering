import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Knut on 19.03.2017.
 *
 * Et register holder orden på en mengde Utstyrsobjekter. En klient kan legge inn nye
 * UtstyrImpl-objekter i registeret, og også endre varebeholdningen for et
 * allerede registrert objekt. Bestillingsliste for alle varene kan lages.
 */
public class RegisterImpl extends UnicastRemoteObject implements Register {
    public static final int ok = -1;
    public static final int ugyldigNr = -2;
    public static final int ikkeNokPåLager = -3;

    private ArrayList<Utstyr> registeret = new ArrayList<Utstyr>();

    public RegisterImpl() throws RemoteException {

    }

    public boolean regNyttUtstyr(int startNr, String startBetegnelse, String startLeverandør, int startPåLager, int startNedreGrense) throws RemoteException{
        if (finnUtstyrindeks(startNr) < 0) { // fins ikke fra før
            Utstyr nytt = new UtstyrImpl(startNr, startBetegnelse, startLeverandør, startPåLager, startNedreGrense);
            registeret.add(nytt);
            return true;
        }
        return false;
    }

    public int endreLagerbeholdning(int nr, int mengde) throws RemoteException{
        int indeks = finnUtstyrindeks(nr);
        if (indeks < 0) {
            return ugyldigNr;
        }
        else {
            if (!(registeret.get(indeks)).endreLagerbeholdning(mengde)) {
                return ikkeNokPåLager;
            } else return ok;
        }
    }

    public int finnUtstyrindeks(int nr) throws RemoteException{
        for (int i = 0; i < registeret.size(); i++) {
            int funnetNr = (registeret.get(i)).finnNr();
            if (funnetNr == nr) {
                return i;
            }
        }
        return -1;
    }

    public String lagBestillingsliste() throws RemoteException {
        String resultat = "\n\nBestillingsliste:\n";
        for (int i = 0; i < registeret.size(); i++) {
            Utstyr u = registeret.get(i);
            resultat += u.finnNr() + ", " + u.finnBetegnelse() + ": " + u.finnBestKvantum() + "\n";
        }
        return resultat;
    }

    public String lagDatabeskrivelse() throws RemoteException{
        String resultat = "Alle data:\n";
        for (int i = 0; i < registeret.size(); i++) {
            resultat += "Nr: " + registeret.get(i).finnNr() + ", " + "Betegnelse: " + registeret.get(i).finnBetegnelse() +
                    ", " + "Leverandør: " + registeret.get(i).finnLeverandør() + ", " + "På lager: " +
                    registeret.get(i).finnPåLager() + ", " + "Nedre grense: " + registeret.get(i).finnNedreGrense() + "\n";
        }
        return resultat;
    }
}
