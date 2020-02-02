/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Diferentes status em que um item classe IX pode estar.
 * @author tenbenites
 */
public enum CIXStatusEnum {
    MNT("Em manutenção"),
    UNAVAILABLE("Indisponível"),
    AVAILABLE("Disponível");
   
   
    private String description;
    private CIXStatusEnum(String description){
      this.description = description;
    }
  
    public String getDescription() {
      return description;
    }
  
    public void setDescription(String description) {
      this.description = description;
    }

    
}
