package shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

import java.util.Collection;

/**
 * @author yujiangtao
 * @date 2018/4/20 11:10
 */
public class AtLeastTwoAuthenticatorStrategy extends AbstractAuthenticationStrategy {
    public AtLeastTwoAuthenticatorStrategy() {
        super();
    }

    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
        // 返回一个权限的认证信息
        return new SimpleAuthenticationInfo();
    }

    @Override
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        // 返回之前合并的
        return aggregate;
    }

    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        AuthenticationInfo info;
        if(singleRealmInfo == null) {
            info = aggregateInfo;
        } else {
            if(aggregateInfo == null) {
                info = singleRealmInfo;
            } else {
                info = merge(singleRealmInfo, aggregateInfo);
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo merge(AuthenticationInfo info, AuthenticationInfo aggregate) {
        return super.merge(info, aggregate);
    }

    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate)
            throws AuthenticationException {
        if(aggregate == null || CollectionUtils.isEmpty(aggregate.getPrincipals())
                || aggregate.getPrincipals().getRealmNames().size() < 2) {
            throw new AuthenticationException("Authentication token of type [" + token.getClass() + "]" +
                "could not be authenticated by any configured realms. Please ensure that at least two realm can" +
                "authenticate these tokens.");
        }
        return aggregate;
    }
}
