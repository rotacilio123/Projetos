/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.bean;

import br.com.sim.util.SequencesName;
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
 * @author Rodrigo Otacilio
 */
@Entity
@Table(name = "locais")
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = SequencesName.SEQUENCE_NAME_LOCAIS, sequenceName = SequencesName.SEQUENCE_NAME_LOCAIS)
public class Local {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SequencesName.SEQUENCE_NAME_LOCAIS)
    @Column(name = "loca_id")
    private Integer id;
    
    @Column(name = "loca_codigo", nullable = false)
    private Integer codigo;
    
    @Column(name = "loca_descricao", nullable = false, length = 100)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "loca_empr_id", nullable = false)
    private Empresa empresa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.codigo);
        hash = 89 * hash + Objects.hashCode(this.descricao);
        hash = 89 * hash + Objects.hashCode(this.empresa);
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
        final Local other = (Local) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Local{" + "id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", empresa=" + empresa + '}';
    }

    public Local() {
    }
}
