<%@ page language="java" 
		 contentType="text/html; charset=ISO-8859-1"
    	 pageEncoding="ISO-8859-1"
    	 import="java.util.ArrayList , java.util.List , 
    	 pojo.Programme , pojo.Action , pojo.Projet , servlet.user"   	 
    	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Interface utilisateur de Suite Project</title>
        <link href="./design/designGlobal.css" type="text/css" rel="stylesheet"/>
        <link href="./design/style_formulaire.css" rel="stylesheet" type="text/css" />
    	<link rel="stylesheet" type="text/css" href="./design/styleUser.css" media="screen" />
		<style type="text/css">a#vlb{display:none}</style>
        <script type="text/javascript" src="./jquery/jquery.js"></script>
		<script type="text/javascript" src="./jscript/globalScript.js"></script>
    </head>	
    <style>
    	.commons{
    		border-radius:10px;
    	}
    </style>
    <body>
    <%
	    String lpubImg=(String)request.getAttribute("lPublicationImg");
		String lpubLnk=(String)request.getAttribute("lPublicationLink");
		String[] lpubContent=request.getAttribute("lPublicationContent").toString().split("---");
		String[] lpubAutor=request.getAttribute("lPublicationAutor").toString().split("---");
		String[] lpubDate=request.getAttribute("lPublicationDate").toString().split("---");
		String[] lpubTitles=request.getAttribute("lPublicationTitle").toString().split("---");
		String[] lpubSlideImg=request.getAttribute("lPublicationSlideImg").toString().split("---");
		
    %>
    	<!-- Entete du user -->
        <div id="entete">
        	<div id="banniere">
				<img width="100%" height="100%" src="./ressources/assets/suite_project_barn.jpg" />
				
				<div id="organisme">
					<p><span text-color='green'>MINISTERE</span><span> DES </span><span>TRANSPORTS</span></p>
				</div>
				<a style='float:top-right;' href='/suite_project/index?user' id='someLink'>Administration</a>
			</div>
            <table border="0" id="menu" cellspacing="10px">
            	<tr>
                	<td id="home"><img width="45%" height="45%" src="./assets/home.png" /><br/>Acceuil</td>
                    <td id="program"><img width="45%" height="45%" src="./assets/program.png" /><br/>Programmes</td>
                    <td id="action"><img width="45%" height="45%" src="./assets/action.png" /><br/>Actions</td>
                    <td id="projet" ><img width="45%" height="45%" src="./assets/projet.png" /><br/>Projets</td>
                    <td class="telecharger" ><img width="45%" height="45%" src="./assets/icone_telechargement.png" /><br/>Télécharger</td>
                    <td id="forum" ><img width="45%" height="45%" src="./assets/forum.png" /><br/>Forum</td>
                </tr>
            </table>
		</div>
		<br/>
        <div id="prolonge">
			
			  <!-- Acceuil ou je definis le sliding -->	
			  <div id="wowslider-container">
				 <div id="wowslider-images">
				 	<%
				 		for(int i=0;i<lpubSlideImg.length;i++){
				 			out.print("<a href='#'><img src='"+lpubSlideImg[i]+"' alt='"+lpubTitles[i]+"' title='"+lpubTitles[i]+"' /></a>");
				 		}
				 	%>
				 	<a href="#"><img src="./data/images/accident.jpg" alt="computer_0029" title="Des sponsors fiables qui oeuvrent fortement pour l'émergence 2035" /></a>
					
				 </div>	
			   </div>
			   <div id="blocProgram" class="commons" >
					<center>
						<!--h1>Programmes</h1>
						<h2>Liste des programmes </h2>
						<hr width="25%" />
						<br-->
						<table border="0">
							<tr>
								<th colspan="4">
									<table border="0" height="50%">
										<fieldset>
											<legend style="font-weight:bold;">PROGRAMMES</legend>
											<span style="font-size:2em;font-weight:bold;">Listes des programmes:</span>
										</fieldset>
									</table>
								</th>
							</tr>
							
							<tr> <th>Désignations</th> <th>Intitulés</th> <th>Objectifs</th> <th>Indicateurs</th> </tr>
							
							<%
								List<Programme> lprog = (List<Programme>)request.getAttribute("lProg");
								for(int i=0; i<lprog.size(); i++)
									out.println("<tr><td>"+lprog.get(i).getDesignation()+"</td><td>"+lprog.get(i).getIntitule()+"</td><td>"+lprog.get(i).getObjectifs()+"</td><td>"+lprog.get(i).getIndicateurs()+"</td></tr>");
							%>
						</table>
						
					</center>
			   </div>
		 
			   <!-- Affichages des actions -->
			   <div id="blocAction" class="commons">
					<center>
						<!--h1>Actions</h1>
						<h2>Affichage de la liste des actions du programme <h1>nomProgram</h1></h2>
						<hr width="25%" />
						<br-->
						<table border="0">
							<tr>
								<th colspan="5">
									<table border="0" height="50%">
										<fieldset>
											<legend style="font-weight:bold;">ACTIONS</legend>
											<span id="spano" style="font-size:2em;font-weight:bold;">Listes des actions par programmes 
										</fieldset>
									</table>
								</th>
							</tr>
							<tr> <th></th><th>Désignations</th> <th>Intitulés</th> <th>Objectifs</th> <th>Indicateurs</th> </tr>
							<%
								//String connexion = request.getParameter("connexion");
								//if(connexion != null){
									List<Action> lact = (List<Action>)request.getAttribute("lAct");
									for(int j=0;j<lprog.size();j++){
										out.println("<tr id='progr"+j+"' class='progr'><td colspan='5' style='background : #acb; border-left:2px solid white;border-top:2px solid white;border-right:2px solid white;'><b>"+lprog.get(j).getDesignation()+"</b> <a href='#'><span>Afficher actions</span> </span>&nbsp;&nbsp;<span>+</span></a></td></tr>");
										for(int i=0; i<lact.size(); i++)
											if(lprog.get(j).getDesignation().equals(lact.get(i).getCode_programme()))
												out.println("<tr class='progr"+j+"' ><td></td><td>"+lact.get(i).getDesignation()+"</td><td>"+lact.get(i).getIntitule()+"</td><td>"+lact.get(i).getObjectifs()+"</td><td>"+lact.get(i).getIndicateurs()+"</td></tr>");
									}	
								//}
								
								//}
							%>							
						</table>

					</center>
			   </div>
			   
			   <!-- Affichages des projets  -->
			   <div id="blocProjet" class="commons">
					<center>
						<!--h1>Projets</h1>
						<h2>Affichage de la liste des projets de l'action <h1>nomAction</h1> et du programme <h1>nomProgram</h1></h2>
						<hr width="25%" />
						<br-->
						<table border="0">
							<tr>
								<th colspan="8" ><table border="0" height="50%">
										<fieldset>
											<legend style="font-weight:bold;">PROJETS</legend>
											<span style="font-size:2em;font-weight:bold;">Listes des projets:</span>
										</fieldset>
									</table>
								</th>
							</tr>
							<tr> <th> </th><th> </th><th>Désignations</th> <th>Intitulés</th> <th>Objectifs</th> <th>Extrants</th> <th>Coûts</th><th>Operations</th></tr>
							
						<%
								List<Projet> lproj = (List<Projet>)request.getAttribute("lProj");
								for(int j=0;j<lprog.size();j++){
									out.println("<tr id='progr"+j+"' class='progr'><td colspan='8' style='background : #acb; border-left:2px solid white;border-top:2px solid white;border-right:2px solid white;'><b>"+lprog.get(j).getDesignation()+"</b><a href='#'><span> Details</span> </span>&nbsp;&nbsp;<span>+</span></a></td></tr>");
									for(int i=0; i<lact.size(); i++){
										if(lprog.get(j).getDesignation().equals(lact.get(i).getCode_programme())){
											out.println("<tr class='progr"+j+"' id='progr"+j+i+"'><td> </td><td colspan='7' style='background : #acb; border-left:2px solid white;border-top:2px solid white;border-right:2px solid white;'>"+lact.get(i).getDesignation()+"<a href='#'><span> details</span> </span>&nbsp;&nbsp;<span>+</span></a></td></tr>");
											for(int k=0; k<lproj.size(); k++){
												//System.out.println("code action-projet = "+lproj.get(k).getCode_action());
												if(lact.get(i).getDesignation().equals(lproj.get(k).getCode_action()) && lprog.get(j).getDesignation().equals(lproj.get(k).getCode_programme())){
													out.println("<tr class='progr"+j+i+"'><td> </td><td> </td><td >"+lproj.get(k).getDesignation()+"</td><td>"+lproj.get(k).getIntitule()+"</td><td>"+lproj.get(k).getObjectifsVises()+"</td><td>"+lproj.get(k).getExtrantsEscomptes()+"</td><td>"+lproj.get(k).getCoutDuProjet()+"</td><td><a href='/suite_project/fichiers/projet/"+lproj.get(k).getDesignation()+"/"+lproj.get(k).getPath()+"' rel='external' title='Fiche Projet utilisateur' class='"+lproj.get(k).getCocher()+"'>FP</a>&nbsp;<a href='' rel='external' title='TDR' class='"+lproj.get(k).getCocher()+"'>TDR</a>&nbsp;<a href='' rel='external' title='details' class='"+lproj.get(k).getCocher()+"'>DTL</a></td></tr>");
												}
											}
										}
									}
								}
						%>								
						</table>
					</center>
			   </div>
			   
			   <!-- Affichages des promoteurs  -->
			   <!--div id="blocPromoteur" class="commons">
					<center>
						<h1>Filtre non encore défini </h1>
						<br>
						<table border="0">
												
						</table>
					</center>
			   </div -->
			   
			   <!-- Plateforme du forum de discussion -->
			   <div id="blocForum" class="commons">
						<table align="center" border="" id="comments">
							<tr><th style="text-align:justify;"><h1>Forum de discussion</h1></th></tr>
							<tr><td>Enoncé du premier commentaire - 1</td></tr>
							<tr><td>suite avec le deuxième commentaire - 2 </td></tr>
							<tr><td>commentaire numéro - 3 </td></tr>
							<tr><td>Encore et toujours des commentaires mais surtt commentaire - 4</td></tr>
							<form method="post" action="">
								<tr>
									<td>
										<textarea rows="6em" cols="50em; name="commentaire"></textarea>
										<input type="submit" value="Poster" name="posterForum" />
									</td>
								</tr>
								
							</form>
						</table>
					
			   </div>
			   
			   <%
			   		int l=0;
			   		for(l=0;l<lpubContent.length;l++){
			   			out.print("<div id='blocPublication"+(l+1)+"' class='commons'><p>");
			   			out.print("<h1>"+lpubTitles[l]+"</h1>");
			   			out.print(lpubContent[l]+"</br>");
			   			out.print("<br><b>AUTEUR : "+lpubAutor[l]+"</b>");
			   			out.print("<br><i><u>Date de publication</u>: "+lpubDate[l]+"</i></p></div>");
			   		}
			   %>
			   
			  
			
			  <div id="blocTelechargements" class="commons">
				<center>
				
					<table border="0">
						<tr>
								
								<th colspan="2">
									<table border="0">
									<tr><td>
									<fieldset style="background:#9ba;">
										<legend><em>Téléchargements</em></legend>	
										<h1> Ressources disponibles </h1>
									</fieldset>
									</td></tr>
									</table>
								</th>
					   </tr>
						<tr> <th>Projets</th> <th id="projListener"><a href="#"><span>show details</span> </span>&nbsp;&nbsp;<span>+</span></a></th> </tr>
						<tr>
							<td colspan="2" >
								<table border="0" id="fp-dl">
									<thead>
									<tr> <th width="5em">Désignation</th> <th>Intitulé</th> <th>Objectifs</th> <th>Extrants</th> <th>Coûts</th> 
										<th>
											<fieldset>
												<legend>Filtre</legend>
												<form method="post" action="#">
													program 
														<select name="filtreProgTelechargements" id='progrs'>
															<%
															  	String dataList1=(String)request.getAttribute("dataList1");
															    out.print(dataList1);
															%>
														</select>
													Action
														<select name="filtreActionTelechargements" id='actProg'>
															<%
															  	String dataList2=(String)request.getAttribute("dataList2");
															    out.print(dataList2);
															%>
														</select>
														
													</fieldset>
														<table style='border:1px solid grey;border-radius:10px;'>
															<tr><td colspan='2'>Statut du projet</td></tr>
															<tr><td>Demarre</td><td><input type="radio" value="start" name="filtreChoixTelechargements" checked="checked"/></td></tr>
															<tr><td>Termine</td><td><input type="radio" value="ended" name="filtreChoixTelechargements" /></td></tr>
															<tr><td>Pas entamme</td><td><input type="radio" value="not-started" name="filtreChoixTelechargements" /></td></tr>
															<tr><td>En cours</td><td><input type="radio" value="running" name="filtreChoixTelechargements" /></td></tr>
														</table>
													<input type="submit" value="filtrerTelecharger" id='filtrerTelechargerProj'/>
												</form>
											
										</th> 
									</tr>
									</thead>
									<tbody>
									<%
										List<Projet> lprojTel = (List<Projet>)request.getAttribute("lProjTel");
										for(int i=0; i<lprojTel.size(); i++){
											System.out.println("chemin vers un fichier = "+lprojTel.get(i).getPath());
											out.println("<tr><td>"+lprojTel.get(i).getDesignation()+"</td><td>"+lprojTel.get(i).getIntitule()+"</td><td>"+lprojTel.get(i).getObjectifsVises()+"</td><td>"+lprojTel.get(i).getExtrantsEscomptes()+"</td><td>"+lprojTel.get(i).getCoutDuProjet()+"</td>");
											out.println("<td align ='left'><span> Dossier Projet </span><!-- a href='"+lprojTel.get(i).getPath()+"'>Télécharger</a>) <br/><span> TDR </span> (<a href='"+lprojTel.get(i).getTDRPath()+"'>Télécharger</a--></td></tr>");
										}
									%>
									</tbody>
								</table>
							</td>
						</tr>
						<tr> <th>Médias</th> <th id="mediaListener"><a href="#"><span>show details</span>&nbsp;&nbsp;<span>+</span></a></th> </tr>
						<tr>
							<td colspan="2">
								<table border="0" id="m-dl">
									<tr class="bgEntete">  <th>Désignation</th> <th>Date de post</th> <th>Opérations</th> </tr>
									<tr>  <td>set-up du navigateur MINT</td> <td>10/06/2015 11:09</td> <td><a href="#">détail </a>|<a href="#"> Télécharger</a></td></tr>
									<tr>  <td>set-up du navigateur MINT</td> <td>10/06/2015 11:09</td> <td><a href="#">détail </a>|<a href="#"> Télécharger</a></td></tr>
								</table>
							</td>
						</tr>
						
					</table>
				</center>
			  </div> 
			  
			  <div id="resultatRecherches" class="commons">
				<center>
				<h1>Résultats de la recherche </h1>
				</center>
			  </div> 
			  
			  <div id="resultatRecherchesProjet" class="commons">
				<center>
				<h1>Résultats de la recherche projet</h1>
			
				</center>
			  </div> 
			  
			  <!-- Présentation de l'état associé Ã  une fiche projet -->
			  <center>
				  <table border="0" id="etatFicheProjet">
						<tr> <th colspan="2">Fiche d'information de projet</th> </tr>
						<tr> <td>Intitulé</td> <td></td> </tr>
						<tr> <td>Contexte de mise en oeuvre</td> <td></td> </tr>
						<tr> <td>Promoteur</td> <td></td> </tr>
						<tr> <td>Programme concerné</td> <td></td> </tr>
						<tr> <td>Maitrise d'ouvrage</td> <td></td> </tr>
						<tr> <td>Maitrise d'oeuvre</td> <td></td> </tr>
						<tr> <td>Partenaires</td> <td></td> </tr>
						<tr> <td>Service responsable</td> <td></td> </tr>
						<tr> <td>Agence d'exécution</td> <td></td> </tr>
						<tr> <td>Description du projet</td> <td></td> </tr>
						<tr> <td>Objectifs visées</td> <td></td> </tr>
						<tr> <td>Justificatifs des besoins</td> <td></td> </tr>
						<tr> <td>Ancrage du projet</td> <td></td> </tr>
						<tr> <td>Coût du projet</td> <td></td> </tr>
						<tr> <td>Nature du financement</td> <td></td> </tr>
						<tr> <td>Population cible</td> <td></td> </tr>
						<tr> <td>Extrants escomptés</td> <td></td> </tr>
						<tr> <td>Effets attendus</td> <td></td> </tr>
						<tr> <td>Impactes</td> <td></td> </tr>
						<tr> <td>Typologie du projet</td> <td></td> </tr>
						<tr> <td colspan="2" style="text-align:center; border:none;background:#def;"><br/><a href="#">Télécharger</a> | <a href="#">Imprimer </a><br/></td><br/> </tr>
						
				  </table>

				  <table border="0" id="etatAction">
				    <tr><th colspan="2">Fiche de présentation de l'action</th></tr>
					<tr> <td>Intitulé</td> <td></td> </tr>
					<tr> <td>Objectifs</td> <td></td> </tr>
					<tr> <td>Indicateurs</td> <td></td> </tr>
					<tr> <td>Valeur de référence</td> <td></td> </tr>
					<tr> <td>Valeur cible</td> <td></td> </tr>
					<tr> <td colspan="2" style="text-align:center; border:none;background:#def;"><br/><a href="#">Télécharger| <a href="#">Imprimer </a></a> <br/></td><br/> </tr>
				  </table>

				  <table border="0" id="TDRProjet">
				    <tr><th colspan="2">Termes de référence du projet numéro désignationProjet</th></tr>
					<tr> <td>Intitulé</td> <td></td> </tr>
					<tr> <td>Contexte de mis en oeuvre</td> <td></td> </tr>
					<tr> <td>Objectifs</td> <td></td> </tr>
					<tr> <td>Couts</td> <td></td> </tr>
					<tr> <td>Délais</td> <td></td> </tr>
					<tr> <td colspan="2" style="text-align:center; border:none;background:#def;"><br/><a href="#">Télécharger| <a href="#">Imprimer </a></a> <br/></td><br/> </tr>
				  </table>

				
				
			  </center>
			 
		<div class="slideshow" id="supplements" align='center' >
			<% out.print(lpubImg); %>
		</div>
		
		 
		<!-- options programmes -->
		<div id="barreRecherche">
			<table border="0" >
					<form>
						<tr> <td  class="search"><input  type="text" name="zoneRechercheAction"></td><td class="buton"><input  type="submit" value=""  name="boutonRechercheAction"></td> </tr>
					</form>
			</table>
		</div>
		
		<div id="barreRechercheProjet">
			<form>
				<table border="0">
					
						<tr> 
							<td class="search"><input  type="text"  name="zoneRechercheProjet" /></td>
							<td class="buton"><input id="rech" type="submit" value="" name="boutonRechercheProjet" /></td> 
						</tr>
					
				</table>
			</form>
		</div>
		<!-- div id="optionProgram" class="option">
			<table width="100%" height="100%" border="0" id="optionProgramTable">
				<tr>
				<tr><td></td></tr>
			</table>
		</div -->
		 
		 
	
		<div id="optionProjet" class="option">
			<table width="100%" height="70%" border="0" id="optionProjetTable">
				<form method="get">
					<tr><td>Rechercher</td></tr>
					<tr>
						<td>
							<fieldset>
								<legend>Statut</legend>
								<label for="cours">En cours</label> <input type="radio" value="cours" name="choiceOptProj">
								<label for="wait">Attente</label> &nbsp;<input type="radio" value="wait" name="choiceOptProj">
								<label for="close"> Fermé</label> &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="close" name="choiceOptProj">
							</fieldset>
						</td>
					</tr>
					<tr><td><input type="submit" value="FiltrerOptProj"></td></tr>
				</form>
			</table>
		</div>
		
		<div id="optionForum" class="option">
			<!-- table width="100%" height="100%" border="0" id="optionForumTable">
				<tr><td>Poster</td></tr>
			</table -->
		</div>
		
		<!-- div id="man"><center><h1>Aide</h1><hr width="50%"/></center>
			<div id="manProgram">
				Usage de l'onglet programme
			</div>
			<div id="manAction">
				Usage de l'onglet action
			</div>
			<div id="manProjet">
				Usage de l'onglet projets
			</div>
			<div id="manForum">
				Usage de l'onglet Forum
			</div>
		</div -->
		</div>
		
		<!-- Pieds de page -->
		
		<div id="pieds">
				<center>
					<P> MINISTERE DES TRANSPORT - Tous droits réservés © 2015</br>
	mint@mint.gov</P>			
				</center>
		</div>	
		<script type="text/javascript" src="./jquery/script.js"></script>
		<script type="text/javascript">
			$("#progrs").on('change',function(e){
				e.preventDefault();
				var donnees="";
	
				donnees="programme="+$("#progrs option:selected").text();
				
				donnees+="&btn=listActions";
				//alert(donnees);
				$.ajax({
					url : '/suite_project/projet',
					type : 'GET',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						//alert(data);
						$("#actProg").html("<select name='filtreActionTelechargements' id='actProg'>"+data+"</select>");	
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
			            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
			        } 
				});
			});
			
			$("#filtrerTelechargerProj").on('click',function(e){
				e.preventDefault();
				var donnees="";
	
				donnees="programme="+$("#progrs option:selected").text();
				donnees+="&action="+$("#actProg option:selected").text();
				
				donnees+="&btn=filtreActProgr";
				//alert(donnees);
					$.ajax({
						url : '/suite_project/projet',
						type : 'GET',
						data : donnees,
						dataType : 'html',
						success : function(data, result, jqXHR){
							$("#fp-dl tbody").replaceWith("<tbody>"+data+"</tbody>");	
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) { 
				            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
				        } 
					});
			});
			
			$("#blocProjet table tr a[href='']").on('click',function(e){
				e.preventDefault();
				var operation=$(this).attr('title');
				var projet=$(this).attr('class');
					var donnees="";
		
					donnees="projet="+projet;
					
					donnees+="&enregistrerProj="+operation;
					alert(donnees);
						$.ajax({
							url : '/suite_project/projet',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								alert(data);
								if(opration=="Fiche Projet utilisateur"){
									alert(data);
									$(window).open('http://www.google.com');
									$(window).open("'"+data+"'");
									return false;
								}
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
					            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
					        } 
						});
			});
		</script>
		<script type="text/javascript">
		   $(function(){
		      setInterval(function(){
		         $(".slideshow ul").animate({marginLeft:-350},800,function(){
		            $(this).css({marginLeft:0}).find("li:last").after($(this).find("li:first"));
		         })
		      }, 10000);
		   });
		</script>
    </body>
</html>
