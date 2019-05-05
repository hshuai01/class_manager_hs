package cn.tohuangshuai.service.impl;

import cn.tohuangshuai.common.util.HSJSONResult;
import cn.tohuangshuai.dao.mapper.TeacherMapper;
import cn.tohuangshuai.pojo.domain.Teacher;
import cn.tohuangshuai.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Objects;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 教师登录
     *
     * @param username
     * @param password
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public HSJSONResult loginTeacher(String username, String password) {
        Example example = new Example(Teacher.class);
        example.createCriteria().andEqualTo("username", username);
        Teacher teacher = teacherMapper.selectOneByExample(example);
        if (teacher != null) {
            if (Objects.equals(password,teacher.getPassword())){
                teacher.setPassword("");
                return HSJSONResult.ok(teacher);
            }else {
                return HSJSONResult.error("密码错误，请重试！");
            }
        }else {
            return HSJSONResult.error("账号不存在！");
        }
    }

    /**
     * 返回一位教师
     * @param id 主键
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Teacher queryById(String id) {
        Example example = new Example(Teacher.class);
        example.createCriteria().andEqualTo("id", id);
        return teacherMapper.selectOneByExample(example);
    }

    /**
     * 修改教师信息
     * @param teacher
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateTeacherInfo(Teacher teacher) {
        Example example = new Example(Teacher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",teacher.getId());
        teacherMapper.updateByExampleSelective(teacher,example);
    }


}
