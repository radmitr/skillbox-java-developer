package hibernate_many_to_many;

import hibernate_many_to_many.entities.Course;
import hibernate_many_to_many.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

// 10.9 Связь ManyToMany
public class Experiment {

    public static void main(String[] args) {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg3.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("--- 1 ---------------------------------------------------------");
        Course course = session.get(Course.class, 1);
        System.out.printf("Количество студентов на курсе \"%s\": %d человек\n",
                course.getName(), course.getStudents().size());

        System.out.println("--- 2 ---------------------------------------------------------");
        List<Student> studentList = course.getStudents();
        studentList.forEach(student -> System.out.println(student.getName()));

        System.out.println("--- 3 ---------------------------------------------------------");
        studentList.forEach(System.out::println);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
