/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import br.com.sim.model.bean.Item;
import br.com.sim.rn.ItemRn;
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
@ManagedBean(name = "itemBean")
@ViewScoped
public class ItemBean {
    
    private Item item = new Item();
    private List<Item> itens;
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            String idItem = context.getExternalContext().getRequestParameterMap().get("iten_id");
            if (idItem != null) {
                Integer id = Integer.valueOf(idItem);
                item = new ItemRn().carregarPorId(id);
                return;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItens() {
        if (itens == null) {
            itens = new ItemRn().listar();
        }
        return itens;
    }

    public String filtrarItens() {
        ItemRn itemRn = new ItemRn();
        itens = itemRn.filtrarItens(item.getDescricao());
        return null;
    }
    
    public String salvarItem() {
        ItemRn itemRn = new ItemRn();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                Item itemAux = itemRn.carregarPorDescricao(item.getDescricao());
                if (itemAux != null && (item.getId() == null || item.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {
            }
            itemRn.salvar(item);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            item = new Item();
            itens = null;
            return "/restrito/itens/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluir() {
        ItemRn itemRn = new ItemRn();
        FacesContext context = FacesContext.getCurrentInstance();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("iten_id"));
        item = itemRn.carregarPorId(id);
        itemRn.excluir(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        item = new Item();
        itens = null;
        return "/restrito/itens/index";
    }
    
    public String excluirAjax() {
        ItemRn itemRn = new ItemRn();
        FacesContext context = FacesContext.getCurrentInstance();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("iten_id"));
        item = itemRn.carregarPorId(id);
        itemRn.excluir(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        item = new Item();
        itens = null;
        return null;
    }
    
    public String limparCampos() {
        item = new Item();
        itens = null;
        return null;
    }
}
