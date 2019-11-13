package com.spring.handler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.pojo.User;
import com.spring.service.UserService;
import com.spring.service.UserServiceInterface;

@Controller
@RequestMapping("/")
public class UserHandler {

	@Autowired
	private UserServiceInterface userServiceInterface;
	
	@RequestMapping("/index")
	public Object index() {
		return "regist";
	}
	@RequestMapping("/regist")
	public String regist(@Valid @ModelAttribute("user")User user,BindingResult br){
//		System.out.println("name="+user.getName());
//		System.out.println("password="+user.getPassword());
		if(br.hasErrors()){
			return "regist";
		}else{
			userServiceInterface.addUser(user);
			return "welcome";
		}
	}
		//小程序数据测试
//		@GetMapping(value = "/bindtext",produces = "application/json;charset=UTF-8")
		@RequestMapping(value = "/bindtext",method = RequestMethod.GET)
		@ResponseBody
		public Object bindtext(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        response.setContentType("text/html;charset=utf-8");
	        /*设置响应头允许ajax跨域访问*/
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        
	        /* 星号表示所有的异域请求都可以接受， */
	        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
	        //获取微信小程序get的参数值并打印
	        String userName = request.getParameter("username");
	        String passsword = request.getParameter("password");
	        System.out.println("username:" + userName + "passsword:" + passsword);
	 
	        //返回值给微信小程序
//	        Writer out = response.getWriter();
//	        out.write("进入后台了go gogo");
//	        out.flush();

	        Map<String, Object> map=new HashMap<String, Object>();
	        map.put("fd", "郝鹏");
	        map.put("dd", "郝鹏");
	        return map;
	}
}
