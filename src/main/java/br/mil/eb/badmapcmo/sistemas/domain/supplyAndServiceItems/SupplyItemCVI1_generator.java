/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.CIXFuelEnum;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Material classe V.1 - Munição.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_CVI1_GENERATOR")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCVI1_generator extends SupplyItemCVI{
        
    @Column//potencia
    private Float power;
    
    
    @Column//combustível
    @Enumerated(EnumType.STRING)
    private CIXFuelEnum fuel;

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
    }

    public CIXFuelEnum getFuel() {
        return fuel;
    }

    public void setFuel(CIXFuelEnum fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "SupplyItemCVI1_generator{" + "power=" + power + ", fuel=" + fuel + '}';
    }
    
}
