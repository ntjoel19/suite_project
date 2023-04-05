<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	<link rel="stylesheet" type="text/css"  href="design/style_formulaire.css" />
	<script src="jquery/jquery.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery-validate.js"></script>
	<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
	<script src="jscript/design.js"></script>
	<script src="jscript/fullcalendar.js"></script>
	<script src="jscript/moment.min.js"></script>
	<title>suivi des projets</title>
	<style type="text/css">
		table td{
			border-bottom:1px solid grey;
		}
		
		.titre{
			font-weight:bold;
			font-size : 1.2em;
			width : 25%;
			border-right:1px solid grey;
			text-transform: uppercase;
		}
		
		table td[colspan='2'],th{
			background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #eae8e8),color-stop(1, #ffffff));
			background: -webkit-linear-gradient(center bottom , #eae8e8 0%, #ffffff 100%) repeat scroll 0 0 #dbdbdb;  
			background: -moz-linear-gradient(center bottom , #eae8e8 0%, #ffffff 100%) repeat scroll 0 0 #dbdbdb; 
			font-weight:bold;
			font-size : 1.5em;
			text-transform: uppercase;
		}
		
		embed{
			background : url(././ressources/loading.gif) no-repeat;
			visibility : hidden	
		}
	</style>
</head>
<body>
		<% String colspan=(String)request.getAttribute("colspan"); %>
	
	<% String intitule=(String)request.getAttribute("intitule");%>
	<% String contexte=(String)request.getAttribute("contexte");%>
	<% String description=(String)request.getAttribute("description");%>
	<% String objectif1=(String)request.getAttribute("objectif1");%>
	<% String objectif2=(String)request.getAttribute("objectif2");%>
	<% String promot1=(String)request.getAttribute("promot1");%>
	<% String promot2=(String)request.getAttribute("promot2");%>
	<% String promot3=(String)request.getAttribute("promot3");%>
	<% String dataList1=(String)request.getAttribute("dataList1");
		String appartenance=(String)request.getAttribute("appartenance");
	   String dataListAction=(String)request.getAttribute("dataListAction");
	   if(dataListAction==null) dataListAction="";
	%>
	<% String ancrage1=(String)request.getAttribute("ancrage1");%>
	<% String ancrage2=(String)request.getAttribute("ancrage2");%>
	<% String effet=(String)request.getAttribute("effet");%>
	<% String impact=(String)request.getAttribute("impact");%>
	<% String justification1=(String)request.getAttribute("justification1");%>
	<% String justification2=(String)request.getAttribute("justification2");%>
	<% String justification3=(String)request.getAttribute("justification3");%>
	<% String cout=(String)request.getAttribute("cout");%>
	<% String population=(String)request.getAttribute("population");%>
	<% String service=(String)request.getAttribute("service");%>
	<% String typologie=(String)request.getAttribute("typologie");%>
	<% String extrants=(String)request.getAttribute("extrants");%>
	<% String nature_finance=(String)request.getAttribute("nature_finance");%>
	<% String maitre_Ovr=(String)request.getAttribute("maitre_Ovr");%>
	<% String maitre_Ovg=(String)request.getAttribute("maitre_Ovg");%>
	<% String partenaire=(String)request.getAttribute("partenaire");%>
	<% String designation=(String)request.getAttribute("designation");%>
	<% String path=(String)request.getAttribute("path");%>
	<% String name=(String)request.getAttribute("name");%>
	<% String porteur=(String)request.getAttribute("porteur");%>
	<% String action=(String)request.getAttribute("action");%>
	<% String programme=(String)request.getAttribute("programme");%>
	
	<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/> 
	</section> 
	
	<div id="suivi_projet">
		<table align='center' style='border:1px solid grey;' width='60%'>
			<thead>
				<tr>
					<th align='center' colspan='2'><u><% out.print(intitule); %></u></br>
						<u><% out.print(action); %></u></br><u><% out.print(programme); %></u><br>
						<a id='someLink' title='retourner ' style='float:left;border-radius:100px;' href='/suite_project/projet?btn=suiviprojet'><-</a><a href='/suite_project/fichiers/projet/<% out.print(designation); %>/<% out.print(path); %>' class='someLink'>TDR</a>&nbsp;<input type='submit' value='Fiche'><br>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class='titre'>Description: </td><td><% out.print(description); %></td>
				</tr>
				<tr>
					<td class='titre'>Contexte: </td><td><% out.print(contexte); %></td>
				</tr>
				<tr>
					<td class='titre'>Ministere: </td><td><% out.print(promot1); %></td>
				</tr>
				<tr>
					<td class='titre'>Secteur: </td><td><% out.print(promot2); %></td>
				</tr>
				<tr>
					<td class='titre'>Sous-secteur: </td><td><% out.print(promot3); %></td>
				</tr>
				<tr>
					<td colspan='2'>Justification du projet: </td>
				</tr>
				<tr>
					<td class='titre'>Besoins Identifies a satisfaire: </td><td><% out.print(justification1); %></td>
				</tr>
				<tr>
					<td class='titre'>Satuation actuelle : </td><td><% out.print(justification2); %></td>
				</tr>
				<tr>
					<td class='titre'>Situation desiree: </td><td><% out.print(justification3); %></td>
				</tr>
				<tr>
					<td colspan='2'>Objectifs du projet </td>
				</tr>
				<tr>
					<td class='titre'>Objectif global: </td><td><% out.print(objectif1); %></td>
				</tr>
				<tr>
					<td class='titre'>Objectif specifique: </td><td><% out.print(objectif2); %></td>
				</tr>
				<tr>
					<td colspan='2'>Ancrage du projet </td>
				</tr>
				<tr>
					<td class='titre'>Objectif strategique global: </td><td><% out.print(ancrage1); %></td>
				</tr>
				<tr>
					<td class='titre'>Objectif strategique: </td><td><% out.print(ancrage2); %></td>
				</tr>
				<tr>
					<td colspan='2'>Cible </td>
				</tr>
				<tr>
					<td class='titre'>Population cible: </td><td><% out.print(population); %></td>
				</tr>
				<tr>
					<td colspan='2'>Extrants du projet</td>
				</tr>
				<tr>
					<td class='titre'>Extrants escomptes: </td><td><% out.print(extrants); %></td>
				</tr>
				<tr>
					<td colspan='2'>Typologie du projet </td>
				</tr>
				<tr>
					<td class='titre'>Typologie : </td><td><% out.print(typologie); %></td>
				</tr>
				<tr>
					<td colspan='2'>Montage Institutionnel </td>
				</tr>
				<tr>
					<td class='titre'>Maitre d'oeuvre : </td><td><% out.print(maitre_Ovr); %></td>
				</tr>
				<tr>
					<td class='titre'>Maitre d'ouvrage: </td><td><% out.print(maitre_Ovg); %></td>
				</tr>
				<tr>
					<td class='titre'>Partenaire : </td><td><% out.print(partenaire); %></td>
				</tr>
				<tr>
					<td class='titre'>Service responsable: </td><td><% out.print(service); %></td>
				</tr>
				<tr>
					<td class='titre'>Agence d'execution: </td><td><% out.print("skjdjh"); %></td>
				</tr>
				<tr>
					<td colspan='2'>Montage Financier </td>
				</tr>
				<tr>
					<td class='titre'>Cout : </td><td><% out.print(cout); %></td>
				</tr>
				<tr>
					<td class='titre'>Nature du financement: </td><td><% out.print(nature_finance); %></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</div>
	<script>
		/*
		var donnees="iuy";
		$(document).ready(function(){
			$("#btnDelete").on('click',function(e){
				e.preventDefault();
				var donnees=$("#formTab").serialize();
				donnees+="&enregistrerProj=delete";
				if(confirm("Etes-vous sures de vouloir supprimer?")){
					$.ajax({
						url : '/suite_project/projet',
						type : 'POST',
						data : donnees,
						dataType : 'html',
						success : function(data, result, jqXHR){
							datas=data.split("??");
							datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>";
							$("#tbody").replaceWith(datas[0]);
							datas[1]="<th colspan='10' id='titreTab'>Liste des projets ("+datas[1]+")</th>";
							$("#titreTab").replaceWith(datas[1]);	
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) { 
				            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
				        } 
					});
				}
			});*/

    </script>
</body>
</html>