/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;
import br.edu.ifsul.dao.ComentarioDAO;
import br.edu.ifsul.dao.FotoDAO;
import br.edu.ifsul.dao.PostagemDAO;
import br.edu.ifsul.dao.VideoDAO;
import br.edu.ifsul.modelo.Comentario;
import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.Postagem;
import br.edu.ifsul.modelo.Video;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Felipe
 */
@ManagedBean(name = "controlePostagem")
@SessionScoped
public class ControlePostagem implements Serializable {
    
    @EJB
    private PostagemDAO<Postagem> dao;
    private Postagem objeto;
    @EJB
    private VideoDAO<Video> daoVideo;
    @EJB
    private FotoDAO<Foto> daoFoto;
    @EJB
    private ComentarioDAO<Comentario> daoComentario;
    private Boolean novoComentario;
    private Comentario comentario;
    
    public ControlePostagem() {        
        
    }
    
    public String listar(){
        return "/privado/postagem/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Postagem();      
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
                comentario.setPostagem(objeto);
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
    
    public void editar(Integer id){
        try {
            objeto = dao.getObjectById(id);            
        } catch (Exception e){
            UtilMensagem.mensagemErro("Erro ao recuperar obejto: "+e.getMessage());            
        }
    }
    
    public void remover(Integer id){
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMensagem.mensagemInformacao("Objeto removido com sucesso!");            
        } catch(Exception e){
            UtilMensagem.mensagemErro("Erro a remover objeto: "+e.getMessage());
        }
    }

    public PostagemDAO<Postagem> getDao() {
        return dao;
    }

    public void setDao(PostagemDAO<Postagem> dao) {
        this.dao = dao;
    }

    public Postagem getObjeto() {
        return objeto;
    }

    public void setObjeto(Postagem objeto) {
        this.objeto = objeto;
    }

    public VideoDAO<Video> getDaoVideo() {
        return daoVideo;
    }

    public void setDaoVideo(VideoDAO<Video> daoVideo) {
        this.daoVideo = daoVideo;
    }

    public FotoDAO<Foto> getDaoFoto() {
        return daoFoto;
    }

    public void setDaoFoto(FotoDAO<Foto> daoFoto) {
        this.daoFoto = daoFoto;
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