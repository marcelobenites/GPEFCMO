/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.auxiliary;

/**
 * Classe transiente para auxiliar no registro de dependências entre unidades na 
 * entidade MUDependencies
 * @see OperationForceMap
 * @author tenbenites
 * @since beta 1.0
 * @version 1.0
 */
public class ForceMap {
    //papel do militar na operação
    private String mil_role;
    //id do militar que participa da atividade
    private Long mil_id;
    //id da atividade operacional
    private Long opa_id;

    public String getMil_role() {
        return mil_role;
    }

    public void setMil_role(String mil_role) {
        this.mil_role = mil_role;
    }

    public Long getMil_id() {
        return mil_id;
    }

    public void setMil_id(Long mil_id) {
        this.mil_id = mil_id;
    }

    public Long getOpa_id() {
        return opa_id;
    }

    public void setOpa_id(Long opa_id) {
        this.opa_id = opa_id;
    }

    @Override
    public String toString() {
        return "ForceMap{" + "mil_role=" + mil_role + ", mil_id=" + mil_id + ", opa_id=" + opa_id + '}';
    }
    
    
}
