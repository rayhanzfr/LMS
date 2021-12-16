package com.lawencon.lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseDaoImpl;

public class BaseServiceLmsImpl extends BaseDaoImpl<BaseEntity>{
	protected AuthPrincipal authPrincipal;
	
	@Autowired
	public void setAuthPrincipal(AuthPrincipal authPrincipal) {
		this.authPrincipal = authPrincipal;
	}
	protected Long getIdAuth() throws Exception {
		if(authPrincipal.getAuth()==null || authPrincipal.getAuth().getPrincipal()==null) {
			throw new Exception("Invalid user");
		}
		return (Long)authPrincipal.getAuth().getPrincipal();
	}
}
