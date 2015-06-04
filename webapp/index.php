<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html lang="pt">
	<head>
		<title>Microdados Enem</title>
		<link href="css/stylesheet.css" type="text/css" rel="stylesheet" />
		<link href="css/table.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<div id="wrap">
			<div id="menu">
				<ul>
					<li><a href="index.php?page=itensEnem2012">Itens Enem 2012</a></li>
					<li><a href="index.php?page=comparativoNotas2012">Comparativo entre notas 2012</a></li>
					<li><a href="index.php?page=contato">Contato</a></li>
				</ul>
			</div>
			<div id="content">
				<?php
					if ( isset($_GET["page"]) ){
						$page = $_GET["page"];
						if ( strcmp($page, "contato") == 0 )
							include "contato.php";
						else
							include $page.".Form.php";
								
					}
					
				?>
			</div>
			<div>
				<p>Dados extraídos dos Microdados ENEM 2012</p>
			</div>
		</div>
	</body>
</html>
