package edu.student.studentinfo.dto.request;

public record CourseUpdateRequest(
        String cname,
        Integer ccredit,
        String cpno
) {
}
