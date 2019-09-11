package com.umpay.consts;

import com.umpay.util.StringUtil;

public enum MerChantTypeEnum {

	Individual("1","个体工商户",Short.valueOf("1")),
	Enterprise("2","企业",Short.valueOf("2")),
	NaturalPerson("3","自然人",Short.valueOf("3")),
	Other("99","其他",Short.valueOf("99"));
	
	private String merChantType;
    private String typeDesc;
    private Short value;
    
    private MerChantTypeEnum(String merChantType, String typeDesc,Short value) {
    	this.merChantType = merChantType;
    	this.typeDesc = typeDesc;
    	this.value =  value;
    }
    
    public static boolean checkMerChantType(String merChantType){
    	if(StringUtil.isEmpty(merChantType))return false;
    	for (MerChantTypeEnum ose : MerChantTypeEnum.values()) {
			if(ose.getMerChantType().equals(merChantType)&&!ose.equals(Other)){
				return true;
			}
		}
    	return false;
    }
    public static MerChantTypeEnum getMerChantTypeEnumForMerChantType(String merChantType){
		if(StringUtil.isEmpty(merChantType))return Other;
		for (MerChantTypeEnum ose : MerChantTypeEnum.values()) {
			if(ose.getMerChantType().equals(merChantType)){
				return ose;
			}
		}
		return Other;
	}
	
    
	
	public String getMerChantType() {
		return merChantType;
	}
	public void setMerChantType(String merChantType) {
		this.merChantType = merChantType;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}

}
