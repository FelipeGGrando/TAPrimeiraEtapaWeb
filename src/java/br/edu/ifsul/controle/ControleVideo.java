/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ComentarioDAO;
import br.edu.ifsul.dao.VideoDAO;
import br.edu.ifsul.modelo.Comentario;
import br.edu.ifsul.modelo.Video;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * import javax.ejb.EJB;
 *
 * @author Felipe
 */
@ManagedBean(name = "controleVideo")
@SessionScoped
public class ControleVideo implements Serializable {

    @EJB
    private VideoDAO<Video> dao;
    private Video objeto;
    @EJB
    private ComentarioDAO<Comentario> daoComentario;
    private Boolean novoComentario;
    private Comentario comentario;

    public ControleVideo() {

    }

    public void novoComentario() {
        comentario = new Comentario();
        novoComentario = true;
    }

    public void alterarComentario(int index) {
        comentario = objeto.getComentarios().get(index);
        novoComentario = false;
    }

    public void salvarComentario() {
        try {
            if (novoComentario) {
                comentario.setVideo(objeto);
                objeto.adicionarComentario(comentario);
            }
            UtilMensagem.mensagemInformacao("Operação realizada com sucesso!");
        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro ao persistir: " + e.getMessage());
        }
    }

    public void removerComentario(int index) {
        objeto.removerComentario(index);
        UtilMensagem.mensagemInformacao("Comentário removido com sucesso!");
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

    public ComentarioDAO<Comentario> getDaoComentario() {
        return daoComentario;
    }

    public void setDaoComentario(ComentarioDAO<Comentario> daoComentario) {
        this.daoComentario = daoComentario;
    }

    public Boolean getNovoComentario() {
        return novoComentario;
    }

    public void setNovoComentario(Boolean novoComentario) {
        this.novoComentario = novoComentario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}
