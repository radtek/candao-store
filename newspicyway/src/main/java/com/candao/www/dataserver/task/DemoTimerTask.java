package com.candao.www.dataserver.task;

import com.candao.www.dataserver.service.msghandler.MsgForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ytq on 2016/4/15.
 */
@Service
public class DemoTimerTask {
    @Autowired
    private MsgForwardService msgForwardService;
    private Timer timer;
    private String group;
    private String userId;
    private String msgType;
    private String msg;
    private Boolean isSingle;
    private Boolean isPlus;
    private Integer count = 0;
    private String str = "呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc" +
            "||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc" +
            "||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc" +
            "||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||" +
            "()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||" +
            "()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc|23abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()" +
            "()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵123abc||()()呵呵呵1";

    public synchronized void run(String group, String userId, String msgType, String msg, Boolean isSingle, int seconds, Boolean isPlus) {
        if (timer != null) {
            cancel();
        }
        this.group = group;
        this.userId = userId;
        this.msgType = msgType;
        this.msg = msg;
        this.isSingle = isSingle;
        this.timer = new Timer();
        this.isPlus = isPlus;
        timer.schedule(new RemindTask(), 0, seconds * 1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            count += 1;
            String countMsg = "";
            if (isPlus) {
                countMsg = "计数：" + count + " msg:" + msg + str;
            } else {
                countMsg = "计数：" + count + " msg:" + msg;
            }
            System.out.println("timertask run ");
            if (null == group && null == isSingle) {
                msgForwardService.broadCastMsg(userId, msgType, countMsg);
            } else {
                if (null == group) {
                    msgForwardService.broadCastMsg(userId, msgType, countMsg, isSingle);
                } else {
                    msgForwardService.broadCastMsg(group, userId, msgType, countMsg, isSingle);
                }
            }
        }
    }

    public synchronized void cancel() {
        System.out.println("timertask cancel ");
        timer.cancel();
        count = 0;
    }

    public static void main(String[] args) {
        DemoTimerTask demoTimerTask = new DemoTimerTask();
        System.out.println(demoTimerTask.str.length());
    }
}
