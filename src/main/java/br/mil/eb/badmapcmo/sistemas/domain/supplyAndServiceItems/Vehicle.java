/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.CIXFuelEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.PostGradEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Dados exclusivos sobre viaturas.
 * @author ten-benites
 */
@Entity
@Table(name = "TBL_SUPPLY_VEHICLE")
@PrimaryKeyJoinColumn(name="id")
public class Vehicle extends SupplyItemCIX{
    //QUESTION_FOR_CLIENT: o catálogo de viaturas será abastecido pelos pefs ou pelas unidades superiores proprietárias das viaturas??
    //Será feira um sistema de reservas/designação pelos PEFs??
    //A foto é atualizada pelos PEFs ou fornecida pelas unidades superiores??
    //MESMAS QUESTÕES PARA EMBARCAÇÕES E MOTORES DE POPA
    
    
    @Column//placa da viatura
    private String board;
    @Column//identificação EB
    private String identification;
    @Column
    @Enumerated(EnumType.STRING)
    private CIXFuelEnum fuel;

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public CIXFuelEnum getFuel() {
        return fuel;
    }

    public void setFuel(CIXFuelEnum fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "board=" + board + ", identification=" + identification + ", fuel=" + fuel + '}';
    }
    
    
   
}
