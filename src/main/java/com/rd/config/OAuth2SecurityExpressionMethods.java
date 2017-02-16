package com.rd.config;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.provider.expression.OAuth2ExpressionUtils;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

import com.rd.security.ApplicationContextProvider;
import com.rd.service.UsuarioService;

public class OAuth2SecurityExpressionMethods {

	private final Authentication authentication;
	
	@Autowired
	UsuarioService usuarioService;

	private Set<String> missingScopes = new LinkedHashSet<String>();

	public OAuth2SecurityExpressionMethods(Authentication authentication) {
		this.authentication = authentication;
	}

	/**
	 * Check if any scope decisions have been denied in the current context and
	 * throw an exception if so. This method automatically wraps any expressions
	 * when using {@link OAuth2MethodSecurityExpressionHandler} or
	 * {@link OAuth2WebSecurityExpressionHandler}.
	 * 
	 * OAuth2Example usage:
	 * 
	 * <pre>
	 * access = &quot;#crud.hasOption('read') or (#crud.hasOption('other') and hasRole('ROLE_USER'))&quot;
	 * </pre>
	 * 
	 * Will automatically be wrapped to ensure that explicit errors are
	 * propagated rather than a generic error when returning false:
	 * 
	 * <pre>
	 * access = &quot;#crud.throwOnError(#crud.hasOption('read') or (#crud.hasOption('other') and hasRole('ROLE_USER'))&quot;
	 * </pre>
	 * 
	 * N.B. normally this method will be automatically wrapped around all your
	 * access expressions. You could use it explicitly to get more control, or
	 * if you have registered your own <code>ExpressionParser</code> you might
	 * need it.
	 * 
	 * @param decision
	 *            the existing access decision
	 * @return true if the OAuth2 token has one of these scopes
	 * @throws InsufficientScopeException
	 *             if the scope is invalid and we the flag is set to throw the
	 *             exception
	 */
	public boolean throwOnError(boolean decision) {
		if (!decision && !missingScopes.isEmpty()) {
			Throwable failure = new InsufficientScopeException("Insufficient scope for this resource", missingScopes);
			throw new AccessDeniedException(failure.getMessage(), failure);
		}
		return decision;
	}

	/**
	 * Check if the current OAuth2 authentication has one of the scopes
	 * specified.
	 * 
	 * @param scope
	 *            the scope to check
	 * @return true if the OAuth2 authentication has the required scope
	 */
	public boolean hasOption(String scope) {
		UsuarioService bean = new UsuarioService();
		AutowireCapableBeanFactory factory = ApplicationContextProvider.getApplicationContext().getAutowireCapableBeanFactory();
		factory.autowireBean( bean );
		factory.initializeBean(bean,"usuarioService");
		User user = (User) authentication.getPrincipal();
		return bean.getCrudByUsuario(user.getUsername(), scope);
	}

	/**
	 * Deny access to oauth requests, so used for example to only allow web UI
	 * users to access a resource.
	 * 
	 * @return true if the current authentication is not an OAuth2 type
	 */
	public boolean denyOAuthClient() {
		return !OAuth2ExpressionUtils.isOAuth(authentication);
	}

	/**
	 * Permit access to oauth requests, so used for example to only allow
	 * machine clients to access a resource.
	 * 
	 * @return true if the current authentication is not an OAuth2 type
	 */
	public boolean isOAuth() {
		return OAuth2ExpressionUtils.isOAuth(authentication);
	}

	/**
	 * Check if the current authentication is acting on behalf of an
	 * authenticated user.
	 * 
	 * @return true if the current authentication represents a user
	 */
	public boolean isUser() {
		return OAuth2ExpressionUtils.isOAuthUserAuth(authentication);
	}

	/**
	 * Check if the current authentication is acting as an authenticated client
	 * application not on behalf of a user.
	 * 
	 * @return true if the current authentication represents a client
	 *         application
	 */
	public boolean isClient() {
		return OAuth2ExpressionUtils.isOAuthClientAuth(authentication);
	}
}
