package com.vvs.springazureapp.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document("students")
@NoArgsConstructor
public class Student {
  
  @Id
  private ObjectId id;
  private String name;
  private String email;

}
