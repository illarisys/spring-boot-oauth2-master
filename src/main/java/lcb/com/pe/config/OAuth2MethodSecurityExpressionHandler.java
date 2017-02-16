package lcb.com.pe.config;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2ExpressionParser;

public class OAuth2MethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

	public OAuth2MethodSecurityExpressionHandler() {
		setExpressionParser(new OAuth2ExpressionParser(getExpressionParser()));
	}

	@Override
	public StandardEvaluationContext createEvaluationContextInternal(Authentication authentication, MethodInvocation mi) {
		StandardEvaluationContext ec = super.createEvaluationContextInternal(authentication, mi);
		ec.setVariable("oauth2", new OAuth2SecurityExpressionMethods(authentication));
		return ec;
	}
}