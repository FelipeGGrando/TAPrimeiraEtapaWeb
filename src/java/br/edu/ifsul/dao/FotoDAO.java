/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.FotoID;
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
public class FotoDAO<T> extends DAOGenerico<Foto> implements Serializable {
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;
    private List<Foto> listarTodos;

    public FotoDAO() {
        super();
        super.setClassePersistente(Foto.class);
    }
    public Foto getObjectById(FotoID id) throws Exception {
        FotoID idObj = new FotoID();
        idObj.setNumero(id.getNumero());
        idObj.setGaleria(super.getEm().find(Galeria.class, id.getGaleria().getId()));
        Foto obj = super.getEm().find(Foto.class, idObj);
        obj.getComentarios();
        obj.getGalerias();
        return obj;
    }

}