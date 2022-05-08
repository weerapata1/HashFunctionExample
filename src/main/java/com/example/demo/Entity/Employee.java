package com.example.demo.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
@Data @Setter @Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO ,generator = "emp_id")
    @SequenceGenerator(sequenceName = "emp_id", name = "emp_id")
    private Long empId;

    private String empName;

    private String empUserName;

    private String empPassword;



}
