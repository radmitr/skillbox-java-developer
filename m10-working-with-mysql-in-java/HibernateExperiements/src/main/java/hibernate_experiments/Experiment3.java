package hibernate_experiments;

import hibernate_experiments.entities.Course;
import hibernate_experiments.entities.CourseType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// 10.7 Hibernate изменение данных в базе
// INSERT
public class Experiment3 {

    public static void main(String[] args) {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Course course = new Course();
        course.setName("Новый курс");
        course.setType(CourseType.BUSINESS);
        course.setTeacherId(1);

        session.save(course);
//        session.persist(course);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
