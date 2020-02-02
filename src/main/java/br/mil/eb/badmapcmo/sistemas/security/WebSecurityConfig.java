package br.mil.eb.badmapcmo.sistemas.security;


import br.mil.eb.badmapcmo.sistemas.service.CurrentUserDetailsService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("br.mil.eb.badmapcmo.sistemas")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CurrentUserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                    //Autorização para arquivos estáticos
                    .antMatchers("/static/**").permitAll()
                    .antMatchers("/", "/login", "/auth/**").permitAll()
                    .antMatchers("/unprotected/**").permitAll()
                    //Autorização para controllers
                    .antMatchers("/userControl/**", "unitControl/**", "zoneControl/**").authenticated()
                    //Autorização para paginas específicas
                    .antMatchers("/templates").authenticated()
                    .antMatchers("/addUser", "/listUsers").hasAnyAuthority("SUPERADMIN", "ADMIN")
                    .antMatchers("/addMilitaryUnit", "/listAllZones").hasAuthority("SUPERADMIN")       
                    .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    //.defaultSuccessUrl("/loginControl/callHome")
                    //.usernameParameter("j_username")
                    //.passwordParameter("j_password")
                    .permitAll()
                    .and()
                 .logout()
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .and()
                .rememberMe();
    }
    
   @Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
   /*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder builder)
			throws Exception {
		builder.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				//.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}
    */
    @Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/vendor/**");
    }
     
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        builder
//                .inMemoryAuthentication()
//                .withUser("eduardo").password("$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm").authorities("EDITOR", "ADMINISTRADOR")
//                .and()
//                .withUser("fernanda").password("$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm").roles("EDITOR");
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
