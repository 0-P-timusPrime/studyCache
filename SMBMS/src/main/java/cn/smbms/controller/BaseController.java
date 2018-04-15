package cn.smbms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 控制层的基类
 * @author Administrator
 *
 */
@Controller
public class BaseController {
	/**
	 * 使用InitBinder解决springMVC日期无法绑定的问题
	 * @param dataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		System.out.println("initBinder=======================");
		  
		dataBinder.registerCustomEditor(Date.class, 
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	 	/*dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				System.out.println("InitBinder setAsText value=======================" + value);
		        try {
		            setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
		        } catch(ParseException e) {
		        	System.out.println(e.getMessage());
		        	e.printStackTrace();
		            setValue(null);
		        }
		    }
		    public String getAsText() {
		    	System.out.println("InitBinder getAsText=======================");
		        return new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
		    }        

		});*/
	}
}
