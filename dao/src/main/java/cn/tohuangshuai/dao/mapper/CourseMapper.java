package cn.tohuangshuai.dao.mapper;

import cn.tohuangshuai.common.util.MyMapper;
import cn.tohuangshuai.pojo.domain.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper extends MyMapper<Course> {
    List<Course> selectAllByTeacherId(@Param("teacherId") String teacherId);

    List<Course> selectAllByGrade(Integer grade);
}
