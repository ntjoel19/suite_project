<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'
    session='true'%>
<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml'>
<head>
<!--<meta http-equiv='Content-Type' content='text/html charset=utf-8' />-->
	<meta charset=utf-8' />
	<link rel='stylesheet' type='text/css'  href='./design/Design_Global.css' />
	<link rel='icon' type='image/png'  href='./ressources/iconeSP.png' />
	<link rel='stylesheet' type='text/css'  href='./design/jquery-ui.css' />
	<!--  link rel='stylesheet' type='text/css'  href='./design/globaldesign.css' /-->
	<link rel='stylesheet' type='text/css'  href='./design/styles.css' />
	<link href='./design/juizDropDownMenu.css' rel='stylesheet' type='text/css' />
	<title>Admin Space</title>
	
	<style>
		#formEtape1,#formEtape2,#formEtape3,#formEtape4,#formAttribuerRole{
			border : 1px solid #999999;
			z-index : 5;
			padding : 5px;
			background: url(././ressources/footerimage.jpg) repeat;
			border-radius : 5px;
			text-align : center;
			-webkit-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-moz-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
			-ms-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-o-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			box-shadow: 0 0 12px grey;
			
			background: #dbdbdb url(../ressources/main.png) repeat-x scroll 0 0;  
			background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #d4d4d4),color-stop(1, #e6e6e6));
			background: -webkit-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			background: -moz-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			border: 1px solid #c0c0c0;
			
			position : absolute;
			display : none;
		}
		#site{
			border: 1px solid #f6f5f5;
			background: #ffff;
		}
		#formEtape1 table,#formEtape2 table,#formEtape3 table,#formEtape4 table,#formAttribuerRole table{
			width : 100%;
			text-align : center;
		}
		.btn{
			border-radius: 5px;
			border : 1px solid #dddddd;	
			height : 90%;
			background : #999999;
			font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;	
			color : #567;
			transition:background 1000ms,color 1000ms,border-color 500ms;	
			float : left;
			box-shadow: 0 0 12px grey;
			margin : 5px;
			padding : 5px;
		}
	</style>
</head>

<body>
    <% 
			String code_pers=(String)session.getAttribute("code_pers");
			String nomUser=(String)session.getAttribute("nom");
			String emailUser=(String)session.getAttribute("email");
			String contactUser=(String)session.getAttribute("contacts");
			String code_role_user=(String)session.getAttribute("code_role");
			
			String btn0=(String)request.getAttribute("btn0");
			String btn1=(String)request.getAttribute("btn1");
			String btn2=(String)request.getAttribute("btn2");
			String btn3=(String)request.getAttribute("btn3");
			String btn4=(String)request.getAttribute("btn4");
			String btn5=(String)request.getAttribute("btn5");
			String btn6=(String)request.getAttribute("btn6");
			String btn7=(String)request.getAttribute("btn7");
			String btn8=(String)request.getAttribute("btn8");
			String btn9=(String)request.getAttribute("btn9");
			String btn10=(String)request.getAttribute("btn10");
			String btn11=(String)request.getAttribute("btn11");
			String btn12=(String)request.getAttribute("btn12");
			String btn13=(String)request.getAttribute("btn13");
			String btn14=(String)request.getAttribute("btn14");
			String btn15=(String)request.getAttribute("btn15");
			//Integer nbUser=request.getAttribute('code_role');
			//String nbCmptAValider=(String)request.getAttribute("nbCmptAValider");
		%>
    <!-- On inclut l'entete (baniere + menu) -->
    <section id='entete'>
    	<jsp:include page='entete_des_pages.jsp' />	
    </section>  
    
    <section id='corps' class='corp'>
       	<table id='mainmenu'>
        	<tr>
            	<td class='tabl2' id='btn1'><%out.print(btn0); %></td>
                <td class='tabl2' id='btn2'><%out.print(btn1); %></td>
                <td class='tabl2' id='btn3'><%out.print(btn2); %></td>
                <td  id='publiCell' rowspan='3'>
                	<div id='c1'>
                    	<u><b>Interface administrateur</b></u></br>
                    </div>
                    <p id='c2' class='cc1'>
                    	<u><b>Création de programmes</b></u></br>
                    	</br>Fournie le formulaire de renseignement des informations 
                    	relatives a un programme. Une fois renseigne, le programme est enregistre
                    	et affiche dans la liste des programmes.
                    </p>
                    <p id='c3' class='cc1'>
                    	<u><b>Création d'actions</b></u></br>
                    	Une action depend d'un programme. Cette fonctionnalite vous permet 
                    	d'enregistrer les actions au travers de formulaire. Durant le renseignement,
                    	vous devrez choisir le programme auquel est lie l'action courante.
                    </p>
                    <p id='c4' class='cc1'>
                    	<u><b>Création de projets</b></u></br>
                    </p>
                    <p id='c5' class='cc1'>
                    	<u><b>Création des medias</b></u></br>
                    </p>
                    <p id='c6' class='cc1'>
                    	<u><b>Création de Publications</b></u></br>
                    	L'administrateur pourait vouloir rendre disponible une information, un document, 
                    	ou quelque autre fichier qui pourrait etre utile aux visiteurs. 
                    	Ces information sont regroupee sous le nom de publication. Ce bouton 
                    	vous donne la possibilite d'enregistrer une publication.
                    </p>
                    <p id='c7' class='cc1'>
                    	<u><b>Création de utilisateurs et des roles</b></u></br>
                    </p>
                    <p id='c8' class='cc1'>
                    	<u><b>Parametrer l'application</b></u></br>
                    </p>
                    <p id='c9' class='cc1'>
                    	<u><b>Statistiques</b></u></br>
                    </p>
                    <p id='c10' class='cc1'>
                    	<u><b>Aide</b></u></br>
                    </p>
                </td>
            </tr>
            <tr>
            	<td class='tabl2' id='btn4'><%out.print(btn3); %></td>
                <td class='tabl2' id='btn5'><%out.print(btn4); %></td>
                <td class='tabl2' id='btn6'><%out.print(btn5); %></td>
            </tr>
            <tr>
            	<% String a =(String)request.getAttribute("nbCmptAValider"); %>
            	<td class='tabl2' id='btn7'><%out.print(btn6); %></td>
                <td class='tabl2' id='btn8'><%out.print(btn7); %></td>
                <td class='tabl2' id='btn9'><%out.print(btn8); %></td>
            </tr>
        </table>
    </section>
    
	
	
	<!-- Inclusion de footer -->
	<section id='pieds'>
		<jsp:include page='pieds_des_pages.jsp'/>
	</section>
    
    <script type='text/javascript' src='./jscript/design.js'></script>
    <script>
    </script>
</body>
</html>
