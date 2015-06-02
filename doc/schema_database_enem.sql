--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.2
-- Dumped by pg_dump version 9.3.2
-- Started on 2015-06-02 12:57:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 176 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1966 (class 0 OID 0)
-- Dependencies: 176
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 175 (class 1259 OID 3692740)
-- Name: consistencia_enem_escola_2013; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE consistencia_enem_escola_2013 (
    id_enem_escola integer NOT NULL,
    nu_inscricao character(12) NOT NULL,
    nu_ano integer,
    cod_escola integer,
    cod_uf_esc integer,
    id_dependencia_adm_esc integer,
    id_localizacao_esc integer
);


ALTER TABLE public.consistencia_enem_escola_2013 OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 3656162)
-- Name: dados_enem_2012; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dados_enem_2012 (
    nu_inscricao character(12) NOT NULL,
    nu_ano integer,
    idade integer,
    tp_sexo integer,
    cod_municipio_insc integer,
    no_municipio_insc character(150),
    uf_insc character(2),
    st_conclusao integer,
    ano_concluiu integer,
    tp_escola integer,
    in_tp_ensino integer,
    tp_estado_civil integer,
    tp_cor_raca integer,
    in_unidade_hospitalar integer,
    in_baixa_visao integer,
    in_cegueira integer,
    in_surdez integer,
    in_deficiencia_auditiva integer,
    in_surdo_cegueira integer,
    in_deficiencia_fisica integer,
    in_deficiencia_mental integer,
    in_deficit_atencao integer,
    in_dislexia integer,
    in_gestante integer,
    in_lactante integer,
    in_idoso integer,
    in_autismo integer,
    in_sabatista integer,
    in_braille integer,
    in_ampliada integer,
    in_ledor integer,
    in_acesso integer,
    in_transcricao integer,
    in_libras integer,
    in_leitura_labial integer,
    in_mesa_cadeira_rodas integer,
    in_mesa_cadeira_separada integer,
    in_apoio_perna integer,
    in_guia_interprete integer,
    in_certificado integer,
    no_entidade_certificacao character(150),
    uf_entidade_certificacao character(2),
    pk_cod_entidade integer,
    cod_municipio_esc integer,
    no_municipio_esc character(150),
    uf_esc character(2),
    id_dependencia_adm integer,
    id_localizacao integer,
    sit_func integer,
    cod_municipio_prova integer,
    no_municipio_prova character(150),
    uf_municipio_prova character(2),
    in_presenca_cn integer,
    in_presenca_ch integer,
    in_presenca_lc integer,
    in_presenca_mt integer,
    nu_nt_cn double precision,
    nu_nt_ch integer,
    nu_nt_lc integer,
    nu_nt_mt integer,
    tx_respostas_cn character(45),
    tx_respostas_ch character(45),
    tx_respostas_lc character(45),
    tx_respostas_mt character(45),
    id_prova_cn integer,
    id_prova_ch integer,
    id_prova_lc integer,
    id_prova_mt integer,
    tp_lingua integer,
    ds_gabarito_cn character(45),
    ds_gabarito_ch character(45),
    ds_gabarito_lc character(50),
    ds_gabarito_mt character(45),
    in_status_redacao character(1),
    nu_nota_comp1 integer,
    nu_nota_comp2 integer,
    nu_nota_comp3 integer,
    nu_nota_comp4 integer,
    nu_nota_comp5 integer,
    nu_nota_redacao integer
);


ALTER TABLE public.dados_enem_2012 OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 3689232)
-- Name: itens_enem_2012; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE itens_enem_2012 (
    cod_area character(2),
    id_item integer,
    ds_gabarito character(1),
    id_habilidade integer,
    ds_cor character(20),
    id_prova integer,
    id integer NOT NULL,
    id_posicao character(2)
);


ALTER TABLE public.itens_enem_2012 OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 3689237)
-- Name: itens_enem_2012_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE itens_enem_2012_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.itens_enem_2012_id_seq OWNER TO postgres;

--
-- TOC entry 1967 (class 0 OID 0)
-- Dependencies: 173
-- Name: itens_enem_2012_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE itens_enem_2012_id_seq OWNED BY itens_enem_2012.id;


--
-- TOC entry 174 (class 1259 OID 3691016)
-- Name: microdados_enem_2013; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE microdados_enem_2013 (
    nu_inscricao character(12) NOT NULL,
    nu_ano integer,
    cod_municipio_residencia integer,
    no_municipio_residencia character(150),
    cod_uf_residencia character(2),
    uf_residencia character(2),
    in_estuda_classe_hospitalar integer,
    cod_escola integer,
    cod_municipio_esc integer,
    no_municipio_esc character(150),
    cod_uf_esc integer,
    uf_esc character(2),
    id_dependencia_adm_esc integer,
    id_localizacao_esc integer,
    sit_func_esc integer,
    idade integer,
    tp_sexo character(1),
    nacionalidade integer,
    cod_municipio_nascimento integer,
    no_municipio_nascimento character(150),
    cod_uf_nascimento character(2),
    uf_nascimento character(2),
    st_conclusao integer,
    ano_concluiu integer,
    tp_escola integer,
    in_tp_ensino integer,
    tp_estado_civil integer,
    tp_cor_raca integer,
    in_baixa_visao integer,
    in_cegueira integer,
    in_surdez integer,
    in_deficiencia_auditiva integer,
    in_surdo_cegueira integer,
    in_deficiencia_fisica integer,
    in_deficiencia_mental integer,
    in_deficit_atencao integer,
    in_dislexia integer,
    in_gestante integer,
    in_lactante integer,
    in_idoso integer,
    in_autismo integer,
    in_sabatista integer,
    in_braille integer,
    in_ampliada_24 integer,
    in_ampliada_18 integer,
    in_ledor integer,
    in_acesso integer,
    in_transcricao integer,
    in_libras integer,
    in_leitura_labial integer,
    in_mesa_cadeira_rodas integer,
    in_mesa_cadeira_separada integer,
    in_apoio_perna integer,
    in_guia_interprete integer,
    in_certificado character(1),
    no_entidade_certificacao character(150),
    cod_uf_entidade_certificacao character(2),
    uf_entidade_certificacao character(2),
    cod_municipio_prova integer,
    no_municipio_prova character(150),
    cod_uf_prova character(2),
    uf_prova character(2),
    in_presenca_cn integer,
    in_presenca_ch integer,
    in_presenca_lc integer,
    in_presenca_mt integer,
    id_prova_cn integer,
    id_prova_ch integer,
    id_prova_lc integer,
    id_prova_mt integer,
    nota_cn numeric(10,2),
    nota_ch numeric(10,2),
    nota_lc numeric(10,2),
    nota_mt numeric(10,2),
    tx_respostas_cn character(50),
    tx_respostas_ch character(50),
    tx_respostas_lc character(50),
    tx_respostas_mt character(50),
    tp_lingua integer,
    gabarito_cn character(50),
    gabarito_ch character(50),
    gabarito_lc character(50),
    gabarito_mt character(50),
    in_status_redacao integer,
    nu_nota_comp1 integer,
    nu_nota_comp2 integer,
    nu_nota_comp3 integer,
    nu_nota_comp4 integer,
    nu_nota_comp5 integer,
    nu_nota_redacao integer,
    q001 character(1),
    q002 character(1),
    q003 character(1),
    q004 integer,
    q005 character(1),
    q006 character(1),
    q007 character(1),
    q008 character(1),
    q009 character(1),
    q010 character(1),
    q011 character(1),
    q012 character(1),
    q013 character(1),
    q014 character(1),
    q015 character(1),
    q016 character(1),
    q017 character(1),
    q018 character(1),
    q019 character(1),
    q020 character(1),
    q021 character(1),
    q022 character(1),
    q023 character(1),
    q024 character(1),
    q025 character(1),
    q026 character(1),
    q027 character(1),
    q028 character(1),
    q029 character(1),
    q030 character(1),
    q031 character(1),
    q032 character(1),
    q033 character(1),
    q034 character(1),
    q035 character(1),
    q036 character(1),
    q037 character(1),
    q038 character(1),
    q039 character(1),
    q040 integer,
    q041 character(1),
    q042 character(1),
    q043 character(1),
    q044 character(1),
    q045 character(1),
    q046 character(1),
    q047 character(1),
    q048 character(1),
    q049 character(1),
    q050 character(1),
    q051 character(1),
    q052 character(1),
    q053 character(1),
    q054 character(1),
    q055 character(1),
    q056 character(1),
    q057 character(1),
    q058 character(1),
    q059 character(1),
    q060 character(1),
    q061 character(1),
    q062 character(1),
    q063 character(1),
    q064 character(1),
    q065 character(1),
    q066 character(1),
    q067 character(1),
    q068 character(1),
    q069 character(1),
    q070 character(1),
    q071 character(1),
    q072 character(1),
    q073 character(1),
    q074 character(1),
    q075 character(1),
    q076 character(1)
);


ALTER TABLE public.microdados_enem_2013 OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 3656228)
-- Name: questionario_enem_2012; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE questionario_enem_2012 (
    nu_inscricao character(12) NOT NULL,
    in_qse integer,
    q01 character(1),
    q02 character(1),
    q03 character(1),
    q04 character(2),
    q05 character(1),
    q06 character(1),
    q07 character(1),
    q08 character(1),
    q09 character(1),
    q10 character(1),
    q11 character(1),
    q12 character(1),
    q13 character(1),
    q14 character(1),
    q15 character(1),
    q16 character(1),
    q17 character(1),
    q18 character(1),
    q19 character(1),
    q20 character(1),
    q21 character(1),
    q22 character(1),
    q23 integer,
    q24 integer,
    q25 integer,
    q26 integer,
    q27 integer,
    q28 integer,
    q29 integer,
    q30 character(1),
    q31 character(1),
    q32 character(1),
    q33 character(1),
    q34 character(1),
    q35 character(1),
    q36 character(1),
    q37 character(1),
    q38 character(1),
    q39 character(1),
    q40 character(1),
    q41 character(1),
    q42 character(1),
    q43 character(1),
    q44 character(1),
    q45 character(1),
    q46 character(1),
    q47 character(1),
    q48 character(1),
    q49 character(1),
    q50 character(1),
    q51 character(1),
    q52 character(1),
    q53 character(1),
    q54 character(1),
    q55 character(1),
    q56 character(1),
    q57 character(1),
    q58 character(1),
    q59 character(1),
    q60 character(1),
    q61 character(1),
    q62 character(1)
);


ALTER TABLE public.questionario_enem_2012 OWNER TO postgres;

--
-- TOC entry 1841 (class 2604 OID 3689239)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itens_enem_2012 ALTER COLUMN id SET DEFAULT nextval('itens_enem_2012_id_seq'::regclass);


--
-- TOC entry 1843 (class 2606 OID 3656169)
-- Name: arquivo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dados_enem_2012
    ADD CONSTRAINT arquivo_pkey PRIMARY KEY (nu_inscricao);


--
-- TOC entry 1851 (class 2606 OID 3692772)
-- Name: pk_CONSISTENCIA_ENEM_ESCOLA_2013; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY consistencia_enem_escola_2013
    ADD CONSTRAINT "pk_CONSISTENCIA_ENEM_ESCOLA_2013" PRIMARY KEY (id_enem_escola);


--
-- TOC entry 1849 (class 2606 OID 3691023)
-- Name: pk_dados_2013; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY microdados_enem_2013
    ADD CONSTRAINT pk_dados_2013 PRIMARY KEY (nu_inscricao);


--
-- TOC entry 1847 (class 2606 OID 3689245)
-- Name: pk_itens_enem_2012; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY itens_enem_2012
    ADD CONSTRAINT pk_itens_enem_2012 PRIMARY KEY (id);


--
-- TOC entry 1845 (class 2606 OID 3656232)
-- Name: questionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY questionario_enem_2012
    ADD CONSTRAINT questionario_pkey PRIMARY KEY (nu_inscricao);


--
-- TOC entry 1965 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-06-02 12:57:20

--
-- PostgreSQL database dump complete
--

