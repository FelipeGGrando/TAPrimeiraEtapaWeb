/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Foto;
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
public class FotoDAO<T> extends DAOGenerico<Foto> implements Serializable {
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;
    private List<Foto> listarTodos;

    public FotoDAO() {
        super();
        super.setClassePersistente(Foto.class);
    }
    @Override
    public Foto getObjectById(Integer id) throws Exception {
        Foto obj = (Foto) super.getEm().find(super.getClassePersistente(), id);
        //Inicializando a coleção de objetos para não ocorrer lazy exception
        obj.getGalerias().size();
        obj.getComentarios().size();
        return obj;
    }

}