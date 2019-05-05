package cn.tohuangshuai.service;

import cn.tohuangshuai.pojo.domain.HSClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HSClassService {

    void insert(HSClass hsClass);

    List<HSClass> getMyClasses(String managerId);

    List<HSClass> getClasses(Integer grade);

    HSClass getClassById(String id);

    void addClassCount(String classId);
}
