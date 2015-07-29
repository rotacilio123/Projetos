package br.com.sim.model.bean;

import br.com.sim.util.SequencesName;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = SequencesName.SEQUENCE_NAME_USUARIOS, name = SequencesName.SEQUENCE_NAME_USUARIOS)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SequencesName.SEQUENCE_NAME_USUARIOS)
    @Column(name = "usua_id")
    private Integer id;

    @Column(name = "usua_nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "usua_email", length = 100, unique = true)
    private String email;

    @Column(name = "usua_login", length = 50, nullable = false, unique = true)
    private String login;

    @Column(name = "usua_senha", length = 50, nullable = false)
    private String senha;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usua_dt_cadastro", nullable = false, updatable = false)
    private Date dataCadastro;

    @Column(name = "usua_ativo", nullable = false)
    private boolean ativo;

    @ManyToMany
    @JoinTable(name = "usuarios_perfis", 
            joinColumns = {@JoinColumn(name = "uspe_usua_id")}, 
            inverseJoinColumns = {@JoinColumn(name = "uspe_perf_id")})
    private List<Perfil> perfis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.email);
        hash = 47 * hash + Objects.hashCode(this.login);
        hash = 47 * hash + Objects.hashCode(this.senha);
        hash = 47 * hash + Objects.hashCode(this.dataCadastro);
        hash = 47 * hash + (this.ativo ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.perfis);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.dataCadastro, other.dataCadastro)) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.perfis, other.perfis)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + ", senha=" + senha + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo + ", perfis=" + perfis + '}';
    }

    public Usuario() {
    }

}
