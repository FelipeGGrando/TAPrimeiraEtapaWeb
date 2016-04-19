/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoa;
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
public class PessoaDAO implements Serializable {
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;
    private List<Pessoa> listarTodos;

    public PessoaDAO() {
    }
    
    public void persist(Pessoa obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(Pessoa obj) throws Exception {
        em.merge(obj);
    }
    
    public void remove(Pessoa obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public Pessoa getObjectById(Integer id) throws Exception {
        return (Pessoa) em.find(Pessoa.class, id);
    }
    
    public List<Pessoa> getListarTodos() {
        return em.createQuery("from Pessoa").getResultList();
    }    

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setListarTodos(List<Pessoa> listarTodos) {
        this.listarTodos = listarTodos;
    }
}