/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ComentarioDAO;
import br.edu.ifsul.dao.FotoDAO;
import br.edu.ifsul.modelo.Comentario;
import br.edu.ifsul.modelo.Foto;
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
@ManagedBean(name = "controleFoto")
@SessionScoped
public class ControleFoto implements Serializable {

    @EJB
    private FotoDAO dao;
    private Foto objeto;
    @EJB
    private ComentarioDAO<Comentario> daoComentario;
    private Boolean novoComentario;
    private Comentario comentario;

    public ControleFoto() {

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
        objeto.adicionarComentario(comentario);
        UtilMensagem.mensagemInformacao("Operação realizada com sucesso!");
    }

    public void removerComentario(int index) {
        objeto.removerComentario(index);
        UtilMensagem.mensagemInformacao("Comentário removido com sucesso!");
    }

    public String listar() {
        return "/privado/video/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Foto();
    }

    public void salvar() {
        try {
            if (objeto.getFotoId()== null) {
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

    public FotoDAO getDao() {
        return dao;
    }

    public void setDao(FotoDAO dao) {
        this.dao = dao;
    }

    public Foto getObjeto() {
        return objeto;
    }

    public void setObjeto(Foto objeto) {
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
