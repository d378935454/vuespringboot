package com.ppcredit.bamboo.backend.web.rest.admin.login.handler;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOResFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.service.SSOLoginService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.web.WebUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.service.SSOResFuncService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.AdminUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.ConstantsUtil;
import com.ppcredit.bamboo.backend.web.rest.util.RSTFulBody;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: SSOAuthInteceptor.java
 * Description:
 * 登录权限控制，主要责任有：
 * 1、验证用户是否有权限登录系统
 * 2、读取cookie，得到用户登录token，查询用户对象，设置session
 *
 * @author yang_hx
 * @created 2015-9-1 上午11:27:02
 */
@Component
public class SSOAuthInteceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(SSOAuthInteceptor.class); // 日志

    @Inject
    private SSOLoginService mySSOLoginService;

    @Inject
    private SSOResFuncService mySSOResFuncService;


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //逻辑
        //首先判断用户DTO的session是否存在
        SSOUserDTO user = AdminUtil.getUserSession(request);
        List<SSORoleDTO> roles = AdminUtil.getRoleSession(request);
        boolean isAjax = request.getHeader("x-requested-with") != null;//是否ajax请求
        int maxAge = -1;
        String loginUrl = "/login/go_login";
        //是否勾选7天免登
        if (AdminUtil.is7freeLogin(request)) {
            maxAge = 3600 * 24 * 7;
        }

        if (user != null) {
            SSOUserDTO curUser = mySSOLoginService.findByid(user.getId());
            if (ConstantsUtil.STATUS_FAILURE.equals(curUser.getUserStatus())) {    //冻结账户退出重新登录
//                sendRedirect(isAjax, request, response, loginUrl);
                return false;
            }
            if (!curUser.getUserLoginPassword().equals(user.getUserLoginPassword())) {  //修改密码退出重新登录
//                sendRedirect(isAjax, request, response, loginUrl);
                return false;
            }
            if (!curUser.getIsAllcustomer().equals(user.getIsAllcustomer())) {          //修改所有客户重新登录
//                sendRedirect(isAjax, request, response, loginUrl);
                return false;
            }
            //如果存在，则通过，同时写入cookie
            AdminUtil.setUserCookie(response, user, maxAge);
            return roleCheck(request, response, user, roles);
        }
        log.info("<<<用户session为空，进行判断cookie>>>");
        //不存在:
        // 读cooike，是否存在
        String token = AdminUtil.getUserCookieToken(request);
        log.info("用户cookie为>>>{}", token);
        if (token != null) {
            // 存在，则得到对应token，查询对象,查询到，则注入session 通过同时刷新cookie
            user = mySSOLoginService.findByid(token);
            if (user != null) {
                roleCheck(request, response, user, roles);
                AdminUtil.setUserSession(request, user);
                AdminUtil.setUserCookie(response, user, maxAge);
                log.info("读取用户token成功，用户>>>{}<<<", user.getUserLoginName());
                return roleCheck(request, response, user, roles);
            }
        }
        // 不存在，则跳转到登录页ss
        sendRedirect(isAjax, request, response, loginUrl);
//		request.getRequestDispatcher("/login/go_login").forward(request, response);
        return false;
    }

    /**
     * 跳转登录页
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void sendRedirect(boolean isAjax,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              String url) throws IOException {
        if (isAjax) {
            writeJson(response,
                    JSONObject.fromObject(new RSTFulBody().sessionTimeOut(request.getContextPath() + url)),
                    ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
        } else {
            response.sendRedirect(request.getContextPath() + url);
        }
    }

    private List<SSORoleDTO> getRoles(HttpServletRequest request, SSOUserDTO user) {
        List<SSORoleDTO> roles = mySSOResFuncService.listRoles(user);
        if (roles == null || roles.size() < 1) {
            log.info("<<<用户没有指定角色>>> {}", user.getUserLoginName());
            return null;
        }
        AdminUtil.setRoleSession(request, roles);
        return roles;
    }

    private List<SSORoleFuncDTO> getRoleFuncs(HttpServletRequest request, SSOUserDTO user, List<SSORoleDTO> roles) {
        List<SSORoleFuncDTO> rf = mySSOResFuncService.listRoleFuncs(roles);
        if (rf == null || rf.size() < 1) {
            log.info("<<<角色没有指定菜单>>> {}>>>", user.getUserLoginName());
            return null;
        }
        for (SSORoleFuncDTO ssoRoleFuncDTO : rf) {
            ssoRoleFuncDTO.getFunc();
            ssoRoleFuncDTO.getRole();
        }
        AdminUtil.setRoleFuncsSession(request, rf);
        return rf;
    }

    private Map<String, SSOResFuncDTO> getFuncs(HttpServletRequest request, SSOUserDTO user, List<SSORoleDTO> roles) {
        Map<String, SSOResFuncDTO> s = new HashMap<String, SSOResFuncDTO>();
        List<SSOResFuncDTO> rf = mySSOResFuncService.list();
        if (rf == null || rf.size() < 1) {
            log.info("<<<角色没有指定菜单>>> {}>>>", user.getUserLoginName());
            return null;
        }
        for (SSOResFuncDTO sSOResFuncDTO : rf) {
            s.put(sSOResFuncDTO.getId(), sSOResFuncDTO);
        }
        AdminUtil.setFuncsSession(request, s);
        return s;
    }

    private boolean roleCheck(HttpServletRequest request, HttpServletResponse response, SSOUserDTO user, List<SSORoleDTO> roles) throws Exception {
        /**
         * 判断角色
         */
        if (roles == null) {
            roles = getRoles(request, user);
            if (roles == null) {
                //指定一个提示界面
                WebUtil.write(response, "用户没有指定角色", ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
                return false;
            }
        }
        /**
         * 判断超级管理员
         */
        boolean flag = true;
        for (SSORoleDTO r : roles) {
            if (r.getAuthAdmin() == 1) {
                Map<String, SSOResFuncDTO> rf = AdminUtil.getFuncsSession(request);
                if (rf == null) {
                    rf = getFuncs(request, user, roles);
                    if (rf == null) {
                        WebUtil.write(response, "角色没有指定菜单", ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
                        return false;
                    }
                }
                flag = false;
            }
        }
        /**
         * 判断菜单
         */
        if (flag) {
            List<SSORoleFuncDTO> rf = AdminUtil.getRoleFuncsSession(request);
            if (rf == null) {
                rf = getRoleFuncs(request, user, roles);
                if (rf == null) {
                    WebUtil.write(response, "角色没有指定菜单", ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    /**
     * 向客户端输出json
     * @author yang_hx
     * @creationDate. 2010-9-18 上午00:19:16
     * @response 响应对象
     * @param outObj 输出的Object
     * @param outEncoding 输出编码
     * @throws IOException
     */
    public void writeJson(HttpServletResponse response, Object outObj, String outEncoding) throws IOException {
        // 设置默认响应类型
        response.setContentType("application/json");
        response.setCharacterEncoding(outEncoding);
        response.setStatus(302);
        PrintWriter out = response.getWriter();
        out.print(outObj);
        out.flush();
        out.close();
    }
}
