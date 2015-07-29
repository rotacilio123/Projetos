/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web.converter;

import br.com.sim.model.bean.Usuario;
import br.com.sim.rn.UsuarioRn;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author RodrigoOtacilio
 */
@FacesConverter(value = "usuarioConverter")
public class UsuarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length() > 0) {
            Integer id = Integer.valueOf(string);
            return new UsuarioRn().carregarPorId(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && o instanceof Usuario) {
            Usuario u = (Usuario) o;
            return String.valueOf(u.getId());
        }
        return "";
    }
    
}
