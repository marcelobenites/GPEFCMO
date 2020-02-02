/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.operations;

import br.mil.eb.badmapcmo.sistemas.domain.Military;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Mapeamento de efetivo militar em cada atividade operacional.
 * @see OperationalActivity.java
 * @author tenbenites
 * @since beta 1.0
 * @version 1.0
 */
@Entity
@Table(name = "TBL_OPERATION_FORCE_MAP")
public class OperationForceMap implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //descrição da função do militar na operação
    @Column
    private String mil_role;
    //id da atividade operacional
    @ManyToOne
    private OperationalActivity operationalActivity;
    //id do militar que participa da operação
    @ManyToOne
    private Military military;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMil_role() {
        return mil_role;
    }

    public void setMil_role(String mil_role) {
        this.mil_role = mil_role;
    }

    public OperationalActivity getOperationalActivity() {
        return operationalActivity;
    }

    public void setOperationalActivity(OperationalActivity operationalActivity) {
        this.operationalActivity = operationalActivity;
    }

    public Military getMilitary() {
        return military;
    }

    public void setMilitary(Military military) {
        this.military = military;
    }

    public OperationForceMap() {
    }

    
    public OperationForceMap(Military military, OperationalActivity operationalActivity) {
        this.operationalActivity = operationalActivity;
        this.military = military;
    }
   
    
    @Override
    public String toString() {
        return "OperationForceMap{" + "id=" + id + ", milRole=" + mil_role + ", operationalActivity=" + operationalActivity + ", military=" + military + '}';
    }
      
    
}
