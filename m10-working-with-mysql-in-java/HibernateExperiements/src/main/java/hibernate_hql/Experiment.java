package hibernate_hql;

import hibernate_hql.entities.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

// 10.14 HQL
public class Experiment {

    public static void main(String[] args) {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg6.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // 1
        System.out.println("--- 1 --------------------------------------------------------");
        String hql = "FROM " + Course.class.getSimpleName();
//        session.createQuery(hql); // deprecated
        List<Course> courseList = session.createQuery(hql, Course.class).getResultList();


        for (Course course : courseList) {
            System.out.println(course.getName());
        }

        System.out.println("---------------------");
        System.out.println("Количество: " + courseList.size());
        System.out.println();

        // 2 - price > 120000
        System.out.println("--- 2 --------------------------------------------------------");
        String hql2 = "FROM " + Course.class.getSimpleName() + " WHERE price > 120000";
//        String hql2 = "FROM Course WHERE price > 120000";
        List<Course> courseList2 = session.createQuery(hql2, Course.class).getResultList();

        for (Course course : courseList2) {
            System.out.println(course.getName());
        }

        System.out.println("---------------------");
        System.out.println("Количество: " + courseList2.size());

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
