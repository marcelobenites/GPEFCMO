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
 * Dados exclusivos sobre reserva de armamento.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_STRUCTURE_ARMORY")
@PrimaryKeyJoinColumn(name="id")
public class StructureT3 extends StructureT2{
 
    @Column//descrição dos tipos de armamento que podem ser estocados
    private String storage_weapon_types;
    @Column//descrição do mobiliário (armários, cabides, etc.) que estão na reserva
    private String storage_furniture;

   
    public String getStorage_weapon_types() {
        return storage_weapon_types;
    }

    public void setStorage_weapon_types(String storage_weapon_types) {
        this.storage_weapon_types = storage_weapon_types;
    }

    public String getStorage_furniture() {
        return storage_furniture;
    }

    public void setStorage_furniture(String storage_furniture) {
        this.storage_furniture = storage_furniture;
    }

    @Override
    public String toString() {
        return "StructureT3{" + "storage_weapon_types=" + storage_weapon_types + ", storage_furniture=" + storage_furniture + '}';
    }

    
    
}
