/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import java.time.LocalDate;
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
 * Item de suprimento Classe VII - Material de Comunicações, Eletrônica e de Informática.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_C7")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCVII extends SupplyItem{
    
    @Column//ano de fabricação
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate  year_of_manufacture;
    
    @Column//numero de série
    private String serial;
         
    @Column//fabricante
    private String manufacturer;  
    
    @Column//Situação
    private String situation;  

    public LocalDate getYear_of_manufacture() {
        return year_of_manufacture;
    }

    public void setYear_of_manufacture(LocalDate year_of_manufacture) {
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

    @Override
    public String toString() {
        return "SupplyItemCVII{" + "year_of_manufacture=" + year_of_manufacture + ", serial=" + serial + ", manufacturer=" + manufacturer + ", situation=" + situation + '}';
    }
     
    
}
