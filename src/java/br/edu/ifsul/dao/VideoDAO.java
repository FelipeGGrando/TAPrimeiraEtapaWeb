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
public class VideoDAO<T> extends DAOGenerico<Video> implements Serializable {
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;
    private List<Video> listarTodos;

    public VideoDAO() {
        super();
        super.setClassePersistente(Video.class);
    }
    @Override
    public Video getObjectById(Integer id) throws Exception {
        Video obj = (Video) super.getEm().find(super.getClassePersistente(), id);
        //Inicializando a coleção de objetos para não ocorrer lazy exception
        obj.getComentarios().size();
        return obj;
    }

}