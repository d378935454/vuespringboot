/**
 * 
 */
package com.ppcredit.bamboo.backend.web.rest.admin.util.limit;

import com.ppcredit.bamboo.backend.web.rest.admin.util.GlobalConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <ul>
 * <li> <b>目的:</b> <br />
 * <p>
 * 存储分页信息与查询条件变量
 * </p>
 * </li>
 * <li><b>采用的不变量：</b></li>
 * <li><b>并行策略：</b></li>
 * <li> <b>修改历史：</b><br />
 * <p>
 * 创建:Oct 31, 2007 1:11:07 PM<br />
 * 
 * </p>
 * </li>
 * <li><b>已知问题：</b>
 * <p>
 * 对注入攻击的的预防也显的丑陋，另：基于上，查询时，查询串中不能含有单引号.
 * </p>
 * </li>
 * </ul>
 */
public class LimitInfo
{

	// 是否需要编码
	private static String LIMIT_HQL_NEEDENCODE = "limit_hql_needEncode";

	// 转码规则从LIMIT_HQL_ENCODE_FROM到LIMIT_HQL_ENCODE_TO
	private static String LIMIT_HQL_ENCODE_FROM = "application_encoding";
	private static String LIMIT_HQL_ENCODE_TO = "database_encoding";

	private static boolean needEncodingHql = GlobalConfig.getBooleanProperty(
			"sys.core", LIMIT_HQL_NEEDENCODE);

	private static String limitHqlEncodeFrom = GlobalConfig.getProperty(
			"sys.core", LIMIT_HQL_ENCODE_FROM);

	private static String limitHqlEncodeTo = GlobalConfig.getProperty(
			"sys.core", LIMIT_HQL_ENCODE_TO);

	private static Log log = LogFactory.getLog(LimitInfo.class);

	private Object storeEx;

	private Map filterMap;

	// 排序信息
	private String sortProperty;

	// 排序方式
	private String sortType = "asc";

	// 页码
	private int pageNum = 1;

	// 每页显示行数
	private int rowDisplayed;

	// 总行数
	private int totalNum;

	// 显示结束行
	private int endLineNum;

	// 第一次进入列表页面
	private boolean firstEnter;

	// 存储action分页地址
	private String action;

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取总共多少页</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 */
	public int getTotalPage()
	{
		int i = totalNum % rowDisplayed;
		return i == 0 ? totalNum / rowDisplayed : totalNum / rowDisplayed + 1;
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：设置过滤串,查询条件</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：
	 * <p>
	 * LimitInfo limitInfo = new ExLimitUtil(request, null).getLimitInfo();<br />
	 * limitInfo.setFilterProperty("name", "=name");//使用等于匹配name='name'<br />
	 * limitInfo.setFilterProperty("name", "name");//使用模糊匹配name like '%name%'<br />
	 * </p>
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @param property
	 * @param value
	 */
	public void addFilterProperty(HqlProperty property)
	{
		if (filterMap == null) {
			filterMap = new HashMap();
		}

		if (property == null)
			return;
		filterMap.put(property.getPropertyName(), property);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：
	 * <p>
	 * 获取查询参数组合的查询条件和引用查询的属性-值Map， 对于useRefQuery值为false的属性将直接构造为字符串查询，
	 * 为true的将为引用查询
	 * </p>
	 * </li>
	 * <li>适用的前提条件:如使用引用查询，请保证设置查询条件时的值与POJO对象的数据类型一致，否则可能出现转型错误！</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：</li>
	 * </ul>
	 * </p>
	 * 
	 * @param as
	 * @return
	 */
	public Object[] getWhereHQL(String as)
	{
		Object[] re = new Object[2];
		re[0] = "";

		if (filterMap == null || filterMap.size() == 0)
			return re;

		StringBuffer sb = new StringBuffer();
		Map paramMap = new HashMap();

		Iterator it = filterMap.keySet().iterator();
		while (it.hasNext()) {
			String propertyName = (String) it.next();
			HqlProperty property = (HqlProperty) filterMap.get(propertyName);
			if (property == null)
				continue;
			// map中索引key可以与name不一样
			String name = property.getPropertyName();
			String valueName = name.replaceAll("\\.", "");
			valueName=valueName.replaceAll("\\-", "");
			Object min = property.getMin();
			Object max = property.getMax();

			if (min == null && max == null) {
				continue;
			}

			if (!property.isUseRefQuery()) {
				if ("".equals(min) && "".equals(max)) {
					continue;
				}

				switch (property.getChkType()) {
				case 1:
					// eq
					sb.append(" and " + as + "." + name + " = '" + min + "'");
					break;
				case 2:
					// like
					sb
							.append(" and " + as + "." + name + " like '" + min
									+ "'");
					break;
				case 3:
					break;
				case 4:
					break;
				}

			} else {
				switch (property.getChkType()) {
				case 1:
					// eql
					sb.append(" and " + as + "." + name + " = :" + valueName);
					paramMap.put(valueName, min);
					break;
				case 2:
					// like
					sb
							.append(" and " + as + "." + name + " like :"
									+ valueName);
					paramMap.put(valueName, min);
					break;
				case 3:
					// compare
					if (min != null && max == null) {
						sb.append(" and " + as + "." + name + " > :"
								+ valueName);
						paramMap.put(valueName, min);
					} else {
						if (min == null && max != null) {
							sb.append(" and " + as + "." + name + " <:"
									+ valueName);
							paramMap.put(valueName, max);
						} else {
							if (min != null && max != null) {
								sb.append(" and (" + as + "." + name + " > :"
										+ valueName + "Min and " + as + "."
										+ name + " <:" + valueName + "Max)");
								paramMap.put(valueName + "Min", min);
								paramMap.put(valueName + "Max", max);
							}
						}
					}
					break;
				case 4:
					// in
					sb.append(" and " + as + "." + name + " in (:" + valueName
							+ ")");
					paramMap.put(valueName, property.getMin());
					break;
				case 5:
					// compare minEq
					if (min != null && max == null) {
						sb.append(" and " + as + "." + name + " >= :"
								+ valueName);
						paramMap.put(valueName, min);
					} else {
						if (min != null && max != null) {
							sb.append(" and (" + as + "." + name + " >= :"
									+ valueName + "Min and " + as + "." + name
									+ " <:" + valueName + "Max)");
							paramMap.put(valueName + "Min", min);
							paramMap.put(valueName + "Max", max);
						}
					}
					break;
				case 6:
					// compare maxEq
					if (min == null && max != null) {
						sb.append(" and " + as + "." + name + " <=:"
								+ valueName);
						paramMap.put(valueName, max);
					} else {
						if (min != null && max != null) {
							sb.append(" and (" + as + "." + name + " > :"
									+ valueName + "Min and " + as + "." + name
									+ " <=:" + valueName + "Max)");
							paramMap.put(valueName + "Min", min);
							paramMap.put(valueName + "Max", max);
						}
					}
					break;
				case 7:
					// compare eq
					if (min != null && max == null) {
						sb.append(" and " + as + "." + name + " >= :"
								+ valueName);
						paramMap.put(valueName, min);
					} else {
						if (min == null && max != null) {
							sb.append(" and " + as + "." + name + " <=:"
									+ valueName);
							paramMap.put(valueName, max);
						} else {
							if (min != null && max != null) {
								sb.append(" and (" + as + "." + name + " >= :"
										+ valueName + "Min and " + as + "."
										+ name + " <=:" + valueName + "Max)");
								paramMap.put(valueName + "Min", min);
								paramMap.put(valueName + "Max", max);
							}
						}
					}
					break;

				// re compare..
				case 8:
					// compare
					if (min != null && max == null) {
						sb.append(" and " + as + "." + name + " < :"
								+ valueName);
						paramMap.put(valueName, min);
					} else {
						if (min == null && max != null) {
							sb.append(" and " + as + "." + name + " > :"
									+ valueName);
							paramMap.put(valueName, max);
						} else {
							if (min != null && max != null) {
								sb.append(" and (" + as + "." + name + " < :"
										+ valueName + "Min or " + as + "."
										+ name + " >:" + valueName + "Max)");
								paramMap.put(valueName + "Min", min);
								paramMap.put(valueName + "Max", max);
							}
						}
					}
					break;

				case 9:
					// compare minEq
					if (min != null && max == null) {
						sb.append(" and " + as + "." + name + " <= :"
								+ valueName);
						paramMap.put(valueName, min);
					} else {
						if (min != null && max != null) {
							sb.append(" and (" + as + "." + name + " <= :"
									+ valueName + "Min or " + as + "." + name
									+ " >:" + valueName + "Max)");
							paramMap.put(valueName + "Min", min);
							paramMap.put(valueName + "Max", max);
						}
					}
					break;
				case 10:
					// compare maxEq
					if (min == null && max != null) {
						sb.append(" and " + as + "." + name + " >=:"
								+ valueName);
						paramMap.put(valueName, max);
					} else {
						if (min != null && max != null) {
							sb.append(" and (" + as + "." + name + " < :"
									+ valueName + "Min or " + as + "." + name
									+ " >=:" + valueName + "Max)");
							paramMap.put(valueName + "Min", min);
							paramMap.put(valueName + "Max", max);
						}
					}
					break;
				case 11:
					// compare eq
					if (min != null && max == null) {
						sb.append(" and " + as + "." + name + " <= :"
								+ valueName);
						paramMap.put(valueName, min);
					} else {
						if (min == null && max != null) {
							sb.append(" and " + as + "." + name + " >=:"
									+ valueName);
							paramMap.put(valueName, max);
						} else {
							if (min != null && max != null) {
								sb.append(" and (" + as + "." + name + " >= :"
										+ valueName + "Min or " + as + "."
										+ name + " >=:" + valueName + "Max)");
								paramMap.put(valueName + "Min", min);
								paramMap.put(valueName + "Max", max);
							}
						}
					}
					break;

				default:
					break;
				}
			}
		}

		String temp = sb.toString();

		if (!StringUtils.isBlank(temp) && needEncodingHql) {// 对where条件进行编码，
															// 使用于应用环境编码与数据库不一致的情况编码
			try {
				temp = new String(temp.getBytes(limitHqlEncodeFrom),
						limitHqlEncodeTo);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		re[0] = temp;
		re[1] = paramMap;
		return re;
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取排序串</li>
	 * <li>适用的前提条件</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：</li>
	 * </ul>
	 * </p>
	 * 
	 * @param as
	 * @return
	 */
	public String getOrder(String as)
	{
		if (sortProperty != null) {
			if (sortType != null) {
				return " order by " + as + "." + sortProperty + " " + sortType;
			} else {
				return " order by " + as + "." + sortProperty;
			}
		} else {
			return "";
		}
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取ExTable拼合的查询排序串</li>
	 * <li>适用的前提条件</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：</li>
	 * </ul>
	 * </p>
	 * 
	 * @param as
	 * @return
	 */
	public Object[] getWhereHQLWithSort(String as)
	{
		Object re[] = getWhereHQL(as);
		re[0] = re[0] + " " + getOrder(as);
		return re;
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：当前页开始行</li>
	 * <li>适用的前提条件</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：</li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 */
	public int getStartLineNum()
	{
		return (pageNum - 1) * rowDisplayed;
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取过滤参数</li>
	 * <li>适用的前提条件</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：</li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @return
	 */
	public HqlProperty getFilterParam(String name)
	{
		if (this.filterMap != null) {
			return (HqlProperty) filterMap.get(name);
		} else {
			return null;
		}
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取过滤参数列表</li>
	 * <li>适用的前提条件</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：</li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 */
	public Map getFilterMap()
	{
		return filterMap;
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：查询的ACTION</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public String getAction(String name, String value)
	{
		if (StringUtils.isBlank(action)) {
			return "";
		} else {
			if (action.endsWith("?")) {
				return action + name + "=" + value;
			} else {
				return action + "&" + name + "=" + value;
			}
		}
	}

	public void setFilterMap(Map filterMap)
	{
		if (this.filterMap != null)
			this.filterMap.putAll(filterMap);
		else
			this.filterMap = filterMap;
	}

	public String getSortProperty()
	{
		return sortProperty;
	}

	public void setSortProperty(String sortProperty)
	{
		this.sortProperty = sortProperty;
	}

	public String getSortType()
	{
		return sortType;
	}

	public void setSortType(String sortType)
	{
		this.sortType = sortType;
	}

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	public int getRowDisplayed()
	{
		return rowDisplayed;
	}

	public void setRowDisplayed(int rowDisplayed)
	{
		this.rowDisplayed = rowDisplayed;
	}

	public int getTotalNum()
	{
		return totalNum;
	}

	public void setTotalNum(int totalNum)
	{
		this.totalNum = totalNum;
	}

	public int getEndLineNum()
	{
		if (this.endLineNum == 0)
			this.endLineNum = pageNum * rowDisplayed;
		return endLineNum;
	}

	public void setEndLineNum(int endLineNum)
	{
		this.endLineNum = endLineNum;
	}

	public boolean isFirstEnter()
	{
		return firstEnter;
	}

	public void setFirstEnter(boolean firstEnter)
	{
		this.firstEnter = firstEnter;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public Object getStoreEx()
	{
		return storeEx;
	}

	public void setStoreEx(Object storeEx)
	{
		this.storeEx = storeEx;
	}

}
