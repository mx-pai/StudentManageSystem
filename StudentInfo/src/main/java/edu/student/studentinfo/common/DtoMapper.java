package edu.student.studentinfo.common;

import edu.student.studentinfo.dto.request.CourseCreateRequest;
import edu.student.studentinfo.dto.request.CourseUpdateRequest;
import edu.student.studentinfo.dto.request.StudentCreateRequest;
import edu.student.studentinfo.dto.request.StudentUpdateRequest;
import edu.student.studentinfo.dto.response.CourseResponse;
import edu.student.studentinfo.dto.response.ScResponse;
import edu.student.studentinfo.dto.response.StudentResponse;
import edu.student.studentinfo.entity.Course;
import edu.student.studentinfo.entity.Sc;
import edu.student.studentinfo.entity.Student;

public class DtoMapper {
    public static StudentResponse toStudentResponse(Student s) {
        return new StudentResponse(s.getSno(), s.getSname(), s.getSsex(), s.getSbirthdate(), s.getSmajor());
    }

    public static Student toStudentEntity(StudentCreateRequest req) {
        Student s = new Student();
        s.setSno(req.sno());
        s.setSname(req.sname());
        s.setSsex(req.ssex());
        s.setSbirthdate(req.sbirthdate());
        s.setSmajor(req.smajor());
        return s;
    }

    public static Student toStudentEntity(String sno, StudentUpdateRequest req) {
        Student s = new Student();
        s.setSno(sno);
        s.setSname(req.sname());
        s.setSsex(req.ssex());
        s.setSbirthdate(req.sbirthdate());
        s.setSmajor(req.smajor());
        return s;
    }

    public static Course toCourseEntity(CourseCreateRequest req) {
        Course c = new Course();
        c.setCno(req.cno());
        c.setCname(req.cname());
        c.setCcredit(req.ccredit());
        c.setCpno(req.cpno());
        return c;
    }

    public static Course toCourseEntity(CourseUpdateRequest req) {
        Course c = new Course();
        c.setCname(req.cname());
        c.setCcredit(req.ccredit());
        c.setCpno(req.cpno());
        return c;
    }

    public static CourseResponse toCourseResponse(Course c){
        return new CourseResponse(c.getCno(), c.getCname(), c.getCcredit(), c.getCpno());
    }

    public static ScResponse toScResponse(Sc sc) {
        return new ScResponse(sc.getSno(), sc.getCno(), sc.getGrade(), sc.getSemester(), sc.getTeachingClass());
    }
}
