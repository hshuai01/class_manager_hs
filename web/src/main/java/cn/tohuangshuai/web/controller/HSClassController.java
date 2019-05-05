package cn.tohuangshuai.web.controller;

import cn.tohuangshuai.common.util.HSJSONResult;
import cn.tohuangshuai.common.util.IdUtil;
import cn.tohuangshuai.pojo.domain.HSClass;
import cn.tohuangshuai.service.HSClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/class")
public class HSClassController extends BasicController{

    @Autowired
    private HSClassService hsClassService;

    //创建一个班级
    @PostMapping("/create")
    public HSJSONResult create(@RequestParam("name")String name,@RequestParam("grade") Integer grade,
                               @RequestParam("managerId") String managerId){
        HSClass hsClass = new HSClass();
        hsClass.setId(IdUtil.getUId());
        hsClass.setName(name);
        hsClass.setManagerId(managerId);
        hsClass.setGrade(grade);
        hsClass.setNumber(1);
        hsClass.setCreateTime(new Date());
        hsClassService.insert(hsClass);
        return HSJSONResult.ok();
    }

    //获取我创建的班级列表
    @PostMapping("/getMyClasses")
    public HSJSONResult getMyClasses(@RequestParam("managerId") String managerId){
        List<HSClass> hsClasses = hsClassService.getMyClasses(managerId);
        return HSJSONResult.ok(hsClasses);
    }

    //获取班级列表

    @PostMapping("/getClasses")
    public HSJSONResult getClasses(@RequestParam("grade") Integer grade){
        if (grade ==null){
            return HSJSONResult.error("");
        }
        List<HSClass> classes = hsClassService.getClasses(grade);
        return HSJSONResult.ok(classes);
    }

    //获取班级BY主键
    @PostMapping("/getClass")
    public HSJSONResult getClassById(@RequestParam("id") String id){
        if (StringUtils.isEmpty(id)){
            return HSJSONResult.error("用户未登录");
        }
        HSClass hsClass = hsClassService.getClassById(id);
        return HSJSONResult.ok(hsClass);
    }


}
