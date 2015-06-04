<?php
include_once "persist/SqlManager.class.php";
$conn = new SqlManager("connect");
$links_por_pag = 5;
$reg_por_pag = 20;

$pagina_atual = (isset($_GET['pg']) && is_numeric($_GET['pg'])) ? $_GET['pg'] : 1;


$select = "SELECT vm_escolas_2012.pk_cod_entidade as \"Código Entidade\", 
nome as \"Escola\",
no_municipio_esc as \"Município\", 
uf_esc as \"UF\", 
max_nu_nota_redacao as \"Maior nota da prova de redação\",
max_nu_nt_mt as \"Maior nota da prova de Matemática\", 
max_nu_nt_lc as \"Maior nota da prova de Linguagens e Códigos\", 
max_nu_nt_ch as \"Maior nota da prova de Ciências Humanas\", 
max_nu_nt_cn as \"Maior nota da prova de Ciências da Natureza\"";

$from = " FROM vm_escolas_2012 inner join vm_maiores_notas_por_escola_2012 on vm_escolas_2012.pk_cod_entidade = vm_maiores_notas_por_escola_2012.pk_cod_entidade where uf_esc = 'RJ' and max_nu_nota_redacao is not null";
$limit .= " ORDER BY  max_nu_nota_redacao desc, max_nu_nt_mt desc, max_nu_nt_lc desc, max_nu_nt_ch desc, max_nu_nt_cn desc 
 LIMIT {$reg_por_pag} OFFSET {$pagina_atual}";


$sqlContador = "SELECT COUNT(*) AS total ".$from;
$busca = pg_query($sqlContador);
$registros = pg_fetch_result($busca,0); 

$total = ($registros%$reg_por_pag==0)?$registros/$reg_por_pag:floor($registros/$reg_por_pag)+1;


$result = pg_query($select.$from.$limit);
$result = pg_fetch_all($result);
	
$return = "<table class='imagetable' align='center' border=0  cellpadding='0' cellspacing='0'>";
$count_column = 0;
foreach ( $result as $key=>$row ){
	$return .= "<tr>";
	foreach ( $row as $key=>$field ){
		$valor = $key;
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
     $paginacao .= '<a href="?page=comparativoNotas2012&pg='.$num.'"> '.$num.'</a>';
  if ($num==$total) break;
}
$paginacao .= "</label></td></th>";

$return .= $paginacao;
$return .= "</table>";
echo $return;

?>