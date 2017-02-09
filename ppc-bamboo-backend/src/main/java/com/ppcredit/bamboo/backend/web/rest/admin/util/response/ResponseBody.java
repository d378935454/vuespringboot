package com.ppcredit.bamboo.backend.web.rest.admin.util.response;


import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ResponseBody.java    
 * Description: 对客户端统一输出的ajaxbody
 * @author yang_hx       
 * @created 2015-9-1 下午3:59:30
 */
public class ResponseBody  implements Response{

	private String respCode;
	private String respMsg;
	private Object respBody;
	
	public ResponseBody(){}
	
	public ResponseBody(String respCode,String respMsg,Object respBody){
		this.respCode = respCode;
		this.respMsg = respMsg;
		this.respBody = respBody;
	}
	
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getRespCode() {
		return respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	
	public Object getRespBody() {
		return respBody;
	}

	public void setRespBody(Object respBody) {
		this.respBody = respBody;
	}

	public String getBody() throws Exception {
		JSONObject jmap = new JSONObject();
		
		jmap.put("resp_code", respCode);
		jmap.put("resp_msg", respMsg);
		jmap.put("resp_body", respBody);
		
		return jmap.toJSONString();
	}
	

}
