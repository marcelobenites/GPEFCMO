package br.mil.eb.badmapcmo.sistemas.domain;
import javax.persistence.*;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.UserClassEnum;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TBL_USER")
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 50)
    private String username;
    
    @Column(nullable = false, length = 60)
    private String password;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    
    @Transient 
    private String passwordConfirmation;
    
    @OneToOne(cascade={CascadeType.ALL})
    private Military military;
    
    @Column(name = "classe")
    @Enumerated(EnumType.STRING)
    private UserClassEnum classe;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Military getMilitary() {
		return military;
	}

	public void setMilitary(Military military) {
		this.military = military;
        }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public UserClassEnum getClasse() {
		return classe;
	}

	public void setClasse(UserClassEnum classe) {
		this.classe = classe;
	}

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

	
	public User() {
		
	}

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", lastLogin=" + lastLogin + ", passwordConfirmation=" + passwordConfirmation + ", military=" + military + ", classe=" + classe + '}';
    }

    
		
}
