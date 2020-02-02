/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_STRUCTURE_STORAGE")
@PrimaryKeyJoinColumn(name="id")
public class StructureT2 extends StructuralItem {
    
    @Column//Capacidade, incluindo unidade de medida variável
    private String storage_capacity;
    @Column//Descrição do tipo de estocagem
    private String type;   
   

   
    public String getStorage_capacity() {
        return storage_capacity;
    }

    public void setStorage_capacity(String storage_capacity) {
        this.storage_capacity = storage_capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StorageStructure{" + "storage_capacity=" + storage_capacity + ", type=" + type + '}';
    }

   

    

    

    
}
