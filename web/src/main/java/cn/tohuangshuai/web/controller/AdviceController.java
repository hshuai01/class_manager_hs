package cn.tohuangshuai.web.controller;


import cn.tohuangshuai.common.util.HSJSONResult;
import cn.tohuangshuai.common.util.IdUtil;
import cn.tohuangshuai.common.util.PagedResult;
import cn.tohuangshuai.pojo.domain.Advice;
import cn.tohuangshuai.service.AdviceService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RestController
@RequestMapping("/advice")
public class AdviceController extends BasicController {

    @Autowired
    private AdviceService adviceService;

    //发布通知 含图片
    @PostMapping(value = "/create", headers = "content-type=multipart/form-data")
    public HSJSONResult create(String title, String context, MultipartFile image, String author,
                               String classId, Integer status,String authorName,String authorFace,Integer authorStatus) throws IOException {

        //设置保存的命名空间
        String uploadPath = "/advice/" + author + "/";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        //图片最终保存路径，如果用户没有上传图片，则先设置一个默认图片
        String finalImagePath = "";
        try {
            if (image != null) {
                String imageName = image.getOriginalFilename();//获取文件名
                if (!StringUtils.isEmpty(imageName)) {
                    //开始上传图片
                    finalImagePath = FILE_SPACE + uploadPath + imageName;//本地路径
                    uploadPath += ("/" + imageName); // 数据库保存地址
                    File outFile = new File(finalImagePath);
                    if (outFile.getParentFile() != null || outFile.getParentFile().isDirectory()) {
                        //创建文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = image.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HSJSONResult.error("上传出错");
        }finally {
           if (fileOutputStream != null){
               fileOutputStream.flush();
               fileOutputStream.close();
           }
        }
        //将数据插入到数据库
        Advice advice = new Advice();
        advice.setId(IdUtil.getUId());
        advice.setTitle(title);
        advice.setContext(context);
        advice.setAuthor(author);
        advice.setClassId(classId);
        advice.setImageUrl(uploadPath);
        advice.setCreateTime(new Date());
        advice.setStatus(status);
        advice.setAuthorName(authorName);
        advice.setAuthorFace(authorFace);
        advice.setAuthorStatus(authorStatus);
        String adviceId = adviceService.save(advice);
        return HSJSONResult.ok(adviceId);
    }

    /**
     * 上传通知无图片
     * @return
     */
    @PostMapping("/createNoImg")
    public HSJSONResult createNoImg(@RequestBody Advice advice){
        advice.setId(IdUtil.getUId());
        advice.setImageUrl(DEFAULT_ADVICE_IMAGE);
        advice.setCreateTime(new Date());
        String adviceId = adviceService.save(advice);
        return HSJSONResult.ok(adviceId);
    }

    /**
     * 分页查询通知列表
     * @return
     */
    @PostMapping("/getAll")
    public HSJSONResult getALL(Integer page,Integer pageSize,String classId){

        if (page == null){
            page = 1;
        }

        if (pageSize == null){
            pageSize = PAGE_SIZE;
        }

        PagedResult result = adviceService.getAllAdvice(page,pageSize,classId);

        return HSJSONResult.ok(result);
    }
}
