/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Felipe
 */
@ManagedBean(name = "controlePessoa")
@SessionScoped
public class ControlePessoa implements Serializable {

    @EJB
    private PessoaDAO dao;
    private Pessoa objeto;
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    private Pessoa amigo;
    private Boolean novoAmigo;

    public ControlePessoa() {

    }

    public String listar() {
        return "/privado/pessoa/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Pessoa();
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

    public void novoAmigo() {
        amigo = new Pessoa();
        novoAmigo = true;
    }

    public void alterarAmigo(int index) {
        amigo = objeto.getAmigos().get(index);
        novoAmigo = false;
    }

    public void salvarAmigo() {
         try {
            if (amigo.getId() == null) {
                dao.persist(amigo);
                dao.persist(objeto);
            } else {
                dao.persist(objeto);
                dao.merge(amigo);
            }
        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro ao persistir: " + e.getMessage());
        }
        if (novoAmigo) {
            objeto.adicionarAmigo(amigo);
            amigo.adicionarAmigo(objeto);
        }
        UtilMensagem.mensagemInformacao("Operação realizada com sucesso!");
    }

    public void removerAmigo(int index) {
        objeto.removerAmigo(index);
        UtilMensagem.mensagemInformacao("Amigo removido com sucesso!");
    }

    public PessoaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaDAO dao) {
        this.dao = dao;
    }

    public Pessoa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public Pessoa getAmigo() {
        return amigo;
    }

    public void setAmigo(Pessoa amigo) {
        this.amigo = amigo;
    }

    public Boolean getNovoAmigo() {
        return novoAmigo;
    }

    public void setNovoAmigo(Boolean novoAmigo) {
        this.novoAmigo = novoAmigo;
    }
}
