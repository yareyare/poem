package com.ivy.constant;

public enum PoemsDynasty {
	
	TANG("tang", "唐朝","唐"), SONG("song", "宋朝","宋"),YUAN("yuan","元朝","元"),MING("ming","明朝","明"),QING("qing","清朝","清");
	
	private String type;
	private String name;
	private String column;
	
	private PoemsDynasty(String type, String name, String column) {
		this.type = type;
		this.name = name;
		this.column = column;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	
}

