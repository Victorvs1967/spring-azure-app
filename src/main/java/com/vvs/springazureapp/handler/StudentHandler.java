package com.vvs.springazureapp.handler;

import com.vvs.springazureapp.model.Student;
import com.vvs.springazureapp.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.http.MediaType.APPLICATION_JSON;;

@Component
public class StudentHandler {
  
  @Autowired
  private StudentRepository studentRepository;

  public Mono<ServerResponse> getStudents(ServerRequest request) {
    Flux<Student> students = studentRepository.findAll();
    return ServerResponse
            .ok()
            .contentType(APPLICATION_JSON)
            .body(students, Student.class);
  }

  public Mono<ServerResponse> getStudent(ServerRequest request) {
    Mono<Student> student = studentRepository.findById(request.pathVariable("id"));
    return ServerResponse
            .ok()
            .contentType(APPLICATION_JSON)
            .body(student, Student.class);
  }

  public Mono<ServerResponse> newStudent(ServerRequest request) {
    Mono<Student> student = request.bodyToMono(Student.class);
    return ServerResponse
            .ok()
            .contentType(APPLICATION_JSON)
            .body(fromPublisher(student.flatMap(this::save), Student.class));
  }

  public Mono<ServerResponse> deleteStudent(ServerRequest request) {
    Mono<Student> student = studentRepository.findById(request.pathVariable("id"));
    return ServerResponse
            .ok()
            .contentType(APPLICATION_JSON)
            .body(fromPublisher(student.flatMap(this::delete), Student.class));
  }

  private Mono<Student> save(Student student) {
    return Mono.fromSupplier(() -> {
      studentRepository.save(student).subscribe();
      return student;
    });
  }

  private Mono<Student> delete(Student student) {
    return Mono.fromSupplier(() -> {
      studentRepository.delete(student).subscribe();
      return student;
    });
  }

}
