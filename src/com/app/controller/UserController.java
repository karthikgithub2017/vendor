package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.User;
import com.app.service.IUserService;
import com.app.util.CodeUtil;
import com.app.util.CommonUtil;

@Controller
public class UserController {
	
	@Autowired
	private IUserService service;
	@Autowired
	private CodeUtil codeUtil;
	@Autowired
	private CommonUtil commonUtil;

	//1.show Register page for USER
	@RequestMapping("/userReg")
	public String showPage(){
		return "UserReg";
	}
	
	//2. Save Data of User to DB
	@RequestMapping(value="/insertUser",method=RequestMethod.POST)
	public String saveUser(@ModelAttribute("user")User user,ModelMap map){
		//generate PWD
		String pwd=codeUtil.genPwd();
		//set to Model Attribute
		user.setUserPwd(pwd);
		
		//save data to DB
		int userId=service.saveUser(user);
		//send email
		String text="welcome to user : user Id is "+user.getUserEmail()+" (or) "+user.getUserContact()
				+" and password is : "+user.getUserPwd();
		
		commonUtil.sendEmail(user.getUserEmail(), "Welcome to User", text);
		
		map.addAttribute("msg", "User created with id:"+userId);
		
		return "UserReg";
	}
	
	/**
	 * 3. To show User Login Page
	 */
	@RequestMapping("/showLogin")
	public String showLoginPage(){
		return "UserLogin";
	}

	/**
	 * 4.User Login Check
	 * i.read un,pwd as Req Params
	 * ii.check using service layer
	 * iii.if valid create session goto Location Page
	 * iv.else return back to login page with error msg
	 */
	@RequestMapping(value="/loginUser",method=RequestMethod.POST)
	public String checkUserLogin(
			@RequestParam("uid")String un,
			@RequestParam("pwd")String pwd,
			ModelMap map,
			HttpServletRequest req){
		String pageName=null;
		User user=service.getUserByNameAndPwd(un, pwd);
		if(user==null){
			map.addAttribute("msg", "UserName/password is invalid");
			pageName="UserLogin";
		}else{
			HttpSession ses=req.getSession();
			ses.setAttribute("userName", user.getUserName());
			pageName="LocationReg";
		}
		return pageName;
	}
	
	/**
	 * 5.Logout User
	 * Read Current session and
	 * destroy that.. goto login page
	 */
	@RequestMapping("/logoutUser")
	public String userLogout(HttpServletRequest req,ModelMap map){
		HttpSession ses=req.getSession(false);
		ses.setAttribute("userName", null);
		ses.invalidate();
		map.addAttribute("msg", "Logout successful");
		return "UserLogin";
	}
}