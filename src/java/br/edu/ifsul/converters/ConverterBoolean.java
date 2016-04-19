package br.edu.ifsul.converters;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converterBoolean")
public class ConverterBoolean implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            Boolean c = true;
            if (string.toUpperCase().equals("SIM")) {
                c = true;
            } else if (string.toUpperCase().equals("NÃO")) {
                c = false;
            }
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Boolean b = (Boolean) o;
        if (b.equals(true)) {
            return "Sim";
        } else if (b.equals(false)){
            return "Não";
        }
        return null;
    }

}
