/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import br.com.sim.model.bean.Linha;
import br.com.sim.rn.LinhaRn;
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
@ManagedBean(name = "linhaBean")
@ViewScoped
public class LinhaBean {
    
    private Linha linha = new Linha();
    private List<Linha> linhas;
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            String idLinha = context.getExternalContext().getRequestParameterMap().get("linh_id");
            if (idLinha != null) {
                Integer id = Integer.valueOf(idLinha);
                linha = new LinhaRn().carregarPorId(id);
                return;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public List<Linha> getLinhas() {
        if (linhas == null) {
            linhas = new LinhaRn().listar();
        }
        return linhas;
    }
    
    public String filtrarLinhas() {
        LinhaRn linhaRn = new LinhaRn();
        linhas = linhaRn.filtrarLinhas(linha.getNome());
        return null;
    }
    
    public String salvarLinha() {
        LinhaRn linhaRn = new LinhaRn();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                Linha linhaAux = linhaRn.carregarPorNome(linha.getNome());
                if (linhaAux != null && (linha.getId() == null || linha.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {
            }
            linhaRn.salvar(linha);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            linha = new Linha();
            linhas = null;
            return "/restrito/linhas/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluir() {
        LinhaRn linhaRn = new LinhaRn();
        FacesContext context = FacesContext.getCurrentInstance();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("linh_id"));
        linha = linhaRn.carregarPorId(id);
        linhaRn.excluir(linha);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        linha = new Linha();
        linhas = null;
        return "/restrito/linhas/index";
    }
    
    public String excluirAjax() {
        LinhaRn linhaRn = new LinhaRn();
        FacesContext context = FacesContext.getCurrentInstance();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("linh_id"));
        linha = linhaRn.carregarPorId(id);
        linhaRn.excluir(linha);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        linha = new Linha();
        linhas = null;
        return null;
    }
    
    public String limparCampos() {
        linha = new Linha();
        linhas = null;
        return null;
    }
}
