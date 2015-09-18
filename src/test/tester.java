/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Student;
import entity.Studypoint;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jeanette
 */
public class tester
{

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemoPU");
        EntityManager em = emf.createEntityManager();
        
        
        //find alle studerende
        System.out.println(em.createNamedQuery("Student.findAll").getResultList());
        
        //find alle studerende med navnet Jan
        System.out.println(em.createNamedQuery("Student.findByFirstname").setParameter("firstname", "jan").getResultList());
        
        //find all studerende med efternavnet olsen
        System.out.println(em.createNamedQuery("Student.findByLastname").setParameter("lastname", "olsen").getResultList());
        
        //Find the total sum of study point scores, for a student given the student id.
        System.out.println(em.createQuery("SELECT SUM (s.score) FROM Studypoint s WHERE s.id = :id").setParameter("id", 2).getResultList());
        
        //Find the total sum of studypoint scores, given to all students.
        System.out.println(em.createQuery("SELECT SUM (s.score) FROM Studypoint s").getResultList());
        
        //Find those students that has the greatest total of studypoint scores
//        System.out.println(em.createNamedQuery("").setParameter().getResultList());
        
        Studypoint sp = new Studypoint();

        sp.setScore(4);

        sp.setDescription("opgaver");

        sp.setMaxval(5);

        sp.setStudent(em.find(Student.class, 1));

        addStudyPoint(sp);
        
        
    }
    
    public Student createStudent()

    {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemoPU");

        EntityManager em = emf.createEntityManager();

       

        Student s = new Student();

        s.setFirstname("Marta");

        s.setLastname("Miszyk");

        em.getTransaction().begin();

        em.persist(s);

        em.getTransaction().commit();

        return em.find(Student.class, s.getId());

       

    }

    public static void addStudyPoint(Studypoint sp)

    {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemoPU");

        EntityManager em = emf.createEntityManager();

       

            em.getTransaction().begin();

            em.persist(sp);

            em.getTransaction().commit();

            System.out.println("point tilføjet");

       

    }

}
