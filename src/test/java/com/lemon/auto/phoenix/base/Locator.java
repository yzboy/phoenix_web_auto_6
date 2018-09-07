package com.lemon.auto.phoenix.base;
/**
 * 原始定位器
 * @author zhangying
 *
 */
public class Locator {

	private String by;
	private String value;
	private String desc;
	public Locator() {
		// TODO Auto-generated constructor stub
		
	}
	public Locator(String by,String value,String desc ) {
		// TODO Auto-generated constructor stub
		this.by = by;
		this.value = value;
		this.desc = desc;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String id) {
		this.by = by;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Locator [by=" + by + ", value=" + value + ", desc=" + desc + "]";
	}
	
}
