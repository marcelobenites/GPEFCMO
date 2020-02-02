/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Os status que uma atividade operacional pode assumir durante seu ciclo de vida.
 * @author tenbenites
 */
public enum OperationalActivityStatusEnum {
    DONE("Realizada"),
    DOING("Em curso"),
    PLAN("Em planejamento");
    private String description;

    private OperationalActivityStatusEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
