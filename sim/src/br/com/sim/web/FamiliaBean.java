/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import br.com.sim.model.bean.Familia;
import br.com.sim.rn.FamiliaRn;
import br.com.sim.util.Messages;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

/**
 *
 * @author Rodrigo Otacilio
 */
@ManagedBean(name = "familiaBean")
@ViewScoped
public class FamiliaBean {
    
    private Familia familia = new Familia();
    private List<Familia> familias;
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            String idFamilia = context.getExternalContext().getRequestParameterMap().get("fami_id");
            if (idFamilia != null) {
                Integer id = Integer.valueOf(idFamilia);
                familia = new FamiliaRn().carregarPorId(id);
                return;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public List<Familia> getFamilias() {
        if (familias == null) {
            familias = new FamiliaRn().listar();
        }
        return familias;
    }

    public String salvarFamilia() {
        FamiliaRn familiaRn = new FamiliaRn();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                Familia familiaAux = familiaRn.carregarPorCodigo(familia.getCodigo());
                if (familiaAux != null && (familia.getId() == null || familia.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {
            }
        System.out.println(familia.toString());
            familiaRn.salvar(familia);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            familia = new Familia();
            familias = null;
            return "/restrito/familias/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String filtrarFamilias() {
        FamiliaRn familiaRn = new FamiliaRn();
        familias = familiaRn.filtrarFamilias(familia.getCodigo());
        return null;
    }
    
    public String limparCampos() {
        familia = new Familia();
        familias = null;
        return null;
    }
    
    public String excluirAjax() {
        FacesContext context = FacesContext.getCurrentInstance();
        FamiliaRn familiaRn = new FamiliaRn();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("fami_id"));
        familia = familiaRn.carregarPorId(id);
        familiaRn.excluir(familia);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        familia = new Familia();
        familias = null;
        return null;
    }
    
    public String excluir() {
        FamiliaRn familiaRn = new FamiliaRn();
        FacesContext context = FacesContext.getCurrentInstance();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("fami_id"));
        familia = familiaRn.carregarPorId(id);
        familiaRn.excluir(familia);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        familia = new Familia();
        familias = null;
        return "/restrito/familias/index";
    }
}
