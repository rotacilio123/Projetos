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
@Table(name = "postos")
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = SequencesName.SEQUENCE_NAME_POSTOS, sequenceName = SequencesName.SEQUENCE_NAME_POSTOS)
public class Posto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SequencesName.SEQUENCE_NAME_POSTOS)
    @Column(name = "post_id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "post_linh_id", nullable = false)
    private Linha linha;
    
    @Column(name = "post_descricao", length = 100, nullable = false)
    private String descricao;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.linha);
        hash = 53 * hash + Objects.hashCode(this.descricao);
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
        final Posto other = (Posto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.linha, other.linha)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Posto{" + "id=" + id + ", linha=" + linha + ", descricao=" + descricao + '}';
    }

    public Posto() {
    }
}
