/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.VideoDAO;
import br.edu.ifsul.modelo.Video;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
import javax.ejb.EJB;
 * @author Felipe
 */
@ManagedBean(name = "controleVideo")
@SessionScoped
public class ControleVideo implements Serializable {

    @EJB
    private VideoDAO dao;
    private Video objeto;

    public ControleVideo() {

    }

    public String listar() {
        return "/privado/video/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Video();
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            UtilMensagem.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro ao persistir: " + e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro ao recuperar objeto: " + e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMensagem.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro a remover objeto: " + e.getMessage());
        }
    }

    public VideoDAO getDao() {
        return dao;
    }

    public void setDao(VideoDAO dao) {
        this.dao = dao;
    }

    public Video getObjeto() {
        return objeto;
    }

    public void setObjeto(Video objeto) {
        this.objeto = objeto;
    }
}
