package com.shixzh.bcms.framework.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

	public static String toJSONString(Map<String, Object> result) {
		ObjectMapper mapper = new ObjectMapper();
        String mapJakcson = null;
		try {
			mapJakcson = mapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mapJakcson;
	}

}
