package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */

/**
 * Item de suprimento Classe V - .
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_C5")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCV extends SupplyItem {
    @ManyToOne//qual sala de armas
    private StructureT3 armory;
    @Column//supre até
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate  up_to;   
    @Column//Segurança
    private String security;
    @Column//dotação
    private Float dot;     
    @Column//fabricante
    private String manufacturer;  

    public StructureT3 getArmory() {
        return armory;
    }

    public void setArmory(StructureT3 armory) {
        this.armory = armory;
    }

    public LocalDate getUp_to() {
        return up_to;
    }

    public void setUp_to(LocalDate up_to) {
        this.up_to = up_to;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public Float getDot() {
        return dot;
    }

    public void setDot(Float dot) {
        this.dot = dot;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "SupplyItemCV{" + "armory=" + armory + ", up_to=" + up_to + ", security=" + security + ", dot=" + dot + ", manufacturer=" + manufacturer + '}';
    }

    
}
