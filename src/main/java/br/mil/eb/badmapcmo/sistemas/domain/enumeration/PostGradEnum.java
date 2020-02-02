/*
 * Desenvolvido pelo Departamento de Tecnologia da Informação - B ADM AP/CMO
 * Autor: 2º TEN Benites
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Posto/graduação do militar.
 * @author benites
 */
public enum PostGradEnum {
    SOLDADOEV("Soldado Efetivo Variável", "Sd EV"),
    SOLDADOEP("Soldado Efetivo Profissional", "Sd EP"),
    CABO("Cabo", "Cb"),
    SARGENTO3("3º Sargento","3º Sgt"),
    SARGENTO2("2º Sargento","2º Sgt"),
    SARGENTO1("1º Sargento","1º Sgt"),
    SUBTENENTE("Subtenente","S Ten"),
    ASPIRANTEOF("Aspirante a Oficial", "Asp"),
    TENENTE2("2º Tenente","2º Ten"),
    TENENTE1("1º Tenente","1º Ten"),
    CAPITAO("Capitão","Cap"),
    MAJOR("Major","Maj"),
    TENCORONEL("Tenente Coronel","Ten Cel"),
    CORONEL("Coronel","Cel"),
    GENERALBDA("General de Brigada","Gen Bda"),
    GENERALDIV("General de Divisão","Gen Div"),
    GENERALEX("General de Exército","Gen Ex"),
    MARECHAL("Marechal","Mar");
    private String description;
    private String abbreviation;
    private PostGradEnum(String description,String abbreviation){
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
