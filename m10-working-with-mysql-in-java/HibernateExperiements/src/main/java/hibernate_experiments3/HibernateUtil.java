package hibernate_experiments3;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Create a SessionFactory to access the database
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration();
            sessionFactory = config.configure("hibernate.cfg7.xml").buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Error in creating SessionFactory object." + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
