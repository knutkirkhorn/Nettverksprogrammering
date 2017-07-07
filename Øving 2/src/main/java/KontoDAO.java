import javax.persistence.*;
import javax.swing.*;
import javax.transaction.Transaction;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Knut on 01.03.2017.
 */
public class KontoDAO {

    private EntityManagerFactory entityManagerFactory;

    public KontoDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    private void closeEntityManager(EntityManager entityManager) {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    public void opprettKonto(Konto konto) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(konto);
            em.getTransaction().commit();
        } finally {
            closeEntityManager(em);
        }
    }

    public ArrayList<Konto> finnKontoerMedStorreSaldo(double saldo) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("FROM Konto WHERE saldo >= :saldo");
            query.setParameter("saldo", saldo);
            return new ArrayList<Konto>(query.getResultList());
        } finally {
            closeEntityManager(em);
        }
    }

    public void endreEier(Konto konto, String eier) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Konto nyKonto = konto;
            nyKonto.setEier(eier);
            //Konto konto1 = em.merge(konto);
            em.merge(nyKonto);
            em.getTransaction().commit();
        } finally {
            closeEntityManager(em);
        }
    }

    public void overforPenger(int trekkKontoNr, int leggTilKontoNr, double sum) {
        EntityManager em = getEntityManager();
        boolean skjedd = false;

        while(!skjedd) {
            try {
                Konto trekkKonto = em.find(Konto.class, trekkKontoNr);
                Konto leggTilKonto = em.find(Konto.class, leggTilKontoNr);

                em.getTransaction().begin();
                trekkKonto.trekk(sum);
                double nySaldo = leggTilKonto.getSaldo() - sum;
                leggTilKonto.setSaldo(nySaldo);
                em.merge(trekkKonto);
                em.merge(leggTilKonto);
                em.getTransaction().commit();
                skjedd = true;
            } catch (OptimisticLockException optiole) {
                skjedd = false;
            } finally {
                closeEntityManager(em);
            }
        }
    }

    public Konto finnEier(int kontonummer) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Konto.class, kontonummer);
        } finally {
            closeEntityManager(em);
        }
    }
}
