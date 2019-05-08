package cn.tohuangshuai.service.impl;

import cn.tohuangshuai.common.util.IdUtil;
import cn.tohuangshuai.dao.mapper.CourseMapper;
import cn.tohuangshuai.pojo.domain.Course;
import cn.tohuangshuai.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;


    /**
     * 保存课程
     * @param course
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Course course) {
        course.setId(IdUtil.getUId());
        course.setCount(0);
        course.setCreateTime(new Date());
        courseMapper.insertSelective(course);
    }
}
