/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web.converter;

import br.com.sim.model.bean.Posto;
import br.com.sim.rn.PostoRn;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author RodrigoOtacilio
 */
@FacesConverter(value = "postoConverter", forClass = Posto.class)
public class PostoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length() > 0) {
            Integer id  = Integer.valueOf(string);
            return new PostoRn().carregarPorId(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && o instanceof Posto) {
            Posto posto = (Posto) o;
            return String.valueOf(posto.getId());
        }
        return "";
    }
    
}
