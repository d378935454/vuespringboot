package com.ppcredit.bamboo.backend.web.rest.admin.manage.controller;

import com.ppcredit.bamboo.backend.web.rest.admin.util.AdminUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import com.ppcredit.bamboo.backend.web.rest.admin.util.web.WebUtil;
import com.ppcredit.bamboo.backend.web.rest.util.Page;
import com.ppcredit.bamboo.backend.web.rest.util.PageInfo;
import com.ppcredit.bamboo.backend.web.rest.util.RSTFulBody;
import com.ppcredit.bamboo.backend.web.rest.admin.manage.service.UserManageService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.ConstantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 * Date: 2016-11-23 14:21:49
 */
@Controller
@RequestMapping("/admin/manage")
public class UserManageController {

    private static Logger logger = LoggerFactory.getLogger(UserManageController.class); // 日志

    @Inject
    private UserManageService myUserManageService;


    /**
     * 初始化页面
     *
     * @param request
     * @return
     */
    @RequestMapping("user_manage")
    public String userManage(HttpServletRequest request, HttpServletResponse response) {
        return "user_manage";
    }

    /**
     * 初始化页面
     *
     * @param request
     * @return
     */
    @RequestMapping("demo_tab")
    public String demoTab(HttpServletRequest request
            , HttpServletResponse response
                          ,Model model
    ) {
        logger.info("初始化流程新增>>>>");
        model.addAttribute("treeName","流程新增");
        return "process/main";
    }
    /**
     * 初始化流程管理
     *
     * @param request
     * @return
     */
    @RequestMapping("process_manage")
    public String processManage(HttpServletRequest request
            , HttpServletResponse response
                          ,Model model
    ) {
        logger.info("初始化流程管理>>>>");
        model.addAttribute("treeName","流程查询");
        return "process/process_manage";
    }


    /**
     * 条件，列表查询
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("query")
    @org.springframework.web.bind.annotation.ResponseBody
    public Object query(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("查询>>>>");

        int offset = Integer.parseInt(request.getParameter("iDisplayStart"));    //起始点
        int pagesize = Integer.parseInt(request.getParameter("iDisplayLength")); //每页数
        String status = request.getParameter("status");
        String searchCondition = request.getParameter("searchCondition");
        logger.info(status + "---------" + searchCondition);

        Map<String, Object> map = new HashMap();
        Pager pager = myUserManageService.query(status, searchCondition, offset, pagesize);
        pager.getResultList();
        map.put("aaData", pager.getResultList());
        map.put("iTotalRecords", pager.getTotalNum());
        map.put("iTotalDisplayRecords", pager.getTotalNum());
        return map;
    }

    /**
     * 六合条件，列表查询
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("queryP")
    @org.springframework.web.bind.annotation.ResponseBody
    public RSTFulBody queryP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("查询>>>>");

        int pageNum = Integer.parseInt(request.getParameter("pageNum"));    //起始页
        int pageSize = Integer.parseInt(request.getParameter("pageSize")); //每页数
        String status = request.getParameter("status");
        String searchCondition = request.getParameter("searchCondition");
        logger.info(status + "---------" + searchCondition);

        Pager pager = myUserManageService.query(status, searchCondition, (pageNum-1)*pageSize, pageSize);
        pager.getResultList();

        Page<HashMap> page = new Page(pageNum,pageSize);
        page.addAll(pager.getResultList());
        page.setTotal(pager.getTotalNum());
        return  new RSTFulBody().success(new PageInfo(page));
    }
    /**
     * 编辑appKey成全局
     *
     * @param request
     * @param response
     */
    @RequestMapping("save")
    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String realName = request.getParameter("realName");
        String appKey = request.getParameter("appKey");
        logger.info("选中保存>>>>", realName, appKey);
        // 删除之前的appKey
            AdminUtil.removeAppKey(request);
            // 重新设置appKey
            AdminUtil.setAppKey(request, realName, appKey);
        WebUtil.write(response,"操作成功", ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
    }

}