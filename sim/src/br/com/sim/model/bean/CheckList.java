/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.bean;

import br.com.sim.util.SequencesName;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author RodrigoOtacilio
 */
@Entity
@Table(name = "check_list")
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = SequencesName.SEQUENCE_NAME_CHECK_LIST, sequenceName = SequencesName.SEQUENCE_NAME_CHECK_LIST)
public class CheckList implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4747911462799713555L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SequencesName.SEQUENCE_NAME_CHECK_LIST)
    @Column(name = "chli_id")
    private Integer id;
    
    @Column(name = "chli_descricao", length = 100, nullable = false)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "chli_iten_id", nullable = false)
    private Item item;
    
    @ManyToOne
    @JoinColumn(name = "chli_fami_id", nullable = false)
    private Familia familia;
    
//    @ManyToMany
//    @JoinTable(name = "maquinas_check_list", joinColumns = {@JoinColumn(name = "macl_chli_id")}, inverseJoinColumns = {@JoinColumn(name = "macl_maqu_id")})
//    private List<Maquina> maquinas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

//    public List<Maquina> getMaquinas() {
//        return maquinas;
//    }
//
//    public void setMaquinas(List<Maquina> maquinas) {
//        this.maquinas = maquinas;
//    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.descricao);
        hash = 61 * hash + Objects.hashCode(this.item);
        hash = 61 * hash + Objects.hashCode(this.familia);
//        hash = 61 * hash + Objects.hashCode(this.maquinas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CheckList other = (CheckList) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        if (!Objects.equals(this.familia, other.familia)) {
            return false;
        }
//        if (!Objects.equals(this.maquinas, other.maquinas)) {
//            return false;
//        }
        return true;
    }

    @Override
    public String toString() {
        return "CheckList{" + "id=" + id + ", item=" + item + ", familia=" + familia + '}';
    }

    public CheckList() {
    }
}
