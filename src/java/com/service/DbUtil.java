
package com.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DbUtil {
    public static EntityManager getEntityManager(String S){
        
        return Persistence.createEntityManagerFactory(S).createEntityManager();
    }
}
