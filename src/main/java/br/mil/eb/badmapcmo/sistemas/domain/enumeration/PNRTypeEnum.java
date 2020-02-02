/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Classificação de PNR quanto ao tipo.
 * @author tenbenites
 */
public enum PNRTypeEnum {
    OFGEN("De oficial-general"), 
    OFSUP("De oficial superior"), 
    CAPTEN("De capitão e tenente"), 
    STSGT("De subtenente e sargento"), 
    CBTFSD("De cabo, taifeiro e soldado");

    private String description;

    private PNRTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
