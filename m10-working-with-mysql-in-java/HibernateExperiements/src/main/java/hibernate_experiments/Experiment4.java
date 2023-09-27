package hibernate_experiments;

import hibernate_experiments.entities.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// 10.7 Hibernate изменение данных в базе
// UPDATE
public class Experiment4 {

    public static void main(String[] args) {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, 49);

        course.setName("Совсем новый курс");
//        session.save(course); // deprecated
        session.persist(course);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
