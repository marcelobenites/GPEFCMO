/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por STI (Seção de Tecnologia da Informação)
 *  2019 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.zoneInfo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de informações sobre município.
 * @author ten-benites
 */
@Entity
@Table(name = "TBL_ZONE_AERODROME")
public class Aerodrome implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    //Homologado
    @Column
    private Boolean homologated;
    //Controlado
    @Column
    private Boolean controlled;
    //Internacional
    @Column
    private Boolean international;
    //Militar
    @Column
    private Boolean military_kind;
    //Público
    @Column
    private Boolean public_kind;
    //Abastecimento AVGÁS
    @Column
    private Boolean av_supply;
    //Abastecimento JET A-1
    @Column
    private Boolean jeta1_supply;
    //Balizamento Noturno
    @Column
    private Boolean night_beacon;
    //Heliponto
    @Column
    private Boolean helipad;
    //Pista > 1.000 m
    @Column
    private Boolean airstrip;
    //Pavimentada
    @Column
    private Boolean pavemented;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getHomologated() {
        return homologated;
    }

    public void setHomologated(Boolean homologated) {
        this.homologated = homologated;
    }

    public Boolean getControlled() {
        return controlled;
    }

    public void setControlled(Boolean controlled) {
        this.controlled = controlled;
    }

    public Boolean getInternational() {
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }

    public Boolean getMilitary_kind() {
        return military_kind;
    }

    public void setMilitary_kind(Boolean military_kind) {
        this.military_kind = military_kind;
    }

    public Boolean getPublic_kind() {
        return public_kind;
    }

    public void setPublic_kind(Boolean public_kind) {
        this.public_kind = public_kind;
    }

    public Boolean getAv_supply() {
        return av_supply;
    }

    public void setAv_supply(Boolean av_supply) {
        this.av_supply = av_supply;
    }

    public Boolean getJeta1_supply() {
        return jeta1_supply;
    }

    public void setJeta1_supply(Boolean jeta1_supply) {
        this.jeta1_supply = jeta1_supply;
    }

    public Boolean getNight_beacon() {
        return night_beacon;
    }

    public void setNight_beacon(Boolean night_beacon) {
        this.night_beacon = night_beacon;
    }

    public Boolean getHelipad() {
        return helipad;
    }

    public void setHelipad(Boolean helipad) {
        this.helipad = helipad;
    }

    public Boolean getAirstrip() {
        return airstrip;
    }

    public void setAirstrip(Boolean airstrip) {
        this.airstrip = airstrip;
    }

    public Boolean getPavemented() {
        return pavemented;
    }

    public void setPavemented(Boolean pavemented) {
        this.pavemented = pavemented;
    }

    @Override
    public String toString() {
        return "Aerodrome{" + "id=" + id + ", homologated=" + homologated + ", controlled=" + controlled + ", international=" + international + ", military_kind=" + military_kind + ", public_kind=" + public_kind + ", av_supply=" + av_supply + ", jeta1_supply=" + jeta1_supply + ", night_beacon=" + night_beacon + ", helipad=" + helipad + ", airstrip=" + airstrip + ", pavemented=" + pavemented + '}';
    }
    
}
