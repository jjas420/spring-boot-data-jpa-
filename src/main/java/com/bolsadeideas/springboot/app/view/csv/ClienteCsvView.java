/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bolsadeideas.springboot.app.view.csv;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author ayosu
 */
@Component("listar.csv")
public class ClienteCsvView extends AbstractView {

    @Override
    protected boolean generatesDownloadContent() {
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public ClienteCsvView() {
        setContentType("text/csv");
    }
    
    

    @Override 
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    response.setHeader("Content-Disposition", "attachment; filename=\"clientes.csv\"");
    response.setContentType(getContentType());
     ICsvBeanWriter  beanWriter= new CsvBeanWriter(response.getWriter(),
                        CsvPreference.STANDARD_PREFERENCE);
     
    Page<Cliente> clientes= ( Page<Cliente>) model.get("clientes");
     String [] header= {"id","nombre","apellido","email","createAt"};
    beanWriter.writeHeader(header);
    for(Cliente cliente:clientes){
        beanWriter.write(cliente, header);
        
    }
    beanWriter.close();
     
     
    
    }
    
}
