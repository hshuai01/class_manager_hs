package cn.tohuangshuai.web.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    //文件存储路径
    public static final String FILE_SPACE = "C:\\hs_class_manager";

    //默认头像路径
    public static final String DEFAULT_FACE = "/total/face/totalface.jpg";

    //默认通知路径
    public static final String DEFAULT_ADVICE_IMAGE = "/total/advice/total.jpg";

    //每页记录数
    public static final Integer PAGE_SIZE = 5;

    //学生
    public static final Integer statusStudent = 5;

    //学习委员
    public static final Integer statusLcm = 4;

    //劳动委员
    public static final Integer statusLdm = 3;

    //生活委员
    public static final Integer statusLm = 2;

    //纪律委员
    public static final Integer statusDc = 1;

    //班长
    public static final Integer statusMon = 0;
}
