package br.unitins.sac.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import br.unitins.frame.application.ApplicationException;
import br.unitins.frame.application.Config;
import br.unitins.frame.application.Util;
import br.unitins.frame.application.ValidationException;
import br.unitins.frame.controller.Controller;
import br.unitins.frame.validation.Validation;
import br.unitins.sac.factory.JPAFactory;
import br.unitins.sac.model.Aluno;
import br.unitins.sac.model.Cidade;
import br.unitins.sac.repository.AlunoRepository;
import br.unitins.sac.repository.CidadeRepository;
import br.unitins.sac.validation.AlunoValidation;

@ManagedBean
@ViewScoped
public class AlunoController extends Controller<Aluno> {

	private List<Cidade> listaCidade;
	private List<Aluno> listaAluno;

	@Override
	public Aluno getEntity() {
		if (entity == null) {
			entity = new Aluno();
			entity.setCidade(new Cidade());
		}
		return entity;
	}

	@Override
	public void clean(ActionEvent actionEvent) {
		super.clean(actionEvent);
		setListaAluno(null);
		setListaCidade(null);
	}

	@Override
	public Validation<Aluno> getValidation() {
		return new AlunoValidation();
	}

	@Override
	protected EntityManager getEntityManager() {
		return JPAFactory.getEntityManager();
	}
	
	@Override
	public void insert(ActionEvent actionEvent) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			validarEntidade();
			
			em.merge(getEntity()); // produto
			em.merge(new Aluno());
			
			em.getTransaction().commit();
			clean(actionEvent);
			Util.infoMessage(Config.INSERT_SUCCESS_MSG);
		} catch (ValidationException e) {
			em.getTransaction().rollback();
			Util.showMessagesWarning(e.getListMessages());
		}
	}
	
	
	public List<Aluno> getListaAluno() {
		if (listaAluno == null) {
			AlunoRepository repository = new AlunoRepository(JPAFactory.getEntityManager());
			listaAluno = repository.bucarTodos();
		}
		return listaAluno;
	}

	public void setListaAluno(List<Aluno> listaServidor) {
		this.listaAluno = listaServidor;
	}


	public List<Cidade> getListaCidade() {
		if (listaCidade == null) {
			CidadeRepository repository = new CidadeRepository(JPAFactory.getEntityManager());
			listaCidade = repository.bucarTodos();
		}
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}
	

}
