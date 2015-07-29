/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import br.com.sim.model.bean.CheckList;
import br.com.sim.model.bean.Familia;
import br.com.sim.model.bean.Item;
import br.com.sim.model.dao.FamiliaDao;
import br.com.sim.model.dao.ItemDao;
import br.com.sim.rn.CheckListRn;
import br.com.sim.util.Messages;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

/**
 *
 * @author RodrigoOtacilio
 */
@ManagedBean(name = "checkListBean")
@ViewScoped
public class CheckListBean {

    private CheckList checkList;
    private List<String> checkLists;
    private List<Familia> familias;
    private List<Familia> familiasNaoAssociadas = new ArrayList<Familia>();
    private List<Familia> familiasAssociadas = new ArrayList<Familia>();
    private List<Item> itens;
    private List<Item> itensNaoAssociados = new ArrayList<Item>();
    private List<Item> itensAssociados = new ArrayList<Item>();
    private String descricao;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        descricao = context.getExternalContext().getRequestParameterMap().get("chli_descricao");
        CheckListRn checkListRn = new CheckListRn();
        try {
            familiasAssociadas = checkListRn.listarFamiliasPorDescricao(descricao);
            itensAssociados = checkListRn.listarItensPorDescricao(descricao);
            familias = getFamilias();
            itens = getItens();
            familias.removeAll(familiasAssociadas);
            itens.removeAll(itensAssociados);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getClass().getName());
        }
    }

    public List<String> getCheckLists() {
        if (checkLists == null) {
            checkLists = new CheckListRn().listar();
        }
        return checkLists;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Familia> getFamilias() {
        if (familias == null) {
            familias = new FamiliaDao().listar();
        }
        return familias;
    }

    public CheckList getCheckList() {
        return checkList;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public String associarFamilia() {
        if (familiasNaoAssociadas != null && familiasNaoAssociadas.size() > 0) {
            for (Familia f : familiasNaoAssociadas) {
                familias.remove(f);
                familiasAssociadas.add(f);
            }
            familiasNaoAssociadas.clear();
        }
        return null;
    }

    public String desassociarFamilia() {
        if (familiasAssociadas != null && familiasAssociadas.size() > 0) {
            for (Familia f : familiasAssociadas) {
                familiasNaoAssociadas.add(f);
                familias.add(f);
            }
            familiasAssociadas.clear();
        }
        return null;
    }

    public List<Familia> getFamiliasNaoAssociadas() {
        return familiasNaoAssociadas;
    }

    public void setFamiliasNaoAssociadas(List<Familia> familiasNaoAssociadas) {
        this.familiasNaoAssociadas = familiasNaoAssociadas;
    }

    public List<Familia> getFamiliasAssociadas() {
        return familiasAssociadas;
    }

    public void setFamiliasAssociadas(List<Familia> familiasAssociadas) {
        this.familiasAssociadas = familiasAssociadas;
    }

    public List<Item> getItens() {
        if (itens == null) {
            itens = new ItemDao().listar();
        }
        return itens;
    }

    public List<Item> getItensNaoAssociados() {
        return itensNaoAssociados;
    }

    public void setItensNaoAssociados(List<Item> itensNaoAssociados) {
        this.itensNaoAssociados = itensNaoAssociados;
    }

    public List<Item> getItensAssociados() {
        return itensAssociados;
    }

    public void setItensAssociados(List<Item> itensAssociados) {
        this.itensAssociados = itensAssociados;
    }

    public String associarItem() {
        if (itensNaoAssociados != null && itensNaoAssociados.size() > 0) {
            for (Item i : itensNaoAssociados) {
                itens.remove(i);
                itensAssociados.add(i);
            }
            itensNaoAssociados.clear();
        }
        return null;
    }

    public String desassociarItem() {
        if (itensAssociados != null && itensAssociados.size() > 0) {
            for (Item i : itensAssociados) {
                itensNaoAssociados.add(i);
                itens.add(i);
            }
            itensAssociados.clear();
        }
        return null;
    }

    public String salvarCheckList() {
        FacesContext context = FacesContext.getCurrentInstance();
        CheckListRn checkListRn = new CheckListRn();
        StringBuilder str = new StringBuilder();
        try {
            for (Familia f : familiasAssociadas) {
                for (Item i : itensAssociados) {
                    CheckList clAux = null;
                    try {
                        clAux = checkListRn.carregarPorFamiliaEItem(f, i);
                    } catch (NoResultException e) {
                    }
                    if (clAux != null) {
                        str.append("Família: " + f.getCodigo() + ", Item: " + i.getDescricao() + "\n");
                        continue;
                    }
                    checkList = new CheckList();
                    checkList.setDescricao(descricao);
                    checkList.setFamilia(f);
                    checkList.setItem(i);
                    checkListRn.salvar(checkList);
                }
            }
            String message = Messages.REGISTRO_SALVO_SUCESSO;
            if (!str.toString().isEmpty()) {
                message += "Estes registros não foral alterados:\n" + str.toString();
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            return null;
        }
        return "/restrito/checklist/index";
    }

    public String excluirCheckList() {
        FacesContext context = FacesContext.getCurrentInstance();
        CheckListRn checkListRn = new CheckListRn();
        String returnPage = context.getExternalContext().getRequestParameterMap().get("return_page");
        try {
            String descricao = context.getExternalContext().getRequestParameterMap().get("chli_descricao");
            for (CheckList c : checkListRn.listarPorDescricao(descricao)) {
                checkListRn.excluir(c);
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            checkLists = null;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return returnPage;
    }
    
}
