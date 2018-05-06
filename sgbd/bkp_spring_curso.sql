--
-- PostgreSQL database dump
--

--
-- TOC entry 182 (class 1259 OID 59768)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id integer NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 59766)
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_id_seq OWNER TO postgres;

--
-- TOC entry 2273 (class 0 OID 0)
-- Dependencies: 181
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_id_seq OWNED BY categoria.id;


--
-- TOC entry 184 (class 1259 OID 59776)
-- Name: cidade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cidade (
    id integer NOT NULL,
    nome character varying(255),
    estado_id integer
);


ALTER TABLE public.cidade OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 59774)
-- Name: cidade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cidade_id_seq OWNER TO postgres;

--
-- TOC entry 2274 (class 0 OID 0)
-- Dependencies: 183
-- Name: cidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cidade_id_seq OWNED BY cidade.id;


--
-- TOC entry 186 (class 1259 OID 59784)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id integer NOT NULL,
    cpf_ou_cnpj character varying(255),
    email character varying(255),
    nome character varying(255),
    senha character varying(255) NOT NULL,
    tipo integer
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 59782)
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE public.cliente_id_seq OWNER TO postgres;

--
-- TOC entry 2275 (class 0 OID 0)
-- Dependencies: 185
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY cliente.id;


--
-- TOC entry 188 (class 1259 OID 59795)
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.endereco (
    id integer NOT NULL,
    bairro character varying(255),
    cep character varying(255),
    complemento character varying(255),
    logradouro character varying(255),
    numero character varying(255),
    cidade_id integer,
    cliente_id integer
);


CREATE SEQUENCE public.endereco OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 59793)
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE public.endereco_id_seq OWNER TO postgres;

--
-- TOC entry 2276 (class 0 OID 0)
-- Dependencies: 187
-- Name: endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.endereco_id_seq OWNED BY endereco.id;


--
-- TOC entry 190 (class 1259 OID 59806)
-- Name: estado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado (
    id integer NOT NULL,
    nome character varying(255)
);


CREATE SEQUENCE public.estado OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 59804)
-- Name: estado_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE public.estado_id_seq OWNER TO postgres;

--
-- TOC entry 2277 (class 0 OID 0)
-- Dependencies: 189
-- Name: estado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_id_seq OWNED BY estado.id;


--
-- TOC entry 191 (class 1259 OID 59812)
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_pedido (
    desconto double precision,
    preco double precision,
    quantidade integer,
    pedido_id integer NOT NULL,
    produto_id integer NOT NULL
);


CREATE SEQUENCE public.item_pedido OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 59817)
-- Name: pagamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pagamento (
    pedido_id integer NOT NULL,
    estado integer
);


CREATE SEQUENCE public.pagamento OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 59822)
-- Name: pagamento_com_boleto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pagamento_com_boleto (
    data_pagamento timestamp without time zone,
    data_vencimento timestamp without time zone,
    pedido_id integer NOT NULL
);


CREATE SEQUENCE public.pagamento_com_boleto OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 59827)
-- Name: pagamento_com_cartao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pagamento_com_cartao (
    numero_de_parcelas integer,
    pedido_id integer NOT NULL
);


CREATE SEQUENCE public.pagamento_com_cartao OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 59834)
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedido (
    id integer NOT NULL,
    instante timestamp without time zone,
    cliente_id integer,
    endereco_de_entrega_id integer
);


CREATE SEQUENCE public.pedido OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 59832)
-- Name: pedido_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE public.pedido_id_seq OWNER TO postgres;

--
-- TOC entry 2278 (class 0 OID 0)
-- Dependencies: 195
-- Name: pedido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pedido_id_seq OWNED BY pedido.id;


--
-- TOC entry 197 (class 1259 OID 59840)
-- Name: perfis_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.perfis_usuario (
    cliente_id integer NOT NULL,
    perfis integer
);


CREATE SEQUENCE public.perfis_usuario OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 59845)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
    id integer NOT NULL,
    nome character varying(255),
    preco double precision
);


CREATE SEQUENCE public.produto OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 59851)
-- Name: produto_categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto_categoria (
    produto_id integer NOT NULL,
    categoria_id integer NOT NULL
);


CREATE SEQUENCE public.produto_categoria OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 59843)
-- Name: produto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE public.produto_id_seq OWNER TO postgres;

--
-- TOC entry 2279 (class 0 OID 0)
-- Dependencies: 198
-- Name: produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produto_id_seq OWNED BY produto.id;


--
-- TOC entry 201 (class 1259 OID 59854)
-- Name: telefone; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.telefone (
    cliente_id integer NOT NULL,
    telefones character varying(255)
);


CREATE SEQUENCE public.telefone OWNER TO postgres;

--
-- TOC entry 2085 (class 2604 OID 59771)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ONLY categoria ALTER COLUMN id SET DEFAULT nextval('public.categoria_id_seq'::regclass);


--
-- TOC entry 2086 (class 2604 OID 59779)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ONLY cidade ALTER COLUMN id SET DEFAULT nextval('public.cidade_id_seq'::regclass);


--
-- TOC entry 2087 (class 2604 OID 59787)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ONLY cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- TOC entry 2088 (class 2604 OID 59798)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ONLY endereco ALTER COLUMN id SET DEFAULT nextval('public.endereco_id_seq'::regclass);


--
-- TOC entry 2089 (class 2604 OID 59809)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ONLY estado ALTER COLUMN id SET DEFAULT nextval('public.estado_id_seq'::regclass);


--
-- TOC entry 2090 (class 2604 OID 59837)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ONLY pedido ALTER COLUMN id SET DEFAULT nextval('public.pedido_id_seq'::regclass);


--
-- TOC entry 2091 (class 2604 OID 59848)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ONLY produto ALTER COLUMN id SET DEFAULT nextval('public.produto_id_seq'::regclass);


--
-- TOC entry 2245 (class 0 OID 59768)
-- Dependencies: 182
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY categoria (id, nome) FROM stdin;
1	Informática
2	Escritório
3	Cama mesa e banho
4	Eletrônicos
5	Jardinagem
6	Decoração
7	Perfumaria
8	Cat 0
9	Cat 1
10	Cat 2
11	Cat 3
12	Cat 4
13	Cat 5
14	Cat 6
15	Cat 7
16	Cat 8
17	Cat 9
18	Cat 10
19	Cat 11
20	Cat 12
21	Cat 13
22	Cat 14
23	Cat 15
24	Cat 16
25	Cat 17
26	Cat 18
27	Cat 19
28	Cat 20
29	Cat 21
30	Cat 22
31	Cat 23
32	Cat 24
33	Cat 25
34	Cat 26
35	Cat 27
36	Cat 28
37	Cat 29
38	Cat 30
39	Cat 31
40	Cat 32
41	Cat 33
42	Cat 34
43	Cat 35
44	Cat 36
45	Cat 37
46	Cat 38
47	Cat 39
48	Cat 40
49	Cat 41
50	Cat 42
51	Cat 43
52	Cat 44
53	Cat 45
54	Cat 46
55	Cat 47
56	Cat 48
57	Cat 49
58	Cat 50
59	Cat 51
60	Cat 52
61	Cat 53
62	Cat 54
\.


--
-- TOC entry 2280 (class 0 OID 0)
-- Dependencies: 181
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_id_seq', 62, true);


--
-- TOC entry 2247 (class 0 OID 59776)
-- Dependencies: 184
-- Data for Name: cidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cidade (id, nome, estado_id) FROM stdin;
1	Uberlândia	1
2	São Paulo	2
3	Campinas	2
\.


--
-- TOC entry 2281 (class 0 OID 0)
-- Dependencies: 183
-- Name: cidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cidade_id_seq', 3, true);


--
-- TOC entry 2249 (class 0 OID 59784)
-- Dependencies: 186
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cliente (id, cpf_ou_cnpj, email, nome, senha, tipo) FROM stdin;
1	36378912377	maria@gmail.com	Maria Silva	$2a$10$1RndOlQ76sm4bUF0uWvrKOFtDBXiMokQJ2Px1WerFy81QWZK234lG	1
2	57463261000	admin@gmail.com	Admin	$2a$10$9xXzWRvE4BrvN80Kj4nIJeAvqPCVBU.Y.N7roSPyzQ1hhd9h/Xuxm	1
\.


--
-- TOC entry 2282 (class 0 OID 0)
-- Dependencies: 185
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cliente_id_seq', 2, true);


--
-- TOC entry 2251 (class 0 OID 59795)
-- Dependencies: 188
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY endereco (id, bairro, cep, complemento, logradouro, numero, cidade_id, cliente_id) FROM stdin;
1	Jardim	38220834	Apto 303	Rua Flores	300	1	1
2	Centro	38777012	Sala 800	Avenida Matos	105	2	1
3	Jardim	38220834	Apto 303	Rua Flores	300	1	1
4	Centro	38777012	Sala 800	Avenida Matos	105	2	1
\.


--
-- TOC entry 2283 (class 0 OID 0)
-- Dependencies: 187
-- Name: endereco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('endereco_id_seq', 4, true);


--
-- TOC entry 2253 (class 0 OID 59806)
-- Dependencies: 190
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY estado (id, nome) FROM stdin;
1	Minas Gerais
2	São Paulo
\.


--
-- TOC entry 2284 (class 0 OID 0)
-- Dependencies: 189
-- Name: estado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('estado_id_seq', 2, true);


--
-- TOC entry 2254 (class 0 OID 59812)
-- Dependencies: 191
-- Data for Name: item_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY item_pedido (desconto, preco, quantidade, pedido_id, produto_id) FROM stdin;
0	2000	1	1	50
0	80	2	1	52
100	800	1	2	51
\.


--
-- TOC entry 2255 (class 0 OID 59817)
-- Dependencies: 192
-- Data for Name: pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pagamento (pedido_id, estado) FROM stdin;
1	2
2	1
\.


--
-- TOC entry 2256 (class 0 OID 59822)
-- Dependencies: 193
-- Data for Name: pagamento_com_boleto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pagamento_com_boleto (data_pagamento, data_vencimento, pedido_id) FROM stdin;
\N	2017-10-20 00:00:00	2
\.


--
-- TOC entry 2257 (class 0 OID 59827)
-- Dependencies: 194
-- Data for Name: pagamento_com_cartao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pagamento_com_cartao (numero_de_parcelas, pedido_id) FROM stdin;
6	1
\.


--
-- TOC entry 2259 (class 0 OID 59834)
-- Dependencies: 196
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pedido (id, instante, cliente_id, endereco_de_entrega_id) FROM stdin;
1	2017-09-30 10:32:00	1	1
2	2017-10-10 19:35:00	1	2
\.


--
-- TOC entry 2285 (class 0 OID 0)
-- Dependencies: 195
-- Name: pedido_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pedido_id_seq', 2, true);


--
-- TOC entry 2260 (class 0 OID 59840)
-- Dependencies: 197
-- Data for Name: perfis_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY perfis_usuario (cliente_id, perfis) FROM stdin;
1	1
2	0
2	1
\.


--
-- TOC entry 2262 (class 0 OID 59845)
-- Dependencies: 199
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY produto (id, nome, preco) FROM stdin;
2	Produto 2	1
3	Produto 3	1
4	Produto 4	1
5	Produto 5	1
6	Produto 6	1
7	Produto 7	1
8	Produto 8	1
9	Produto 9	1
10	Produto 10	1
11	Produto 11	1
12	Produto 12	1
13	Produto 13	1
14	Produto 14	1
15	Produto 15	1
16	Produto 16	1
17	Produto 17	1
18	Produto 18	1
19	Produto 19	1
20	Produto 20	1
21	Produto 21	1
22	Produto 22	1
23	Produto 23	1
24	Produto 24	1
25	Produto 25	1
26	Produto 26	1
27	Produto 27	1
28	Produto 28	1
29	Produto 29	1
30	Produto 30	1
31	Produto 31	1
32	Produto 32	1
33	Produto 33	1
34	Produto 34	1
35	Produto 35	1
36	Produto 36	1
37	Produto 37	1
38	Produto 38	1
39	Produto 39	1
40	Produto 40	1
41	Produto 41	1
42	Produto 42	1
43	Produto 43	1
44	Produto 44	1
45	Produto 45	1
46	Produto 46	1
47	Produto 47	1
48	Produto 48	1
49	Produto 49	1
50	Computador	2000
51	Impressora	800
52	Mouse	80
53	Mesa de escritório	300
54	Toalha	50
55	Colcha	200
56	TV true color	1200
57	Roçadeira	800
58	Abajour	100
59	Pendente	180
60	Shampoo	90
1	Produto 1	1
\.


--
-- TOC entry 2263 (class 0 OID 59851)
-- Dependencies: 200
-- Data for Name: produto_categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY produto_categoria (produto_id, categoria_id) FROM stdin;
1	1
2	1
3	1
4	1
5	1
6	1
7	1
8	1
9	1
10	1
11	1
12	1
13	1
14	1
15	1
16	1
17	1
18	1
19	1
20	1
21	1
22	1
23	1
24	1
25	1
26	1
27	1
28	1
29	1
30	1
31	1
32	1
33	1
34	1
35	1
36	1
37	1
38	1
39	1
40	1
41	1
42	1
43	1
44	1
45	1
46	1
47	1
48	1
49	1
50	1
50	1
50	4
51	1
51	2
51	1
51	2
51	4
52	1
52	1
52	4
53	2
54	3
55	3
56	4
57	5
58	6
59	6
60	7
\.


--
-- TOC entry 2286 (class 0 OID 0)
-- Dependencies: 198
-- Name: produto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('produto_id_seq', 60, true);


--
-- TOC entry 2264 (class 0 OID 59854)
-- Dependencies: 201
-- Data for Name: telefone; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY telefone (cliente_id, telefones) FROM stdin;
1	27363323
1	93838393
\.


--
-- TOC entry 2093 (class 2606 OID 59773)
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- TOC entry 2095 (class 2606 OID 59781)
-- Name: cidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);


--
-- TOC entry 2097 (class 2606 OID 59792)
-- Name: cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- TOC entry 2101 (class 2606 OID 59803)
-- Name: endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 2103 (class 2606 OID 59811)
-- Name: estado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);


--
-- TOC entry 2105 (class 2606 OID 59816)
-- Name: item_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT item_pedido_pkey PRIMARY KEY (pedido_id, produto_id);


--
-- TOC entry 2109 (class 2606 OID 59826)
-- Name: pagamento_com_boleto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagamento_com_boleto
    ADD CONSTRAINT pagamento_com_boleto_pkey PRIMARY KEY (pedido_id);


--
-- TOC entry 2111 (class 2606 OID 59831)
-- Name: pagamento_com_cartao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagamento_com_cartao
    ADD CONSTRAINT pagamento_com_cartao_pkey PRIMARY KEY (pedido_id);


--
-- TOC entry 2107 (class 2606 OID 59821)
-- Name: pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagamento
    ADD CONSTRAINT pagamento_pkey PRIMARY KEY (pedido_id);


--
-- TOC entry 2113 (class 2606 OID 59839)
-- Name: pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (id);


--
-- TOC entry 2115 (class 2606 OID 59850)
-- Name: produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 2099 (class 2606 OID 59858)
-- Name: uk_cmxo70m08n43599l3h0h07cc6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT uk_cmxo70m08n43599l3h0h07cc6 UNIQUE (email);


--
-- TOC entry 2128 (class 2606 OID 59919)
-- Name: fk1c0y58d3n6x3m6euv2j3h64vt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto_categoria
    ADD CONSTRAINT fk1c0y58d3n6x3m6euv2j3h64vt FOREIGN KEY (produto_id) REFERENCES produto(id);


--
-- TOC entry 2125 (class 2606 OID 59904)
-- Name: fk1fihyy2fnocpuwc74674qmfkv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk1fihyy2fnocpuwc74674qmfkv FOREIGN KEY (endereco_de_entrega_id) REFERENCES endereco(id);


--
-- TOC entry 2124 (class 2606 OID 59899)
-- Name: fk30s8j2ktpay6of18lbyqn3632; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk30s8j2ktpay6of18lbyqn3632 FOREIGN KEY (cliente_id) REFERENCES cliente(id);


--
-- TOC entry 2119 (class 2606 OID 59874)
-- Name: fk60ym08cfoysa17wrn1swyiuda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT fk60ym08cfoysa17wrn1swyiuda FOREIGN KEY (pedido_id) REFERENCES pedido(id);


--
-- TOC entry 2129 (class 2606 OID 59924)
-- Name: fk8aafha0njkoyoe3kvrwsy3g8u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY telefone
    ADD CONSTRAINT fk8aafha0njkoyoe3kvrwsy3g8u FOREIGN KEY (cliente_id) REFERENCES cliente(id);


--
-- TOC entry 2117 (class 2606 OID 59864)
-- Name: fk8b1kcb3wucapb8dejshyn5fsx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT fk8b1kcb3wucapb8dejshyn5fsx FOREIGN KEY (cidade_id) REFERENCES cidade(id);


--
-- TOC entry 2118 (class 2606 OID 59869)
-- Name: fk8s7ivtl4foyhrfam9xqom73n9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT fk8s7ivtl4foyhrfam9xqom73n9 FOREIGN KEY (cliente_id) REFERENCES cliente(id);


--
-- TOC entry 2126 (class 2606 OID 59909)
-- Name: fkcg0ogggka2ea7a3pnjinjtq0l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY perfis_usuario
    ADD CONSTRAINT fkcg0ogggka2ea7a3pnjinjtq0l FOREIGN KEY (cliente_id) REFERENCES cliente(id);


--
-- TOC entry 2122 (class 2606 OID 59889)
-- Name: fkcr74vrxf8nfph0knq2bho8doo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagamento_com_boleto
    ADD CONSTRAINT fkcr74vrxf8nfph0knq2bho8doo FOREIGN KEY (pedido_id) REFERENCES pagamento(pedido_id);


--
-- TOC entry 2116 (class 2606 OID 59859)
-- Name: fkkworrwk40xj58kevvh3evi500; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT fkkworrwk40xj58kevvh3evi500 FOREIGN KEY (estado_id) REFERENCES estado(id);


--
-- TOC entry 2127 (class 2606 OID 59914)
-- Name: fkq3g33tp7xk2juh53fbw6y4y57; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto_categoria
    ADD CONSTRAINT fkq3g33tp7xk2juh53fbw6y4y57 FOREIGN KEY (categoria_id) REFERENCES categoria(id);


--
-- TOC entry 2123 (class 2606 OID 59894)
-- Name: fkta3cdnuuxclwfh52t4qi432ow; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagamento_com_cartao
    ADD CONSTRAINT fkta3cdnuuxclwfh52t4qi432ow FOREIGN KEY (pedido_id) REFERENCES pagamento(pedido_id);


--
-- TOC entry 2121 (class 2606 OID 59884)
-- Name: fkthad9tkw4188hb3qo1lm5ueb0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagamento
    ADD CONSTRAINT fkthad9tkw4188hb3qo1lm5ueb0 FOREIGN KEY (pedido_id) REFERENCES pedido(id);


--
-- TOC entry 2120 (class 2606 OID 59879)
-- Name: fktk55mn6d6bvl5h0no5uagi3sf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT fktk55mn6d6bvl5h0no5uagi3sf FOREIGN KEY (produto_id) REFERENCES produto(id);


--
-- TOC entry 2271 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-05-06 11:51:52 -03

--
-- PostgreSQL database dump complete
--

