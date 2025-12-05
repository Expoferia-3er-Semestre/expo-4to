package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.PeriodoAcademico;

import java.util.List;

public interface IPeriodosAcademicosServicio {

    List<PeriodoAcademico> listarPeriodosAcademicos(Integer idPeriodoAcademico);

    void guardarPeriodoAcademico(PeriodoAcademico periodoAcademico);

}
