/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.*;
import java.time.Year;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Item de suprimento Classe IX - Material Naval, de Motomecanização e de Aviação.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_C9")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCIX extends SupplyItem {
    @Column//total esperado
    private Year year_of_manufacture;
    @Column//total em uso/disponível
    private String brand;
    @Column//providência
    private String providence;
   
    @Column
    @Enumerated(EnumType.STRING)
    private CIXStatusEnum status;

    @Column
    @Enumerated(EnumType.STRING)
    private CIXTypeEnum purpose;
    
   

    public Year getYear_of_manufacture() {
        return year_of_manufacture;
    }

    public void setYear_of_manufacture(Year year_of_manufacture) {
        this.year_of_manufacture = year_of_manufacture;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProvidence() {
        return providence;
    }

    public void setProvidence(String providence) {
        this.providence = providence;
    }

    public CIXStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CIXStatusEnum status) {
        this.status = status;
    }

    public CIXTypeEnum getPurpose() {
        return purpose;
    }

    public void setPurpose(CIXTypeEnum purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "SupplyItemC9{" + "year_of_manufacture=" + year_of_manufacture + ", brand=" + brand + ", providence=" + providence + ", status=" + status + ", purpose=" + purpose + '}';
    }


       
    
    
}
