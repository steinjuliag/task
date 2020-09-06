package task.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import task.model.Status;
import task.model.Tarefa;
import task.service.TarefaService;

@Named(value = "tarefaController")
@RequestScoped
public class TarefaController {

	@Inject
	private TarefaService tarefaService;

	private List<Tarefa> tarefas;

	@Inject
	private Tarefa tarefa;

	@PostConstruct
	public void init() {
		tarefas = this.tarefaService.listar();
	}

	/************** Gets e setters dos atributo da tela *****************/

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	/************** METODOS DE ACAO *****************/

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void buttonSalvar() {
		String msg = tarefa.getTitulo() + " Salvo com sucesso!";
		addMessage(msg);

		if (this.tarefa.getIdTarefa() != null) {
			this.tarefaService.atualizar(tarefa);
		} else {
			this.tarefaService.salvar(tarefa);
		}

		this.tarefas = this.tarefaService.listar();
		this.limparCampos();
	}

	public void limparCampos() {
		tarefa = new Tarefa();
	}

	public void buttonExcluir(Tarefa tarefa) {
		this.tarefaService.excluir(tarefa);
		this.tarefas = this.tarefaService.listar();
	}

	public void buttonAtualizar(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public String valorStatus(Status status) {
		String valor = " ";

		if (status.equals(Status.NAO_INICIADO)) {
			valor = "Não iniciado";
		} else if (status.equals(Status.INICIADO)) {
			valor = "Andamento";
		} else if (status.equals(Status.FINALIZADO)) {
			valor = "Finalizado";
		}
		return valor;

	}
}