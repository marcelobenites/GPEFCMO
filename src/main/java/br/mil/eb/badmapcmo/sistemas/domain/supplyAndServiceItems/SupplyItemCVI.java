/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Item de suprimento Classe IV.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_C6")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCVI extends SupplyItem{
    
    @Column//ano fabricação
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Year year_of_manufacture;
    
    @Column//numero de série
    private String serial;
         
    @Column//fabricante
    private String manufacturer;  

    @Column//situação
    private String situation;
    
    @Column//motivo
    private String reason;
    
    @Column//providência
    private String response_action;
    
    @Column//quantidade
    private Integer amount;

    public Year getYear_of_manufacture() {
        return year_of_manufacture;
    }

    public void setYear_of_manufacture(Year year_of_manufacture) {
        this.year_of_manufacture = year_of_manufacture;
    }
    
    
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResponse_action() {
        return response_action;
    }

    public void setResponse_action(String response_action) {
        this.response_action = response_action;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SupplyItemCVI{" + "year_of_manufacture=" + year_of_manufacture + ", serial=" + serial + ", manufacturer=" + manufacturer + ", situation=" + situation + ", reason=" + reason + ", response_action=" + response_action + ", amount=" + amount + '}';
    }

    
    
}
