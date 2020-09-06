package task.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.graalvm.compiler.nodes.virtual.EnsureVirtualizedNode;

import task.model.Tarefa;
import task.uteis.Uteis;

public class TaskRepository {

	EntityManager entityManager;

	public void salvar(Tarefa tarefa) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.persist(tarefa);
	}

	public void excluir(Tarefa tarefa) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(tarefa);
	}

	public void atualizar(Tarefa tarefa) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.merge(tarefa);
	}

	public List<Tarefa> listar() {
		entityManager = Uteis.JpaEntityManager();
		List<Tarefa> retorno = entityManager.createQuery("SELECT t from " + Tarefa.class.getSimpleName() + " t")
				.getResultList();
		return retorno;
	}

}
