/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.FotoID;
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
public class FotoIDDAO<T> extends DAOGenerico<FotoID> implements Serializable {
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;
    private List<FotoID> listarTodos;

    public FotoIDDAO() {
        super();
        super.setClassePersistente(FotoID.class);
    }
    @Override
    public FotoID getObjectById(Integer id) throws Exception {
        FotoID obj = (FotoID) super.getEm().find(super.getClassePersistente(), id);     
        return obj;
    }

}