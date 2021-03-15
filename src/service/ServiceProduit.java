package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.Produit;
import util.HibernateUtil;

public class ServiceProduit {

	public void ajoutProduit(Produit p) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); Date date =
		 * sdf.parse("2020.10.11"); Produit p = new Produit("Voiture", date);
		 */
		session.save(p);
		session.getTransaction().commit();
		session.close();
	}
}
