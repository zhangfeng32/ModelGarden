package com.modelgarden.common;

public enum OrderStatus
{
    CREATED, // 已创建
    COMMITED, // 用户已提交
    NOTIFIED, // 已通知礼仪模特
    ACCEPTED, // 礼仪模特接受
    REJECTED, // 礼仪模特拒绝
    FINISHED // 确认订单结束
}
