/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.operations;
import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.auxiliary.ForceMap;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.OperationalActivityStatusEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * Atividade operacional realizada por um pelotão.
 * @author tenbenites
 * @since beta 1.0
 * @version 1.0
 */
@Entity
@Table(name = "TBL_OPERATIONAL_ACTIVITY")
public class OperationalActivity implements Serializable {
    //TODO terminar a modelagem desta classe
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Nome da operação 
    @NotNull
    @Column//(unique = true, name = "MU_name")
    @Length(max = 50)
    private String name;
    
    
    
    //Pelotão executor da operação
    @ManyToOne
    private MilitaryUnit military_unit;
    
    @ManyToOne()
    private Military last_author;
        
    //Descrição de objetivo/finalidade
    @Column
    private String main_goal;
    
    //Descrição de resultados da missão
    @Column
    private String report;
    
    //Data/hora estimada para início
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate estimated_start_date;
    
    //Data/hora estimada para término
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate estimated_finish_date;
    
    //Data/hora real de início
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate real_start_date;
    
    //Data/hora real de término
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate real_finish_date;
    
    //Data/hora estimada para início
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime estimated_start_time;
    
    //Data/hora estimada para término
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime estimated_finish_time;
    
    //Data/hora real de início
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime real_start_time;
    
    //Data/hora real de término
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime real_finish_time;
    
    //Distância da sede (Km)
    @Column
    private Float headquarter_distance;
    
    //Descrição da situação das estradas
    @Column
    private String roads;
    
    //Região abrangida
    @Column
    private String region;
    
    //Alterações na missão
    @Column
    private String force_alterations;
    
    //Alterações de material
    @Column
    private String supply_alterations;

    //Alterações de material
    @Column
    private String supply_description;
    
    //Órgãos e Agências participantes
    @Column
    private String supporters;
    
    @Column(columnDefinition="Decimal(10,7)")//lat do local da operação
    private BigDecimal latitude;
    
    @Column(columnDefinition="Decimal(10,7)")//long do local da operação
    private BigDecimal longitude;
    
    //Status da atividade
    @Column
    @Enumerated(EnumType.STRING)
    private OperationalActivityStatusEnum status;
    
    //Última atualização
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime last_update;
    
    //Efetivo total: por posto/Graduação (lista de militares a ser persistida no banco)
    @Transient
    List<ForceMap> forceMap;
    
    
    //Checagem sobre opção de edição de pelotão
    @Transient
    private Boolean set_force;

    public Boolean getSet_force() {
        return set_force;
    }

    public void setSet_force(Boolean set_force) {
        this.set_force = set_force;
    }

       
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MilitaryUnit getMilitary_unit() {
        return military_unit;
    }

    public void setMilitary_unit(MilitaryUnit military_unit) {
        this.military_unit = military_unit;
    }

    public String getMain_goal() {
        return main_goal;
    }

    public void setMain_goal(String main_goal) {
        this.main_goal = main_goal;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public LocalDate getEstimated_start_date() {
        return estimated_start_date;
    }

    public void setEstimated_start_date(LocalDate estimated_start_date) {
        this.estimated_start_date = estimated_start_date;
    }

    public LocalDate getEstimated_finish_date() {
        return estimated_finish_date;
    }

    public void setEstimated_finish_date(LocalDate estimated_finish_date) {
        this.estimated_finish_date = estimated_finish_date;
    }

    public LocalDate getReal_start() {
        return real_start_date;
    }

    public void setReal_start(LocalDate real_start_date) {
        this.real_start_date = real_start_date;
    }

    public LocalDate getReal_finish() {
        return real_finish_date;
    }

    public void setReal_finish(LocalDate real_finish_date) {
        this.real_finish_date = real_finish_date;
    }

    public LocalDate getReal_start_date() {
        return real_start_date;
    }

    public void setReal_start_date(LocalDate real_start_date) {
        this.real_start_date = real_start_date;
    }

    public LocalDate getReal_finish_date() {
        return real_finish_date;
    }

    public void setReal_finish_date(LocalDate real_finish_date) {
        this.real_finish_date = real_finish_date;
    }

    public LocalTime getEstimated_start_time() {
        return estimated_start_time;
    }

    public void setEstimated_start_time(LocalTime estimated_start_time) {
        this.estimated_start_time = estimated_start_time;
    }

    public LocalTime getEstimated_finish_time() {
        return estimated_finish_time;
    }

    public void setEstimated_finish_time(LocalTime estimated_finish_time) {
        this.estimated_finish_time = estimated_finish_time;
    }

    public LocalTime getReal_start_time() {
        return real_start_time;
    }

    public void setReal_start_time(LocalTime real_start_time) {
        this.real_start_time = real_start_time;
    }

    public LocalTime getReal_finish_time() {
        return real_finish_time;
    }

    public void setReal_finish_time(LocalTime real_finish_time) {
        this.real_finish_time = real_finish_time;
    }

    public String getSupply_description() {
        return supply_description;
    }

    public void setSupply_description(String supply_description) {
        this.supply_description = supply_description;
    }

        
    public Float getHeadquarter_distance() {
        return headquarter_distance;
    }

    public void setHeadquarter_distance(Float headquarter_distance) {
        this.headquarter_distance = headquarter_distance;
    }

    public String getRoads() {
        return roads;
    }

    public void setRoads(String roads) {
        this.roads = roads;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getForce_alterations() {
        return force_alterations;
    }

    public void setForce_alterations(String force_alterations) {
        this.force_alterations = force_alterations;
    }

    public String getSupply_alterations() {
        return supply_alterations;
    }

    public void setSupply_alterations(String supply_alterations) {
        this.supply_alterations = supply_alterations;
    }

    public String getSupporters() {
        return supporters;
    }

    public void setSupporters(String supporters) {
        this.supporters = supporters;
    }

    public List<ForceMap> getForceMap() {
        return forceMap;
    }

    public void setForceMap(List<ForceMap> forceMap) {
        this.forceMap = forceMap;
    }

    public OperationalActivityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OperationalActivityStatusEnum status) {
        this.status = status;
    }
    /**Setter especial que seta o status da operação com base em suas datas reais fornecidas*/
    public OperationalActivityStatusEnum setStatusByDate() {
        this.status = OperationalActivityStatusEnum.PLAN;
        if(this.real_start_date == null){//não há data real de início
            this.status = OperationalActivityStatusEnum.PLAN;
        }else{//Já iniciou
            if(this.real_finish_date==null){//não há data real de término, operação em execução
                this.status = OperationalActivityStatusEnum.DOING;
            }else{//Já terminou
                this.status = OperationalActivityStatusEnum.DONE;
            }        
        }        
        return this.status;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    public Military getLast_author() {
        return last_author;
    }

    public void setLast_author(Military last_author) {
        this.last_author = last_author;
    }

    public OperationalActivity() {
        this.set_force = Boolean.FALSE;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "OperationalActivity{" + "id=" + id + ", name=" + name + ", military_unit=" + military_unit + ", last_author=" + last_author + ", main_goal=" + main_goal + ", report=" + report + ", estimated_start_date=" + estimated_start_date + ", estimated_finish_date=" + estimated_finish_date + ", real_start_date=" + real_start_date + ", real_finish_date=" + real_finish_date + ", estimated_start_time=" + estimated_start_time + ", estimated_finish_time=" + estimated_finish_time + ", real_start_time=" + real_start_time + ", real_finish_time=" + real_finish_time + ", headquarter_distance=" + headquarter_distance + ", roads=" + roads + ", region=" + region + ", force_alterations=" + force_alterations + ", supply_alterations=" + supply_alterations + ", supply_description=" + supply_description + ", supporters=" + supporters + ", latitude=" + latitude + ", longitude=" + longitude + ", status=" + status + ", last_update=" + last_update + ", forceMap=" + forceMap + ", set_force=" + set_force + '}';
    }

}
