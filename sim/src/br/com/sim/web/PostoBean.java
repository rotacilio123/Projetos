/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import br.com.sim.model.bean.Linha;
import br.com.sim.model.bean.Posto;
import br.com.sim.rn.LinhaRn;
import br.com.sim.rn.MaquinaRn;
import br.com.sim.rn.PostoRn;
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
@ManagedBean(name = "postoBean")
@ViewScoped
public class PostoBean {
    
    private Posto posto = new Posto();
    private List<Posto> postos;
    private List<Linha> linhas;
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        PostoRn postoRn = new PostoRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("post_id"));
            posto = postoRn.carregarPorId(id);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
    }

    public List<Posto> getPostos() {
        if (postos == null) {
            postos = new PostoRn().listar();
        }
        return postos;
    }
    
    public List<Linha> getLinhas() {
        if (linhas == null) {
            linhas = new LinhaRn().listar();
        }
        return linhas;
    }
    
    public String salvarPosto() {
        FacesContext context = FacesContext.getCurrentInstance();
        PostoRn postoRn = new PostoRn();
        try {
            Posto postoAux = null;
            try {
                postoAux = postoRn.pesquisarPosto(posto.getDescricao(), posto.getLinha());
                if (postoAux == null && (posto.getId() == null || posto.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {}
            postoRn.salvar(posto);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            return "/restrito/postos/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("post_id"));
        PostoRn postoRn = new PostoRn();
        posto = postoRn.carregarPorId(id);
        try {
            if (new MaquinaRn().listarMaquinasPorPosto(posto).size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_DELETADO_ERROR_RELATIONS, null));
                return null;
            }
            postoRn.excluir(posto);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            return "/restrito/postos/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluirAjax() {
        FacesContext context = FacesContext.getCurrentInstance();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("post_id"));
        PostoRn postoRn = new PostoRn();
        posto = postoRn.carregarPorId(id);
        try {
            if (new MaquinaRn().listarMaquinasPorPosto(posto).size() > 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_DELETADO_ERROR_RELATIONS, null));
                return null;
            }
            postoRn.excluir(posto);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
        }
        return null;
    }
    
    public String filtrarPostos() {
        postos = new PostoRn().filtrarPostos(posto.getDescricao(), posto.getLinha());
        return null;
    }
    
    public String limparCampos() {
        posto = new Posto();
        postos = null;
        return null;
    }
}
