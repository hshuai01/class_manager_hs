package cn.tohuangshuai.web.controller;

import cn.tohuangshuai.common.util.HSJSONResult;
import cn.tohuangshuai.common.util.IdUtil;
import cn.tohuangshuai.pojo.domain.Course;
import cn.tohuangshuai.pojo.domain.HSClass;
import cn.tohuangshuai.pojo.domain.StuCourse;
import cn.tohuangshuai.pojo.domain.Student;
import cn.tohuangshuai.service.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController extends BasicController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private HSClassService hsClassService;

    @Autowired
    private AdviceService adviceService;

    @Autowired
    private CommentService commentService;

    /**
     * 学生注册功能
     *
     * @param student
     * @return
     */
    @RequestMapping("/regist")
    public HSJSONResult regist(@RequestBody Student student) {
        student.setId(IdUtil.getUId());
        student.setStatus(statusStudent);//默认普通学生
        student.setImageUrl(DEFAULT_FACE);
        boolean flag = studentService.regist(student);
        if (flag) {
            return HSJSONResult.ok();
        } else {
            return HSJSONResult.error("用户名已存在，请重试！");
        }
    }

    /**
     * 学生登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public HSJSONResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isEmpty(username)) {
            return HSJSONResult.error("账号为空");
        }
        if (StringUtils.isEmpty(password)) {
            return HSJSONResult.error("密码为空");
        }
        Student student = studentService.login(username, password);
        if (student == null) {
            return HSJSONResult.error("账号或密码错误");
        }
        student.setPassword("");
        return HSJSONResult.ok(student);
    }

    /**
     *  根据主键查找一名学生
     * @param id
     * @return
     */
    @RequestMapping("/query")
    public HSJSONResult query(@RequestParam("id") String id){
        if (StringUtils.isEmpty(id)){
            return HSJSONResult.error("");
        }
        Student student = studentService.queryById(id);
        return HSJSONResult.ok(student);
    }

    /**
     * 返回某一个班级的所有学生
     * @param classId
     * @return
     */
    @RequestMapping("/queryByClass")
    public HSJSONResult queryStudentsByClass(@RequestParam("classId") String classId){
        List<Student> students = studentService.getStudentsByClass(classId);
        return HSJSONResult.ok(students);
    }

    /**
     * 学生用户头像更换
     * @param files
     * @param id
     * @return
     */
    @RequestMapping("/uploadFace")
    public HSJSONResult uploadFace (@RequestParam("file") MultipartFile[] files, String id){
        if (StringUtils.isEmpty(id)){
            return HSJSONResult.error("用户id为空！");
        }
        //上传的相对路径，也是数据库存储的值
        String uploadPathDB = "/student/" + id + "/face";
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
        Student student = new Student();
        student.setImageUrl(uploadPathDB);
        student.setId(id);
        studentService.updateStudentInfo(student);
        adviceService.updateUserFace(student.getId(),student.getImageUrl());
        commentService.updateUserFace(student.getId(),student.getImageUrl());
        return HSJSONResult.ok(uploadPathDB);
    }


    /**
     *
     * @param classId
     * @return
     */
    @RequestMapping("/getCoursesById")
    public HSJSONResult getCoursesByGrade(String classId,String studentId){
        if (classId == null){
            return HSJSONResult.error("请求出错");
        }
        if (studentId == null){
            return HSJSONResult.error("请求出错");
        }

        HSClass hsClass = hsClassService.getClassById(classId);

        List<Course> courses = courseService.getCoursesByGrade(hsClass.getGrade(),studentId);

        return HSJSONResult.ok(courses);
    }

    /**
     * 获取我选择的课程
     * @param id
     * @return
     */
    @RequestMapping("/getMyCourses")
    public HSJSONResult getMyCourses(String id){

        if (id == null){
            return HSJSONResult.error("请求错误");
        }

        List<Course> courses = courseService.getCoursesByStudentId(id);

        return HSJSONResult.ok(courses);
    }

    /**
     * 进行选课
     * @param stuCourse
     * @return
     */
    @RequestMapping("/chooseCourse")
    public HSJSONResult chooseCourse(@RequestBody StuCourse stuCourse){

        if (stuCourse.getStudentId() == null){
            return HSJSONResult.error("请求错误");
        }

        if (stuCourse.getCourseId() == null){
            return HSJSONResult.error("请求错误");
        }
        courseService.chooseCourse(stuCourse);

        return HSJSONResult.ok();
    }

    /**
     * 取消选课
     * @param stuCourse
     * @return
     */
    @RequestMapping("/cancelCourse")
    public HSJSONResult cancelCourse(@RequestBody StuCourse stuCourse){
        if (stuCourse.getStudentId() == null){
            return HSJSONResult.error("请求错误");
        }

        if (stuCourse.getCourseId() == null){
            return HSJSONResult.error("请求错误");
        }

        courseService.cancelCourse(stuCourse);
        return HSJSONResult.ok();
    }


    /**
     * 获取该班级下的学生列表
     * @param courseId
     * @return
     */
    @RequestMapping("/getStudentsByCourse")
    public HSJSONResult getStudentsByCourse(String courseId){
        if (courseId == null){
            return HSJSONResult.error("请求错误");
        }

        List<Student> students = studentService.getStudentsByCourse(courseId);

        return HSJSONResult.ok(students);
    }

}
