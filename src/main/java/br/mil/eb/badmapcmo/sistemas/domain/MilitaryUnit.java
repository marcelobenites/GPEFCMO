/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.domain;

import br.mil.eb.badmapcmo.sistemas.domain.*;
import br.mil.eb.badmapcmo.sistemas.domain.PELDST.MUStaff;
import br.mil.eb.badmapcmo.sistemas.domain.PELDST.MUStaff;
import br.mil.eb.badmapcmo.sistemas.domain.auxiliary.MUDeps;
import br.mil.eb.badmapcmo.sistemas.domain.auxiliary.Region;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.MilitaryUnitTypeEnum;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import static org.hibernate.FetchMode.LAZY;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Unidade militar cadastrada no sistema.
 *
 * @author benites
 * @since beta 1.0
 * @version 1.0
 */
@Entity
@Table(name = "TBL_MILITARY_UNITY")
public class MilitaryUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Length(max = 50)
    private String MU_name;

    @Column
    @Length(max = 50)
    private String MU_initials;

    @NotNull
    @Column//(nullable = false)//TODO depois do banco estavel adicionar todas as condições de notnull
    @Enumerated(EnumType.STRING)
    private MilitaryUnitTypeEnum MU_type;

    @Column
    @Length(max = 200)
    private String MU_website;

    @Column
    @Length(max = 50)
    private String MU_tel;

    @Column
    @Length(max = 50)
    private String MU_email;

    @Column
    private String commander;
    
    @Column
    private String S_commander;
    
    @Column
    private String subordination;

    @Lob @Basic(fetch=FetchType.LAZY)
    @Column
    private String MU_history;
    
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate last_update;
    
    @ManyToOne()//Último a alterar alguma informação da unidade
    private Military last_author;
    
    @Column
    private Integer perimeter;
    
    @Column
    private Integer ritex;
    
    @Column
    private Integer radio_freq;
    
    //Descrição do ambiente operacional
    @Column
    private String operational_environment;
    
    //Localidades mais importantes no entorno do PEF
    @Column
    private String main_zones;
    
    //Relacionamento com estrangeiros (BOL, PAR, outros)
    @Column
    private String foreign_relation;
    
    //Etta geral País Viz
    @Column
    private String general_etta;

    /*Foreign key*/
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    
    /*Foreign key*/
    @OneToOne(cascade = {CascadeType.ALL})
    private MUStaff staff;
    
    /*Foreign key*/
    @OneToMany 
    private List<Zone> zones;

    //Registro de ids de municípios abrangidos pela unidade   
    @Transient
    List<Region> regions;

    //Registro de dependencia entre a unidade e as demais    
    //TODO_improvement_priority3: verificar se é conveniente em termos de performance
    //colocar toda a árvore de dependencias de forma transiente nos objetos MU
    @Transient
    List<MUDeps> deps;

    public String getMU_email() {
        return MU_email;
    }

    public void setMU_email(String MU_email) {
        this.MU_email = MU_email;
    }

    public String getCommander() {
        return commander;
    }

    public void setCommander(String commander) {
        this.commander = commander;
    }

    public String getMU_history() {
        return MU_history;
    }

    public void setMU_history(String MU_history) {
        this.MU_history = MU_history;
    }

    public LocalDate getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDate last_update) {
        this.last_update = last_update;
    }

    public Military getLast_author() {
        return last_author;
    }

    public void setLast_author(Military last_author) {
        this.last_author = last_author;
    }
    
    public List<MUDeps> getDeps() {
        return deps;
    }

    public void setDeps(List<MUDeps> deps) {
        this.deps = deps;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMU_name() {
        return MU_name;
    }

    public void setMU_name(String MU_name) {
        this.MU_name = MU_name;
    }

    public String getMU_initials() {
        return MU_initials;
    }

    public void setMU_initials(String MU_initials) {
        this.MU_initials = MU_initials;
    }

    public MilitaryUnitTypeEnum getMU_type() {
        return MU_type;
    }

    public void setMU_type(MilitaryUnitTypeEnum MU_type) {
        this.MU_type = MU_type;
    }

    public String getMU_website() {
        return MU_website;
    }

    public void setMU_website(String MU_website) {
        this.MU_website = MU_website;
    }

    public String getMU_tel() {
        return MU_tel;
    }

    public void setMU_tel(String MU_tel) {
        this.MU_tel = MU_tel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MilitaryUnit() {

    }

    public MUStaff getStaff() {
        return staff;
    }

    public void setStaff(MUStaff staff) {
        this.staff = staff;
    }

    public String getS_commander() {
        return S_commander;
    }

    public void setS_commander(String S_commander) {
        this.S_commander = S_commander;
    }

    public String getSubordination() {
        return subordination;
    }

    public void setSubordination(String subordination) {
        this.subordination = subordination;
    }

    public Integer getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Integer perimeter) {
        this.perimeter = perimeter;
    }

    public Integer getRitex() {
        return ritex;
    }

    public void setRitex(Integer ritex) {
        this.ritex = ritex;
    }

    public Integer getRadio_freq() {
        return radio_freq;
    }

    public void setRadio_freq(Integer radio_freq) {
        this.radio_freq = radio_freq;
    }

    public String getOperational_environment() {
        return operational_environment;
    }

    public void setOperational_environment(String operational_environment) {
        this.operational_environment = operational_environment;
    }

    public String getMain_zones() {
        return main_zones;
    }

    public void setMain_zones(String main_zones) {
        this.main_zones = main_zones;
    }

    public String getForeign_relation() {
        return foreign_relation;
    }

    public void setForeign_relation(String foreign_relation) {
        this.foreign_relation = foreign_relation;
    }

    public String getGeneral_etta() {
        return general_etta;
    }

    public void setGeneral_etta(String general_etta) {
        this.general_etta = general_etta;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    @Override
    public String toString() {
        return "MilitaryUnit{" + "id=" + id + ", MU_name=" + MU_name + ", MU_initials=" + MU_initials + ", MU_type=" + MU_type + ", MU_website=" + MU_website + ", MU_tel=" + MU_tel + ", MU_email=" + MU_email + ", commander=" + commander + ", S_commander=" + S_commander + ", subordination=" + subordination + ", MU_history=" + MU_history + ", last_update=" + last_update + ", last_author=" + last_author + ", perimeter=" + perimeter + ", ritex=" + ritex + ", radio_freq=" + radio_freq + ", operational_environment=" + operational_environment + ", main_zones=" + main_zones + ", foreign_relation=" + foreign_relation + ", general_etta=" + general_etta + ", address=" + address + ", staff=" + staff + ", zones=" + zones + ", regions=" + regions + ", deps=" + deps + '}';
    }
    
}
