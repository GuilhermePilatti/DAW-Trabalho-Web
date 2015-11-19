/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.MateriaPrima;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ws
 */
@FacesConverter(value = "converterMateriaPrima")
public class ConverterMateriaPrima implements Converter, Serializable{

    // Método que converte da tela para um objeto(MateriaPrima)
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.equals("Seleciona um registro")){
            return null;
        }
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAW-Trabalho-ModelPU");
        EntityManager em = emf.createEntityManager();
        
        try{
            return em.find(MateriaPrima.class, Integer.parseInt(value));
        } catch (Exception e){
            return null;
        } finally{
            em.close();
            emf.close();
        }
    }

    // Método que converte do objeto para a tela
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        MateriaPrima obj = (MateriaPrima) value;
        return obj.getCodigo().toString();
    }
    
}
