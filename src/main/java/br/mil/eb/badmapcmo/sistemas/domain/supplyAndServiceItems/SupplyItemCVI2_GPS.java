/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Material classe V.1 - Munição.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_CVI2_GPS")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCVI2_GPS extends SupplyItemCVI{
    @Column//quantidade prevista
    private Float expected_amount;
    @Column//quantidade existente
    private Float current_amount;

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

    @Override
    public String toString() {
        return "SupplyItemCVI2_GPS{" + "expected_amount=" + expected_amount + ", current_amount=" + current_amount + '}';
    }
    
    
    
}
