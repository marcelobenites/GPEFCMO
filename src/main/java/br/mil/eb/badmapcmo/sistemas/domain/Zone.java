/*
 * Desenvolvido pelo Departamento de Tecnologia da Informação - B ADM AP/CMO
 * Autor: 2º TEN Benites
 */
package br.mil.eb.badmapcmo.sistemas.domain;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.UserClassEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.StatesEnum;
import br.mil.eb.badmapcmo.sistemas.domain.zoneInfo.Aerodrome;
import br.mil.eb.badmapcmo.sistemas.domain.zoneInfo.SecurityOSPF;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Classe de informações sobre município.
 * @author benites
 */
@Entity
@Table(name = "TBL_ZONE")
public class Zone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    //Nome do município
    @Column
    private String county_name;    
    //Estado
    @Column
    @Enumerated(EnumType.STRING)
    private StatesEnum state_name;
    
    /*******************************Fronteira**********************************/
    //País
    @Column
    private String country_name;   
    //Países fronteiriços
    @Column
    private String frontier_countries;   
    //Tipos de fronteira
    //QUESTION_FOR_CLIENT: e se houver mais de um tipo de fronteira?
    @Column
    private String frontier_type;   
    //Descrição da fronteira
    @Column
    private String frontier_description;   
    //QUESTION_FOR_CLIENT: cada município está vinculado a somente um pef??
    //Unidade Militar Fronteiriça
//    @ManyToOne
//    private MilitaryUnit frontier_militaryUnit;
    /******************************Autoridades*********************************/
    //Prefeito
    @Column
    private String mayor;
    @Column
    private String councilmen;
    @Column
    private String judge;
    @Column
    private String prosecutor;
    @Column
    private String other_authorities;
    
    /*******************************Indígenas**********************************/
    @Column
    private String funai_dsei;
    //Etinias
    @Column
    private String ethnicities;
    //Problemas com indígenas
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column
    private String problems_with_indigenous;
    
    
    //Movimentos reivindicatórios
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column
    private String claim_movements;
    
    //ONGs
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column
    private String ngos;
    
    
    //Registro de ids de Segurança e defesa civil   
    //@Transient
    //List<SecurityOSPF> security_list;
    
    /*********************************Educação*********************************/
    //Universidades
    @Column
    private String universities;
    //Outras escolas
    @Column
    private String schools;
    
    /**********************************Saúde***********************************/
    //Hospitais
    @Column
    private String hospitals;
    //Postos de saúde
    @Column
    private String health_posts;
    //Postos de saúde
    @Column
    private String evacuation_system;
    
    //Telecomunicações
    @Column
    private String communication;
    
    //Transporte público
    @Column
    private String publicTransport;
    
    //Rodovias e estradas
    @Column
    private String highways;
    
    //Porto
    @Column
    private String port;
      
    //Aeródromo
    @Column
    private Aerodrome aerodrome;
    
//    public MilitaryUnit getFrontier_militaryUnit() {
//        return frontier_militaryUnit;
//    }
//
//    public void setFrontier_militaryUnit(MilitaryUnit frontier_militaryUnit) {
//        this.frontier_militaryUnit = frontier_militaryUnit;
//    }    
    
    public String getMayor() {
        return mayor;
    }

    public void setMayor(String mayor) {
        this.mayor = mayor;
    }

    public String getClaim_movements() {
        return claim_movements;
    }

    public void setClaim_movements(String claim_movements) {
        this.claim_movements = claim_movements;
    }

    public String getFunai_dsei() {
        return funai_dsei;
    }

    public void setFunai_dsei(String funai_dsei) {
        this.funai_dsei = funai_dsei;
    }

    public String getNgos() {
        return ngos;
    }

    public void setNgos(String ngos) {
        this.ngos = ngos;
    }

    public String getUniversities() {
        return universities;
    }

    public void setUniversities(String universities) {
        this.universities = universities;
    }

    public String getHospitals() {
        return hospitals;
    }

    public void setHospitals(String hospitals) {
        this.hospitals = hospitals;
    }

    
    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getPublicTransport() {
        return publicTransport;
    }

    public void setPublicTransport(String publicTransport) {
        this.publicTransport = publicTransport;
    }

    public String getHighways() {
        return highways;
    }

    public void setHighways(String highways) {
        this.highways = highways;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getFrontier_countries() {
        return frontier_countries;
    }

    public void setFrontier_countries(String frontier_countries) {
        this.frontier_countries = frontier_countries;
    }

    public String getFrontier_type() {
        return frontier_type;
    }

    public void setFrontier_type(String frontier_type) {
        this.frontier_type = frontier_type;
    }

    public String getFrontier_description() {
        return frontier_description;
    }

    public void setFrontier_description(String frontier_description) {
        this.frontier_description = frontier_description;
    }
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public StatesEnum getState_name() {
        return state_name;
    }

    public void setState_name(StatesEnum state_name) {
        this.state_name = state_name;
    }
    
    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCouncilmen() {
        return councilmen;
    }

    public void setCouncilmen(String councilmen) {
        this.councilmen = councilmen;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getProsecutor() {
        return prosecutor;
    }

    public void setProsecutor(String prosecutor) {
        this.prosecutor = prosecutor;
    }

    public String getOther_authorities() {
        return other_authorities;
    }

    public void setOther_authorities(String other_authorities) {
        this.other_authorities = other_authorities;
    }

    public String getEthnicities() {
        return ethnicities;
    }

    public void setEthnicities(String ethnicities) {
        this.ethnicities = ethnicities;
    }

    public String getProblems_with_indigenous() {
        return problems_with_indigenous;
    }

    public void setProblems_with_indigenous(String problems_with_indigenous) {
        this.problems_with_indigenous = problems_with_indigenous;
    }

//    public List<SecurityOSPF> getSecurity_list() {
//        return security_list;
//    }
//
//    public void setSecurity_list(List<SecurityOSPF> security_list) {
//        this.security_list = security_list;
//    }

    public String getSchools() {
        return schools;
    }

    public void setSchools(String schools) {
        this.schools = schools;
    }

    public String getHealth_posts() {
        return health_posts;
    }

    public void setHealth_posts(String health_posts) {
        this.health_posts = health_posts;
    }

    public String getEvacuation_system() {
        return evacuation_system;
    }

    public void setEvacuation_system(String evacuation_system) {
        this.evacuation_system = evacuation_system;
    }

    public Aerodrome getAerodrome() {
        return aerodrome;
    }

    public void setAerodrome(Aerodrome aerodrome) {
        this.aerodrome = aerodrome;
    }

    @Override
    public String toString() {
        return "Zone{" + "id=" + id + ", county_name=" + county_name + ", state_name=" + state_name + ", country_name=" + country_name + ", frontier_countries=" + frontier_countries + ", frontier_type=" + frontier_type + ", frontier_description=" + frontier_description + ", mayor=" + mayor + ", councilmen=" + councilmen + ", judge=" + judge + ", prosecutor=" + prosecutor + ", other_authorities=" + other_authorities + ", funai_dsei=" + funai_dsei + ", ethnicities=" + ethnicities + ", problems_with_indigenous=" + problems_with_indigenous + ", claim_movements=" + claim_movements + ", ngos=" + ngos + ", universities=" + universities + ", schools=" + schools + ", hospitals=" + hospitals + ", health_posts=" + health_posts + ", evacuation_system=" + evacuation_system + ", communication=" + communication + ", publicTransport=" + publicTransport + ", highways=" + highways + ", port=" + port + ", aerodrome=" + aerodrome + '}';
    }    
    
}
