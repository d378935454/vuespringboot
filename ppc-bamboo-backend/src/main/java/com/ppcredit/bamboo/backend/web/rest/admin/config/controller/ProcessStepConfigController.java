package com.ppcredit.bamboo.backend.web.rest.admin.config.controller;

import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.Param;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepArgumentDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepConfigDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepLogicDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.service.ProcessStepConfigService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.AdminUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.UUID;
import com.ppcredit.bamboo.backend.web.rest.admin.util.text.StringUtils;
import com.ppcredit.bamboo.backend.web.rest.type.ArgType;
import com.ppcredit.bamboo.backend.web.rest.util.RSTFulBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.github.abel533.echarts.code.SeriesType.map;


@Controller
@RequestMapping("/admin/config")
public class ProcessStepConfigController {

    private static Logger logger = LoggerFactory.getLogger(ProcessStepConfigController.class); // 日志
    private static String processStepArgument = "processStepArgumentDTOs";
    private static String inputList = "inputList";

    @Inject
    private ProcessStepConfigService myProcessStepConfigService;

    @RequestMapping("query_apiList")
    @ResponseBody
    public Object queryApiList(HttpServletRequest request, HttpServletResponse response) {
        // 获取session中的appKey
        String appKey = AdminUtil.getAppKey(request);
        String type = request.getParameter("type");
        Map<String, Object> map = new HashMap();
        List<Param> apiList = myProcessStepConfigService.queryApiList(appKey, type);
        map.put("apiList", apiList);
        return map;
    }

    /**
     * @return
     */
    @RequestMapping("query_Arg")
    @ResponseBody
    public RSTFulBody queryOutArg(String processId
            , String stepId) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap();
        List<ProcessStepArgumentDTO> arguments = myProcessStepConfigService.queryArgumentByProcessId(processId, stepId);
        List configs = myProcessStepConfigService.queryConfigByprocessId(processId, stepId);
        map.put("arguments", arguments);
        map.put("configs", configs);
        return new RSTFulBody().success(map);
    }

    /**
     * 得到configInterface信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryInterface/{stepId}/{configId}")
    @ResponseBody
    public RSTFulBody queryInterface(HttpServletRequest request,
                                     Model model,
                                     @PathVariable String configId,
                                     @PathVariable String stepId
    ) {
        HashMap<String, Object> map = new HashMap<>();

        List<ProcessStepArgumentDTO> processStepArgumentDTOs = myProcessStepConfigService.queryArgumentByConfigId(configId, null);
        List<ProcessStepConfigDTO> processStepConfigDTOs = myProcessStepConfigService.queryConfigByStepId(stepId);
        map.put(processStepArgument, processStepArgumentDTOs);
        map.put("processStepConfigDTOs", processStepConfigDTOs.isEmpty() ? null : processStepConfigDTOs.get(0));
        return new RSTFulBody().success(map);
    }

    /**
     * 节点类型:接口，保存
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("saveInterface")
    @ResponseBody
    public RSTFulBody saveInterface(
                                    String stepId,
                                    String configId,
                                    String apiId,
                                    @RequestBody ConcurrentMap<String, List<LinkedHashMap<String,String>>> map
    )  {
        logger.info("节点保存>>>>");

        ProcessStepConfigDTO psc;
        if (StringUtils.isEmpty(configId)) {//新增
            configId = UUID.randomUUID().toString().replace("-", "");
            psc = myProcessStepConfigService.save(stepId, configId, apiId,map.get(inputList), map.get("outList"));
        } else {
            psc = myProcessStepConfigService.updateInterface(stepId, configId, apiId, map.get(inputList), map.get("outList"));
        }
        myProcessStepConfigService.delRefenrenceArg();
        return new RSTFulBody().success(psc).data("保存成功");
    }


    /**
     * 得到Input信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryInput/{configId}")
    @ResponseBody
    public RSTFulBody queryInput(HttpServletRequest request,
                                 Model model,
                                 @PathVariable String configId) {
        HashMap<String, Object> map = new HashMap<>();
        List<ProcessStepArgumentDTO> processStepArgumentDTOs = myProcessStepConfigService.queryArgumentByConfigId(configId, null);
        map.put(processStepArgument, processStepArgumentDTOs);
        return new RSTFulBody().success(map);
    }

    /**
     * 创建出参
     *
     * @param request
     * @return
     */
    @RequestMapping("/createOutParam")
    @ResponseBody
    public RSTFulBody createOutParam(HttpServletRequest request,
                                     String argparam,
                                     int configId){

        return new RSTFulBody().success(myProcessStepConfigService.createOutParam(configId, argparam));
    }

    /**
     * 节点类型:用户输入，保存
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("saveInput")
    @ResponseBody
    public RSTFulBody saveInput(HttpServletRequest request,
                                String stepId,
                                String configId,
                                ArgType argType,
                                @RequestBody List<Map<String, String>> argName)  {
        logger.info("用户输入保存>>>>");
        if (StringUtils.isEmpty(configId)) {//新增
            configId = UUID.randomUUID().toString().replace("-", "");
            myProcessStepConfigService.save(stepId, configId, argType, argName);
        } else {//先删除 再修改
            myProcessStepConfigService.delArgumentByConfigId(configId);
            myProcessStepConfigService.save(stepId, configId, argType, argName);
        }
        myProcessStepConfigService.delRefenrenceArg();
        return new RSTFulBody().success("").data("保存成功");
    }

    /**
     * 得到configLogic信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryLogic/{stepId}/{configId}")
    @ResponseBody
    public RSTFulBody queryLogic(HttpServletRequest request,
                                 Model model,
                                 @PathVariable String configId,
                                 @PathVariable String stepId
    ) {
        logger.info("新建节点>>>>");
        HashMap<String, Object> map = new HashMap<>();

        List<ProcessStepArgumentDTO> processStepArgumentDTOs = myProcessStepConfigService.queryArgumentByConfigId(configId, null);
        List<ProcessStepLogicDTO> processStepLogicDTOs = myProcessStepConfigService.queryLogicByConfigId(configId);
        List<ProcessStepConfigDTO> processStepConfigDTOs = myProcessStepConfigService.queryConfigByStepId(stepId);
        map.put(processStepArgument, processStepArgumentDTOs);
        map.put("processStepLogicDTOs", processStepLogicDTOs);
        map.put("processStepConfigDTOs", processStepConfigDTOs.isEmpty() ? null : processStepConfigDTOs.get(0));
        return new RSTFulBody().success(map);
    }

    /**
     * 节点类型:本地逻辑，保存
     *
     * @param request
     * @param argName 包名名称
     * @param stepId  节点id
     * @return
     * @throws Exception
     */
    @RequestMapping("saveLogic")
    @ResponseBody
    public RSTFulBody saveLogic(HttpServletRequest request,
                                String configId,
                                String stepId,
                                String argName,
                                @RequestBody ConcurrentMap<String, Object> map
    )  {
        logger.info("逻辑保存>>>>");
        ArgType argType = ArgType.INPUT;// 参数类型
        String jarMethod = (String) map.get("jarMethod");
//        String type = null;    // 类型
        ProcessStepConfigDTO psa;
        if (StringUtils.isEmpty(configId)) {//新增
            configId = UUID.randomUUID().toString().replace("-", "");
                psa = myProcessStepConfigService.save(null, stepId, configId, argType, jarMethod, (List<LinkedHashMap<String, String>>) map.get(inputList), (List<LinkedHashMap<String, String>>) map.get("codeList"));
        } else {
            psa = myProcessStepConfigService.updateLogic(null, stepId, configId, argType, jarMethod, (List<LinkedHashMap<String, String>>) map.get(inputList), (List<LinkedHashMap<String, String>>) map.get("codeList"));
        }
        myProcessStepConfigService.delRefenrenceArg();
        return new RSTFulBody().success(psa).data("保存成功");
    }
    /**
     * 节点类型:结束输出，保存
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("saveEnd")
    @ResponseBody
    public RSTFulBody saveEnd(HttpServletRequest request,
                                String stepId,
                                String configId,
                                ArgType argType,
                                @RequestBody List<Map<String, String>> argName)  {
        logger.info("用户输入保存>>>>");
        if (StringUtils.isEmpty(configId)) {//新增
            configId = UUID.randomUUID().toString().replace("-", "");
            myProcessStepConfigService.save(stepId, configId, argType, argName);
        } else {//先删除 再修改
            myProcessStepConfigService.delArgumentByConfigId(configId);
            myProcessStepConfigService.save(stepId, configId, argType, argName);
        }
        myProcessStepConfigService.delRefenrenceArg();
        return new RSTFulBody().success("").data("保存成功");
    }

}
