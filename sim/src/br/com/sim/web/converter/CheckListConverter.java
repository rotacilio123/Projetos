/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web.converter;

import br.com.sim.model.bean.CheckList;
import br.com.sim.rn.CheckListRn;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author RodrigoOtacilio
 */
@FacesConverter(value = "checkListConverter")
public class CheckListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            if (string != null && string.trim().length() > 0) {
                Integer id  = Integer.valueOf(string);
                return new CheckListRn().carregarPorId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try {
            if (o != null && o instanceof CheckList) {
                CheckList checkList = (CheckList) o;
                return String.valueOf(checkList.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
