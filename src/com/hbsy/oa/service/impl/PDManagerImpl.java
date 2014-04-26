package com.hbsy.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;

import com.hbsy.oa.domain.Form;
import com.hbsy.oa.domain.TaskView;
import com.hbsy.oa.service.PDManager;


@Service("pdManager")
public class PDManagerImpl implements PDManager{
	//@Resource(name="processEngine")
	private ProcessEngine processEngine;

	public Collection<ProcessDefinition> getLasterVersions() {
		List<ProcessDefinition> dList = this.processEngine.getRepositoryService()
		.createProcessDefinitionQuery()
		.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)
		.list();
	    
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for(ProcessDefinition pd:dList){
			map.put(pd.getKey(), pd);
		}
		return map.values();
	}

	public void deploy(File resource) throws Exception{
		/**
		 * 鍦ㄨ繖娈典唬鐮佷腑spring鍐呴儴宸茬粡鎶婁簨鍔＄鐞嗕簡
		 */
		InputStream inputStream = new FileInputStream(resource);
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		this.processEngine.getRepositoryService()
		.createDeployment()
		.addResourcesFromZipInputStream(zipInputStream)
		.deploy();
	}

	public void deletePDByKEY(String key) {
		List<ProcessDefinition> pdList = this.processEngine.getRepositoryService()
		.createProcessDefinitionQuery()
		.processDefinitionKey(key)
		.list();
		
		for(ProcessDefinition pd:pdList){
			this.processEngine.getRepositoryService()
			.deleteDeploymentCascade(pd.getDeploymentId());
		}
	}

	public InputStream showImage(String deploymentId) {
		/**
		 * 1銆佸湪ProessDefinition涓湁deploymentId
		 * 2銆佹牴鎹甦eploymentID鑳芥煡璇㈠嚭鍞竴鐨凱D
		 * 3銆佹牴鎹甈D寰楀埌ImageResourceName
		 */
		ProcessDefinition pd = this.processEngine.getRepositoryService()
		.createProcessDefinitionQuery()
		.deploymentId(deploymentId)
		.uniqueResult();
		return this.processEngine.getRepositoryService()
		.getResourceAsStream(deploymentId, pd.getImageResourceName());
	}
	
	public String sumit(String key){
		/**
		 * 鎶奻orm琛ㄥ崟浣滀负娴佺▼鍙橀噺瀛樻斁鍦╦bpm寮曟搸涓簲璇ュ湪鍚姩娴佺▼瀹炰緥鐨勬椂鍊欐墽琛�
		 */
		Map<String, Form> map = new HashMap<String, Form>();
		
		ProcessInstance pi = this.processEngine.getExecutionService()
		.startProcessInstanceById(key,map);
		/**
		 * 鏍规嵁娴佺▼瀹炰緥鍙互寰楀埌璇ユ祦绋嬪疄渚嬩腑褰撳墠姝ｅ湪鎵ц鐨勪换鍔�
		 */
		Task task = this.processEngine.getTaskService()
		.createTaskQuery().executionId(pi.getId())
		.uniqueResult();
		
		this.processEngine.getTaskService().completeTask(task.getId());
		return key;
	}
	
	/**
	 * 瀹℃壒浠ｇ爜
	 * @return
	 */
	public String mytaskList(){
		List<Task> taskList = this.processEngine.getTaskService()
		.createTaskQuery()
		.assignee("")
		.list();
		List<TaskView> taskViewList = new ArrayList<TaskView>();
		for(Task task:taskList){
			TaskView taskView = new TaskView();
			Form form = (Form)this.processEngine.getExecutionService().getVariable(task.getExecutionId(), "form");
			taskView.setForm(form);
			taskView.setTask(task);
		}
		return null;
	}
	
	public String approve(){
		return null;
		/**
		 * 1銆佸垽鏂槸鍚﹀悓鎰�
		 *     *  濡傛灉鍚屾剰锛屽畬鎴愪换鍔★紝鍐嶆鏌ユ祦绋嬪疄渚嬫槸鍚︾粨鏉�
		 *         *  濡傛灉缁撴潫锛屽垯鏁翠釜娴佺▼瀹炰緥瀹屾垚浜嗭紝鎶奻orm琛ㄤ腑鐩稿簲琛岀殑鐘舵�鏀规垚宸插畬鎴�
		 *         *  濡傛灉娌℃湁缁撴潫
		 *     *  濡傛灉涓嶅悓鎰忥紝鍒欐祦绋嬬洿鎺ョ粨鏉燂紝鎶奻orm琛ㄤ腑鐩稿簲鐨勮鐨勭姸鎬佹敼鎴愭湭閫氳繃
		 *    taskId-->TASK-->executionId-->ProcessIntance鏄惁null鍙互鍒ゆ柇娴佺▼瀹炰緥鏄惁缁撴潫
		 */
	}
}
