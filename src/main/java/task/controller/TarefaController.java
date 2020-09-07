package task.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
		if (validar()) {
			if (this.tarefa.getIdTarefa() != null) {
				this.tarefaService.atualizar(tarefa);
//				if(this.tarefa.getStatus().equals(Status.FINALIZADO)) {
//	            	this.tempoGasto(this.tarefa.getDt_inicio_tf());
//				}
			} else {
				tarefa.setDt_inicio_tf(LocalDateTime.now());
				this.tarefaService.salvar(tarefa);
			}
			String msg = tarefa.getTitulo() + " Salvo com sucesso!";
			addMessage(msg);

			this.tarefas = this.tarefaService.listar();
			this.limparCampos();
		}
	}

	public void limparCampos() {
		tarefa = new Tarefa();
	}

//	public long tempoGasto(LocalDateTime date) {
//		LocalDateTime dataInicio = date;
//		LocalDateTime  dataFim = this.tarefa.getDt_inicio_tf();
//		long  tempoGasto = 8;
//		 return  tempoGasto;
//	}
	
	public void buttonExcluir(Tarefa tarefa) {
		this.tarefaService.excluir(tarefa);
		this.tarefas = this.tarefaService.listar();
	}

	public void buttonAtualizar(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public void teste() {
		System.out.println("ssss");
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

	private boolean validar() {
		// Tive problemas com required, fiz a validação manualmente

		boolean todosCamposValido = true;

		if (this.tarefa.getTitulo() == null || this.tarefa.getTitulo() == "" || this.tarefa.getDescricao() == null
				|| this.tarefa.getDescricao() == "" || this.tarefa.getStatus() == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Por favor preencha os campos obrigatorio", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			todosCamposValido = false;
		}

		return todosCamposValido;
	}

}