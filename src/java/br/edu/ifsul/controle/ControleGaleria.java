/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FotoDAO;
import br.edu.ifsul.dao.GaleriaDAO;
import br.edu.ifsul.modelo.Foto;
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
    @EJB
    private FotoDAO<Foto> daoFoto;
    private Boolean novaFoto;
    private Foto foto;

    public ControleGaleria() {

    }
    
        public void novaFoto() {
        foto = new Foto();
        novaFoto = true;
    }

    public void alterarFoto(int index) {
        foto = objeto.getFotos().get(index);
        novaFoto = false;
    }

    public void salvarFoto() {
        objeto.adicionarFoto(foto);
        UtilMensagem.mensagemInformacao("Operação realizada com sucesso!");
    }

    public void removerFoto(int index) {
        objeto.removerFoto(index);
        UtilMensagem.mensagemInformacao("Endereço removido com sucesso!");
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

    public FotoDAO<Foto> getDaoFoto() {
        return daoFoto;
    }

    public void setDaoFoto(FotoDAO<Foto> daoFoto) {
        this.daoFoto = daoFoto;
    }

    public Boolean getNovaFoto() {
        return novaFoto;
    }

    public void setNovaFoto(Boolean novaFoto) {
        this.novaFoto = novaFoto;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
}
