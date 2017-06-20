package com.gs.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Wang Genshen on 2017-06-16.
 */
public class PathUtil {
    public static String getSRCPath(HttpServletRequest request) {
        return request.getServletContext().getRealPath("/") + "WEB-INF/classes/";
    }
}
