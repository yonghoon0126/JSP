package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Command : 만능 리모콘의 역할(행동을 하나로 만들기)
//BoardCommand   :Command패턴==인터페이스
public interface BoardCommand {
	public void execute(HttpServletRequest request,
			HttpServletResponse response);
}
