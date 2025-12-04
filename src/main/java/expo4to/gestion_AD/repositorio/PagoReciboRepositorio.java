package expo4to.gestion_AD.repositorio;

import expo4to.gestion_AD.modelo.DetallesPago;
import expo4to.gestion_AD.modelo.PagoRecibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagoReciboRepositorio extends JpaRepository<PagoRecibo, Integer> {

    // Buscamos detalles que sean Inscripciones
    @Query(value = "SELECT COUNT(dp.id) FROM detalles_pagos dp " +
            "INNER JOIN pagos_recibos pr ON dp.id_pago_recibo = pr.id " +
            "WHERE pr.id_estudiante = :estudianteId " +
            "AND dp.id_tipo_pago = :tipoPagoId " +
            "AND dp.id_ano_escolar = :anoEscolarId " +
            "AND dp.estado = TRUE",
            nativeQuery = true)
    long contarInscripcionesPagadas(@Param("estudianteId") Integer estudianteId,
                                    @Param("tipoPagoId") Integer tipoPagoId,
                                    @Param("anoEscolarId") Integer anoEscolarId);

    // Buscamos detalles que sean mensualidades
    @Query("SELECT d FROM PagoRecibo pr JOIN pr.detalles d " + // ⬅️ INICIA DESDE PagoRecibo y une Detalles
            "WHERE pr.estudiante.id = :estudianteId " +
            "AND d.tipoPago.id = 1 " + // ⬅️ ID del Tipo Pago Mensualidad (verificar este ID)
            "AND d.anoEscolar.id = :anoEscolarId " +
            "AND d.mesCorrespondiente IS NOT NULL " +
            "ORDER BY d.mesCorrespondiente ASC")
    List<DetallesPago> findMensualidadesPorEstudianteYAno(
            @Param("estudianteId") Integer estudianteId,
            @Param("anoEscolarId") Integer anoEscolarId
    );

    List<PagoRecibo> findByEstudianteId(Integer idEstudiante);
}
