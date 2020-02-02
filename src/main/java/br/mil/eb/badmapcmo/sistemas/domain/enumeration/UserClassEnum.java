/*
 * Desenvolvido pelo Departamento de Tecnologia da Informação - B ADM AP/CMO
 * Autor: 2º TEN Benites
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;
import java.io.Serializable;
/**
 * Enum de classificação das possibilidades de acesso às informações sobre 
 * unidades militares cadastradas no sistema. Com excessão do administrador, 
 * os demais usuários têm acesso limitado às unidades subordinadas a suas 
 * unidades de origem.
 * @author benites
 */
public enum UserClassEnum implements Serializable {
    SUPERADMIN("Super administrador"),
    ADMIN("Administrador"),/*Pode adicionar ou remover usuários*/
    READ_WRITE("Usuário leitura e escrita"),
    READ_ONLY("Usuário apenas leitura");
    private String descricao;
   

    UserClassEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return super.toString();
    }
   
}
