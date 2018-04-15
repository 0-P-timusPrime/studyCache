package cn.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;

/**
 * ��֤��¼����
 * @author lee
 *
 */
@Controller
public class LoginController {
	Logger logger = Logger.getLogger(LoginController.class);
	@Resource
	private UserService userService;
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 验证登录的方法
	 * @param userCode
	 * @param userPassword
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/dologin.html", method=RequestMethod.POST)
	public String doLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpServletRequest request,HttpSession session){
		User user = null;
		try {
			user = userService.login(userCode, userPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user != null){
			session.setAttribute(Constants.USER_SESSION, user);
			return "redirect:/sys/main.html";
		}else{
			request.setAttribute("error", "用户名或密码错误");
			return "login";
		}
		
		
	}
	/**
	 * 登录成功
	 * @return
	 */
	@RequestMapping("/sys/main.html")
	public String main(){
		return "frame";
	}
	/**
	 * 登录失败的方法
	 * @return
	 */
	@RequestMapping("login.html")
	public String login(){
		logger.debug("LoginController welcome SMBMS==================");
		return "login";
	}
	/**
	 * 异常的方法
	 * @return
	 */
	@RequestMapping(value="/syserror.html")
	public String sysError(){
		return "syserror";
	}
	
}
