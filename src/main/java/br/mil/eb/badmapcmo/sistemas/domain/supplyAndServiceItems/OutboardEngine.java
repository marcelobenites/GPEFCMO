/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_OUTBOARDMOTOR")
@PrimaryKeyJoinColumn(name="id")
public class OutboardEngine extends SupplyItemCIX{
    @Column//consumo
    private Float consumption;
    @Column//consumo
    private Float power;
    @Column//consumo
    private Float twoT_oil;

    public Float getConsumption() {
        return consumption;
    }

    public void setConsumption(Float consumption) {
        this.consumption = consumption;
    }

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
    }

    public Float getTwoT_oil() {
        return twoT_oil;
    }

    public void setTwoT_oil(Float twoT_oil) {
        this.twoT_oil = twoT_oil;
    }

    @Override
    public String toString() {
        return "OutboardEngine{" + "consumption=" + consumption + ", power=" + power + ", twoT_oil=" + twoT_oil + '}';
    }
    
    
}
