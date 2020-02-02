/*
 * Desenvolvido pelo Departamento de Tecnologia da Informação - B ADM AP/CMO
 * Autor: 2º TEN Benites
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Classificação dos tipos de suprimento conforme 
 * PORTARIA NORMATIVA Nro 614/MD, de 24 de outubro de 2002, que dispõe sobre 
 * a Doutrina de Logística Militar.
 * @author benites
 */
public enum SupplyClassEnum {
    CLASSI("Classe I - Material de Subsistência"), 
    CLASSII("Classe II - Material de Intendência"), 
    CLASSIII("Classe III - Combustíveis e Lubrificantes"), 
    CLASSIII1("Classe III.1 - Gás GLP"), 
    CLASSIII2("Classe III.2 - Gasolina"), 
    CLASSIII3("Classe III.3 - Diesel"), 
    CLASSIV("Classe IV - Material de Construção"), 
    CLASSV1("Classe V.1 - Munição"), 
    CLASSV2("Classe V.2 - Armamento"), 
    CLASSVI("Classe VI - Material de Engenharia e de Cartografia"), 
    CLASSVI1("Classe VI.1 - Gerador"), 
    CLASSVI2("Classe VI.2 - GPS"), 
    CLASSVII("Classe VII - Material de Comunicações, Eletrônica e de Informática"), 
    CLASSVIII("Classe VIII - Material de Saúde"), 
    CLASSIX("Classe IX - Material Naval, de Motomecanização e de Aviação"),
    CLASSIX1("Classe IX.1 - Viatura"),
    CLASSIX2("Classe IX.2 - Embarcação"),
    CLASSIX3("Classe IX.3 - Motor de Popa"),
    CLASSX("Classe X - Materiais não incluídos nas demais classes"),
    SCLASS("Sem Classe definida -  Todas as classes");

    private String description;
   
    private SupplyClassEnum(String description){
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
