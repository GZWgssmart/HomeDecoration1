package com.gs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.gs.util.common.Constants;
/**
 * 
 * @author 曾创
 *编码过滤器
 */
public class EncodingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		response.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		response.setContentType(Constants.DEFAULT_CONTENT_TYPE);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
