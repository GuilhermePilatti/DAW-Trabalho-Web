/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PedidoDAO;
import br.edu.ifsul.dao.VendaDAO;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleVenda")
@ViewScoped
public class ControleVenda implements Serializable{
    private VendaDAO dao;
    private Venda objeto;
    private PedidoDAO daoPedido;

    public ControleVenda() {
        dao = new VendaDAO();
        daoPedido = new PedidoDAO();
    }
    
    public String listar(){
        return "/privado/venda/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Venda();
    }
    
    public void salvar(){
        try{
            objeto.setValorTotal(objeto.getPedido().getValorTotal());
            objeto.setQuantidadeSacas(objeto.getPedido().getQuantidadeSacas());
            objeto.setPesoTotal(objeto.getPedido().getPesoTotal());
            
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

    public VendaDAO getDao() {
        return dao;
    }

    public void setDao(VendaDAO dao) {
        this.dao = dao;
    }

    public Venda getObjeto() {
        return objeto;
    }

    public void setObjeto(Venda objeto) {
        this.objeto = objeto;
    }

    public PedidoDAO getDaoPedido() {
        return daoPedido;
    }

    public void setDaoPedido(PedidoDAO daoPedido) {
        this.daoPedido = daoPedido;
    }
}
