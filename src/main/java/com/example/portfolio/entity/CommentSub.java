package com.example.portfolio.entity;

import com.example.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class CommentSub implements Serializable {

    @Autowired
    UserService userService;

    private static final long serialVersionUID = 1L;
    /**
     * 评论id
     */
    private Long id;

    /**
     * 内容id
     */
    private Long cid;

    /**
     * 内容类型
     */
    private String type;

    /**
     * 父评论id
     */
    private Long fid;

    /**
     * 评论用户id
     */
    private User commentUser;

    /**
     * 目标用户id
     */
    private User targetUser;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private LocalDateTime createDate;

    private List<CommentSub> childrenList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public User getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public List<CommentSub> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<CommentSub> childrenList) {
        this.childrenList = childrenList;
    }

    public CommentSub transComment(Comment comment){
        CommentSub temp = new CommentSub();
        temp.setId(comment.getId());
        temp.setCid(comment.getCid());
        temp.setCreateDate(comment.getCreateDate());
        temp.setType(comment.getType());
        temp.setFid(comment.getFid());
        temp.setContent(comment.getContent());
        return temp;
    }
}
