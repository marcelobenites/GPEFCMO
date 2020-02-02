/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Classificação de PNR quanto à categoria.
 * @author tenbenites
 */
public enum PNRCategoryEnum {
    FUNC("Funcional"), 
    NFUNC("Não funcional");

    private String description;

    private PNRCategoryEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
