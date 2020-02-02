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
public enum SewageSystemEnum {
    NET("Rede de esgoto"),
    CPOOL("Fossa sanitária");

    private String description;
   
    private SewageSystemEnum(String description){
      this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
