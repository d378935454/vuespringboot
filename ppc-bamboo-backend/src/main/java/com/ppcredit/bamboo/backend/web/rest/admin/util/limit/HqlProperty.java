/**
 * 
 */
package com.ppcredit.bamboo.backend.web.rest.admin.util.limit;
import java.util.Collection;

/**
 * <ul>
 * <li> <b>目的:</b> <br />
 * <p>
 * 分页查询时构建SQL语句的属性条件类
 * </p>
 * </li>
 * <li><b>采用的不变量：</b></li>
 * <li><b>并行策略：</b></li>
 * <li> <b>修改历史：</b><br />
 * <p>
 * 创建:Nov 9, 2007 2:45:46 PM<br />
 * 
 * </p>
 * </li>
 * <li><b>已知问题：</b></li>
 * </ul>
 */

public class HqlProperty
{
	String ASC = "asc";
	String DESC = "desc";
	// 属性名称
	private String propertyName;

	// 属性最大值
	private Object max;

	// 最小值:如非比较查询时，查询值存储在min中
	private Object min;

	// 查询类型:like, in, compare,eq,
	private int chkType;

	// 是否使用引用查询
	private boolean useRefQuery;

	private boolean reverseCompare;
	// =
	public static int TYPE_EQ = 1;

	// like
	public static int TYPE_LIKE = 2;

	// min<name<max...
	public static int TYPE_COMPARE = 3;

	// min<=name<max
	public static int TYPE_COMPARE_EQ_MIN = 5;

	// min<name<=max
	public static int TYPE_COMPARE_EQ_MAX = 6;

	// min<=name<=max
	public static int TYPE_COMPARE_EQ = 7;

	// name > max or name<min
	public static int TYPE_COMPARE_RE = 8;

	// name >max or name <= min
	public static int TYPE_COMPARE_EQ_MIN_RE = 9;

	// name >= max or name <min
	public static int TYPE_COMPARE_EQ_MAX_RE = 10;

	// name >=max or name <=min
	public static int TYPE_COMPARE_EQ_RE = 11;

	// in
	public static int TYPE_IN = 4;

	/**
	 * 构造函数，可通过不同字段的组合构造属性
	 * 
	 * @param propertyName
	 * @param max
	 * @param min
	 * @param chkType
	 * @param useRefQuery
	 *            是否使用引用查询构建, 如本值为false将，直接构造成SQL语句
	 * @param reverseCompare
	 *            反转比对查询
	 */
	public HqlProperty(String propertyName, Object max, Object min,
			int chkType, boolean useRefQuery, boolean reverseCompare)
	{
		this.propertyName = propertyName;
		this.max = max;
		this.min = min;
		this.chkType = chkType;
		this.useRefQuery = useRefQuery;
		this.reverseCompare = reverseCompare;
	}

	public HqlProperty(String propertyName, Object max, Object min,
			int chkType, boolean useRefQuery)
	{
		this.propertyName = propertyName;
		this.max = max;
		this.min = min;
		this.chkType = chkType;
		this.useRefQuery = useRefQuery;
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个等于比较的属性</li>
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
	public static HqlProperty getEq(String name, Object value)
	{
		return new HqlProperty(name, null, value, HqlProperty.TYPE_EQ, true);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个使用like查询的属性</li>
	 * <li>适用的前提条件:value字段已经使用匹配符构建</li>
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
	public static HqlProperty getLike(String name, String value)
	{
		return new HqlProperty(name, null, value, HqlProperty.TYPE_LIKE, true);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个min<X<max的比对属性</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：同getEqCompare方法 </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param max
	 * @param min
	 * 
	 * @return
	 */
	public static HqlProperty getCompare(String name, Object max, Object min)
	{
		return new HqlProperty(name, max, min, HqlProperty.TYPE_COMPARE, true);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个min<=X<=max的比对属性</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：
	 * <p>
	 * name<=max: HqlProperty.getEqCompare(name, max, null); <br />
	 * name>=min: HqlProperty.getEqCompare(name, null, min);<br />
	 * min<=name<=max: HqlProperty.getEqCompare(name, max, min);<br />
	 * </p>
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param max
	 * @param min
	 * @return
	 */
	public static HqlProperty getEqCompare(String name, Object max, Object min)
	{
		return new HqlProperty(name, max, min, HqlProperty.TYPE_COMPARE_EQ,
				true);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个min<=X<max的比对属性</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子:同getEqCompare方法 </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param max
	 * @param min
	 * @return
	 */
	public static HqlProperty getEqMinCompare(String name, Object max,
			Object min)
	{
		return new HqlProperty(name, max, min, HqlProperty.TYPE_COMPARE_EQ_MIN,
				true);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个min<X<=max的比对属性</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：同getEqCompare方法 </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param max
	 * @param min
	 * @return
	 */
	public static HqlProperty getEqMaxCompare(String name, Object max,
			Object min)
	{
		return new HqlProperty(name, max, min, HqlProperty.TYPE_COMPARE_EQ_MAX,
				true);
	}

	// =-====================

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个x<min or x>max的比对属性</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：同getEqCompare方法 </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param max
	 * @param min
	 * 
	 * @return
	 */
	public static HqlProperty getReCompare(String name, Object max, Object min)
	{
		return new HqlProperty(name, max, min, HqlProperty.TYPE_COMPARE_RE,
				true);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个x<=min or x>=max的比对属性</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：
	 * <p>
	 * name<=max: HqlProperty.getEqCompare(name, max, null); <br />
	 * name>=min: HqlProperty.getEqCompare(name, null, min);<br />
	 * min<=name<=max: HqlProperty.getEqCompare(name, max, min);<br />
	 * </p>
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param max
	 * @param min
	 * @return
	 */
	public static HqlProperty getReEqCompare(String name, Object max, Object min)
	{
		return new HqlProperty(name, max, min, HqlProperty.TYPE_COMPARE_EQ_RE,
				true);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个x<=min or x>max的比对属性</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子:同getEqCompare方法 </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param max
	 * @param min
	 * @return
	 */
	public static HqlProperty getReEqMinCompare(String name, Object max,
			Object min)
	{
		return new HqlProperty(name, max, min,
				HqlProperty.TYPE_COMPARE_EQ_MIN_RE, true);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个X<min or X>=max的比对属性</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：同getEqCompare方法 </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param max
	 * @param min
	 * @return
	 */
	public static HqlProperty getReEqMaxCompare(String name, Object max,
			Object min)
	{
		return new HqlProperty(name, max, min,
				HqlProperty.TYPE_COMPARE_EQ_MAX_RE, true);
	}

	// =======================

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个in[collection]查询属性</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param values
	 * @return
	 */
	public static HqlProperty getIn(String name, Collection values)
	{
		return new HqlProperty(name, null, values, HqlProperty.TYPE_IN, true);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一个in[array]查询属性</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 * @param values
	 * @return
	 */
	public static HqlProperty getIn(String name, Object[] values)
	{
		return new HqlProperty(name, null, values, HqlProperty.TYPE_IN, true);
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}

	public Object getMax()
	{
		return max;
	}

	public void setMax(Object max)
	{
		this.max = max;
	}

	public Object getMin()
	{
		return min;
	}

	public void setMin(Object min)
	{
		this.min = min;
	}

	public int getChkType()
	{
		return chkType;
	}

	public void setChkType(int chkType)
	{
		this.chkType = chkType;
	}

	public boolean isUseRefQuery()
	{
		return useRefQuery;
	}

	public void setUseRefQuery(boolean useRefQuery)
	{
		this.useRefQuery = useRefQuery;
	}
}
