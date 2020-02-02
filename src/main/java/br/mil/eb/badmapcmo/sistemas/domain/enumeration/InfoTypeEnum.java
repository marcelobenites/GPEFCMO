/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.domain.enumeration;

/**
 * Tipos de informação genérica cadastrada no sistema 
 * Esta classificação é utilizada por várias entidades
 * @see MilitaryUnit
 * @author tenbenites
 */
public enum InfoTypeEnum {
    HISTORY("História"),
    NEWS("Notícia");
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private InfoTypeEnum(String description) {
        this.description = description;
    }
    
}
