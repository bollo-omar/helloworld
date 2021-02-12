package com.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import com.model.Bhpost;
import java.util.Arrays;
import java.util.List;

public class DBPost {

    public static void insert(Bhpost post) {
        EntityManager em = DbUtil.getEntityManager("helloworldPU");
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            em.persist(post);
            et.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(Bhpost post) {
        EntityManager em = DbUtil.getEntityManager("helloworldPU");
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            em.merge(post);
            et.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Bhpost post) {
        EntityManager em = DbUtil.getEntityManager("helloworldPU");
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            em.remove(em.merge(post));
            et.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static List<Bhpost> posts() {
        EntityManager em = DbUtil.getEntityManager("helloworldPU");

        String query = "Selectb from Bhpost b";

        List<Bhpost> _posts = null;

        try {
            TypedQuery<Bhpost> q = em.createQuery(query, Bhpost.class);
            _posts = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return _posts;
    }

    public static List<Bhpost> postOfUser(long userid) {
        EntityManager em = DbUtil.getEntityManager("helloworldPU");

        String query = "Select b from Bhpost b where b.Bhuser.userid = :userid";

        List<Bhpost> _posts = null;

        try {
            TypedQuery<Bhpost> q = em.createQuery(query, Bhpost.class);
            q.setParameter("userid", userid);
            _posts = q.getResultList();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return _posts;
    }
    public static List<Bhpost> postOfUser(String email) {
        EntityManager em = DbUtil.getEntityManager("helloworldPU");

        String query = "Select b from Bhpost b where b.Bhuser.useremail = :useremail";

        List<Bhpost> _posts = null;

        try {
            TypedQuery<Bhpost> q = em.createQuery(query, Bhpost.class);
            q.setParameter("useremail", email);
            _posts = q.getResultList();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return _posts;
    }
    public static List<Bhpost> searchPosts (String search){
        EntityManager em = DbUtil.getEntityManager("helloworldPU");
        
         String query = "select b from Bhpost b where b.posttext like :search";

        List<Bhpost> searchPosts = null;

        try {
            TypedQuery<Bhpost> q = em.createQuery(query, Bhpost.class);
            q.setParameter("search", search);
            searchPosts = q.getResultList();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return searchPosts;
        
        
    }
}
