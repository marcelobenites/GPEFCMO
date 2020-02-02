/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.GarbageTreatmentTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.ServiceCatalogEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Serviço de coleta de lixo para PEF.
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_SERVICE_GARBAGECOLLECTION")
@PrimaryKeyJoinColumn(name="id")
public class SVC3_GarbageCollection extends Service {
     
    @Column//Residências atendidas
    private Integer served_homes;
    @Column//Frequencia de coleta
    private Integer collection_frequency;
    @Column//Distância do depósito para locais de manancial
    private Float fountain_distance;
    @Column//Sistema utilizado(Rede/fossa)
    @Enumerated(EnumType.STRING)
    private GarbageTreatmentTypeEnum gargage_treatmentType;
   

    public Integer getServed_homes() {
        return served_homes;
    }

    public void setServed_homes(Integer served_homes) {
        this.served_homes = served_homes;
    }

    public Integer getCollection_frequency() {
        return collection_frequency;
    }

    public void setCollection_frequency(Integer collection_frequency) {
        this.collection_frequency = collection_frequency;
    }

    public Float getFountain_distance() {
        return fountain_distance;
    }

    public void setFountain_distance(Float fountain_distance) {
        this.fountain_distance = fountain_distance;
    }

    public GarbageTreatmentTypeEnum getGargage_treatmentType() {
        return gargage_treatmentType;
    }

    public void setGargage_treatmentType(GarbageTreatmentTypeEnum gargage_treatmentType) {
        this.gargage_treatmentType = gargage_treatmentType;
    }

    
    public SVC3_GarbageCollection() {
        super.setServiceType(ServiceCatalogEnum.SVC3);    
    }

    @Override
    public String toString() {
        return "SVC3_GarbageCollection{" + "served_homes=" + served_homes + ", collection_frequency=" + collection_frequency + ", fountain_distance=" + fountain_distance + ", gargage_treatmentType=" + gargage_treatmentType + '}';
    }
    
    
}
