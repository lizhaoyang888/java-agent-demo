package com.example.agent.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Objects;
import java.util.TimeZone;

/**
 * JSON处理工具类
 *
 * @author shichunyang
 */
@Slf4j
public class JsonUtil {
	private static final JsonMapper MAPPER = new JsonMapper();

	private JsonUtil() {
	}

	/**
	 * 将JSON格式数据转换成对象(失败返回NULL)
	 *
	 * @param json json字符串
	 */
	public static <T> T json2Object(String json, Class<T> clazz) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}

		try {
			return MAPPER.readValue(json, clazz);
		} catch (Exception e) {
			log.warn("json2Object, json==>{}, error==>{}", json, JsonUtil.object2Json(e));
			return null;
		}
	}

	public static <T> T json2Object(String json, TypeReference<T> typeReference) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}

		try {
			return MAPPER.readValue(json, typeReference);
		} catch (Exception e) {
			log.warn("json2Object, json==>{}, error==>{}", json, JsonUtil.object2Json(e));
			return null;
		}
	}

	/**
	 * 将JAVA对象转换成JSON字符串(失败返回NULL)
	 *
	 * @param object java对象
	 * @return json字符串
	 */
	public static String object2Json(Object object) {
		if (Objects.isNull(object)) {
			return null;
		}

		try {
			return MAPPER.writeValueAsString(object);
		} catch (Exception e) {
			return null;
		}
	}

	public static class JsonMapper extends ObjectMapper {
		private static final long serialVersionUID = 1L;

		static ObjectMapper mapper;

		static {
			mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			TimeZone timeZone = TimeZone.getTimeZone(ZoneId.SHORT_IDS.get("CTT"));
			mapper.setTimeZone(timeZone);
		}

		public JsonMapper() {
			super(mapper);
		}
	}
}
