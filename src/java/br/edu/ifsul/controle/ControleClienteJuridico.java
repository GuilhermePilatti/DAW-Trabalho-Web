/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClienteJuridicoDAO;
import br.edu.ifsul.modelo.ClienteJuridico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleClienteJuridico")
@ViewScoped
public class ControleClienteJuridico implements Serializable{
    private ClienteJuridicoDAO dao;
    private ClienteJuridico objeto;

    public ControleClienteJuridico() {
        dao = new ClienteJuridicoDAO();
    }
    
    public String listar(){
        return "/privado/clientejuridico/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new ClienteJuridico();
    }
    
    public void salvar(){
        try{
            if(objeto.getCodigo()== null){
                dao.pesistir(objeto);
            } else{
                dao.merge(objeto);
            }
            
            Util.mensagemInformacao("Objeto persistido com sucesso");
        } catch(Exception e){
            Util.mensagemErro(e.getMessage());
        }
    }
    
    public void editar(Integer id){
        try{
            objeto = dao.getObjectById(id);
        } catch(Exception e){
            Util.mensagemErro(e.getMessage());
        }
    }
    
    public void remover(Integer id){
        try{
            dao.remover(id);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch(Exception e){
            Util.mensagemErro(e.getMessage());
        }
    }

    public ClienteJuridicoDAO getDao() {
        return dao;
    }

    public void setDao(ClienteJuridicoDAO dao) {
        this.dao = dao;
    }

    public ClienteJuridico getObjeto() {
        return objeto;
    }

    public void setObjeto(ClienteJuridico objeto) {
        this.objeto = objeto;
    }
}
