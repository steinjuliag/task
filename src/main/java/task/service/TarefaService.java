package task.service;

import java.util.List;

import javax.inject.Inject;

import task.model.Tarefa;
import task.repository.TaskRepository;

public class TarefaService {

	@Inject
	TaskRepository taskRepository;

	public void salvar(Tarefa tarefa) {
		taskRepository.salvar(tarefa);
	}

	public void excluir(Tarefa tarefa) {
		taskRepository.excluir(tarefa);
	}

	public void atualizar(Tarefa tarefa) {
		taskRepository.atualizar(tarefa);
	}

	public List<Tarefa> listar() {
		return taskRepository.listar();
	}

}
