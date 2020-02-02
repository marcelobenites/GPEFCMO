/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Tipos de combustível.
 * @author tenbenites
 */
public enum CIXFuelEnum {
    DIESEL("Diesel"),
    GLV("GLV"),
    GASOLINE("Gasolina"),
    FLEX("Etanol/Gasolina"),
    KEROSENE("Querosene"),
    ETHANOL("Etanol");
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private CIXFuelEnum(String description) {
        this.description = description;
    }
}
