/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClienteFisicoDAO;
import br.edu.ifsul.modelo.ClienteFisico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleClienteFisico")
@ViewScoped
public class ControleClienteFisico implements Serializable{
    private ClienteFisicoDAO dao;
    private ClienteFisico objeto;

    public ControleClienteFisico() {
        dao = new ClienteFisicoDAO();
    }
    
    public String listar(){
        return "/privado/clientefisico/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new ClienteFisico();
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

    public ClienteFisicoDAO getDao() {
        return dao;
    }

    public void setDao(ClienteFisicoDAO dao) {
        this.dao = dao;
    }

    public ClienteFisico getObjeto() {
        return objeto;
    }

    public void setObjeto(ClienteFisico objeto) {
        this.objeto = objeto;
    }
}
