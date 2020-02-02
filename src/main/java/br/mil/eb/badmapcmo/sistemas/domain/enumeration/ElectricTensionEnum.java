/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 *
 * @author tenbenites
 */
public enum ElectricTensionEnum {
    V127("127Volts"), 
    V220("220Volts"),
    biVolt("127/220Volts");

    private String description;

    private ElectricTensionEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
