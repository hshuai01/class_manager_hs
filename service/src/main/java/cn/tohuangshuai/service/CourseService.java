package cn.tohuangshuai.service;

import cn.tohuangshuai.pojo.domain.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    void save(Course course);

    List<Course> getCoursesByTeacherId(String teacherId);

    List<Course> getCoursesByGrade(Integer grade);
}
