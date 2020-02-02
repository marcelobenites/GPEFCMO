/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Classificação de PNR quanto a natureza.
 * @author tenbenites
 */
public enum PNRKindEnum {
    SINGLEHOUSE("Casa isolada"), 
    VILLAGEHOUSE("Casa em vila militar"), 
    APARTMENT("Apartamento");

    private String description;

    private PNRKindEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
