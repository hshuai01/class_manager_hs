package cn.tohuangshuai.service;

import cn.tohuangshuai.pojo.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    boolean regist(Student student);

    Student login(String username, String password);

    Student queryById(String id);

    List<Student> getStudentsByClass(String classId);

    void updateStudentInfo(Student student);
}
