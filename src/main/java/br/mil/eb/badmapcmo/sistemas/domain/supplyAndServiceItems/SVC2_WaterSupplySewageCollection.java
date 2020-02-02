/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.ServiceCatalogEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.SewageSystemEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Dados sobre coleta de esgoto
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SERVICE_WATER_SEWAGE")
@PrimaryKeyJoinColumn(name="id")
public class SVC2_WaterSupplySewageCollection extends Service{
    //Fornecimento de água
    @Column//Origem captação
    private String water_origin;
    @Column//Devolução
    private String water_exit;
    @Column//Equipamento utilizado
    private String water_equipment;
    @Column//Tratamento
    private String water_treatment;
    @Column//Capacidade de armazenagem
    private Float water_capacity;
    @Column//Utilização
    private String water_use;
    @Column//Devolução
    private Float water_consumption;
    @Column//Distancia origem/armazenagem
    private Float water_distance;
    
    
    //Fornecimento de esgoto
    @Column//Residências atendidas
    private Integer sewage_served_homes;
    @Column//Sistema utilizado(Rede/fossa)
    @Enumerated(EnumType.STRING)
    private SewageSystemEnum sewage_serviceType;
    @Column//Tratamento
    private String sewage_treatment;
    @Column//Armazenagem
    private String sewage_storage;
    @Column//Frequência de Manutenção
    private Integer sewage_maintainment_frequence;
    @Column//Devolução ao ambiente (Sem tratamento / líquido / sólido / nada)
    private String sewage_devolution;
    @Column//Distância do descarte para locais de captação de água
    private Float sewage_to_water_distance;

    public String getWater_origin() {
        return water_origin;
    }

    public void setWater_origin(String water_origin) {
        this.water_origin = water_origin;
    }

    public String getWater_exit() {
        return water_exit;
    }

    public void setWater_exit(String water_exit) {
        this.water_exit = water_exit;
    }

    public String getWater_equipment() {
        return water_equipment;
    }

    public void setWater_equipment(String water_equipment) {
        this.water_equipment = water_equipment;
    }

    public String getWater_treatment() {
        return water_treatment;
    }

    public void setWater_treatment(String water_treatment) {
        this.water_treatment = water_treatment;
    }

    public Float getWater_capacity() {
        return water_capacity;
    }

    public void setWater_capacity(Float water_capacity) {
        this.water_capacity = water_capacity;
    }

    public String getWater_use() {
        return water_use;
    }

    public void setWater_use(String water_use) {
        this.water_use = water_use;
    }

    public Float getWater_consumption() {
        return water_consumption;
    }

    public void setWater_consumption(Float water_consumption) {
        this.water_consumption = water_consumption;
    }

    public Float getWater_distance() {
        return water_distance;
    }

    public void setWater_distance(Float water_distance) {
        this.water_distance = water_distance;
    }

    public Integer getSewage_served_homes() {
        return sewage_served_homes;
    }

    public void setSewage_served_homes(Integer sewage_served_homes) {
        this.sewage_served_homes = sewage_served_homes;
    }

    public SewageSystemEnum getSewage_serviceType() {
        return sewage_serviceType;
    }

    public void setSewage_serviceType(SewageSystemEnum sewage_serviceType) {
        this.sewage_serviceType = sewage_serviceType;
    }

    public String getSewage_treatment() {
        return sewage_treatment;
    }

    public void setSewage_treatment(String sewage_treatment) {
        this.sewage_treatment = sewage_treatment;
    }

    public String getSewage_storage() {
        return sewage_storage;
    }

    public void setSewage_storage(String sewage_storage) {
        this.sewage_storage = sewage_storage;
    }

    public Integer getSewage_maintainment_frequence() {
        return sewage_maintainment_frequence;
    }

    public void setSewage_maintainment_frequence(Integer sewage_maintainment_frequence) {
        this.sewage_maintainment_frequence = sewage_maintainment_frequence;
    }

    public String getSewage_devolution() {
        return sewage_devolution;
    }

    public void setSewage_devolution(String sewage_devolution) {
        this.sewage_devolution = sewage_devolution;
    }

    public Float getSewage_to_water_distance() {
        return sewage_to_water_distance;
    }

    public void setSewage_to_water_distance(Float sewage_to_water_distance) {
        this.sewage_to_water_distance = sewage_to_water_distance;
    }

    
    
    public SVC2_WaterSupplySewageCollection() {
        super.setServiceType(ServiceCatalogEnum.SVC2);       
    }

    
    
    @Override
    public String toString() {
        return "SVC2_WaterSupplySewageCollection{" + "water_origin=" + water_origin + ", water_exit=" + water_exit + ", water_equipment=" + water_equipment + ", water_treatment=" + water_treatment + ", water_capacity=" + water_capacity + ", water_use=" + water_use + ", water_consumption=" + water_consumption + ", water_distance=" + water_distance + ", sewage_served_homes=" + sewage_served_homes + ", sewage_serviceType=" + sewage_serviceType + ", sewage_treatment=" + sewage_treatment + ", sewage_storage=" + sewage_storage + ", sewage_maintainment_frequence=" + sewage_maintainment_frequence + ", sewage_devolution=" + sewage_devolution + ", sewage_to_water_distance=" + sewage_to_water_distance + '}';
    }


    
    
}
