package com.app.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CodeUtil {

	private String genStr(int size){
		return UUID
				 .randomUUID()
				 .toString()
				 .replaceAll("-", "")
				 .substring(0, size);
	}
	
	public String genPwd(){
		return genStr(6);
	}
	public String genToken(){
		return genStr(8);
	}
}
