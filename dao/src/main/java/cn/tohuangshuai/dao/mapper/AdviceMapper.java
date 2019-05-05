package cn.tohuangshuai.dao.mapper;

import cn.tohuangshuai.common.util.MyMapper;
import cn.tohuangshuai.pojo.domain.Advice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdviceMapper extends MyMapper<Advice> {
    List<Advice> queryAllAdvice(@Param("classId") String classId);
}
