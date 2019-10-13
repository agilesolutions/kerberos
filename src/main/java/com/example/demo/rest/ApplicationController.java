package com.example.demo.rest;

import java.security.PrivilegedExceptionAction;
import java.util.Base64;

import javax.security.auth.Subject;

import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

import com.sun.security.jgss.ExtendedGSSContext;
import com.sun.security.jgss.ExtendedGSSCredential;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.kerberos.authentication.KerberosServiceRequestToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

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
 * 
 * https://github.com/spring-projects/spring-security-kerberos/tree/master/spring-security-kerberos-core/src/main/java/org/springframework/security/kerberos/authentication
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

	@GetMapping(value = "/forward")
	@ApiOperation(value = "Kerberos ticket forward", notes = "Test delegated ticket with kerberos S4U")
	public String showVersion() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// https://dzone.com/articles/microservices-and-kerberos-authentication
		if (authentication instanceof KerberosServiceRequestToken) {

			KerberosServiceRequestToken spring = (KerberosServiceRequestToken) SecurityContextHolder.getContext()
					.getAuthentication();

			KerberosServiceRequestToken token = (KerberosServiceRequestToken) authentication;

			Subject subject = token.getTicketValidation().subject();

			/**
			 * Gets the (Base64) encoded response token assuming one is available.
			 * 
			 * see
			 * https://github.com/spring-projects/spring-security-kerberos/blob/master/spring-security-kerberos-core/src/main/java/org/springframework/security/kerberos/authentication/KerberosServiceRequestToken.java
			 *
			 * @return encoded response token
			 */
			String base64Token = token.getEncodedResponseToken();

			logger.info("base 64 spnego token {} of Demo application", base64Token);

			if (token.getTicketValidation() == null) {
				logger.info("No delegation possible");
			} else {

				try {
					
					byte[] rawToken = createServiceToken("servername");

					logger.info("Raw token {}", rawToken);
					
					String forwardedToken = generateToken(rawToken);

					logger.info("SPNEGO token {}", forwardedToken);

					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		} else {
			logger.info("No kerberos token available on security context holder");
		}


		logger.info("Running Demo application in environment {}", environment);

		return version;
	}


	/**
	 * Establish GSS context and generate TGS token.
	 *
	 * @param targetUserName user to impersonate
	 * @param targetService  target service SPN
	 * @return Base64 token
	 * @throws Exception many thinks may fail
	 */
	public String generateToken(byte[] token) throws Exception {

		final String result = Base64.getEncoder().encodeToString(token);
		System.out.println("Token " + Base64.getEncoder().encodeToString(token));

		return result;
	}

	/**
	 * https://dzone.com/articles/microservices-and-kerberos-authentication
	 * @param serviceName
	 * @return
	 * @throws Exception
	 */
	public static byte[] createServiceToken(String serviceName) throws Exception {
		KerberosServiceRequestToken authentication = (KerberosServiceRequestToken) SecurityContextHolder.getContext()
				.getAuthentication();
		Subject subject = authentication.getTicketValidation().subject();
		return Subject.doAs(subject, (PrivilegedExceptionAction<byte[]>) () -> {
			GSSManager manager = GSSManager.getInstance();
			GSSName name = manager.createName("HTTP@" + serviceName, GSSName.NT_HOSTBASED_SERVICE);
			GSSContext context = manager.createContext(name, null,
					authentication.getTicketValidation().getGssContext().getDelegCred(),
					GSSContext.INDEFINITE_LIFETIME);
			context.requestCredDeleg(true);
			byte[] serviceToken = context.initSecContext(authentication.getToken(), 0,
					authentication.getToken().length);
			context.dispose();
			return serviceToken;
		});
	}

}
