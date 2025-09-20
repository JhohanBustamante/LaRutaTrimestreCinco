package com.EjemploModel.controller;

import java.util.List;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.EjemploModel.model.Reporte;
import com.EjemploModel.model.ReporteFiltroDTO;
import com.EjemploModel.service.ReporteMultiCriterioService;
import com.EjemploModel.service.ReporteService;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteMultiCriterioService reporteMultiCriterioService;

    @Autowired
    private ReporteService reporteService;

    @PostMapping
    public ResponseEntity<Reporte> crearReporte(@RequestBody Reporte reporte) {
        Reporte nuevo = reporteService.crearReporte(reporte);
        return ResponseEntity.ok(nuevo);
    }

    // 🔹 Listar todos los reportes
    @GetMapping("/todos")
    public ResponseEntity<List<Reporte>> listarReportes() {
        return ResponseEntity.ok(reporteService.listarReportes());
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> obtenerReporte(
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Double cantidadMin,
            @RequestParam(required = false) Double cantidadMax) {

        ReporteFiltroDTO filtro = new ReporteFiltroDTO();
        filtro.setFechaInicio(fechaInicio);
        filtro.setFechaFin(fechaFin);
        filtro.setCategoria(categoria);
        filtro.setEstado(estado);
        filtro.setCantidadMin(cantidadMin);
        filtro.setCantidadMax(cantidadMax);

        List<Reporte> reportes = reporteMultiCriterioService.generarReporte(filtro);
        return ResponseEntity.ok(reportes);
    }

    @GetMapping(value = "/pdf", produces = "application/pdf")
public ResponseEntity<byte[]> generarReportePdf(
        @RequestParam(required = false) LocalDate fechaInicio,
        @RequestParam(required = false) LocalDate fechaFin,
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) String estado,
        @RequestParam(required = false) Double cantidadMin,
        @RequestParam(required = false) Double cantidadMax) {

    // 1. Construir DTO filtro
    ReporteFiltroDTO filtro = new ReporteFiltroDTO();
    filtro.setFechaInicio(fechaInicio);
    filtro.setFechaFin(fechaFin);
    filtro.setCategoria(categoria);
    filtro.setEstado(estado);
    filtro.setCantidadMin(cantidadMin);
    filtro.setCantidadMax(cantidadMax);

    // 2. Obtener lista de reportes
    List<Reporte> reportes = reporteMultiCriterioService.generarReporte(filtro);

    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        com.lowagie.text.Document document = new com.lowagie.text.Document();
        com.lowagie.text.pdf.PdfWriter.getInstance(document, baos);
        document.open();

        // Título
        document.add(new com.lowagie.text.Paragraph("📊 Reporte de Actividades"));
        document.add(new com.lowagie.text.Paragraph("Fecha de generación: " + LocalDate.now()));
        document.add(new com.lowagie.text.Paragraph(" ")); // salto de línea

        // Tabla
        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(5);
        table.addCell("ID");
        table.addCell("Descripción");
        table.addCell("Categoría");
        table.addCell("Estado");
        table.addCell("Fecha");

        for (Reporte r : reportes) {
            table.addCell(r.getId().toString());
            table.addCell(r.getDescripcion() != null ? r.getDescripcion() : "-");
            table.addCell(r.getCategoria() != null ? r.getCategoria() : "-");
            table.addCell(r.getEstado() != null ? r.getEstado() : "-");
            table.addCell(r.getFecha() != null ? r.getFecha().toString() : "-");
        }

        document.add(table);
        document.close();

        byte[] pdfBytes = baos.toByteArray();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=reporte.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfBytes);

    } catch (Exception e) {
        return ResponseEntity.status(500).build();
    }
}

}
