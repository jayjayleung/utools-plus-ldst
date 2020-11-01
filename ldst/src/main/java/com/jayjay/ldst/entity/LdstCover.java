package com.jayjay.ldst.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JayJay
 * @since 2020-11-01
 */
public class LdstCover implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String cover;

    /**
     * 封面图片url
     */
    private String coverImgUrl;

    /**
     * 图片数量
     */
    private Integer total;

    /**
     * 类型
     */
    private String type;

    /**
     * 图片日期
     */
    private LocalDateTime date;

    /**
     * 爬取日期
     */
    private LocalDateTime createDate;

    /**
     * 原始详情链接
     */
    private String originUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    @Override
    public String toString() {
        return "LdstCover{" +
        "id=" + id +
        ", title=" + title +
        ", cover=" + cover +
        ", coverImgUrl=" + coverImgUrl +
        ", total=" + total +
        ", type=" + type +
        ", date=" + date +
        ", createDate=" + createDate +
        ", originUrl=" + originUrl +
        "}";
    }
}
