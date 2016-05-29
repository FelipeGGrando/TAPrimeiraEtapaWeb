/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Postagem;
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
public class PostagemDAO<T> extends DAOGenerico<Postagem> implements Serializable {
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;
    private List<Postagem> listarTodos;

    public PostagemDAO() {
        super();
        super.setClassePersistente(Postagem.class);
    }
    @Override
    public Postagem getObjectById(Integer id) throws Exception {
        Postagem obj = (Postagem) super.getEm().find(super.getClassePersistente(), id);
        //Inicializando a coleção de objetos para não ocorrer lazy exception
        obj.getComentarios().size();
        return obj;
    }

}