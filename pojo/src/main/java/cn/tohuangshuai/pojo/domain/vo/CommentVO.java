package cn.tohuangshuai.pojo.domain.vo;

import java.util.Date;

public class CommentVO {

    private String id;

    private String comments;

    private String authorId;

    private String authorFace;

    private String authorName;

    private String adviceId;

    private String fatherCommentId;

    private String toAuthorId;

    private String toAuthorName;

    private Date createTime;

    private String timeAgoStr;

    public CommentVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFace() {
        return authorFace;
    }

    public void setAuthorFace(String authorFace) {
        this.authorFace = authorFace;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(String adviceId) {
        this.adviceId = adviceId;
    }

    public String getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(String fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public String getToAuthorId() {
        return toAuthorId;
    }

    public void setToAuthorId(String toAuthorId) {
        this.toAuthorId = toAuthorId;
    }

    public String getToAuthorName() {
        return toAuthorName;
    }

    public void setToAuthorName(String toAuthorName) {
        this.toAuthorName = toAuthorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTimeAgoStr() {
        return timeAgoStr;
    }

    public void setTimeAgoStr(String timeAgoStr) {
        this.timeAgoStr = timeAgoStr;
    }
}
