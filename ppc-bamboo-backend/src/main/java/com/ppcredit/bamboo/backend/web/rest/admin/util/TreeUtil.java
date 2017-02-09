package com.ppcredit.bamboo.backend.web.rest.admin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOResFuncDTO;

/**
 * 
 * Title: TreeUtil.java    
 * Description: 构建树形菜单，可根据用户权限来进行扩展
 * @author yang_hx       
 * @created 2015-9-1 上午11:23:10
 */
public class TreeUtil {

	private StringBuffer html = new StringBuffer();
	private List<SSOResFuncDTO> nodes;
	private static final String ENTER = "\r\n";
	private static final String TAB = "\t";
	
	public TreeUtil(List<SSOResFuncDTO> nodes){
		this.nodes = nodes;
	}
	

	/**
	 * 构建树
	 * 
	 * <li>
			<a href="index.html"> 
				<i class="fa fa-tachometer fa-fw"></i>
				<span class="menu-text">Dashboard</span> 
				<span class="selected"></span>
			</a>
		</li>
		<li class="has-sub">
			<a href="javascript:;" class=""> 
				<i class="fa fa-bookmark-o fa-fw"></i> 
				<span class="menu-text">UI Features</span> 
				<span class="arrow"></span> 
			</a>
			<ul class="sub">
				<li>
					<a href="elements.html">
						<span class="sub-menu-text">Elements</span>
					</a>
				</li>
			</ul>
		</li>
	 * @return
	 */
	public String buildTree(){  
        html.append("<ul id='ppcredit_admin_tree'>").append(ENTER);
        for (SSOResFuncDTO node : nodes) {
        	byte level = node.getLevel();
        	if(level >= 5){
        		continue;
        	}
//            String id = node.getId();
            Map<String,String> tag = buildTag(node);
            if (node.getParentid() == null) {
                html.append(TAB).append("<li "+tag.get("li") +" >").append(ENTER);
                html.append(getTAB(2)).append("<a "+tag.get("a")+" >").append(ENTER);
                html.append(getTAB(3)).append("<i class=\"fa "+node.getIcon()+" fa-fw\"></i>").append(ENTER);
                html.append(getTAB(3)).append("<span class=\"menu-text\">"+node.getName()+"</span> ").append(ENTER);
                html.append(getTAB(3)).append("<span "+tag.get("span")+" ></span>").append(ENTER);
                html.append(getTAB(2)).append("</a>").append(ENTER);
                build(node);
                html.append(TAB).append("</li>").append(ENTER);
            }
        }
        html.append("</ul>").append(ENTER); 
        return html.toString();  
    }  
	
	/**
	 * 构建子节点
	 * @param node
	 */
	private void build(SSOResFuncDTO node){  
        List<SSOResFuncDTO> children = getChildren(node);  
        if (!children.isEmpty()) {
            html.append(getTAB(2)).append("<ul class=\"sub\" >").append(ENTER);  
            for (SSOResFuncDTO child : children) {
                html.append(getTAB(3)).append("<li>").append(ENTER);
                html.append(getTAB(4)).append("<a  href=\""+child.getUrl()+"\">").append(ENTER);
                html.append(getTAB(5)).append("<span class=\"sub-menu-text\">"+child.getName()+"</span>").append(ENTER);
                html.append(getTAB(4)).append("</a>").append(ENTER);
                html.append(getTAB(3)).append("</li>").append(ENTER);
                build(child);  
            }  
            html.append(getTAB(2)).append("</ul>").append(ENTER);  
        }   
    }
	/**
	 * 得到子节点
	 * @param node
	 * @return
	 */
	private List<SSOResFuncDTO> getChildren(SSOResFuncDTO node){  
        List<SSOResFuncDTO> children = new ArrayList<SSOResFuncDTO>();  
        String id = node.getId();  
        for (SSOResFuncDTO child : nodes) {
        	byte level = child.getLevel();
        	if(level >= 5){
        		continue;
        	}
            if (id.equals(child.getParentid())) {  
                children.add(child);  
            }
        } 
        return children;  
    }
	
	/**
	 * 是否有子节点
	 * @param node
	 * @return
	 */
	private boolean hasChildren(SSOResFuncDTO node){
		String id = node.getId();
		for (SSOResFuncDTO child : nodes) {
            if (id.equals(child.getParentid())) {  
            	return true;
            }
        }
		return false;
	}
	private Map<String,String> buildTag(SSOResFuncDTO node){
		Map<String,String> m = new HashMap<String,String>();
		//如果有子节点，设定标签
		if(hasChildren(node)){
			m.put("li", "class=\"has-sub\"");
			m.put("a", "href=\"javascript:;\"");
			m.put("span", "class=\"arrow\"");
		}else{
			m.put("li", "");
			m.put("a", "href=\""+node.getUrl()+"\"");
			m.put("span", "class=\"selected\"");
		}
		return m;
	}
	
	private static String getTAB(int count){
		String t = "";
		for(int i=0 ; i<count; i++){
			t = t+ TAB;
		}
		return t;
	}
}
