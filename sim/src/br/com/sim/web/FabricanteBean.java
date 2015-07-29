/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import br.com.sim.model.bean.Fabricante;
import br.com.sim.rn.FabricanteRn;
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
@ManagedBean(name = "fabricanteBean")
@ViewScoped
public class FabricanteBean {

    private Fabricante fabricante = new Fabricante();
    private List<Fabricante> fabricantes;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        FabricanteRn fabricanteRn = new FabricanteRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("fabr_id"));
            fabricante = fabricanteRn.carregarPorId(id);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public List<Fabricante> getFabricantes() {
        if (fabricantes == null) {
            fabricantes = new FabricanteRn().listar();
        }
        return fabricantes;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public String salvarFabricante() {
        FacesContext context = FacesContext.getCurrentInstance();
        FabricanteRn fabricanteRn = new FabricanteRn();
        try {
            Fabricante fabrAux = null;
            try {
                fabrAux = fabricanteRn.carregarPorNome(fabricante.getNome());
                if (fabrAux != null && (fabricante.getId() == null || fabricante.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {
            }
            fabricanteRn.salvar(fabricante);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            return "/restrito/fabricantes/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        FabricanteRn fabricanteRn = new FabricanteRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("fabr_id"));
            fabricante = fabricanteRn.carregarPorId(id);
            fabricanteRn.excluir(fabricante);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            return "/restrito/fabricantes/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }

    public String excluirAjax() {
        FacesContext context = FacesContext.getCurrentInstance();
        FabricanteRn fabricanteRn = new FabricanteRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("fabr_id"));
            fabricante = fabricanteRn.carregarPorId(id);
            fabricanteRn.excluir(fabricante);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            fabricante = new Fabricante();
            fabricantes = null;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }

    public String limparCampos() {
        fabricante = new Fabricante();
        fabricantes = null;
        return null;
    }

    public String filtrarFabricantes() {
        fabricantes = new FabricanteRn().filtrarFabricantes(fabricante.getNome());
        return null;
    }
}
