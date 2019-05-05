package cn.tohuangshuai.dao.mapper;

import cn.tohuangshuai.common.util.MyMapper;
import cn.tohuangshuai.pojo.domain.HSClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HSClassMapper extends MyMapper<HSClass> {
    public List<HSClass> getMyClasses(String managerId);

    public void addClassCount(@Param("id") String classId);
}
