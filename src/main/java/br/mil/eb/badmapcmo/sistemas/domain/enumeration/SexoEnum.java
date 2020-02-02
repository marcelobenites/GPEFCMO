/*
 * Desenvolvido pelo Departamento de Tecnologia da Informação - B ADM AP/CMO
 * Autor: 2º TEN Benites
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Sexo do militar.
 * @author benites
 */
public enum SexoEnum {
	FEMININO('F'), MASCULINO('M');

    private char descricao;

    SexoEnum(char descricao) {
        this.descricao = descricao;
    }

    public char getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
