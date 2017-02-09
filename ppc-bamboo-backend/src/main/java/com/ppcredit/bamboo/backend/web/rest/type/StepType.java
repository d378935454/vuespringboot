package com.ppcredit.bamboo.backend.web.rest.type;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

//@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum StepType {
    INPUT(0, "用户输入"),
    INTERFACE(1, "接口"),
    JAR(2, "本地逻辑jar"),
    EL(3, "简单逻辑（表达式）"),
    END(4, "结束");
    /**
     * 枚举值
     */
    private int value;
    /**
     * 描述
     */
    private String desc;

    private StepType(int value, String desc) {
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

    public static StepType getEnum(int value) {
        StepType resultEnum = null;
        StepType[] enumAry = StepType.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (enumAry[i].getValue() == value) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

    @JsonValue
    public Map<String, Object> returnMap() {
        Map<String, Object> map = new HashMap();
        map.put("name", this.name());
        map.put("value", this.value);
        map.put("desc", this.desc);
        return map;
    }

//    public static Map<String, Map<String, Object>> toMap() {
//        StepType[] ary = StepType.values();
//        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
//        for (int num = 0; num < ary.length; num++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            String key = String.valueOf(getEnum(ary[num].getValue()));
//            map.put("value", String.valueOf(ary[num].getValue()));
//            map.put("desc", ary[num].getDesc());
//            enumMap.put(key, map);
//        }
//        return enumMap;
//    }
//
//    /**
//     * 支付方式类型
//     *
//     * @return
//     */
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    public static List toListForPayWay() {
//        StepType[] ary = StepType.values();
//        List list = new ArrayList();
//        for (int num = 0; num < ary.length; num++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("value", StepType.getEnum(ary[num].getValue()).name());
//            map.put("desc", ary[num].getDesc());
//            list.add(map);
//        }
//        return list;
//    }
//
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    public static List toList() {
//        StepType[] ary = StepType.values();
//        List list = new ArrayList();
//        for (int i = 0; i < ary.length; i++) {
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("name", ary[i].name());
//            map.put("value", String.valueOf(ary[i].getValue()));
//            map.put("desc", ary[i].getDesc());
//            list.add(map);
//        }
//        return list;
//    }
//
//    /**
//     * 取枚举的json字符串
//     *
//     * @return
//     */
//    public static String getJsonStr() {
//        StepType[] enums = StepType.values();
//        StringBuffer jsonStr = new StringBuffer("[");
//        for (StepType senum : enums) {
//            if (!"[".equals(jsonStr.toString())) {
//                jsonStr.append(",");
//            }
//            jsonStr.append("{id:'").append(senum).append("',desc:'").append(senum.getDesc()).append("',value:'")
//                    .append(senum.getValue()).append("'}");
//        }
//        jsonStr.append("]");
//        return jsonStr.toString();
//    }
}
