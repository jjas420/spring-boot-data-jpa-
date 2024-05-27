/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bolsadeideas.springboot.app.view.pdf;

import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.ItemFactura;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;

import java.net.URL;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author ayosu
 */
@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {

    FormatoDePedefe membrete = new FormatoDePedefe();

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Factura factura = (Factura) model.get("factura");
        String nombreLogo = "/ImagenPdf/can.png";
        URL resUrl = this.getClass().getResource(nombreLogo);

        Image imagen = Image.getInstance(resUrl);
        imagen.scalePercent(10, 10);
        String nombre1 = factura.getCliente().getNombre();

        membrete.menbrete(imagen, document, nombre1, "factura del cliente");

        PdfPTable tabla = new PdfPTable(1);

        tabla.setSpacingBefore(20);
        tabla.setSpacingAfter(20);
        tabla.addCell("Datos Del Cliente");
        tabla.addCell(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
        tabla.addCell(factura.getCliente().getEmail());

        PdfPTable tabla2 = new PdfPTable(1);
        tabla2.setSpacingAfter(20);

        tabla2.addCell("Datos de la Factura");
        tabla2.addCell("Folio: " + factura.getId());
        tabla2.addCell("Descripcion: " + factura.getDescripcion());
        tabla2.addCell("Fecha: " + factura.getCreateAt());
        document.add(tabla);
        document.add(tabla2);
        PdfPTable tabla3 = new PdfPTable(4);

        tabla3.addCell("Productos");
        tabla3.addCell("Precio");
        tabla3.addCell("Cantidad");
        tabla3.addCell("total");
        for (ItemFactura item : factura.getItems()) {
            tabla3.addCell(item.getProducto().getNombre());
            tabla3.addCell(item.getProducto().getPrecio().toString());
            tabla3.addCell(item.getCantidad().toString());
            tabla3.addCell(item.calcularImporte().toString());

        }
        document.add(tabla3);
        
        String total= factura.getTotal().toString();

        membrete.menbrete(imagen, document, total, "total");
        

    }

}
