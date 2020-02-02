/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Categorias possíveis de comando atribuídas a um comandante.
 * @author tenbenites
 */
public enum CommandCategoryEnum {
    CMD("Comandante", "Cmt"),
    SCMD("Subcomandante", "SCmt");
    private String description;
    private String abbreviation;
    private CommandCategoryEnum(String description,String abbreviation){
      this.description = description;
      this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    
    
}
