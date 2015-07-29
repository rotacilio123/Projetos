/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web.converter;

import br.com.sim.model.bean.Item;
import br.com.sim.model.bean.Local;
import br.com.sim.model.dao.LocalDao;
import br.com.sim.rn.ItemRn;
import br.com.sim.rn.LocalRn;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author RodrigoOtacilio
 */
@FacesConverter(value = "itemConverter")
public class ItemConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length() > 0) {
            Integer id  = Integer.valueOf(string);
            return new ItemRn().carregarPorId(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && o instanceof Item) {
            Item item = (Item) o;
            return String.valueOf(item.getId());
        }
        return "";
    }
    
}
