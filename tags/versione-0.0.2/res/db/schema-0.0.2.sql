--
-- PostgreSQL database dump
--

-- Started on 2010-05-06 01:25:37

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1959 (class 1262 OID 17028)
-- Name: pegaso; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE pegaso WITH TEMPLATE = template0 ENCODING = 'UTF8';


ALTER DATABASE pegaso OWNER TO postgres;

\connect pegaso

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 6 (class 2615 OID 23078)
-- Name: doc; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA doc;


ALTER SCHEMA doc OWNER TO postgres;

--
-- TOC entry 1960 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 334 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE PROCEDURAL LANGUAGE plpgsql;


SET search_path = doc, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1375 (class 1259 OID 31748)
-- Dependencies: 6
-- Name: acconto; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE acconto (
    id bigint NOT NULL,
    data date,
    totale double precision,
    note character varying,
    fattura bigint
);


ALTER TABLE doc.acconto OWNER TO postgres;

--
-- TOC entry 1383 (class 1259 OID 34510)
-- Dependencies: 6
-- Name: bolla; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE bolla (
    id bigint NOT NULL
);


ALTER TABLE doc.bolla OWNER TO postgres;

--
-- TOC entry 1379 (class 1259 OID 31825)
-- Dependencies: 6
-- Name: consegna; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE consegna (
    id bigint NOT NULL,
    descrizione character varying
);


ALTER TABLE doc.consegna OWNER TO postgres;

--
-- TOC entry 1378 (class 1259 OID 31818)
-- Dependencies: 6
-- Name: ddt; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE ddt (
    id bigint NOT NULL,
    serie character(3),
    sconto double precision,
    spese_trasporto double precision,
    spese_incasso double precision,
    causale bigint,
    riferimento character varying,
    consegna_a_mezzo bigint,
    porto bigint,
    pagamento bigint,
    colli integer,
    destinazione bigint
);


ALTER TABLE doc.ddt OWNER TO postgres;

--
-- TOC entry 1373 (class 1259 OID 31698)
-- Dependencies: 1741 1742 1743 6
-- Name: dettaglio_documento; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE dettaglio_documento (
    id bigint NOT NULL,
    descrizione character varying,
    um character(1),
    iva bigint,
    quantita double precision DEFAULT 0,
    imponibile double precision DEFAULT 0,
    sconto integer DEFAULT 0,
    documento bigint
);


ALTER TABLE doc.dettaglio_documento OWNER TO postgres;

--
-- TOC entry 1370 (class 1259 OID 23079)
-- Dependencies: 6
-- Name: documento; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE documento (
    id bigint NOT NULL,
    data_emissione date,
    tipo_documento smallint,
    cliente bigint,
    note character varying,
    numero_documento bigint
);


ALTER TABLE doc.documento OWNER TO postgres;

--
-- TOC entry 1376 (class 1259 OID 31760)
-- Dependencies: 6
-- Name: fattura; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE fattura (
    id bigint NOT NULL,
    spese_trasporto double precision,
    spese_incasso double precision,
    pagamento bigint,
    note_pagamento character varying,
    serie character(1),
    sconto double precision,
    banca_abi character(5),
    banca_cab character(5),
    banca_iban character varying(1)
);


ALTER TABLE doc.fattura OWNER TO postgres;

--
-- TOC entry 1374 (class 1259 OID 31713)
-- Dependencies: 6
-- Name: iva; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE iva (
    id bigint NOT NULL,
    codice_iva integer,
    iva integer,
    descrizione character varying
);


ALTER TABLE doc.iva OWNER TO postgres;

--
-- TOC entry 1371 (class 1259 OID 31659)
-- Dependencies: 1738 1739 1740 6
-- Name: ordine; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE ordine (
    id bigint NOT NULL,
    sconto integer DEFAULT 0,
    spese_trasporto double precision DEFAULT 0,
    spese_incasso double precision DEFAULT 0,
    annotazioni character varying,
    pagamento bigint,
    note_pagamento character varying,
    banca_abi character(5),
    banca_cab character(5),
    banca_iban character varying,
    stato bigint,
    convertito integer,
    tipo_documento character(3)
);


ALTER TABLE doc.ordine OWNER TO postgres;

--
-- TOC entry 1380 (class 1259 OID 31832)
-- Dependencies: 6
-- Name: porto; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE porto (
    id bigint NOT NULL,
    descrizione character varying
);


ALTER TABLE doc.porto OWNER TO postgres;

--
-- TOC entry 1377 (class 1259 OID 31801)
-- Dependencies: 6
-- Name: ricevuta; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE ricevuta (
    id bigint NOT NULL,
    spese_trasporto double precision,
    spese_incasso double precision,
    pagamento bigint,
    note_pagamento character varying,
    serie character(3),
    sconto double precision,
    banca_abi character(5),
    banca_cab character(5),
    banca_iban character varying(1)
);


ALTER TABLE doc.ricevuta OWNER TO postgres;

--
-- TOC entry 1372 (class 1259 OID 31674)
-- Dependencies: 6
-- Name: stato; Type: TABLE; Schema: doc; Owner: postgres; Tablespace: 
--

CREATE TABLE stato (
    id bigint NOT NULL,
    descrizione character varying
);


ALTER TABLE doc.stato OWNER TO postgres;

SET search_path = public, pg_catalog;

--
-- TOC entry 1339 (class 1259 OID 17029)
-- Dependencies: 1720 1721 5
-- Name: articolo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articolo (
    idarticolo bigint NOT NULL,
    codfornitore character varying(40),
    codbarre character varying(40),
    descrizione character varying(100) NOT NULL,
    prezzo_acquisto double precision,
    iva bigint,
    um bigint,
    prezzo_dettaglio double precision,
    prezzo_ingrosso double precision,
    imballo character varying(50),
    peso double precision,
    sconto bigint,
    idreparto bigint,
    colore character varying(40),
    scorta_minima bigint,
    note character varying(200),
    data_inserimento date,
    carico_iniziale bigint,
    scorta_massima bigint,
    numero_pacchetti integer DEFAULT 0,
    idpannello bigint,
    qta_infinita boolean DEFAULT false
);


ALTER TABLE public.articolo OWNER TO postgres;

--
-- TOC entry 1340 (class 1259 OID 17036)
-- Dependencies: 1722 1723 1724 1725 1726 5
-- Name: carico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE carico (
    idcarico bigint NOT NULL,
    idfornitore bigint,
    data_carico date,
    ora_carico time without time zone,
    note character varying(200),
    iddocumento bigint,
    num_documento character varying,
    data_documento date,
    totale_documento double precision,
    sospeso integer DEFAULT 0,
    rif_doc integer DEFAULT -1,
    sconto integer DEFAULT 0,
    iva_documento integer DEFAULT -1,
    ins_pn integer DEFAULT 0,
    riferimento_ordine bigint
);


ALTER TABLE public.carico OWNER TO postgres;

--
-- TOC entry 1341 (class 1259 OID 17046)
-- Dependencies: 1727 5
-- Name: dettaglio_carico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dettaglio_carico (
    idarticolo bigint NOT NULL,
    idcarico bigint NOT NULL,
    qta double precision NOT NULL,
    prezzo_acquisto double precision,
    sconto integer DEFAULT 0
);


ALTER TABLE public.dettaglio_carico OWNER TO postgres;

--
-- TOC entry 1342 (class 1259 OID 17049)
-- Dependencies: 5
-- Name: fornitore; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fornitore (
    idfornitore bigint NOT NULL,
    data_inserimento date,
    nome character varying(100) NOT NULL,
    piva character varying(40),
    codfisc character varying(40),
    via character varying(40),
    cap character varying(40),
    citta character varying(40),
    tel character varying(40),
    cell character varying(40),
    fax character varying(40),
    email character varying(40),
    website character varying(40),
    note character varying(200),
    codbarre character varying,
    provincia bigint
);


ALTER TABLE public.fornitore OWNER TO postgres;

--
-- TOC entry 1343 (class 1259 OID 17054)
-- Dependencies: 5
-- Name: um; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE um (
    idum bigint NOT NULL,
    nome character varying(40) NOT NULL,
    descrizione character varying(100)
);


ALTER TABLE public.um OWNER TO postgres;

--
-- TOC entry 1344 (class 1259 OID 17056)
-- Dependencies: 1458 5
-- Name: articoli_caricati_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW articoli_caricati_view AS
    SELECT d.idarticolo, d.idcarico, a.codbarre, a.descrizione, a.iva, u.nome AS um, d.qta, d.prezzo_acquisto, d.sconto FROM articolo a, carico c, dettaglio_carico d, fornitore f, um u WHERE ((((a.idarticolo = d.idarticolo) AND (c.idcarico = d.idcarico)) AND (c.idfornitore = f.idfornitore)) AND (u.idum = a.um));


ALTER TABLE public.articoli_caricati_view OWNER TO postgres;

--
-- TOC entry 1345 (class 1259 OID 17059)
-- Dependencies: 5
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cliente (
    idcliente bigint NOT NULL,
    data_inserimento date,
    nome character varying(40) NOT NULL,
    cognome character varying(40) NOT NULL,
    piva character varying(40),
    codfisc character varying(40),
    via character varying(40),
    cap character varying(40),
    citta character varying(40),
    tel character varying(40),
    cell character varying(40),
    fax character varying(40),
    email character varying(50),
    website character varying(40),
    note character varying(200),
    data_nascita date,
    num_doc character varying,
    rilasciato_il date,
    nato_a character varying,
    intestazione character varying,
    provincia bigint,
    documento bigint,
    rilasciato_da bigint,
    nazionalita bigint,
    rilasciato_di character varying
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 1346 (class 1259 OID 17064)
-- Dependencies: 5
-- Name: dettaglio_scarico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dettaglio_scarico (
    idordine bigint NOT NULL,
    idarticolo bigint NOT NULL,
    qta double precision NOT NULL,
    sconto bigint,
    prezzo_acquisto double precision,
    prezzo_vendita double precision,
    iva integer
);


ALTER TABLE public.dettaglio_scarico OWNER TO postgres;

--
-- TOC entry 1347 (class 1259 OID 17066)
-- Dependencies: 1728 1729 1730 1731 1732 1733 5
-- Name: scarico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE scarico (
    idordine bigint NOT NULL,
    idcliente bigint,
    data_ordine date,
    ora_ordine time without time zone,
    note character varying(200),
    tipo_documento bigint,
    num_documento character varying,
    data_documento date,
    totale_documento double precision,
    iva_documento integer DEFAULT -1,
    doc_emesso integer,
    doc_fiscale integer,
    ins_pn integer DEFAULT 0,
    idpagamento bigint,
    idcausale bigint,
    spese_incasso double precision DEFAULT 0,
    spese_trasporto double precision DEFAULT 0,
    data_trasp date,
    ora_trasp time with time zone,
    colli integer,
    peso double precision DEFAULT 0,
    consegna character varying,
    porto character varying,
    diversa_dest character varying,
    idaspetto bigint,
    sconto integer DEFAULT 0,
    pagamento bigint,
    sconto_euro double precision,
    numero_fattura character varying(200),
    numero_ricevuta character varying(20),
    numero_non_fiscale character varying(20),
    riferimento_ordine bigint
);


ALTER TABLE public.scarico OWNER TO postgres;

--
-- TOC entry 1348 (class 1259 OID 17077)
-- Dependencies: 1459 5
-- Name: articoli_scaricati_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW articoli_scaricati_view AS
    SELECT d.idordine, d.idarticolo, a.codbarre, a.descrizione, d.prezzo_acquisto, a.prezzo_ingrosso, a.prezzo_dettaglio, a.iva, u.nome AS um, d.qta, d.sconto, d.prezzo_vendita FROM articolo a, dettaglio_scarico d, scarico o, cliente c, um u WHERE ((((a.idarticolo = d.idarticolo) AND (d.idordine = o.idordine)) AND (o.idcliente = c.idcliente)) AND (u.idum = a.um));


ALTER TABLE public.articoli_scaricati_view OWNER TO postgres;

--
-- TOC entry 1349 (class 1259 OID 17080)
-- Dependencies: 5
-- Name: aspetto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aspetto (
    idaspetto bigint NOT NULL,
    nome character varying(40) NOT NULL
);


ALTER TABLE public.aspetto OWNER TO postgres;

--
-- TOC entry 1350 (class 1259 OID 17087)
-- Dependencies: 5
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE categoria (
    id bigint NOT NULL,
    data_creazione date,
    descrizione character varying NOT NULL,
    note character varying
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 1351 (class 1259 OID 17092)
-- Dependencies: 5
-- Name: causale; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE causale (
    idcausale bigint NOT NULL,
    nome character varying(40) NOT NULL
);


ALTER TABLE public.causale OWNER TO postgres;

--
-- TOC entry 1382 (class 1259 OID 31843)
-- Dependencies: 5
-- Name: destinazione; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE destinazione (
    id bigint NOT NULL,
    ragione_sociale character varying,
    piva character(1),
    cofice_fiscale character(1),
    indirizzo character varying,
    cap character(5),
    localita character(1),
    provincia character(2),
    paese bigint,
    telefono character(1),
    fax character(1),
    email character(1),
    cellulare character(1),
    note character varying
);


ALTER TABLE public.destinazione OWNER TO postgres;

--
-- TOC entry 1352 (class 1259 OID 17101)
-- Dependencies: 1734 1735 5
-- Name: dettaglio_scarico_manuale; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dettaglio_scarico_manuale (
    id bigint NOT NULL,
    descrizione character varying NOT NULL,
    qta double precision DEFAULT 0 NOT NULL,
    sconto bigint DEFAULT 0,
    prezzo_vendita double precision,
    iva integer,
    ordine bigint NOT NULL
);


ALTER TABLE public.dettaglio_scarico_manuale OWNER TO postgres;

--
-- TOC entry 1353 (class 1259 OID 17110)
-- Dependencies: 5
-- Name: documento_cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE documento_cliente (
    id bigint NOT NULL,
    descrizione character varying NOT NULL,
    abbreviazione character varying(10)
);


ALTER TABLE public.documento_cliente OWNER TO postgres;

--
-- TOC entry 1354 (class 1259 OID 17115)
-- Dependencies: 5
-- Name: ente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ente (
    idente bigint NOT NULL,
    descrizione character varying NOT NULL
);


ALTER TABLE public.ente OWNER TO postgres;

--
-- TOC entry 1355 (class 1259 OID 17120)
-- Dependencies: 1460 5
-- Name: qta_caricate_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW qta_caricate_view AS
    SELECT a.idarticolo, a.codbarre, sum(d.qta) AS sum FROM articolo a, carico c, dettaglio_carico d WHERE (((d.idcarico = c.idcarico) AND (a.idarticolo = d.idarticolo)) AND (c.iddocumento <> 0)) GROUP BY a.idarticolo, a.codbarre;


ALTER TABLE public.qta_caricate_view OWNER TO postgres;

--
-- TOC entry 1356 (class 1259 OID 17123)
-- Dependencies: 1461 5
-- Name: qta_scaricate_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW qta_scaricate_view AS
    SELECT a.idarticolo, a.codbarre, sum(d.qta) AS sum FROM articolo a, scarico c, dettaglio_scarico d WHERE ((d.idordine = c.idordine) AND (a.idarticolo = d.idarticolo)) GROUP BY a.idarticolo, a.codbarre;


ALTER TABLE public.qta_scaricate_view OWNER TO postgres;

--
-- TOC entry 1357 (class 1259 OID 17126)
-- Dependencies: 1462 5
-- Name: giacenza_articoli_all_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW giacenza_articoli_all_view AS
    SELECT c.idarticolo, a.codbarre AS codice, a.descrizione, um.nome AS um, c.sum AS carico, o.sum AS scarico, (c.sum - o.sum) AS deposito, a.prezzo_acquisto, (a.prezzo_acquisto * (c.sum - o.sum)) AS prezzo_tot FROM ((articolo a JOIN (qta_caricate_view c LEFT JOIN qta_scaricate_view o ON ((c.idarticolo = o.idarticolo))) ON ((a.idarticolo = c.idarticolo))) JOIN um ON ((a.um = um.idum)));


ALTER TABLE public.giacenza_articoli_all_view OWNER TO postgres;

--
-- TOC entry 1358 (class 1259 OID 17130)
-- Dependencies: 1463 5
-- Name: giacenza_articoli_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW giacenza_articoli_view AS
    SELECT c.idarticolo, a.codbarre AS codice, a.descrizione, um.nome AS um, c.sum AS carico, o.sum AS scarico, (c.sum - o.sum) AS deposito, a.prezzo_acquisto, (a.prezzo_acquisto * (c.sum - o.sum)) AS prezzo_tot FROM ((articolo a JOIN (qta_caricate_view c LEFT JOIN qta_scaricate_view o ON ((c.idarticolo = o.idarticolo))) ON ((a.idarticolo = c.idarticolo))) JOIN um ON ((a.um = um.idum))) WHERE ((c.sum - o.sum) > ((0)::numeric)::double precision);


ALTER TABLE public.giacenza_articoli_view OWNER TO postgres;

--
-- TOC entry 1359 (class 1259 OID 17134)
-- Dependencies: 5
-- Name: immagine_articolo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE immagine_articolo (
    id bigint NOT NULL,
    nome character varying NOT NULL,
    estensione character(10),
    file bytea,
    articolo bigint
);


ALTER TABLE public.immagine_articolo OWNER TO postgres;

--
-- TOC entry 1360 (class 1259 OID 17144)
-- Dependencies: 5
-- Name: nazionalita; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE nazionalita (
    idnazionalita bigint NOT NULL,
    descrizione character varying NOT NULL
);


ALTER TABLE public.nazionalita OWNER TO postgres;

--
-- TOC entry 1381 (class 1259 OID 31839)
-- Dependencies: 5
-- Name: paese; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE paese (
    id bigint NOT NULL,
    descrizione character(1)
);


ALTER TABLE public.paese OWNER TO postgres;

--
-- TOC entry 1361 (class 1259 OID 17149)
-- Dependencies: 5
-- Name: pagamento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pagamento (
    idpagamento bigint NOT NULL,
    nome character varying(40) NOT NULL
);


ALTER TABLE public.pagamento OWNER TO postgres;

--
-- TOC entry 1362 (class 1259 OID 17151)
-- Dependencies: 5
-- Name: pannelli; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pannelli (
    idpannelli bigint NOT NULL,
    nome character varying(40)
);


ALTER TABLE public.pannelli OWNER TO postgres;

--
-- TOC entry 1363 (class 1259 OID 17158)
-- Dependencies: 1736 1737 5
-- Name: progressivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE progressivi (
    prog_fattura integer NOT NULL,
    prog_ricevuta integer NOT NULL,
    prog_scontrino integer NOT NULL,
    prog_non_fiscale integer DEFAULT 0 NOT NULL,
    anno integer DEFAULT 2008 NOT NULL
);


ALTER TABLE public.progressivi OWNER TO postgres;

--
-- TOC entry 1364 (class 1259 OID 17162)
-- Dependencies: 5
-- Name: provincia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE provincia (
    idprovincia bigint NOT NULL,
    provincia character varying NOT NULL,
    targa character(2)
);


ALTER TABLE public.provincia OWNER TO postgres;

--
-- TOC entry 1365 (class 1259 OID 17167)
-- Dependencies: 5
-- Name: reparto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reparto (
    idreparto bigint NOT NULL,
    data_creazione date,
    nome character varying(40),
    descrizione character varying(100),
    categoria bigint
);


ALTER TABLE public.reparto OWNER TO postgres;

--
-- TOC entry 1366 (class 1259 OID 17169)
-- Dependencies: 5
-- Name: tipo_documento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo_documento (
    iddocumento bigint NOT NULL,
    tipo character varying(40) NOT NULL,
    descrizione character varying(200)
);


ALTER TABLE public.tipo_documento OWNER TO postgres;

--
-- TOC entry 1367 (class 1259 OID 17171)
-- Dependencies: 5
-- Name: tmp_etichette; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_etichette (
    id bigint NOT NULL,
    codice character varying,
    descrizione character varying,
    prezzo double precision,
    note character varying
);


ALTER TABLE public.tmp_etichette OWNER TO postgres;

--
-- TOC entry 1368 (class 1259 OID 17176)
-- Dependencies: 5
-- Name: tmp_etichette_fornitori; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_etichette_fornitori (
    idfornitore bigint NOT NULL,
    nome character varying,
    via character varying,
    cap character varying,
    citta character varying,
    provincia character varying
);


ALTER TABLE public.tmp_etichette_fornitori OWNER TO postgres;

--
-- TOC entry 1369 (class 1259 OID 17181)
-- Dependencies: 5
-- Name: tmp_scelti; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_scelti (
    descrizione character varying,
    qta integer,
    prezzo double precision,
    totale double precision
);


ALTER TABLE public.tmp_scelti OWNER TO postgres;

SET search_path = doc, pg_catalog;

--
-- TOC entry 1820 (class 2606 OID 31754)
-- Dependencies: 1375 1375
-- Name: pk_acconto; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY acconto
    ADD CONSTRAINT pk_acconto PRIMARY KEY (id);


--
-- TOC entry 1828 (class 2606 OID 31831)
-- Dependencies: 1379 1379
-- Name: pk_consegna; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY consegna
    ADD CONSTRAINT pk_consegna PRIMARY KEY (id);


--
-- TOC entry 1826 (class 2606 OID 31824)
-- Dependencies: 1378 1378
-- Name: pk_ddt; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ddt
    ADD CONSTRAINT pk_ddt PRIMARY KEY (id);


--
-- TOC entry 1816 (class 2606 OID 31790)
-- Dependencies: 1373 1373
-- Name: pk_dettaglio_documento; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dettaglio_documento
    ADD CONSTRAINT pk_dettaglio_documento PRIMARY KEY (id);


--
-- TOC entry 1810 (class 2606 OID 31653)
-- Dependencies: 1370 1370
-- Name: pk_documento; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT pk_documento PRIMARY KEY (id);


--
-- TOC entry 1822 (class 2606 OID 31766)
-- Dependencies: 1376 1376
-- Name: pk_fattura; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fattura
    ADD CONSTRAINT pk_fattura PRIMARY KEY (id);


--
-- TOC entry 1836 (class 2606 OID 34513)
-- Dependencies: 1383 1383
-- Name: pk_id_bolla; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bolla
    ADD CONSTRAINT pk_id_bolla PRIMARY KEY (id);


--
-- TOC entry 1818 (class 2606 OID 31719)
-- Dependencies: 1374 1374
-- Name: pk_iva; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY iva
    ADD CONSTRAINT pk_iva PRIMARY KEY (id);


--
-- TOC entry 1812 (class 2606 OID 31668)
-- Dependencies: 1371 1371
-- Name: pk_ordine; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ordine
    ADD CONSTRAINT pk_ordine PRIMARY KEY (id);


--
-- TOC entry 1830 (class 2606 OID 31838)
-- Dependencies: 1380 1380
-- Name: pk_porto; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY porto
    ADD CONSTRAINT pk_porto PRIMARY KEY (id);


--
-- TOC entry 1824 (class 2606 OID 31807)
-- Dependencies: 1377 1377
-- Name: pk_ricevuta; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ricevuta
    ADD CONSTRAINT pk_ricevuta PRIMARY KEY (id);


--
-- TOC entry 1814 (class 2606 OID 31680)
-- Dependencies: 1372 1372
-- Name: pk_stato; Type: CONSTRAINT; Schema: doc; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY stato
    ADD CONSTRAINT pk_stato PRIMARY KEY (id);


SET search_path = public, pg_catalog;

--
-- TOC entry 1745 (class 2606 OID 17198)
-- Dependencies: 1339 1339
-- Name: articoli_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT articoli_pkey PRIMARY KEY (idarticolo);


--
-- TOC entry 1775 (class 2606 OID 17200)
-- Dependencies: 1349 1349
-- Name: aspetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aspetto
    ADD CONSTRAINT aspetto_pkey PRIMARY KEY (idaspetto);


--
-- TOC entry 1750 (class 2606 OID 17202)
-- Dependencies: 1340 1340
-- Name: carichi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY carico
    ADD CONSTRAINT carichi_pkey PRIMARY KEY (idcarico);


--
-- TOC entry 1780 (class 2606 OID 17204)
-- Dependencies: 1351 1351
-- Name: causale_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY causale
    ADD CONSTRAINT causale_pkey PRIMARY KEY (idcausale);


--
-- TOC entry 1764 (class 2606 OID 17206)
-- Dependencies: 1345 1345
-- Name: clienti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT clienti_pkey PRIMARY KEY (idcliente);


--
-- TOC entry 1754 (class 2606 OID 17210)
-- Dependencies: 1341 1341 1341
-- Name: dettaglio_carichi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dettaglio_carico
    ADD CONSTRAINT dettaglio_carichi_pkey PRIMARY KEY (idarticolo, idcarico);


--
-- TOC entry 1767 (class 2606 OID 17212)
-- Dependencies: 1346 1346 1346
-- Name: dettaglio_ordini_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dettaglio_scarico
    ADD CONSTRAINT dettaglio_ordini_pkey PRIMARY KEY (idordine, idarticolo);


--
-- TOC entry 1758 (class 2606 OID 17216)
-- Dependencies: 1342 1342
-- Name: fornitori_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fornitore
    ADD CONSTRAINT fornitori_pkey PRIMARY KEY (idfornitore);


--
-- TOC entry 1778 (class 2606 OID 17218)
-- Dependencies: 1350 1350
-- Name: id_primary_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT id_primary_key PRIMARY KEY (id);


--
-- TOC entry 1789 (class 2606 OID 17220)
-- Dependencies: 1359 1359
-- Name: id_primarykey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY immagine_articolo
    ADD CONSTRAINT id_primarykey PRIMARY KEY (id);


--
-- TOC entry 1808 (class 2606 OID 17228)
-- Dependencies: 1368 1368
-- Name: key_fornitori; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tmp_etichette_fornitori
    ADD CONSTRAINT key_fornitori PRIMARY KEY (idfornitore);


--
-- TOC entry 1773 (class 2606 OID 17230)
-- Dependencies: 1347 1347
-- Name: ordini_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT ordini_pkey PRIMARY KEY (idordine);


--
-- TOC entry 1794 (class 2606 OID 17232)
-- Dependencies: 1361 1361
-- Name: pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pagamento
    ADD CONSTRAINT pagamento_pkey PRIMARY KEY (idpagamento);


--
-- TOC entry 1797 (class 2606 OID 17234)
-- Dependencies: 1362 1362
-- Name: pannelli_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pannelli
    ADD CONSTRAINT pannelli_pkey PRIMARY KEY (idpannelli);


--
-- TOC entry 1799 (class 2606 OID 17236)
-- Dependencies: 1363 1363
-- Name: pk_anno; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY progressivi
    ADD CONSTRAINT pk_anno PRIMARY KEY (anno);


--
-- TOC entry 1834 (class 2606 OID 31849)
-- Dependencies: 1382 1382
-- Name: pk_destinazione; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY destinazione
    ADD CONSTRAINT pk_destinazione PRIMARY KEY (id);


--
-- TOC entry 1783 (class 2606 OID 17238)
-- Dependencies: 1352 1352 1352
-- Name: pk_dettaglio_ordine_manuale; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dettaglio_scarico_manuale
    ADD CONSTRAINT pk_dettaglio_ordine_manuale PRIMARY KEY (id, ordine);


--
-- TOC entry 1806 (class 2606 OID 17240)
-- Dependencies: 1366 1366
-- Name: pk_documento; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_documento
    ADD CONSTRAINT pk_documento PRIMARY KEY (iddocumento);


--
-- TOC entry 1785 (class 2606 OID 17242)
-- Dependencies: 1353 1353
-- Name: pk_documento_cliente; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY documento_cliente
    ADD CONSTRAINT pk_documento_cliente PRIMARY KEY (id);


--
-- TOC entry 1787 (class 2606 OID 17244)
-- Dependencies: 1354 1354
-- Name: pk_ente; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ente
    ADD CONSTRAINT pk_ente PRIMARY KEY (idente);


--
-- TOC entry 1801 (class 2606 OID 17246)
-- Dependencies: 1364 1364
-- Name: pk_idprovincia; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY provincia
    ADD CONSTRAINT pk_idprovincia PRIMARY KEY (idprovincia);


--
-- TOC entry 1791 (class 2606 OID 17248)
-- Dependencies: 1360 1360
-- Name: pk_nazionalita; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nazionalita
    ADD CONSTRAINT pk_nazionalita PRIMARY KEY (idnazionalita);


--
-- TOC entry 1832 (class 2606 OID 31842)
-- Dependencies: 1381 1381
-- Name: pk_paese; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY paese
    ADD CONSTRAINT pk_paese PRIMARY KEY (id);


--
-- TOC entry 1804 (class 2606 OID 17254)
-- Dependencies: 1365 1365
-- Name: reparti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reparto
    ADD CONSTRAINT reparti_pkey PRIMARY KEY (idreparto);


--
-- TOC entry 1762 (class 2606 OID 17256)
-- Dependencies: 1343 1343
-- Name: um_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY um
    ADD CONSTRAINT um_pkey PRIMARY KEY (idum);


--
-- TOC entry 1746 (class 1259 OID 17257)
-- Dependencies: 1339
-- Name: idx_articoli1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_articoli1 ON articolo USING btree (idreparto);


--
-- TOC entry 1747 (class 1259 OID 17258)
-- Dependencies: 1339
-- Name: idx_articoli2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_articoli2 ON articolo USING btree (um);


--
-- TOC entry 1748 (class 1259 OID 17260)
-- Dependencies: 1339
-- Name: idx_articoli4; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_articoli4 ON articolo USING btree (idarticolo);


--
-- TOC entry 1776 (class 1259 OID 17261)
-- Dependencies: 1349
-- Name: idx_aspetto1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_aspetto1 ON aspetto USING btree (idaspetto);


--
-- TOC entry 1751 (class 1259 OID 17262)
-- Dependencies: 1340
-- Name: idx_carichi1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_carichi1 ON carico USING btree (idcarico);


--
-- TOC entry 1752 (class 1259 OID 17263)
-- Dependencies: 1340
-- Name: idx_carichi2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_carichi2 ON carico USING btree (idfornitore);


--
-- TOC entry 1781 (class 1259 OID 17264)
-- Dependencies: 1351
-- Name: idx_causale1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_causale1 ON causale USING btree (idcausale);


--
-- TOC entry 1765 (class 1259 OID 17265)
-- Dependencies: 1345
-- Name: idx_clienti1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_clienti1 ON cliente USING btree (idcliente);


--
-- TOC entry 1755 (class 1259 OID 17266)
-- Dependencies: 1341
-- Name: idx_dettaglio_carichi1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dettaglio_carichi1 ON dettaglio_carico USING btree (idcarico);


--
-- TOC entry 1756 (class 1259 OID 17267)
-- Dependencies: 1341
-- Name: idx_dettaglio_carichi2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dettaglio_carichi2 ON dettaglio_carico USING btree (idarticolo);


--
-- TOC entry 1768 (class 1259 OID 17268)
-- Dependencies: 1346
-- Name: idx_dettaglio_ordini1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dettaglio_ordini1 ON dettaglio_scarico USING btree (idordine);


--
-- TOC entry 1769 (class 1259 OID 17269)
-- Dependencies: 1346
-- Name: idx_dettaglio_ordini2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dettaglio_ordini2 ON dettaglio_scarico USING btree (idarticolo);


--
-- TOC entry 1759 (class 1259 OID 17270)
-- Dependencies: 1342
-- Name: idx_fornitori1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_fornitori1 ON fornitore USING btree (idfornitore);


--
-- TOC entry 1770 (class 1259 OID 17271)
-- Dependencies: 1347
-- Name: idx_ordini1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_ordini1 ON scarico USING btree (idcliente);


--
-- TOC entry 1771 (class 1259 OID 17272)
-- Dependencies: 1347
-- Name: idx_ordini2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_ordini2 ON scarico USING btree (idordine);


--
-- TOC entry 1792 (class 1259 OID 17273)
-- Dependencies: 1361
-- Name: idx_pagamento1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_pagamento1 ON pagamento USING btree (idpagamento);


--
-- TOC entry 1795 (class 1259 OID 17274)
-- Dependencies: 1362
-- Name: idx_pannelli1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_pannelli1 ON pannelli USING btree (idpannelli);


--
-- TOC entry 1802 (class 1259 OID 17275)
-- Dependencies: 1365
-- Name: idx_reparti1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_reparti1 ON reparto USING btree (idreparto);


--
-- TOC entry 1760 (class 1259 OID 17276)
-- Dependencies: 1343
-- Name: idx_um1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_um1 ON um USING btree (idum);


SET search_path = doc, pg_catalog;

--
-- TOC entry 1951 (class 2606 OID 34524)
-- Dependencies: 1378 1829 1380
-- Name: fk183f44b2cc309; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ddt
    ADD CONSTRAINT fk183f44b2cc309 FOREIGN KEY (porto) REFERENCES porto(id);


--
-- TOC entry 1952 (class 2606 OID 34529)
-- Dependencies: 1361 1793 1378
-- Name: fk183f46876bab9; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ddt
    ADD CONSTRAINT fk183f46876bab9 FOREIGN KEY (pagamento) REFERENCES public.pagamento(idpagamento);


--
-- TOC entry 1954 (class 2606 OID 34539)
-- Dependencies: 1379 1378 1827
-- Name: fk183f46f9756f9; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ddt
    ADD CONSTRAINT fk183f46f9756f9 FOREIGN KEY (consegna_a_mezzo) REFERENCES consegna(id);


--
-- TOC entry 1953 (class 2606 OID 34534)
-- Dependencies: 1779 1378 1351
-- Name: fk183f4a431c4e9; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ddt
    ADD CONSTRAINT fk183f4a431c4e9 FOREIGN KEY (causale) REFERENCES public.causale(idcausale);


--
-- TOC entry 1928 (class 2606 OID 34554)
-- Dependencies: 1370 1345 1763
-- Name: fk383d52b4c85dd7a1; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT fk383d52b4c85dd7a1 FOREIGN KEY (cliente) REFERENCES public.cliente(idcliente);


--
-- TOC entry 1938 (class 2606 OID 31880)
-- Dependencies: 1821 1376 1375
-- Name: fk_acconto_fattura; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY acconto
    ADD CONSTRAINT fk_acconto_fattura FOREIGN KEY (fattura) REFERENCES fattura(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1956 (class 2606 OID 34514)
-- Dependencies: 1809 1370 1383
-- Name: fk_bolla_documento; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY bolla
    ADD CONSTRAINT fk_bolla_documento FOREIGN KEY (id) REFERENCES documento(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1927 (class 2606 OID 31654)
-- Dependencies: 1763 1370 1345
-- Name: fk_cliente; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT fk_cliente FOREIGN KEY (cliente) REFERENCES public.cliente(idcliente) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1946 (class 2606 OID 31855)
-- Dependencies: 1779 1378 1351
-- Name: fk_ddt_causale; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ddt
    ADD CONSTRAINT fk_ddt_causale FOREIGN KEY (causale) REFERENCES public.causale(idcausale) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1947 (class 2606 OID 31860)
-- Dependencies: 1379 1378 1827
-- Name: fk_ddt_consegna; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ddt
    ADD CONSTRAINT fk_ddt_consegna FOREIGN KEY (consegna_a_mezzo) REFERENCES consegna(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1950 (class 2606 OID 31875)
-- Dependencies: 1833 1378 1382
-- Name: fk_ddt_destinazione; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ddt
    ADD CONSTRAINT fk_ddt_destinazione FOREIGN KEY (destinazione) REFERENCES public.destinazione(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1949 (class 2606 OID 31870)
-- Dependencies: 1378 1793 1361
-- Name: fk_ddt_pagamento; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ddt
    ADD CONSTRAINT fk_ddt_pagamento FOREIGN KEY (pagamento) REFERENCES public.pagamento(idpagamento) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1948 (class 2606 OID 31865)
-- Dependencies: 1380 1829 1378
-- Name: fk_ddt_porto; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ddt
    ADD CONSTRAINT fk_ddt_porto FOREIGN KEY (porto) REFERENCES porto(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1935 (class 2606 OID 31796)
-- Dependencies: 1374 1373 1817
-- Name: fk_dett_doc_iva; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY dettaglio_documento
    ADD CONSTRAINT fk_dett_doc_iva FOREIGN KEY (iva) REFERENCES iva(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1934 (class 2606 OID 31791)
-- Dependencies: 1809 1370 1373
-- Name: fk_dettagliodocumento_documento; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY dettaglio_documento
    ADD CONSTRAINT fk_dettagliodocumento_documento FOREIGN KEY (documento) REFERENCES documento(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1940 (class 2606 OID 31767)
-- Dependencies: 1809 1376 1370
-- Name: fk_fattura_documento; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY fattura
    ADD CONSTRAINT fk_fattura_documento FOREIGN KEY (id) REFERENCES documento(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1941 (class 2606 OID 31772)
-- Dependencies: 1793 1376 1361
-- Name: fk_fattura_pagamento; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY fattura
    ADD CONSTRAINT fk_fattura_pagamento FOREIGN KEY (pagamento) REFERENCES public.pagamento(idpagamento) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1929 (class 2606 OID 31669)
-- Dependencies: 1370 1371 1809
-- Name: fk_ordine_documento; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ordine
    ADD CONSTRAINT fk_ordine_documento FOREIGN KEY (id) REFERENCES documento(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1931 (class 2606 OID 31743)
-- Dependencies: 1361 1371 1793
-- Name: fk_ordine_pagamento; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ordine
    ADD CONSTRAINT fk_ordine_pagamento FOREIGN KEY (pagamento) REFERENCES public.pagamento(idpagamento) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1943 (class 2606 OID 31808)
-- Dependencies: 1370 1809 1377
-- Name: fk_ricevuta_documento; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ricevuta
    ADD CONSTRAINT fk_ricevuta_documento FOREIGN KEY (id) REFERENCES documento(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1944 (class 2606 OID 31813)
-- Dependencies: 1377 1361 1793
-- Name: fk_ricevuta_pagamento; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ricevuta
    ADD CONSTRAINT fk_ricevuta_pagamento FOREIGN KEY (pagamento) REFERENCES public.pagamento(idpagamento) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1930 (class 2606 OID 31688)
-- Dependencies: 1371 1813 1372
-- Name: fk_stato; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ordine
    ADD CONSTRAINT fk_stato FOREIGN KEY (stato) REFERENCES stato(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1945 (class 2606 OID 34574)
-- Dependencies: 1361 1793 1377
-- Name: fka36abdc56876bab9; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ricevuta
    ADD CONSTRAINT fka36abdc56876bab9 FOREIGN KEY (pagamento) REFERENCES public.pagamento(idpagamento);


--
-- TOC entry 1939 (class 2606 OID 34519)
-- Dependencies: 1375 1376 1821
-- Name: fkb9d3709be17c79bf; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY acconto
    ADD CONSTRAINT fkb9d3709be17c79bf FOREIGN KEY (fattura) REFERENCES fattura(id);


--
-- TOC entry 1942 (class 2606 OID 34559)
-- Dependencies: 1361 1376 1793
-- Name: fkbfdad7096876bab9; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY fattura
    ADD CONSTRAINT fkbfdad7096876bab9 FOREIGN KEY (pagamento) REFERENCES public.pagamento(idpagamento);


--
-- TOC entry 1932 (class 2606 OID 34564)
-- Dependencies: 1813 1371 1372
-- Name: fkc3df715f4b855c23; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ordine
    ADD CONSTRAINT fkc3df715f4b855c23 FOREIGN KEY (stato) REFERENCES stato(id);


--
-- TOC entry 1933 (class 2606 OID 34569)
-- Dependencies: 1793 1371 1361
-- Name: fkc3df715f6876bab9; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY ordine
    ADD CONSTRAINT fkc3df715f6876bab9 FOREIGN KEY (pagamento) REFERENCES public.pagamento(idpagamento);


--
-- TOC entry 1937 (class 2606 OID 34549)
-- Dependencies: 1370 1809 1373
-- Name: fkc9584a007bbd39d5; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY dettaglio_documento
    ADD CONSTRAINT fkc9584a007bbd39d5 FOREIGN KEY (documento) REFERENCES documento(id);


--
-- TOC entry 1936 (class 2606 OID 34544)
-- Dependencies: 1374 1817 1373
-- Name: fkc9584a008434dbd5; Type: FK CONSTRAINT; Schema: doc; Owner: postgres
--

ALTER TABLE ONLY dettaglio_documento
    ADD CONSTRAINT fkc9584a008434dbd5 FOREIGN KEY (iva) REFERENCES iva(id);


SET search_path = public, pg_catalog;

--
-- TOC entry 1837 (class 2606 OID 17283)
-- Dependencies: 1803 1365 1339
-- Name: articoli_idreparto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT articoli_idreparto_fkey FOREIGN KEY (idreparto) REFERENCES reparto(idreparto);


--
-- TOC entry 1838 (class 2606 OID 17288)
-- Dependencies: 1343 1761 1339
-- Name: articoli_um_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT articoli_um_fkey FOREIGN KEY (um) REFERENCES um(idum);


--
-- TOC entry 1839 (class 2606 OID 17293)
-- Dependencies: 1339 1796 1362
-- Name: articolo_idpannello; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT articolo_idpannello FOREIGN KEY (idpannello) REFERENCES pannelli(idpannelli);


--
-- TOC entry 1848 (class 2606 OID 17298)
-- Dependencies: 1340 1757 1342
-- Name: carichi_idfornitore_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carico
    ADD CONSTRAINT carichi_idfornitore_fkey FOREIGN KEY (idfornitore) REFERENCES fornitore(idfornitore);


--
-- TOC entry 1849 (class 2606 OID 17303)
-- Dependencies: 1340 1366 1805
-- Name: carico_documento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carico
    ADD CONSTRAINT carico_documento FOREIGN KEY (iddocumento) REFERENCES tipo_documento(iddocumento);


--
-- TOC entry 1856 (class 2606 OID 17308)
-- Dependencies: 1744 1339 1341
-- Name: dettaglio_carichi_idarticolo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_carico
    ADD CONSTRAINT dettaglio_carichi_idarticolo_fkey FOREIGN KEY (idarticolo) REFERENCES articolo(idarticolo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1857 (class 2606 OID 17313)
-- Dependencies: 1341 1340 1749
-- Name: dettaglio_carichi_idcarico_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_carico
    ADD CONSTRAINT dettaglio_carichi_idcarico_fkey FOREIGN KEY (idcarico) REFERENCES carico(idcarico) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1884 (class 2606 OID 17318)
-- Dependencies: 1339 1346 1744
-- Name: dettaglio_ordini_idarticolo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico
    ADD CONSTRAINT dettaglio_ordini_idarticolo_fkey FOREIGN KEY (idarticolo) REFERENCES articolo(idarticolo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1885 (class 2606 OID 17323)
-- Dependencies: 1772 1346 1347
-- Name: dettaglio_ordini_idordine_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico
    ADD CONSTRAINT dettaglio_ordini_idordine_fkey FOREIGN KEY (idordine) REFERENCES scarico(idordine) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1886 (class 2606 OID 17328)
-- Dependencies: 1346 1339 1744
-- Name: fk1dbc9d9735a4475c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico
    ADD CONSTRAINT fk1dbc9d9735a4475c FOREIGN KEY (idarticolo) REFERENCES articolo(idarticolo);


--
-- TOC entry 1887 (class 2606 OID 17333)
-- Dependencies: 1346 1347 1772
-- Name: fk1dbc9d975c93fa50; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico
    ADD CONSTRAINT fk1dbc9d975c93fa50 FOREIGN KEY (idordine) REFERENCES scarico(idordine);


--
-- TOC entry 1888 (class 2606 OID 17338)
-- Dependencies: 1772 1347 1346
-- Name: fk1dbc9d975f4bb7a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico
    ADD CONSTRAINT fk1dbc9d975f4bb7a FOREIGN KEY (idordine) REFERENCES scarico(idordine);


--
-- TOC entry 1889 (class 2606 OID 17343)
-- Dependencies: 1744 1339 1346
-- Name: fk1dbc9d97616f28b2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico
    ADD CONSTRAINT fk1dbc9d97616f28b2 FOREIGN KEY (idarticolo) REFERENCES articolo(idarticolo);


--
-- TOC entry 1850 (class 2606 OID 17348)
-- Dependencies: 1340 1342 1757
-- Name: fk2106b16f186f34b0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carico
    ADD CONSTRAINT fk2106b16f186f34b0 FOREIGN KEY (idfornitore) REFERENCES fornitore(idfornitore);


--
-- TOC entry 1851 (class 2606 OID 17353)
-- Dependencies: 1366 1340 1805
-- Name: fk2106b16f4fca5f5c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carico
    ADD CONSTRAINT fk2106b16f4fca5f5c FOREIGN KEY (iddocumento) REFERENCES tipo_documento(iddocumento);


--
-- TOC entry 1852 (class 2606 OID 17358)
-- Dependencies: 1342 1340 1757
-- Name: fk2106b16f66007e1a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carico
    ADD CONSTRAINT fk2106b16f66007e1a FOREIGN KEY (idfornitore) REFERENCES fornitore(idfornitore);


--
-- TOC entry 1853 (class 2606 OID 17363)
-- Dependencies: 1805 1366 1340
-- Name: fk2106b16f7764e3c6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carico
    ADD CONSTRAINT fk2106b16f7764e3c6 FOREIGN KEY (iddocumento) REFERENCES tipo_documento(iddocumento);


--
-- TOC entry 1858 (class 2606 OID 17368)
-- Dependencies: 1339 1341 1744
-- Name: fk2cf0bbb35a4475c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_carico
    ADD CONSTRAINT fk2cf0bbb35a4475c FOREIGN KEY (idarticolo) REFERENCES articolo(idarticolo);


--
-- TOC entry 1859 (class 2606 OID 17373)
-- Dependencies: 1341 1744 1339
-- Name: fk2cf0bbb616f28b2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_carico
    ADD CONSTRAINT fk2cf0bbb616f28b2 FOREIGN KEY (idarticolo) REFERENCES articolo(idarticolo);


--
-- TOC entry 1860 (class 2606 OID 17378)
-- Dependencies: 1341 1749 1340
-- Name: fk2cf0bbb76942038; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_carico
    ADD CONSTRAINT fk2cf0bbb76942038 FOREIGN KEY (idcarico) REFERENCES carico(idcarico);


--
-- TOC entry 1861 (class 2606 OID 17383)
-- Dependencies: 1749 1341 1340
-- Name: fk2cf0bbbf94b844e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_carico
    ADD CONSTRAINT fk2cf0bbbf94b844e FOREIGN KEY (idcarico) REFERENCES carico(idcarico);


--
-- TOC entry 1920 (class 2606 OID 17388)
-- Dependencies: 1339 1359 1744
-- Name: fk2e361b0318c98541; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY immagine_articolo
    ADD CONSTRAINT fk2e361b0318c98541 FOREIGN KEY (articolo) REFERENCES articolo(idarticolo);


--
-- TOC entry 1921 (class 2606 OID 17393)
-- Dependencies: 1339 1359 1744
-- Name: fk2e361b0344946697; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY immagine_articolo
    ADD CONSTRAINT fk2e361b0344946697 FOREIGN KEY (articolo) REFERENCES articolo(idarticolo);


--
-- TOC entry 1923 (class 2606 OID 31597)
-- Dependencies: 1339 1359 1744
-- Name: fk2e361b034494669d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY immagine_articolo
    ADD CONSTRAINT fk2e361b034494669d FOREIGN KEY (articolo) REFERENCES articolo(idarticolo);


--
-- TOC entry 1883 (class 2606 OID 31562)
-- Dependencies: 1784 1353 1345
-- Name: fk334b85fa1e6724d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85fa1e6724d FOREIGN KEY (documento) REFERENCES documento_cliente(id);


--
-- TOC entry 1880 (class 2606 OID 31547)
-- Dependencies: 1360 1790 1345
-- Name: fk334b85fa4335dc81; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85fa4335dc81 FOREIGN KEY (nazionalita) REFERENCES nazionalita(idnazionalita);


--
-- TOC entry 1881 (class 2606 OID 31552)
-- Dependencies: 1345 1364 1800
-- Name: fk334b85faca050bc7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85faca050bc7 FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);


--
-- TOC entry 1882 (class 2606 OID 31557)
-- Dependencies: 1786 1345 1354
-- Name: fk334b85fae615563a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85fae615563a FOREIGN KEY (rilasciato_da) REFERENCES ente(idente);


--
-- TOC entry 1868 (class 2606 OID 17398)
-- Dependencies: 1360 1790 1345
-- Name: fk334b85fe14d14597; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85fe14d14597 FOREIGN KEY (nazionalita) REFERENCES nazionalita(idnazionalita);


--
-- TOC entry 1869 (class 2606 OID 17403)
-- Dependencies: 1353 1345 1784
-- Name: fk334b85fe1e6724d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85fe1e6724d FOREIGN KEY (documento) REFERENCES documento_cliente(id);


--
-- TOC entry 1870 (class 2606 OID 17408)
-- Dependencies: 1790 1360 1345
-- Name: fk334b85fe4335dc81; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85fe4335dc81 FOREIGN KEY (nazionalita) REFERENCES nazionalita(idnazionalita);


--
-- TOC entry 1871 (class 2606 OID 17413)
-- Dependencies: 1345 1784 1353
-- Name: fk334b85fe4b8f46f7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85fe4b8f46f7 FOREIGN KEY (documento) REFERENCES documento_cliente(id);


--
-- TOC entry 1872 (class 2606 OID 17418)
-- Dependencies: 1364 1345 1800
-- Name: fk334b85fe7c73c25d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85fe7c73c25d FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);


--
-- TOC entry 1873 (class 2606 OID 17423)
-- Dependencies: 1345 1786 1354
-- Name: fk334b85fec82859e4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85fec82859e4 FOREIGN KEY (rilasciato_da) REFERENCES ente(idente);


--
-- TOC entry 1874 (class 2606 OID 17428)
-- Dependencies: 1364 1800 1345
-- Name: fk334b85feca050bc7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85feca050bc7 FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);


--
-- TOC entry 1875 (class 2606 OID 17433)
-- Dependencies: 1345 1786 1354
-- Name: fk334b85fee615563a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk334b85fee615563a FOREIGN KEY (rilasciato_da) REFERENCES ente(idente);


--
-- TOC entry 1924 (class 2606 OID 17438)
-- Dependencies: 1777 1365 1350
-- Name: fk4137f483c5ec56d3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reparto
    ADD CONSTRAINT fk4137f483c5ec56d3 FOREIGN KEY (categoria) REFERENCES categoria(id);


--
-- TOC entry 1926 (class 2606 OID 31602)
-- Dependencies: 1350 1365 1777
-- Name: fk4137f489c5ec56d3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reparto
    ADD CONSTRAINT fk4137f489c5ec56d3 FOREIGN KEY (categoria) REFERENCES categoria(id);


--
-- TOC entry 1890 (class 2606 OID 31577)
-- Dependencies: 1744 1339 1346
-- Name: fk53b10ec0616f28b8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico
    ADD CONSTRAINT fk53b10ec0616f28b8 FOREIGN KEY (idarticolo) REFERENCES articolo(idarticolo);


--
-- TOC entry 1891 (class 2606 OID 31582)
-- Dependencies: 1772 1347 1346
-- Name: fk53b10ec0dcd97e5b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico
    ADD CONSTRAINT fk53b10ec0dcd97e5b FOREIGN KEY (idordine) REFERENCES scarico(idordine);


--
-- TOC entry 1867 (class 2606 OID 31592)
-- Dependencies: 1800 1364 1342
-- Name: fk6e203432ca050bc7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fornitore
    ADD CONSTRAINT fk6e203432ca050bc7 FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);


--
-- TOC entry 1864 (class 2606 OID 17453)
-- Dependencies: 1364 1342 1800
-- Name: fk6e2034367c73c25d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fornitore
    ADD CONSTRAINT fk6e2034367c73c25d FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);


--
-- TOC entry 1865 (class 2606 OID 17458)
-- Dependencies: 1342 1800 1364
-- Name: fk6e203436ca050bc7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fornitore
    ADD CONSTRAINT fk6e203436ca050bc7 FOREIGN KEY (provincia) REFERENCES provincia(idprovincia);


--
-- TOC entry 1913 (class 2606 OID 31622)
-- Dependencies: 1347 1763 1345
-- Name: fk71e8b4742c64e626; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fk71e8b4742c64e626 FOREIGN KEY (idcliente) REFERENCES cliente(idcliente);


--
-- TOC entry 1914 (class 2606 OID 31627)
-- Dependencies: 1361 1347 1793
-- Name: fk71e8b4746876bab9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fk71e8b4746876bab9 FOREIGN KEY (pagamento) REFERENCES pagamento(idpagamento);


--
-- TOC entry 1910 (class 2606 OID 31607)
-- Dependencies: 1774 1349 1347
-- Name: fk71e8b47471754802; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fk71e8b47471754802 FOREIGN KEY (idaspetto) REFERENCES aspetto(idaspetto);


--
-- TOC entry 1911 (class 2606 OID 31612)
-- Dependencies: 1779 1347 1351
-- Name: fk71e8b474838d36e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fk71e8b474838d36e FOREIGN KEY (idcausale) REFERENCES causale(idcausale);


--
-- TOC entry 1912 (class 2606 OID 31617)
-- Dependencies: 1366 1805 1347
-- Name: fk71e8b474b9473f36; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fk71e8b474b9473f36 FOREIGN KEY (tipo_documento) REFERENCES tipo_documento(iddocumento);


--
-- TOC entry 1915 (class 2606 OID 31632)
-- Dependencies: 1347 1361 1793
-- Name: fk71e8b474e6f43bfe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fk71e8b474e6f43bfe FOREIGN KEY (idpagamento) REFERENCES pagamento(idpagamento);


--
-- TOC entry 1916 (class 2606 OID 17473)
-- Dependencies: 1347 1352 1772
-- Name: fk7b7d12b317496d75; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico_manuale
    ADD CONSTRAINT fk7b7d12b317496d75 FOREIGN KEY (ordine) REFERENCES scarico(idordine);


--
-- TOC entry 1917 (class 2606 OID 17478)
-- Dependencies: 1352 1772 1347
-- Name: fk7b7d12b3c0aa2e9f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico_manuale
    ADD CONSTRAINT fk7b7d12b3c0aa2e9f FOREIGN KEY (ordine) REFERENCES scarico(idordine);


--
-- TOC entry 1863 (class 2606 OID 31572)
-- Dependencies: 1341 1749 1340
-- Name: fk859427531cd4410; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_carico
    ADD CONSTRAINT fk859427531cd4410 FOREIGN KEY (idcarico) REFERENCES carico(idcarico);


--
-- TOC entry 1862 (class 2606 OID 31567)
-- Dependencies: 1341 1744 1339
-- Name: fk8594275616f28b8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_carico
    ADD CONSTRAINT fk8594275616f28b8 FOREIGN KEY (idarticolo) REFERENCES articolo(idarticolo);


--
-- TOC entry 1922 (class 2606 OID 17483)
-- Dependencies: 1359 1744 1339
-- Name: fk_articolo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY immagine_articolo
    ADD CONSTRAINT fk_articolo FOREIGN KEY (articolo) REFERENCES articolo(idarticolo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1925 (class 2606 OID 17488)
-- Dependencies: 1365 1777 1350
-- Name: fk_categoria; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reparto
    ADD CONSTRAINT fk_categoria FOREIGN KEY (categoria) REFERENCES categoria(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1955 (class 2606 OID 31850)
-- Dependencies: 1831 1381 1382
-- Name: fk_destinazione_paese; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY destinazione
    ADD CONSTRAINT fk_destinazione_paese FOREIGN KEY (paese) REFERENCES paese(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1918 (class 2606 OID 17493)
-- Dependencies: 1352 1772 1347
-- Name: fk_dettaglio_ordine_manuale2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico_manuale
    ADD CONSTRAINT fk_dettaglio_ordine_manuale2 FOREIGN KEY (ordine) REFERENCES scarico(idordine);


--
-- TOC entry 1876 (class 2606 OID 17498)
-- Dependencies: 1345 1784 1353
-- Name: fk_documento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_documento FOREIGN KEY (documento) REFERENCES documento_cliente(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1892 (class 2606 OID 17503)
-- Dependencies: 1774 1347 1349
-- Name: fk_idaspetto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fk_idaspetto FOREIGN KEY (idaspetto) REFERENCES aspetto(idaspetto) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1893 (class 2606 OID 17508)
-- Dependencies: 1347 1351 1779
-- Name: fk_idcausale; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fk_idcausale FOREIGN KEY (idcausale) REFERENCES causale(idcausale) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1894 (class 2606 OID 17513)
-- Dependencies: 1793 1347 1361
-- Name: fk_idpagamento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fk_idpagamento FOREIGN KEY (idpagamento) REFERENCES pagamento(idpagamento) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1877 (class 2606 OID 17518)
-- Dependencies: 1345 1790 1360
-- Name: fk_nazionalita; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_nazionalita FOREIGN KEY (nazionalita) REFERENCES nazionalita(idnazionalita);


--
-- TOC entry 1895 (class 2606 OID 17523)
-- Dependencies: 1347 1793 1361
-- Name: fk_pagamento2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fk_pagamento2 FOREIGN KEY (pagamento) REFERENCES pagamento(idpagamento);


--
-- TOC entry 1878 (class 2606 OID 17528)
-- Dependencies: 1364 1345 1800
-- Name: fk_provincia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_provincia FOREIGN KEY (provincia) REFERENCES provincia(idprovincia) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1879 (class 2606 OID 17533)
-- Dependencies: 1786 1345 1354
-- Name: fk_rilasciatoda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_rilasciatoda FOREIGN KEY (rilasciato_da) REFERENCES ente(idente) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1854 (class 2606 OID 31537)
-- Dependencies: 1340 1342 1757
-- Name: fkae7c164166007e16; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carico
    ADD CONSTRAINT fkae7c164166007e16 FOREIGN KEY (idfornitore) REFERENCES fornitore(idfornitore);


--
-- TOC entry 1855 (class 2606 OID 31542)
-- Dependencies: 1805 1366 1340
-- Name: fkae7c16417764e3c6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carico
    ADD CONSTRAINT fkae7c16417764e3c6 FOREIGN KEY (iddocumento) REFERENCES tipo_documento(iddocumento);


--
-- TOC entry 1840 (class 2606 OID 17543)
-- Dependencies: 1339 1761 1343
-- Name: fkb6c0de4f1d09f963; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT fkb6c0de4f1d09f963 FOREIGN KEY (um) REFERENCES um(idum);


--
-- TOC entry 1841 (class 2606 OID 17548)
-- Dependencies: 1365 1803 1339
-- Name: fkb6c0de4f483dc33e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT fkb6c0de4f483dc33e FOREIGN KEY (idreparto) REFERENCES reparto(idreparto);


--
-- TOC entry 1844 (class 2606 OID 18076)
-- Dependencies: 1339 1362 1796
-- Name: fkb6c0de4f73530abe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT fkb6c0de4f73530abe FOREIGN KEY (idpannello) REFERENCES pannelli(idpannelli);


--
-- TOC entry 1842 (class 2606 OID 17558)
-- Dependencies: 1761 1343 1339
-- Name: fkb6c0de4fc7ddf8d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT fkb6c0de4fc7ddf8d FOREIGN KEY (um) REFERENCES um(idum);


--
-- TOC entry 1843 (class 2606 OID 17563)
-- Dependencies: 1339 1803 1365
-- Name: fkb6c0de4fcaf52754; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT fkb6c0de4fcaf52754 FOREIGN KEY (idreparto) REFERENCES reparto(idreparto);


--
-- TOC entry 1847 (class 2606 OID 31532)
-- Dependencies: 1339 1343 1761
-- Name: fkb6c0de551d09f963; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT fkb6c0de551d09f963 FOREIGN KEY (um) REFERENCES um(idum);


--
-- TOC entry 1845 (class 2606 OID 31522)
-- Dependencies: 1339 1365 1803
-- Name: fkb6c0de55483dc344; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT fkb6c0de55483dc344 FOREIGN KEY (idreparto) REFERENCES reparto(idreparto);


--
-- TOC entry 1846 (class 2606 OID 31527)
-- Dependencies: 1339 1362 1796
-- Name: fkb6c0de5573530abe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articolo
    ADD CONSTRAINT fkb6c0de5573530abe FOREIGN KEY (idpannello) REFERENCES pannelli(idpannelli);


--
-- TOC entry 1919 (class 2606 OID 31587)
-- Dependencies: 1772 1347 1352
-- Name: fkc27806e0978ef180; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_scarico_manuale
    ADD CONSTRAINT fkc27806e0978ef180 FOREIGN KEY (ordine) REFERENCES scarico(idordine);


--
-- TOC entry 1896 (class 2606 OID 17578)
-- Dependencies: 1361 1347 1793
-- Name: fkc3df71631ae5714f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df71631ae5714f FOREIGN KEY (pagamento) REFERENCES pagamento(idpagamento);


--
-- TOC entry 1897 (class 2606 OID 17583)
-- Dependencies: 1345 1347 1763
-- Name: fkc3df71632c64e62a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df71632c64e62a FOREIGN KEY (idcliente) REFERENCES cliente(idcliente);


--
-- TOC entry 1898 (class 2606 OID 17588)
-- Dependencies: 1361 1793 1347
-- Name: fkc3df71636876bab9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df71636876bab9 FOREIGN KEY (pagamento) REFERENCES pagamento(idpagamento);


--
-- TOC entry 1899 (class 2606 OID 17593)
-- Dependencies: 1774 1349 1347
-- Name: fkc3df716371754802; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df716371754802 FOREIGN KEY (idaspetto) REFERENCES aspetto(idaspetto);


--
-- TOC entry 1900 (class 2606 OID 17598)
-- Dependencies: 1351 1347 1779
-- Name: fkc3df7163838d36e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df7163838d36e FOREIGN KEY (idcausale) REFERENCES causale(idcausale);


--
-- TOC entry 1901 (class 2606 OID 17603)
-- Dependencies: 1351 1347 1779
-- Name: fkc3df71638af03784; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df71638af03784 FOREIGN KEY (idcausale) REFERENCES causale(idcausale);


--
-- TOC entry 1902 (class 2606 OID 17608)
-- Dependencies: 1366 1805 1347
-- Name: fkc3df716391acbacc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df716391acbacc FOREIGN KEY (tipo_documento) REFERENCES tipo_documento(iddocumento);


--
-- TOC entry 1903 (class 2606 OID 17613)
-- Dependencies: 1361 1793 1347
-- Name: fkc3df71639962f294; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df71639962f294 FOREIGN KEY (idpagamento) REFERENCES pagamento(idpagamento);


--
-- TOC entry 1904 (class 2606 OID 17618)
-- Dependencies: 1763 1347 1345
-- Name: fkc3df7163af1c4a40; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df7163af1c4a40 FOREIGN KEY (idcliente) REFERENCES cliente(idcliente);


--
-- TOC entry 1905 (class 2606 OID 17623)
-- Dependencies: 1366 1347 1805
-- Name: fkc3df7163b9473f36; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df7163b9473f36 FOREIGN KEY (tipo_documento) REFERENCES tipo_documento(iddocumento);


--
-- TOC entry 1906 (class 2606 OID 17628)
-- Dependencies: 1793 1361 1347
-- Name: fkc3df7163e6f43bfe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df7163e6f43bfe FOREIGN KEY (idpagamento) REFERENCES pagamento(idpagamento);


--
-- TOC entry 1907 (class 2606 OID 17633)
-- Dependencies: 1774 1349 1347
-- Name: fkc3df7163f42cac18; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT fkc3df7163f42cac18 FOREIGN KEY (idaspetto) REFERENCES aspetto(idaspetto);


--
-- TOC entry 1866 (class 2606 OID 17638)
-- Dependencies: 1800 1364 1342
-- Name: fornitori_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fornitore
    ADD CONSTRAINT fornitori_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1908 (class 2606 OID 17653)
-- Dependencies: 1805 1366 1347
-- Name: ordine_documento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT ordine_documento FOREIGN KEY (tipo_documento) REFERENCES tipo_documento(iddocumento);


--
-- TOC entry 1909 (class 2606 OID 17658)
-- Dependencies: 1347 1345 1763
-- Name: ordini_idcliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY scarico
    ADD CONSTRAINT ordini_idcliente_fkey FOREIGN KEY (idcliente) REFERENCES cliente(idcliente);


--
-- TOC entry 1961 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2010-05-06 01:25:38

--
-- PostgreSQL database dump complete
--

