package hibernate_experiments2;

import hibernate_experiments2.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

// Task 10.3
public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            // 0 - get all courses from DB
            System.out.println("--- 0 ---------------------------------------------------------");
//            List<Course> courseList = session.createQuery("FROM Course", Course.class).list();
            List<Course> courseList = session.createQuery(
                    "FROM "+ Course.class.getSimpleName(), Course.class).list();
//            courseList.forEach(System.out::println);

            // 1 - get the first student from the first course
            System.out.println("--- 1 ---------------------------------------------------------");
            Student student = courseList.get(0).getStudents().get(0);
            System.out.println(student);

            // 2 - get all subscriptions from the first course
            System.out.println("--- 2 ---------------------------------------------------------");
            List<Subscription> subscriptions = courseList.get(0).getSubscriptions();
            subscriptions.forEach(System.out::println);

            // 3 - get a composite id of the first subscription
            System.out.println("--- 3 ---------------------------------------------------------");
            System.out.println(subscriptions.get(0).getId());

            // 4 - get a subscription by composite id SubscriptionKey(student_id, course_id)
            System.out.println("--- 4 ---------------------------------------------------------");
            Subscription subscription = session.get(Subscription.class, new SubscriptionKey(4, 3));
            System.out.println(subscription);

            // 5 - get a purchase by composite id PurchaseKey(student_name, course_name)
            System.out.println("--- 5 ---------------------------------------------------------");
            Purchase purchase = session.get(Purchase.class,
                    new PurchaseKey("Барсуков Виктор", "Java-разработчик"));
            System.out.println(purchase);

            // 6 - get all purchases from DB
//            System.out.println("--- 6 ---------------------------------------------------------");
//            List<Purchase> purchaseList = session.createQuery(
//                    "FROM Purchase" , Purchase.class).list();
//            purchaseList.forEach(System.out::println);

            // 7 - get all subscriptions from DB
//            System.out.println("--- 7 ---------------------------------------------------------");
//            List<Subscription> subscriptionList = session.createQuery(
//                    "FROM Subscription", Subscription.class).list();
//            subscriptionList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
