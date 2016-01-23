package com.modelgarden.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.modelgarden.common.ResultMessage;

/**
 * 主页控制器
 * @author Administrator
 *
 */

@Controller
public class HomePageController
{
    /**
     * 获取主页的详细内容
     * @return
     */
    @RequestMapping(value = "/getHomepageInfo", method = RequestMethod.POST)
    public Object getHomepageInfo()
    {
        ResultMessage rm  = new ResultMessage();
        rm.setResult(0);
        rm.setMessage("Success to invoke getHomepageInfo function, It's a test!");
        return new ResultMessage();
    }
}
