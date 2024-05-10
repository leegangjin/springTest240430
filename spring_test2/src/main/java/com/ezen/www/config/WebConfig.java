package com.ezen.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfiguation.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		// filter 설정
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("UTF-8");
		encoding.setForceEncoding(true);//외부로 나가는 데이토도 인코딩 설정
		
		return new Filter[] {encoding}; 
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 그외 기타 사용자 설정
		// 파일 업로드 설정
		// 사용자 지정 익셉션 처리 설정
		
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		String uploadLocation ="C:\\myProject\\java\\fileUpload";
		int maxFileSize = 1024*1024*20; //20M
		int maxReqSize = maxFileSize*2;
		int fileSizeThreshold =maxFileSize;
		
		MultipartConfigElement multipartConfig =
				new MultipartConfigElement(uploadLocation,maxFileSize,maxReqSize,fileSizeThreshold);
		
		registration.setMultipartConfig(multipartConfig);
		
		
		
	}
   
}
