package hibernate_experiments3;

import hibernate_experiments3.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

// Task 10.4
public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            // fill LinkedPurchaseList
            String allPurchasesHQL = "FROM " + Purchase.class.getSimpleName();
            List<Purchase> purchases = session.createQuery(allPurchasesHQL, Purchase.class).getResultList();
            session.beginTransaction();
            fillLinkedPurchaseList(purchases, session);
            session.getTransaction().commit();

            // get all linkedPurchases
            List<LinkedPurchase> linkedPurchases = getAllLinkedPurchases(session);
            linkedPurchases.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static void fillLinkedPurchaseList(List<Purchase> purchases, Session session) {
        for (Purchase p : purchases) {
            int studentId = p.getStudent().getId();
            int courseId = p.getCourse().getId();
            LinkedPurchase linkedPurchase = new LinkedPurchase(
                    new LinkedPurchaseKey(studentId, courseId),
                    p.getStudent(),
                    p.getCourse(),
                    p.getStudent().getName(),
                    p.getCourse().getName(),
                    p.getPrice(),
                    p.getSubscriptionDate()
            );
            session.persist(linkedPurchase);
            printPurchase(p);
        }
    }

    private static List<LinkedPurchase> getAllLinkedPurchases(Session session) {
        String allLinkedPurchasesHQL = "FROM " + LinkedPurchase.class.getSimpleName();
        Query<LinkedPurchase> query = session.createQuery(allLinkedPurchasesHQL, LinkedPurchase.class);
        List<LinkedPurchase> linkedPurchases = query.getResultList();

        return linkedPurchases;
    }

    private static void printPurchase(Purchase purchase) {
        System.out.printf("""
                        student_id:         %s
                        course_id:          %s
                        student_name:       %s
                        course_name:        %s
                        price:              %s
                        subscription_date:  %s
                        -----------------------------------------
                        """,
                purchase.getStudent().getId(),
                purchase.getCourse().getId(),
                purchase.getStudent().getName(),
                purchase.getCourse().getName(),
                purchase.getPrice(),
                purchase.getSubscriptionDate()
        );
    }
}
