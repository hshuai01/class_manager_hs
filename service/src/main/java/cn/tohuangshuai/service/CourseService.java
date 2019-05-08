package cn.tohuangshuai.service;

import cn.tohuangshuai.pojo.domain.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    void save(Course course);
}
