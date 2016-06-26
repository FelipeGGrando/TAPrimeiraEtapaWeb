/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.PostagemDAO;
import br.edu.ifsul.dao.VideoDAO;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Postagem;
import br.edu.ifsul.modelo.Video;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Felipe
 */
@ManagedBean(name = "controleRelatorios")
@SessionScoped
public class ControleRelatorios implements Serializable {

    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    @EJB
    private VideoDAO<Video> daoVideo;
    @EJB
    private PostagemDAO<Postagem> daoPostagem;

    public ControleRelatorios() {

    }

    public void imprimeRelatorioPessoas() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioPessoas", parametros, daoPessoa.getListaTodos());
    }

    public void imprimeRelatorioPostagens() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioPostagens", parametros, daoPostagem.getListaTodos());
    }

    public void imprimeRelatorioVideos() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioVideos", parametros, daoVideo.getListaTodos());
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public PostagemDAO<Postagem> getDaoPostagem() {
        return daoPostagem;
    }

    public void setDaoPostagem(PostagemDAO<Postagem> daoPostagem) {
        this.daoPostagem = daoPostagem;
    }

    public VideoDAO<Video> getDaoVideo() {
        return daoVideo;
    }

    public void setDaoVideo(VideoDAO<Video> daoVideo) {
        this.daoVideo = daoVideo;
    }

}
