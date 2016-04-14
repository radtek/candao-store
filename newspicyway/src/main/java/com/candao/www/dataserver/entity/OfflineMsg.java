package com.candao.www.dataserver.entity;

import java.util.Date;

/**
 * Created by ytq on 2016/3/17.
 */
public class OfflineMsg {
    private Integer id;
    private String msgType;
    private String content;
    private String deviceGroup;
    private String deviceId;
    private Integer isSingle;
    private Date createTime;

    public OfflineMsg() {

    }

    public OfflineMsg(String msgType, String content, String deviceGroup, String deviceId, Integer isSingle) {
        this.msgType = msgType;
        this.content = content;
        this.deviceGroup = deviceGroup;
        this.deviceId = deviceId;
        this.isSingle = isSingle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Integer isSingle) {
        this.isSingle = isSingle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(String deviceGroup) {
        this.deviceGroup = deviceGroup;
    }
}