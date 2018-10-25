package com.project.supermarket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 实际地址与虚拟地址映射
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

//文件磁盘图片url 映射
//配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
		//file:F://springboot//supermarket1//supermarket//admin-dashboard//
		registry.addResourceHandler("/**").addResourceLocations("file:F://springboot//supermarket2//supermarket//admin-dashboard//");
		//file:F://springboot//supermarket1//supermarket//src//main//webapp//resources//images//
		registry.addResourceHandler("/resources/images/**").addResourceLocations("file:F://springboot//supermarket2//supermarket//src//main//webapp//resources//images//");
	}

}






