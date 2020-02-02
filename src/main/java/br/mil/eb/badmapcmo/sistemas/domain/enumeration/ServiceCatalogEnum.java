/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Catálogo de serviços disponíveis para cadastro em pelotão.
 * @author tenbenites
 */
public enum ServiceCatalogEnum {
    SVC1("Outros Serviços"),
    SVC2("Água/Esgoto"),
    SVC3("Coleta de Lixo"),
    SVC4("Energia Elétrica"),
    SVC5("Convênios");
    
    private String description;
   
    private ServiceCatalogEnum(String description){
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
        return this.name();
    }
}
