/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Comentario;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Felipe
 */

@Stateless
public class ComentarioDAO<T> extends DAOGenerico<Comentario> implements Serializable {

    public ComentarioDAO() {
        super();
        super.setClassePersistente(Comentario.class);
    }

}