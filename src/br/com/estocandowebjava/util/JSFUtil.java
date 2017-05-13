package br.com.estocandowebjava.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	public static void adicionarMensagemSucesso(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "Dados salvos com sucesso.");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}
	
	public static void adicionarMensagemErro(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "Erro encontrado");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}
}
