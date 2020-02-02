/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.ElectricTensionEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.ServiceCatalogEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Descrição de rede de energia de PEF.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SERVICE_ENERGYNET")
@PrimaryKeyJoinColumn(name="id")
public class SVC4_EnergyNet extends Service {
    @Column//Capacidade do Transformador VA
    private Float transformer_capacity;
    @Column//Consumo médio
    private Float average_consumption;
    @Column//Tempo de utilização (Horas)
    private Integer usage_time;
    @Column//Tempo de utilização
    private Integer phases;
    @Column//Cobertura
    private String coverage;
    @Column//Tensão
    @Enumerated(EnumType.STRING)
    private ElectricTensionEnum tension;

    public Float getTransformer_capacity() {
        return transformer_capacity;
    }

    public void setTransformer_capacity(Float transformer_capacity) {
        this.transformer_capacity = transformer_capacity;
    }

    public Float getAverage_consumption() {
        return average_consumption;
    }

    public void setAverage_consumption(Float average_consumption) {
        this.average_consumption = average_consumption;
    }

    public Integer getUsage_time() {
        return usage_time;
    }

    public void setUsage_time(Integer usage_time) {
        this.usage_time = usage_time;
    }

    public Integer getPhases() {
        return phases;
    }

    public void setPhases(Integer phases) {
        this.phases = phases;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public ElectricTensionEnum getTension() {
        return tension;
    }

    public void setTension(ElectricTensionEnum tension) {
        this.tension = tension;
    }

    public SVC4_EnergyNet() {
        super.setServiceType(ServiceCatalogEnum.SVC4);
    }

    @Override
    public String toString() {
        return "SVC4_EnergyNet{" + "transformer_capacity=" + transformer_capacity + ", average_consumption=" + average_consumption + ", usage_time=" + usage_time + ", phases=" + phases + ", coverage=" + coverage + ", tension=" + tension + '}';
    }
    
    
}
