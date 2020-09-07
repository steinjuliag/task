package task.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_tarefa")
public class Tarefa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTarefa;

	@Enumerated(EnumType.STRING)
	private Status status;

	private String titulo;

	private String descricao;

	@Column(name="dt_inicio_tf")
	private LocalDateTime dt_inicio_tf;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(Long idTarefa) {
		this.idTarefa = idTarefa;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getDt_inicio_tf() {
		return dt_inicio_tf;
	}

	public void setDt_inicio_tf(LocalDateTime dt_inicio_tf) {
		this.dt_inicio_tf = dt_inicio_tf;
	}

	@Override
	public String toString() {
		return "Tarefa [idTarefa=" + idTarefa + ", status=" + status + ", titulo=" + titulo + ", descricao=" + descricao
				+ ", dt_inicio_tf=" + dt_inicio_tf + "]";
	}

	

	

}
