package com.hbsy.oa.action;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.hbsy.oa.action.base.BaseAction;
import com.hbsy.oa.domain.Department;
import com.hbsy.oa.domain.Post;
import com.hbsy.oa.domain.User;
import com.hbsy.oa.service.DepartmentService;
import com.hbsy.oa.service.PostService;
import com.hbsy.oa.service.UserService;
import com.opensymphony.xwork2.ActionContext;


public class UserAction extends BaseAction<User>{
	private UserService userService;
	
	private DepartmentService departmentService;
	
	private PostService postService;
	
	private Long did;
	
	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Long[] getPids() {
		return pids;
	}

	public void setPids(Long[] pids) {
		this.pids = pids;
	}

	private Long[] pids;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String getAllUser(){
		Collection<User> userList = this.userService.getAllUser();
		ActionContext.getContext().put("userList", userList);
		return listAction;
	}
	
	/**
	 * 璺宠浆鍒板鍔犻〉闈�
	 * @return
	 */
	public String addUI(){
		Collection<Department> dList = this.departmentService.getAllDepartment();
		Collection<Post> pList = this.postService.getAllPost();
		ActionContext.getContext().put("dList", dList);
		ActionContext.getContext().put("pList", pList);
		return ADD_UI;
	}
	
	/**
	 * 澧炲姞鐢ㄦ埛
	 */
	public String add(){
		/**
		 * 1銆佹柊寤轰竴涓猽ser瀵硅薄锛屾妸鐢ㄦ埛鐨勬櫘閫氬睘鎬х殑鍊艰祴鍊�
		 * 2銆佹煡璇㈡煇涓�釜閮ㄩ棬
		 * 3銆佹煡璇㈡煇涓�簺宀椾綅
		 * 4銆佺畝鍘嗙敤鎴峰拰閮ㄩ棬涔嬮棿鐨勫叧绯�
		 * 5銆佸缓绔嬬敤鎴峰拰宀椾綅涔嬮棿鐨勫叧绯�
		 *  鍒嗘瀽锛�
		 *      鐢ㄦ埛鍜岄儴闂ㄤ箣闂达紝鐢ㄦ埛缁存姢鍏崇郴
		 *      鐢ㄦ埛鍜屽矖浣嶄箣闂达紝鐢ㄦ埛鍙互缁存姢鍏崇郴
		 *      鍙淇濆瓨鐢ㄦ埛锛屽氨闂存帴缁存姢鍏崇郴
		 */
		
		/**
		 * 鎺ュ彈椤甸潰鏁版嵁鐨勫垎鏋愶細
		 *    1銆佹ā鍨嬮┍鍔ㄥ彧鑳芥帴鏀朵竴鑸睘鎬х殑鏁版嵁
		 *    2銆佸睘鎬ч┍鍔ㄦ帴鏀跺紩鐢ㄧ被鍨�
		 */
		
		User user = new User();
		BeanUtils.copyProperties(this.getModel(), user);
		Department department = this.departmentService.getDepartmentById(this.did);
		//寤虹珛浜嗙敤鎴峰拰閮ㄩ棬涔嬮棿鐨勫叧绯�
		user.setDepartment(department);
		Set<Post> posts = this.postService.getPostsByIds(this.pids);
		//寤虹珛鐢ㄦ埛鍜屽矖浣嶄箣闂寸殑鍏崇郴
		user.setPosts(posts);
		this.userService.saveUser(user);
		return action2action;
	}
	
	public String delete(){
		this.userService.deleteUser(this.getModel().getUid());
		return action2action;
	}
	
	/**
	 * 璺宠浆鍒颁慨鏀归〉闈�
	 * @return
	 */
	public String updateUI(){
		User user = this.userService.getUserByID(this.getModel().getUid());
		/**
		 * 鎶婄敤鎴峰帇鍏ュ埌鏍堥《锛岀敤浜庝竴鑸睘鎬х殑鍥炴樉
		 */
		ActionContext.getContext().getValueStack().push(user);
		/**
		 * 鍥犱负褰撳墠璁块棶鐨刟ction鍦ㄥ璞℃爤涓紝鎵�互action涓殑灞炴�鍙互鍦ㄩ〉闈笂鐩存帴璁块棶
		 */
		this.did = user.getDepartment().getDid();
		Set<Post> posts = user.getPosts();
		this.pids = new Long[posts.size()];
		int index = 0;
		for(Post post:posts){
			this.pids[index] = post.getPid();
			index++;
		}
		
		Collection<Department> dList = this.departmentService.getAllDepartment();
		Collection<Post> pList = this.postService.getAllPost();
		ActionContext.getContext().put("dList", dList);
		ActionContext.getContext().put("pList", pList);
		
		return updateUI;
	}
	
	public String update(){
		User user = this.userService.getUserByID(this.getModel().getUid());
		BeanUtils.copyProperties(this.getModel(), user);
		
		Department department = this.departmentService.getDepartmentById(this.did);
		user.setDepartment(department);
		
		Set<Post> posts = this.postService.getPostsByIds(this.pids);
		user.setPosts(posts);
		
		this.userService.updateUser(user);
		return action2action;
	}
}
