package cn.tohuangshuai.service.impl;

import cn.tohuangshuai.common.util.IdUtil;
import cn.tohuangshuai.common.util.PagedResult;
import cn.tohuangshuai.common.util.TimeAgoUtils;
import cn.tohuangshuai.dao.mapper.CommentMapper;
import cn.tohuangshuai.pojo.domain.Advice;
import cn.tohuangshuai.pojo.domain.Comment;
import cn.tohuangshuai.pojo.domain.vo.CommentVO;
import cn.tohuangshuai.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 保存留言
     * @param comment
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Comment comment) {
        comment.setId(IdUtil.getUId());
        comment.setCreateTime(new Date());
        commentMapper.insertSelective(comment);
    }

    /**
     * 分页查询留言
     * @param adviceId
     * @param page
     * @param pageSize
     * @return
     */

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult getComments(String adviceId, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);

        List<CommentVO> commentVOList = commentMapper.selectALLByAdviceId(adviceId);
        //agoTime进行赋值
        for (CommentVO c: commentVOList){
            String timeAgo = TimeAgoUtils.format(c.getCreateTime());
            c.setTimeAgoStr(timeAgo);
        }
        PageInfo<CommentVO> pageInfo = new PageInfo<>(commentVOList);
        PagedResult result = new PagedResult();
        result.setTotal(pageInfo.getPages());
        result.setRows(commentVOList);
        result.setRecord(pageInfo.getTotal());
        result.setPage(page);

        return result;
    }

    /**
     * 修改评论头像
     * @param id
     * @param imageUrl
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserFace(String id, String imageUrl) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("authorId",id);
        Comment comment = new Comment();
        comment.setAuthorFace(imageUrl);
        commentMapper.updateByExampleSelective(comment,example);
    }


}
