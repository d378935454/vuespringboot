package com.ppcredit.bamboo.backend.web.rest.admin.util.text;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CityUtils {
	public  String json = "";


	public String cmbJson = "";
	
	
	
	public String pinganJson = "";

	
	public List<String> getCityListByProvname(String provname){
		List<String> list = new ArrayList<String>();
		JSONArray arr = JSONArray.parseArray(json);
		for(int i=0;i<arr.size();i++){
			JSONObject obj = arr.getJSONObject(i);
			String pro = obj.getString("Province");
			if(provname.indexOf(pro) >= 0){
				String city = obj.getString("CityName");
				list.add(city);
			}
		}
		return list;
	}
	
	public String getProvnameByCityName(String cityName){
		String provname = "";
		JSONArray arr = JSONArray.parseArray(json);
		for(int i=0;i<arr.size();i++){
			JSONObject obj = arr.getJSONObject(i);
			String city = obj.getString("CityName");
			if(city.indexOf(cityName) >= 0){
				provname = obj.getString("Province");
			}
		}
		
		return provname;
	}
	
	
	

}

