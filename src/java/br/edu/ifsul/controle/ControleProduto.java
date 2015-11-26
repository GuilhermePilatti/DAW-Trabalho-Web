/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MateriaPrimaDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.modelo.ItensProduto;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleProduto")
@ViewScoped
public class ControleProduto implements Serializable{
    private ProdutoDAO dao;
    private Produto objeto;
    private ItensProduto item;
    private Boolean novoItem;
    private MateriaPrimaDAO daoMateriaPrima;

    public ControleProduto() {
        dao = new ProdutoDAO();
        daoMateriaPrima = new MateriaPrimaDAO();
    }
    
    public void novoItem(){
        item = new ItensProduto();
        novoItem = true;
    }
    
    public void alterarItem(int index){
        item = objeto.getItens().get(index);
        novoItem = false;
    }
    
    public void salvarItem(){
        if(novoItem){
            objeto.adicionarItem(item);
        }
        Util.mensagemInformacao("Item adicionado com sucesso");
    }
    
    public void removertItem(int index){
        objeto.removerItem(index);
        Util.mensagemInformacao("Item removido com sucesso");
    }
    
    public String listar(){
        return "/privado/produto/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Produto();
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

    public ProdutoDAO getDao() {
        return dao;
    }

    public void setDao(ProdutoDAO dao) {
        this.dao = dao;
    }

    public Produto getObjeto() {
        return objeto;
    }

    public void setObjeto(Produto objeto) {
        this.objeto = objeto;
    }

    public ItensProduto getItem() {
        return item;
    }

    public void setItem(ItensProduto item) {
        this.item = item;
    }

    public Boolean getNovoItem() {
        return novoItem;
    }

    public void setNovoItem(Boolean novoItem) {
        this.novoItem = novoItem;
    }

    public MateriaPrimaDAO getDaoMateriaPrima() {
        return daoMateriaPrima;
    }

    public void setDaoMateriaPrima(MateriaPrimaDAO daoMateriaPrima) {
        this.daoMateriaPrima = daoMateriaPrima;
    }
}
