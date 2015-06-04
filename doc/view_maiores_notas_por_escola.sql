CREATE TABLE maiores_notas_por_escola as 
select
v1.pk_cod_entidade,
v1.max_nu_nt_cn,
v2.max_nu_nt_ch,
v3.max_nu_nt_lc,
v4.max_nu_nt_mt,
v5.max_nu_nota_redacao
from
(SELECT pk_cod_entidade, max(nu_nt_cn) as max_nu_nt_cn FROM dados_enem_2012 GROUP BY pk_cod_entidade) as v1
inner join (SELECT PK_COD_ENTIDADE, max(nu_nt_ch) as max_nu_nt_ch FROM dados_enem_2012 GROUP BY pk_cod_entidade) as v2 on v1.pk_cod_entidade = v2.pk_cod_entidade
inner join (SELECT PK_COD_ENTIDADE, max(nu_nt_lc) as max_nu_nt_lc FROM dados_enem_2012 GROUP BY pk_cod_entidade) as v3 on v1.pk_cod_entidade = v3.pk_cod_entidade
inner join (SELECT PK_COD_ENTIDADE, max(nu_nt_mt) as max_nu_nt_mt FROM dados_enem_2012 GROUP BY pk_cod_entidade)  as v4 on v1.pk_cod_entidade = v4.pk_cod_entidade
inner join (SELECT PK_COD_ENTIDADE, max(nu_nota_redacao) as max_nu_nota_redacao FROM dados_enem_2012 GROUP BY pk_cod_entidade)  as v5 on v1.pk_cod_entidade = v5.pk_cod_entidade


create table vm_escolas_2012 as
SELECT distinct
PK_COD_ENTIDADE,
COD_MUNICIPIO_ESC,
NO_MUNICIPIO_ESC,
UF_ESC,
ID_DEPENDENCIA_ADM,
ID_LOCALIZACAO,
SIT_FUNC
FROM dados_enem_2012