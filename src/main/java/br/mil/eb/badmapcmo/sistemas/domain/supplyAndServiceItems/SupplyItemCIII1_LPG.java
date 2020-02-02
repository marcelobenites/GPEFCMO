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
 * Gás Liquefeito de Petrólelo - GLP (Liquefied Petroleum Gas).
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_LPG")
@PrimaryKeyJoinColumn(name="id")
public class SupplyItemCIII1_LPG  extends SupplyItemCIII{
    @Column//Kg do botijão/cilindro
    private Float content;
    
    public Float getContent() {
        return content;
    }

    public void setContent(Float content) {
        this.content = content;
    }

    
    
    public SupplyItemCIII1_LPG() {
        super.setMetric_unit("Botijão/Cilindro");
        super.setName("Gás de Cozinha");
        
    }
    
    
    
    
    
}
