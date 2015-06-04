<?php
include_once "persist/SqlManager.class.php";

$conn = new SqlManager("connect");
$links_por_pag = 5;
$reg_por_pag = 20;
if (isset($_GET['pg'])) 
	$pg = $_GET['pg'];
else	
	$pg = "1";

$query = "SELECT * FROM itens_enem_2012 LIMIT {$reg_por_pag} OFFSET {$pg}";
$busca = pg_query($query);
$registros = pg_fetch_result($busca,0); 


$total = ($registros%$reg_por_pag==0)?$registros/$reg_por_pag:floor($registros/$reg_por_pag)+1;

$result = pg_query($query);
$result = pg_fetch_all($result);
	
	$return = "<table class='imagetable' align='center' border=0  cellpadding='0' cellspacing='0'>";
	$count_column = 0;
	foreach ( $result as $key=>$row ){
		$return .= "<tr>";
		foreach ( $row as $key=>$field ){
			$valor = utf8_decode($key);
			$return .= "<th>";
			$return .= "<label>" . $valor . "</label>";
			$return .= "</th>";
			$count_column++;
		}
		break;
		$return .= "</tr>";
	}

	foreach ( $result as $row ){
		$return .= "<tr>";
		foreach ( $row as $key=>$field ){
			$valor = utf8_decode($field);
			$return .= "<td align='center'>";
			$return .= "<label>" . $valor . "</label>";
			$return .= "</td>";
		}
		$return .= "</tr>";	
	}

if (((strlen($pg)>1) and (substr($pg, -1)!=0)) or ((strlen($pg)==1) and (substr($pg, -1)!=1))) {
  for ($i=1;$i<=9;$i++) {
     $fim = $pg+$i;
        if (substr($fim, -1)==0)  break;
  }
  for ($o=1;$o<=9;$o++) {
     $inicio = $pg-$o;
     if (strlen($pg)==1)
        if (substr($inicio, -1)==1)  break;
     if (strlen($pg)>1)
        if (substr($inicio, -1)==0)  break;
  }
} else {
  $inicio = $pg;
  if (strlen($pg)==1)
     $fim = $pg+9;
  else
     $fim = $pg+10;
}
$paginacao = "<tr><th align='center' colspan={$count_column}> <label id='paginacao'>";
for ($num=$inicio;$num<=$fim;$num++) {
  if ($pg==$num)
     $paginacao .= ' <b>['.$num.']</b> ';
  else
     $paginacao .= '<a href="?page=paginacao&pg='.$num.'"> '.$num.'</a>';
  if ($num==$total) break;
}
$paginacao .= "</label></td></th>";

$return .= $paginacao;
$return .= "</table>";
echo $return;

?>