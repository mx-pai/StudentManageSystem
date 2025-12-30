package edu.student.studentinfo.dto.response;

public record ScResponse(
        String sno,
        String cno,
        Integer grade,
        String semester,
        String teachingClass
) {
}
