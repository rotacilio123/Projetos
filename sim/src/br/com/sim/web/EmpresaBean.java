/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import br.com.sim.model.bean.Empresa;
import br.com.sim.rn.EmpresaRn;
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
@ManagedBean(name = "empresaBean")
@ViewScoped
public class EmpresaBean {
    
    private Empresa empresa = new Empresa();
    private List<Empresa> empresas;
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        EmpresaRn empresaRn = new EmpresaRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("empr_id"));
            empresa = empresaRn.carregarPorId(id);
        } catch (Exception e) {
        }
    }

    public List<Empresa> getEmpresas() {
        if (empresas == null) {
            empresas = new EmpresaRn().listar();
        }
        return empresas;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public String salvarEmpresa() {
        FacesContext context = FacesContext.getCurrentInstance();
        EmpresaRn empresaRn = new EmpresaRn();
        try {
            Empresa empAux = null;
            try {
                empAux = empresaRn.carregarPorNomeECodigo(empresa.getCodigo(), empresa.getNome());
                if (empAux != null && (empresa.getId() == null || empresa.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {
            }
            empresaRn.salvar(empresa);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            return "/restrito/empresas/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        EmpresaRn empresaRn = new EmpresaRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("empr_id"));
            empresa = empresaRn.carregarPorId(id);
            empresaRn.excluir(empresa);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            return "/restrito/empresas/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluirAjax() {
        FacesContext context = FacesContext.getCurrentInstance();
        EmpresaRn empresaRn = new EmpresaRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("empr_id"));
            empresa = empresaRn.carregarPorId(id);
            empresaRn.excluir(empresa);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            empresa = new Empresa();
            empresas = null;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
        }
        return null;    }
    
    public String limparCampos() {
        empresa = new Empresa();
        empresas = null;
        return null;
    }
    
    public String filtrarEmpresas() {
        empresas = new EmpresaRn().filtrarEmpresas(empresa.getCodigo(), empresa.getNome());
        return null;
    }
    
    
}
