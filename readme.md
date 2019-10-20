# Getting Started

### SPNEGO and Springboot documentation

* [MSA and Kerberos - the final ticketforwarding answer](https://dzone.com/articles/microservices-and-kerberos-authentication)

* [Introduction to SPNEGO/Kerberos Authentication in Spring](https://www.baeldung.com/spring-security-kerberos)
* [Spring Security Kerberos Integration](https://www.baeldung.com/spring-security-kerberos-integration)
* [Ticketforwarding part 1](https://stackoverflow.com/questions/12529243/delegate-forward-kerberos-tickets-with-spring-security)
* [Issues with forwarding](https://github.com/spring-projects/spring-security-kerberos/issues/103)
* [Spring boot ticket validation](https://raw.githubusercontent.com/spring-projects/spring-security-kerberos/master/spring-security-kerberos-core/src/main/java/org/springframework/security/kerberos/authentication/sun/SunJaasKerberosTicketValidator.java)
* [Spring Kerberos 1.0.1](https://docs.spring.io/spring-security-kerberos/docs/1.0.1.RELEASE/api/)
* [Configure ticket validator](http://code-addict.pl/spring-security-kerberos/)
* [the bloody authenticator](https://github.com/spring-projects/spring-security-kerberos/blob/master/spring-security-kerberos-core/src/main/java/org/springframework/security/kerberos/authentication/sun/SunJaasKerberosTicketValidator.java)
* [get token from kerberos service request token](http://useof.org/java-open-source/org.springframework.security.kerberos.authentication.KerberosServiceRequestToken)


## The SunJaasKerberosTicketValidator can be configured to store the authentication context

* [ticketValidator.setHoldOnToGSSContext(true)](https://github.com/spring-projects/spring-security-kerberos/issues/103)

## Keycloak and Springboot

Keycloak is based on a set of administrative UIs and a RESTful API. Keycloak supports fine-grained authorization policies and is able to combine different access control mechanisms such as:

Attribute-based access control (ABAC)

Role-based access control (RBAC)

User-based access control (UBAC)

Context-based access control (CBAC)

* [Securing Spring Microservices with Keycloak part 1](https://blog.jdriven.com/2018/10/securing-spring-microservices-with-keycloak-part-1/)
* [Securing Spring Microservices with Keycloak part 2](https://blog.jdriven.com/2018/10/securing-spring-microservices-with-keycloak-part-2/)
* [REST Service Protected Using Keycloak Authorization Services](https://medium.com/@ravthiru/rest-service-protected-using-keycloak-authorization-services-a6ad2d8ecb9f)
* [The sources from above post](https://github.com/ravthiru/keycloak-recepies)
* [Keycloak github quickstarts](https://github.com/keycloak/keycloak-quickstarts)
* [About policies and authorization](https://www.keycloak.org/docs/6.0/authorization_services/#_overview)
* []()
* []()
* []()

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/htmlsingle/#boot-features-security)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/htmlsingle/#production-ready)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)

