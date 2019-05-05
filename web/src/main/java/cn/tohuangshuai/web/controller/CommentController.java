package cn.tohuangshuai.web.controller;

import cn.tohuangshuai.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comment")
@RestController
public class CommentController extends BasicController{

    @Autowired
    private CommentService commentService;



}
