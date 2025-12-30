package edu.student.studentinfo.dto.request;

public record CourseCreateRequest(
        String cno,
        String cname,
        Integer ccredit,
        String cpno
) {
}
