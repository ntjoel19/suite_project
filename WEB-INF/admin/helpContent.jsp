<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion des utilisateurs</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	<script src="jquery/jquery.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery-validate.js"></script>
	<script src="jquery/jq-valide-unobstrusive.js"></script>
	<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
	<script src="jscript/design.js"></script>
<style type="text/css">
	:-moz-placeholder {
	    color: grey;
	}
	 
	::-webkit-input-placeholder {
	    color: grey;
	}
	*:focus {
		outline: none;
	}
	.label {
	    margin-top: 3px;
	    display:inline-block;
	    float:left;
	    padding:3px;
	    font-size: 13px;
	}
	.form_hint, .required_notification {
		font-size: 11px;
	}
	.in{
		border:1px solid #aaa;
	    box-shadow: 0px 0px 3px #ccc, 0 10px 15px #eee inset;
	    border-radius:2px;
	    -moz-transition: padding .25s;
	    -webkit-transition: padding .25s;
	    -o-transition: padding .25s;
	}
	.in:focus{
		 background: #fff;
	    border:1px solid #555;
	    box-shadow: 0 0 3px #aaa; 
	    padding-right:5px;
	}
	.in:required{
		background: #fff url(images/red_asterisk.png) no-repeat 98% center;
	}
	#suivi_projet table{
		border: 1px solid #c0c0c0;
		-webkit-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
		-moz-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
		-ms-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
		-o-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
		box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
	}
	#editRole{
		height: 92%;
	}
	h1,h2,h3,h4{
		text-align:center;
		background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #d4d4d4),color-stop(1, #e6e6e6));
		background: -webkit-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
		background: -moz-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb; 
	}
</style> 
</head>
<body>
	<% 
		String type = (String)request.getParameter("save");
		String type2 = (String)request.getParameter("saveAdd");
	%>
		<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/>  
			<table id="menu_gestion">
				<tr><form id="formSavePersonne" method="post" enctype='multipart/form-data' action="/suite_project/AjoutPersonne">
					<td id="menu_gestiontitre"><b>Aide </b
					></td>
				</tr>
			</table> 
		</section>	
			<div id="suivi_projet" align='center' style='background:#f6f6f6;overflow:auto;'>
					<h1 id='Intro'>Aide de SuiteProject</h1>
					<object  width='80%' height='100%' type="application/pdf" name="PDF" id="PDF"> 
						<param name="src" value="././ressources/helpContent/help.pdf" /> 
					</object>
			</div>
			</form>
	
	<!-- Inclusion de footer -->
	<div id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</div>
</body>
</html>

<script type="text/javascript"> 
	PDF.SetShowToolBar("true"); //--- barre d'outils true(visible) false(non visible) ---// 
	PDF.SetShowScrollbar("true"); //--- barre de scroll true(visible) false(non visible) ---// 
	PDF.SetPageMode("none"); //--- cache les signets ---// 
	PDF.setZoom('80%'); //--- Zoom le document à 80% ---// 
</script>