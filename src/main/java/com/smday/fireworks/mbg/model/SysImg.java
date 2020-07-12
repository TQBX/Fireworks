package com.smday.fireworks.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SysImg implements Serializable {
    @ApiModelProperty(value = "图片id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "分组id")
    private Integer groupId;

    @ApiModelProperty(value = "图片url")
    private String imgUrl;

    @ApiModelProperty(value = "是否收藏，未收藏0，收藏1")
    private String isMarked;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @ApiModelProperty(value = "上传者")
    private String uploadBy;

    @ApiModelProperty(value = "上传时间")
    private Date uploadTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsMarked() {
        return isMarked;
    }

    public void setIsMarked(String isMarked) {
        this.isMarked = isMarked;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", groupId=").append(groupId);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", isMarked=").append(isMarked);
        sb.append(", remark=").append(remark);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", uploadBy=").append(uploadBy);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}