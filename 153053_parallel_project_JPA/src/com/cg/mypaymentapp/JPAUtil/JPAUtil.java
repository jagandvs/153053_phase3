package com.cg.mypaymentapp.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	public static EntityManagerFactory entitymanagerfactory;
	public static EntityManager entitymanager;
	
	static {
		entitymanagerfactory = Persistence.createEntityManagerFactory("JPA-PU");
	}
	
	public static EntityManager getEntityManager() {
		if(entitymanager==null || !entitymanager.isOpen()) {
			entitymanager = entitymanagerfactory.createEntityManager();
		}
		return entitymanager;
	}
	

}
