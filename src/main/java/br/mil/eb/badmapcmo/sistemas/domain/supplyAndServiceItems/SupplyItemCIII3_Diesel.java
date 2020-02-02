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
 * Item de suprimento Classe III.3 - Diesel
 * @author tenbenites
 */

@Entity
@Table(name = "TBL_SUPPLY_DIESEL")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCIII3_Diesel extends SupplyItemCIII{
    
    @Column//tipo de estocagem
    private String storage_type;

    public String getStorage_type() {
        return storage_type;
    }

    public void setStorage_type(String storage_type) {
        this.storage_type = storage_type;
    }

    @Override
    public String toString() {
        return "SupplyItemCIII3_Diesel{" + "storage_type=" + storage_type + '}';
    }
    
    
}
