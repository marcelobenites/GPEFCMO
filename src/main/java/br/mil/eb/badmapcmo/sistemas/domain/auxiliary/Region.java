/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.auxiliary;

import br.mil.eb.badmapcmo.sistemas.domain.Zone;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe auxiliar transiente que mapeia uma região para uma unidade.
 * @author tenbenites
 */
public class Region {
     //Registro de ids de municípios abrangidos pela unidade   
    //tipo de dependencia que pode ocorrer entre duas unidades, recebido de um enum
    Boolean included;
    //id da unidade que se relaciona com a unidade em adição/edição
    Long zone_id;

    public Boolean getIncluded() {
        return included;
    }

    public void setIncluded(Boolean included) {
        this.included = included;
    }

    public Long getZone_id() {
        return zone_id;
    }

    public void setZone_id(Long zone_id) {
        this.zone_id = zone_id;
    }

    @Override
    public String toString() {
        return "Region{" + "included=" + included + ", mu_id=" + zone_id + '}';
    }

    
    
    
}
