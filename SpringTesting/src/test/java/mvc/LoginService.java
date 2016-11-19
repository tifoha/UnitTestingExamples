package mvc;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 * Created by Vitaliy Sereda on 19.11.16.
 */
@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginService {
	private String username;

	private String password;

	public LoginService(@Value("#{request.getParameter('userId')}") String username,
						@Value("#{request.getParameter('password')}") String password) {
		this.username = username;
		this.password = password;
	}

	public boolean isValid() {
		return Objects.equals(username, password);
	}
}
