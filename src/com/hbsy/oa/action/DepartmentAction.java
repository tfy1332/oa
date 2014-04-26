package com.hbsy.oa.action;

import java.util.Collection;

import org.springframework.beans.BeanUtils;

import com.hbsy.oa.action.base.BaseAction;
import com.hbsy.oa.domain.Department;
import com.hbsy.oa.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;

public class DepartmentAction extends BaseAction<Department>{

	private DepartmentService departmentService;
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public String init(){
		return "query";
	}
	public String getAllDepartment(){
		Collection<Department> dList = this.departmentService.getAllDepartment();
		ActionContext.getContext().put("dList", dList);
		ActionContext.getContext().getValueStack().push(dList);//鏀惧叆鍒板璞℃爤鐨勬爤椤�
		
		/**
		 * valueStack
		 *    *  struts2涓暟鎹紶杈撶殑閫氶亾鏄痸alueStack
		 *    *  瀵硅薄鏍�
		 *          濡傛灉涓�釜鏁版嵁鍦ㄥ璞℃爤锛岄偅涔堝鏋滄妸杩欎釜鏁版嵁杩涜鍥炴樉鐨勬椂鍊欙紝椤甸潰涓婂彧闇�name灞炴�灏卞彲浠ヤ簡
		 *          鏁版嵁鏀惧叆鍒板璞℃爤锛岃闂晥鐜囨瘮杈冮珮锛岀壒鍒槸鏍堥《
		 *          瀛樺彇鏂瑰紡
		 *             *  鍒╃敤push鏂规硶鎶婁竴涓璞℃斁鍏ュ埌鏍堥《
		 *             *  鍒╃敤valueStack.getRoot().add(0,鏁版嵁)
		 *             *  鍒╃敤valueStack.peek鏂规硶鍙互鎶婃爤椤朵腑鐨勫璞″彇鍑�
		 *             *  鍒╃敤valueStack.getRoot().add()鏂规硶鎶婁竴涓璞℃斁鍏ュ埌瀵硅薄鏍堜腑
		 *             *  valueStack.set(key,obj) 鎶妅ey浣滀负map鐨刱ey,obj浣滀负map鐨剉alue锛屽厛缁勬垚涓�釜map瀵硅薄锛屾妸map瀵硅薄鏀惧叆鍒板璞℃爤鐨勬爤椤�
		 *             //valueStack.set(key, o)
		 *    *  map鏍�
		 *          put鍙互鎶婁竴涓暟鎹斁鍏ュ埌map鏍堜腑
		 *          
		 */
		
//		ValueStack valueStack = ActionContext.getContext().getValueStack();
//		valueStack.getRoot().add(0,"aa");
//		List<List<Department>> lists = new ArrayList<List<Department>>();
//		List<Department> list = new ArrayList<Department>();
//		Department department = new Department();
//		department.setDname("aaa");
//		list.add(department);
//		lists.add(list);
//		ActionContext.getContext().put("lists", lists);
		
		
//		List<Map<String, Department>> listMap = new ArrayList<Map<String,Department>>();
//		Map<String, Department> map = new HashMap<String, Department>();
//		Department department = new Department();
//		department.setDname("aaa");
//		map.put("d1", department);
//		listMap.add(map);
//		ActionContext.getContext().put("listMap", listMap);
		
//		Map<String, List<Department>> map = new HashMap<String, List<Department>>();
//		List<Department> list = new ArrayList<Department>();
//		Department department = new  Department();
//		department.setDname("aaa");
//		list.add(department);
//		map.put("list", list);
//		ActionContext.getContext().put("map",map);
//		List<Map<String, List<Department>>> list = new ArrayList<Map<String,List<Department>>>();
//		Department department = new Department();
//		department.setDname("aaa");
//		List<Department> list1 = new ArrayList<Department>();
//		list1.add(department);
//		Map<String, List<Department>> map = new HashMap<String, List<Department>>();
//		map.put("map", list1);
//		list.add(map);
//		ActionContext.getContext().put("list", list);
//		ActionContext.getContext().getValueStack().push("aaa");
		//List<List<Map<String,List<Map<String,Department>>>>>
		return listAction;
	}
	
	public String delete(){
		this.departmentService.deleteDepartment(this.getModel().getDid());
		return action2action;
	}
	
	public String addUI(){
		return ADD_UI;
	}
	
	public String add(){
		Department department = new Department();
		BeanUtils.copyProperties(this.getModel(), department);
		this.departmentService.saveDepartment(department);
		return action2action;
	}
	
	public String updateUI(){
		/**
		 * 椤甸潰涓婄殑鏁版嵁瑕佸洖鏄�
		 *    涓�埇鎯呭喌涓嬪洖鏄剧殑鏁版嵁閮芥斁鍦ㄥ璞℃爤涓�
		 */
		Department department = this.departmentService.getDepartmentById(this.getModel().getDid());
		ActionContext.getContext().getValueStack().push(department);
		return UPDATE_UI;
	}
	
	public String update(){
		Department department = this.departmentService.getDepartmentById(this.getModel().getDid());
		BeanUtils.copyProperties(this.getModel(), department);
		this.departmentService.updateDepartment(department);
		return action2action;
	}
}
