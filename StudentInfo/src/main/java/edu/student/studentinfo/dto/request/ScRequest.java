package edu.student.studentinfo.dto.request;

public record ScRequest(
        String sno,
        String cno,
        String semester,
        String teachingClass
) {
}
