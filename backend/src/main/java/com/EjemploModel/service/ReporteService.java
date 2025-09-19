package com.EjemploModel.service;


import com.EjemploModel.model.Usuario;
import com.EjemploModel.repository.UsuarioRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ByteArrayInputStream generarReporteUsuarios() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Título
            document.add(new Paragraph("Reporte de Usuarios"));
            document.add(new Paragraph(" ")); // salto de línea

            // Tabla
            PdfPTable table = new PdfPTable(3);
            table.addCell("ID");
            table.addCell("Apodo");
            table.addCell("Correo");

            List<Usuario> usuarios = usuarioRepository.findAll();
            for (Usuario u : usuarios) {
                table.addCell(String.valueOf(u.getId()));
                table.addCell(u.getApodo());
                table.addCell(u.getCorreo());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
