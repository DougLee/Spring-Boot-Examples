package com.example.springbootshiro.config;

import com.example.springbootshiro.domain.MenuInfo;
import com.example.springbootshiro.domain.RoleInfo;
import com.example.springbootshiro.domain.UserInfo;
import com.example.springbootshiro.service.MenuService;
import com.example.springbootshiro.service.RoleService;
import com.example.springbootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		UserInfo user = (UserInfo) SecurityUtils.getSubject().getPrincipal();
		String userName = user.getUsername();

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		List<RoleInfo> roleList = this.roleService.findUserRole(userName);
		Set<String> roleSet = new HashSet<String>();
		for (RoleInfo r : roleList) {
			roleSet.add(r.getRoleName());
		}
		simpleAuthorizationInfo.setRoles(roleSet);

		List<MenuInfo> permissionList = this.menuService.findUserPermissions(userName);
		Set<String> permissionSet = new HashSet<String>();
		for (MenuInfo m : permissionList) {
			permissionSet.add(m.getPerms());
		}
		simpleAuthorizationInfo.setStringPermissions(permissionSet);
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		UserInfo user = this.userService.findByName(userName);

		if (user == null) {
			throw new UnknownAccountException("用户名或密码错误！");
		}
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("用户名或密码错误！");
		}
		if ("0".equals(user.getStatus())) {
			throw new LockedAccountException("账号已被锁定,请联系管理员！");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
