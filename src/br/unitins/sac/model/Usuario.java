package br.unitins.sac.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.unitins.frame.model.Model;

@Entity
public class Usuario extends Model<Usuario> {
	private static final long serialVersionUID = 1364268628649221460L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqidusuario")
	@SequenceGenerator(name = "seqidusuario", sequenceName = "seqidusuario", allocationSize = 1)
	private Integer id;
	private String nome;
	private String usuario;
	private String senha;

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	


	

}
