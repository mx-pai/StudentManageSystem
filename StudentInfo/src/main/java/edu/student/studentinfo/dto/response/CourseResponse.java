package edu.student.studentinfo.dto.response;

public record CourseResponse(
        String cno,
        String cname,
        Integer ccredit,
        String cpno
) {
}
