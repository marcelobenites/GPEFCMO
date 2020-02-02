/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Enum geral para marcação de disponibilidades, para elementos com mais opções 
 * de status Enums específicos foram criados.
 * @author tenbenites
 */
public enum GeneralAvailabilityEnum {
    AVAILABLE("Disponível"),
    OCCUPIED("Ocupado"),
    FREE("Desocupado"),
    UNAVAILABLE("Indisponível");
    private String description;
    private GeneralAvailabilityEnum(String description){
      this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
