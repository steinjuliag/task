package task.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "testeController")
public class TesteController {

	private List<Usuario> usuarios;

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}