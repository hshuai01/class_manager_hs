package cn.tohuangshuai.service.impl;

import cn.tohuangshuai.dao.mapper.StudentMapper;
import cn.tohuangshuai.pojo.domain.HSClass;
import cn.tohuangshuai.pojo.domain.Student;
import cn.tohuangshuai.service.HSClassService;
import cn.tohuangshuai.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private HSClassService hsClassService;

    //注册学生
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean regist(Student student) {
        //判断用户名是否存在
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",student.getUsername());
        if (studentMapper.selectOneByExample(example) != null) {
            return false;
        }else {
            studentMapper.insert(student);
            //注册学生成功后为该生班级人数+1
            hsClassService.addClassCount(student.getClassId());
            return true;
        }
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Student login(String username, String password) {
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        Student student = studentMapper.selectOneByExample(example);
        if (student == null){
            return null;
        }else {
            if (Objects.equals(password,student.getPassword())){
                //密码正确，登录成功
                return student;
            }else {
                return null;
            }
        }
    }

    /**
     * 根据主键查找一名学生
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Student queryById(String id) {
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",id);
        Student student = studentMapper.selectOneByExample(example);
        return student;
    }

    /**
     * 根据班级查找学生
     * @param classId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Student> getStudentsByClass(String classId) {
        List<Student> students = studentMapper.getStudentsByClass(classId);
        return students;
    }

    /**
     * 修改学生信息
     * @param student
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStudentInfo(Student student) {
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",student.getId());
        studentMapper.updateByExampleSelective(student,example);
    }
}
