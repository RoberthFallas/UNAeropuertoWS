/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto;

import static io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation.HEADER;
import java.util.Collections;
import static java.util.Collections.singletonList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Roberth :)
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION, HEADER.name())))
                .securityContexts(singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        singletonList(SecurityReference.builder()
                                                .reference("JWT")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        )
                                )
                                .build())
                )
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage("org.una.UNAeropuerto.controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Autenticación", "Métodos de Seguridad"),
                        new Tag("Aerolíneas", "Entidad de Aerolínea"),
                        new Tag("Alertas", "Entidad de Alerta"),
                        new Tag("Áreas", "Entidad de Área"),
                        new Tag("Aviones", "Entidad de Avión"),
                        new Tag("Bitácoras", "Entidad de Bitácora"),
                        new Tag("Cobros", "Entidad de Cobro "),
                        new Tag("Gastos Reparaciones", "Entidad de Gasto Reparación"),
                        new Tag("Lugares", "Entidad de Lugar"),
                        new Tag("Parámetros del Sistema", "Entidad de Parámetro Sistema"),
                        new Tag("Pistas", "Entidad de Pista"),
                        new Tag("Provedores", "Entidad de Provedor"),
                        new Tag("Roles", "Entidad de Rol"),
                        new Tag("Roles Usuarios","Entidad de Rol por Usuario "),
                        new Tag("Servicios de Mantenimiento","Entidad de Servicio de Mantenimiento"),
                        new Tag("Tipos de Reparaciones","Entidad de Tipo de Reparacion"),
                        new Tag("Tipos de Servicios","Entidad de Tipo de Servicio"),
                        new Tag("Usuarios","Entidad de Usuario"),
                         new Tag("Vuelos","Entidad de Vuelos")
                );
    }
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(
//                        RequestHandlerSelectors
//                                .basePackage("org.una.UNAeropuerto.controllers"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo())
//                .tags(new Tag("Seguridad", "Metodos de Seguridad"),
//                        new Tag("Usuarios", "Entidad de Usuarios")
//                );
//
//    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "UNAeropuerto",
                "Rest API UNAeropuerto.",
                "Versión:2.1.0",
                "https://google.com",
                new Contact("UNA Sede Región Brunca", "https://srb.una.ac.cr/index.php/es/", "decanatosrb@una.cr"),
                "Apache-2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }

}
