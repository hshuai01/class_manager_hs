package cn.tohuangshuai.service;

import cn.tohuangshuai.common.util.PagedResult;
import cn.tohuangshuai.pojo.domain.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void save(Comment comment);

    PagedResult getComments(String adviceId, Integer page, Integer pageSize);

    void updateUserFace(String id, String imageUrl);
}
