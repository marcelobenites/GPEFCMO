/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.CIXStatusEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.SupplyClassEnum;
import java.time.Year;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Armamento.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_CV_WEAPONRY")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCV2_weaponry  extends SupplyItemCV{
    
    @Column//disponibilidade
    @Enumerated(EnumType.STRING)
    private CIXStatusEnum status;    
    @Column//suprido até
    private String weapon_type;
    @Column//modelo
    private String model;
    @Column//numero de série
    private String serial;
    @Column//ano fabricação
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Year year_of_manufacture;

    public CIXStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CIXStatusEnum status) {
        this.status = status;
    }

    public String getWeapon_type() {
        return weapon_type;
    }

    public void setWeapon_type(String weapon_type) {
        this.weapon_type = weapon_type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Year getYear_of_manufacture() {
        return year_of_manufacture;
    }

    public void setYear_of_manufacture(Year year_of_manufacture) {
        this.year_of_manufacture = year_of_manufacture;
    }

    @Override
    public String toString() {
        return "SupplyItemCV2_weaponry{" + "status=" + status + ", type=" + weapon_type + ", model=" + model + ", serial=" + serial + ", year_of_manufacture=" + year_of_manufacture + '}';
    }

    
    
    
}
