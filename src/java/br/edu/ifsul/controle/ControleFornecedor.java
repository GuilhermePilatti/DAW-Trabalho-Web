/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FornecedorDAO;
import br.edu.ifsul.modelo.Fornecedor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleFornecedor")
@ViewScoped
public class ControleFornecedor implements Serializable{
    private FornecedorDAO dao;
    private Fornecedor objeto;

    public ControleFornecedor() {
        dao = new FornecedorDAO();
    }
    
    public String listar(){
        return "/privado/fornecedor/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Fornecedor();
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
            Util.mensagemErro("Erro ao remover objeto "+Util.getMessageErro(e));
        }
    }
    
    public void editar(Integer id){
        try{
            objeto = dao.getObjectById(id);
        } catch(Exception e){
            Util.mensagemErro("Erro ao remover objeto "+Util.getMessageErro(e));
        }
    }
    
    public void remover(Integer id){
        try{
            dao.remover(id);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch(Exception e){
            Util.mensagemErro("Erro ao remover objeto "+Util.getMessageErro(e));
        }
    }

    public FornecedorDAO getDao() {
        return dao;
    }

    public void setDao(FornecedorDAO dao) {
        this.dao = dao;
    }

    public Fornecedor getObjeto() {
        return objeto;
    }

    public void setObjeto(Fornecedor objeto) {
        this.objeto = objeto;
    }
}
