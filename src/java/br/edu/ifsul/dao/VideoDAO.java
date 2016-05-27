/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Video;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Felipe
 */

@Stateful
public class VideoDAO implements Serializable {
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;
    private List<Video> listarTodos;

    public VideoDAO() {
        
    }
    
    public void persist(Video obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(Video obj) throws Exception {
        em.merge(obj);
    }
    
    public void remove(Video obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public Video getObjectById(Integer id) throws Exception {
        return (Video) em.find(Video.class, id);
    }
    
    public List<Video> getListarTodos() {
        return em.createQuery("from Video").getResultList();
    }    

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setListarTodos(List<Video> listarTodos) {
        this.listarTodos = listarTodos;
    }
}