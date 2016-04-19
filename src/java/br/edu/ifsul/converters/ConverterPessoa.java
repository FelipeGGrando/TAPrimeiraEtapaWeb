package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Felipe
 */

@FacesConverter(value = "converterPessoa")
public class ConverterPessoa implements Serializable, Converter{
    
    @PersistenceContext(unitName = "TAPrimeiraEtapaWebPU")
    private EntityManager em;    

    // converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Pessoa.class, Integer.parseInt(string));
    }

    // converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Pessoa p = (Pessoa) o;
        return p.getId().toString();
    }

}


