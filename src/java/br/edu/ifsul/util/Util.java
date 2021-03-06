/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ws
 */
public class Util {
    public static void mensagemInformacao(String strMensagem){
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, strMensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public static void mensagemErro(String strMensagem){
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, strMensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public static String getMessageErro(Exception e) {
        while(e.getCause() != null) {
            e = (Exception) e.getCause();
        }
        String erro = e.getMessage();
        
        if (erro.contains("operador não existe")) {
            erro = "Erro de operador!";
        }
        
        if (erro.contains("viola restrição de chave estrangeira")){
            erro = "Registro não pode ser excluido pois possui referencias em outros locais";
        }
        return erro;
    }
}
