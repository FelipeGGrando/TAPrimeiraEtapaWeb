/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Galeria;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Felipe
 */

@Stateless
public class GaleriaDAO implements Serializable {
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;
    private List<Galeria> listarTodos;

    public GaleriaDAO() {
        
    }
    
    public void persist(Galeria obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(Galeria obj) throws Exception {
        em.merge(obj);
    }
    
    public void remove(Galeria obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public Galeria getObjectById(Integer id) throws Exception {
        return (Galeria) em.find(Galeria.class, id);
    }
    
    public List<Galeria> getListarTodos() {
        return em.createQuery("from Galeria").getResultList();
    }    

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setListarTodos(List<Galeria> listarTodos) {
        this.listarTodos = listarTodos;
    }
}