/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoa;
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
public class PessoaDAO<T> extends DAOGenerico<Pessoa> implements Serializable {
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;
    private List<Pessoa> listarTodos;

    public PessoaDAO() {
        super();
        super.setClassePersistente(Pessoa.class);
    }
    @Override
    public Pessoa getObjectById(Integer id) throws Exception {
        Pessoa obj = (Pessoa) super.getEm().find(super.getClassePersistente(), id);
        //Inicializando a coleção de objetos para não ocorrer lazy exception
        obj.getAmigos().size();
        return obj;
    }

}