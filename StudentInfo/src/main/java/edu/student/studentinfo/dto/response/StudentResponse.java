package edu.student.studentinfo.dto.response;

import java.time.LocalDate;
public record StudentResponse(
        String sno,
        String sname,
        String ssex,
        LocalDate sbirthdate,
        String smajor
) {}