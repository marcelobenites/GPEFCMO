/*
 * Desenvolvido pelo Departamento de Tecnologia da Informação - B ADM AP/CMO
 * Autor: 2º TEN Benites
 */
package br.mil.eb.badmapcmo.sistemas.domain;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.SexoEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.PostGradEnum;
import java.io.Serializable;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Tabela de informações dos militares cadastrados no sistema, tanto como 
 * usuários quanto para cadastro de efetivo em PEFs e missões. Como o sistema 
 * só irá admitir cadastro de militares, uma superclasse pessoa não se fez
 * necessária.
 * @author benites
 */
@Entity
@Table(name = "TBL_MILITARY")
public class Military implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 50)
    private String surname;
    @Column(nullable = false, length = 50)
    private String war_name;
    @Column(unique=true, nullable = false, length = 50)   
    private Integer military_identitiy;
    @Column(length = 50)
    private Integer phone1;
    @Column(length = 50)
    private Integer phone2;
    @Column(length = 50)
    private String email;
    @Column()
    private boolean isUser;
        
    @Column
    @Enumerated(EnumType.STRING)
    private SexoEnum gender;
    
    @Column(nullable = false, name="post_grad")
    @Enumerated(EnumType.STRING)
    private PostGradEnum post;
    
           
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birth;
    

    //Foreign key - unidade de origem do militar, utilizada para identificar 
    //o poder de visualização das demais unidades no sistema.
    @ManyToOne
    private MilitaryUnit militaryUnit;
    
    
    @OneToOne(cascade={CascadeType.ALL})
    private Address address;

          
     
    public boolean isIsUser() {
        return isUser;
    }

    /*@OneToMany(mappedBy = "militar")
    private List<PeriodosDePermanencia> periodosDePermanencias;*/
    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getWar_name() {
        return war_name;
    }

    public void setWar_name(String war_name) {
        this.war_name = war_name;
    }

    public Integer getMilitary_identitiy() {
        return military_identitiy;
    }

    public void setMilitary_identitiy(Integer military_identitiy) {
        this.military_identitiy = military_identitiy;
    }

    public Integer getPhone1() {
        return phone1;
    }

    public void setPhone1(Integer phone1) {
        this.phone1 = phone1;
    }

    public Integer getPhone2() {
        return phone2;
    }

    public void setPhone2(Integer phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SexoEnum getGender() {
        return gender;
    }

    public void setGender(SexoEnum gender) {
        this.gender = gender;
    }

    public PostGradEnum getPost() {
        return post;
    }

    public void setPost(PostGradEnum post) {
        this.post = post;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public MilitaryUnit getMilitaryUnit() {
        return militaryUnit;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    


    /*
    public Endereco getEndereco() {
            return endereco;
    }


    public void setEndereco(Endereco endereco) {
            this.endereco = endereco;
    }


    public List<PeriodosDePermanencia> getPeriodosDePermanencias() {
            return periodosDePermanencias;
    }


    public void setPeriodosDePermanencias(List<PeriodosDePermanencia> periodosDePermanencias) {
            this.periodosDePermanencias = periodosDePermanencias;
    }*/
	
    public Military() {
    }

    @Override
    public String toString() {
        return "Military{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", war_name=" + war_name + ", military_identitiy=" + military_identitiy + ", phone1=" + phone1 + ", phone2=" + phone2 + ", email=" + email + ", isUser=" + isUser + ", gender=" + gender + ", post=" + post + ", birth=" + birth + ", militaryUnit=" + militaryUnit + ", address=" + address + '}';
    }

    

    	

}
