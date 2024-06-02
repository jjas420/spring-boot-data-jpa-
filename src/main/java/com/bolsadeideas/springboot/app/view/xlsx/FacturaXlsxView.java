/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bolsadeideas.springboot.app.view.xlsx;

import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.ItemFactura;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

/**
 *
 * @author ayosu
 */
@Component("factura/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Factura factura = (Factura) model.get("factura");

        Sheet sheet = workbook.createSheet("factura" + "_" + factura.getCliente().getNombre() + "_" + factura.getCliente().getApellido());
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
                row = sheet.createRow(1);
        cell = row.createCell(0);

        cell.setCellValue(factura.getCliente().getEmail());


        sheet.createRow(5).createCell(0).setCellValue("datos de la factura");
        sheet.createRow(6).createCell(0).setCellValue("folio: " + factura.getId());
        sheet.createRow(7).createCell(0).setCellValue("descripcion: " + factura.getDescripcion());
        sheet.createRow(8).createCell(0).setCellValue("fecha:" + factura.getCreateAt());

        Row header = sheet.createRow(9);
        header.createCell(0).setCellValue("Producto");
        header.createCell(1).setCellValue("Precio");
        header.createCell(2).setCellValue("Cantidad");
        header.createCell(3).setCellValue("Total");

        int rownum = 10;
        for (ItemFactura item : factura.getItems()) {
            Row fila = sheet.createRow(rownum++);
            fila.createCell(0).setCellValue(item.getProducto().getNombre());
            fila.createCell(1).setCellValue(item.getProducto().getPrecio());
            fila.createCell(2).setCellValue(item.getCantidad());
            fila.createCell(3).setCellValue(item.calcularImporte());
        }
        Row filatotal = sheet.createRow(rownum);
        filatotal.createCell(2).setCellValue("Gran Total: ");
        filatotal.createCell(3).setCellValue(factura.getTotal());

    }

}
