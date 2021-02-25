package ttl.larku.app;

import ttl.larku.domain.Student;

import javax.persistence.*;
import java.util.List;

public class JPADemo {

    public static void main(String[] args) {
        JPADemo jpaDemo = new JPADemo();
        //jpaDemo.insertStudent();
        jpaDemo.updateStudent();
        jpaDemo.selectAll();
    }

    private EntityManagerFactory factory;
    public JPADemo() {
       factory = Persistence.createEntityManagerFactory("LarkUPU_SE");
    }

    public void selectAll() {
        EntityManager manager = factory.createEntityManager();

        TypedQuery<Student> query = manager.createQuery("Select s from Student s", Student.class);
        List<Student> students = query.getResultList();

        System.out.println("students");
        students.forEach(System.out::println);

    }

    public void insertStudent() {
        EntityManager manager = factory.createEntityManager();

        Student student = new Student("Rachel", "383 8939 9393", Student.Status.HIBERNATING);

        manager.getTransaction().begin();

        manager.persist(student);

        manager.getTransaction().commit();

    }

    public void updateStudent() {
        EntityManager manager = factory.createEntityManager();
        Student student = manager.find(Student.class, 15);
        manager.close();

        student.setName("Rackel");

        manager = factory.createEntityManager();

        manager.getTransaction().begin();


        manager.merge(student);
//        student.setName("Rackel");


        manager.getTransaction().commit();

    }


}



