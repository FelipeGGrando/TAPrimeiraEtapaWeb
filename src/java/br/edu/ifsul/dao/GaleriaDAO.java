/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Galeria;
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
public class GaleriaDAO<T> extends DAOGenerico<Galeria> implements Serializable {
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;
    private List<Galeria> listarTodos;

    public GaleriaDAO() {
        super();
        super.setClassePersistente(Galeria.class);
    }
    @Override
    public Galeria getObjectById(Integer id) throws Exception {
        Galeria obj = (Galeria) super.getEm().find(super.getClassePersistente(), id);
        //Inicializando a coleção de objetos para não ocorrer lazy exception
        obj.getFotos().size();
        return obj;
    }

}