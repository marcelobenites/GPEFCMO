/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Diferentes tipos/propósito para um item classe IX.
 * @author tenbenites
 */
public enum CIXTypeEnum {
    ADM("Administrativo"),
    OP("Operacional");
   
   
    private String description;
    private CIXTypeEnum(String description){
      this.description = description;
    }
  
    public String getDescription() {
      return description;
    }
  
    public void setDescription(String description) {
      this.description = description;
    }

    
}