package com.ppcredit.bamboo.backend.web.rest.admin.login.handler;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOResFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.service.SSOResFuncService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.AdminUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.TreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * Title: TreeViewInteceptor.java    
 * Description: 左部导航菜单树形结构验证
 * 检查用户可查看的菜单树，在view层展示，如果session中有则不需要反复查询树形结构
 * @author yang_hx       
 * @created 2015-9-1 上午11:31:42
 */
@Component
public class TreeViewInteceptor implements HandlerInterceptor {
	
	private static Logger log = LoggerFactory.getLogger(TreeViewInteceptor.class); // 日志
	
	@Resource
	private SSOResFuncService mySSOResFuncService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		SSOUserDTO user = AdminUtil.getUserSession(request);
		//判断用户session中是否有树形菜单
		String tree = AdminUtil.getTreeView(request);
		
		if(tree == null){
			log.info("用户>>>{}<<<重新生成tree",user.getUserLoginName());
			//得到用户角色，角色得到菜单
			List<SSOResFuncDTO> funcs = mySSOResFuncService.getUserFuncsList(user);
			if(funcs != null && funcs.size()>0){
				//生成树形结构
				TreeUtil u = new TreeUtil(funcs);
				tree = u.buildTree();
				//存入session
				AdminUtil.setTreeView(request, tree);
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

}
