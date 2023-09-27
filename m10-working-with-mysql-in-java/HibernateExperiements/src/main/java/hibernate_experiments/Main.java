package hibernate_experiments;

import hibernate_experiments.entities.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Random;

// Task 10.2
public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        try {
            // get all courses from DB
            List<Course> courseList = session.createQuery("FROM Course", Course.class).list();

            // get random course from DB
            Course course = session.get(Course.class, new Random().nextInt(courseList.size()));
            if (course != null) {
                System.out.printf("id=%d - %s: %d чел.\n", course.getId(), course.getName(), course.getStudentsCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
            registry.close();
        }
    }
}
