package com.example.demo.rest;
import org.ietf.jgss.GSSContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.kerberos.authentication.KerberosServiceRequestToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
/**
 * https://github.com/eugenp/tutorials/tree/master/spring-security-sso/spring-security-sso-kerberos
 * https://stackoverflow.com/questions/12529243/delegate-forward-kerberos-tickets-with-spring-security
 * https://github.com/spring-projects/spring-security-kerberos/issues/103
 * https://raw.githubusercontent.com/spring-projects/spring-security-kerberos/master/spring-security-kerberos-core/src/main/java/org/springframework/security/kerberos/authentication/sun/SunJaasKerberosTicketValidator.java
 * https://spring.io/projects/spring-security-kerberos
 * https://docs.spring.io/spring-security-kerberos/docs/1.0.1.RELEASE/api/
 * 
 * 
 * @author Carol
 *
 */
public class ApplicationController {
	
	 private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
	
	
    @Value("${spring.application.version}")
    private String version;

    @Value("${spring.application.environment}")
    private String environment;

	@GetMapping(value = "/version")
	public String showVersion() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication instanceof KerberosServiceRequestToken) {
		    KerberosServiceRequestToken token = (KerberosServiceRequestToken) authentication;

		    if (token.getTicketValidation() == null) {
		        // No delegation possible...
		    } else {
		        GSSContext context = token.getTicketValidation().getGssContext();

		        // ...
		    }
		}
		
        logger.info("Running version {} of Demo application", version);
        
        logger.info("Running Demo application in environment {}", environment);

		return version;
	}


}
