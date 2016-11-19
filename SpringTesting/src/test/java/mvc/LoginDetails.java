package mvc;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by Vitaliy Sereda on 19.11.16.
 */
@Component
@Scope (scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginDetails {
	@Value ("#{session.getAttribute('userId')}")
	private String user;

	@Value("#{session.getAttribute('loggedInTime')}")
	private LocalDateTime loginTime;

	public String getUser() {
		return user;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}
}
