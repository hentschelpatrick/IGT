package de.igt.transactions;

import de.igt.models.Customer;
import de.igt.models.Status;
import de.igt.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;

public class NestedTransactions {
    private static Logger logger = Logger.getRootLogger();
    static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    public static void main(String[] args) {
        try {
            Customer customer = new Customer();
            customer.setEMAIL("test@test.de");
            customer.setFIRST_NAME("Test");
            customer.setLAST_NAME("asod");
            customer.setADDRESS("asdasdo str. 79");
            customer.setCOUNTRY("DE");
            customer.setAGE(10);
            customer.setMILES_FLOWN_YEAR(12314241203L);
            customer.setTOTAL_MILES_FLOWN(123131L);
            customer.setStatus(Status.NONE);

            logger.info("\n\nTA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            //Keine geschachtelten Transaktionen möglich!
            tm.begin();

            long queryStart = System.currentTimeMillis();

            em.persist(customer);
            em.flush();
            em.close();
            tm.commit();
            long queryEnd = System.currentTimeMillis();

            logger.info("\n\nCreation took ".concat(String.valueOf(queryEnd - queryStart)).concat("ms.\n\n"));

        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }
}
