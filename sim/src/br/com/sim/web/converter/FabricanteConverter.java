/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web.converter;

import br.com.sim.model.bean.Fabricante;
import br.com.sim.model.bean.Familia;
import br.com.sim.rn.FabricanteRn;
import br.com.sim.rn.FamiliaRn;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author RodrigoOtacilio
 */
@FacesConverter(value = "fabricanteConverter")
public class FabricanteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length() > 0) {
            Integer id  = Integer.valueOf(string);
            return new FabricanteRn().carregarPorId(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && o instanceof Fabricante) {
            Fabricante fabricante = (Fabricante) o;
            return String.valueOf(fabricante.getId());
        }
        return "";
    }
    
}
