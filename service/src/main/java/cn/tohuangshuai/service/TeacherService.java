package cn.tohuangshuai.service;

import cn.tohuangshuai.common.util.HSJSONResult;
import cn.tohuangshuai.pojo.domain.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    public HSJSONResult loginTeacher(String username,String password);

    public Teacher queryById(String id);

    void updateTeacherInfo(Teacher teacher);
}
