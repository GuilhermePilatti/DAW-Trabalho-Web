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
}
