/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SUPPLY_WATERCRAFT")
@PrimaryKeyJoinColumn(name="id")
public class Watercraft extends SupplyItemCIX{

    @Column//total qtd de passageiros
    private Integer amount_of_passangers;
    @Column//Capacidade de carga
    private Float  capacity ;
//    Tipo
//Nome
//Fabricação (ano)
//Motor
//Carga (capacidade)
//Qtd passageiros
//Situação
//Motivo
//Providência
//Foto
//Observação

    public Integer getAmount_of_passangers() {
        return amount_of_passangers;
    }

    public void setAmount_of_passangers(Integer amount_of_passangers) {
        this.amount_of_passangers = amount_of_passangers;
    }

    public Float getCapacity() {
        return capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Watercraft{" + "amount_of_passangers=" + amount_of_passangers + ", capacity=" + capacity + '}';
    }

}
