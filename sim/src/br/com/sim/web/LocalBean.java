/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import br.com.sim.model.bean.Empresa;
import br.com.sim.model.bean.Local;
import br.com.sim.rn.EmpresaRn;
import br.com.sim.rn.LocalRn;
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
@ManagedBean(name = "localBean")
@ViewScoped
public class LocalBean {
    
    private Local local = new Local();
    private List<Local> locais;
    private List<Empresa> empresas;
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        LocalRn localRn = new LocalRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("loca_id"));
            local = localRn.carregarPorId(id);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public List<Local> getLocais() {
        if (locais == null) {
            locais = new LocalRn().listar();
        }
        return locais;
    }

    public List<Empresa> getEmpresas() {
        if (empresas == null) {
            empresas = new EmpresaRn().listar();
        }
        return empresas;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Local getLocal() {
        return local;
    }
    
    public String salvarLocal() {
        FacesContext context = FacesContext.getCurrentInstance();
        LocalRn localRn = new LocalRn();
        try {
            Local localAux = null;
            try {
                localAux = localRn.carregarPorCodigoEEMpresa(local.getCodigo(), local.getEmpresa());
                if (localAux != null && (local.getId() == null || local.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {}
            localRn.salvar(local);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            return "/restrito/locais/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String limparCampos() {
        local = new Local();
        locais = null;
        return null;
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        LocalRn localRn = new LocalRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("loca_id"));
            local = localRn.carregarPorId(id);
            localRn.excluir(local);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            return "/restrito/locais/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluirAjax() {
        FacesContext context = FacesContext.getCurrentInstance();
        LocalRn localRn = new LocalRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("loca_id"));
            local = localRn.carregarPorId(id);
            localRn.excluir(local);
            local = new Local();
            locais = null;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String filtrarLocais() {
        locais = new LocalRn().filtrarLocais(local.getCodigo(), local.getDescricao(), local.getEmpresa());
        return null;
    }
}
