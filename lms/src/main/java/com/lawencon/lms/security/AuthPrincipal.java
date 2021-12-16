package com.lawencon.lms.security;

import org.springframework.security.core.Authentication;

public interface AuthPrincipal {
	abstract Authentication getAuth();
}
