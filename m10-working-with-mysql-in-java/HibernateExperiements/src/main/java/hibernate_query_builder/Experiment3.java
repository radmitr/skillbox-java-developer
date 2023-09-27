package hibernate_query_builder;

import hibernate_query_builder.entities.Course;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

// 10.13 Where и OrderBy
public class Experiment3 {

    public static void main(String[] args) {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg5.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root).where(builder.greaterThan(root.get("price"), 100_000))
                .orderBy(builder.desc(root.get("price"))); // WHERE, ORDER BY
        // 1
//        List<Course> courseList = session.createQuery(query).getResultList();
        // 2 - setMaxResults(5)
        List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();

        for (Course course : courseList) {
            System.out.println(course.getName() + " - " + course.getPrice());
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
