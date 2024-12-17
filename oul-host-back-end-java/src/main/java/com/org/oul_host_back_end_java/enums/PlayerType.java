package com.org.oul_host_back_end_java.enums;

public enum PlayerType {
    AVENGERS("AVENGERS",
			"https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json"),
	JUSTICE_LEAGUE("JUSTICE_LEAGUE",
			"https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml");
	
	private String type;
	private String uri;
	
	private PlayerType(String type,
			String uri) {
		this.type = type;
		this.uri = uri;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getUri() {
		return uri;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}
}