--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4beta1
-- Started on 2015-08-08 00:14:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 208 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2205 (class 0 OID 0)
-- Dependencies: 208
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 174 (class 1259 OID 16386)
-- Name: check_list_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE check_list_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE check_list_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 175 (class 1259 OID 16388)
-- Name: check_list; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE check_list (
    chli_id integer DEFAULT nextval('check_list_seq'::regclass) NOT NULL,
    chli_iten_id integer NOT NULL,
    chli_fami_id integer NOT NULL,
    chli_descricao character varying(100) NOT NULL
);


ALTER TABLE check_list OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16392)
-- Name: empresas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE empresas_seq
    START WITH 3
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresas_seq OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 16394)
-- Name: empresas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE empresas (
    empr_id integer DEFAULT nextval('empresas_seq'::regclass) NOT NULL,
    empr_nome character varying(100) NOT NULL,
    empr_codigo integer NOT NULL
);


ALTER TABLE empresas OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16398)
-- Name: fabricantes_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fabricantes_seq
    START WITH 18
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fabricantes_seq OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16400)
-- Name: fabricantes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fabricantes (
    fabr_id integer DEFAULT nextval('fabricantes_seq'::regclass) NOT NULL,
    fabr_nome character varying(100) NOT NULL
);


ALTER TABLE fabricantes OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16404)
-- Name: familias_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE familias_seq
    START WITH 26
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE familias_seq OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16406)
-- Name: familias; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE familias (
    fami_id integer DEFAULT nextval('familias_seq'::regclass) NOT NULL,
    fami_codigo integer NOT NULL
);


ALTER TABLE familias OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16410)
-- Name: itens_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE itens_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE itens_seq OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16412)
-- Name: itens; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE itens (
    iten_id integer DEFAULT nextval('itens_seq'::regclass) NOT NULL,
    iten_descricao character varying(100) NOT NULL
);


ALTER TABLE itens OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16416)
-- Name: linhas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE linhas_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE linhas_seq OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16418)
-- Name: linhas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE linhas (
    linh_id integer DEFAULT nextval('linhas_seq'::regclass) NOT NULL,
    linh_nome character varying(100) NOT NULL
);


ALTER TABLE linhas OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16422)
-- Name: locais_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE locais_seq
    START WITH 4
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE locais_seq OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16424)
-- Name: locais; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE locais (
    loca_id integer DEFAULT nextval('locais_seq'::regclass) NOT NULL,
    loca_codigo integer NOT NULL,
    loca_descricao character varying(100) NOT NULL,
    loca_empr_id integer NOT NULL
);


ALTER TABLE locais OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16428)
-- Name: manutencoes_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE manutencoes_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE manutencoes_seq OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16430)
-- Name: manutencoes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE manutencoes (
    manu_id integer DEFAULT nextval('manutencoes_seq'::regclass) NOT NULL,
    manu_maqu_id integer NOT NULL,
    manu_usua_id_tecnico integer,
    manu_stat_id integer NOT NULL
);


ALTER TABLE manutencoes OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16434)
-- Name: manutencoes_corretivas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE manutencoes_corretivas (
    maco_manu_id integer DEFAULT nextval('manutencoes_seq'::regclass) NOT NULL,
    maco_seto_id integer NOT NULL,
    maco_usua_id_solicitante integer NOT NULL,
    maco_dt_abertura timestamp without time zone NOT NULL,
    maco_dt_realizada timestamp without time zone,
    maco_problema character varying(100) NOT NULL
);


ALTER TABLE manutencoes_corretivas OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 16438)
-- Name: manutencoes_preventivas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE manutencoes_preventivas (
    mapr_manu_id integer DEFAULT nextval('manutencoes_seq'::regclass) NOT NULL,
    mapr_dt_prevista timestamp without time zone NOT NULL,
    mapr_dt_realizada timestamp without time zone,
    mapr_observacoes text
);


ALTER TABLE manutencoes_preventivas OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16445)
-- Name: maquinas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE maquinas_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE maquinas_seq OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 16447)
-- Name: maquinas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE maquinas (
    maqu_id integer DEFAULT nextval('maquinas_seq'::regclass) NOT NULL,
    maqu_loca_id integer NOT NULL,
    maqu_post_id integer NOT NULL,
    maqu_fabr_id integer NOT NULL,
    maqu_fami_id integer NOT NULL,
    maqu_ordem integer NOT NULL,
    maqu_num_serie character varying(100) NOT NULL,
    maqu_modelo character varying(100) NOT NULL,
    maqu_descricao character varying(100),
    maqu_dt_fabricacao timestamp without time zone NOT NULL,
    maqu_dt_instalacao timestamp without time zone,
    maqu_ativa boolean NOT NULL,
    maqu_periodo_manutencao integer NOT NULL
);


ALTER TABLE maquinas OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 16451)
-- Name: maquinas_check_list_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE maquinas_check_list_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE maquinas_check_list_seq OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 16453)
-- Name: maquinas_check_list; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE maquinas_check_list (
    macl_id integer DEFAULT nextval('maquinas_check_list_seq'::regclass) NOT NULL,
    macl_maqu_id integer NOT NULL,
    macl_chli_id integer NOT NULL
);


ALTER TABLE maquinas_check_list OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16457)
-- Name: perfis_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE perfis_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE perfis_seq OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16459)
-- Name: perfis; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE perfis (
    perf_id integer DEFAULT nextval('perfis_seq'::regclass) NOT NULL,
    perf_nome character varying(100) NOT NULL,
    perf_descricao character varying(100)
);


ALTER TABLE perfis OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16463)
-- Name: postos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE postos_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE postos_seq OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16465)
-- Name: postos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE postos (
    post_id integer DEFAULT nextval('postos_seq'::regclass) NOT NULL,
    post_linh_id integer NOT NULL,
    post_descricao character varying(100) NOT NULL
);


ALTER TABLE postos OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16469)
-- Name: setores_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE setores_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE setores_seq OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16471)
-- Name: setores; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE setores (
    seto_id integer DEFAULT nextval('setores_seq'::regclass) NOT NULL,
    seto_nome character varying(100) NOT NULL
);


ALTER TABLE setores OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16475)
-- Name: status_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE status_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE status_seq OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16477)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE status (
    stat_id integer DEFAULT nextval('status_seq'::regclass) NOT NULL,
    stat_descricao character varying(100) NOT NULL
);


ALTER TABLE status OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16481)
-- Name: usuarios_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuarios_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE usuarios_seq OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16483)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuarios (
    usua_id integer DEFAULT nextval('usuarios_seq'::regclass) NOT NULL,
    usua_nome character varying(100) NOT NULL,
    usua_email character varying(100),
    usua_login character varying(50) NOT NULL,
    usua_senha character varying(50) NOT NULL,
    usua_ativo boolean NOT NULL,
    usua_dt_cadastro timestamp without time zone NOT NULL
);


ALTER TABLE usuarios OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16487)
-- Name: usuarios_perfis_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuarios_perfis_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE usuarios_perfis_seq OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16489)
-- Name: usuarios_perfis; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuarios_perfis (
    uspe_id integer DEFAULT nextval('usuarios_perfis_seq'::regclass) NOT NULL,
    uspe_usua_id integer NOT NULL,
    uspe_perf_id integer NOT NULL
);


ALTER TABLE usuarios_perfis OWNER TO postgres;

--
-- TOC entry 2165 (class 0 OID 16388)
-- Dependencies: 175
-- Data for Name: check_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY check_list (chli_id, chli_iten_id, chli_fami_id, chli_descricao) FROM stdin;
1	1	1	PRIMEIRA CONFIDURAÇÃO
2	2	1	PRIMEIRA CONFIDURAÇÃO
3	3	1	PRIMEIRA CONFIDURAÇÃO
4	4	1	PRIMEIRA CONFIDURAÇÃO
5	5	1	PRIMEIRA CONFIDURAÇÃO
6	6	1	PRIMEIRA CONFIDURAÇÃO
7	7	1	PRIMEIRA CONFIDURAÇÃO
8	8	1	PRIMEIRA CONFIDURAÇÃO
9	1	2	PRIMEIRA CONFIDURAÇÃO
10	2	2	PRIMEIRA CONFIDURAÇÃO
11	3	2	PRIMEIRA CONFIDURAÇÃO
12	4	2	PRIMEIRA CONFIDURAÇÃO
13	5	2	PRIMEIRA CONFIDURAÇÃO
14	6	2	PRIMEIRA CONFIDURAÇÃO
15	7	2	PRIMEIRA CONFIDURAÇÃO
16	8	2	PRIMEIRA CONFIDURAÇÃO
17	1	3	PRIMEIRA CONFIDURAÇÃO
18	2	3	PRIMEIRA CONFIDURAÇÃO
19	3	3	PRIMEIRA CONFIDURAÇÃO
20	4	3	PRIMEIRA CONFIDURAÇÃO
21	5	3	PRIMEIRA CONFIDURAÇÃO
22	6	3	PRIMEIRA CONFIDURAÇÃO
23	7	3	PRIMEIRA CONFIDURAÇÃO
24	8	3	PRIMEIRA CONFIDURAÇÃO
25	1	4	PRIMEIRA CONFIDURAÇÃO
26	2	4	PRIMEIRA CONFIDURAÇÃO
27	3	4	PRIMEIRA CONFIDURAÇÃO
28	4	4	PRIMEIRA CONFIDURAÇÃO
29	5	4	PRIMEIRA CONFIDURAÇÃO
30	6	4	PRIMEIRA CONFIDURAÇÃO
31	7	4	PRIMEIRA CONFIDURAÇÃO
32	8	4	PRIMEIRA CONFIDURAÇÃO
33	1	19	SEGUNDA CONFIGURAÇÃO
34	2	19	SEGUNDA CONFIGURAÇÃO
35	3	19	SEGUNDA CONFIGURAÇÃO
36	4	19	SEGUNDA CONFIGURAÇÃO
37	5	19	SEGUNDA CONFIGURAÇÃO
38	6	19	SEGUNDA CONFIGURAÇÃO
39	7	19	SEGUNDA CONFIGURAÇÃO
40	11	19	SEGUNDA CONFIGURAÇÃO
41	12	19	SEGUNDA CONFIGURAÇÃO
42	21	19	SEGUNDA CONFIGURAÇÃO
43	22	19	SEGUNDA CONFIGURAÇÃO
44	23	19	SEGUNDA CONFIGURAÇÃO
45	24	19	SEGUNDA CONFIGURAÇÃO
46	25	19	SEGUNDA CONFIGURAÇÃO
47	1	20	SEGUNDA CONFIGURAÇÃO
48	2	20	SEGUNDA CONFIGURAÇÃO
49	3	20	SEGUNDA CONFIGURAÇÃO
50	4	20	SEGUNDA CONFIGURAÇÃO
51	5	20	SEGUNDA CONFIGURAÇÃO
52	6	20	SEGUNDA CONFIGURAÇÃO
53	7	20	SEGUNDA CONFIGURAÇÃO
54	11	20	SEGUNDA CONFIGURAÇÃO
55	12	20	SEGUNDA CONFIGURAÇÃO
56	21	20	SEGUNDA CONFIGURAÇÃO
57	22	20	SEGUNDA CONFIGURAÇÃO
58	23	20	SEGUNDA CONFIGURAÇÃO
59	24	20	SEGUNDA CONFIGURAÇÃO
60	25	20	SEGUNDA CONFIGURAÇÃO
61	1	21	SEGUNDA CONFIGURAÇÃO
62	2	21	SEGUNDA CONFIGURAÇÃO
63	3	21	SEGUNDA CONFIGURAÇÃO
64	4	21	SEGUNDA CONFIGURAÇÃO
65	5	21	SEGUNDA CONFIGURAÇÃO
66	6	21	SEGUNDA CONFIGURAÇÃO
67	7	21	SEGUNDA CONFIGURAÇÃO
68	11	21	SEGUNDA CONFIGURAÇÃO
69	12	21	SEGUNDA CONFIGURAÇÃO
70	21	21	SEGUNDA CONFIGURAÇÃO
71	22	21	SEGUNDA CONFIGURAÇÃO
72	23	21	SEGUNDA CONFIGURAÇÃO
73	24	21	SEGUNDA CONFIGURAÇÃO
74	25	21	SEGUNDA CONFIGURAÇÃO
75	1	22	SEGUNDA CONFIGURAÇÃO
76	2	22	SEGUNDA CONFIGURAÇÃO
77	3	22	SEGUNDA CONFIGURAÇÃO
78	4	22	SEGUNDA CONFIGURAÇÃO
79	5	22	SEGUNDA CONFIGURAÇÃO
80	6	22	SEGUNDA CONFIGURAÇÃO
81	7	22	SEGUNDA CONFIGURAÇÃO
82	11	22	SEGUNDA CONFIGURAÇÃO
83	12	22	SEGUNDA CONFIGURAÇÃO
84	21	22	SEGUNDA CONFIGURAÇÃO
85	22	22	SEGUNDA CONFIGURAÇÃO
86	23	22	SEGUNDA CONFIGURAÇÃO
87	24	22	SEGUNDA CONFIGURAÇÃO
88	25	22	SEGUNDA CONFIGURAÇÃO
89	1	23	SEGUNDA CONFIGURAÇÃO
90	2	23	SEGUNDA CONFIGURAÇÃO
91	3	23	SEGUNDA CONFIGURAÇÃO
92	4	23	SEGUNDA CONFIGURAÇÃO
93	5	23	SEGUNDA CONFIGURAÇÃO
94	6	23	SEGUNDA CONFIGURAÇÃO
95	7	23	SEGUNDA CONFIGURAÇÃO
96	11	23	SEGUNDA CONFIGURAÇÃO
97	12	23	SEGUNDA CONFIGURAÇÃO
98	21	23	SEGUNDA CONFIGURAÇÃO
99	22	23	SEGUNDA CONFIGURAÇÃO
100	23	23	SEGUNDA CONFIGURAÇÃO
101	24	23	SEGUNDA CONFIGURAÇÃO
102	25	23	SEGUNDA CONFIGURAÇÃO
103	1	24	SEGUNDA CONFIGURAÇÃO
104	2	24	SEGUNDA CONFIGURAÇÃO
105	3	24	SEGUNDA CONFIGURAÇÃO
106	4	24	SEGUNDA CONFIGURAÇÃO
107	5	24	SEGUNDA CONFIGURAÇÃO
108	6	24	SEGUNDA CONFIGURAÇÃO
109	7	24	SEGUNDA CONFIGURAÇÃO
110	11	24	SEGUNDA CONFIGURAÇÃO
111	12	24	SEGUNDA CONFIGURAÇÃO
112	21	24	SEGUNDA CONFIGURAÇÃO
113	22	24	SEGUNDA CONFIGURAÇÃO
114	23	24	SEGUNDA CONFIGURAÇÃO
115	24	24	SEGUNDA CONFIGURAÇÃO
116	25	24	SEGUNDA CONFIGURAÇÃO
117	9	12	TERCEIRA CONFIGURAÇÃO
118	10	12	TERCEIRA CONFIGURAÇÃO
119	11	12	TERCEIRA CONFIGURAÇÃO
120	12	12	TERCEIRA CONFIGURAÇÃO
121	13	12	TERCEIRA CONFIGURAÇÃO
122	14	12	TERCEIRA CONFIGURAÇÃO
123	15	12	TERCEIRA CONFIGURAÇÃO
124	16	12	TERCEIRA CONFIGURAÇÃO
125	17	12	TERCEIRA CONFIGURAÇÃO
126	18	12	TERCEIRA CONFIGURAÇÃO
127	19	12	TERCEIRA CONFIGURAÇÃO
128	20	12	TERCEIRA CONFIGURAÇÃO
129	21	12	TERCEIRA CONFIGURAÇÃO
130	22	12	TERCEIRA CONFIGURAÇÃO
131	23	12	TERCEIRA CONFIGURAÇÃO
132	9	13	TERCEIRA CONFIGURAÇÃO
133	10	13	TERCEIRA CONFIGURAÇÃO
134	11	13	TERCEIRA CONFIGURAÇÃO
135	12	13	TERCEIRA CONFIGURAÇÃO
136	13	13	TERCEIRA CONFIGURAÇÃO
137	14	13	TERCEIRA CONFIGURAÇÃO
138	15	13	TERCEIRA CONFIGURAÇÃO
139	16	13	TERCEIRA CONFIGURAÇÃO
140	17	13	TERCEIRA CONFIGURAÇÃO
141	18	13	TERCEIRA CONFIGURAÇÃO
142	19	13	TERCEIRA CONFIGURAÇÃO
143	20	13	TERCEIRA CONFIGURAÇÃO
144	21	13	TERCEIRA CONFIGURAÇÃO
145	22	13	TERCEIRA CONFIGURAÇÃO
146	23	13	TERCEIRA CONFIGURAÇÃO
147	9	14	TERCEIRA CONFIGURAÇÃO
148	10	14	TERCEIRA CONFIGURAÇÃO
149	11	14	TERCEIRA CONFIGURAÇÃO
150	12	14	TERCEIRA CONFIGURAÇÃO
151	13	14	TERCEIRA CONFIGURAÇÃO
152	14	14	TERCEIRA CONFIGURAÇÃO
153	15	14	TERCEIRA CONFIGURAÇÃO
154	16	14	TERCEIRA CONFIGURAÇÃO
155	17	14	TERCEIRA CONFIGURAÇÃO
156	18	14	TERCEIRA CONFIGURAÇÃO
157	19	14	TERCEIRA CONFIGURAÇÃO
158	20	14	TERCEIRA CONFIGURAÇÃO
159	21	14	TERCEIRA CONFIGURAÇÃO
160	22	14	TERCEIRA CONFIGURAÇÃO
161	23	14	TERCEIRA CONFIGURAÇÃO
162	9	15	TERCEIRA CONFIGURAÇÃO
163	10	15	TERCEIRA CONFIGURAÇÃO
164	11	15	TERCEIRA CONFIGURAÇÃO
165	12	15	TERCEIRA CONFIGURAÇÃO
166	13	15	TERCEIRA CONFIGURAÇÃO
167	14	15	TERCEIRA CONFIGURAÇÃO
168	15	15	TERCEIRA CONFIGURAÇÃO
169	16	15	TERCEIRA CONFIGURAÇÃO
170	17	15	TERCEIRA CONFIGURAÇÃO
171	18	15	TERCEIRA CONFIGURAÇÃO
172	19	15	TERCEIRA CONFIGURAÇÃO
173	20	15	TERCEIRA CONFIGURAÇÃO
174	21	15	TERCEIRA CONFIGURAÇÃO
175	22	15	TERCEIRA CONFIGURAÇÃO
176	23	15	TERCEIRA CONFIGURAÇÃO
177	9	16	TERCEIRA CONFIGURAÇÃO
178	10	16	TERCEIRA CONFIGURAÇÃO
179	11	16	TERCEIRA CONFIGURAÇÃO
180	12	16	TERCEIRA CONFIGURAÇÃO
181	13	16	TERCEIRA CONFIGURAÇÃO
182	14	16	TERCEIRA CONFIGURAÇÃO
183	15	16	TERCEIRA CONFIGURAÇÃO
184	16	16	TERCEIRA CONFIGURAÇÃO
185	17	16	TERCEIRA CONFIGURAÇÃO
186	18	16	TERCEIRA CONFIGURAÇÃO
187	19	16	TERCEIRA CONFIGURAÇÃO
188	20	16	TERCEIRA CONFIGURAÇÃO
189	21	16	TERCEIRA CONFIGURAÇÃO
190	22	16	TERCEIRA CONFIGURAÇÃO
191	23	16	TERCEIRA CONFIGURAÇÃO
\.


--
-- TOC entry 2206 (class 0 OID 0)
-- Dependencies: 174
-- Name: check_list_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('check_list_seq', 191, true);


--
-- TOC entry 2167 (class 0 OID 16394)
-- Dependencies: 177
-- Data for Name: empresas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY empresas (empr_id, empr_nome, empr_codigo) FROM stdin;
1	DIGIBOARD ELETRÔNICA DA AMAZÔNIA LTDA	452
3	INSTITUTO DE TECNOLOGIA JOSÉ ROCHA SÉRGIO CARDOSO	954
\.


--
-- TOC entry 2207 (class 0 OID 0)
-- Dependencies: 176
-- Name: empresas_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresas_seq', 6, true);


--
-- TOC entry 2169 (class 0 OID 16400)
-- Dependencies: 179
-- Data for Name: fabricantes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fabricantes (fabr_id, fabr_nome) FROM stdin;
1	HOSOTEC
2	NORDSON
3	FUJI
4	DEK
5	TRI
6	KOH YOUNG
7	BTU
8	FONTON
9	ORVED & BROCK
10	TOLIHAN CORP
11	METCAL
12	BPM
13	SIEMENS
14	UNIVERSAL
15	NORDSON
16	ELETROVERT
17	PENDENTE
\.


--
-- TOC entry 2208 (class 0 OID 0)
-- Dependencies: 178
-- Name: fabricantes_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fabricantes_seq', 19, true);


--
-- TOC entry 2171 (class 0 OID 16406)
-- Dependencies: 181
-- Data for Name: familias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY familias (fami_id, fami_codigo) FROM stdin;
1	100039
2	100040
3	100041
4	100043
5	100048
6	100052
7	100054
8	100055
9	100056
10	100057
11	100059
12	100060
13	100061
14	200017
15	200080
16	200090
17	200199
18	200202
19	200204
20	200207
21	200215
22	200216
23	200217
24	200218
25	200221
26	200222
\.


--
-- TOC entry 2209 (class 0 OID 0)
-- Dependencies: 180
-- Name: familias_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('familias_seq', 27, true);


--
-- TOC entry 2173 (class 0 OID 16412)
-- Dependencies: 183
-- Data for Name: itens; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY itens (iten_id, iten_descricao) FROM stdin;
1	Preventiva
2	Corretiva
3	Lubrificação
4	JIG
5	ICT
6	Piloto
7	Subst. Ajuste
8	Subst. (Troca)
9	Desobstrução
10	Ajuste
11	Fixação
12	Aferição
13	Alteração
14	Protótipo
15	Otimização
16	Limpeza
17	Limp. / Lubrif.
18	Limp. / Fixação
19	Verificação
20	Rev. do Software
21	Recondicionamento
22	Fixture
23	Sem perda de Prod.
24	Com perda de Prod.
25	Construção / Reforma
\.


--
-- TOC entry 2210 (class 0 OID 0)
-- Dependencies: 182
-- Name: itens_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('itens_seq', 25, true);


--
-- TOC entry 2175 (class 0 OID 16418)
-- Dependencies: 185
-- Data for Name: linhas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY linhas (linh_id, linh_nome) FROM stdin;
1	LM-01
2	LM-02
3	LM-03
4	LM-04
5	LM-05
6	LM-06
7	LM-07
8	LM-08
9	LM-09
10	LM-10
11	LM-11
12	LM-12
13	LM-13
14	LM-14
15	LM-15
16	LM-16
17	NÃO POSSUI
\.


--
-- TOC entry 2211 (class 0 OID 0)
-- Dependencies: 184
-- Name: linhas_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('linhas_seq', 1, false);


--
-- TOC entry 2177 (class 0 OID 16424)
-- Dependencies: 187
-- Data for Name: locais; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY locais (loca_id, loca_codigo, loca_descricao, loca_empr_id) FROM stdin;
1	31	IAC-SMT-G3	1
2	8	IAC-PTH G4	1
3	2	II-UNIDADE-MB INFORMATICA	1
\.


--
-- TOC entry 2212 (class 0 OID 0)
-- Dependencies: 186
-- Name: locais_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('locais_seq', 5, true);


--
-- TOC entry 2179 (class 0 OID 16430)
-- Dependencies: 189
-- Data for Name: manutencoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY manutencoes (manu_id, manu_maqu_id, manu_usua_id_tecnico, manu_stat_id) FROM stdin;
38	67	\N	1
\.


--
-- TOC entry 2180 (class 0 OID 16434)
-- Dependencies: 190
-- Data for Name: manutencoes_corretivas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY manutencoes_corretivas (maco_manu_id, maco_seto_id, maco_usua_id_solicitante, maco_dt_abertura, maco_dt_realizada, maco_problema) FROM stdin;
38	1	1	2015-08-06 22:29:58.86	\N	NÃO ESTÁ REALIZANDO O TRABALHO CORRETAMENTE
\.


--
-- TOC entry 2181 (class 0 OID 16438)
-- Dependencies: 191
-- Data for Name: manutencoes_preventivas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY manutencoes_preventivas (mapr_manu_id, mapr_dt_prevista, mapr_dt_realizada, mapr_observacoes) FROM stdin;
\.


--
-- TOC entry 2213 (class 0 OID 0)
-- Dependencies: 188
-- Name: manutencoes_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('manutencoes_seq', 38, true);


--
-- TOC entry 2183 (class 0 OID 16447)
-- Dependencies: 193
-- Data for Name: maquinas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY maquinas (maqu_id, maqu_loca_id, maqu_post_id, maqu_fabr_id, maqu_fami_id, maqu_ordem, maqu_num_serie, maqu_modelo, maqu_descricao, maqu_dt_fabricacao, maqu_dt_instalacao, maqu_ativa, maqu_periodo_manutencao) FROM stdin;
67	1	1	1	1	1	NUMERO DE SERIE 0001	MODELO 0001	DESCRIÇÃO 0001	2015-01-01 00:00:00	2015-03-01 00:00:00	t	30
\.


--
-- TOC entry 2185 (class 0 OID 16453)
-- Dependencies: 195
-- Data for Name: maquinas_check_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY maquinas_check_list (macl_id, macl_maqu_id, macl_chli_id) FROM stdin;
43	67	1
44	67	2
45	67	3
46	67	4
47	67	5
48	67	6
\.


--
-- TOC entry 2214 (class 0 OID 0)
-- Dependencies: 194
-- Name: maquinas_check_list_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('maquinas_check_list_seq', 48, true);


--
-- TOC entry 2215 (class 0 OID 0)
-- Dependencies: 192
-- Name: maquinas_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('maquinas_seq', 67, true);


--
-- TOC entry 2187 (class 0 OID 16459)
-- Dependencies: 197
-- Data for Name: perfis; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY perfis (perf_id, perf_nome, perf_descricao) FROM stdin;
1	ROLE_ADMINISTRADOR	ADMINISTRADOR
2	ROLE_TECNICO	TÉCNICO
3	ROLE_LIDER_DE_LINHA	LIDER DE LINHA
\.


--
-- TOC entry 2216 (class 0 OID 0)
-- Dependencies: 196
-- Name: perfis_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('perfis_seq', 6, true);


--
-- TOC entry 2189 (class 0 OID 16465)
-- Dependencies: 199
-- Data for Name: postos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY postos (post_id, post_linh_id, post_descricao) FROM stdin;
1	1	POSTO-01
\.


--
-- TOC entry 2217 (class 0 OID 0)
-- Dependencies: 198
-- Name: postos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('postos_seq', 2, true);


--
-- TOC entry 2191 (class 0 OID 16471)
-- Dependencies: 201
-- Data for Name: setores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY setores (seto_id, seto_nome) FROM stdin;
1	SETOR DE MANUTENÇÃO
2	SETOR DE ENGENHARIA IAC
3	SETOR DE ENGENHARIA JIG
\.


--
-- TOC entry 2218 (class 0 OID 0)
-- Dependencies: 200
-- Name: setores_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('setores_seq', 5, true);


--
-- TOC entry 2193 (class 0 OID 16477)
-- Dependencies: 203
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY status (stat_id, stat_descricao) FROM stdin;
1	AGUARDANDO DELEGAÇÃO DA ENGENHARIA
2	AGUARDANDO REALIZAÇÃO DO TÉCNICO
3	AGUARDANDO APROVAÇÃO DA ENGENHARIA
4	REALIZADA
\.


--
-- TOC entry 2219 (class 0 OID 0)
-- Dependencies: 202
-- Name: status_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('status_seq', 4, true);


--
-- TOC entry 2195 (class 0 OID 16483)
-- Dependencies: 205
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuarios (usua_id, usua_nome, usua_email, usua_login, usua_senha, usua_ativo, usua_dt_cadastro) FROM stdin;
1	RODRIGO OTACILIO QUEIROZ LINS DA SILVA	roqls21@yahoo.com.br	rotacilio	e10adc3949ba59abbe56e057f20f883e	t	2015-06-30 20:52:33.75
2	TÉCNICO	tecnico@tecnico.com	tecnico	e10adc3949ba59abbe56e057f20f883e	t	2015-06-30 20:58:32.552
3	ADMINISTRADOR	administrador@admin.com	admin	e10adc3949ba59abbe56e057f20f883e	t	2015-06-30 20:59:09.233
4	LIDER DE LINHA	lider_de_linha@linha.com	liderlinha	e10adc3949ba59abbe56e057f20f883e	t	2015-06-30 20:59:35.198
\.


--
-- TOC entry 2197 (class 0 OID 16489)
-- Dependencies: 207
-- Data for Name: usuarios_perfis; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuarios_perfis (uspe_id, uspe_usua_id, uspe_perf_id) FROM stdin;
2	2	2
3	3	1
4	4	3
16	1	1
\.


--
-- TOC entry 2220 (class 0 OID 0)
-- Dependencies: 206
-- Name: usuarios_perfis_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuarios_perfis_seq', 16, true);


--
-- TOC entry 2221 (class 0 OID 0)
-- Dependencies: 204
-- Name: usuarios_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuarios_seq', 4, true);


--
-- TOC entry 2001 (class 2606 OID 16494)
-- Name: pk_check_list_chli_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY check_list
    ADD CONSTRAINT pk_check_list_chli_id PRIMARY KEY (chli_id);


--
-- TOC entry 2003 (class 2606 OID 16496)
-- Name: pk_empresas_empr_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY empresas
    ADD CONSTRAINT pk_empresas_empr_id PRIMARY KEY (empr_id);


--
-- TOC entry 2005 (class 2606 OID 16498)
-- Name: pk_fabricantes_fabr_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fabricantes
    ADD CONSTRAINT pk_fabricantes_fabr_id PRIMARY KEY (fabr_id);


--
-- TOC entry 2007 (class 2606 OID 16500)
-- Name: pk_familias_fami_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY familias
    ADD CONSTRAINT pk_familias_fami_id PRIMARY KEY (fami_id);


--
-- TOC entry 2009 (class 2606 OID 16502)
-- Name: pk_itens_iten_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY itens
    ADD CONSTRAINT pk_itens_iten_id PRIMARY KEY (iten_id);


--
-- TOC entry 2011 (class 2606 OID 16504)
-- Name: pk_linhas_linh_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY linhas
    ADD CONSTRAINT pk_linhas_linh_id PRIMARY KEY (linh_id);


--
-- TOC entry 2013 (class 2606 OID 16506)
-- Name: pk_locais_loca_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY locais
    ADD CONSTRAINT pk_locais_loca_id PRIMARY KEY (loca_id);


--
-- TOC entry 2017 (class 2606 OID 16508)
-- Name: pk_manutencoes_corretivas_maco_manu_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY manutencoes_corretivas
    ADD CONSTRAINT pk_manutencoes_corretivas_maco_manu_id PRIMARY KEY (maco_manu_id);


--
-- TOC entry 2015 (class 2606 OID 16510)
-- Name: pk_manutencoes_manu_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY manutencoes
    ADD CONSTRAINT pk_manutencoes_manu_id PRIMARY KEY (manu_id);


--
-- TOC entry 2019 (class 2606 OID 16512)
-- Name: pk_manutencoes_preventivas_mapr_manu_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY manutencoes_preventivas
    ADD CONSTRAINT pk_manutencoes_preventivas_mapr_manu_id PRIMARY KEY (mapr_manu_id);


--
-- TOC entry 2023 (class 2606 OID 16514)
-- Name: pk_maquinas_check_list_macl_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY maquinas_check_list
    ADD CONSTRAINT pk_maquinas_check_list_macl_id PRIMARY KEY (macl_id);


--
-- TOC entry 2021 (class 2606 OID 16516)
-- Name: pk_maquinas_maqu_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY maquinas
    ADD CONSTRAINT pk_maquinas_maqu_id PRIMARY KEY (maqu_id);


--
-- TOC entry 2025 (class 2606 OID 16518)
-- Name: pk_perfis_perf_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY perfis
    ADD CONSTRAINT pk_perfis_perf_id PRIMARY KEY (perf_id);


--
-- TOC entry 2027 (class 2606 OID 16520)
-- Name: pk_postos_post_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY postos
    ADD CONSTRAINT pk_postos_post_id PRIMARY KEY (post_id);


--
-- TOC entry 2029 (class 2606 OID 16522)
-- Name: pk_setores_seto_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY setores
    ADD CONSTRAINT pk_setores_seto_id PRIMARY KEY (seto_id);


--
-- TOC entry 2031 (class 2606 OID 16524)
-- Name: pk_status_stat_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY status
    ADD CONSTRAINT pk_status_stat_id PRIMARY KEY (stat_id);


--
-- TOC entry 2035 (class 2606 OID 16526)
-- Name: pk_usuarios_perfis_uspe_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuarios_perfis
    ADD CONSTRAINT pk_usuarios_perfis_uspe_id PRIMARY KEY (uspe_id);


--
-- TOC entry 2033 (class 2606 OID 16528)
-- Name: pk_usuarios_usua_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT pk_usuarios_usua_id PRIMARY KEY (usua_id);


--
-- TOC entry 2036 (class 2606 OID 16530)
-- Name: fk_check_list_chli_fami_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY check_list
    ADD CONSTRAINT fk_check_list_chli_fami_id FOREIGN KEY (chli_fami_id) REFERENCES familias(fami_id) MATCH FULL;


--
-- TOC entry 2037 (class 2606 OID 16535)
-- Name: fk_check_list_chli_iten_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY check_list
    ADD CONSTRAINT fk_check_list_chli_iten_id FOREIGN KEY (chli_iten_id) REFERENCES itens(iten_id) MATCH FULL;


--
-- TOC entry 2038 (class 2606 OID 16540)
-- Name: fk_locais_loca_empr_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY locais
    ADD CONSTRAINT fk_locais_loca_empr_id FOREIGN KEY (loca_empr_id) REFERENCES empresas(empr_id) MATCH FULL;


--
-- TOC entry 2042 (class 2606 OID 16545)
-- Name: fk_manutencoes_corretivas_maco_manu_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY manutencoes_corretivas
    ADD CONSTRAINT fk_manutencoes_corretivas_maco_manu_id FOREIGN KEY (maco_manu_id) REFERENCES manutencoes(manu_id) MATCH FULL;


--
-- TOC entry 2043 (class 2606 OID 16550)
-- Name: fk_manutencoes_corretivas_maco_seto_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY manutencoes_corretivas
    ADD CONSTRAINT fk_manutencoes_corretivas_maco_seto_id FOREIGN KEY (maco_seto_id) REFERENCES setores(seto_id) MATCH FULL;


--
-- TOC entry 2044 (class 2606 OID 16555)
-- Name: fk_manutencoes_corretivas_maco_usua_id_solicitante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY manutencoes_corretivas
    ADD CONSTRAINT fk_manutencoes_corretivas_maco_usua_id_solicitante FOREIGN KEY (maco_usua_id_solicitante) REFERENCES usuarios(usua_id) MATCH FULL;


--
-- TOC entry 2039 (class 2606 OID 16560)
-- Name: fk_manutencoes_manu_maqu_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY manutencoes
    ADD CONSTRAINT fk_manutencoes_manu_maqu_id FOREIGN KEY (manu_maqu_id) REFERENCES maquinas(maqu_id) MATCH FULL;


--
-- TOC entry 2040 (class 2606 OID 16565)
-- Name: fk_manutencoes_manu_stat_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY manutencoes
    ADD CONSTRAINT fk_manutencoes_manu_stat_id FOREIGN KEY (manu_stat_id) REFERENCES status(stat_id) MATCH FULL;


--
-- TOC entry 2041 (class 2606 OID 16570)
-- Name: fk_manutencoes_manu_usua_id_tecnico; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY manutencoes
    ADD CONSTRAINT fk_manutencoes_manu_usua_id_tecnico FOREIGN KEY (manu_usua_id_tecnico) REFERENCES usuarios(usua_id) MATCH FULL;


--
-- TOC entry 2045 (class 2606 OID 16575)
-- Name: fk_manutencoes_preventivas_mapr_manu_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY manutencoes_preventivas
    ADD CONSTRAINT fk_manutencoes_preventivas_mapr_manu_id FOREIGN KEY (mapr_manu_id) REFERENCES manutencoes(manu_id) MATCH FULL;


--
-- TOC entry 2050 (class 2606 OID 16580)
-- Name: fk_maquinas_check_list_macl_chli_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY maquinas_check_list
    ADD CONSTRAINT fk_maquinas_check_list_macl_chli_id FOREIGN KEY (macl_chli_id) REFERENCES check_list(chli_id) MATCH FULL;


--
-- TOC entry 2051 (class 2606 OID 16585)
-- Name: fk_maquinas_check_list_macl_maqu_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY maquinas_check_list
    ADD CONSTRAINT fk_maquinas_check_list_macl_maqu_id FOREIGN KEY (macl_maqu_id) REFERENCES maquinas(maqu_id) MATCH FULL;


--
-- TOC entry 2046 (class 2606 OID 16590)
-- Name: fk_maquinas_maqu_fabr_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY maquinas
    ADD CONSTRAINT fk_maquinas_maqu_fabr_id FOREIGN KEY (maqu_fabr_id) REFERENCES fabricantes(fabr_id) MATCH FULL;


--
-- TOC entry 2047 (class 2606 OID 16595)
-- Name: fk_maquinas_maqu_fami_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY maquinas
    ADD CONSTRAINT fk_maquinas_maqu_fami_id FOREIGN KEY (maqu_fami_id) REFERENCES familias(fami_id) MATCH FULL;


--
-- TOC entry 2048 (class 2606 OID 16600)
-- Name: fk_maquinas_maqu_loca_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY maquinas
    ADD CONSTRAINT fk_maquinas_maqu_loca_id FOREIGN KEY (maqu_loca_id) REFERENCES locais(loca_id) MATCH FULL;


--
-- TOC entry 2049 (class 2606 OID 16605)
-- Name: fk_maquinas_maqu_post_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY maquinas
    ADD CONSTRAINT fk_maquinas_maqu_post_id FOREIGN KEY (maqu_post_id) REFERENCES postos(post_id) MATCH FULL;


--
-- TOC entry 2052 (class 2606 OID 16610)
-- Name: fk_postos_post_linh_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postos
    ADD CONSTRAINT fk_postos_post_linh_id FOREIGN KEY (post_linh_id) REFERENCES linhas(linh_id) MATCH FULL;


--
-- TOC entry 2053 (class 2606 OID 16615)
-- Name: fk_usuarios_perfis_uspe_perf_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios_perfis
    ADD CONSTRAINT fk_usuarios_perfis_uspe_perf_id FOREIGN KEY (uspe_perf_id) REFERENCES perfis(perf_id) MATCH FULL;


--
-- TOC entry 2054 (class 2606 OID 16620)
-- Name: fk_usuarios_perfis_uspe_usua_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios_perfis
    ADD CONSTRAINT fk_usuarios_perfis_uspe_usua_id FOREIGN KEY (uspe_usua_id) REFERENCES usuarios(usua_id) MATCH FULL;


--
-- TOC entry 2204 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-08-08 00:14:28

--
-- PostgreSQL database dump complete
--

