/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.*;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Dados exclusivos sobre PNRs(Próprios Nacionais Residenciais).
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_STRUCTURE_PNR")
@PrimaryKeyJoinColumn(name="id")
public class StructureT1 extends StructuralItem{
    
    @Column
    @Enumerated(EnumType.STRING)
    private GeneralAvailabilityEnum occupation_status;
    @Column
    @Enumerated(EnumType.STRING)
    private PNRTypeEnum PNRType;
    @Column
    @Enumerated(EnumType.STRING)
    private PNRCategoryEnum PNRCategory;
    @Column
    @Enumerated(EnumType.STRING)
    private PNRKindEnum PNRKind;

    public GeneralAvailabilityEnum getOccupation_status() {
        return occupation_status;
    }

    public void setOccupation_status(GeneralAvailabilityEnum occupation_status) {
        this.occupation_status = occupation_status;
    }

   

    public PNRTypeEnum getPNRType() {
        return PNRType;
    }

    public void setPNRType(PNRTypeEnum PNRType) {
        this.PNRType = PNRType;
    }

    public PNRCategoryEnum getPNRCategory() {
        return PNRCategory;
    }

    public void setPNRCategory(PNRCategoryEnum PNRCategory) {
        this.PNRCategory = PNRCategory;
    }

    public PNRKindEnum getPNRKind() {
        return PNRKind;
    }

    public void setPNRKind(PNRKindEnum PNRKind) {
        this.PNRKind = PNRKind;
    }

    @Override
    public String toString() {
        return "StructureT1{" + "occupation_status=" + occupation_status + ", PNRType=" + PNRType + ", PNRCategory=" + PNRCategory + ", PNRKind=" + PNRKind + '}';
    }

    
   
    
    
}
