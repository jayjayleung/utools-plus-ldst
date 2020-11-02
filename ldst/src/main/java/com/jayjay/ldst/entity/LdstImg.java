package com.jayjay.ldst.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JayJay
 * @since 2020-11-02
 */
public class LdstImg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 封面id
     */
    private String coverId;

    /**
     * 图片链接
     */
    private String imgUrl;

    /**
     * 图片原始链接
     */
    private String originImgUrl;

    /**
     * 顺序
     */
    private Integer sequence;

    /**
     * 创建日期
     */
    private LocalDateTime createDate;

    /**
     * 图片大小
     */
    private String size;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOriginImgUrl() {
        return originImgUrl;
    }

    public void setOriginImgUrl(String originImgUrl) {
        this.originImgUrl = originImgUrl;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "LdstImg{" +
        "id=" + id +
        ", coverId=" + coverId +
        ", imgUrl=" + imgUrl +
        ", originImgUrl=" + originImgUrl +
        ", sequence=" + sequence +
        ", createDate=" + createDate +
        ", size=" + size +
        "}";
    }
}
