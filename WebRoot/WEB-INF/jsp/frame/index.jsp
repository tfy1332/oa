<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>HBSy OA</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="100,*,25" framespacing="0" border="0" frameborder="0">
   <frame src="forwardAction_forward.action?forward=top" name="TopMenu"  scrolling="no" noresize />
    <frameset cols="180,*" id="resize">
        <frame noresize name="menu" src="forwardAction_forward.action?forward=left" scrolling="yes" />
        <frame noresize name="right" src="forwardAction_forward.action?forward=right" scrolling="yes" />
    </frameset>
    <frame noresize name="status_bar" scrolling="no" src="forwardAction_forward.action?forward=bottom" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
