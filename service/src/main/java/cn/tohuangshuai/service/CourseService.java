package cn.tohuangshuai.service;

import cn.tohuangshuai.pojo.domain.Course;
import cn.tohuangshuai.pojo.domain.StuCourse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    void save(Course course);

    List<Course> getCoursesByTeacherId(String teacherId);

    List<Course> getCoursesByGrade(Integer grade,String studentId);

    void chooseCourse(StuCourse stuCourse);

    List<Course> getCoursesByStudentId(String id);

    void cancelCourse(StuCourse stuCourse);
}
