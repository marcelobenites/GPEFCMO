/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por STI (Seção de Tecnologia da Informação)
 *  2019 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.zoneInfo;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.OspfCategoryEnum;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author ten-benites
 */
@Entity
@Table(name = "TBL_ZONE_SECURITY_OSPF")
public class SecurityOSPF implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    
    @Column
    @Enumerated(EnumType.STRING)
    private OspfCategoryEnum category;
    
    //Efetivo
    @Column   
    private Integer total_staff;
    
    //Cmt/Ch/Dir/Del
    @Column
    private String command;
    
    //natureza da unidade
    @Column
    private String nature;
    
    //Vtr (Tipo/Qnt)
    @Column
    private String vtr;
    
    //campo de observações
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column
    private String observations;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OspfCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(OspfCategoryEnum category) {
        this.category = category;
    }

    public Integer getTotal_staff() {
        return total_staff;
    }

    public void setTotal_staff(Integer total_staff) {
        this.total_staff = total_staff;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getVtr() {
        return vtr;
    }

    public void setVtr(String vtr) {
        this.vtr = vtr;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "SecurityOSPF{" + "id=" + id + ", category=" + category + ", total_staff=" + total_staff + ", command=" + command + ", nature=" + nature + ", vtr=" + vtr + ", observations=" + observations + '}';
    }

    
}
