package edu.student.studentinfo.dto.request;

import java.time.LocalDate;

public record StudentUpdateRequest(
        String sname,
        String ssex,
        LocalDate sbirthdate,
        String smajor
) {
}
