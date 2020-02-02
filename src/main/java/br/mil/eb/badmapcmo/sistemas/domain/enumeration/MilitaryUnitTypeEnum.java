/*
 * Desenvolvido pelo Departamento de Tecnologia da Informação - B ADM AP/CMO
 * Autor: 2º TEN Benites
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Classificação das unidades militares cadastradas no sistema.
 * @author benites
 */
public enum MilitaryUnitTypeEnum {
    CMDMIL("Comando militar"),
    CCOP("Centro de Coordenação de Operações"),
    BDACMEC("Brigada de Cavalaria Mecanizada"),
    BDAINFMTZ("Brigada de Infantaria Motorizada"),
    BDAINFFRON("Brigada de Infantaria de Fronteira"),
    RCMEC("Regimento de Cavalaria Mecanizado"),
    CED("Comando de Elemento Destacado"),
    BFRON("Batalhão de Fronteira"),
    CIAFRON("Companhia de Fronteira"),
    PELD("Pelotão Destacado"),
    ESQD("Esquadrão Destacado"),
    PEF("Pelotão Especial de Fronteira");
    private String descricao;
   
    private MilitaryUnitTypeEnum(String descricao){
      this.descricao = descricao;
    }
  
    public String getDescricao() {
      return descricao;
    }
  
    public void setDescricao(String descricao) {
      this.descricao = descricao;
    }
}
