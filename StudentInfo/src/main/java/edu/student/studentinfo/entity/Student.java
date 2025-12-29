package edu.student.studentinfo.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Student {
    private String sno;
    private String sname;
    private String ssex;
    private LocalDate sbirthdate;
    private String smajor;
}
