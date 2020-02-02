/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Item de suprimento Classe III - Combustíveis e Lubrificantes.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_C3")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCIII extends SupplyItem {
    @Column//total esperado
    private Float expected_amount;
    @Column//total em uso/disponível
    private Float current_amount;
    @Column//disponibilidade do material
    private Float deficit;
    @Column//situação
    private String situation;
    @ManyToOne//qual sala de armas está armazenada
    private StructureT2 storage;

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

    public Float getDeficit() {
        return deficit;
    }

    public void setDeficit(Float deficit) {
        this.deficit = deficit;
    }

    public StructureT2 getStorage() {
        return storage;
    }

    public void setStorage(StructureT2 storage) {
        this.storage = storage;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "SupplyItemCIII{" + "expected_amount=" + expected_amount + ", current_amount=" + current_amount + ", deficit=" + deficit + ", situation=" + situation + ", storage=" + storage + '}';
    }

    
    
    
 
}
