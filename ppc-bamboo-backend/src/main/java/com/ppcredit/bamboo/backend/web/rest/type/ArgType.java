package com.ppcredit.bamboo.backend.web.rest.type;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ArgType {
    INPUT(0,"入参"),
    OUT(1,"出参");
    /**
     * 枚举值
     */
    private int value;
    /**
     * 描述
     */
    private String desc;

    private ArgType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonValue
    public Map<String, Object> returnMap() {
        Map<String, Object> map = new HashMap();
        map.put("name", this.name());
        map.put("value", this.value);
        map.put("desc", this.desc);
        return map;
    }
    public static ArgType getEnum(int value) {
        ArgType resultEnum = null;
        ArgType[] enumAry = ArgType.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (enumAry[i].getValue() == value) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }
//    @JsonValue
    public static Map<String, Map<String, Object>> toMap() {
        ArgType[] ary = ArgType.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(ary[num].getValue()));
            map.put("value", String.valueOf(ary[num].getValue()));
            map.put("desc", ary[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    /**
     * 支付方式类型
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List toListForPayWay() {
        ArgType[] ary = ArgType.values();
        List list = new ArrayList();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("value", ArgType.getEnum(ary[num].getValue()).name());
            map.put("desc", ary[num].getDesc());
            list.add(map);
        }
        return list;
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List toList() {
        ArgType[] ary = ArgType.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("value", String.valueOf(ary[i].getValue()));
            map.put("desc", ary[i].getDesc());
            list.add(map);
        }
        return list;
    }

    /**
     * 取枚举的json字符串
     *
     * @return
     */
    public static String getJsonStr() {
        ArgType[] enums = ArgType.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (ArgType senum : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(senum).append("',desc:'").append(senum.getDesc()).append("',value:'")
                    .append(senum.getValue()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
