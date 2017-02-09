package com.ppcredit.bamboo.backend.web.rest.admin.step.controller;

import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepConfigDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.service.ProcessStepConfigService;
import com.ppcredit.bamboo.backend.web.rest.admin.step.dto.ProcessStepDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.step.service.ProcessStepService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import com.ppcredit.bamboo.backend.web.rest.admin.util.UUID;
import com.ppcredit.bamboo.backend.web.rest.type.StepType;
import com.ppcredit.bamboo.backend.web.rest.util.Page;
import com.ppcredit.bamboo.backend.web.rest.util.PageInfo;
import com.ppcredit.bamboo.backend.web.rest.util.RSTFulBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin/step")
public class ProcessStepController {

    private static Logger logger = LoggerFactory.getLogger(ProcessStepController.class); // 日志

    @Inject
    private ProcessStepService myProcessStepService;
    @Inject
    private ProcessStepConfigService processStepConfigService;

    /**
     * 节点列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/stepList")
    public String stepList(HttpServletRequest request, HttpServletResponse response) {
        logger.info("节点列表>>>>");
        return "process/step_list";
    }


    /**
     * 新建节点
     *
     * @param request
     * @return
     */
    @RequestMapping("/newStep")
    public String newStep(HttpServletRequest request,
                          Model model,
                          String processId) {
        logger.info("新建节点>>>>");
        Pager pager = myProcessStepService.query(processId, 0, 1,null);
        model.addAttribute("total",pager.getTotalNum());
        return "process/new_step";
    }
    /**
     * 得到step信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryStep/{stepId}")
    @ResponseBody
    public RSTFulBody queryStep(HttpServletRequest request,
                          Model model,
                          @PathVariable String stepId) {
        logger.info("新建节点>>>>");
        HashMap<String,Object> map=new HashMap<>();
        ProcessStepDTO processStepDTO = myProcessStepService.queryStepBystepId(stepId);

        List<ProcessStepConfigDTO> processStepConfigDTOs= processStepConfigService.queryConfigByStepId(stepId);
        map.put("processStepDTO", processStepDTO);
        map.put("processStepConfigDTOs", processStepConfigDTOs);
        return new RSTFulBody().success(map);
    }
    /**
     * 重命名流程，保存
     *
     * @throws Exception
     */
    @RequestMapping("updateStep")
    @ResponseBody
    public RSTFulBody updateStep(String stepId,
                             StepType stepType,
                             Integer nextOrders,
                             String stepDesc
    ) throws Exception {
        logger.info("流程保存>>>>");
        ProcessStepDTO processStepDTO=myProcessStepService.queryStepBystepId(stepId);
        processStepDTO.setStepType(stepType);
        processStepDTO.setStepDesc(stepDesc);
        processStepDTO.setNextOrders(String.valueOf(nextOrders));
        myProcessStepService.update(processStepDTO);
        return new RSTFulBody().success().data("修改成功");
    }

    /**
     * 出入参别名配置页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/paramConfig")
    public String paramConfig(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("出入参别名配置>>>>");
        return "process/param_config";
    }

    /**
     * 人工输入界面配置页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/inputConfig")
    public String inputConfig(HttpServletRequest request, HttpServletResponse response) {
        logger.info("人工输入界面配置>>>>");
        return "process/input_config";
    }

    /**
     * 逻辑配置页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logic/{stepType}")
    public String logic(HttpServletRequest request, HttpServletResponse response,
                        Model model,
                        @PathVariable StepType stepType) {
        logger.info("逻辑配置>>>>");
        model.addAttribute("stepType", stepType);
        return "process/logic";
    }

    /**
     * 出入参界面预览页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/end")
    public String end(HttpServletRequest request, HttpServletResponse response) {
        logger.info("出入参界面预览>>>>");
        return "process/end";
    }

    /**
     * 节点查询
     *
     * @param request
     */
    @RequestMapping("query")
    @org.springframework.web.bind.annotation.ResponseBody
    public RSTFulBody query(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("节点查询>>>>");
        String processId = request.getParameter("processId");
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));    //起始页
        int pageSize = Integer.parseInt(request.getParameter("pageSize")); //每页数

        Pager pager = myProcessStepService.query(processId, (pageNum - 1) * pageSize, pageSize,1);
        Page<HashMap> page = new Page(pageNum, pageSize);
        page.addAll(pager.getResultList());
        page.setTotal(pager.getTotalNum());
        return new RSTFulBody().success(new PageInfo(page));
    }


    /**
     * 新建节点，保存
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping("save")
    @ResponseBody
    public RSTFulBody save(HttpServletRequest request,
                           String processId,
                           String stepId,
                           StepType stepType,
                           String stepDesc,
                           String orders,
                           String nextOrders
    ) throws Exception {
        logger.info("节点保存>>>>");
        ProcessStepDTO processStepDTO=myProcessStepService.queryStepBystepId(stepId);
        try {
            if(null==processStepDTO) {//没有该step就新增
                processStepDTO = myProcessStepService.save(processId, stepId, stepType, stepDesc, orders, nextOrders);
            }else {//有就update
                processStepDTO.setStepType(stepType);
                processStepDTO.setStepType(stepType);
                processStepDTO.setStepDesc(stepDesc);
                processStepDTO.setStepDesc(stepDesc);
                processStepDTO.setNextOrders(nextOrders);
                myProcessStepService.update(processStepDTO);
            }
        } catch (Exception e) {
            logger.error("Failed to format {}", e);
            return new RSTFulBody().fail().data(e.getMessage());
        }
        return new RSTFulBody().success(processStepDTO);
    }
    /**
     * 删除节点
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping("delStep/{stepId}")
    @ResponseBody
    public RSTFulBody delStep(HttpServletRequest request,
                           @PathVariable String stepId
    )  {
        logger.info("删除节点>>>>");
        myProcessStepService.delStepByStepId(stepId);
        return new RSTFulBody().success();
    }
    /**
     * 新建节点，保存
     *
     * @throws Exception
     */
    @RequestMapping("createStepId")
    @ResponseBody
    public RSTFulBody createStepId(
    ) {
       String stepId= UUID.randomUUID().toString().replace("-", "");
        return new RSTFulBody().success(stepId);
    }


}
