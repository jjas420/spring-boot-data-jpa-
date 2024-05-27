/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bolsadeideas.springboot.app.view.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

/**
 *
 * @author ayosu
 */
public class FormatoDePedefe extends PdfPageEventHelper {

    /**
     * Constructor de la clase, inicializa la imagen que se utilizara en el
     * membrete
     */

    public void menbrete(Image imgen, Document document, String cliente, String palabra2) {
    PdfPTable table = new PdfPTable(3);

        try {
            var celda1 = new PdfPCell(imgen);

            PdfPCell celda2 = new PdfPCell(new Phrase(palabra2));
            PdfPCell celda3 = new PdfPCell(new Phrase(cliente));

            celda1.setBorder(Rectangle.BOTTOM);
            celda2.setBorder(Rectangle.BOTTOM);
            celda2.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);

            table.addCell(celda1);
            table.addCell(celda2);
            table.addCell(celda3);

            table.setTotalWidth(350f);
            document.add(table);

        } catch (Exception r) {
            System.out.println("error al leer imagen");
        }
    }

    /**
     * Manejador del evento onEndPage, usado para generar el encabezado
     */
}
