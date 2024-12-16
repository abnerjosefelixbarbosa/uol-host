package com.org.oul_host_back_end_java.enums;

public enum PlayerType {
	Avengers("Avengers",
			"https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json"),
	JusticeLeague("Avengers",
			"https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml");
	
	private String type;
	private String url;
	
	private PlayerType(String type,
			String url) {
		this.type = type;
		this.url = url;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
}