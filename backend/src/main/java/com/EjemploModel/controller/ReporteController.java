package com.EjemploModel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.EjemploModel.model.Reporte;
import com.EjemploModel.model.ReporteFiltroDTO;
import com.EjemploModel.service.ReporteMultiCriterioService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteMultiCriterioService reporteService;

    @GetMapping
    public ResponseEntity<List<Reporte>> obtenerReporte(
        @RequestParam(required = false) LocalDate fechaInicio,
        @RequestParam(required = false) LocalDate fechaFin,
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) String estado,
        @RequestParam(required = false) Double cantidadMin,
        @RequestParam(required = false) Double cantidadMax
    ) {
        // Crear DTO manualmente
        ReporteFiltroDTO filtro = new ReporteFiltroDTO();
        filtro.setFechaInicio(fechaInicio);
        filtro.setFechaFin(fechaFin);
        filtro.setCategoria(categoria);
        filtro.setEstado(estado);
        filtro.setCantidadMin(cantidadMin);
        filtro.setCantidadMax(cantidadMax);

        List<Reporte> reportes = reporteService.generarReporte(filtro);
        return ResponseEntity.ok(reportes);
    }
}
