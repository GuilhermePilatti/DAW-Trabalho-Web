/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FornecedorDAO;
import br.edu.ifsul.dao.MateriaPrimaDAO;
import br.edu.ifsul.modelo.MateriaPrima;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleMateriaPrima")
@ViewScoped
public class ControleMateriaPrima implements Serializable{
    private MateriaPrimaDAO dao;
    private MateriaPrima objeto;
    private FornecedorDAO daoFornecedor;

    public ControleMateriaPrima() {
        dao = new MateriaPrimaDAO();
        daoFornecedor = new FornecedorDAO();
    }
    
    public String listar(){
        return "/privado/materiaprima/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new MateriaPrima();
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

    public MateriaPrimaDAO getDao() {
        return dao;
    }

    public void setDao(MateriaPrimaDAO dao) {
        this.dao = dao;
    }

    public MateriaPrima getObjeto() {
        return objeto;
    }

    public void setObjeto(MateriaPrima objeto) {
        this.objeto = objeto;
    }

    public FornecedorDAO getDaoFornecedor() {
        return daoFornecedor;
    }

    public void setDaoFornecedor(FornecedorDAO daoFornecedor) {
        this.daoFornecedor = daoFornecedor;
    }
}
