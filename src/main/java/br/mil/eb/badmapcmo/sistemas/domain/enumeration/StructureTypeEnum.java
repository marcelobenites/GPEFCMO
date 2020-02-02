/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Classificação dos tipos de suprimento conforme 
 * PORTARIA NORMATIVA Nro 614/MD, de 24 de outubro de 2002, que dispõe sobre 
 * a Doutrina de Logística Militar.
 * @author benites
 */
public enum StructureTypeEnum {
    TYPE1("PNR"),
    TYPE2("Estocagem"),
    TYPE3("Sala de Armas"),
    TYPE4("Outras Instalações");
    
    
    private String description;
   
    private StructureTypeEnum(String description){
      this.description = description;
    }
  
    public String getDescription() {
      return description;
    }
  
    public void setDescription(String description) {
      this.description = description;
    }

    @Override
    public String toString() {
        return "StructureTypeEnum{" + "description=" + description + '}';
    }

   
    
}
