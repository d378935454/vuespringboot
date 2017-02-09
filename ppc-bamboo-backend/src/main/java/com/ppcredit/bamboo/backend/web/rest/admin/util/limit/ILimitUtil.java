/**
 * 
 */
package com.ppcredit.bamboo.backend.web.rest.admin.util.limit;

import javax.servlet.http.HttpServletRequest;


/**
 * <ul>
 * <li> <b>目的:</b> <br />
 * <p>
 * 分页助手类核心接口
 * </p>
 * </li>
 * <li><b>采用的不变量：</b></li>
 * <li><b>并行策略：</b></li>
 * <li> <b>修改历史：</b><br />
 * <p>
 * 创建:Nov 13, 2007 11:49:58 AM<br />
 * 
 * </p>
 * </li>
 * <li><b>已知问题：</b></li>
 * </ul>
 */

public interface ILimitUtil
{
	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取分页信息</li>
	 * <li>适用的前提条件</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：</li>
	 * </ul>
	 * </p>
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @param tableId
	 *            表ID:作为字段前缀使用，以防止参数或变量名称与业务功能变量名称冲突,如果为null，则使用默认的变量ex
	 * @param rowDisplayed
	 *            默认每页显示行数, 如果rowDisplayed小于或等于0，则不进行分页操作
	 * @return
	 */
	public LimitInfo getLimitInfo(HttpServletRequest request, String tableId,
                                  int rowDisplayed);

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：设置分页信息</li>
	 * <li>适用的前提条件</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题:</li>
	 * <li>调用的例子：</li>
	 * </ul>
	 * </p>
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @param limitInfo
	 *            {@link LimitInfo}
	 */
	public void setLimitInfo(HttpServletRequest request, LimitInfo limitInfo);
	
	/**
	 * 是否excel导出
	 */
	public boolean isExported();
}
