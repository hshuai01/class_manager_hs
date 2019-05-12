package cn.tohuangshuai.dao.mapper;

import cn.tohuangshuai.common.util.MyMapper;
import cn.tohuangshuai.pojo.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends MyMapper<Student> {
    List<Student> getStudentsByClass(@Param("classId") String classId);

    List<Student> getStudentsByIdList(List<String> studentIds);
}
