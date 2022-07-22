import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MainClass {

    public static void main(String[] args) {
        Student student = new Student("John Johnathan", 3,
            "Computer Science", LocalDate.of(2000,1,1), Gender.MALE);

        // create configurations from hibernate.cfg.xml
        Configuration configuration = new Configuration()
            .configure() // you can pass the config file path
            .addAnnotatedClass(Student.class); // add the entity classes

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties())
            .build(); // registers the services needed for hibernate with the configs

        SessionFactory sf = configuration.buildSessionFactory(registry);
        Session session = sf.openSession(); // needs to be closed / try-with-resources

        Transaction tx = session.beginTransaction();
        session.persist(student);
        tx.commit();

        sf.close();
    }
}
