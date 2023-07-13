package com.hasnain.HibernateTasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;




/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		Configuration con =new Configuration().configure().addAnnotatedClass(Person.class).addAnnotatedClass(Car.class);
		
		ServiceRegistry reg=new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		
		SessionFactory sf= con.buildSessionFactory(reg);
		
		
		
		
		
		Session session=sf.openSession();
		
		Transaction tx= session.beginTransaction();
		
		
		
		//Criteria Queries
		
		// 2. All persons whose name begins with a certain letter
//		Criteria criteria = session.createCriteria(Person.class);
//		criteria.add(Restrictions.like("name", "p%"));
//		List<Person> persons = criteria.list();
//		for (Person person : persons) {
//		    System.out.println("Person: " + person.getName());
//		    for (Car car : person.getCars()) {
//		        System.out.println("Car: " + car.getModelName() + ", Registration No: " + car.getRegistrationNo());
//		    }
//		}
		
		//3.	All persons who have more than 2 cars.
//		Criteria criteria = session.createCriteria(Person.class);
//		criteria.createAlias("cars", "c");
//		criteria.add(Restrictions.sizeGt("cars", 2));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		List<Person> persons = criteria.list();
//		for (Person person : persons) {
//		    System.out.println("Person: " + person.getName());
//		}

		//4  All Cars who belong to people whose name begins with a certain character.
//		Criteria criteria = session.createCriteria(Car.class);
//		criteria.createAlias("person", "p");
//		criteria.add(Restrictions.like("p.name", "p%"));
//		List<Car> cars = criteria.list();
//		for (Car car : cars) {
//	        System.out.println("Car: " + car.getModelName() + ", Registration No: " + car.getRegistrationNo());
//	    }
		
		//HQL Queries
		
		//4.	All Cars who belong to people whose name begins with a certain character.
//		String hql = "SELECT c FROM Car c JOIN c.person p WHERE p.name LIKE 'p%'";
//		Query query = session.createQuery(hql);
//		List<Car> cars = query.list();
//		for (Car car : cars) {
//	        System.out.println("Car: " + car.getModelName() + ", Registration No: " + car.getRegistrationNo());
//	    }
		
		//3.	All persons who have more than 2 cars.
//		String hql = "SELECT p FROM Person p WHERE SIZE(p.cars) > 2";
//		Query query = session.createQuery(hql);
//		List<Person> persons = query.list();
//		for (Person person : persons) {
//		    System.out.println("Person: " + person.getName());
//		}
		
		
		// 2. All persons whose name begins with a certain letter
//		String hql = "FROM Person p WHERE p.name LIKE 'p%'";
//		Query query = session.createQuery(hql);
//		List<Person> persons = query.list();
//		for (Person person : persons) {
//		    System.out.println("Person: " + person.getName());
//		}
		
		
//		// 1.All person and their cars
//		Query query = session.createQuery("from Person");
//		List<Person> persons = query.list() ;
//
//		for (Person person : persons) {
//		    System.out.println("Person: " + person.getName());
//		    for (Car car : person.getCars()) {
//		        System.out.println("Car: " + car.getModelName() + ", Registration No: " + car.getRegistrationNo());
//		    }
//		}
		
		
//input random records		
//		for(int i=0;i<5;i++) {
//		
//		Person p= createRandomPerson();
//		System.out.println(p.toString()); 
//		session.save(p);
//		System.out.println(p.toString()); 
//		for(Car c : p.getCars()) {
//		
//			session.save(c);
//			
//		}
//		}
		
		tx.commit();
    }
    
    public static Person createRandomPerson() {
        Random random = new Random();

        // Generate a random name
        String name = "Person" + UUID.randomUUID().toString().substring(0, 8);

        // Generate a random address
        String address = "Address" + UUID.randomUUID().toString().substring(0, 8);

        // Create a list of random cars
        List<Car> cars = new ArrayList<Car>();
        int carCount = random.nextInt(5) + 1; // Generate a random number of cars between 1 and 5
        for (int i = 0; i < carCount; i++) {
            Car car = createRandomCar(); // Set the person for the car
            cars.add(car);
        }

        // Create a new Person object with the random data
        Person person = new Person(name, address);
        person.setCars(cars);
        
        for (Car car : cars) {
            car.setPerson(person); // Set the person for the car
        }


        return person;
    }
    
    public static Car createRandomCar() {
        Random random = new Random();

        // Generate a random model name
        String modelName = "Model" + UUID.randomUUID().toString().substring(0, 8);

        // Generate a random registration number
        String registrationNo = "Reg" + random.nextInt(10000);

        // Create a new Car object with the random data
        return new Car(modelName, registrationNo);
    }
}
