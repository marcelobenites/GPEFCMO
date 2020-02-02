/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 *
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SERVICE_WATERSUPPLY")
@PrimaryKeyJoinColumn(name="id")
public class WaterSupply extends Service {
    
    @Column
    private String captation;
    @Column
    private String equipment;
    @Column
    private Float treatment;//(Tipo e capacidade)
    @Column
    private Float storage; //(capacidade)
    @Column
//    private String usage;
//    @Column
    private String devolution;
    @Column
    private String observation;
    @Column
    private Float flow;
    @Column
    private Float distance;

    public String getCaptation() {
        return captation;
    }

    public void setCaptation(String captation) {
        this.captation = captation;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Float getTreatment() {
        return treatment;
    }

    public void setTreatment(Float treatment) {
        this.treatment = treatment;
    }

    public Float getStorage() {
        return storage;
    }

    public void setStorage(Float storage) {
        this.storage = storage;
    }

    public String getDevolution() {
        return devolution;
    }

    public void setDevolution(String devolution) {
        this.devolution = devolution;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Float getFlow() {
        return flow;
    }

    public void setFlow(Float flow) {
        this.flow = flow;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    
    


    
    
}
