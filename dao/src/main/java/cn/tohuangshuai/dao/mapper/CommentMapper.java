package cn.tohuangshuai.dao.mapper;

import cn.tohuangshuai.common.util.MyMapper;
import cn.tohuangshuai.pojo.domain.Comment;
import cn.tohuangshuai.pojo.domain.vo.CommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends MyMapper<Comment> {

    List<CommentVO> selectALLByAdviceId(String adviceId);
}
