package exercice_3;

import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//met le fichier config dans ton fichier src sinon ca foire.

//Bon, j'ai fait les classes et le config est bonne a priori(enfin change juste les trucs pas générique). J'ai ausi refait les db en ajoutant les trucs facultatifs
//par contre ici je galère j'ai pris l'exemple si dessous mais je débug la fonction depuis 3h(la il me demande une librairies appahe...) donc je pense que t'a
//interet a laisser tomber celle ci pour en trouver une qui marche. J'ai pas trop touché au fichier .xml il se peut que tu doive y toucher
//pour que ca concorde bien avec la db.

//C'est maintenant que ca devient drole
//http://www.tutorialspoint.com/hibernate/hibernate_examples.html
//J'ai utilisé ca pour cette partie

//Si tu veux test, tu dois foutre toutes les jar de ca http://sourceforge.net/projects/hibernate/files/hibernate4/4.1.7.Final/ en library
//! Ca marche pas.


public class Scenario {
	private static SessionFactory factory; 
	public static void main(String[] args) {
		try{
			Configuration cfg=new Configuration();  
			cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
			factory=cfg.buildSessionFactory();  
	    }catch (Throwable ex) { 
	    	System.err.println("Failed to create sessionFactory object." + ex);
	    	throw new ExceptionInInitializerError(ex); 
	    }
		Session session=factory.openSession();  
		Transaction t=session.beginTransaction();  
		Scenario sen = new Scenario();
		sen.getId();
	}
	   
	public long getId(){
	Session session = factory.openSession();
	Transaction tx = null;
	try{
		List<Client> client = session.createQuery("FROM Client").list();
		for(Iterator<Client> iterator = client.iterator(); iterator.hasNext();){
			Client c = iterator.next();
			System.out.println(c.getId());
		}
		tx.commit();
	}catch (HibernateException e) {
		if (tx!=null) tx.rollback();
        e.printStackTrace(); 
    }finally {
    	session.close(); 
    }
	return 0;
	   
	}
}
