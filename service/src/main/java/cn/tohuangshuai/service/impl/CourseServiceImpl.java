package cn.tohuangshuai.service.impl;

import cn.tohuangshuai.common.util.IdUtil;
import cn.tohuangshuai.dao.mapper.CourseMapper;
import cn.tohuangshuai.dao.mapper.StuCourseMapper;
import cn.tohuangshuai.pojo.domain.Course;
import cn.tohuangshuai.pojo.domain.StuCourse;
import cn.tohuangshuai.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StuCourseMapper stuCourseMapper;


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

    /**
     * 查询某位教师的所有课程
     * @param teacherId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Course> getCoursesByTeacherId(String teacherId) {

        List<Course> courses = courseMapper.selectAllByTeacherId(teacherId);

        return courses;
    }

    /**
     * 根据班级获取课程列表
     * @param grade
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Course> getCoursesByGrade(Integer grade,String studentId) {
        //该年级下所有课程
        List<Course> courses = courseMapper.selectAllByGrade(grade);
        //获取该生选的课程记录
        Example example = new Example(StuCourse.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("studentId",studentId);
        List<StuCourse> stuCourses = stuCourseMapper.selectByExample(example);
        //去掉已选课程
        for (int i = 0; i < stuCourses.size(); i++) {
            for (int j = 0; j < courses.size(); j++) {
                if (stuCourses.get(i).getCourseId().equals(courses.get(j).getId())){
                    courses.remove(j);
                }
            }
        }
        return courses;
    }

    /**
     * 添加一条选课记录
     * @param stuCourse
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void chooseCourse(StuCourse stuCourse) {
        stuCourse.setId(IdUtil.getUId());
        stuCourseMapper.insertSelective(stuCourse);
        //课程人数加1
        courseMapper.addCount(stuCourse.getCourseId());
    }

    /**
     * 获取我的课程列表
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Course> getCoursesByStudentId(String id) {
        Example example = new Example(StuCourse.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("studentId",id);
        List<StuCourse> stuCourses = stuCourseMapper.selectByExample(example);
        List<String> coursesId = new ArrayList<>();
        if (stuCourses.size()!= 0){
            //获取课程ID列表
            for (StuCourse course : stuCourses){
                coursesId.add(course.getCourseId());
            }
            List<Course> myCourses = courseMapper.getCoursesByIdList(coursesId);
            return myCourses;
        }else {
            return null;
        }

    }

    /**
     * 取消选课
     * @param stuCourse
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void cancelCourse(StuCourse stuCourse) {
        Example example = new Example(StuCourse.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("studentId",stuCourse.getStudentId());
        criteria.andEqualTo("courseId",stuCourse.getCourseId());
        stuCourseMapper.deleteByExample(example);
        courseMapper.reduceCount(stuCourse.getCourseId());
    }
}
