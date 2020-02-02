/*
 * Desenvolvido pelo Departamento de Tecnologia da Informação - B ADM AP/CMO
 * Autor: 2º TEN Benites
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Indica a situação de permanência de um militar em relação a uma unidade.
 * @author benites
 */
public enum SituacaoPermaneciaEnum {
    ATIVO("General de Divisão"),
    FINALIZADO("General de Exército"),
    BAIXADO("Afastado temporariamente");
    
    private String descricao;
    private SituacaoPermaneciaEnum(String descricao){
      this.descricao = descricao;
    }
  
    public String getDescricao() {
      return descricao;
    }
  
    public void setDescricao(String descricao) {
      this.descricao = descricao;
    }
}
