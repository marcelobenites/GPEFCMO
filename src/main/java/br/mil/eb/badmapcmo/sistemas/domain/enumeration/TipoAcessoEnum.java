/*
 * Desenvolvido pelo Departamento de Tecnologia da Informação - B ADM AP/CMO
 * Autor: 2º TEN Benites
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Enum de classificação das possibilidades de acesso às informações sobre 
 * unidades militares cadastradas no sistema. Com excessão do administrador, 
 * os demais usuários têm acesso limitado às unidades subordinadas a suas 
 * unidades de origem.
 * @author benites
 */
public enum TipoAcessoEnum {
    READONLY("Acesso de leitura"),//Pode visualizar informações das unidades
    WRITEONLY("Acesso de escrita"),//Pode visualizar/acrescentar/modificar informações sobre as unidades
    ADMIN("Acesso de administrador"),//Pode manipular informações sobre unidades e gerenciar usuários leitura/escrita
    SUPERADMIN("Acesso de administrador/desenvolvedor");//Pode editar unidades e usuários sem restrições
    private String descricao;
   
    private TipoAcessoEnum(String descricao){
      this.descricao = descricao;
    }
  
    public String getDescricao() {
      return descricao;
    }
  
    public void setDescricao(String descricao) {
      this.descricao = descricao;
    }
}
