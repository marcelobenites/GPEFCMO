/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Descrição de convênios para um PEF. [TODO:classse para remoção]
 * @author tenbenites
 * @deprecated 
 */
@Entity
@Table(name = "TBL_SERVICE_INSURANCE")
@PrimaryKeyJoinColumn(name="id")
public class SVC5_Insurance extends Service{
    
}
