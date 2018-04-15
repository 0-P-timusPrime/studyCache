package cn.smbms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController {
	Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@RequestMapping(value="/list.html")
	public String userlist(Model model,
			@RequestParam(value="queryUserName",required=false)String queryUserName,
			@RequestParam(value="queryUserRole",required=false)String queryUserRole,
			@RequestParam(value="pageIndex",required=false)String pageIndex){
		logger.info("getUserList ---- > queryUserName: " + queryUserName);
		logger.info("getUserList ---- > queryUserRole: " + queryUserRole);
		logger.info("getUserList ---- > pageIndex: " + pageIndex);
		List<User> userList = null;
		Integer _queryUserRole = null;
		List<Role> roleList = null;
		int pageSize = Constants.pageSize;
		int currentPageNo = 1;
		
		if(queryUserName == null){
			queryUserName = "";
		}
		if (queryUserRole != null && queryUserRole != "") {
			_queryUserRole = Integer.parseInt(queryUserRole);
		}
		if(pageIndex != null){
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				
				return "redirect:/syserror.html";
			}
		}
		
		int totalCount = 0;
		try {
			totalCount = userService.getUserCount(queryUserName, _queryUserRole);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PageSupport pages = new PageSupport();
		pages.setPageSize(pageSize);
		pages.setCurrentPageNo(currentPageNo);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		
		//判断首页和尾页
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if (currentPageNo >= totalCount) {
			currentPageNo = totalCount;
		}
		
		try {
			userList = userService.getUserList(queryUserName, _queryUserRole, currentPageNo, pageSize);
			
			roleList = roleService.getRoleList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userList", userList);
		model.addAttribute("roleList",roleList);
		model.addAttribute("queryUserName", queryUserName);
		model.addAttribute("queryUserRole", queryUserRole);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "userlist";
	}
	
	
}
