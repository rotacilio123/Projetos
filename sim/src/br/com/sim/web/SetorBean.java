/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import br.com.sim.model.bean.Setor;
import br.com.sim.rn.SetorRn;
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
@ManagedBean(name = "setorBean")
@ViewScoped
public class SetorBean {
    
    private Setor setor = new Setor();
    private List<Setor> setores;
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        SetorRn setorRn = new SetorRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("seto_id"));
            setor = setorRn.carregarPorId(id);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public List<Setor> getSetores() {
        if (setores == null) {
            setores = new SetorRn().listar();
        }
        return setores;
    }
    
    public String salvarSetor() {
        FacesContext context = FacesContext.getCurrentInstance();
        SetorRn setorRn = new SetorRn();
        try {
            Setor setorAux = null;
            try {
                setorAux = setorRn.carregarPorNome(setor.getNome());
                if (setorAux != null && (setor.getId() == null || setor.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {}
            setorRn.salvar(setor);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            return "/restrito/setores/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        SetorRn setorRn = new SetorRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("seto_id"));
            setor = setorRn.carregarPorId(id);
            setorRn.excluir(setor);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            return "/restrito/setores/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluirAjax() {
        FacesContext context = FacesContext.getCurrentInstance();
        SetorRn setorRn = new SetorRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("seto_id"));
            setor = setorRn.carregarPorId(id);
            setorRn.excluir(setor);
            setor = new Setor();
            setores = null;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String limparCampos() {
        setor = new Setor();
        setores = null;
        return null;
    }
    
    public String filtrarSetores() {
        setores = new SetorRn().filtrarSetores(setor.getNome());
        return null;
    }
    
}
