/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import br.com.sim.model.bean.Perfil;
import br.com.sim.rn.PerfilRn;
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
@ManagedBean(name = "perfilBean")
@ViewScoped
public class PerfilBean {
    
    private Perfil perfil = new Perfil();
    private List<Perfil> perfis;
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        PerfilRn perfilRn = new PerfilRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("perf_id"));
            perfil = perfilRn.carregarPorId(id);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public List<Perfil> getPerfis() {
        if (perfis == null) {
            perfis = new PerfilRn().listar();
        }
        return perfis;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    public String salvarPerfil() {
        FacesContext context = FacesContext.getCurrentInstance();
        PerfilRn perfilRn = new PerfilRn();
        try {
            Perfil perfilAux = null;
            try {
                perfilAux = perfilRn.carregarPorNome(perfil.getNome());
                if (perfilAux != null && (perfil.getId() == null || perfil.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {}
            perfilRn.salvar(perfil);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            return "/restrito/perfis/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        PerfilRn perfilRn = new PerfilRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("perf_id"));
            perfil = perfilRn.carregarPorId(id);
            perfilRn.excluir(perfil);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            return "/restrito/perfis/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluirAjax() {
        FacesContext context = FacesContext.getCurrentInstance();
        PerfilRn perfilRn = new PerfilRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("perf_id"));
            perfil = perfilRn.carregarPorId(id);
            perfilRn.excluir(perfil);
            perfil = new Perfil();
            perfis = null;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String limparCampos() {
        perfil = new Perfil();
        perfis = null;
        return null;
    }
    
    public String filtrarPerfis() {
        perfis = new PerfilRn().filtrarPerfis(perfil.getNome(), perfil.getDescricao());
        return null;
    }
    
}
