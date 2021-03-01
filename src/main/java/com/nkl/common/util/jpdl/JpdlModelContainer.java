/**
 * CopyRight (C) 2006-2009 合肥xxxx有限公司
 * @author yy
 * @project JbpmExamples
 * @version 1.0
 * @mail yy629_86 at 163 dot com
 * @date 2009-9-6 下午06:00:14
 * @description
 */
package com.nkl.common.util.jpdl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.nkl.common.util.jpdl.model.JpdlModel;

/**
 * 用来存放jpdlModel的容器
 * 
 * @author yeyong
 * 
 */
public class JpdlModelContainer {
	private Map<String, JpdlModel> jbdlModelMap = new HashMap<String, JpdlModel>();

	/**
	 * 判断是否含有指定流程定义id的JpdlModel
	 * 
	 * @param processDefintionId
	 * @return
	 */
	public boolean hasJpdlModel(String processDefintionId) {
		return jbdlModelMap.containsKey(processDefintionId);
	}

	/**
	 * 获得指定流程定义id的JpdlModel
	 * 
	 * @param processDefintionId
	 * @return
	 */
	public JpdlModel getJpdlModel(String processDefintionId) {
		return jbdlModelMap.get(processDefintionId);
	}
	/**
	 * 放入新的JpdlModel
	 * @param processDefintionId
	 * @param inputStream
	 */
	public void putJpdlModel(String processDefintionId, InputStream inputStream) {
		try {
			JpdlModel jpdlModel = new JpdlModel(inputStream);
			jbdlModelMap.put(processDefintionId, jpdlModel);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
