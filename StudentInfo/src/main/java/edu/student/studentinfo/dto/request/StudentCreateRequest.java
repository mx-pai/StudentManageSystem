package edu.student.studentinfo.dto.request;

import java.time.LocalDate;

public record StudentCreateRequest(
        String sno,
        String sname,
        String ssex,
        LocalDate sbirthdate,
        String smajor
) {}
