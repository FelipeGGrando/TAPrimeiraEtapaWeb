/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.GaleriaDAO;
import br.edu.ifsul.modelo.Galeria;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Felipe
 */
@ManagedBean(name = "controleGaleria")
@SessionScoped
public class ControleGaleria implements Serializable {

    @EJB
    private GaleriaDAO dao;
    private Galeria objeto;

    public ControleGaleria() {

    }

    public String listar() {
        return "/privado/galeria/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Galeria();
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

    public GaleriaDAO getDao() {
        return dao;
    }

    public void setDao(GaleriaDAO dao) {
        this.dao = dao;
    }

    public Galeria getObjeto() {
        return objeto;
    }

    public void setObjeto(Galeria objeto) {
        this.objeto = objeto;
    }
}
