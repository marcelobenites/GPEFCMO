/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 *
 * @author tenbenites
 */
public enum StatesEnum {
   AC("Acre","AC"),
   AL("Alagoas","AL"),
   AP("Amapá","AP"), 	
   AM("Amazonas","AM"),
   BA("Bahia","BA"),
   CE("Ceará","CE"),
   DF("Distrito Federal","DF"),
   ES("Espírito Santo","ES"),
   GO("Goiás","GO"),
   MA("Maranhão","MA"),
   MT("Mato Grosso","MT"),
   MS("Mato Grosso do Sul","MS"),
   MG("Minas Gerais","MG"),
   PA("Pará","PA"),
   PB("Paraíba","PB"),
   PR("Paraná","PR"),
   PE("Pernambuco","PE"),
   PI("Piauí","PI"),
   RG("Rio de Janeiro","RJ"),
   RN("Rio Grande do Norte","RN"),
   RS("Rio Grande do Sul","RS"),
   RO("Rondônia","RO"),
   RR("Roraima","RR"),
   SC("Santa Catarina","SC"),
   SP("São Paulo","SP"),
   SE("Sergipe","SE"),
   TO("Tocantins","TO");
   
   
    private String description;
    private String abbreviation;
    private StatesEnum(String description,String abbreviation){
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
