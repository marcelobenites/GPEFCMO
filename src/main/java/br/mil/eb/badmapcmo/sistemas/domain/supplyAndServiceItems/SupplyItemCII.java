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
 * Item de suprimento Classe II.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_C2")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCII extends SupplyItem{
    @Column//suprido até
    private Float expected_amount;
    @Column//quantidade existente
    private Float current_amount;
    @Column//disponibilidade do material
    private Boolean availability;

    public Float getExpected_amount() {
        return expected_amount;
    }

    public void setExpected_amount(Float expected_amount) {
        this.expected_amount = expected_amount;
    }

    public Float getCurrent_amount() {
        return current_amount;
    }

    public void setCurrent_amount(Float current_amount) {
        this.current_amount = current_amount;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "SupplyItemC2{" + "expected_amount=" + expected_amount + ", current_amount=" + current_amount + ", availability=" + availability + '}';
    }
    
    
}
