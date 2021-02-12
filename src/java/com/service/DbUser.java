package com.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.model.Bhuser;
import javax.persistence.EntityTransaction;

public class DbUser {
    public static Bhuser getUser(long userID){
        EntityManager em = DbUtil.getEntityManager("helloworldPU");
        
        Bhuser user = em.find(Bhuser.class,userID);
        
        return user;
    }
    public static void insert(Bhuser user){
        EntityManager em = DbUtil.getEntityManager("helloworldPU");
        
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
            em.persist(user);
            et.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
        }
        finally{
            em.close();
        }
    }
    public static String getGravatarUrl(String email, Integer size){
        
        StringBuilder url = new StringBuilder();
        url.append("http://www.gravatar.com/avatar/");
        url.append(MD5Util.md5Hex(email.getBytes()));
        url.append("?s=").append(size.toString());
        
        return  url.toString();
    }
    public static void update(Bhuser user){
        EntityManager em = DbUtil.getEntityManager("helloworldPU");
        
        EntityTransaction et = em.getTransaction();
        
        try{
            et.begin();
            em.merge(em.merge(user));
            et.commit();
        }catch(Exception ex){
            System.out.println(ex);
            et.rollback();
        }
        finally{
            em.close();
        }
    }
    public static void delete(Bhuser user){
        EntityManager em = DbUtil.getEntityManager("helloworldPU");
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
            em.remove(user);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        }
        finally{
            em.close();
        }
    }
    public static Bhuser getUserByEmail(String email){
        EntityManager em = DbUtil.getEntityManager("helloworldPU");
        
        String query = "Select u from Bhuser u" + "where u.useremail = :useremail";
        
        TypedQuery<Bhuser> q = em.createQuery(query,Bhuser.class);
        q.setParameter("useremail", email);
        
        Bhuser user = null;
        
        try {
            System.out.println("Printing out single user");
            
            user = q.getSingleResult();
            System.out.println(user.getUsername());
        } catch (NoResultException nre) {
            System.out.println(nre);
        }
        finally{
            em.close();
        }
        
        return user;
    }
    public static boolean isValidUser(String userEmail,String userPassword){
        EntityManager em = DbUtil.getEntityManager("helloworldPU");
        
        String query = "Select count(b.bhuserid) from Bhuser b where "+
                "b.useremail = :useremail and "+
                "b.userpassword = :userpassword";
        TypedQuery<Long> q = em.createQuery(query,Long.class);
        
        boolean result = false;
        q.setParameter("useremail",userEmail);
        q.setParameter("userpassword", userPassword);
        
        try {
            long userID = q.getSingleResult();
            if(userID>0) result = true;
        } catch (Exception e) {
            result = false;
        }
        finally{
            em.close();
        }
        
        return  result;
    }
}
