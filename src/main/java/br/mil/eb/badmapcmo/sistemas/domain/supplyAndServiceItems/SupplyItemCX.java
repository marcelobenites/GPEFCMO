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
 * Item de suprimento Classe I.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_C10")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCX extends SupplyItem{
    
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate  up_to;
    
    @Column//suprido até
    private Float expected_amount;
    @Column//quantidade existente
    private Float current_amount;
    //TODO colocar opção de selecionar unidades de medida em uma lista
   
  
    //qual a estrutura de armazenagem do item
    @ManyToOne
    private StructureT2 storage;

    

    public LocalDate getUp_to() {
        return up_to;
    }

    public void setUp_to(LocalDate up_to) {
        this.up_to = up_to;
    }
   

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

    

    public StructureT2 getStorage() {
        return storage;
    }

    public void setStorage(StructureT2 storage) {
        this.storage = storage;
    }

    
    
    
    
}
