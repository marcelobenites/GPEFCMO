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
@Table(name = "TBL_SUPPLY_CV1_MUNITION")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCV1_munition extends SupplyItemCV{
    @Column//suprido até
    private Float expected_amount;
    @Column//quantidade existente
    private Float current_amount;   
    @ManyToOne//qual sala de armas está armazenada
    private StructureT3 armory;
    @Column//validade/obsolescencia
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate shelf_life;  
    
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

    public StructureT3 getArmory() {
        return armory;
    }

    public void setArmory(StructureT3 armory) {
        this.armory = armory;
    }

    public LocalDate getShelf_life() {
        return shelf_life;
    }

    public void setShelf_life(LocalDate shelf_life) {
        this.shelf_life = shelf_life;
    }

    @Override
    public String toString() {
        return "SupplyItemCV1_munition{" + "expected_amount=" + expected_amount + ", current_amount=" + current_amount + ", armory=" + armory + ", shelf_life=" + shelf_life + '}';
    }

    
}
