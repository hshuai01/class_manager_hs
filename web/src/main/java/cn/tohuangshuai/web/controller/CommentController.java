package cn.tohuangshuai.web.controller;

import cn.tohuangshuai.common.util.HSJSONResult;
import cn.tohuangshuai.common.util.PagedResult;
import cn.tohuangshuai.pojo.domain.Comment;
import cn.tohuangshuai.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comment")
@RestController
public class CommentController extends BasicController{

    @Autowired
    private CommentService commentService;

    /**
     * 保存留言
     * @param comment
     * @return
     */
    @RequestMapping("/save")
    public HSJSONResult save(@RequestBody Comment comment){

        commentService.save(comment);

        return HSJSONResult.ok();
    }

    @RequestMapping("/getComments")
    public HSJSONResult getComments(String adviceId , Integer page ,Integer pageSize){
        if (page == null){
            page = 1;
        }
        if (pageSize == null){
            pageSize = PAGE_SIZE;
        }
        PagedResult list = commentService.getComments(adviceId,page,pageSize);


        return HSJSONResult.ok(list);
    }

}
