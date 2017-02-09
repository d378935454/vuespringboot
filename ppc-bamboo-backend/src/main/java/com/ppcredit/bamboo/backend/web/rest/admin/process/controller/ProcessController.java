package com.ppcredit.bamboo.backend.web.rest.admin.process.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppcredit.bamboo.backend.web.rest.admin.process.dto.ProcessDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.process.service.ProcessService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.ConstantsUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import com.ppcredit.bamboo.backend.web.rest.admin.util.web.WebUtil;
import com.ppcredit.bamboo.backend.web.rest.util.Page;
import com.ppcredit.bamboo.backend.web.rest.util.PageInfo;
import com.ppcredit.bamboo.backend.web.rest.util.RSTFulBody;

/**
 * 新建流程
 *
 * @date: 2017年1月6日10:48:02
 */
@Controller
@RequestMapping("/admin/process")
public class ProcessController {

    private static Logger logger = LoggerFactory.getLogger(ProcessController.class); // 日志

    @Inject
    private ProcessService myProcessService;

    /**
     * 选择appKey页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/appKey")
    public String appKey(HttpServletRequest request, HttpServletResponse response) {
        logger.info("选择appKey>>>>");
        return "process/app_key";
    }

    /**
     * process_list页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/process_list")
    public String processList(HttpServletRequest request, HttpServletResponse response) {
        logger.info("process_list页面>>>>");
        return "process/process_list";
    }

    /**
     * 初始化appkey列表
     *
     * @param request
     * @return
     */
    @RequestMapping("appkey_list")
    public String appkeyList(HttpServletRequest request
            , HttpServletResponse response
            , Model model
    ) {
        logger.info("初始化appkey列表>>>>");
        return "process/appkey_list";
    }

    /**
     * 流程查询
     *
     * @param request
     */
    @RequestMapping("query")
    @ResponseBody
    public RSTFulBody query(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("流程查询>>>>");
        String param = request.getParameter("param");
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));    //起始页
        int pageSize = Integer.parseInt(request.getParameter("pageSize")); //每页数

        Pager pager = myProcessService.queryByName(param, (pageNum - 1) * pageSize, pageSize);
        Page<HashMap> page = new Page(pageNum, pageSize);
        page.addAll(pager.getResultList());
        page.setTotal(pager.getTotalNum());
        return new RSTFulBody().success(new PageInfo(page));
    }

    /**
     * 新建流程，保存
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("save")
    @ResponseBody
    public RSTFulBody save(HttpServletRequest request, HttpServletResponse response) {
        logger.info("流程保存>>>>");
        String appKey = ""; // 流程名称
        String processName = request.getParameter("processName"); // 流程名称
        String processDesc = request.getParameter("processDesc"); // 流程描述
        ProcessDTO processDTO = myProcessService.save(appKey, processName, processDesc);
        return new RSTFulBody().success(processDTO).data("新建成功");
    }

    /**
     * 重命名流程，保存
     *
     * @throws Exception
     */
    @RequestMapping("update")
    @ResponseBody
    public RSTFulBody update(int id,
                             String processName,
                             String processDesc
    )  {
        logger.info("流程保存>>>>");
        ProcessDTO processDTO = myProcessService.update(id, processName, processDesc);
        return new RSTFulBody().success(processDTO).data("新建成功");
    }

    /**
     * 删除流程
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("delProcess/{processId}")
    @ResponseBody
    public RSTFulBody delProcessByProcessId(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @PathVariable String processId
    )  {
        logger.info("删除节点>>>>");
        myProcessService.delProcessByProcessId(processId);
        return new RSTFulBody().success();
    }


    /**
     * appkey列表
     * @param request
     * @return
     */
    @RequestMapping("/process_appkey_manage")
    public String processAppkeyManage(HttpServletRequest request
            , HttpServletResponse response
            , Model model
    ) {
        logger.info("初始化appkey manage列表>>>>");
        model.addAttribute("treeName","流程appkey关联管理");
        return "process/process_appkey_manage";
    }
    
    /**
     * 流程appkey关联配置
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     * @throws IOException 
     */
    @RequestMapping("/process_appkey")
    public void processAppkey(HttpServletRequest request, HttpServletResponse response) throws  Exception {
        logger.info("process_list页面>>>>");
        	Map<String,Object> map = new HashMap();
        	String appkey = request.getParameter("appKey");
        	List<ProcessDTO> processList = myProcessService.queryProcessList();
        	List<ProcessDTO> processCheckList = myProcessService.queryCheckedProcessList(appkey);
        	map.put("processList", processList);
        	map.put("processCheckList", processCheckList);
        	com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody  
        	body = new com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody("success", "保存成功", map);
        	WebUtil.write(response, body.getBody(),ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
    }
    
    /**
     * 保存
     * @param request
     * @param response
     * @throws Exception
     */
	@RequestMapping("saveProcessAppkey")
	public void saveProcessAppkey(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String appKey = request.getParameter("userId"); // userId对应appKey
		String ids = request.getParameter("ids"); // 选中的checkbox
		com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody  body =  null;
		try {
			myProcessService.saveProcessAppkey(appKey, ids);
			body = new com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody("success", "保存成功", "保存成功");
		} catch (Exception e) {
			body = new com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody("error", "保存失败", "保存失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(),ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}

	/**
	 * 继续修改
	 */
	@RequestMapping("loadProcessConfiguration")
	public void loadProcessConfiguration(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<ProcessDTO> processList = myProcessService.queryProcessList();
		map.put("processList", processList);
		com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody
    	body = new com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody("success", "保存成功", map);
		WebUtil.write(response, body.getBody(),ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}

}
