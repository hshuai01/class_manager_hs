package cn.tohuangshuai.service.impl;

import cn.tohuangshuai.dao.mapper.HSClassMapper;
import cn.tohuangshuai.pojo.domain.HSClass;
import cn.tohuangshuai.service.HSClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HSClassServiceImpl implements HSClassService {

    @Autowired
    private HSClassMapper hsClassMapper;

    /**
     * 创建一个班级
     * @param hsClass
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insert(HSClass hsClass) {
        hsClassMapper.insert(hsClass);
    }

    /**
     * 查找创建者的班级
     * @param managerId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<HSClass> getMyClasses(String managerId) {

        return hsClassMapper.getMyClasses(managerId);
    }

    /**
     * 根据年级查找班级
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<HSClass> getClasses(Integer grade) {
        Example example = new Example(HSClass.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("grade",grade);
        return hsClassMapper.selectByExample(example);
    }

    /**
     * 根据主键查找班级
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public HSClass getClassById(String id) {
        Example example = new Example(HSClass.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",id);
        return hsClassMapper.selectOneByExample(example);
    }

    @Override
    public void addClassCount(String classId) {
        hsClassMapper.addClassCount(classId);
    }
}
