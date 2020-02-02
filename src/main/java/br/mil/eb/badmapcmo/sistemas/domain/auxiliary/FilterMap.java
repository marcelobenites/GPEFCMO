/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.auxiliary;

import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe auxiliar não persistida no banco que auxilia na aplicação de filtros
 * no mapa principal do sistema.
 * @author tenbenites
 */
public class FilterMap {
    //Unidade militar a ser mostrada no mapa
    private MilitaryUnit militaryUnit;
   
    //Mostrar operações em planejamento?
    private Boolean showPlannedOA;
    //Mostrar operações em andamento?
    private Boolean showDoingOA;
    //Mostrar operações em planejamento?
    private Boolean showDoneOA;
    //Mostrar operações em planejamento?
    private Boolean showPELs;
    //Mostrar unidades militares?
    private Boolean showMUs;
    //Data de início
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate start_date;
    //Data de término
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate finish_date;
    //horário de início
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime start_time;
    //horário de término
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime finish_time;
    
       
    public MilitaryUnit getMilitaryUnit() {
        return militaryUnit;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public Boolean getShowPlannedOA() {
        return showPlannedOA;
    }

    public void setShowPlannedOA(Boolean showPlannedOA) {
        this.showPlannedOA = showPlannedOA;
    }

    public Boolean getShowDoingOA() {
        return showDoingOA;
    }

    public void setShowDoingOA(Boolean showDoingOA) {
        this.showDoingOA = showDoingOA;
    }

    public Boolean getShowDoneOA() {
        return showDoneOA;
    }

    public void setShowDoneOA(Boolean showDoneOA) {
        this.showDoneOA = showDoneOA;
    }

    public Boolean getShowPELs() {
        return showPELs;
    }

    public void setShowPELs(Boolean showPELs) {
        this.showPELs = showPELs;
    }

    public Boolean getShowMUs() {
        return showMUs;
    }

    public void setShowMUs(Boolean showMUs) {
        this.showMUs = showMUs;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(LocalDate finish_date) {
        this.finish_date = finish_date;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(LocalTime finish_time) {
        this.finish_time = finish_time;
    }

    public FilterMap() {
        this.militaryUnit = null;
        this.showPlannedOA = null;
        this.showDoingOA = null;
        this.showDoneOA = null;
        this.showPELs = null;
        this.showMUs = null;
        this.start_date = null;
        this.finish_date = null;
        this.start_time = null;
        this.finish_time = null;
    }

    
    public FilterMap(MilitaryUnit militaryUnit, Boolean showPlannedOA, Boolean showDoingOA, Boolean showDoneOA, Boolean showPELs, Boolean showMUs, LocalDate start_date, LocalDate finish_date, LocalTime start_time, LocalTime finish_time) {
        this.militaryUnit = militaryUnit;
        this.showPlannedOA = showPlannedOA;
        this.showDoingOA = showDoingOA;
        this.showDoneOA = showDoneOA;
        this.showPELs = showPELs;
        this.showMUs = showMUs;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.start_time = start_time;
        this.finish_time = finish_time;
    }
    
    
    public FilterMap(Boolean showPlannedOA, Boolean showDoingOA, Boolean showDoneOA, Boolean showPELs, Boolean showMUs) {
        this.showPlannedOA = showPlannedOA;
        this.showDoingOA = showDoingOA;
        this.showDoneOA = showDoneOA;
        this.showPELs = showPELs;
        this.showMUs = showMUs;
        this.militaryUnit = null;
    }

    @Override
    public String toString() {
        return "FilterMap{" + "militaryUnit=" + militaryUnit + ", showPlannedOA=" + showPlannedOA + ", showDoingOA=" + showDoingOA + ", showDoneOA=" + showDoneOA + ", showPELs=" + showPELs + ", showMUs=" + showMUs + ", start_date=" + start_date + ", finish_date=" + finish_date + ", start_time=" + start_time + ", finish_time=" + finish_time + '}';
    }
 
    
    
    
}
