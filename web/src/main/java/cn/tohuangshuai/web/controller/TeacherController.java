package cn.tohuangshuai.web.controller;

import cn.tohuangshuai.common.util.HSJSONResult;
import cn.tohuangshuai.pojo.domain.Student;
import cn.tohuangshuai.pojo.domain.Teacher;
import cn.tohuangshuai.service.StudentService;
import cn.tohuangshuai.service.TeacherService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends BasicController{

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public HSJSONResult login(@RequestParam("username") String username, @RequestParam("password") String password){
        return teacherService.loginTeacher(username,password);
    }

    /**
     * 查找一名教师
     * @param id
     * @return
     */
    @PostMapping("/query")
    public HSJSONResult query(@RequestParam("id")String id ){
       Teacher teacher = teacherService.queryById(id);
       if (teacher != null){
           teacher.setPassword("");
           return HSJSONResult.ok(teacher);
       }else{
           return HSJSONResult.error("出错了");
       }
    }

    /**
     * 上传教师头像
     * @param files
     * @param id
     * @return
     */
    @PostMapping("/uploadFace")
    public HSJSONResult upload(@RequestParam("file")MultipartFile[] files,String id){
        if (StringUtils.isEmpty(id)){
            return HSJSONResult.error("用户id为空！");
        }
        //上传的相对路径，也是数据库存储的值
        String uploadPathDB = "/teacher/" + id + "/face";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        try {
            if (files != null && files.length > 0) {

                //获取文件名
                String filename = files[0].getOriginalFilename();

                if (!StringUtils.isEmpty(filename)) {
                    //文件上传的最终保存路径
                    String finalFacePath = FILE_SPACE + uploadPathDB + "/" + filename;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + filename);
                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } else {
                return HSJSONResult.error("上传出错...");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Teacher teacher = new Teacher();
        teacher.setImageUrl(uploadPathDB);
        teacher.setId(id);
        teacherService.updateTeacherInfo(teacher);
        return HSJSONResult.ok("上传成功");
    }

    /**
     *
     * @param id
     * @param stuId
     * @param status
     * @return
     */
    @PostMapping("/changeStuStatus")
    public HSJSONResult changeStuStatus(@RequestParam("id") String id,@RequestParam("stuId") String stuId,
                                        @RequestParam("status") Integer status){
        if (StringUtils.isEmpty(id)){
            return HSJSONResult.error("非法操作");
        }
        Student student = new Student();
        student.setId(stuId);
        student.setStatus(status);
        studentService.updateStudentInfo(student);
        return HSJSONResult.ok();
    }



}
