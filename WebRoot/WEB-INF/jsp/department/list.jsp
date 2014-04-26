<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<script language="javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<html>
<head>
    <title>部门列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/blue/pageCommon.css" />
    <script type="text/javascript">
    </script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/css/images/title_arrow.gif"/> 部门管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">部门名称</td>
				<td width="200px">职能说明</td>
				<td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="departmentList">
        	<!-- 
        		value
        		   *  如果value属性不写，则默认迭代对象栈的栈顶的元素
        		   *  如果把一个数据放入到对象栈，不需要加#号访问
        		   *  如果把一个数据放入到map栈，需要加#号访问
        		说明：
        		   *  当前正在迭代的元素在栈顶
        		   *  <s:property/>默认输出的栈顶的元素
        	 -->
      
        	<s:iterator value="#dList">
				<tr class="TableDetail1 template">
					<td><s:property value="dname"/></td>
					<td><s:property value="description"/></td>
					<td>
						<!-- 
							struts2标签中不能用el表达式
							html标签中不能用ognl表达式
							<s:textfield name="username" value="%{ognl表达式}"/>
						 -->
						<s:a action="departmentAction_delete?did=%{did}">删除</s:a>
						<s:a action="departmentAction_updateUI?did=%{did}">修改</s:a>
					</td>
				</tr>
			</s:iterator>
		
			<!-- 
				List<List<Department>>
			 -->
			 <!-- 
			<s:iterator value="#lists">
				<s:iterator>
					<s:property value="dname"/>
				</s:iterator>
			</s:iterator>
			 -->
			 <!-- 
			 	List<Map<String,Department>>
			  -->
			  <!-- 
			  <s:iterator value="#listMap">
			  	<s:iterator>
			  		<s:property value="key"/>
			  		<s:property value="value.dname"/>
			  	</s:iterator>
			  </s:iterator>
			   -->
			   <!-- Map<String,List<Department>> -->
			  <!-- 
			  <s:iterator value="#map">
			  	 <s:property value="key"/>
			  	 <s:iterator value="value">
			  	 	<s:property value="dname"/>
			  	 </s:iterator>
			  </s:iterator>
			  -->
			  <!-- 
			  		List<Map<String, List<Department>>>
			   -->
			   <!-- 
			  <s:iterator value="#list">
			  	<s:iterator>
			  		<s:property value="key"/>
			  		<s:iterator value="value">
			  			<s:property value="dname"/>
			  		</s:iterator>
			  	</s:iterator>
			  </s:iterator>
			   --> 
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="departmentAction_addUI.action"><img src="${pageContext.request.contextPath}/css/images/createNew.png" /></a>
        </div>
    </div>
</div>

<!--说明-->	
<div id="Description"> 
	说明：<br />
	1，列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表。<br />
	2，点击部门名称，可以查看此部门相应的下级部门列表。<br />
	3，删除部门时，同时删除此部门的所有下级部门。
</div>

</body>
</html>
