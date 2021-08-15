package com.vvs.springazureapp.router;

import com.vvs.springazureapp.handler.StudentHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static  org.springframework.web.reactive.function.server.RequestPredicates.*;
import static  org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
public class StudentRouter {
  
  @Bean
  public RouterFunction<ServerResponse> studentRouterFunction(StudentHandler studentHandler) {
    return RouterFunctions
            .route(GET("/student").and(accept(APPLICATION_JSON)), studentHandler::getStudents)
            .andRoute(GET("/student/{id}").and(accept(APPLICATION_JSON)), studentHandler::getStudent)
            .andRoute(POST("/student").and(accept(APPLICATION_JSON)), studentHandler::newStudent)
            .andRoute(DELETE("/student/{id}").and(accept(APPLICATION_JSON)), studentHandler::deleteStudent);
  }
}
