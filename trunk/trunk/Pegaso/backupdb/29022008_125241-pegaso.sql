--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

ALTER TABLE ONLY public.ordini DROP CONSTRAINT ordini_idcliente_fkey;
ALTER TABLE ONLY public.ordini DROP CONSTRAINT ordine_documento;
ALTER TABLE ONLY public.movimento_banca DROP CONSTRAINT idconto;
ALTER TABLE ONLY public.conto_bancario DROP CONSTRAINT idbanca;
ALTER TABLE ONLY public.fornitori DROP CONSTRAINT fornitori_fk;
ALTER TABLE ONLY public.clienti DROP CONSTRAINT fk_provincia;
ALTER TABLE ONLY public.ordini DROP CONSTRAINT fk_idpagamento;
ALTER TABLE ONLY public.ordini DROP CONSTRAINT fk_idcausale;
ALTER TABLE ONLY public.ordini DROP CONSTRAINT fk_idaspetto;
ALTER TABLE ONLY public.dettaglio_ordini DROP CONSTRAINT dettaglio_ordini_idordine_fkey;
ALTER TABLE ONLY public.dettaglio_ordini DROP CONSTRAINT dettaglio_ordini_idarticolo_fkey;
ALTER TABLE ONLY public.dettaglio_carichi DROP CONSTRAINT dettaglio_carichi_idcarico_fkey;
ALTER TABLE ONLY public.dettaglio_carichi DROP CONSTRAINT dettaglio_carichi_idarticolo_fkey;
ALTER TABLE ONLY public.carichi DROP CONSTRAINT carico_documento;
ALTER TABLE ONLY public.carichi DROP CONSTRAINT carichi_idfornitore_fkey;
ALTER TABLE ONLY public.articoli DROP CONSTRAINT articoli_um_fkey;
ALTER TABLE ONLY public.articoli DROP CONSTRAINT articoli_idreparto_fkey;
ALTER TABLE ONLY public.articoli DROP CONSTRAINT articoli_idfornitore_fkey;
DROP INDEX public.idx_um1;
DROP INDEX public.idx_reparti1;
DROP INDEX public.idx_pagamento1;
DROP INDEX public.idx_ordini2;
DROP INDEX public.idx_ordini1;
DROP INDEX public.idx_fornitori1;
DROP INDEX public.idx_dettaglio_ordini2;
DROP INDEX public.idx_dettaglio_ordini1;
DROP INDEX public.idx_dettaglio_carichi2;
DROP INDEX public.idx_dettaglio_carichi1;
DROP INDEX public.idx_clienti1;
DROP INDEX public.idx_causale1;
DROP INDEX public.idx_carichi2;
DROP INDEX public.idx_carichi1;
DROP INDEX public.idx_aspetto1;
DROP INDEX public.idx_articoli4;
DROP INDEX public.idx_articoli3;
DROP INDEX public.idx_articoli2;
DROP INDEX public.idx_articoli1;
ALTER TABLE ONLY public.um DROP CONSTRAINT um_pkey;
ALTER TABLE ONLY public.reparti DROP CONSTRAINT reparti_pkey;
ALTER TABLE ONLY public.provincia DROP CONSTRAINT pk_idprovincia;
ALTER TABLE ONLY public.tipo_documento DROP CONSTRAINT pk_documento;
ALTER TABLE ONLY public.pagamento DROP CONSTRAINT pagamento_pkey;
ALTER TABLE ONLY public.ordini DROP CONSTRAINT ordini_pkey;
ALTER TABLE ONLY public.tmp_etichette_fornitori DROP CONSTRAINT key_fornitori;
ALTER TABLE ONLY public.movimento_banca DROP CONSTRAINT idmov;
ALTER TABLE ONLY public.conto_bancario DROP CONSTRAINT idconto_pkey;
ALTER TABLE ONLY public.banca DROP CONSTRAINT idbanca_pkey;
ALTER TABLE ONLY public.fornitori DROP CONSTRAINT fornitori_pkey;
ALTER TABLE ONLY public.dettaglio_ordini DROP CONSTRAINT dettaglio_ordini_pkey;
ALTER TABLE ONLY public.dettaglio_carichi DROP CONSTRAINT dettaglio_carichi_pkey;
ALTER TABLE ONLY public.clienti DROP CONSTRAINT clienti_pkey;
ALTER TABLE ONLY public.causale DROP CONSTRAINT causale_pkey;
ALTER TABLE ONLY public.carichi DROP CONSTRAINT carichi_pkey;
ALTER TABLE ONLY public.aspetto DROP CONSTRAINT aspetto_pkey;
ALTER TABLE ONLY public.articoli DROP CONSTRAINT articoli_pkey;
DROP TABLE public.utenti;
DROP TABLE public.tmp_etichette_fornitori;
DROP TABLE public.tmp_etichette;
DROP TABLE public.tipo_documento;
DROP TABLE public.reparti;
DROP TABLE public.provincia;
DROP TABLE public.pagamento;
DROP TABLE public.movimento_banca;
DROP VIEW public.giacenza_articoli_view;
DROP VIEW public.giacenza_articoli_all_view;
DROP VIEW public.qta_scaricate_view;
DROP VIEW public.qta_caricate_view;
DROP TABLE public.conto_bancario;
DROP TABLE public.causale;
DROP TABLE public.banca;
DROP TABLE public.aspetto;
DROP VIEW public.articoli_scaricati_view;
DROP TABLE public.ordini;
DROP TABLE public.dettaglio_ordini;
DROP TABLE public.clienti;
DROP VIEW public.articoli_caricati_view;
DROP TABLE public.um;
DROP TABLE public.fornitori;
DROP TABLE public.dettaglio_carichi;
DROP TABLE public.carichi;
DROP TABLE public.articoli;
DROP PROCEDURAL LANGUAGE plpgsql;
DROP SCHEMA public;
--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'Standard public schema';


--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE PROCEDURAL LANGUAGE plpgsql;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: articoli; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articoli (
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
    peso bigint,
    sconto bigint,
    idreparto bigint,
    colore character varying(40),
    scorta_minima bigint,
    note character varying(200),
    data_inserimento date,
    idfornitore bigint,
    carico_iniziale bigint
);


ALTER TABLE public.articoli OWNER TO postgres;

--
-- Name: carichi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE carichi (
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
    ins_pn integer DEFAULT 0
);


ALTER TABLE public.carichi OWNER TO postgres;

--
-- Name: dettaglio_carichi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dettaglio_carichi (
    idarticolo bigint NOT NULL,
    idcarico bigint NOT NULL,
    qta double precision NOT NULL,
    prezzo_acquisto double precision,
    sconto integer DEFAULT 0
);


ALTER TABLE public.dettaglio_carichi OWNER TO postgres;

--
-- Name: fornitori; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fornitori (
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


ALTER TABLE public.fornitori OWNER TO postgres;

--
-- Name: um; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE um (
    idum bigint NOT NULL,
    nome character varying(40) NOT NULL,
    descrizione character varying(100)
);


ALTER TABLE public.um OWNER TO postgres;

--
-- Name: articoli_caricati_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW articoli_caricati_view AS
    SELECT d.idarticolo, d.idcarico, a.codbarre, a.descrizione, a.iva, u.nome AS um, d.qta, d.prezzo_acquisto, d.sconto FROM articoli a, carichi c, dettaglio_carichi d, fornitori f, um u WHERE ((((a.idarticolo = d.idarticolo) AND (c.idcarico = d.idcarico)) AND (c.idfornitore = f.idfornitore)) AND (u.idum = a.um));


ALTER TABLE public.articoli_caricati_view OWNER TO postgres;

--
-- Name: clienti; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE clienti (
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
    documento character varying,
    num_doc character varying,
    rilasciato_il date,
    rilasciato_da character varying,
    nato_a character varying,
    intestazione character varying,
    nazionalita character varying,
    provincia bigint
);


ALTER TABLE public.clienti OWNER TO postgres;

--
-- Name: dettaglio_ordini; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dettaglio_ordini (
    idordine bigint NOT NULL,
    idarticolo bigint NOT NULL,
    qta double precision NOT NULL,
    sconto bigint,
    prezzo_acquisto double precision,
    prezzo_vendita double precision,
    iva integer
);


ALTER TABLE public.dettaglio_ordini OWNER TO postgres;

--
-- Name: ordini; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ordini (
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
    sconto integer DEFAULT 0
);


ALTER TABLE public.ordini OWNER TO postgres;

--
-- Name: articoli_scaricati_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW articoli_scaricati_view AS
    SELECT d.idordine, d.idarticolo, a.codbarre, a.descrizione, a.prezzo_acquisto, a.prezzo_ingrosso, a.prezzo_dettaglio, a.iva, u.nome AS um, d.qta, d.sconto FROM articoli a, dettaglio_ordini d, ordini o, clienti c, um u WHERE ((((a.idarticolo = d.idarticolo) AND (d.idordine = o.idordine)) AND (o.idcliente = c.idcliente)) AND (u.idum = a.um));


ALTER TABLE public.articoli_scaricati_view OWNER TO postgres;

--
-- Name: aspetto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aspetto (
    idaspetto bigint NOT NULL,
    nome character varying(40) NOT NULL
);


ALTER TABLE public.aspetto OWNER TO postgres;

--
-- Name: banca; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE banca (
    idbanca bigint NOT NULL,
    ragione_sociale character varying NOT NULL,
    note character varying
);


ALTER TABLE public.banca OWNER TO postgres;

--
-- Name: causale; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE causale (
    idcausale bigint NOT NULL,
    nome character varying(40) NOT NULL
);


ALTER TABLE public.causale OWNER TO postgres;

--
-- Name: conto_bancario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE conto_bancario (
    idconto bigint NOT NULL,
    idbanca bigint NOT NULL,
    abi integer,
    cab integer,
    cc integer,
    iban character varying
);


ALTER TABLE public.conto_bancario OWNER TO postgres;

--
-- Name: qta_caricate_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW qta_caricate_view AS
    SELECT a.idarticolo, a.codbarre, sum(d.qta) AS sum FROM articoli a, carichi c, dettaglio_carichi d WHERE ((d.idcarico = c.idcarico) AND (a.idarticolo = d.idarticolo)) GROUP BY a.idarticolo, a.codbarre;


ALTER TABLE public.qta_caricate_view OWNER TO postgres;

--
-- Name: qta_scaricate_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW qta_scaricate_view AS
    SELECT a.idarticolo, a.codbarre, sum(d.qta) AS sum FROM articoli a, ordini c, dettaglio_ordini d WHERE ((d.idordine = c.idordine) AND (a.idarticolo = d.idarticolo)) GROUP BY a.idarticolo, a.codbarre;


ALTER TABLE public.qta_scaricate_view OWNER TO postgres;

--
-- Name: giacenza_articoli_all_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW giacenza_articoli_all_view AS
    SELECT c.idarticolo, a.codbarre AS codice, a.descrizione, um.nome AS um, c.sum AS carico, o.sum AS scarico, (c.sum - o.sum) AS deposito, a.prezzo_acquisto, (a.prezzo_acquisto * (c.sum - o.sum)) AS prezzo_tot FROM ((articoli a JOIN (qta_caricate_view c LEFT JOIN qta_scaricate_view o ON ((c.idarticolo = o.idarticolo))) ON ((a.idarticolo = c.idarticolo))) JOIN um ON ((a.um = um.idum)));


ALTER TABLE public.giacenza_articoli_all_view OWNER TO postgres;

--
-- Name: giacenza_articoli_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW giacenza_articoli_view AS
    SELECT c.idarticolo, a.codbarre AS codice, a.descrizione, um.nome AS um, c.sum AS carico, o.sum AS scarico, (c.sum - o.sum) AS deposito, a.prezzo_acquisto, (a.prezzo_acquisto * (c.sum - o.sum)) AS prezzo_tot FROM ((articoli a JOIN (qta_caricate_view c LEFT JOIN qta_scaricate_view o ON ((c.idarticolo = o.idarticolo))) ON ((a.idarticolo = c.idarticolo))) JOIN um ON ((a.um = um.idum))) WHERE ((c.sum - o.sum) > ((0)::numeric)::double precision);


ALTER TABLE public.giacenza_articoli_view OWNER TO postgres;

--
-- Name: movimento_banca; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE movimento_banca (
    idmov bigint NOT NULL,
    idconto bigint NOT NULL,
    data_inserimento date,
    data_scadenza date,
    descrizione character varying,
    entrate double precision,
    uscite double precision,
    note character varying
);


ALTER TABLE public.movimento_banca OWNER TO postgres;

--
-- Name: pagamento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pagamento (
    idpagamento bigint NOT NULL,
    nome character varying(40) NOT NULL
);


ALTER TABLE public.pagamento OWNER TO postgres;

--
-- Name: provincia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE provincia (
    idprovincia bigint NOT NULL,
    provincia character varying NOT NULL,
    targa character(2)
);


ALTER TABLE public.provincia OWNER TO postgres;

--
-- Name: reparti; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reparti (
    idreparto bigint NOT NULL,
    data_creazione date,
    nome character varying(40),
    descrizione character varying(100)
);


ALTER TABLE public.reparti OWNER TO postgres;

--
-- Name: tipo_documento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo_documento (
    iddocumento bigint NOT NULL,
    tipo character varying(40) NOT NULL,
    descrizione character varying(200)
);


ALTER TABLE public.tipo_documento OWNER TO postgres;

--
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
-- Name: utenti; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE utenti (
    idutente bigint NOT NULL,
    nome character varying(20) NOT NULL,
    pwd character varying(20),
    lettura integer,
    scrittura integer,
    esecuzione integer,
    note character varying
);


ALTER TABLE public.utenti OWNER TO postgres;

--
-- Data for Name: articoli; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3661, 'STILO', 'SOGGIORNO49B', 'SOGGIORNO STILO SEDIA SEDUTA CUOIO', 45.899999999999999, 20, 1, 0, 101.666666666667, '', 0, 0, 4, '', 0, '', NULL, 101, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3666, '01704600135', 'MENSOLA14', 'MENSOLA SYSTEM BOX 90 X 25', 54.450000000000003, 20, 1, 0, 0, '', 0, 0, 9, '', 0, '', '2007-10-12', 101, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3667, 'FORNOVSR', 'FORNO30', 'FORNO INOX REX', 214.30000000000001, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-12', 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3670, '', '565062', 'SOTTOL.2A+3C 85P36H88', 50.700000000000003, 20, 1, 0, 95, '', 0, 0, 1, '', 0, '', '2007-10-13', 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3671, 'FI250/2TA+', 'CUCFLO2B', 'FRIGO LT 230 CLASSE A+', 348.10000000000002, 20, 1, 0, 0, '', 0, 0, 2, '', 0, 'IN SOSTITUZIONE DI FRIGORIFERO FI2590FA (FT.219/290107)(N.C893/150207).', '2007-10-15', 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3672, 'L-SEDAR8N', 'CUCFLO2C', 'SEDIA AIR 8 CUOIO', 53.350000000000001, 0, 1, 0, 0, '', 0, 0, 2, '', 0, 'SOSTITUZIONE DI CUCFLO2 (FT.1497/280207) (N.C. 1960/150307)', '2007-10-15', 430, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3673, 'PARMA', 'CUCFLO3', 'CUCINA PARMA ML.360', 1385.75, 20, 1, 0, 3116.666666666667, '', 0, 0, 2, '', 0, 'MODELLO IN PROMOZIONE IN OFFERTA A 
€.2400,00 TRASPORTO E MONTAGGIO COMPRESI


', NULL, 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3674, '', 'CUCFLOP1', 'CUCINE PERSONALIZZATE', 160.59999999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-15', 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3675, 'ALNORMH', 'CUCFLOP2', 'ALZATINA CM.65', 3.2000000000000002, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-15', 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3676, 'SINTESI', 'CUCFLOP3', 'TOP E ALZATINA CM.195', 101.5, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-15', 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3677, '', 'CUCFLOP4', 'CUCINE PERSONALIZZATE', 394.30000000000001, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-15', 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3684, 'VEGA', 'CUCGLOP', 'CUCINA PERSONALIZZATA', 86.700000000000003, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-16', 423, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3685, '', 'DIVANO165', 'DIVANO 3PF FINTAPELLE', 170, 20, 1, 0, 0, '', 0, 0, 3, '', 0, '', '2007-10-16', 444, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3686, '', 'DIVANO166', 'DIVANO 2PF FINTAPELLE', 135, 20, 1, 0, 0, '', 0, 0, 3, '', 0, '', '2007-10-16', 444, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3687, 'SESAMO', 'CAMERETTAP', 'CAMERETTA PERSONALIZZATA', 1178.7, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-16', 344, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3688, 'ICARO', 'CAMERETTAP1', 'CAMERETTE PERSONALIZZATE', 853.64999999999998, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-16', 344, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3689, 'I6230', 'LETTOP', 'LETTO C/TURCA', 134.34999999999999, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-16', 344, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3690, 'CASSIA', 'CUCMAG', 'CUCINA CASSIA MT. 2,70 C/TAV.130X80', 1400.95, 20, 1, 0, 3583.3333333333303, '', 0, 0, 2, '', 0, '', NULL, 445, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3691, '2103472', 'VETRINA18', 'VETRINA NOCE 2103472', 699, 20, 1, 0, 0, '', 0, 0, 4, '', 0, '', '2007-10-16', 39, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3692, 'NINFA', 'POLTRONA78', 'POLTRONA', 70, 20, 1, 0, 0, '', 0, 0, 3, '', 0, '', '2007-10-16', 441, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3693, 'CRISTIAN', 'DIVANO167', 'DIVANO LETTO MATRIM.', 512, 20, 1, 0, 0, '', 0, 0, 3, '', 0, '', '2007-10-16', 441, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3701, 'D21CLA190080730', 'MATERASSO114', 'MATERASSO 80 X 190 CLASSIC', 106, 20, 1, 0, 212.5, '', 0, 0, 6, '', 0, '', NULL, 446, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3702, 'D21CLA190160730', 'MATERASSO113', 'MATERASSO 160 X 190 CLASSIC', 212, 20, 1, 0, 425, '', 0, 0, 6, '', 0, '', NULL, 446, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3662, 'SORGENTE', 'SOGGIORNO50', 'SOGGIORNO SORGENTE  270 X 220', 1221.3, 20, 1, 0, 2712.5, '', 0, 0, 4, '', 0, '', NULL, 101, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3663, 'SORGENTE', 'SOGGIORNO50B', 'SOGGIORNO SORGENTE  SEDIA', 51.299999999999997, 20, 1, 0, 114.166666666667, '', 0, 0, 4, '', 0, '', NULL, 101, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3664, 'FORNOIZN', 'FORNO29', 'FORNO 90 ARISTON', 735.35000000000002, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-05', 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3678, 'PARMA', 'CUCFLOP5', 'CUCINA PERSONALIZZATA', 1197.8499999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-15', 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3679, 'CANNOLXS', 'CAPPA', 'CAPPA 90 INOX', 79.599999999999994, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-15', 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3680, 'LET PD/B', 'PIEDE', 'PIEDI GIROLETTO', 5.4000000000000004, 20, 1, 0, 0, '', 0, 0, 4, '', 0, '', '2007-10-15', 407, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3681, '127002', 'POLTRONA77', 'POLTRONA PARSIFAL CAT. EXTRA', 412, 20, 1, 0, 0, '', 0, 0, 3, '', 0, '', '2007-10-15', 298, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3682, '127031', 'DIVANO164', 'DIVANO PARSIFAL 3PF PELLE SUPER', 1057.1500000000001, 20, 1, 0, 0, '', 0, 0, 3, '', 0, '', '2007-10-15', 298, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3683, 'M05400CS', 'SEDIA139', 'SEDIA M 54 FOND LEGNO', 52.100000000000001, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-15', 34, 6);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3694, '', 'CAMERETTA54', 'CAMERETTA PONTE 2 LETTI', 1152.8499999999999, 20, 1, 0, 2416.666666666667, '', 0, 0, 5, '', 0, '', '2007-10-16', 294, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3695, '', 'CAMERETTAP2', 'CAMERETTA PERSONALIZZATA', 435.69999999999999, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-16', 294, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3696, '', 'LIBRERIA4', 'LIBRERIA', 388.64999999999998, 20, 1, 0, 0, '', 0, 0, 4, '', 0, '', '2007-10-16', 294, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3697, '', 'CAMERETTA55', 'CAMERETTA DECAPE''', 903.14999999999998, 20, 1, 0, 1891.6666666666672, '', 0, 0, 5, '', 0, '', NULL, 294, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (9, '', 'ASTA', 'ASTA APPENDIABITI', 11, 20, 1, 0, 2.0833333333333299, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3698, '', 'MENSOLAP', 'MENSOLE PERSONALIZZATE', 173.84999999999999, 20, 1, 0, 0, '', 0, 0, 9, '', 0, '', '2007-10-16', 294, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3699, '', 'ARMADIO56', 'ARMADIO PERSONALIZZATO', 452.64999999999998, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-16', 294, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3703, 'D21EXC190160930', 'MATERASSO116', 'MATERASSO 160 X 190 EXCELS ', 418, 20, 1, 0, 837.5, '', 0, 0, 6, '', 0, '', NULL, 446, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3700, 'D21G0L190160750', 'MATERASSO115', 'MATERASSO 160 X 190 GOLD', 380, 20, 1, 0, 760, '', 0, 0, 6, '', 0, '', NULL, 446, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3705, 'D23RER190160020', 'RETE51', 'RETE EQUILIBRIUM 160 X 190 ROYAL', 88.5, 20, 1, 0, 177.5, '', 0, 0, 9, '', 0, '', NULL, 446, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3706, 'D23RIM190160020', 'RETE52', 'RETE IMPERIAL 160 X 190 ROYAL', 137.5, 20, 1, 0, 275, '', 0, 0, 9, '', 0, '', NULL, 446, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3707, 'D24GCM067042000', 'GUANCIALI26', 'GUANCIALE CAMOMILLA MEMORY F', 23, 20, 1, 0, 45.833333333333336, '', 0, 0, 6, '', 0, '', NULL, 446, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3708, 'D24GCAO66041000', 'GUANCIALI27', 'GUANCIALE CARAMELLA MEMORY F', 24, 20, 1, 0, 48.333333333333336, '', 0, 0, 6, '', 0, '', NULL, 446, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3709, 'D24DO0G80050000', 'GUANCIALI28', 'GUANCIALE DOLCESONNO FIBRA', 12.75, 20, 1, 0, 25.833333333333336, '', 0, 0, 5, '', 0, '', NULL, 446, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3711, 'D24GBE07848000', 'GUANCIALI29', 'GUANCIALE BELSONNO FIBRA', 7.75, 20, 1, 0, 15.8333333333333, '', 0, 0, 5, '', 0, '', '2007-10-17', 446, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3712, 'MAXIM', 'CAMERAP', 'CAMERA PERSONALIZZATA', 588.60000000000002, 20, 1, 0, 0, '', 0, 0, 4, '', 0, '', '2007-10-17', 196, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3713, 'FLORA', 'CAMERAP1', 'CAMERA PERSONALIZZATA', 846.20000000000005, 20, 1, 0, 0, '', 0, 0, 4, '', 0, '', '2007-10-17', 196, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3714, '', 'QUADRO33', 'QUADRO 50 X 70 OLIO SU TELA C/CORNICE', 75, 20, 1, 0, 150, '', 0, 0, 9, '', 0, '', '2007-10-17', 315, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3715, 'LAURA', 'POLTRONA72A', 'POLTRONA LAURA CAT.D', 259.89999999999998, 20, 1, 0, 259.90000000000003, '', 0, 0, 3, '', 0, '', NULL, 328, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3716, '81', 'VETRINA19', 'VETRINA', 1301, 20, 1, 0, 2600, '', 0, 0, 4, '', 0, '', '2007-10-17', 320, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3717, '80', 'CREDENZA18', 'CREDENZA', 1301, 20, 1, 0, 2600, '', 0, 0, 4, '', 0, '', NULL, 320, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3718, 'E/307', 'TAVOLO72', 'TAVOLO', 840, 20, 1, 0, 1666.6666666666667, '', 0, 0, 4, '', 0, '', NULL, 320, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3719, '821', 'SEDIA140', 'SEDIA IMBOTTITA', 116, 20, 1, 0, 233.33333333333334, '', 0, 0, 4, '', 0, '', NULL, 320, 6);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3732, 'VIVIANA', 'CUCINACSP77', 'CUCINA PERSONALIZZATA', 574, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-19', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3733, 'ALBAL85P00PO', 'LETTO71', 'LETTO ALBATROS SINGOLO', 113.34999999999999, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-19', 384, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3734, 'GUATENNIN', 'GUANCIALI14B', 'GUANCIALE WATERLILY NINFEA', 40.799999999999997, 20, 1, 0, 88.333333333333343, '', 0, 0, 1, '', 0, '', NULL, 400, 3);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3735, 'ART.273/V', 'ARMADIO57', 'ARMADIO 2A  A.273/V H. 235', 81.049999999999997, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-20', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3736, 'ART.274/V', 'ARMADIO58', 'ARMADIO 2A C/CASS  A.274/V H. 235', 95, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3738, 'BARBARA', 'BASE80', 'BASE 80 BARBARA', 47.399999999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-20', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3737, 'ART.117/R', 'LETTO72', 'LETTO SINGOLO ROMA  A.117/R', 40.100000000000001, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3704, 'D21ROY190160740', 'MATERASSO117', 'MATERASSO 160 X 190 ROYAL', 0, 0, 1, 0, 0, '', 0, 0, 6, '', 0, 'OMAGGIO
', NULL, 446, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3710, 'D24GBE07848000', 'GUANCIALI29A', 'GUANCIALE BELSONNO FIBRA', 0, 0, 1, 0, 0, '', 0, 0, 5, '', 0, 'OMAGGIO', NULL, 446, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3739, 'BARBARA', 'PENSILE80', 'PENSILE 80 BARBARA', 26.600000000000001, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3740, '', 'RETE38A', 'RETE 80 X 190 DOGHE', 23.100000000000001, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', NULL, 420, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3766, 'VIVIANA', 'CUCINACSP77A', 'CUCINA PERSONALIZZATA', 0, 20, 1, 0, 0, '', 0, 0, 2, '', 0, 'GIA'' RICEVUTA CON FT.5034/170907', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3720, 'EPOCA', 'CAMERETTAP3', 'CAMERETTA PERSONALIZZATA', 299.69999999999999, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-17', 64, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (137, '', 'LETTINO', 'LETTINO 4440221', 441, 20, 1, 0, 883.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 39, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3721, 'REGAL', 'CAMERA78', 'CAMERA DA LETTO', 4894, 20, 1, 0, 11450, '', 0, 0, 4, '', 0, '', NULL, 447, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3722, '', 'CAMERETTAP4', 'CAMERETTA PERSONALIZZATA', 68.400000000000006, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-17', 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3723, '', 'CAMERETTAP5', 'CAMERETTA PERSONALIZZATA', 254.80000000000001, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-17', 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3724, '', 'ARMADIOP', 'ARMADIO PERSONALIZZATO', 200.05000000000001, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-17', 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3725, '', 'SOGGIORNOP1', 'MOBILETTI PERSONALIZZATI', 442.10000000000002, 20, 1, 0, 0, '', 0, 0, 12, '', 0, '', '2007-10-17', 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3727, 'S2010M', 'SEDIA141', 'SEDIA FUTURE S2010M', 28.5, 20, 1, 0, 58.333333333333329, '', 0, 0, 4, '', 0, 'IN PROMOZIONE
', '2007-10-17', 73, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3726, 'T/1011 YORK', 'TAVOLO73', 'TAVOLO 80 X 140 ALL. YORK', 236, 20, 1, 0, 472.5, '', 0, 0, 4, '', 0, 'IN PROMOZIONE', NULL, 73, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3742, 'ART.517/B', 'SETTIMINO5', 'SETTIMINO A.517/B', 70.299999999999997, 20, 1, 0, 140, '', 0, 0, 4, '', 0, '', NULL, 24, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3743, '252/V', 'ARMADIO59', 'ARMADIO 2A H.200 A.252/V', 69.849999999999994, 20, 1, 0, 141.66666666666669, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3744, '346/H', 'DIVANO168', 'DIVANO 3P LETTO 346/H  ITALIA', 150, 20, 1, 0, 300, '', 0, 0, 3, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3746, '', 'RETE53', 'RETE 120 X 190 DOGHE', 37.149999999999999, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-20', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3747, '409/P', 'SEDIA142', 'SEDIA LONDRA A.409/P F/PAGLIA', 20.899999999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-20', 420, 6);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3745, '309/H', 'DIVANO169', 'DIVANO MINI FISSO 309/H ', 60.600000000000001, 20, 1, 0, 0, '', 0, 0, 3, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3741, 'GILDA', 'MATERASSO107C', 'MATERASSO 160 X 190 GILDA', 98.400000000000006, 20, 1, 0, 0, '', 0, 0, 6, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3767, 'T160M', 'LETTO75', 'TESTIERA LETTO RODO', 106.90000000000001, 20, 1, 0, 0, '', 0, 0, 4, '', 0, '', '2007-10-22', 407, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3768, '', 'CAMERA64A', 'LETTO CAMERA PLANA', 167.15000000000001, 20, 1, 0, 375, '', 0, 0, 4, '', 0, 'RIMANENZA
', NULL, 243, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3748, '1001', 'TAVOLINO66', 'TAVOLINO BACHECA ART.1001', 64.5, 20, 1, 0, 129.16666666666703, '', 0, 0, 9, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3769, 'CASSIA', 'TAVOLO75', 'TAVOLO 160 X 80', 190.94999999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-22', 445, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3770, '4551P101', 'PIANO', 'PIANO 1016X600X45', 0, 20, 1, 0, 0, '', 0, 0, 9, '', 0, 'SOSTITUZIONE	', '2007-10-22', 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3728, 'TP120 X 80', 'TAVOLO74', 'TAVOLO 120 X 80 ALL.', 130.55000000000001, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-17', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (176, '', 'MENSOLA12', 'MENSOLA 60 X 60', 15.300000000000001, 20, 1, 0, 30.833333333333336, '', 0, 0, 8, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (192, '', 'POLTRONA72', 'POLTRONA LAURA CAT.B', 200, 20, 1, 0, 400, '', 0, 0, 3, '', 0, '', NULL, 328, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3729, 'MADISON', 'CUCINACSP76', 'CUCINA PERSONALIZZATA', 187.59999999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-17', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3730, 'SONIA', 'CUCINACS6A', 'CUCINA SONIA ML.3.15', 1097.55, 20, 1, 0, 2250, '', 0, 0, 1, '', 0, 'RIMANENZA DA CUCINACS6
', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3731, 'SONIA', 'CUCINACS6B', 'CUCINA SONIA ML.3.15 ', 743.75, 20, 1, 0, 1750, '', 0, 0, 1, '', 0, 'COMPLETAMENTO X CUCINACS6A
', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3749, 'RUXFMS', 'RUBINETTO23', 'RUBINETTO SOTTOFINESTRA INOX', 75.150000000000006, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-20', 430, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3754, 'MINA02', 'CUCINT', 'CUCINA ML.3', 1275.2, 20, 1, 0, 2845, '', 0, 0, 2, '', 0, '', '2007-10-20', 448, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3755, 'MINA02', 'CUCINTA', 'SOSTITUZIONE ANTE E TOP', 0, 20, 1, 0, 0, '', 0, 0, 2, '', 0, 'SOSTITUZIONE', NULL, 448, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3756, 'CASSIA', 'CUCMAGA', 'SOSTITUZIONE MENSOLONE 60X60', 0, 20, 1, 0, 0, '', 0, 0, 2, '', 0, 'SOSTITUZIONE
', NULL, 445, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3757, 'CASSIA', 'CUCMAGB', 'SOSTITUZIONE TOP 60X150', 0, 20, 1, 0, 0, '', 0, 0, 2, '', 0, 'SOSTITUZIONE
', NULL, 445, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3759, '4080/34', 'CAMERA49A', 'SOSTITUZIONE LETTO BERNINI', 0, 20, 1, 0, 0, '', 0, 0, 4, '', 0, 'SOSTITUZIONE
', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3760, '', 'CAMERETTAP7', 'CAMERETTA PERSONALIZZATA', 410.35000000000002, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-20', 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3761, '916662', '916662', 'PORTACOPY L65P55H62', 57, 20, 1, 0, 117.5, '', 0, 0, 9, '', 0, '', '2007-10-20', 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3762, '913367', '913367', 'MOBILE C/RUOTE L90P51H59,5', 54.299999999999997, 20, 1, 0, 111.6666666666667, '', 0, 0, 9, '', 0, '', NULL, 394, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3758, '', 'CAMERETTAP6', 'CAMERETTA PERSONALIZZATA', 1046.05, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', NULL, 294, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3763, '9163T32/980928', 'SCRIVANIA', 'SCRIVANIA ABS 160 X 80', 122, 20, 1, 0, 0, '', 0, 0, 12, '', 0, '', '2007-10-20', 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3764, '584466', '584466A', 'CARRELLO TV 2ANTE 76X47X79', 60.299999999999997, 20, 1, 0, 113.33333333333334, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3765, '584467', '584467B', 'CARR.TV 2A L94P54H70', 70.150000000000006, 20, 1, 0, 131.66666666666669, '', 0, 0, 4, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (224, '', 'RETE10', 'RETE 80 X 190 METALLO NORMALE', 16.5, 20, 1, 0, 33.333333333333336, '', 0, 0, 1, '', 0, '', NULL, 69, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (221, '', 'RETE27', 'RETE 165 X 191 FLEXWOOD', 93, 20, 1, 0, 185.83333333333334, '', 0, 0, 4, '', 0, '', NULL, 221, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3750, 'M05400CS', 'SEDIA139A', 'SEDIA M 54 FOND LEGNO', 54.5, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', NULL, 34, 6);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3665, '01704721018', 'ANTE', 'SOSTITUZIONE ANTE SYSTEM BOX ', 0, 0, 1, 0, 0, '', 0, 0, 4, '', 0, 'SOSTITUZIONI', NULL, 101, 5);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3669, 'SOSTITUZIONI', 'CUCFLO2A', 'SOSTITUZIONE TOP E ZOCCOLI', 0, 0, 1, 0, 0, '', 0, 0, 2, '', 0, 'SOSTITUZIONI
', NULL, 430, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3668, 'FRIGOTOR', 'FRIGO', 'SOSTITUZIONE FRIGO LT.230 REX', 0, 0, 1, 0, 0, '', 0, 0, 2, '', 0, 'SOSTITUZIONE
', NULL, 430, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3609, 'BOLEK85POIGF', 'LETTO66', 'SOSTITUZIONE LETTO BOLERO CON POMOLI  CERAMICA', 0, 0, 1, 0, 0, '', 0, 0, 4, '', 0, 'SOSTITUZIONE', NULL, 384, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3752, 'I6321/I1340', 'LETTO73', 'LETTO TURCA 2 RETI DOGHE', 204.69999999999999, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', NULL, 344, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3753, 'I6220', 'LETTO74', 'LETTO C/TURCA FEY', 84.049999999999997, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', NULL, 344, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3751, '', 'MENSOLAP1', 'MENSOLE PERSONALIZZATE', 63.25, 20, 1, 0, 0, '', 0, 0, 9, '', 0, '', NULL, 344, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3771, 'S2010M', 'SEDIA141A', 'SEDIA FUTURE S2010M', 0, 20, 1, 0, 0, '', 0, 0, 4, '', 0, 'SOSTITUZIONE
', NULL, 73, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3773, 'SONIA', 'CUCINACSP78', 'CUCINA PERSONALIZZATA', 1731.55, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-23', 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3774, 'SONIA', 'ANTA', 'SOSTITUZIONE ANTA SONIA 1002X447 SCOLAPIATTI', 0, 20, 1, 0, 0, '', 0, 0, 2, '', 0, 'RESO NS. D.D.T. N.24/2007 DEL 231007', '2007-10-23', 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3775, 'D24GNVO72042000', 'GUANCIALI30', 'GUANCIALE NUVOLA VISCO', 24, 20, 1, 0, 48.333333333333336, '', 0, 0, 5, '', 0, '', NULL, 446, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3786, 'ART.123/R', 'LETTO76', 'LETTO MATRIM. UDINE  A.123/R', 93.400000000000006, 20, 1, 0, 93.40000000000002, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3787, 'ART.410/P', 'SEDIA132B', 'SEDIA ART.410/P F/LEGNO', 23.75, 20, 1, 0, 23.75, '', 0, 0, 1, '', 0, '', NULL, 420, 6);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3772, 'ZAIRA', 'SALOTTO21', 'SALOTTO 3+2 ZAIRA CAT.B', 372.75, 20, 1, 0, 0, '', 0, 0, 3, '', 0, '', '2007-10-22', 328, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (283, '', 'SEDIA131', 'SEDIA SE65 CROMO', 39.149999999999999, 20, 1, 0, 120, '', 0, 0, 1, '', 0, '', NULL, 381, 6);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (274, '', 'SEDIA134', 'SEDIA 3 ARCHI', 107.25, 20, 1, 0, 214.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 358, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3779, 'SINTESI', 'CUCFLOP6', 'CUCINA PERSONALIZZATA', 0, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', NULL, 430, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3780, 'ML', 'MENSOLAP2', 'MENSOLA ML.1,50', 36.399999999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-23', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3781, 'PLS4', 'TOPP', 'TOP ML.1,80', 61, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3782, 'TN251', 'MANIGLIA', 'MANIGLIA SONIA INOX', 3.75, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', NULL, 363, 20);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3783, 'VICTOR', 'SALOTTO19A', 'SOSTITUZIONE FODERA CUSCINO SEDUTA', 0, 20, 1, 0, 0, '', 0, 0, 1, '', 0, 'NS. DDT 19/2007 DEL 020707 RESO A FORNITORE
', NULL, 441, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3784, '21KB142', 'PANNELLI1', 'SOSTITUZIONE PANNELLI INF. 103,2 X 66,4', 0, 20, 1, 0, 0, '', 0, 0, 5, '', 0, 'SOSTITUZIONI RESO NS.DDT 10/2007 DEL 150207', '2007-10-23', 294, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3785, '9D20404/9D2045F', 'LETTO73A', 'SOSTITUZIONE OVALINA E FRONTALINO', 0, 20, 1, 0, 0, '', 0, 0, 5, '', 0, 'SOSTITUZIONE RESO NS.DDT 22/2007 DEL 091007', NULL, 344, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3778, 'C2YUP', 'SALOTTO22', 'SALOTTO ANGOLARE TESS. NOFLEK', 0, 20, 1, 0, 2291.666666666667, '', 0, 0, 3, '', 0, '', NULL, 278, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3789, 'I2817', 'PIANO1', 'SOSTITUZIONE PIANO TRAPEZIO', 0, 20, 1, 0, 0, '', 0, 0, 5, '', 0, 'SOSTITUZIONE', NULL, 344, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3788, 'I45241', 'ARMADIOP1', 'ARMADIO PERSONALIZZATO', 0, 0, 1, 0, 0, '', 0, 0, 5, '', 0, '', NULL, 344, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3790, 'I6424', 'SETTIMINO6', 'SETTIMANALE', 0, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', NULL, 344, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3776, 'ITALIA00CC', 'TAVOLO76', 'TAVOLO ITALIA CILIEGIO 85 X 130 X 220', 256.5, 20, 1, 0, 570, '', 0, 0, 4, '', 0, '', NULL, 34, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3777, 'FIOREOO', 'SEDIA143', 'SEDIA FIORE IN FAGGIO  CILIEGIO CERA', 36, 20, 1, 0, 80, '', 0, 0, 4, '', 0, '', NULL, 34, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (309, '', 'SOGGIORNO5', 'SOGGIORNO ARMONIA LACC. ANTIC.', 1927, 20, 1, 0, 3875, '', 0, 0, 1, '', 0, '', NULL, 66, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (367, '', 'SALOTTO14', 'SALOTTINO 2+1+1+TAV CIGNI LACC.BLU''', 532, 20, 1, 0, 1076.25, '', 0, 0, 1, '', 0, '', NULL, 78, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (357, '', 'VETRINA2', 'VETRINA CIGNI LACC.BLU'' A.227', 496, 20, 1, 0, 992.08333333333337, '', 0, 0, 1, '', 0, '', NULL, 78, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (536, '', 'SEDIA132A', 'SEDIA ART.410/P F/LEGNO', 22.199999999999999, 20, 1, 0, 45, '', 0, 0, 1, '', 0, '', NULL, 420, 6);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (537, '', 'SEDIA136', 'SEDIA ZETA /H', 32.799999999999997, 20, 1, 0, 65.833333333333343, '', 0, 0, 1, '', 0, '', NULL, 34, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (609, '', 'COLONNA', 'COLONNA PORTAVASO', 85.5, 20, 1, 0, 172.5, '', 0, 0, 1, '', 0, '', NULL, 199, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (635, '', 'CREDENZA3', 'CREDENZA 2 ANTE ART.207/S', 444.5, 20, 1, 0, 904.16666666666674, '', 0, 0, 1, '', 0, '', NULL, 78, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (634, '', 'INGRESSO25', 'INGRESSO ARTE POVERA ART.154S-168S-178S', 559, 20, 1, 0, 1119.1666666666667, '', 0, 0, 1, '', 0, '', NULL, 78, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (648, '', 'INGRESSO27', 'INGRESSO SIENA COMP.D', 670, 20, 1, 0, 1340, '', 0, 0, 4, '', 0, '', NULL, 27, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (640, '', 'RUBINETTO8', 'RUBINETTO DOCCIA ESTRAIBILE', 45, 20, 1, 0, 90.416666666666657, '', 0, 0, 1, '', 0, '', NULL, 7, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (646, '', 'SEDIA28', 'SEDIA A3', 12.5, 20, 1, 0, 29.166666666666668, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (642, '', 'SOGGIORNO20', 'SOGGIORNO COMP.4710 SENZA TAV.E SEDIE', 1322, 20, 1, 0, 2647.0833333333348, '', 0, 0, 1, '', 0, '', NULL, 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (656, '', 'RUBINETTO10', 'RUBINETTO MONOFORO SPRINT', 8, 20, 1, 0, 16.666666666666668, '', 0, 0, 1, '', 0, '', NULL, 7, 3);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (654, '', 'TAVOLINO25', 'TAVOLINO ART.3757472', 212, 20, 1, 0, 426.25, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (726, '', 'LETTO14', 'LETTO A CASTELLO', 61.950000000000003, 20, 1, 0, 150, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (710, '', 'PC9051', 'PC 90 5GAS ACC.ELETT.', 127.2, 20, 1, 0, 304.16666666666669, '', 0, 0, 2, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (764, '', 'LUCE', 'FARETTO ALOGENO X VETRINA', 19.5, 20, 1, 0, 38.75, '', 0, 0, 1, '', 0, '', NULL, 218, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (752, '', 'TAVOLINO31', 'TAVOLINO CANOVA', 236, 20, 1, 0, 473.75, '', 0, 0, 1, '', 0, '', NULL, 30, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (806, '', 'PORTATELEFONO8', 'PORTATELEFONO STELLA', 72.5, 20, 1, 0, 146.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 222, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (805, '', 'SEDIA34', 'SEDIA M37 NOCE', 34, 20, 1, 0, 69.166666666666671, '', 0, 0, 1, '', 0, '', NULL, 34, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (792, '', 'TAVOLINO34', 'TAVOLINO FOULARD 2005/1', 206.5, 20, 1, 0, 413.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 171, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (835, '', 'RETE22', 'RETE 160 X 195 FLEXWOOD', 95.5, 20, 1, 0, 185.83333333333334, '', 0, 0, 1, '', 0, '', NULL, 221, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (899, '', 'MATERASSO34', 'MATERASSO 80 X 190 STAMPATO', 22.649999999999999, 20, 1, 0, 57.083333333333336, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (957, '', 'SEDIA43', 'SEDIA IUNIOR C/BRACC. - U F F I C I O -', 45, 20, 1, 0, 90.416666666666657, '', 0, 0, 1, '', 0, '', NULL, 34, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1006, '', 'PORTATV23', 'PORTATV A.TV80NO 80 X 40  - U F F I C IO', 24.5, 20, 1, 0, 50, '', 0, 0, 1, '', 0, '', NULL, 150, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1008, '', 'SOGGIORNO23', 'SOGGIORNO COMP.4 C/TAV.+6SEDIE', 743, 20, 1, 0, 1506.6666666666699, '', 0, 0, 1, '', 0, '', NULL, 243, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (979, '', 'TAPPETO8', 'TAPPETO ORIENTALE ANNODATO A MANO', 143.5, 20, 1, 0, 288.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 237, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1085, '', 'POLTRONA22', 'POLTRONA S300 UDINE', 42, 20, 1, 0, 84.1666666666667, '', 0, 0, 1, '', 0, '', NULL, 150, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1100, '', 'CUCINALAA', 'TAVOLO 90 X 90', 124, 20, 1, 0, 248.33333333333331, '', 0, 0, 1, '', 0, '', NULL, 177, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1139, '', 'GINOCCHIERA', 'GINOCCHIERA DATTILO S403', 55.5, 20, 1, 0, 112.08333333333334, '', 0, 0, 1, '', 0, '', NULL, 150, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1159, '', 'DIVANO57', 'DIVANO 3PF RONDO'' C/FRA.CAT.C', 421.5, 20, 1, 0, 843.75, '', 0, 0, 1, '', 0, '', NULL, 255, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1146, '', 'LIBRERIA3', 'LIBRERIA DA PARETE H.227,5 L.100', 121.5, 20, 1, 0, 245.8333333333334, '', 0, 0, 1, '', 0, '', NULL, 150, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1158, '', 'POLTRONA24', 'POLTRONA ISOTTA CAT.B', 291, 20, 1, 0, 583.3333333333336, '', 0, 0, 1, '', 0, '', NULL, 255, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1164, '', 'SOGGIORNO13A', 'TAVOLO A LIBRO 100X100 FLO'' NOCE', 278.5, 20, 1, 0, 559.5833333333336, '', 0, 0, 1, '', 0, '', NULL, 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1184, '', 'SPECCHIO1', 'SPECCHIO RLS01 RIALTO', 64.5, 20, 1, 0, 129.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 256, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1172, '', 'TAPPETO10', 'TAPPETO 7505800', 56.5, 20, 1, 0, 114.16666666666667, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1206, '', 'SCRITTOIO', 'SCRITTOIO ART.2959/31', 255, 20, 1, 0, 516.66666666666674, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1207, '', 'SEDIA52', 'SEDIA ART.2930/31', 83.5, 20, 1, 0, 167.91666666666669, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1213, '', 'SOGGIORNO25', 'SOGGIORNO PARETE START', 1440, 20, 1, 0, 3043.3333333333344, '', 0, 0, 1, '', 0, '', NULL, 211, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1272, '', 'DIVANO66', 'DIVANO 3PF OPERA/M C/CUSCINO', 651.5, 20, 1, 0, 1305, '', 0, 0, 1, '', 0, '', NULL, 219, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1273, '', 'DIVANO67', 'DIVANO 2PF OPERA/M C/CUSCINO', 590.5, 20, 1, 0, 1183.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 219, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1265, '', 'LUMI', 'TRITTICO LUMI', 90.5, 20, 1, 0, 180.83333333333334, '', 0, 0, 1, '', 0, '', NULL, 234, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1271, '', 'SEDIA54', 'SEDIA CURVA 3 SPALLE', 22.5, 20, 1, 0, 45.416666666666671, '', 0, 0, 1, '', 0, '', NULL, 41, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1249, '', 'TAVOLINO41', 'TAVOLINO STELLA/C', 212, 20, 1, 0, 426.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 222, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1320, '', 'APPENDIABITO1', 'APPENDIABITO CROMO-CILIEGIO', 86, 20, 1, 0, 172.5, '', 0, 0, 1, '', 0, '', NULL, 73, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1321, '', 'APPENDIABITO2', 'APPENDIABITO ALL-PERO', 83.5, 20, 1, 0, 167.91999999999999, '', 0, 0, 1, '', 0, '', NULL, 73, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1292, '', 'RUBINETTO12', 'RUBINETTO MISCELATORE CROMATO', 27, 20, 1, 0, 54.166666666666671, '', 0, 0, 1, '', 0, '', NULL, 7, 8);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1327, '', 'QUADRO12', 'QUADRO STAMPA', 5, 20, 1, 0, 10.416666666666668, '', 0, 0, 1, '', 0, '', NULL, 73, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1328, '', 'QUADRO13', 'QUADRO STAMPA', 5, 20, 1, 0, 10.416666666666668, '', 0, 0, 1, '', 0, '', NULL, 73, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1329, '', 'QUADRO14', 'STAMPA', 7.5, 20, 1, 0, 15, '', 0, 0, 1, '', 0, '', NULL, 73, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1384, '', 'CONSOLLE2', 'CONSOLLE LUIGI XIII', 1008, 20, 1, 0, 2016.6666666666674, '', 0, 0, 1, '', 0, '', NULL, 272, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1380, '', 'RETE29', 'RETE 80 X 190 FLEXWOOD', 37, 20, 1, 0, 75.416666666666671, '', 0, 0, 1, '', 0, '', NULL, 221, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1455, '', 'INGRESSO29', 'INGRESSO C/SCARPIERA ALOA', 398, 20, 1, 0, 796.25, '', 0, 0, 1, '', 0, '', NULL, 53, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1454, '', 'SPECCHIERA23', 'SPECCHIERA 12009817', 93, 20, 1, 0, 187.5, '', 0, 0, 1, '', 0, '', NULL, 81, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1479, '', 'DIVANO77', 'DIVANO 3PF IDEA ALCANTARA', 738, 20, 1, 0, 1476.25, '', 0, 0, 1, '', 0, '', NULL, 278, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1480, '', 'DIVANO78', 'DIVANO 2PF IDEA ALCANTARA', 581.5, 20, 1, 0, 1166.6666666666667, '', 0, 0, 1, '', 0, '', NULL, 278, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1473, '', 'RUBINETTO14', 'RUBINETTO MISC.C/DOCC.TOPAZIO AVENA', 107.5, 20, 1, 0, 215.41666666666669, '', 0, 0, 1, '', 0, '', NULL, 5, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1544, '', 'ARMADIO26', 'ARMADIO 6 PORTE NOCE', 2133, 20, 1, 0, 4265.4166666666661, '', 0, 0, 1, '', 0, '', NULL, 260, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1546, '', 'COMO6', 'COMO'' 5 CASS. + RIBALTA', 799, 20, 1, 0, 1601.25, '', 0, 0, 1, '', 0, '', NULL, 260, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1570, '', 'CROCIFISSO', 'CROCIFISSO DORATO', 49, 20, 1, 0, 99.166666666666671, '', 0, 0, 1, '', 0, '', NULL, 61, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1578, '', 'CUCINAMETA15', 'LAVELLO INOX 45 1V/SIFONE A114400', 38, 20, 1, 0, 77.5, '', 0, 0, 1, '', 0, '', NULL, 42, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1589, '', 'ELPIANTANA', 'PIANTANA ART.192', 212.5, 20, 1, 0, 426.25, '', 0, 0, 1, '', 0, '', NULL, 36, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1593, '', 'INGRESSO10', 'INGRESSO 3 PEZZI', 535.5, 20, 1, 0, 1076.25, '', 0, 0, 1, '', 0, '', NULL, 96, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1594, '', 'INGRESSO13', 'INGRESSO ART.198', 792, 20, 1, 0, 1592.5, '', 0, 0, 1, '', 0, '', NULL, 86, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1596, '', 'INGRESSO15', 'INGRESSO PARETE COMP.18', 514, 20, 1, 0, 1032.9166666666667, '', 0, 0, 1, '', 0, '', NULL, 86, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1625, '', 'PORTACOMPUTER', 'PORTACOMPUTER METALEGNO DA 90', 140.5, 20, 1, 0, 282.08333333333337, '', 0, 0, 1, '', 0, '', NULL, 53, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1629, '', 'PORTATV2', 'PORTATV A.2313031', 178, 20, 1, 0, 358.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1631, '', 'PORTATV6', 'PORTATV A.2175/EL51 NERO-VETRO', 94.5, 20, 1, 0, 189.58333333333334, '', 0, 0, 1, '', 0, '', NULL, 73, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1642, '', 'QUADRO9', 'DECORAZIONI', 5, 20, 1, 0, 10.416666666666668, '', 0, 0, 1, '', 0, '', NULL, 57, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1648, '', 'RETE15', 'RETE 160 X 190 DOGHE', 51.5, 20, 1, 0, 103.33333333333334, '', 0, 0, 1, '', 0, '', NULL, 69, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1650, '', 'RETE4', 'RETE 160 X 190 METALLO STANDARD', 58.5, 20, 1, 0, 119.16666666666667, '', 0, 0, 1, '', 0, '', NULL, 45, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1665, '', 'SCARPIERA14', 'SCARPIERA E094 FE/F SERR.FUCSIA', 91, 20, 1, 0, 183.33333333333343, '', 0, 0, 1, '', 0, '', NULL, 4, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1681, '', 'SOGGIORNO9', 'SOGGIORNO NOCE MAGNOLIA - U F F I C I O', 860, 20, 1, 0, 1721.6666666666665, '', 0, 0, 1, '', 0, '', NULL, 60, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1719, '', 'GRUPPOLETTO1A', 'SPECCHIERA ORO A.172', 227.5, 20, 1, 0, 473.75, '', 0, 0, 1, '', 0, '', NULL, 78, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1682, '', 'SPECCHIERA1', 'SPECCHIERA OVALINE NOCE', 41.5, 20, 1, 0, 84.166666666666671, '', 0, 0, 1, '', 0, '', NULL, 85, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1685, '', 'SPECCHIERA3', 'SPECCHIERA A.1202817 ORO ANTICO', 147, 20, 1, 0, 295, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1687, '', 'SPECCHIERA5', 'SPECCHIO OLMELLA NOCE - U F F I C I O', 23, 20, 1, 0, 47.5, '', 0, 0, 1, '', 0, '', NULL, 43, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1689, '', 'SPECCHIERA8', 'SPECCHIERA A.1027 ORO-ARGENTO', 248.5, 20, 1, 0, 497.5, '', 0, 0, 1, '', 0, '', NULL, 28, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1690, '', 'SPECCHIERA9', 'SPECCHIERA PARIGI', 71, 20, 1, 0, 142.08333333333334, '', 0, 0, 1, '', 0, '', NULL, 97, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1699, '', 'TAVOLINO21', 'TAVOLINO METALLO/VETRO A.468300', 147, 20, 1, 0, 295, '', 0, 0, 1, '', 0, '', NULL, 53, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1701, '', 'TAVOLINO24', 'TAVOLINO MASSICCIO C/VETRO A.289/S', 235, 20, 1, 0, 474.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 78, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1776, '', 'MENSOLA', 'MENSOLA VECCH. VENETO DA 120', 56.5, 20, 1, 0, 114.16666666666667, '', 0, 0, 1, '', 0, '', NULL, 103, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1800, '', 'TAVOLINO15', 'TAVOLINOA.693600 ZECCA CIL.', 153, 20, 1, 0, 307.91666666666669, '', 0, 0, 1, '', 0, '', NULL, 53, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1804, '', 'TOP7', 'TOP DA 60 CM. 60', 15.550000000000001, 20, 1, 0, 31.25, '', 0, 0, 1, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1882, '', 'INDOSSATORE4', 'INDOSSATORE', 49.5, 20, 1, 0, 99.583333333333343, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1898, '', 'QUADRO15', 'QUADRO C/STAMPA 50X70', 26, 20, 1, 0, 51.666666666666671, '', 0, 0, 1, '', 0, '', NULL, 251, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1899, '', 'QUADRO16', 'QUADRO ARTE POVERA 40X50', 8, 20, 1, 0, 15.833333333333334, '', 0, 0, 1, '', 0, '', NULL, 251, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1901, '', 'QUADRO18', 'QUADRO C/STAMPA 30X40', 15.5, 20, 1, 0, 31.25, '', 0, 0, 1, '', 0, '', NULL, 251, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1878, '', 'STIRAPANTALONI', 'STIRAPANTALONI', 77.5, 20, 1, 0, 155, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1945, '', 'COMONCINO', 'COMONCINO LACCATO', 717, 20, 1, 0, 1435.4166666666667, '', 0, 0, 1, '', 0, '', NULL, 21, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1947, '', 'STAMPA1', 'STAMPA SU TAVOLA', 36, 20, 1, 0, 73.333333333333343, '', 0, 0, 1, '', 0, '', NULL, 81, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1948, '', 'STAMPA2', 'STAMPA SU TAVOLA', 41.5, 20, 1, 0, 82.916666666666671, '', 0, 0, 1, '', 0, '', NULL, 81, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1946, '', 'VETRINETTA', 'VETRINETTA LACCATA', 807, 20, 1, 0, 1618.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 21, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1997, '', 'ARGENTIERA6', 'ARGENTIERA 2P BERNINI', 1093, 20, 1, 0, 2179.166666666667, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1998, '', 'CREDENZONE', 'CREDENZONE 2P 7C BERNINI', 961, 20, 1, 0, 2000, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1968, '', 'DIVANO86', 'DIVANO 2P SOUL CAT.LUSSO COL.BAGUTTA 104', 664, 20, 1, 0, 1334.1666666666665, '', 0, 0, 1, '', 0, '', NULL, 298, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1969, '', 'DIVANO87', 'DIVANO 3P SOUL CAT.LUSSO COL.BAGUTTA 104', 804, 20, 1, 0, 1609.5833333333335, '', 0, 0, 1, '', 0, '', NULL, 298, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2000, '', 'SEDIA69', 'SEDIA ARPA BERNINI A', 133, 20, 1, 0, 291.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2001, '', 'SEDIA69A', 'SEDIA CAPOTAVOLA ARPA BERNINI A', 157.5, 20, 1, 0, 333.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1999, '', 'TAVOLO32', 'TAVOLO RETT.ALL.180 X 100', 849, 20, 1, 0, 1813.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (1959, '', 'TAVOLINO45', 'TAVOLINO ART. 3758472', 137, 20, 1, 0, 275.83333333333297, '', 0, 0, 1, '', 0, '', NULL, 39, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2029, '', 'COMODINO9', 'COMODINO 3 CASS.', 263.5, 20, 1, 0, 527.5, '', 0, 0, 1, '', 0, '', NULL, 260, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2030, '', 'LETTO30', 'LETTO MATRIMONIALE NOCE', 606.5, 20, 1, 0, 1213.75, '', 0, 0, 1, '', 0, '', NULL, 260, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2048, '', 'POLTRONA31', 'POLTRONA 46101268', 290.5, 20, 1, 0, 581.25, '', 0, 0, 1, '', 0, '', NULL, 81, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2060, '', 'ARGENTIERA4', 'ARGENTIERA 2 ANTE', 442, 20, 1, 0, 891.66999999999996, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2083, '', 'CAMERA49', 'CAMERA BERNINI', 3124.5, 20, 1, 0, 8612.5, '', 0, 0, 4, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2085, '', 'CAMERA52', 'CAMERA CLIVIA', 1010, 20, 1, 0, 2020, '', 0, 0, 1, '', 0, '', NULL, 20, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2078, '', 'DIVANO102', 'DIVANO 2PF PARSIFAL PELLE E.', 759.5, 20, 1, 0, 1527.9166666666667, '', 0, 0, 1, '', 0, '', NULL, 298, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2079, '', 'DIVANO103', 'DIVANO 3PF PARSIFAL PELLE E.', 911.5, 20, 1, 0, 1829.1666666666667, '', 0, 0, 1, '', 0, '', NULL, 298, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2057, '', 'SPECCHIERA27', 'SPECCHIERA 1211517', 141.5, 20, 1, 0, 284.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2101, '', 'QUADRO22', 'QUADRO 50 X 70', 15.5, 20, 1, 0, 83.333333333333343, '', 0, 0, 1, '', 0, '', NULL, 104, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2108, '', 'QUADRO24', 'DIPINTO 50X60', 51.5, 20, 1, 0, 104.16666666666667, '', 0, 0, 1, '', 0, '', NULL, 315, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2109, '', 'QUADRO25', 'DIPINTO 50X70', 26, 20, 1, 0, 52.083333333333329, '', 0, 0, 1, '', 0, '', NULL, 315, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2168, '', 'LAVELLO1203', 'LAVELLO LOGICA 621 1160X500 FRANKE', 64.5, 20, 1, 0, 129.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 240, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2186, '', 'RUBINETTO16', 'RUBINETTO MISC.B/SNODO SARAH', 22.5, 20, 1, 0, 45.416666666666671, '', 0, 0, 1, '', 0, '', NULL, 7, 11);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2162, 'ART.124/R', 'RETE36A', 'RETE A GABBIA C/DOGHE E C/MATERASSO', 31.5, 20, 1, 0, 63.333333333333336, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2215, '', 'GUANCIALI11', 'GUANCIALE ANTIRUGHE', 23.5, 20, 1, 0, 47.5, '', 0, 0, 1, '', 0, '', NULL, 325, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2212, '', 'GUANCIALI8', 'GUANCIALE MISTO LANA', 12, 20, 1, 0, 24.166666666666668, '', 0, 0, 1, '', 0, '', NULL, 325, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2206, '', 'MATERASSO61', 'MATERASSO 80 X 190 ONDA A.R.', 56.5, 20, 1, 0, 114.16666666666667, '', 0, 0, 1, '', 0, '', NULL, 325, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2201, '', 'POLTRONA41', 'POLTRONA CLASSICA ANTICATA', 93, 20, 1, 0, 187.5, '', 0, 0, 1, '', 0, '', NULL, 64, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2191, '', 'SEDIA80', 'SEDIA STADIO', 30.149999999999999, 20, 1, 0, 67.083333333333343, '', 0, 0, 1, '', 0, '', NULL, 243, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2200, '', 'SPECCHIERA29', 'SPECCHIERA ART.12005517', 105, 20, 1, 0, 211.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 81, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2247, '', 'APPENDIABITO4', 'PIANTANA', 72.5, 20, 1, 0, 145, '', 0, 0, 1, '', 0, '', NULL, 81, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2325, '', 'CAMERETTA23B', 'SEDIA BUNNY VERDE', 40, 20, 1, 0, 75.420000000000016, '', 0, 0, 1, '', 0, '', NULL, 211, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2339, '', 'DIVANO118', 'DIVANO ECO CLELIA', 259, 20, 1, 0, 451.66666666666663, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2368, '', 'RETE37', 'RETE 160 X 190 ORT.DOGHE IN LEGNO', 40.5, 20, 1, 0, 81.25, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2383, '', 'RETE38', 'RETE 80 X 190 DOGHE', 19.5, 20, 1, 0, 39.583333333333336, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2411, '', 'SALOTTO16', 'SALOTTO C/PENISOLA CAT.C', 622.5, 20, 1, 0, 1248.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2425, '', 'CAMERETTA37', 'CAMERETTA COMPONIBILE', 1234.5, 20, 1, 0, 2475, '', 0, 0, 1, '', 0, '', NULL, 344, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2450, '', 'CUCINAPAA', 'TOPS LAMINATO ML.3,34', 76.5, 20, 1, 0, 154.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 150, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2439, '', 'DIVANO107A', 'DIVANO 3PF STELTON C. CAT.C', 297, 20, 1, 0, 602.5, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2440, '', 'DIVANO108A', 'DIVANO 2PF STELTON C. CAT.C', 248, 20, 1, 0, 499.16666666666686, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2417, '', 'MATERASSO73', 'MATERASSO 160 X 190 LIPARI ORT.', 88, 20, 1, 0, 176.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 335, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2493, '', 'POLTRONA47', 'POLTRONA LEGNO-STOFFA', 71, 20, 1, 0, 142.08333333333334, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2474, '', 'SALOTTO13A', 'POLTRONA ELEONORA', 304.5, 20, 1, 0, 611.25, '', 0, 0, 1, '', 0, '', NULL, 65, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2484, '', 'SOGGIORNO34', 'SOGGIORNO COMPONIBILE', 1123, 20, 1, 0, 2495.8333333333335, '', 0, 0, 1, '', 0, '', NULL, 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2549, '', 'CAMERETTA38', 'CAMERETTA PONTE', 824.5, 20, 1, 0, 1845.8333333333335, '', 0, 0, 1, '', 0, '', NULL, 344, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2523, '', 'RUBINETTO17', 'RUBINETTO MISCELATORE ECO', 10, 20, 1, 0, 45.416666666666671, '', 0, 0, 1, '', 0, '', NULL, 7, 29);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2553, '', 'SCRITTOIO2', 'COMPOS. PORTACOMPUTER', 370.5, 20, 1, 0, 823.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 344, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2512, '', 'SEDIA90', 'SEDIA FONDO STOFFA', 30, 20, 1, 0, 60.416666666666671, '', 0, 0, 1, '', 0, '', NULL, 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2597, '', 'CUCINACS1', 'CUCINA VIVIANA C/TAV./6 SEDIE', 1154.4000000000001, 20, 1, 0, 2780, '', 0, 0, 1, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2576, '', 'LAVELLO1206', 'LAVELLO 116 X 50 2V + G FOSTER', 44.5, 20, 1, 0, 88.75, '', 0, 0, 1, '', 0, '', NULL, 359, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2586, '', 'LETTO45', 'LETTO CON CONTEN. MOD.EURO', 506.55000000000001, 20, 1, 0, 1062.0833333333335, '', 0, 0, 1, '', 0, '', NULL, 196, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2554, '', 'MENSOLA9', 'MENSOLA BORDATA', 30.5, 20, 1, 0, 68.333333333333343, '', 0, 0, 1, '', 0, '', NULL, 344, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2581, '', 'POLTRONA50', 'POLTRONCINA MAYA', 227, 20, 1, 0, 476.25, '', 0, 0, 1, '', 0, '', NULL, 196, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2606, '', 'MENSOLA10', 'MENSOLA IONE PORTA CD', 117.5, 20, 1, 0, 225.83333333333343, '', 0, 0, 1, '', 0, '', NULL, 344, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2638, '', 'POLTRONA54', 'POLTRONA GIORGIA CHIUSA CAT.B', 144.55000000000001, 20, 1, 0, 320.83333333333337, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2625, '', 'SOGGIORNO38', 'SOGGIORNO CILIEGIO C/TAVOLO/4SEDIE', 1163.7, 20, 1, 0, 2587.5, '', 0, 0, 1, '', 0, '', NULL, 243, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2642, '', 'SOGGIORNO39', 'SOGGIORNO OLD STYLE', 1979.0999999999999, 20, 1, 0, 4191.666666666667, '', 0, 0, 1, '', 0, '', NULL, 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2616, '', 'TESSUTO1', 'COPRIMATERASSO E POGGIASCHIENA', 46.5, 20, 1, 0, 129.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2627, '', 'TESSUTO2', 'COPRIMATERASSO E POGGIASCHIENA', 98.5, 20, 1, 0, 0, '', 0, 0, 1, '', 0, '', NULL, 209, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2626, NULL, 'POLTRONA53', 'POLTRONA ACTIVA TOP MASSAGE', 1250, 20, 1, 3000, 2500, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 370, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2669, '', 'CUCINALA4A', 'PC 70 4GAS + PESC.', 264.94999999999999, 20, 1, 0, 530, '', 0, 0, 1, '', 0, '', NULL, 177, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2681, '', 'DIVANO129', 'DIVANO 2PF LAURA CAT.B', 253, 20, 1, 0, 641.66666666666674, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2647, '', 'INGRESSO6A', 'COLONNA APP.TO VETRO-MARMO', 111.5, 20, 1, 0, 275, '', 0, 0, 1, '', 0, '', NULL, 27, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2689, '', 'LAVELLO802', 'INOX 86 X 50 2 VASCHE', 49.100000000000001, 20, 1, 0, 136.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2686, '', 'LETTO47', 'LETTO FUSHIDO2', 909.29999999999995, 20, 1, 0, 1804.1666666666667, '', 0, 0, 1, '', 0, '', NULL, 308, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2679, '', 'SALOTTO17', 'SALOTTO SARA C/PENISOLA CAT.B', 636, 20, 1, 0, 1404.1666666666674, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2684, '', 'TAVOLO45', 'TAVOLO 130 X 80 ALL.', 95.549999999999997, 20, 1, 0, 287.08333333333337, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2680, NULL, 'DIVANO128', 'DIVANO 3PF LAURA CAT.B', 297, 20, 1, 891, 742.5, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2698, '', 'ARMADIO40', 'ARMADIO 2A SCORR.', 1443.9000000000001, 20, 1, 0, 2975, '', 0, 0, 1, '', 0, '', NULL, 383, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2699, '', 'COMO11', 'COMO'' CLASS', 345.55000000000001, 20, 1, 0, 708.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 383, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2732, '', 'DIVANO132', 'DIVANO 2PF KATTY', 140, 20, 1, 0, 341.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 392, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2730, '', 'GRUPPOLETTO4', 'GRUPPOLETTO 11/M', 290, 20, 1, 0, 625, '', 0, 0, 1, '', 0, '', NULL, 392, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2706, '', 'LAVELLO1207', 'LAVELLO 116 X 50 SX-DX', 44, 20, 1, 0, 98.333333333333343, '', 0, 0, 1, '', 0, '', NULL, 359, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2716, '', 'SEDIA99', 'SEDIA STOFFA', 48.600000000000001, 20, 1, 0, 108.33333333333334, '', 0, 0, 1, '', 0, '', NULL, 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2700, '', 'SPECCHIERA32', 'SPECCHIERA CLASS', 82.150000000000006, 20, 1, 0, 170.83333333333331, '', 0, 0, 1, '', 0, '', NULL, 383, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2714, NULL, 'SOGGIORNO40', 'SOGGIORNO FLO'' CILIEGIO', 2058.75, 20, 1, 5490, 4575, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2715, NULL, 'TAVOLO48', 'TAVOLO QUADR.ALL. FINESTRATO', 425.25, 20, 1, 1134, 945, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2773, '', 'CAMERETTA43', 'CAMERETTA COMPONIBILE-M76013AB', 608.29999999999995, 20, 1, 0, 1258.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 394, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2774, '', 'CAMERETTA44', 'CAMERETTA COMPONIBILE-M76022AG', 588.39999999999998, 20, 1, 0, 1200, '', 0, 0, 1, '', 0, '', NULL, 394, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2765, '', 'GUANCIALI15', 'GUANCIALE ECO', 4, 20, 1, 0, 9.1666666666666679, '', 0, 0, 1, '', 0, '', NULL, 399, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2743, '', 'MATERASSO80', 'MATERASSO 160 X 190 ECOCLASSIC', 279.39999999999998, 20, 1, 0, 639.58333333333337, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2744, '', 'MATERASSO82A', 'MATERASSO 160 X 190 OASI M.R.', 154.55000000000001, 20, 1, 0, 309.16666666666669, '', 0, 0, 7, '', 0, '', NULL, 400, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2746, '', 'MATERASSO83', 'MATERASSO 80 X 190 ALIANTE', 57.799999999999997, 20, 1, 0, 133.3333333333334, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2738, '', 'RETE39', 'RETE 160 X 190 DOGHE ORTOP.', 75.049999999999997, 20, 1, 0, 175, '', 0, 0, 1, '', 0, '', NULL, 335, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2739, '', 'RETE40', 'RETE 80 X 190 DOGHE ORTOP.', 37.549999999999997, 20, 1, 0, 100.83333333333334, '', 0, 0, 1, '', 0, '', NULL, 335, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2741, '', 'RETE42', 'RETE 80 X 190 DOGAL', 32.299999999999997, 20, 1, 0, 75, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2753, '', 'SEDIA102', 'SEDIA 9001 LIGURIA PAG21 CILIEGIO', 9.8000000000000007, 20, 1, 0, 33.333333333333329, '', 0, 0, 1, '', 0, '', NULL, 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2757, '', 'SEDIA104', 'SEDIA 9002 FRIULI PAG21 CILIEGIO', 14.9, 20, 1, 0, 47.5, '', 0, 0, 1, '', 0, '', NULL, 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2758, '', 'SEDIA105', 'SEDIA G020 GIREVOLE F1CP WA01', 24.100000000000001, 20, 1, 0, 50, '', 0, 0, 1, '', 0, '', NULL, 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2778, '', 'SPECCHIERA33', 'SPECCHIERA CORA', 66.799999999999997, 20, 1, 0, 141.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 196, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2769, '', 'TAVOLINO58', 'TAVOLINO', 75, 20, 1, 0, 166.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 399, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2786, NULL, 'TAVOLO49', 'TAVOLO 90 X 160 X 250 MICHELANGELO', 500, 20, 1, 1575, 1312.5, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2784, '', 'ARGENTIERA7', 'ARGENTIERA 3P MICHELANGELO', 876, 20, 1, 0, 2300, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2819, '', 'CAMERA60', 'CAMERA TIZIANO', 3830, 20, 1, 0, 8583.3333333333321, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2785, '', 'CREDENZONE2', 'CREDENZONE 2P 4C MICHELANGELO', 653, 20, 1, 0, 1716.6666666666667, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2801, '', 'POLTRONA54A', 'POLTRONA GIORGIA APERTA CAT.B', 136.40000000000001, 20, 1, 0, 304.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2802, '', 'POLTRONA54B', 'POLTRONA GIORGIA CHIUSA CAT.C', 148.80000000000001, 20, 1, 0, 330.83333333333337, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2790, '', 'SEDIA109', 'SEDIA IMBOTTITA MICHELANGELO', 90.25, 20, 1, 0, 241.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 111, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2848, '', 'CAMERA61', 'CAMERA KELLY', 638.25, 20, 1, 0, 1716.6666666666667, '', 0, 0, 1, '', 0, '', NULL, 407, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2844, '', 'MATERASSO85', 'MATERASSO 160 X 190 ECOMIX', 422.19999999999999, 20, 1, 0, 916.66666666666674, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2832, '', 'POLTRONA62', 'POLTRONA KOBRA IN PELLE', 379.60000000000002, 20, 1, 0, 759.16666666666674, '', 0, 0, 3, '', 0, '', NULL, 34, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2845, '', 'RETE44', 'RETE 160 X 190 BASIC C/PIEDI', 134.94999999999999, 20, 1, 0, 316.66666666666686, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2826, '', 'SEDIA114', 'SEDIA SE755 CROMO', 285.19999999999999, 20, 1, 0, 591.66666666666674, '', 0, 0, 1, '', 0, '', NULL, 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2831, '', 'TAVOLINO61', 'TAVOLINO PM802 110X55 PORTATV', 237, 20, 1, 0, 491.66666666666686, '', 0, 0, 1, '', 0, '', NULL, 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2841, NULL, 'SOGGIORNO43', 'SOGGIORNO SYSTEM BOX', 1014.75, 20, 1, 2814, 2345, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2847, NULL, 'GUANCIALI20', 'GUANCIALE MEMORY FOAM', 26, 20, 1, 67.799999999999997, 56.5, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2854, NULL, 'CAMERA62', 'CAMERA MAXIM', 1846, 20, 1, 5160, 4300, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 196, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2893, '', 'ZCOMODINO2', 'COMODINO', 73, 20, 1, 0, 145.83333333333334, '', 0, 0, 1, '', 0, '', NULL, 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2894, '', 'ZCONTROMOBILE', 'CONTROMOBILE', 1100, 20, 1, 0, 2208.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2895, '', 'ZCREDENZINA', 'CREDENZINA C/RIBALTA', 360, 20, 1, 0, 720.83333333333337, '', 0, 0, 1, '', 0, '', NULL, 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2889, '', 'ZMENSOLA', 'MENSOLA CM.170', 42, 20, 1, 0, 83.333333333333343, '', 0, 0, 1, '', 0, '', NULL, 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2899, '', 'ZOCCOLO3', 'ZOCCOLO PERSONALIZZATO CM.135', 10.75, 20, 1, 0, 21.25, '', 0, 0, 1, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2900, '', 'ZPIANTANA', 'PIANTANA', 70, 20, 1, 0, 141.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2891, NULL, 'ZCOMODINO', 'COMODINO', 30, 20, 1, 72, 60, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2896, NULL, 'ZINGRESSO', 'INGRESSO 3 PZ.', 250, 20, 1, 600, 500, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2898, NULL, 'ZSCRIVANIA', 'SCRIVANIA C/LIBRERIA', 300, 20, 1, 720, 600, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2901, NULL, 'ZPORTAPC', 'PORTAPC', 150, 20, 1, 360, 300, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2911, NULL, 'ZTAVOLO', 'TAVOLO', 200, 20, 1, 480, 400, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2912, NULL, 'ZSPECCHIERA', 'SPECCHIERA', 50, 20, 1, 120, 100, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2913, NULL, 'ZSEDIA2', 'SEDIA', 20, 20, 1, 48, 40, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 408, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2960, '', 'CUCINACS6', 'CUCINA SONIA', 1692.45, 20, 1, 0, 0, '', 0, 0, 1, '', 0, 'ACQUISTI X €. 2.790,00
VENDUTI  X €. 1.692,45
RIMANENZA = €. 1.097,55 IN CUCINACS6A', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2949, '', 'CAMERETTA47', 'CAMERETTA SANNI', 407.35000000000002, 20, 1, 0, 1071.6666666666667, '', 0, 0, 1, '', 0, '', NULL, 265, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2948, '', 'COMODINO3AA', 'COMODINO L9 1/CASS.', 24.350000000000001, 20, 1, 0, 61.666666666666671, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2954, '', 'DIVANO137', 'DIVANO 3PF KOALA PELLE', 350, 20, 1, 0, 866.66666666666674, '', 0, 0, 1, '', 0, '', NULL, 412, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2955, '', 'DIVANO138', 'DIVANO 2PF KOALA PELLE', 300, 20, 1, 0, 666.66666666666674, '', 0, 0, 1, '', 0, '', NULL, 412, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2942, '', 'SETTIMINO3', 'SETTIMINO', 248, 20, 1, 0, 495.83333333333337, '', 0, 0, 1, '', 0, '', NULL, 409, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2986, '', 'DIVANO143', 'DIVANO 2PF MINUETTO C/FRANGIA CAT.E', 514.54999999999995, 20, 1, 0, 1364.1666666666665, '', 0, 0, 1, '', 0, '', NULL, 255, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2987, '', 'DIVANO144', 'DIVANO 3PF MINUETTO C/FRANGIA CAT.E', 601.54999999999995, 20, 1, 0, 1596.6666666666667, '', 0, 0, 1, '', 0, '', NULL, 255, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2997, '', 'DIVANO145', 'DIVANO 140 ECO CLELIA', 174, 20, 1, 0, 443.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2991, '', 'LAVELLO902', 'LAVELLO 86 X 50 2V. INOX', 46.100000000000001, 20, 1, 0, 127.5, '', 0, 0, 1, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2999, '', 'LETTO14A', 'LETTO A CASTELLO', 62, 20, 1, 0, 125, '', 0, 0, 4, '', 0, '', NULL, 154, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3007, '', 'RETE37A', 'RETE 160 X 190 ORT. DOGHE LEGNO', 44.399999999999999, 20, 1, 0, 112.5, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3001, '', 'RETE46', 'RETE 80 X 190 ORTOP. DOGHE', 21.699999999999999, 20, 1, 0, 54.583333333333357, '', 0, 0, 1, '', 0, '', NULL, 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2972, '', 'SOGGIORNO46', 'SOGGIORNO COMPONIBILE', 244.44999999999999, 20, 1, 0, 504.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 394, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3016, NULL, 'TAVOLINO62', 'TAVOLINO A.3751390', 346.5, 20, 1, 924, 770, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3019, NULL, 'POLTRONA63', 'POLTRONA A.4615399', 85.5, 20, 1, 216, 180, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3031, '', '560753', 'COL.1ANTA 30P31H117', 28.300000000000001, 20, 1, 0, 53.329999999999998, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3032, '', '560761', 'SOTTOL.2A+3C 81P34H86', 51.200000000000003, 20, 1, 0, 96.670000000000002, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3024, '', '561551', 'COL.2AN+1CA 30P31H191', 44.799999999999997, 20, 1, 0, 84.170000000000002, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3025, '', '561562', 'SOTTOL.2A+3C 85P36H88', 50.700000000000003, 20, 1, 0, 95, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3086, '', 'CAMERA64', 'CAMERA PLANA', 1079, 20, 1, 0, 1079, '', 0, 0, 1, '', 0, '', NULL, 243, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3056, '', '5721TP01', 'RACCORDO X SCRIV.75X60', 17.350000000000001, 20, 1, 0, 32.5, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3095, '', 'CAMERA63A', 'COMODINI,COMO'',SPECCHIO', 222.30000000000001, 20, 1, 0, 494.16666666666697, '', 0, 0, 1, '', 0, '', NULL, 243, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3096, '', 'CAMERA65', 'CAMERA FLORA', 1340.25, 20, 1, 0, 3125, '', 0, 0, 1, '', 0, '', NULL, 196, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3072, '', 'CREDENZA16', 'CREDENZA 2A/6C ROSSINI', 813.14999999999998, 20, 1, 0, 1808.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 358, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3068, '', 'POLTRONA65A', 'POLTRONA A.3608399', 121.59999999999999, 20, 1, 0, 256.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3089, '', 'RETE40A', 'RETE 80 X 190 DOGHE ATOS', 37.25, 20, 1, 0, 91.666666666666671, '', 0, 0, 1, '', 0, '', NULL, 335, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3074, '', 'SEDIA121', 'SEDIA TESSUTO', 104.40000000000001, 20, 1, 0, 232.5, '', 0, 0, 1, '', 0, '', NULL, 358, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3076, '', 'SOGGIORNO47', 'SOGGIORNO STILO', 714.14999999999998, 20, 1, 0, 1587.5, '', 0, 0, 1, '', 0, '', NULL, 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3073, '', 'TAVOLO59', 'TAVOLO CM 160 ALL.', 448.64999999999998, 20, 1, 0, 997.5, '', 0, 0, 1, '', 0, '', NULL, 358, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3075, NULL, 'VETRINA15', 'VETRINA 2A ROSSINI', 1116, 20, 1, 2976, 2480, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 358, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3085, NULL, 'TAVOLO41A', 'TAVOLO RETT. NOCE', 215.30000000000001, 20, 1, 552, 460, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 243, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3123, '', '584411', 'LIBRERIA 5VANI 61,5P27H185', 40, 20, 1, 0, 75, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3118, '', '584421', 'PORTA PC 1A+1C 141P61H73', 81.900000000000006, 20, 1, 0, 154.16999999999999, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3122, '', '584451', 'COMODINO 1C', 28.300000000000001, 20, 1, 0, 53.329999999999998, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3121, '', '584456', 'LETTO SINGOLO', 77.349999999999994, 20, 1, 0, 145, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3111, '', '584471', 'SPECC 1AN 84,5P17H98,5', 42.149999999999999, 20, 1, 0, 79.170000000000002, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3112, '', '584472', 'SOTTOL. 2A+3C 85P36H84', 54.950000000000003, 20, 1, 0, 103.33, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3113, '', '995578', 'LAMPADA SPECCHIO BAGNO DOMUS', 8, 20, 1, 0, 15, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3100, '', 'CAMERA66', 'CAMERA CAMELIA', 1405.8499999999999, 20, 1, 0, 3004.166666666667, '', 0, 0, 1, '', 0, '', NULL, 243, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3139, '', 'GRUPPOLETTO2A', 'COMODINO RAFFAELLO TOP MARMO', 205.19999999999999, 20, 1, 0, 458.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 243, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3109, '', 'LAVELLO901V', 'LAVELLO 90 1 VASCA', 61.049999999999997, 20, 1, 0, 145.83333333333331, '', 0, 0, 1, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3143, '', 'LAVELLO901VA', 'LAVELLO 90 1 VASCA', 39.25, 20, 1, 0, 116.6666666666667, '', 0, 0, 1, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3102, '', 'PC604G3', 'PC 60 4G ACC.EL.', 68.200000000000003, 20, 1, 0, 162.5, '', 0, 0, 2, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3176, '', '455112', 'PORTATV 101.6X60X50.5', 61.600000000000001, 20, 1, 0, 127.5, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3177, '', '455113', 'PORTATV 101.6X60X52.5', 91.650000000000006, 20, 1, 0, 189.16999999999999, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3178, '', '455114', 'TAVOLINO 101.6X60X39', 72.25, 20, 1, 0, 150, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3179, '', '455115', 'ALZATATV 80X51X15', 19.899999999999999, 20, 1, 0, 41.670000000000002, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3182, '', '783380', 'PENISOLA 110X69X73', 46.950000000000003, 20, 1, 0, 88.329999999999998, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3157, '', 'ARMADIO45', 'ARMADIO', 1215.8499999999999, 20, 1, 0, 2550, '', 0, 0, 1, '', 0, '', NULL, 196, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3166, '', 'MATERASSO90', 'MATERASSO 160 X 190 TREND PLUS', 125.7, 20, 1, 0, 294.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 417, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3167, '', 'MATERASSO91', 'MATERASSO 160 X 190 TREND BEIGE', 104.25, 20, 1, 0, 247.49999999999997, '', 0, 0, 1, '', 0, '', NULL, 417, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3170, '', 'RETE48', 'RETE 160 X 190 VENUS 6 DOGA LARGA', 67.799999999999997, 20, 1, 0, 158.33333333333334, '', 0, 0, 1, '', 0, '', NULL, 417, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3189, NULL, 'LETTO57', 'LETTO MATRIM. GABBIANO/P', 77, 20, 1, 222, 185, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 384, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3207, '', '760741', 'SCRITTOIO', 57.25, 20, 1, 0, 118.34, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3200, '', '980986', 'SCALETTA X LETTO CASTELLO', 28.350000000000001, 20, 1, 0, 55.829999999999998, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3192, '', 'DIVANO148', 'DIVANO LETTO HAPPY', 119, 20, 1, 0, 266.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 418, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3213, '', 'MATERASSO94', 'MATERASSO 80 X 190 SMART', 58.450000000000003, 20, 1, 0, 127.5, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3214, '', 'MATERASSO95', 'MATERASSO 80 X 190 GILDA', 46.5, 20, 1, 0, 93.333333333333343, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3215, '', 'MATERASSO96', 'MATERASSO 80 X 190 STAMPATO', 23.25, 20, 1, 0, 46.666666666666671, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3212, '', 'RETE41A', 'RETE 160 X 190 TROPEA DOGHE', 83.099999999999994, 20, 1, 0, 183.3333333333334, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3210, '', 'RETE49', 'RETE 80 X 190 DOGHE', 23, 20, 1, 0, 75.833333333333343, '', 0, 0, 1, '', 0, '', NULL, 419, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3202, '', 'SEDIA125', 'SEDIA LEGNO', 36, 20, 1, 0, 75, '', 0, 0, 1, '', 0, '', NULL, 394, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3206, '', 'MATERASSO93', 'MATERASSO 80 X 190 VASSALLO', 28.949999999999999, 20, 1, 0, 58.3333333333333, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3266, '', 'GUANCIALI14A', 'GUANCIALE WATERLILY NINFEA', 21.550000000000001, 20, 1, 0, 0, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3268, '', '584403A', 'ARM. 3A+2A 127P54H210', 143.25, 20, 1, 0, 268.33999999999997, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3248, '', 'DIVANO149', 'DIVANO 2P PELLE', 243, 20, 1, 0, 666.66666666666674, '', 0, 0, 1, '', 0, '', NULL, 425, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3249, '', 'DIVANO150', 'DIVANO 3P PELLE', 317, 20, 1, 0, 866.66666666666674, '', 0, 0, 1, '', 0, '', NULL, 425, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3251, '', 'MATERASSO98', 'MATERASSO 80 X 190 CASABLANCA', 69.75, 20, 1, 0, 139.58333333333334, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3272, '', 'MATERASSO99', 'MATERASSO 160 X 190 ALIANTE', 134.30000000000001, 20, 1, 0, 291.66666666666686, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3276, '', 'P1054', 'PINTDECOR CM.80 X 80', 96, 20, 1, 0, 191.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 426, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3274, '', 'P1162', 'PINTDECOR CM.60 X 140', 127.2, 20, 1, 0, 254.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 426, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3275, '', 'P1292', 'PINTDECOR CM.67 X 197', 138, 20, 1, 0, 275.83333333333337, '', 0, 0, 1, '', 0, '', NULL, 426, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3253, '', 'PORTABITO3', 'PORTABITO', 95.400000000000006, 20, 1, 0, 212.5, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3269, '', 'SEDIA127', 'SEDIA ELIZABETH', 41.450000000000003, 20, 1, 0, 98.333333333333343, '', 0, 0, 1, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3278, '', 'VETRINA16', 'VETRINA 4 ANTE', 1032, 20, 1, 0, 2414.166666666667, '', 0, 0, 1, '', 0, '', NULL, 66, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3285, '', '414262', 'PORTA PC + ALZATA', 46.149999999999999, 20, 1, 0, 86.670000000000002, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3305, '', 'CAMERETTA51', 'CAMERETTA A DU2 COLONNE', 307, 20, 1, 0, 764.16999999999996, '', 0, 0, 1, '', 0, '', NULL, 394, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3279, '', 'BUFFET', 'BUFFET 4 ANTE', 654.10000000000002, 20, 1, 0, 1530, '', 0, 0, 1, '', 0, '', NULL, 66, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3310, '', 'CAMERA70', 'CAMERA ALICE', 1850.3499999999999, 20, 1, 0, 3708.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 429, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3299, NULL, 'DIVANO151', 'DIVANO 2PF IS200 1011', 387.60000000000002, 20, 1, 1224, 1020, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 180, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3300, NULL, 'DIVANO152', 'DIVANO 3PF IS200 1011', 480.69999999999999, 20, 1, 1518, 1265, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 180, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3303, '', 'CAMERETTA50', 'CAMERETTA SOPPALCO', 344, 20, 1, 0, 860, '', 0, 0, 1, '', 0, '', NULL, 394, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3301, '', 'MATERASSO81A', 'MATERASSO 160 X 190 ERGOMASSAGE', 302.94999999999999, 20, 1, 0, 634.16666666666674, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3306, NULL, 'LETTO61', 'LETTO TURCA RETE DOGHE', 94, 20, 1, 282, 235, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 394, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3280, '', 'TAVOLO65', 'TAVOLO ALLUNGABILE', 435.19999999999999, 20, 1, 0, 1018.3333333333337, '', 0, 0, 1, '', 0, '', NULL, 66, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3315, '', 'DIVANO153', 'DIVANO 3PL IVANA', 235, 20, 1, 0, 470, '', 0, 0, 3, '', 0, '', NULL, 328, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3309, NULL, 'GRUPPOLETTO5', 'GRUPPOLETTO', 1752.05, 20, 1, 4920, 4100, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 428, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3314, NULL, 'TAVOLINO64', 'TAVOLINO 3750300', 220.5, 20, 1, 588, 490, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3323, NULL, 'LETTO62', 'LETTO CONCERTO', 400.5, 20, 1, 1224, 1020, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 384, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3327, NULL, 'CRISTALLIERA2', 'CRISTALLIERA 2P', 310, 20, 1, 1032, 860, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 432, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3365, '', 'ARMADIO47', 'ARMADIO 2ANTE AURORA', 1534.5, 20, 1, 0, 3410, '', 0, 0, 1, '', 0, '', NULL, 415, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3334, '', 'CAMERA71', 'CAMERA LINDA', 500, 20, 1, 0, 1333.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3340, '', 'CUCINACSP44A', 'PC.60 4GAS C/ACC.', 60.399999999999999, 20, 1, 0, 184.16666666666663, '', 0, 0, 1, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3361, '', 'CUCINACSP44A1', 'PC.60 4GAS C/ACC.', 57.049999999999997, 20, 1, 0, 184.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3332, '', 'MATERASSO100', 'MATERASSO 160 X 190 DOLCEVITA BOX', 114, 20, 1, 0, 228.33333333333331, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3335, '', 'MATERASSO102', 'MATERASSO 160 X 190 SMART', 116.95, 20, 1, 0, 254.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 400, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3329, '', 'POLTRONA69', 'POLTRONA DENISE', 600, 20, 1, 0, 1525, '', 0, 0, 1, '', 0, '', NULL, 433, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3364, '', 'SEDIA130', 'SEDIA PISA 45', 10.300000000000001, 20, 1, 0, 20.833333333333336, '', 0, 0, 2, '', 0, '', NULL, 434, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3330, '', 'TAVOLINO65', 'TAVOLINO 3774796', 269.10000000000002, 20, 1, 0, 598.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3351, '', 'TAVOLO38A', 'TAVOLO 90 X 90 LIBRO', 100.7, 20, 1, 0, 201.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3363, '', 'TAVOLO69', 'TAVOLO 90 X 60 +30+30 CROMO VETRO', 196.05000000000001, 20, 1, 0, 562.5, '', 0, 0, 1, '', 0, '', NULL, 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3368, '', 'VETRINA17', 'VETRINA CURVA', 266.89999999999998, 20, 1, 0, 659.16666666666674, '', 0, 0, 1, '', 0, '', NULL, 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3354, NULL, 'LAVELLO1202B', 'LAVELLO ONDA FRANKE 116', 66.650000000000006, 20, 1, 168, 140, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 5, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3369, NULL, 'LAMPADA3', 'LAMPADA INTERNA', 34.450000000000003, 20, 1, 102, 85, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3397, '', 'ARMADIO51', 'ARMADIO 2A C/CASS.H.185', 86, 20, 1, 0, 162.5, '', 0, 0, 1, '', 0, '', NULL, 436, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3391, '', 'ARMADIO48', 'ARMADIO 2A H.200', 62.5, 20, 1, 0, 133.33333333333334, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3392, '', 'ARMADIO49', 'ARMADIO 2A C/CASS. H.200', 70.549999999999997, 20, 1, 0, 150, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3374, '', 'CAMERA73', 'CAMERA DA LETTO', 1901.5, 20, 1, 0, 5283.3333333333339, '', 0, 0, 1, '', 0, '', NULL, 429, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3370, '', 'CREDENZA17', 'CREDENZA', 442.25, 20, 1, 0, 1093.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 101, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3400, '', 'LETTO64', 'LETTO MATRIM.', 49.700000000000003, 20, 1, 0, 94.166666666666671, '', 0, 0, 1, '', 0, '', NULL, 436, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3372, '', 'MATERASSO107', 'MATERASSO 160 X 190 GILDA', 92.950000000000003, 20, 1, 0, 185.83333333333334, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3394, '', 'MATERASSO107A', 'MATERASSO 160 X 190 GILDA', 88.700000000000003, 20, 1, 0, 185.8333333333334, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3395, '', 'MATERASSO95A', 'MATERASSO 80 X 190 GILDA', 44.350000000000001, 20, 1, 0, 93.333333333333343, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3383, '', 'RUBINETTO18', 'MISCELATORE CROMO', 14.75, 20, 1, 0, 28.333333333333336, '', 0, 0, 1, '', 0, '', NULL, 359, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3389, NULL, 'SEDIA129A', 'SEDIA SE02', 26.649999999999999, 20, 1, 66, 55, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3401, NULL, 'COMODINO13', 'COMODINO 2/CASS.', 26.199999999999999, 20, 1, 60, 50, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 436, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3438, '', 'CAMERA74', 'CAMERA MILLENIA', 3186.3499999999999, 20, 1, 0, 6680, '', 0, 0, 1, '', 0, '', NULL, 196, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3449, '', 'CAMERA75', 'CAMERA ATMOSFERE', 1500.0999999999999, 20, 1, 0, 4354.166666666667, '', 0, 0, 1, '', 0, '', NULL, 429, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3450, '', 'CAMERA76', 'CAMERA MAXIM', 2135.0500000000002, 20, 1, 0, 4475.8333333333339, '', 0, 0, 1, '', 0, '', NULL, 196, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3452, '', 'CAMERETTA52', 'CAMERETTA CABINA', 1384.3499999999999, 20, 1, 0, 2898.3333333333335, '', 0, 0, 1, '', 0, '', NULL, 294, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3415, '', 'DIVANO156', 'DIVANO ROMANTICA LETTO 120 CAT.B BR.6', 535, 20, 1, 0, 1304.1666666666667, '', 0, 0, 1, '', 0, '', NULL, 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3439, '', 'GRULET', 'GRUPPO LETTO LUNA', 706.79999999999995, 20, 1, 0, 1666.6666666666665, '', 0, 0, 1, '', 0, '', NULL, 196, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3441, '', 'GUANCIALI24', 'GUANCIALI IN FIBRA FLORIDA', 2.7999999999999998, 20, 1, 0, 5.8333333333333339, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3440, '', 'POLTRONA71', 'POLTRONA 417/P GINA', 36.75, 20, 1, 0, 73.75, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3421, '', 'MATERASSO93A', 'MATERASSO 80 X 190 VASSALLO', 29.300000000000001, 20, 1, 0, 58.3333333333333, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3448, '', 'CUCFLO', 'CUCINA MOD. PRATICA ML.3,60', 1517.8499999999999, 20, 1, 0, 3604.1666666666697, '', 0, 0, 1, '', 0, '', NULL, 430, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3454, '', 'CUCFLO1', 'CUCINA REFLEX', 1840.6500000000001, 20, 1, 0, 4370, '', 0, 0, 1, '', 0, '', NULL, 430, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3501, '', 'CUCINACS1C', 'COMPL.TO FIANCO-TOP-ALZATA', 72.599999999999994, 20, 1, 0, 201.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3481, '', 'DIVANO157A', 'DIVANO 2PF ECOPELLE', 116, 20, 1, 0, 225, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3464, '', 'FORNO28', 'FORNO COUNTRY AVENA FRANKE', 270, 20, 1, 0, 677.5, '', 0, 0, 1, '', 0, '', NULL, 5, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3465, '', 'LAVELLO12010', 'LAVELLO CALYPSO AVENA FRANKE', 170, 20, 1, 0, 407.5, '', 0, 0, 1, '', 0, '', NULL, 5, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3467, NULL, 'RUBINETTO20', 'MISCELATORE CANNA ALTA IDRONO', 15, 20, 1, 48, 40, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 5, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3468, NULL, 'RUBINETTO21', 'MISCELATORE DOMINOX', 10, 20, 1, 30, 25, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 5, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3469, NULL, 'RUBINETTO22', 'MISCELATORE C/DOCCIA DOMINOX', 15, 20, 1, 48, 40, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 5, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3482, '', 'MATERASSO107B', 'MATERASSO 160 X 190 GILDA', 92.400000000000006, 20, 1, 0, 185.83333333333334, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3462, '', 'PC70A', 'P.C. 70 TREND AVENA FRANKE', 235, 20, 1, 0, 592.5, '', 0, 0, 1, '', 0, '', NULL, 5, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3491, '', 'QUADRO31', 'QUADRO 35X50', 8, 20, 1, 0, 16.666666666666668, '', 0, 0, 1, '', 0, '', NULL, 104, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3466, '', 'RUBINETTO19', 'MISCELATORE PLATINO AVENA FRANKE', 45, 20, 1, 0, 115.83333333333334, '', 0, 0, 1, '', 0, '', NULL, 5, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3480, NULL, 'DIVANO158A', 'DIVANO 3PF ECOPELLE', 136, 20, 1, 312, 260, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3487, NULL, 'QUADRO27', 'QUADRO 30X40', 20, 20, 1, 48, 40, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 104, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3488, NULL, 'QUADRO28', 'QUADRO 50X70', 50, 20, 1, 120, 100, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 104, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3489, NULL, 'QUADRO29', 'QUADRO 60X90', 120, 20, 1, 288, 240, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 104, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3490, NULL, 'QUADRO30', 'QUADRO 90X120', 100, 20, 1, 240, 200, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 104, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3492, NULL, 'QUADRO32', 'QUADRO 30X40', 30, 20, 1, 72, 60, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 104, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3493, NULL, 'SPECCHIERA36', 'SPECCHIERA 60X80', 120, 20, 1, 288, 240, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 104, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3502, NULL, 'DIVANO112E', 'DIVANO SOLE 2PF CAT.B', 166.15000000000001, 20, 1, 426, 355, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 328, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3547, '', 'SALOTTO19', 'SALOTTO VICTOR', 0, 20, 1, 0, 0, '', 0, 0, 1, '', 0, 'NS. DDT 19/2007 DEL 020707 RESO A FORNITORE
', NULL, 441, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3516, '', 'ARMADIO48A', 'ARMADIO 2A H.200', 65.099999999999994, 20, 1, 0, 130, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3508, '', 'CORNICE1', 'CORNICE ML.5,07', 80.650000000000006, 20, 1, 0, 192.5, '', 0, 0, 1, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3528, '', 'CUCINACS10', 'CUCINA OPERA ML.3', 1826.45, 20, 1, 0, 5083.3333333333339, '', 0, 0, 1, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3526, '', 'CUCINACS8', 'CUCINA MADISON ML.3', 1042.5999999999999, 20, 1, 0, 2316.666666666667, '', 0, 0, 1, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3545, '', 'CUCINACS9A', 'PC 60 4 GAS ACC.ELETT.', 82.450000000000003, 20, 1, 0, 183.33333333333334, '', 0, 0, 1, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3532, '', 'DIVANO159', 'DIVANO 3P 2MECC PELLE 10', 803.70000000000005, 20, 1, 0, 1770.8333333333335, '', 0, 0, 1, '', 0, '', NULL, 180, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3533, '', 'DIVANO160', 'DIVANO 2PF PELLE 10', 624.14999999999998, 20, 1, 0, 1371.6666666666665, '', 0, 0, 1, '', 0, '', NULL, 180, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3515, '', 'MATERASSO101A', 'MATERASSO 160 X 190 VASSALLO', 60.899999999999999, 20, 1, 0, 121.66666666666666, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3509, '', 'P1658', 'APPENDIABITI 40 X 95', 54.5, 20, 1, 0, 95.833333333333343, '', 0, 0, 1, '', 0, '', NULL, 426, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3510, '', 'P1664', 'APPENDIABITI 40 X 95', 54.5, 20, 1, 0, 95.833333333333343, '', 0, 0, 1, '', 0, '', NULL, 426, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3511, '', 'P846', 'QUADRO 70 X 150', 91, 20, 1, 0, 167.5, '', 0, 0, 1, '', 0, '', NULL, 426, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3521, '', 'POLTRONA71A', 'POLTRONA ART.417/P GINA', 38.299999999999997, 20, 1, 0, 76.666666666666671, '', 0, 0, 1, '', 0, '', NULL, 420, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3542, NULL, 'DIVANO161', 'DIVANO 2PF LEGNO', 200, 20, 1, 480, 400, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 440, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3561, NULL, 'MENSOLA13', 'MENSOLA SOPRAP.L.104-SP.3-P.36', 23.949999999999999, 20, 1, 66, 55, NULL, 0, 0, 1, NULL, 0, NULL, '2007-05-15', 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3550, '', '45105N', 'TAVOLINO', 45.100000000000001, 20, 1, 0, 92.5, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3562, 'VALENTINI', '544206', 'CASSETTIERA', 60, 20, 1, 0, 120, '', 0, 0, 4, '', 0, '											', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3563, 'VALENTINI', '560770', 'SPECC.1AN.84.5P19H98.5 CIL.POM', 51.75, 20, 1, 0, 97.5, '', 0, 0, 4, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3026, '', '561571', 'SPECC.1AN 84,5P19H98,5', 44.799999999999997, 20, 1, 0, 84.170000000000002, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3030, '', '565071', 'SPECC.1AN 84,5P19H98,5', 44.799999999999997, 20, 1, 0, 84.170000000000002, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3552, '', '584466', 'CARRELLO TV 2ANTE 74X47X79', 58.399999999999999, 20, 1, 0, 109.17, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3564, 'VALENTINI', '584467A', 'CARR.TV 2A L94P54H70', 64, 20, 1, 0, 125.83, '', 0, 0, 4, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3027, '', '990422', 'LAVABO APP.RESINA 84X40 BIANCO', 43.200000000000003, 20, 1, 0, 81.670000000000002, '', 0, 0, 1, '', 0, '', NULL, 394, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3034, '', '990423', 'LAVABO APP.CERAMICA 86X50 BIANCO', 69.900000000000006, 20, 1, 0, 131.66, '', 0, 0, 1, '', 0, '', NULL, 394, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3565, '', 'ARMADIO52', 'ARMADIO N2A H.185', 62.399999999999999, 20, 1, 0, 125, '', 0, 0, 5, '', 0, '', NULL, 436, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3051, '', 'CAMERETTA48', 'CAMERETTA ICARO', 873.04999999999995, 20, 1, 0, 1837.5, '', 0, 0, 1, '', 0, '', NULL, 344, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3566, 'CIESSE', 'CUCINACS1A', 'BASE 45 CASS. VIVIANA', 107.95, 20, 1, 0, 216.66666666666669, '', 0, 0, 2, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3578, 'CIESSE', 'CUCINACS2', 'CUCINA ELIZABETH', 2624.9499999999998, 20, 1, 0, 5250, '', 0, 0, 2, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3579, 'CIESSE', 'CUCINACS4', 'CUCINE DEBORA-YLENIA', 1307.3, 20, 1, 0, 2616.666666666667, '', 0, 0, 2, '', 0, '', NULL, 363, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3576, 'CIESSE', 'CUCINACS4A', 'COMPLET. CUCINA YLENIA', 234.65000000000001, 20, 1, 0, 651.66666666666674, '', 0, 0, 2, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3567, 'CIESSE', 'CUCINACS7', 'CUCINA DEBORA', 1400, 20, 1, 0, 2800, '', 0, 0, 2, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3568, 'CIESSE', 'CUCINACSP53A', 'COMPLETAMENTO CUCINA PERS.', 189.69999999999999, 20, 1, 0, 378.33333333333337, '', 0, 0, 2, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3569, 'CIESSE', 'CUCINACSP55', 'CUCINA PERSONALIZZATA', 1326.5, 20, 1, 0, 2653.3333333333335, '', 0, 0, 2, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3570, 'CIESSE', 'CUCINACSP63', 'CUCINA PERSONALIZZATA', 659.10000000000002, 20, 1, 0, 1318.3333333333335, '', 0, 0, 2, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3571, 'CIESSE', 'CUCINACSP65A', 'COMPLETAMENTI E SOSTITUZIONI', 336.55000000000001, 20, 1, 0, 673.33333333333326, '', 0, 0, 2, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3572, '', 'LAVELLO1208', 'LAVELLO 116 X 50 FRANKE', 204.25, 20, 1, 0, 408.33333333333337, '', 0, 0, 2, '', 0, '', NULL, 240, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3047, '', 'LETTO54', 'LETTO FLAMENCO/P', 242.5, 20, 1, 0, 458.33333333333337, '', 0, 0, 1, '', 0, '', NULL, 384, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3573, 'DUEBI', 'MATERASSO108', 'MATERASSO 120 X 180 GILDA', 66.549999999999997, 20, 1, 0, 133.33333333333343, '', 0, 0, 7, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3043, '', 'POLTRONA65', 'POLTRONA A.3608399 F.72', 121.59999999999999, 20, 1, 0, 256.66666666666669, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3580, 'MAIELLARO', 'POLTRONA66', 'POLTRONA A.2609099 F.82', 111.15000000000001, 20, 1, 0, 234.16666666666669, '', 0, 0, 4, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3045, '', 'POLTRONA66A', 'POLTRONA A.3609099', 111.15000000000001, 20, 1, 0, 234.16666666666669, '', 0, 0, 1, '', 0, '', NULL, 39, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3574, '', 'QUADRO10', 'QUADRI ASSORTITI (PAESAGGI E SACRI)', 9.5, 20, 1, 0, 19.166666666666668, '', 0, 0, 8, '', 0, '', NULL, 104, 28);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3575, '', 'QUADRO26', 'QUADRI ASSORTITI', 7.5, 20, 1, 0, 15, '', 0, 0, 8, '', 0, '', NULL, 104, 15);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3581, 'ARTI', 'RUBINETTO6', 'RUBINETTO MONOFORO MAN.STELA CROMO', 12.5, 20, 1, 0, 25.833333333333336, '', 0, 0, 2, '', 0, '', NULL, 7, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3098, '', 'SEDIA102A', 'SEDIA 9001 PAGLIA CILIEGIO', 11.4, 20, 1, 0, 32.083333333333336, '', 0, 0, 1, '', 0, '', NULL, 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3048, '', 'SEDIA118', 'SEDIA GO13 F1CP NERO JS01', 26.649999999999999, 20, 1, 0, 50.833333333333329, '', 0, 0, 1, '', 0, '', NULL, 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3050, '', 'SEDIA120', 'SEDIA GO13 F1CP NERO NE01', 28.350000000000001, 20, 1, 0, 54.583333333333336, '', 0, 0, 1, '', 0, '', NULL, 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3582, 'TSB', 'SEDIA128', 'SEDIA', 77.799999999999997, 20, 1, 0, 182.0833333333334, '', 0, 0, 4, '', 0, '', NULL, 66, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3128, '', 'SOGGIORNO48', 'SOGGIORNO A PARETE COMP.1', 1392.5, 20, 1, 0, 3133.3333333333344, '', 0, 0, 1, '', 0, '', NULL, 243, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3583, 'PISANI', 'SPECCHIERA37', 'SPECCHIERA 60X80', 65, 20, 1, 0, 130, '', 0, 0, 9, '', 0, '', NULL, 104, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3577, '', 'TAPPETO9', 'TAPPETO 7501421', 76.5, 20, 1, 0, 155, '', 0, 0, 9, '', 0, '', NULL, 355, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3587, 'PXSST', 'POLTRONA73', 'POLTRONA RELAX MOD. SIESTA', 408, 20, 1, 0, 887.5, '', 0, 0, 3, '', 0, '	', NULL, 278, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3586, '', 'SOGGIORNOP', 'COLONNA L.60 COMPLETA DI RIPIANI', 90.900000000000006, 20, 1, 0, 202, '', 0, 0, 4, '', 0, 'FT.14961/300707', '2007-08-29', 101, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3589, 'PXARR', 'POLTRONA75', 'POLTRONA RELAX MOD. AURORA', 466, 20, 1, 0, 1012.5, '', 0, 0, 3, '', 0, '', NULL, 278, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3588, 'PX3KTT', 'POLTRONA74', 'POLTRONA RELAX KITTY ELETTR. C/ALZATA', 755, 20, 1, 0, 1640, '', 0, 0, 3, '', 0, '', '2007-08-30', 278, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3590, 'PZZ', 'PANNELLI', 'PANNELLI X ANTE SX DX', 150, 20, 1, 0, 0, '', 0, 0, 4, '', 0, 'SOSTITUZIONE SU ANTE ESPOSTE', '2007-08-30', 308, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3591, 'CAB703', 'ARMADIO53', 'ARMADIO DUCALE 4A PATINATO ', 728.29999999999995, 20, 1, 0, 1445, '', 0, 0, 4, '', 0, '', '2007-08-30', 308, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3592, '1049104', 'LETTO65', 'LETTO QUERIDA IN FERRO', 966.64999999999998, 20, 1, 0, 1850, '', 0, 0, 4, '', 0, '', '2007-08-30', 401, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3593, '1132', 'COMODINO14', 'COMODINO QUERIDA IN FERRO E CRISTALLO', 235.15000000000001, 20, 1, 0, 450, '', 0, 0, 4, '', 0, '', '2007-08-30', 401, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3594, 'ZR11C287', 'CUSCINO', 'CUSCINO CILINDRO', 70.549999999999997, 20, 1, 0, 135, '', 0, 0, 4, '', 0, '', '2007-08-30', 401, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3595, 'ZG31C287', 'SETLETTO', 'COPRILETTO E FEDERE', 253.40000000000001, 20, 1, 0, 485, '', 0, 0, 4, '', 0, '', '2007-08-30', 401, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3596, 'ZV02C287', 'CUSCINO1', 'CUSCINO LETTO 60 X 40', 39.200000000000003, 20, 1, 0, 75, '', 0, 0, 4, '', 0, '', '2007-08-30', 401, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3597, 'ZP02160', 'PIUMINO', 'PIUMINO DRACON', 70.549999999999997, 20, 1, 0, 135, '', 0, 0, 4, '', 0, '', '2007-08-30', 401, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3598, '', 'CUCINACSP66', 'CUCINA PERSONALIZZATA', 825.35000000000002, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3599, '', 'CUCINACSP67', 'CUCINA PERSONALIZZATA', 498.19999999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '	', '2007-10-01', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3600, '', 'CUCINACSP68', 'CUCINA PERSONALIZZATA', 56.899999999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-01', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3601, '', 'CUCINACSP69', 'CUCINA PERSONALIZZATA', 411.5, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-01', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3602, '', 'CUCINACSP70', 'BASE E PENSILE', 300.05000000000001, 20, 1, 0, 0, '', 0, 0, 2, '', 0, 'SOSTITUZIONI', NULL, 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3603, '', 'TOP8', 'TOP 60 X 180', 58.75, 20, 1, 0, 0, '', 0, 0, 2, '', 0, 'SOSTITUZIONI', '2007-10-01', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3604, 'TOSCA', 'CUCINACSP71', 'CUCINA PERSONALIZZATA', 2108.5, 20, 1, 0, 0, '', 0, 0, 2, '', 0, 'SOSTITUISCE MOD.DOLCE VITA', '2007-10-01', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3605, '', 'CUCINACSP72', 'CUCINA PERSONALIZZATA', 567.64999999999998, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-02', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3606, '', 'CUCINACSP73', 'CUCINA PERSONALIZZATA', 161.19999999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-02', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3607, '', 'CUCINACSP74', 'SOSTITUZIONE DOLCE VITA', 646.10000000000002, 20, 1, 0, 0, '', 0, 0, 2, '', 0, 'SOSTITUZIONE:
PENSILA 45 - PENSILE 60 - PANNELLO NOBILITATO - COLO. FORNO - CASSA X B.2 CASS.+CEST. - CASSA X B.90 LAVELLO.', '2007-10-02', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3608, '', 'CUCINACSP75', 'BASE E PENSILE', 48.149999999999999, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-02', 363, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3610, 'OALIORT090', 'MATERASSO109', 'MATERASSO 90 X 200 ALIANTE', 92.650000000000006, 20, 1, 0, 0, '', 0, 0, 6, '', 0, '', '2007-10-02', 400, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3611, 'PEGORT120', 'MATERASSO110', 'MATERASSO 120 X 190 PEGASO', 120.59999999999999, 20, 1, 0, 0, '', 0, 0, 6, '', 0, '', '2007-10-02', 400, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3612, 'ALIORT160', 'MATERASSO99A', 'MATERASSO 160 X 190 ALIANTE', 141.30000000000001, 20, 1, 0, 304.16666666666663, '', 0, 0, 6, '', 0, '', NULL, 400, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3629, '', 'LETTO68', 'LETTO A CASTELLO ARCO', 72.75, 20, 1, 0, 145.83333333333303, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3623, 'ART.261/V', 'CAMERETTA53', 'CAMERETTA PONTE SOFI''', 316.55000000000001, 20, 1, 0, 666.66666666666674, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3613, 'TRO160', 'RETE41B', 'RETE 160 X 190 TROPEA', 84.950000000000003, 20, 1, 0, 184.16666666666669, '', 0, 0, 9, '', 0, '', NULL, 400, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3614, 'MAX160', 'RETE50', 'RETE 160 X 190 MAXIMA', 120.05, 20, 1, 0, 260.83333333333337, '', 0, 0, 9, '', 0, '', NULL, 400, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3615, '', 'CUC2B', 'CUCINA MONICA MT.3', 730, 20, 1, 0, 1250, '', 0, 0, 2, '', 0, '', '2007-10-02', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3616, '', 'CUC2BP', 'CUCINA MONICA MT.2,55', 660, 20, 1, 0, 660, '', 0, 0, 2, '', 0, '', '2007-10-02', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3624, 'ART.251/V', 'ARMADIO55', 'ARMADIO 1A H.200 A.251/V', 55.850000000000001, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3618, '403/P', 'SEDIA137', 'SEDIA LIONE', 12.15, 20, 1, 0, 25, '', 0, 0, 2, '', 0, '', '2007-10-02', 420, 6);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3619, '326/H', 'DIVANO162', 'DIVANO 3P LETTO 326/H ', 134.69999999999999, 20, 1, 0, 270.83333333333337, '', 0, 0, 3, '', 0, '', '2007-10-02', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3620, 'ART.13', 'TAVOLO70', 'TAVOLO A.13 TOULIPIER 130 X 85', 100.05, 20, 1, 0, 0, '', 0, 0, 2, '', 0, '', '2007-10-02', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3621, 'ART.257/V', 'LETTO67', 'LETTO MATRIMONIALE 257/V', 63.649999999999999, 20, 1, 0, 0, '', 0, 0, 4, '', 0, '', '2007-10-02', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3622, '', 'MATERASSO101B', 'MATERASSO 160 X 190 VASSALLO', 65.650000000000006, 20, 1, 0, 133.33333333333334, '', 0, 0, 6, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3625, 'ART.258/V', 'COMODINO15', 'COMODINO 1 CASS. ART.258/V', 23.75, 20, 1, 0, 47.5, '', 0, 0, 5, '', 0, '', '2007-10-02', 420, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3627, '', 'MATERASSO111', 'MATERASSO 160 X 190 STAMPATO', 49.5, 20, 1, 0, 100, '', 0, 0, 6, '', 0, '', '2007-10-02', 420, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3626, '', 'MATERASSO96A', 'MATERASSO 80 X 190 STAMPATO', 24.800000000000001, 20, 1, 0, 50, '', 0, 0, 6, '', 0, '', NULL, 420, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3630, '', 'MATERASSO96A', 'MATERASSO 80 X 190 STAMPATO', 24.800000000000001, 20, 1, 0, 0, '', 0, 0, 6, '', 0, '', '2007-10-03', 420, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3631, '', 'MATERASSO93B', 'MATERASSO 80 X 190 VASSALLO', 32.850000000000001, 20, 1, 0, 66.666666666666671, '', 0, 0, 6, '', 0, '', '2007-10-03', 420, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3632, '', 'MATERASSO95B', 'MATERASSO 80 X 190 GILDA', 49.200000000000003, 20, 1, 0, 100, '', 0, 0, 6, '', 0, '', '2007-10-03', 420, 6);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3633, 'ART.455/U', 'TAVOLO71', 'TAVOLO 90 X 90 LIBRO MASS. A.455/U', 112.3, 20, 1, 0, 225, '', 0, 0, 2, '', 0, '', '2007-10-03', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3634, 'ART.321/H', 'SALOTTO20', 'SALOTTO 3 + 2 ECOPELLE BETTY', 266, 20, 1, 0, 540, '', 0, 0, 3, '', 0, '', '2007-10-03', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3617, '253/V', 'ARMADIO54', 'ARMADIO 2A C/C H.200 A.253/V', 76.950000000000003, 20, 1, 0, 154.166666666667, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3635, 'ART.254/V', 'SPECCHIO2', 'SPECCHIO ART.254/V', 28.5, 20, 1, 0, 58.333333333333329, '', 0, 0, 4, '', 0, '', '2007-10-03', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3636, 'ART.259/V', 'SCRITTOIO3', 'SCRITTOIO A.259/V', 41.799999999999997, 20, 1, 0, 83.333333333333343, '', 0, 0, 5, '', 0, '', '2007-10-03', 420, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3637, '', 'RETE37B', 'RETE 160 X 190 ORT. DOGHE LEGNO', 45.399999999999999, 20, 1, 0, 91.666666666666657, '', 0, 0, 1, '', 0, '', NULL, 420, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3638, 'ART.119/V', 'LETTO69', 'LETTO MATRIMONIALE  ROMA 119/V', 60.5, 20, 1, 0, 120.83333333333334, '', 0, 0, 4, '', 0, '', NULL, 420, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3639, 'ART.285/G', 'GUANCIALI25', 'GUANCIALI IN FIBRA ', 3.6000000000000001, 20, 1, 0, 7.5, '', 0, 0, 1, '', 0, '', NULL, 420, 10);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3658, 'STILO', 'SOGGIORNO49', 'SOGGIORNO STILO 300 X 225', 1087.6500000000001, 20, 1, 0, 2416.666666666667, '', 0, 0, 4, '', 0, '', NULL, 101, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3659, 'SORGENTE', 'SOGGIORNO50A', 'SOGGIORNO SORGENTE  TAVOLO 100 X 100', 288, 20, 1, 0, 640, '', 0, 0, 4, '', 0, '', NULL, 101, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3640, '', 'LETTO14C', 'LETTO A CASTELLO', 65.700000000000003, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3628, '', 'LETTO14B', 'LETTO A CASTELLO', 68.700000000000003, 20, 1, 0, 137.5, '', 0, 0, 5, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3641, '', 'RETE37C', 'RETE 160 X 190 ORT. DOGHE LEGNO', 43.100000000000001, 20, 1, 0, 87.5, '', 0, 0, 1, '', 0, '', NULL, 420, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3642, '', 'LETTO70', 'LETTO SINGOLO A.255/V', 45.200000000000003, 20, 1, 0, 0, '', 0, 0, 5, '', 0, '', '2007-10-03', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3643, '', 'CUC2B1', 'CUCINA MONICA MT.3', 736, 20, 1, 0, 1250, '', 0, 0, 2, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3644, 'BELINDA', 'DIVANO163', 'DIVANO 3P LETTO 110 X 190 BELINDA', 95, 20, 1, 0, 191.66666666666669, '', 0, 0, 3, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3645, '', 'SCARPIERA15', 'SCARPIERA ALTA P/P', 60, 20, 1, 0, 120, '', 0, 0, 9, '', 0, '', '2007-10-03', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3646, 'LORENA', 'CAMERA77', 'CAMERA LORENA', 409.5, 20, 1, 0, 820.83333333333326, '', 0, 0, 4, '', 0, '', '2007-10-03', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3647, 'ART.14', 'GRUPPOLETTO6', 'GRUPPOLETTO ART.14', 435, 20, 1, 0, 870.83333333333326, '', 0, 0, 4, '', 0, '', '2007-10-03', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3648, 'OSCAR', 'MATERASSO112', 'MATERASSO 80 X 190 OSCAR', 36.299999999999997, 20, 1, 0, 72.5, '', 0, 0, 6, '', 0, '', NULL, 420, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3649, 'ART.321/H', 'SALOTTO20A', 'SALOTTO 3 + 2 ECOPELLE BETTY', 252, 20, 1, 0, 0, '', 0, 0, 3, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3650, 'ART.624', 'PIANTANA', 'PIANTANA ART.624', 22.699999999999999, 20, 1, 0, 45.833333333333329, '', 0, 0, 9, '', 0, '', '2007-10-03', 420, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3651, '', 'POLTRONA76', 'POLTRONA ECO', 42, 20, 1, 0, 83.333333333333343, '', 0, 0, 4, '', 0, '', NULL, 420, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3652, '', 'COMO12', 'COMO'' 4 CASS.', 68.299999999999997, 20, 1, 0, 137.5, '', 0, 0, 4, '', 0, '', '2007-10-03', 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3653, 'ART. L10', 'SPECCHIO3', 'SPECCHIO ART. L10', 28.350000000000001, 20, 1, 0, 56.666666666666671, '', 0, 0, 4, '', 0, '', NULL, 420, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3654, '', 'RETE36', 'RETE A GABBIA C/DOGHE E C/MATERASSO', 38.75, 20, 1, 0, 95, '', 0, 0, 1, '', 0, '', '2007-10-03', 154, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3655, '101C3221', 'SETTIMINO4', 'SETTIMINO C3221', 184.84999999999999, 20, 1, 0, 0, '', 0, 0, 4, '', 0, '', '2007-10-04', 24, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3558, '', 'CUCFLO2', 'CUCINA REFLEX C/TAV.+ 4 SEDIE', 2175.3499999999999, 20, 1, 0, 7091.6666666666697, '', 0, 0, 1, '', 0, 'SCONTO SPECIALE X ESPO + 40%', NULL, 430, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3656, '95900301/75', 'SCARPIERA16', 'SCARPIERA', 99.049999999999997, 20, 1, 0, 198.3333333333334, '', 0, 0, 9, '', 0, '', '2007-10-04', 443, 2);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3657, '163047-1271999', 'SEDIA138', 'SEDIA LICIA', 50.200000000000003, 20, 1, 0, 0, '', 0, 0, 4, '', 0, '
', '2007-10-04', 416, 4);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3660, 'STILO', 'SOGGIORNO49A', 'SOGGIORNO STILO TAVOLO QUAD. 100 X 100', 212.40000000000001, 20, 1, 0, 470.83333333333337, '', 0, 0, 4, '', 0, '', NULL, 101, 1);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (2752, '', 'SEDIA101', 'SEDIA 9004 PIEMONTE PAG21 NOCE', 19.800000000000001, 20, 1, 0, 70.833333333333343, '', 0, 0, 1, '', 0, '', NULL, 381, 0);
INSERT INTO articoli (idarticolo, codfornitore, codbarre, descrizione, prezzo_acquisto, iva, um, prezzo_dettaglio, prezzo_ingrosso, imballo, peso, sconto, idreparto, colore, scorta_minima, note, data_inserimento, idfornitore, carico_iniziale) VALUES (3584, '', '414255', 'CARRELLO PC SPRINT', 40.549999999999997, 20, 1, 0, 76.670000000000002, '', 0, 0, 9, '', 0, '', '2007-07-27', 394, 1);


--
-- Data for Name: aspetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO aspetto (idaspetto, nome) VALUES (0, 'VISTA');


--
-- Data for Name: banca; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: carichi; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (306, 101, '2007-07-31', '16:05:14.359', '', 1, '2860', '2004-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (307, 363, '2007-07-31', '16:13:30.843', '', 1, '6023', '2006-11-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (309, 101, '2007-08-29', '11:38:36.234', '', 1, '14961', '2007-07-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (310, 278, '2007-08-30', '08:22:57.093', '', 1, '5397', '2007-05-17', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (311, 308, '2007-08-30', '19:04:07.703', 'MERCE IN SOSTITUZIONE', 1, '10361', '2007-07-13', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (314, 363, '2007-07-30', '10:47:32.416', '', 1, '4236', '2007-07-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (315, 363, '2007-07-30', '10:57:53.525', '', 1, '3421', '2007-06-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (316, 363, '2007-07-30', '11:01:33.525', '', 1, '3217', '2007-06-07', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (317, 363, '2007-07-30', '11:05:29.51', '', 1, '2821', '2007-05-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (319, 363, '2007-10-01', '18:55:47.296', 'SOSTITUZIONE', 1, '2208', '2007-04-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (318, 363, '2007-07-30', '18:56:43.453', 'SOSTITUZIONI', 1, '1545', '2007-03-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (320, 363, '2007-07-30', '19:21:59.109', 'HA SOSTITUIO MOD. DOLCE VITA', 1, '1086', '2007-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (321, 363, '2007-07-30', '15:48:14.437', '', 1, '967', '2007-02-26', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (322, 363, '2007-07-30', '15:51:50.296', '', 1, '444', '2007-01-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (323, 363, '2007-07-30', '16:00:50.859', '', 1, '258', '2007-01-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (324, 363, '2007-07-30', '16:04:26.734', '', 1, '179', '2007-01-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (325, 384, '2007-07-30', '16:10:40.656', 'SOSTITUZIONE', 1, '462', '2007-01-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (326, 400, '2007-07-30', '16:18:15.875', '', 1, '5194', '2007-06-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (327, 400, '2007-07-30', '17:12:17.562', '', 1, '560', '2007-01-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (331, 420, '2007-07-30', '11:45:04.781', '', 1, '1082', '2007-06-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (332, 420, '2007-07-30', '11:56:35.218', '', 1, '844', '2007-05-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (333, 420, '2007-07-30', '12:06:55.156', '', 1, '788', '2007-05-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (334, 420, '2007-07-31', '12:30:18.375', '', 1, '722', '2007-05-11', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (342, 24, '2007-07-30', '11:21:49.281', '', 1, '721', '2007-04-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (343, 443, '2007-07-30', '11:50:07.171', '', 1, '843', '2007-04-21', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (344, 443, '2007-05-19', '11:52:09.125', '', 1, '1070', '2007-05-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (345, 416, '2007-07-30', '12:02:42.187', '', 1, '10870', '2007-06-14', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (348, 430, '2007-07-30', '09:45:32.703', '', 1, '221', '2007-01-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (349, 113, '2007-10-12', '16:36:01.608', 'SOSTITUZIONI', 3, '3766', '2007-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (350, 113, '2007-10-12', '16:40:44.718', '', 3, '6516', '2007-03-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (351, 101, '2007-10-12', '16:57:22.858', '	', 3, '219', '2007-01-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (352, 101, '2007-10-12', '17:03:36.561', '', 3, '220', '2007-01-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (353, 101, '2007-10-15', '11:25:05.468', '', 3, '1496', '2007-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (354, 101, '2007-10-15', '11:41:22.546', '	', 3, '1497', '2007-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (355, 101, '2007-10-15', '11:47:27.468', '', 3, '3843', '2007-04-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (356, 101, '2007-10-15', '12:00:03.703', '', 3, '4447', '2007-05-21', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (357, 101, '2007-10-15', '16:17:10.906', '', 3, '5149', '2007-06-11', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (358, 101, '2007-10-15', '16:22:21.328', '', 3, '4860', '2007-06-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (359, 101, '2007-10-15', '16:25:49.281', '', 3, '7538', '2007-07-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (366, 299, '2007-10-16', '16:07:15.281', '', 3, '293', '2007-01-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (367, 423, '2007-10-16', '16:17:34.781', '', 3, '87', '2007-01-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (368, 135, '2007-10-16', '16:24:33.171', '', 3, '1012', '2007-10-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (369, 135, '2007-10-16', '16:27:46.187', '', 3, '2218', '2007-03-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (370, 135, '2007-10-16', '16:31:26.453', '', 3, '2466', '2007-04-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (371, 39, '2007-10-16', '17:02:29.093', '', 3, '16', '2007-01-08', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (372, 266, '2007-10-16', '17:12:39.14', '', 3, '1432', '2007-03-21', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (373, 338, '2007-10-16', '17:17:43.953', '', 3, '339', '2007-01-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (374, 338, '2007-10-16', '17:21:42.468', '', 3, '1313', '2007-03-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (312, 308, '2007-08-30', '09:42:40.812', '', 1, '2899', '2007-03-12', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (313, 401, '2007-08-30', '10:06:17.203', '', 1, '2506', '2007-03-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (328, 420, '2007-07-30', '18:22:10.703', '', 1, '1356', '2007-07-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (329, 420, '2007-07-30', '18:50:29.906', '', 1, '1267', '2007-07-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (330, 420, '2007-07-30', '19:51:52.187', '', 1, '1178', '2007-07-10', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (335, 420, '2007-07-30', '16:36:41.078', '', 1, '626', '2007-05-08', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (336, 420, '2007-07-30', '16:56:17', '', 1, '612', '2007-04-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (337, 420, '2007-07-30', '17:01:07.046', '', 1, '562', '2007-04-17', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (338, 420, '2007-07-30', '17:04:37.359', '', 1, '539', '2007-04-12', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (339, 420, '2007-07-30', '17:14:04.39', '', 1, '394', '2007-03-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (340, 420, '2007-07-30', '17:28:26.406', '', 1, '226', '2007-02-13', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (341, 420, '2007-07-30', '17:42:17.625', '', 1, '61', '2007-01-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (346, 101, '2007-07-30', '08:27:45.109', '', 1, '3544', '2007-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (347, 101, '2007-07-30', '09:22:01.671', '', 1, '3765', '2007-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (360, 101, '2007-10-15', '18:09:09.828', '', 3, '7539', '2007-07-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (362, 33, '2007-10-15', '18:29:07.703', '', 3, '396', '2007-01-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (361, 113, '2007-10-15', '18:32:34.937', '', 0, '7540', '2007-07-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (363, 34, '2007-10-15', '18:39:18.734', '', 3, '115', '2007-10-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (364, 34, '2007-10-15', '18:45:02.843', '', 3, '493', '2007-04-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (365, 167, '2007-10-15', '18:52:08.14', '', 3, '44', '2007-01-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (375, 280, '2007-10-16', '17:56:47.093', '', 3, '27', '2007-01-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (376, 280, '2007-10-16', '18:12:37.062', '', 3, '78', '2007-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (377, 280, '2007-10-16', '18:57:20.156', '', 0, '232', '2007-04-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (378, 280, '2007-10-16', '19:14:32.921', '', 3, '459', '2007-07-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (379, 251, '2007-10-17', '11:16:09.859', '', 0, '4141', '2007-05-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (380, 50, '2007-10-17', '11:39:37.781', '', 3, '5255', '2007-05-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (381, 50, '2007-10-17', '11:44:49.312', '', 3, '7686', '2007-07-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (382, 331, '2007-10-17', '11:50:30.296', '', 3, '1', '2007-01-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (383, 429, '2007-10-17', '12:01:46.109', '', 3, '1018', '2007-05-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (384, 61, '2007-10-17', '12:15:57.75', '', 3, '129', '2007-02-03', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (394, 201, '2007-10-19', '17:20:24.687', '', 3, '2618', '2004-06-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (395, 201, '2007-10-19', '17:25:14.75', 'COMPLETAMENTO CUCINACS6A', 3, '4917', '2007-09-11', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (396, 201, '2007-10-19', '17:28:50.218', '', 3, '5034', '2007-09-17', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (397, 272, '2007-10-19', '17:37:07.234', '', 3, '10982', '2007-07-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (398, 417, '2007-10-19', '17:46:54.687', '', 3, '6230', '2007-08-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (399, 24, '2007-10-20', '09:06:49.359', '', 0, '1431', '2007-08-02', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (420, 201, '2007-10-22', '09:51:24.484', '', 3, '5152', '2007-09-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (421, 33, '2007-10-22', '10:00:52.718', '', 3, '3171', '2007-05-21', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (422, 36, '2007-10-22', '10:41:17.25', '', 3, '8025', '2007-10-10', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (423, 39, '2007-10-22', '10:44:34.093', '', 3, '6595', '2007-09-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (424, 68, '2007-10-22', '10:55:04.125', '', 3, '814748', '2007-03-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (425, 109, '2007-10-22', '10:59:19.812', '', 3, '5450', '2007-07-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (427, 201, '2007-10-23', '11:57:59.439', '', 1, '9156', '2007-10-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (428, 201, '2007-10-23', '12:05:48.924', '', 1, '8615', '2007-10-04', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (429, 251, '2007-10-23', '12:21:15.314', '', 1, '840', '2007-10-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (437, 24, '2007-10-24', '12:10:30.109', '', 3, '1921', '2007-10-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (385, 65, '2007-10-17', '15:24:10.109', '', 3, '1517', '2007-02-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (386, 65, '2007-10-17', '15:38:15.421', '', 3, '1086', '2007-04-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (387, 68, '2007-10-17', '15:44:07.031', '', 3, '806036', '2007-02-03', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (388, 68, '2007-10-17', '15:55:09.296', '', 3, '814749', '2007-03-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (389, 68, '2007-10-17', '16:40:21', '', 3, '817397', '2007-04-02', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (390, 68, '2007-10-17', '16:41:19.14', '', 3, '833448', '2007-06-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (391, 109, '2007-10-17', '17:14:17.296', '', 3, '4592', '2007-06-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (400, 421, '2007-10-20', '09:23:18', '', 3, '1489', '2007-08-08', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (401, 421, '2007-10-20', '10:04:54.406', '', 3, '1537', '2007-08-13', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (402, 24, '2007-10-20', '10:46:34.64', '', 3, '1554', '2007-08-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (426, 429, '2007-10-22', '11:59:38.625', '', 3, '1857', '2007-10-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (431, 3, '2007-10-23', '16:03:25.861', '', 1, '9575', '2007-10-12', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (432, 101, '2007-10-23', '16:06:58.236', '', 1, '12569', '2007-10-12', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (434, 338, '2007-10-23', '16:30:39.033', '', 1, '6100', '2007-05-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (435, 280, '2007-10-23', '16:36:25.705', '', 1, '1071', '2007-02-12', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (436, 135, '2007-10-23', '16:46:08.518', '', 1, '2711330', '2007-10-04', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (439, 135, '2007-10-24', '16:34:21.89', '', 1, '2712179', '2007-10-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (440, 135, '2007-10-24', '16:35:21.296', 'SOSTITUZIONE	', 1, '2712180', '2007-10-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (433, 201, '2007-10-23', '18:08:15.375', '', 3, '5489', '2007-10-08', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (430, 167, '2007-10-23', '10:47:59.734', '	', 3, '2234', '2007-10-12', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (285, 53, '2007-07-30', '11:53:23.343', '', 1, '7768', '1995-06-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (286, 39, '2007-07-30', '11:53:36.656', '', 1, '4488', '1997-06-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (287, 30, '2007-07-30', '11:53:50.968', '', 1, '2480', '1997-10-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (288, 171, '2007-07-30', '11:54:05.343', '', 1, '1814', '1997-12-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (289, 222, '2007-07-30', '11:54:22.437', '', 1, '02', '1999-03-11', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (290, 39, '2007-07-30', '16:25:48.703', '', 1, '10081', '1999-12-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (392, 201, '2007-10-17', '18:25:58.093', '', 3, '4485', '2007-07-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (393, 201, '2007-10-17', '18:29:12.453', '', 3, '4671', '2007-08-06', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (403, 24, '2007-10-20', '12:03:08.437', '', 3, '1630', '2007-09-07', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (404, 24, '2007-10-20', '12:11:05.75', '', 3, '1686', '2007-09-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (405, 24, '2007-10-20', '12:14:14.468', '', 3, '1738', '2007-09-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (406, 24, '2007-10-20', '12:25:37.796', '', 3, '1832', '2007-10-09', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (291, 39, '2007-07-30', '11:54:52.89', '', 1, '1372', '2006-03-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (292, 39, '2007-07-30', '11:55:06.484', '', 1, '2178', '2006-04-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (293, 420, '2007-07-30', '11:55:20.359', '', 1, '507', '2006-05-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (294, 243, '2007-07-30', '11:55:36.062', '', 1, '14953', '2004-10-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (295, 154, '2007-07-30', '11:55:49.25', '', 1, '1938', '2000-08-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (296, 154, '2007-07-30', '11:56:03.546', '', 1, '356', '2003-03-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (299, 408, '2007-07-30', '11:59:16.156', '', 3, 'CONDONO 2002', '2002-12-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (298, 363, '2007-07-30', '10:05:37.906', '', 1, '5485', '2006-10-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (25, 394, '2007-07-27', '16:45:26.453', '', 1, '544723', '2004-07-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (297, 209, '2007-07-30', '15:41:06.625', '', 1, '2250', '2003-03-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (407, 101, '2007-10-20', '16:39:26.515', '', 3, '8647', '2007-09-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (408, 167, '2007-10-20', '16:44:57.812', '', 3, '2059', '2007-09-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (409, 135, '2007-10-20', '16:50:08.156', '', 3, '5782', '2007-09-17', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (19, 394, '2007-07-27', '19:08:43.921', '', 1, '660165', '2005-12-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (410, 135, '2007-10-20', '16:56:42.843', '', 3, '5976', '2007-09-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (20, 394, '2007-07-27', '19:09:33.484', '', 1, '760986', '2006-12-14', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (411, 135, '2007-10-20', '17:01:50.343', '', 3, '6574', '2007-10-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (412, 36, '2007-10-20', '17:20:59.281', '', 3, '7627', '2007-09-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (413, 39, '2007-10-20', '17:32:24.593', '', 3, '245', '2007-01-17', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (414, 39, '2007-10-20', '17:33:05.171', '', 3, '246', '2007-01-17', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (21, 394, '2007-07-27', '19:11:06.921', '', 1, '612830', '2005-03-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (22, 394, '2007-07-27', '19:11:45.296', '', 1, '707319', '2006-02-17', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (415, 280, '2007-10-20', '17:36:32.609', '', 3, '556', '2007-09-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (416, 347, '2007-10-20', '17:40:55.046', '', 3, '4027', '2007-08-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (417, 68, '2007-10-20', '17:46:21.14', '', 3, '842046', '2007-08-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (418, 68, '2007-10-20', '17:55:18.578', '', 3, '845831', '2007-09-12', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (419, 68, '2007-10-20', '18:08:39.296', '', 0, '851205', '2007-10-04', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (23, 394, '2007-07-27', '19:14:08.953', '', 1, '541689', '2004-09-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (24, 394, '2007-07-27', '19:14:28.859', '', 1, '739170', '2006-09-05', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (301, 394, '2007-07-30', '19:52:39.421', '', 1, '649365', '2005-10-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (302, 394, '2007-07-30', '19:54:22.718', '', 1, '728209', '2006-06-21', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (26, 394, '2007-07-27', '19:56:20.359', '', 1, '552015', '2004-11-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (27, 394, '2007-07-27', '19:57:42.906', '', 1, '749480', '2006-10-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (28, 394, '2007-07-27', '19:58:09', '', 1, '626539', '2005-06-13', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (29, 394, '2007-07-27', '19:58:35.062', '', 1, '622653', '2005-05-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (30, 73, '2007-07-28', '07:54:22.296', '', 1, '2540', '1999-05-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (31, 81, '2007-07-28', '07:56:05.421', '', 1, '6640', '2001-11-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (32, 111, '2007-07-28', '07:56:33.453', '', 1, '248', '2001-01-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (35, 260, '2007-07-28', '12:05:03.765', '', 1, '182', '2000-02-11', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (34, 111, '2007-07-28', '07:58:12.453', '', 1, '9109', '2003-11-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (41, 420, '2007-07-28', '10:00:07.687', '', 1, '1084', '2006-08-07', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (72, 21, '2007-07-28', '10:54:25.50', '', 1, '364', '2000-10-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (82, 430, '2007-07-28', '11:43:27.75', '', 1, '10035', '2006-10-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (36, 383, '2007-07-28', '07:59:52.437', '', 1, '5077', '2003-07-21', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (37, 196, '2007-07-28', '08:00:13.343', '', 1, '1568', '2005-02-21', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (38, 415, '2007-07-28', '08:00:31', '', 1, '537', '2006-05-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (83, 430, '2007-07-28', '11:44:02.765', '', 1, '10269', '2006-10-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (39, 420, '2007-07-28', '08:01:20.265', '', 1, '824', '2006-07-13', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (53, 243, '2007-07-28', '11:48:52.64', '', 1, '16734', '2004-11-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (64, 265, '2007-07-28', '12:12:53.671', '', 1, '5912', '2004-05-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (40, 420, '2007-07-28', '08:02:24.015', '', 1, '1727', '2006-11-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (43, 39, '2007-07-28', '18:56:23.593', '', 1, '7478', '1996-10-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (42, 436, '2007-07-28', '08:04:33.765', '', 1, '10416', '2006-07-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (44, 66, '2007-07-28', '08:06:13.359', '', 1, '293', '2005-12-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (45, 111, '2007-07-28', '08:06:28.062', '', 1, '1351', '2001-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (46, 20, '2007-07-28', '08:06:45.296', '', 1, '2763', '2001-03-13', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (47, 111, '2007-07-28', '08:07:15.64', '', 1, '94', '2004-01-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (48, 407, '2007-07-28', '08:07:30.703', '', 1, '1267', '2004-03-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (49, 196, '2007-07-28', '08:07:44.187', '', 1, '2907', '2004-03-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (50, 243, '2007-07-28', '08:08:00.968', '', 1, '5614', '2004-04-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (51, 243, '2007-07-28', '08:08:18.781', '', 1, '16318', '2004-11-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (52, 196, '2007-07-28', '08:08:36.015', '', 1, '13369', '2004-11-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (54, 429, '2007-07-28', '08:09:14.312', '', 1, '105', '2006-03-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (55, 420, '2007-07-28', '08:09:26.875', '', 1, '408', '2006-04-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (56, 429, '2007-07-28', '08:09:44.359', '', 1, '233', '2006-06-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (57, 196, '2007-07-28', '08:09:58.781', '', 1, '10017', '2006-09-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (58, 429, '2007-07-28', '08:10:11.562', '', 1, '375', '2006-10-12', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (59, 196, '2007-07-28', '08:10:23.734', '', 1, '10489', '2006-10-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (60, 211, '2007-07-28', '08:10:47.015', '', 1, '109', '1999-01-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (61, 344, '2007-07-28', '08:11:03.671', '', 1, '8576', '2002-10-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (62, 344, '2007-07-28', '08:11:22.89', '', 1, '404', '2003-10-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (63, 394, '2007-07-28', '08:11:53.578', '', 1, '447274', '2003-10-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (65, 344, '2007-07-28', '08:12:27.234', '', 1, '6174', '2004-10-11', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (66, 394, '2007-07-28', '08:13:18.75', '', 1, '711804', '2006-03-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (67, 294, '2007-07-28', '08:13:33.125', '', 1, '603', '2006-10-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (68, 199, '2007-07-28', '08:13:54.703', '', 1, '73', '1997-02-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (70, 436, '2007-07-28', '08:14:19.937', '', 1, '11381', '2006-07-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (71, 154, '2007-07-28', '08:14:47.687', '', 1, '1431', '2004-10-05', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (73, 272, '2007-07-28', '08:15:32.328', '', 1, '307', '1999-07-08', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (74, 363, '2007-07-28', '08:15:50.937', '', 1, '6324', '2006-11-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (75, 358, '2007-07-28', '08:16:48.218', '', 1, '884', '2004-10-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (76, 101, '2007-07-28', '08:17:22.078', '', 1, '10115', '2006-06-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (77, 78, '2007-07-28', '08:18:15.484', '', 1, '103', '1997-05-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (78, 111, '2007-07-28', '08:18:33.187', '', 1, '7731', '2001-10-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (80, 432, '2007-07-28', '08:18:56.421', '', 1, '379', '2006-03-09', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (81, 61, '2007-07-28', '08:19:17.50', '', 1, '3342', '1995-10-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (85, 363, '2007-07-28', '08:20:27.296', '', 1, '411', '2003-02-07', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (84, 430, '2007-07-28', '11:44:37.312', '', 1, '12880', '2006-12-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (86, 363, '2007-07-28', '08:20:55.421', '', 1, '5031', '2006-10-06', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (87, 363, '2007-07-28', '08:21:12.39', '', 1, '684', '2003-02-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (88, 363, '2007-07-28', '08:21:38.125', '', 1, '5835', '2006-11-09', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (89, 363, '2007-07-28', '08:21:54.562', '', 1, '2346', '2003-06-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (90, 363, '2007-07-28', '08:22:10.828', '', 1, '4947', '2003-11-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (91, 363, '2007-07-30', '08:22:35.968', '', 1, '2618', '2004-06-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (92, 363, '2007-07-30', '08:22:55.593', '', 1, '786', '2006-02-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (93, 363, '2007-07-30', '08:23:34.64', '', 1, '5030', '2006-10-06', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (94, 363, '2007-07-30', '08:24:05.609', '', 1, '2086', '2006-04-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (95, 363, '2007-07-30', '08:24:29.437', '', 1, '1841', '2006-04-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (96, 363, '2007-07-30', '19:01:59.031', '', 1, '5690', '2006-11-06', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (97, 363, '2007-07-30', '08:25:17.64', '', 1, '4162', '2006-07-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (98, 363, '2007-07-30', '08:25:34.578', '', 1, '7030', '2006-12-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (99, 177, '2007-07-30', '08:25:55.062', '', 1, '815', '1999-11-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (100, 177, '2007-07-30', '08:26:22.625', '', 1, '423', '1996-09-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (101, 42, '2007-07-30', '08:26:38.64', '', 1, '1642', '1995-10-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (102, 150, '2007-07-30', '08:26:53.39', '', 1, 'a2410', '1998-10-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (103, 298, '2007-07-30', '08:27:19.687', '', 1, '437', '2001-02-14', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (104, 328, '2007-07-30', '08:27:46.703', '', 1, '2485', '2002-10-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (105, 328, '2007-07-30', '08:28:01.156', '', 1, '2224', '2006-10-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (106, 328, '2007-07-30', '08:28:17.796', '', 1, '1747', '2004-06-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (107, 328, '2007-07-30', '08:29:04.718', '', 1, '1684', '2003-06-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (108, 392, '2007-07-30', '08:29:36.531', '', 1, '2bis', '2003-09-08', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (109, 412, '2007-07-30', '08:29:59.078', '', 1, '520', '2004-05-10', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (110, 255, '2007-07-30', '08:30:21.25', '', 1, '624', '2004-07-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (111, 328, '2007-07-30', '08:30:35.578', '', 1, '2135', '2004-07-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (112, 418, '2007-07-30', '08:30:52.937', '', 1, '1827', '2005-04-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (113, 425, '2007-07-30', '08:31:16.812', '', 1, '331', '2005-09-02', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (114, 180, '2007-07-30', '08:31:39.484', '', 1, '78', '2006-02-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (115, 328, '2007-07-30', '08:31:54.078', '', 1, '517', '2006-03-11', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (116, 328, '2007-07-30', '08:32:11.953', '', 1, '1806', '2006-08-06', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (117, 420, '2007-07-30', '08:33:18.921', '', 1, '1468', '2006-10-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (118, 180, '2007-07-30', '08:33:45.421', '', 1, '522', '2006-12-09', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (119, 440, '2007-07-30', '08:34:10.296', '', 1, '8', '2006-02-13', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (120, 255, '2007-07-30', '08:34:46.515', '', 1, '1396', '1998-12-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (121, 219, '2007-07-30', '08:35:13.046', '', 1, '244', '1999-03-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (122, 278, '2007-07-30', '08:35:50.671', '', 1, '18715', '2000-12-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (123, 298, '2007-07-30', '08:36:15.062', '', 1, '2770', '2000-11-03', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (124, 36, '2007-07-30', '08:36:36.078', '', 1, '1924', '1994-11-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (125, 5, '2007-07-30', '08:39:15.562', '', 1, '1850', '2006-10-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (126, 150, '2007-07-30', '08:39:31.125', '', 1, 'd532', '1998-11-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (127, 196, '2007-07-30', '08:39:47.89', '', 1, '10018', '2006-09-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (128, 78, '2007-07-30', '08:40:29.046', '', 1, '138', '1992-07-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (129, 243, '2007-07-30', '08:40:46.937', '', 1, '7264', '1999-10-14', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (130, 428, '2007-07-30', '08:41:02.421', '', 1, '284', '2006-03-14', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (131, 325, '2007-07-30', '08:42:12.25', '', 1, '2649', '2001-09-21', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (132, 400, '2007-07-30', '08:42:43.812', '', 1, '8479', '2005-11-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (133, 399, '2007-07-30', '08:43:36.968', '', 1, '3', '2003-10-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (134, 400, '2007-07-30', '08:44:49.593', '', 1, '2203', '2004-03-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (135, 420, '2007-07-30', '08:45:30.984', '', 1, '1379', '2006-10-04', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (171, 420, '2007-07-30', '09:43:36.906', '', 1, '436', '2005-09-07', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (136, 39, '2007-07-30', '08:45:59.078', '', 1, '7583', '2000-07-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (137, 96, '2007-07-30', '08:46:15.703', '', 1, '345', '1990-04-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (188, 370, '2007-07-30', '16:14:32.359', '', 1, '3-26', '2003-03-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (138, 86, '2007-07-30', '08:46:40.656', '', 1, '658', '1992-03-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (139, 27, '2007-07-30', '08:46:57.75', '', 1, '1002', '1997-09-08', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (140, 53, '2007-07-30', '08:47:13.203', '', 1, '2896', '1999-11-10', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (141, 27, '2007-07-30', '10:05:24.734', '', 1, '1141', '1994-07-26', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (142, 5, '2007-07-30', '10:06:03.187', '', 1, '689', '2006-05-10', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (143, 240, '2007-07-30', '10:06:28.578', '', 1, '2491', '2001-11-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (144, 359, '2007-07-30', '10:06:55.734', '', 1, '473', '2003-02-05', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (145, 359, '2007-07-30', '10:07:21.296', '', 1, '3657', '2003-07-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (146, 240, '2007-07-30', '10:07:45.078', '', 1, '1007', '2005-03-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (147, 154, '2007-07-30', '10:08:04.968', '', 1, '990', '2003-07-01', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (149, 363, '2007-07-30', '10:08:50.328', '', 1, '1307', '2004-03-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (150, 39, '2007-07-30', '10:09:09.656', '', 1, '5581', '2006-10-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (148, 363, '2007-07-30', '19:02:56.125', '', 1, '189', '2004-01-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (151, 420, '2007-07-30', '10:09:48.281', '', 1, '116', '2005-06-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (152, 196, '2007-07-30', '10:10:02.109', '', 1, '7790', '2003-06-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (153, 308, '2007-07-30', '10:10:21.078', '', 1, '6962', '2003-07-07', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (154, 384, '2007-07-30', '10:10:41.812', '', 1, '13513', '2005-10-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (155, 384, '2007-07-30', '10:11:08.296', '', 1, '16315', '2005-12-13', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (156, 384, '2007-07-30', '10:11:25.984', '', 1, '2983', '2006-03-10', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (157, 150, '2007-07-30', '10:11:43.421', '', 1, 'c1304', '1998-12-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (158, 218, '2007-07-30', '10:11:56.656', '', 1, '1465', '1997-11-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (159, 234, '2007-07-30', '10:12:11', '', 1, '207', '1999-03-26', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (160, 420, '2007-07-30', '10:12:29.046', '', 1, '323', '2006-04-05', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (161, 400, '2007-07-30', '10:13:32.734', '', 1, '2928', '2006-04-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (162, 420, '2007-07-30', '10:14:06.875', '', 1, '606', '2006-06-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (163, 420, '2007-07-30', '10:14:27.812', '', 1, '1564', '2006-11-06', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (164, 154, '2007-07-30', '10:14:47.468', '', 1, '716', '2004-06-01', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (165, 335, '2007-07-30', '10:15:53.578', '', 1, '1712', '2003-09-05', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (166, 400, '2007-07-30', '10:16:51.203', '', 1, '7778', '2003-10-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (167, 400, '2007-07-30', '10:17:15.171', '', 1, '6693', '2006-09-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (168, 400, '2007-07-30', '10:17:29.265', '', 1, '7853', '2004-10-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (169, 417, '2007-07-30', '10:18:20.046', '', 1, '80', '2005-03-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (170, 154, '2007-07-30', '10:18:46.25', '', 1, '141', '2005-06-03', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (172, 400, '2007-07-30', '11:01:29.64', '', 1, '9100', '2005-12-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (173, 400, '2007-07-30', '11:02:10.453', '', 1, '4724', '2006-06-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (174, 103, '2007-07-30', '11:02:32.296', '', 1, '17117', '1994-08-02', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (175, 344, '2007-07-30', '11:03:03.375', '', 1, '1875', '2003-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (176, 363, '2007-07-30', '11:03:24.656', '', 1, '4585', '2006-08-11', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (177, 363, '2007-07-30', '11:03:39.656', '', 1, '6848', '2006-12-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (178, 344, '2007-07-30', '11:04:05.937', '', 1, '405', '2003-01-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (179, 426, '2007-07-30', '11:04:45.203', '', 1, '6380', '2005-11-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (180, 426, '2007-07-30', '11:05:20.609', '', 1, '7122', '2006-11-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (181, 363, '2007-07-30', '11:05:41.343', '', 1, '5278', '2006-10-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (182, 363, '2007-07-30', '11:05:58.734', '', 1, '944', '2006-03-06', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (183, 150, '2007-07-30', '11:06:14.187', '', 1, 'c1023', '1998-10-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (184, 81, '2007-07-30', '11:06:56.281', '', 1, '1688', '2000-04-17', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (185, 64, '2007-07-30', '11:07:18.234', '', 1, '8269', '2001-08-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (186, 154, '2007-07-30', '11:07:41.421', '', 1, '2176', '2002-11-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (187, 196, '2007-07-30', '11:07:59.218', '', 1, '1866', '2003-02-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (189, 328, '2007-07-30', '11:08:58.687', '', 1, '3122', '2003-12-01', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (190, 34, '2007-07-30', '11:09:23.015', '', 1, '1830', '2004-06-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (225, 7, '2007-07-30', '15:48:35.593', '', 1, '51', '1999-04-09', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (228, 7, '2007-07-30', '15:59:15.578', '', 1, '49', '2002-12-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (207, 104, '2007-07-30', '12:21:43.50', '', 1, '3', '2003-03-12', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (191, 39, '2007-07-30', '11:11:18.468', '', 1, '6435', '2004-09-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (193, 39, '2007-07-30', '11:12:22.343', '', 1, '6986', '2004-10-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (192, 39, '2007-07-30', '11:13:10.734', '', 1, '6593', '2004-09-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (194, 433, '2007-07-30', '11:13:39.671', '', 1, '58', '2006-04-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (196, 39, '2007-07-30', '11:14:31.328', '', 1, '5336', '2005-10-12', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (197, 53, '2007-07-30', '11:14:47.171', '', 1, '9853', '1992-10-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (199, 39, '2007-07-30', '11:15:18.656', '', 1, '205', '1996-01-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (198, 222, '2007-07-30', '11:15:34.781', '', 1, '25', '1998-03-03', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (200, 150, '2007-07-30', '11:16:01.296', '', 1, '367', '1998-09-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (201, 73, '2007-07-30', '11:16:15.25', '', 1, '8439', '1993-12-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (202, 104, '2007-07-30', '11:16:55.50', '', 1, '6-98', '1998-10-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (206, 315, '2007-07-30', '16:39:47.078', '', 1, '12', '2001-04-05', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (204, 251, '2007-07-30', '11:17:57.109', '', 1, '1-2000', '2000-07-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (205, 104, '2007-07-30', '11:18:17.906', '', 1, '7', '2001-04-03', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (208, 104, '2007-07-30', '11:21:30.093', '', 1, '3', '2006-11-14', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (209, 57, '2007-07-30', '11:21:57.25', '', 1, '1', '1997-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (210, 69, '2007-07-30', '11:22:16', '', 1, '62', '1999-06-21', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (212, 221, '2007-07-30', '11:23:25.906', '', 1, '126', '1998-03-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (213, 221, '2007-07-30', '11:23:43.125', '', 1, '151', '1999-04-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (214, 221, '2007-07-30', '11:24:09.14', '', 1, '482', '1999-11-02', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (215, 154, '2007-07-30', '11:24:27', '', 1, '1329', '2003-08-05', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (216, 154, '2007-07-30', '11:25:00.187', '', 1, '1119', '2002-06-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (217, 154, '2007-07-30', '11:25:25.625', '', 1, '1556', '2002-08-08', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (218, 154, '2007-07-30', '11:25:53.031', '', 1, '1224', '2004-08-10', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (219, 154, '2007-07-30', '11:26:16.078', '', 1, '1349', '2002-07-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (220, 45, '2007-07-30', '11:26:44.562', '', 1, '1456', '1992-09-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (221, 335, '2007-07-30', '11:27:00.078', '', 1, '2192', '2004-11-09', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (222, 400, '2007-07-30', '11:27:37', '', 1, '4618', '2005-06-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (223, 154, '2007-07-30', '11:28:04.343', '', 1, '1112', '2004-07-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (224, 419, '2007-07-30', '11:28:30.50', '', 1, '1042', '2005-06-14', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (226, 5, '2007-07-30', '11:29:41.796', '', 1, '960', '1999-11-09', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (227, 7, '2007-07-30', '11:30:03.406', '', 1, '115', '2001-06-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (229, 359, '2007-07-30', '11:30:45.218', '', 1, '3456', '2006-06-23', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (230, 7, '2007-07-30', '11:31:06.859', '', 1, '158', '1994-10-04', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (231, 7, '2007-07-30', '11:31:43.468', '', 1, '184', '1997-06-14', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (232, 65, '2007-07-30', '11:31:59.953', '', 1, '2209', '1994-10-07', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (233, 328, '2007-07-30', '11:32:20.703', '', 1, '2130', '2002-09-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (234, 441, '2007-07-30', '11:32:36.828', '', 1, '4699', '2006-12-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (235, 4, '2007-07-30', '11:32:57.64', '', 1, '196', '1994-07-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (236, 111, '2007-07-30', '11:33:30.281', '', 1, '1497', '1999-03-10', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (237, 381, '2007-07-30', '11:35:11.609', '', 1, '3574', '2003-10-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (238, 381, '2007-07-30', '11:35:51.015', '', 1, '5231', '2004-10-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (239, 381, '2007-07-30', '11:36:23.89', '', 1, '323', '2004-01-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (240, 381, '2007-07-30', '11:36:55.031', '', 1, '4605', '2004-09-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (241, 394, '2007-07-30', '11:37:14.343', '', 1, '625185', '2005-06-01', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (242, 363, '2007-07-30', '11:37:36.437', '', 1, '5200', '2005-11-11', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (243, 381, '2007-07-30', '11:37:54.281', '', 1, '3956', '2006-06-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (244, 434, '2007-07-30', '11:38:11.703', '', 1, '333', '2006-05-18', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (245, 381, '2007-07-30', '11:38:39.14', '', 1, '3185', '2006-05-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (246, 420, '2007-07-30', '11:39:19.75', '', 1, '909', '2006-07-21', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (247, 358, '2007-07-30', '11:39:41.625', '', 1, '1737', '2006-11-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (248, 34, '2007-07-30', '11:40:03.75', '', 1, '2916', '2006-12-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (249, 154, '2007-07-30', '11:40:23.328', '', 1, '1863', '2000-08-10', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (250, 34, '2007-07-30', '11:40:58.578', '', 1, '79', '1998-01-26', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (251, 34, '2007-07-30', '11:41:14.078', '', 1, '1872', '1998-07-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (252, 41, '2007-07-30', '11:41:31.859', '', 1, '19', '1999-03-29', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (303, 363, '2007-07-31', '12:13:29.312', '', 1, '3469', '2004-07-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (304, 328, '2007-07-31', '12:23:58.828', '', 1, '2498', '2006-11-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (254, 243, '2007-07-30', '11:43:02.609', '', 1, '2844', '2003-02-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (255, 101, '2007-07-30', '11:43:23.687', '', 1, '9065', '2002-05-20', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (305, 69, '2007-07-31', '12:27:50.406', '', 1, '34', '1999-04-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (253, 111, '2007-07-30', '10:31:15.812', '', 1, '7937', '2000-11-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (256, 101, '2007-07-30', '11:44:04.312', '', 1, '12401', '2003-07-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (257, 409, '2007-07-30', '11:44:23.265', '', 1, '288', '2004-05-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (258, 101, '2007-07-30', '11:44:37.015', '', 1, '6892', '1996-04-09', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (259, 101, '2007-07-30', '11:44:50.64', '', 1, '13858', '1997-06-16', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (260, 243, '2007-07-30', '11:45:06.046', '', 1, '4766', '1998-09-28', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (261, 211, '2007-07-30', '11:45:21.765', '', 1, '731', '1999-02-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (262, 101, '2007-07-30', '11:45:46.515', '', 1, '18989', '2002-11-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (263, 243, '2007-07-30', '11:46:07.375', '', 1, '4651', '2003-03-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (264, 101, '2007-07-30', '11:46:22.89', '', 1, '7854', '2003-05-19', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (266, 394, '2007-07-30', '11:47:02.218', '', 1, '628730', '2005-06-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (267, 101, '2007-07-30', '11:47:24.39', '', 1, '14940', '2004-10-25', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (268, 243, '2007-07-30', '11:47:39.406', '', 1, '17897', '2004-12-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (269, 66, '2007-07-30', '11:47:53.062', '', 1, '1328', '1994-10-03', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (270, 60, '2007-07-30', '11:48:06.781', '', 1, '12678', '1995-10-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (271, 85, '2007-07-30', '11:48:30.484', '', 1, '262', '1991-09-15', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (272, 81, '2007-07-30', '11:48:44', '', 1, '6037', '1999-11-08', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (273, 39, '2007-07-30', '11:48:57.062', '', 1, '560', '2001-01-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (274, 81, '2007-07-30', '11:49:18.75', '', 1, '4107', '2001-08-10', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (275, 39, '2007-07-30', '11:49:34.359', '', 1, '1837', '1995-06-26', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (276, 196, '2007-07-30', '11:49:52.703', '', 1, '12707', '2003-10-31', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (277, 43, '2007-07-30', '11:50:10.531', '', 1, '2333', '1991-06-27', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (278, 28, '2007-07-30', '11:50:33.968', '', 1, '184', '1994-05-02', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (279, 97, '2007-07-30', '11:50:51.89', '', 1, '454', '1992-09-17', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (280, 256, '2007-07-30', '11:51:13.968', '', 1, '17133', '1998-12-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (281, 81, '2007-07-30', '11:51:41.968', '', 1, '4743', '2000-10-30', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (282, 39, '2007-07-30', '11:52:26.718', '', 1, '9552', '1998-12-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (283, 237, '2007-07-30', '11:52:50.187', '', 1, '01', '1999-01-22', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (284, 53, '2007-07-30', '11:53:06.546', '', 1, '14229', '1996-12-24', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (442, 381, '2007-12-13', '12:45:45.831', '', 1, '123', '2007-12-13', NULL, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (449, 355, '2008-01-12', '17:28:12.546', '', 1, '1', '2008-01-12', 2312.2199999999998, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (451, 39, '2008-01-13', '16:26:13.109', '', 1, '1', '2008-01-13', 0, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (450, 39, '2008-01-12', '17:41:39.359', '', 1, '1', '2008-01-12', 2.3999999999999999, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (452, 39, '2008-01-13', '16:29:39.656', '', 1, '1', '2008-01-13', 1.2, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (453, 363, '2008-01-13', '16:33:09.812', '', 1, '1', '2008-01-13', 0, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (454, 363, '2008-01-13', '16:34:36.687', '', 1, '1', '2008-01-13', 0, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (455, 363, '2008-01-13', '16:37:45.828', '', 1, '1', '2008-01-13', 1.2, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (456, 363, '2008-01-13', '16:55:42.89', '		', 1, '1', '2008-01-13', 0, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (445, 39, '2008-01-12', '13:54:01.14', '			', 1, '123', '2008-01-12', 642, 1, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (441, 39, '2007-10-24', '12:17:22.906', '', 1, '2712181', '2007-10-19', 1.2, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (448, 39, '2008-01-12', '17:28:47.265', '', 1, '1', '2008-01-12', 109.8, 1, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (460, 320, '2008-01-14', '21:07:00.093', '', 1, '3334', '2008-01-14', 240, 0, 459, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (462, 320, '2008-01-14', '22:03:58.546', '', 1, '444', '2008-01-14', 240, 0, 461, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (461, 320, '2008-01-14', '22:02:37.625', '', 2, '444', '2008-01-14', 240, 0, 462, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (463, 39, '2008-01-14', '01:54:33.539', '', 1, '5555', '2008-01-15', 1.2, 1, 457, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (457, 39, '2008-01-14', '20:34:46.843', '', 2, '12', '2008-01-14', 0, 0, 463, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (464, 363, '2008-01-15', '02:01:37.776', '', 1, '111/a', '2008-01-15', 0, 0, -1, 0, NULL, NULL);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (458, 39, '2008-01-14', '20:36:42.125', '', 2, '1', '2008-01-14', 1.2, 0, 465, 0, 0, 0);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (466, 363, '2008-02-28', '03:38:02.109', '', 1, '1212', '2008-02-28', 0, 0, -1, 0, 0, 0);
INSERT INTO carichi (idcarico, idfornitore, data_carico, ora_carico, note, iddocumento, num_documento, data_documento, totale_documento, sospeso, rif_doc, sconto, iva_documento, ins_pn) VALUES (465, 363, '2008-01-14', '00:44:14.984', '', 1, '1111', '2008-02-28', 924.60000000000002, 0, -1, 0, 0, 0);


--
-- Data for Name: causale; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO causale (idcausale, nome) VALUES (0, 'VENDITA');


--
-- Data for Name: clienti; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO clienti (idcliente, data_inserimento, nome, cognome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, data_nascita, documento, num_doc, rilasciato_il, rilasciato_da, nato_a, intestazione, nazionalita, provincia) VALUES (0, '2007-11-05', 'BANCO', 'BANCO', '', '', 'C/DA SELLITTE', NULL, 'BISIGNANO', '', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO clienti (idcliente, data_inserimento, nome, cognome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, data_nascita, documento, num_doc, rilasciato_il, rilasciato_da, nato_a, intestazione, nazionalita, provincia) VALUES (1, '2007-11-06', 'SERGIO', 'FALCONE', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO clienti (idcliente, data_inserimento, nome, cognome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, data_nascita, documento, num_doc, rilasciato_il, rilasciato_da, nato_a, intestazione, nazionalita, provincia) VALUES (2, '2007-11-06', 'ROCCO', 'FUSELLA', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO clienti (idcliente, data_inserimento, nome, cognome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, data_nascita, documento, num_doc, rilasciato_il, rilasciato_da, nato_a, intestazione, nazionalita, provincia) VALUES (3, '2007-11-06', 'LUCA', 'BRINDISI', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO clienti (idcliente, data_inserimento, nome, cognome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, data_nascita, documento, num_doc, rilasciato_il, rilasciato_da, nato_a, intestazione, nazionalita, provincia) VALUES (4, '2008-02-28', 'ANTONIO', 'RASO', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);


--
-- Data for Name: conto_bancario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: dettaglio_carichi; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3299, 114, 1, 387.60000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3300, 114, 1, 480.69999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3315, 115, 1, 235, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3415, 116, 1, 535, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3481, 117, 1, 116, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3480, 117, 1, 136, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3573, 117, 1, 66.549999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3533, 118, 1, 624.14999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3532, 118, 1, 803.70000000000005, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3542, 119, 2, 200, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1159, 120, 1, 421.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1273, 121, 1, 590.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1272, 121, 1, 651.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1480, 122, 1, 581.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1479, 122, 1, 738, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1968, 123, 1, 664, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1969, 123, 1, 804, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1589, 124, 1, 212.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3465, 125, 1, 170, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3439, 127, 1, 706.79999999999995, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1719, 128, 1, 227.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3139, 129, 1, 205.19999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3309, 130, 1, 1752.05, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2215, 131, 2, 23.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2212, 131, 1, 12, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2206, 131, 1, 56.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3266, 132, 6, 21.550000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2765, 133, 10, 4, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2847, 134, 1, 26, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2844, 134, 1, 422.19999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3441, 135, 20, 2.7999999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1882, 136, 1, 49.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1593, 137, 1, 535.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1594, 138, 1, 792, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1596, 138, 1, 514, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (648, 139, 1, 670, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1455, 140, 1, 398, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2523, 228, 29, 10, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2841, 306, 1, 1014.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3583, 208, 3, 65, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3368, 76, 2, 266.89999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2899, 307, 1, 10.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3251, 171, 2, 69.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3206, 171, 2, 28.949999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3392, 41, 2, 70.549999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3395, 41, 2, 44.350000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3421, 41, 1, 29.300000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1804, 298, 1, 15.550000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1997, 253, 1, 1093, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1945, 72, 1, 717, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1946, 72, 1, 807, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3448, 82, 1, 1517.8499999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3454, 83, 1, 1840.6500000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3558, 84, 1, 2175.3499999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3100, 53, 1, 1405.8499999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2949, 64, 1, 407.35000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3575, 207, 15, 7.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2626, 188, 1, 1250, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1959, 290, 1, 137, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2108, 206, 1, 51.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2109, 206, 2, 26, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3056, 25, 1, 17.350000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3568, 96, 1, 189.69999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3570, 96, 1, 659.10000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3109, 148, 1, 61.049999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3586, 309, 1, 90.900000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3588, 310, 1, 755, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3587, 310, 1, 408, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3589, 310, 1, 466, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3590, 311, 2, 150, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3598, 314, 1, 825.35000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3599, 315, 1, 498.19999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3600, 316, 1, 56.899999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3601, 317, 1, 411.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3602, 318, 1, 300.05000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3603, 319, 1, 58.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3604, 320, 1, 2108.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3605, 321, 1, 567.64999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3606, 322, 1, 161.19999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3607, 323, 1, 646.10000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3608, 324, 1, 48.149999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3609, 325, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3610, 326, 1, 92.650000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3611, 327, 1, 120.59999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3613, 327, 4, 84.950000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3614, 327, 2, 120.05, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3612, 327, 1, 141.30000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3626, 331, 4, 24.800000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3631, 331, 4, 32.850000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3632, 331, 6, 49.200000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3633, 332, 1, 112.3, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3634, 332, 1, 266, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3617, 333, 1, 76.950000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3624, 333, 1, 55.850000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3629, 333, 2, 72.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3635, 333, 1, 28.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3628, 333, 1, 68.700000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3617, 334, 2, 76.950000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3625, 334, 4, 23.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3622, 334, 2, 65.650000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3636, 334, 2, 41.799999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3637, 334, 2, 45.399999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2647, 141, 1, 111.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3354, 142, 1, 66.650000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2168, 143, 2, 64.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2576, 144, 3, 44.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2706, 145, 2, 44, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3572, 146, 1, 204.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2689, 147, 1, 49.100000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3143, 149, 1, 39.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (137, 150, 1, 441, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2999, 151, 1, 62, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3215, 151, 3, 23.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2586, 152, 1, 506.55000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2686, 153, 1, 909.29999999999995, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3047, 154, 1, 242.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3189, 155, 2, 77, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3323, 156, 1, 400.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1146, 157, 1, 121.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (764, 158, 1, 19.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1265, 159, 1, 90.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3332, 160, 1, 114, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3335, 161, 2, 116.95, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3372, 162, 1, 92.950000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3214, 162, 2, 46.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3482, 163, 4, 92.400000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (899, 164, 1, 22.649999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2417, 165, 6, 88, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2738, 165, 4, 75.049999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2739, 165, 4, 37.549999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2743, 166, 1, 279.39999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2746, 166, 2, 57.799999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2741, 166, 1, 32.299999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3301, 167, 1, 302.94999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2744, 168, 1, 154.55000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3167, 169, 1, 104.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3166, 169, 1, 125.7, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3170, 169, 2, 67.799999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3272, 172, 1, 134.30000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3213, 172, 5, 58.450000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3272, 173, 2, 134.30000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1776, 174, 1, 56.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2606, 175, 2, 117.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (176, 176, 1, 15.300000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3561, 177, 1, 23.949999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2553, 178, 1, 370.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2554, 178, 1, 30.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3274, 179, 1, 127.2, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3275, 179, 1, 138, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3276, 179, 1, 96, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3510, 180, 1, 54.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3509, 180, 1, 54.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3511, 180, 1, 91, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3102, 181, 1, 68.200000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (710, 182, 1, 127.2, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1085, 183, 1, 42, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2048, 184, 1, 290.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2201, 185, 1, 93, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2493, 186, 2, 71, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2581, 187, 1, 227, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2801, 189, 1, 136.40000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2638, 189, 1, 144.55000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2802, 189, 1, 148.80000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2832, 190, 2, 379.60000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3019, 191, 1, 85.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3043, 192, 2, 121.59999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3045, 192, 2, 111.15000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3580, 193, 1, 111.15000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3068, 193, 1, 121.59999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3580, 192, 1, 111.15000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3329, 194, 1, 600, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3253, 196, 1, 95.400000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1625, 197, 1, 140.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1629, 199, 1, 178, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (806, 198, 1, 72.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1006, 200, 1, 24.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1631, 201, 1, 94.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1899, 204, 8, 8, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1901, 204, 2, 15.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1898, 204, 5, 26, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2101, 205, 6, 15.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3487, 208, 5, 20, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3492, 208, 5, 30, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3491, 208, 10, 8, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3488, 208, 4, 50, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3489, 208, 1, 120, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3490, 208, 2, 100, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1642, 209, 2, 5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (224, 210, 1, 16.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (835, 212, 1, 95.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (221, 213, 1, 93, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1380, 214, 2, 37, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2162, 215, 1, 38.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2368, 216, 2, 40.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2368, 217, 4, 40.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3007, 218, 2, 44.399999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2383, 219, 4, 19.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1650, 220, 2, 58.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3089, 221, 1, 37.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3212, 222, 5, 83.099999999999994, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3001, 223, 4, 21.699999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3210, 224, 2, 23, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1292, 225, 8, 27, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1473, 226, 1, 107.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2186, 227, 11, 22.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3383, 229, 4, 14.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3581, 230, 1, 12.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (640, 231, 1, 45, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2411, 233, 1, 622.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3547, 234, 1, 530, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1665, 235, 1, 91, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1206, 236, 1, 255, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2753, 237, 12, 9.8000000000000007, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2757, 237, 8, 14.9, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2758, 237, 1, 24.100000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3098, 238, 18, 11.4, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2826, 239, 2, 285.19999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3048, 240, 1, 26.649999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3050, 240, 2, 28.350000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3202, 241, 4, 36, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3269, 242, 4, 41.450000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3389, 243, 2, 26.649999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3364, 244, 1, 10.300000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (536, 246, 6, 22.199999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (274, 247, 2, 107.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2714, 256, 1, 2058.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2715, 256, 1, 425.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2942, 257, 1, 248, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1164, 258, 1, 278.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (642, 259, 1, 1322, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1008, 260, 1, 743, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1213, 261, 1, 1440, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2484, 262, 1, 1123, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2625, 263, 1, 1163.7, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2642, 264, 1, 1979.0999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2972, 266, 1, 244.44999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3076, 267, 1, 714.14999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3128, 268, 1, 1392.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (309, 269, 1, 1927, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1681, 270, 1, 860, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1682, 271, 3, 41.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1454, 272, 1, 93, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2057, 273, 1, 141.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2200, 274, 1, 105, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1685, 275, 1, 147, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2778, 276, 1, 66.799999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1687, 277, 1, 23, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1689, 278, 1, 248.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1690, 279, 1, 71, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1184, 280, 1, 64.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1948, 281, 1, 41.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1947, 281, 1, 36, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3577, 282, 1, 76.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1172, 282, 1, 56.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1800, 284, 1, 153, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1699, 285, 1, 147, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (654, 286, 1, 212, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (752, 287, 1, 236, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (792, 288, 1, 206.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1249, 289, 1, 212, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3314, 291, 1, 220.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3330, 292, 1, 269.10000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3351, 293, 1, 100.7, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3085, 294, 1, 215.30000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2684, 295, 1, 95.549999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2616, 296, 1, 46.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2627, 297, 1, 98.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2891, 299, 1, 30, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2893, 299, 1, 73, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2894, 299, 1, 1100, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2895, 299, 1, 360, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2896, 299, 1, 250, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2889, 299, 1, 42, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2900, 299, 1, 70, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2901, 299, 1, 150, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2898, 299, 1, 300, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2913, 299, 4, 20, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2912, 299, 1, 50, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2911, 299, 1, 200, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2991, 303, 1, 46.100000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3591, 312, 1, 728.29999999999995, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3592, 313, 1, 966.64999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3593, 313, 2, 235.15000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3594, 313, 2, 70.549999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3595, 313, 1, 253.40000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3596, 313, 2, 39.200000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3597, 313, 1, 70.549999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3615, 328, 1, 730, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3616, 328, 1, 660, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3617, 328, 1, 76.950000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3618, 328, 6, 12.15, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3619, 329, 1, 134.69999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3620, 329, 1, 100.05, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3621, 329, 1, 63.649999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3622, 329, 1, 65.650000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3623, 329, 1, 316.55000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3619, 330, 1, 134.69999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3624, 330, 1, 55.850000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3625, 330, 2, 23.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3626, 330, 4, 24.800000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3627, 330, 2, 49.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3628, 330, 1, 68.700000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3629, 330, 1, 72.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3638, 334, 2, 60.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3639, 334, 10, 3.6000000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3655, 342, 1, 184.84999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3656, 343, 2, 99.049999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3656, 344, 1, 99.049999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3657, 345, 4, 50.200000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3658, 346, 1, 1087.6500000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3660, 346, 1, 212.40000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3661, 346, 1, 45.899999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3665, 349, 5, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3666, 350, 1, 54.450000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3667, 351, 1, 214.30000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3668, 351, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3669, 352, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3671, 353, 1, 348.10000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3672, 354, 4, 53.350000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3673, 355, 1, 1385.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3206, 170, 2, 28.949999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3272, 161, 2, 134.30000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (192, 304, 1, 200, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1648, 305, 2, 51.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3212, 161, 2, 83.099999999999994, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3640, 335, 1, 65.700000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3641, 335, 4, 43.100000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3001, 335, 4, 21.699999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3617, 335, 2, 76.950000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3625, 335, 1, 23.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3642, 335, 1, 45.200000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3643, 336, 1, 736, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3644, 336, 1, 95, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3645, 336, 1, 60, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3646, 337, 1, 409.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3647, 338, 1, 435, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3648, 339, 4, 36.299999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3421, 339, 2, 29.300000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3649, 340, 1, 252, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3421, 340, 4, 29.300000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3650, 340, 2, 22.699999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3651, 340, 2, 42, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3652, 341, 1, 68.299999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3653, 341, 1, 28.350000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3625, 341, 2, 23.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3638, 341, 1, 60.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2162, 341, 1, 31.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3662, 347, 1, 1221.3, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3659, 347, 1, 288, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3663, 347, 4, 51.299999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3664, 348, 1, 735.35000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3674, 356, 1, 160.59999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3675, 357, 1, 3.2000000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3676, 358, 1, 101.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3677, 359, 1, 394.30000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3684, 366, 1, 86.700000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3685, 367, 1, 170, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3686, 367, 1, 135, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3687, 368, 1, 1178.7, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3688, 369, 1, 853.64999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3689, 370, 1, 134.34999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3690, 371, 1, 1400.95, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3691, 372, 1, 699, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3692, 373, 1, 70, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3693, 374, 1, 512, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3702, 379, 1, 212, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3701, 379, 2, 106, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3700, 379, 1, 380, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3703, 379, 1, 418, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3705, 379, 1, 88.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3706, 379, 1, 137.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3707, 379, 2, 23, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3708, 379, 2, 24, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3709, 379, 2, 12.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3711, 379, 2, 7.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3710, 379, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3704, 379, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3712, 380, 1, 588.60000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3713, 381, 1, 846.20000000000005, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3714, 382, 2, 75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3715, 383, 1, 259.89999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3716, 384, 1, 1301, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3717, 384, 1, 1301, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3718, 384, 1, 840, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3719, 384, 6, 116, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3728, 392, 1, 130.55000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3729, 393, 1, 187.59999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3730, 394, 1, 1097.55, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3731, 395, 1, 743.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3732, 396, 1, 574, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3733, 397, 2, 113.34999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3612, 398, 3, 141.30000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3734, 398, 3, 40.799999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3735, 399, 1, 81.049999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3736, 399, 1, 95, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3635, 399, 1, 28.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3737, 399, 2, 40.100000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3738, 399, 1, 47.399999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3739, 399, 1, 26.600000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3631, 399, 6, 32.850000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3622, 399, 2, 65.650000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3654, 399, 4, 38.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3637, 399, 3, 45.399999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3749, 407, 1, 75.150000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3750, 408, 6, 54.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3751, 409, 1, 63.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3752, 410, 1, 204.69999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3753, 411, 1, 84.049999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3754, 412, 1, 1275.2, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3756, 413, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3757, 414, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3758, 415, 1, 1046.05, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3759, 416, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3760, 417, 1, 410.35000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3761, 418, 1, 57, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3762, 418, 2, 54.299999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3764, 419, 1, 60.299999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3765, 419, 1, 70.150000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3763, 419, 1, 122, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3766, 420, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3767, 421, 1, 106.90000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3755, 422, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3769, 423, 1, 190.94999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3770, 424, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3771, 425, 2, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3773, 427, 1, 1731.55, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3709, 429, 12, 12.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3775, 429, 2, 24, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3711, 429, 10, 7.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3786, 437, 1, 93.400000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3737, 437, 1, 40.100000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3678, 360, 1, 1197.8499999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3679, 361, 1, 79.599999999999994, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3680, 362, 4, 5.4000000000000004, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3681, 363, 2, 412, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3682, 364, 1, 1057.1500000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3683, 365, 6, 52.100000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3694, 375, 1, 1152.8499999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3695, 375, 1, 435.69999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3696, 376, 1, 388.64999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3697, 377, 1, 903.14999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3698, 378, 1, 173.84999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3699, 378, 1, 452.64999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3720, 385, 1, 299.69999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3721, 386, 1, 4894, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3722, 387, 1, 68.400000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3723, 388, 1, 254.80000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3724, 389, 1, 200.05000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3725, 390, 1, 442.10000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3727, 391, 4, 28.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3726, 391, 1, 236, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3740, 399, 4, 23.100000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3617, 400, 4, 76.950000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3741, 400, 1, 98.400000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3632, 400, 2, 49.200000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3629, 400, 2, 72.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3742, 400, 1, 70.299999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3631, 401, 6, 32.850000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3742, 401, 2, 70.299999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3625, 402, 2, 23.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3617, 402, 1, 76.950000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3743, 402, 1, 69.849999999999994, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3644, 402, 1, 95, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3744, 402, 1, 150, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3615, 402, 1, 730, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3623, 403, 1, 316.55000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3737, 404, 1, 40.100000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3745, 404, 1, 60.600000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3746, 404, 1, 37.149999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3615, 404, 1, 730, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3743, 405, 1, 69.849999999999994, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3624, 405, 1, 55.850000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3647, 406, 1, 435, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3631, 406, 2, 32.850000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3747, 406, 6, 20.899999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3748, 406, 1, 64.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3778, 431, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3779, 432, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3780, 433, 1, 36.399999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3781, 433, 1, 61, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3782, 433, 20, 3.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3783, 434, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3784, 435, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3785, 436, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3742, 437, 2, 70.299999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3787, 437, 6, 23.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3788, 439, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3789, 440, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3790, 441, 1, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3776, 430, 1, 256.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3777, 430, 4, 36, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3772, 426, 1, 372.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1578, 101, 1, 38, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2450, 102, 1, 76.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2079, 103, 1, 911.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2440, 104, 1, 248, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2439, 104, 1, 297, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3502, 105, 1, 166.15000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2339, 106, 1, 259, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2681, 107, 1, 253, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2680, 107, 1, 297, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2732, 108, 1, 140, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2730, 108, 1, 290, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2955, 109, 1, 300, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2954, 109, 1, 350, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2986, 110, 1, 514.54999999999995, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2987, 110, 1, 601.54999999999995, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2997, 111, 1, 174, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3192, 112, 1, 119, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3248, 113, 1, 243, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3249, 113, 1, 317, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2679, 107, 1, 636, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3440, 117, 1, 36.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1158, 120, 1, 291, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3469, 125, 8, 15, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3467, 125, 8, 15, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3468, 125, 8, 10, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3466, 125, 1, 45, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3462, 125, 1, 235, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (367, 128, 1, 532, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2845, 134, 1, 134.94999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (357, 128, 1, 496, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2769, 133, 1, 75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1878, 136, 1, 77.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3016, 191, 1, 346.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3493, 208, 1, 120, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1207, 236, 1, 83.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2831, 239, 1, 237, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3363, 245, 1, 196.05000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (537, 248, 4, 32.799999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (646, 249, 6, 12.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (805, 250, 6, 34, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (957, 251, 1, 45, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1271, 252, 6, 22.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2000, 253, 4, 133, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3285, 19, 1, 46.149999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2001, 253, 2, 157.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3552, 20, 1, 58.399999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1999, 253, 1, 849, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3550, 20, 1, 45.100000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2191, 254, 6, 30.149999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3179, 21, 1, 19.899999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2512, 255, 4, 30, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3182, 21, 1, 46.950000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3176, 21, 1, 61.600000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3177, 21, 1, 91.650000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3178, 21, 1, 72.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3562, 22, 1, 60, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3031, 23, 1, 28.300000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3024, 23, 1, 44.799999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3034, 23, 2, 69.900000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3027, 23, 1, 43.200000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3032, 23, 1, 51.200000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3025, 23, 1, 50.700000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3026, 23, 1, 44.799999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3030, 23, 1, 44.799999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3563, 24, 1, 51.75, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2716, 256, 4, 48.600000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3122, 26, 1, 28.300000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3113, 26, 1, 8, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3121, 26, 1, 77.349999999999994, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3268, 301, 1, 143.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3118, 302, 1, 81.900000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3123, 26, 1, 40, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3112, 26, 1, 54.950000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3111, 26, 1, 42.149999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3564, 27, 1, 64, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3207, 28, 1, 57.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3200, 29, 1, 28.350000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2029, 35, 2, 263.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3369, 76, 2, 34.450000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1327, 30, 4, 5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1328, 30, 4, 5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1329, 30, 4, 7.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2247, 31, 1, 72.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2060, 32, 1, 442, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2784, 34, 1, 876, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2785, 34, 1, 653, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2790, 34, 4, 90.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2786, 34, 1, 500, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1544, 35, 1, 2133, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1546, 35, 1, 799, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2030, 35, 1, 606.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2698, 36, 1, 1443.9000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2699, 36, 1, 345.55000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2700, 36, 1, 82.150000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3157, 37, 1, 1215.8499999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3391, 39, 1, 62.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3394, 39, 1, 88.700000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3516, 40, 1, 65.099999999999994, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3521, 40, 1, 38.299999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3397, 42, 1, 86, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3565, 42, 1, 62.399999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3400, 42, 1, 49.700000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3279, 44, 1, 654.10000000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3582, 44, 6, 77.799999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3280, 44, 1, 435.19999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3278, 44, 1, 1032, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2085, 46, 1, 1010, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2819, 47, 1, 3830, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2854, 49, 1, 1846, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3095, 50, 1, 222.30000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3086, 51, 1, 1173.3, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3096, 52, 1, 1340.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3310, 54, 1, 1850.3499999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3334, 55, 1, 500, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3374, 56, 1, 1901.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3438, 57, 1, 3186.3499999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3449, 58, 1, 1500.0999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3450, 59, 1, 2135.0500000000002, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2325, 60, 1, 40, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2425, 61, 1, 1234.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2549, 62, 1, 824.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2773, 63, 1, 608.29999999999995, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2774, 63, 1, 588.39999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3051, 65, 1, 873.04999999999995, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3305, 66, 1, 307, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3303, 66, 1, 344, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3306, 66, 1, 94, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3452, 67, 1, 1384.3499999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (609, 68, 1, 85.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3401, 70, 1, 26.199999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2948, 71, 2, 24.350000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1384, 73, 1, 1008, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3508, 74, 1, 80.650000000000006, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3072, 75, 1, 813.14999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3074, 75, 6, 104.40000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3073, 75, 1, 448.64999999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3075, 75, 1, 1116, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3370, 76, 1, 442.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (635, 77, 1, 444.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (634, 77, 1, 559, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1701, 77, 1, 235, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1998, 78, 1, 961, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3327, 80, 1, 310, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1570, 81, 8, 49, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2597, 85, 1, 1154.4000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3576, 86, 1, 234.65000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3528, 86, 1, 1826.45, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3566, 87, 1, 107.95, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3501, 88, 1, 72.599999999999994, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3578, 89, 1, 2624.9499999999998, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3579, 90, 1, 1307.3, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2960, 91, 1, 2790, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3567, 92, 1, 1400, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3526, 93, 1, 1042.5999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3545, 93, 1, 82.450000000000003, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3340, 94, 1, 60.399999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3361, 95, 1, 57.049999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3569, 97, 1, 1326.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3571, 98, 1, 336.55000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2669, 99, 1, 264.94999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1100, 100, 1, 124, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3515, 40, 2, 60.899999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1320, 30, 0, 86, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1321, 30, 0, 83.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3365, 38, 0, 1534.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2848, 48, 1, 638.25, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2078, 103, 1, 759.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2083, 45, 1, 3124.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2474, 232, 1, 304.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3464, 125, 1, 270, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1139, 126, 1, 55.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (283, 245, 1, 39.149999999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3574, 202, 1, 9.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2752, 442, 2, 19.800000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2752, 237, 2, 19.800000000000001, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3584, 19, 2, 40.549999999999997, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (979, 283, 15, 143.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (656, 225, 2, 8, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (367, 445, 1, 532, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3310, 449, 1, 1850.3499999999999, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3577, 449, 1, 76.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (1265, 448, 1, 90.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (9, 450, 1, 1, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (9, 445, 1, 1, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (9, 448, 1, 1, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (9, 441, 1, 1, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (9, 43, 1, 1, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (9, 452, 1, 1, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (9, 455, 1, 1, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2911, 460, 1, 200, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2911, 462, 1, 200, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3774, 428, 4000, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3774, 454, 4000, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3774, 455, 4000, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3774, 456, 4000, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3774, 464, 4000, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3774, 466, 4000, 0, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (9, 465, 1, 11, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (2078, 465, 1, 759.5, 0);
INSERT INTO dettaglio_carichi (idarticolo, idcarico, qta, prezzo_acquisto, sconto) VALUES (3774, 465, 1, 0, 0);


--
-- Data for Name: dettaglio_ordini; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3586, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3587, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3588, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3589, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 9, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3590, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 137, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 176, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3598, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3599, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3591, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3592, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3593, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3594, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3595, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3596, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3597, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 192, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3600, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3601, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3602, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3603, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3604, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 221, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 224, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3605, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3606, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3607, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3608, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3609, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3610, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3611, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3612, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3613, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3614, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3630, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3631, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3632, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3633, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3634, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3635, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3636, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3637, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3638, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3639, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3655, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 274, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3656, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3657, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 283, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3660, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3661, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3665, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3666, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 309, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3667, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3668, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3669, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3670, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3671, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3672, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3673, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3674, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3675, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3676, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3677, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3684, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3685, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3686, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 357, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3615, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3616, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3617, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3618, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 367, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3619, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3620, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3621, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3622, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3623, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3624, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3625, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3626, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3627, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3628, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3629, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3640, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3641, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3642, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3643, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3644, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3645, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3646, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3647, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3648, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3649, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3650, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3651, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3652, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3653, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3654, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3658, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3659, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3662, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3663, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3664, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 536, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 537, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3678, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3679, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 609, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3680, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3681, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3682, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3683, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 634, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 635, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3687, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3688, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 640, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 642, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3689, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 646, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 648, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3690, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3691, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 654, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 656, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3692, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3693, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3700, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3701, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 710, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3702, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3703, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3704, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3705, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 726, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3706, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3707, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3708, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3709, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3710, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 752, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3711, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3712, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 764, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3713, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 792, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3714, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3715, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 805, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 806, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3716, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3717, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3718, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3719, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 835, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3732, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3733, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3734, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3735, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3736, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 899, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3737, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3738, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3739, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3740, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 957, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3766, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 979, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3767, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3768, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1006, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1008, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3769, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3770, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3771, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3773, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3774, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3775, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3786, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3787, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1085, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1100, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1139, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1146, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1158, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1159, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1164, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1172, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1184, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3694, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3695, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1206, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1207, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3696, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1213, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3697, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3698, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3699, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1249, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3720, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3721, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1265, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3722, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3723, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1271, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1272, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1273, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1292, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3724, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1320, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1321, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3725, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1327, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1328, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1329, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3726, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3727, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3741, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3742, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1380, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1384, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3743, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3744, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3745, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1454, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1455, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3746, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1473, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1479, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1480, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3747, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3748, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3772, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3776, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3777, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1544, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3778, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1546, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3779, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3780, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3781, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3782, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1570, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1578, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3783, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3784, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3785, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1589, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1593, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1594, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1596, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3788, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3789, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3790, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1625, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1629, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1631, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1642, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1648, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1650, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3728, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1665, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3729, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3730, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3731, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3749, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1681, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1682, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3750, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3751, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1685, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1687, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3752, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1689, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1690, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3753, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3754, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3755, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1699, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1701, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3756, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3757, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3758, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1719, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3759, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3760, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1776, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3761, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3762, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1800, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3763, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3764, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1804, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3765, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1878, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1882, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1898, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1899, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1901, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1945, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1946, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1947, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1948, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1959, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1968, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1969, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1997, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1998, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 1999, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2000, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2001, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2029, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2030, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2048, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2057, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2060, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2078, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2079, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2083, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2085, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2101, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2108, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2109, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2162, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2168, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2186, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2191, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2200, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2201, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2206, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2212, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2215, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2247, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2325, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2339, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2368, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2383, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2411, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2417, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2425, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2439, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2440, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2450, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2474, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2484, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2493, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2512, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2523, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2549, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2553, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2554, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2576, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2581, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2586, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2597, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2606, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2616, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2625, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2626, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2627, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2638, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2642, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2647, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2669, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2679, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2680, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2681, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2684, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2686, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2689, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2698, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2699, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2700, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2706, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2714, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2715, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2716, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2730, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2732, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2738, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2739, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2741, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2743, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2744, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2746, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2752, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2753, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2757, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2758, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2765, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2769, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2773, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2774, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2778, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2784, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2785, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2786, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2790, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2801, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2802, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2819, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2826, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2831, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2832, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2841, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2844, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2845, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2847, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2848, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2854, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2889, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2891, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2893, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2894, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2895, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2896, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2898, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2899, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2900, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2901, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2911, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2912, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2913, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2942, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2948, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2949, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2954, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2955, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2960, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2972, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2986, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2987, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2991, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2997, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 2999, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3001, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3007, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3016, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3019, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3024, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3025, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3026, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3027, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3030, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3031, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3032, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3034, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3043, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3045, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3047, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3048, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3050, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3051, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3056, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3068, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3072, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3073, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3074, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3075, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3076, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3085, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3086, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3089, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3095, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3096, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3098, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3100, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3102, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3109, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3111, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3112, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3113, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3118, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3121, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3122, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3123, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3128, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3139, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3143, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3157, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3166, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3167, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3170, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3176, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3177, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3178, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3179, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3182, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3189, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3192, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3200, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3202, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3206, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3207, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3210, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3212, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3213, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3214, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3215, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3248, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3249, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3251, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3253, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3266, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3268, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3269, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3272, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3274, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3275, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3276, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3278, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3279, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3280, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3285, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3299, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3300, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3301, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3303, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3305, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3306, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3309, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3310, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3314, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3315, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3323, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3327, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3329, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3330, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3332, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3334, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3335, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3340, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3351, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3354, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3361, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3363, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3364, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3365, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3368, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3369, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3370, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3372, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3374, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3383, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3389, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3391, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3392, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3394, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3395, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3397, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3400, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3401, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3415, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3421, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3438, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3439, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3440, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3441, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3448, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3449, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3450, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3452, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3454, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3462, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3464, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3465, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3466, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3467, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3468, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3469, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3480, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3481, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3482, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3487, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3488, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3489, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3490, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3491, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3492, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3493, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3501, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3502, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3508, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3509, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3510, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3511, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3515, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3516, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3521, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3526, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3528, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3532, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3533, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3542, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3545, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3547, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3550, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3552, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3558, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3561, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3562, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3563, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3564, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3565, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3566, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3567, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3568, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3569, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3570, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3571, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3572, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3573, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3574, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3575, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3576, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3577, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3578, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3579, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3580, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3581, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3582, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3583, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (0, 3584, 0, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (11, 9, 2, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (12, 367, 2, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (13, 9, 2, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (14, 640, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (15, 9, 2, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (18, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (19, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (20, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (21, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (22, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (23, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (24, 9, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (26, 3774, 1111.55, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (27, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (28, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (29, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (30, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (31, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (32, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (33, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (34, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (35, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (36, 3774, 1000, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (37, 3774, 123, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (38, 3774, 1112, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (39, 3774, 111, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (40, 3774, 221, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (41, 3774, 1, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (42, 3774, 123, 0, NULL, NULL, NULL);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (41, 2083, 1, 0, 0, 0, 0);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (41, 1139, 1, 0, 0, 0, 0);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (41, 1589, 1, 0, 0, 0, 0);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (1, 3774, 1, 0, 0, 0, 0);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (1, 3738, 1, 0, 0, 0, 0);
INSERT INTO dettaglio_ordini (idordine, idarticolo, qta, sconto, prezzo_acquisto, prezzo_vendita, iva) VALUES (1, 3075, 1, 0, 0, 0, 0);


--
-- Data for Name: fornitori; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (3, NULL, 'ALFA S.R.L.', '123254010', NULL, 'VIA MILANO, 75', '100', 'ROMA', '', NULL, '', NULL, NULL, '', 'ALFA01', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (5, NULL, 'ALPEX SRL', '1918150796', NULL, 'VIALE EMILIA 67/Q', '88063', 'CATANZARO LIDO', '', NULL, '', NULL, NULL, 'ELETTRODOMESTICI DA INCASSO SFUSI', 'ALPEX', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (6, NULL, 'ARMOBIL SPA', '382660934', NULL, 'VIA A.VOLTA, 20', '33080', 'PRATA DI PORDENONE', '0434-610083', NULL, '0434-610289', NULL, NULL, 'CAMERE, SALE, SOGGIORNI, TAVOLI VETRO', 'ARMOBI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (7, NULL, 'ARTI TERMOSANITARI DI CAPUTO', '4212340725', NULL, 'VIA G.PAPALIA, 3-C/12', '', 'BARI', '080-5544001', NULL, '080-5544001', NULL, NULL, 'RUBINETTERIE', 'ARTI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (8, NULL, 'ARTMOBILI DI SALVATORE DI MARIA', '2860150875', NULL, 'VIA BRUXELLES, 18', '95027', 'S. GREGORIO DI CATANIA', '', NULL, '', NULL, NULL, 'INGRESSI, TAVOLI VETRO COMPLEMENTI', 'ARTMOB', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (9, NULL, 'ATLAS MOBILIFICIO SRL', '50350529', NULL, 'TRAV. VALDICHIANA EST, 119', '53049', 'TORRITTA DI SIENA', '', NULL, '', NULL, NULL, 'CAMERE E SALE', 'ATLAS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (10, NULL, 'ASTOR MOBILI SRL', '3350550285', NULL, 'VIA BETTINE, 2', '35030', 'SACCOLONGO', '049-8015033', NULL, '049-8015867', NULL, NULL, 'INGRESSI, TAVOLINI E VARIE MODERNI', 'ASTOR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (11, NULL, 'AZZURRA IMBOTTITI DI ZAFFINI P.', '1156210419', NULL, 'VIA LOMBARDIA, 3', '61020', 'GALLO DI PETRIANO', '0722-52791/905', NULL, '0722-52905', NULL, NULL, 'SALOTTI E DIVANI', 'AZZURR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (12, NULL, 'BERLONI MOBILI SPA', '451260418', NULL, 'VIA DELL''INDUSTRIA, 28', '61100', 'PESARO', '', NULL, '', NULL, NULL, 'CUCINE COMPONIBILI', 'BERLON', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (13, NULL, 'BEZZETTO F.LLI SNC', '270220239', NULL, 'LOCALITA'' MERLE', '37050', 'ISOLA RIZZA', '', NULL, '', NULL, NULL, 'MOBILI D''ARTE', 'BEZZET', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (14, NULL, 'BONTEMPI SPA', '1201730429', NULL, 'VIA DIRETTISSIMA DEL CONERO, 71', '60021', 'CAMERANO', '', NULL, '', NULL, NULL, 'LETTI, TAVOLINI, SEDIE, CARRELLI E VARIE', 'BONTEM', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (15, NULL, 'BRENELLI AMEDEO SPA', '218800936', NULL, 'VIA DEI SOLDI, 11', '33070', 'BRUGNERA', '0434-624056/584', NULL, '0434-623973', NULL, NULL, 'CAMERE, SALE, SOGGIORNI, PARETI', 'BRENEL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (16, NULL, 'CANGURO NUOVA', '349960286', NULL, 'VIA MOSCHINE', '35030', 'BAONE', '0429-2883', NULL, '0429-2884', NULL, NULL, 'SCARPIERE', 'CANGUR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (17, NULL, 'CUCINE DEL LEVANTE DI CAVONE E', '454880725', NULL, 'VIA MACCHIE STRADA PRIVATA', '70057', 'BARI PALESE', '080-5522318', NULL, '', NULL, NULL, 'CUCINE COMPONIBILI', 'CDL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (18, NULL, 'COMER SUD SRL', '1603340710', NULL, 'VIA OFANTINA, 0,500', '71042', 'CERIGNOLA', '0885-414691', NULL, '0885-414691', NULL, NULL, 'DIVANI E SALOTTI (3P LETTO)', 'COMER', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (19, NULL, 'COMPAS INDUSTRIA MOBILI', '73640930', NULL, 'VIA OPITERGINA, 103', '33080', 'PRATA', '0434-620092/3/4', NULL, '', NULL, NULL, 'SALE, SOGGIORNI, PARETI', 'COMPAS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (20, NULL, 'CORSINI SPA', '110350410', NULL, 'STRADA PROVINCIALE, 65', '61025', 'MONTELABBATE', '0721-4921', NULL, '0721-492207', NULL, NULL, 'CAMERE SALE', 'CORSIN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (21, NULL, 'CORSO ARREDAMENTI SRL', '749170239', NULL, 'VIA MERLE, 75', '37050', 'ISOLA RIZZA', '045-7100935', NULL, '045-7102099', NULL, NULL, 'MOBILI ARTE POVERA NOCE MASSELLO', 'CORSO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (22, NULL, 'DELL''AQUILA', '597020718', NULL, 'VIA TORREMAGGIORE KM. 0,850', '71016', 'SAN SEVERO', '0882-72333', NULL, '0882-376184', NULL, NULL, 'RACCOGLITORE', 'DELL''A', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (23, NULL, 'DFD INDUSTRIA MOBILI SRL', '315150417', NULL, 'STRADA MONTECALVELLO, 4', '61020', 'S. ANGELO IN LIZZOLA', '0721-910132/3', NULL, '0721-910488', NULL, NULL, 'CAMERE E SALE', 'DFD', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (24, NULL, 'DUEGI SRL FABBRICA MOBILI', '1848400238', NULL, 'VIA SANTA CROCE, 28/C', '37050', 'ANGIARI', '0442-97099', NULL, '0442-97171', NULL, NULL, 'MOBILI IN STILE', 'DUEGI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (25, NULL, 'EFFEDUE SPA INDUSTRIA PER L''ARREDAMENTO', '', NULL, 'VIA DI VITTORIO, 2 ZONA IND.LE', '61022', 'TALACCHIO DI COLBORDOLO', '', NULL, '', NULL, NULL, 'CAMERE E SALE', 'EFFEDU', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (26, NULL, 'ELVE INDUSTRIA MOBILI SPA', '73830937', NULL, 'VIA N. SAURO, 111', '33070', 'BRUGNERA', '0434-624933', NULL, '0434-623460', NULL, NULL, 'CAMERE, SALE, SOGGIORNI, PARETI', 'ELVE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (27, NULL, 'ESALINEA SRL', '735760415', NULL, 'VIA PANTANELLI, 35/41', '61025', 'MONTELABBATE', '', NULL, '', NULL, NULL, 'INGRESSI', 'ESALIN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (28, NULL, 'FANFANI ANDREA', '1529690487', NULL, 'VIA CHIANTIGIANA, 202/B', '50020', 'GINESTRA', '055-8713203', NULL, '055-8713531', NULL, NULL, 'SPECCHIERE E TAVOLINI IN STILE', 'FANFAN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (29, NULL, 'FEG MOBILI SRL', '929430411', NULL, 'VIA DELL''INDUSTRIA, 78', '61020', 'CHIUSA DI GINESTRETO', '0721-481101', NULL, '0721-481101', NULL, NULL, 'CAMERETTE E SOGGIORNI ECONOMICI', 'FEG', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (30, NULL, 'FRIULIMPORT SRL', '238900930', NULL, 'VIA DELLA CHIESA, 10 FRAZ. VILLANO', '33080', 'PRATA DI PORDENONE', '0434-626620', NULL, '0434-626877', NULL, NULL, 'SALOTTI E DIVANI ANTIMACCHIA', 'FRIULI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (31, NULL, 'FULGINI ORILIO E F.LLI SPA', '110800414', NULL, 'VIA FERMO, 32', '61100', 'PESARO', '', NULL, '', NULL, NULL, 'CAMERE E SALE', 'FULGIN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (32, NULL, 'FUTURA SNC DI L. SEGAT & C.', '1170360935', NULL, 'VIA PASCOLI, 11', '33080', 'VILLANOVA DI PRATA', '0434-626910', NULL, '0434-626093', NULL, NULL, 'INGRESSI E COMPLEMENTI', 'FUTURA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (33, NULL, 'GIMAS SRL', '1286890403', NULL, 'VIA SACCO E VANZETTI, 86', '47010', 'TERRA DEL SOLE CASTROCARO', '0543-766886', NULL, '0543-767884', NULL, NULL, 'SALOTTI E DIVANI', 'GIMAS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (34, NULL, 'GIPI LE SEDIE SNC', '925640427', NULL, 'ZONA IND.LE G.MO BRODOLINI, 10', '60025', 'LORETO', '071-7500351', NULL, '071-7500336', NULL, NULL, 'TAVOLI E SEDIE', 'GIPI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (35, NULL, 'GRATTAROLA SPA', '231300138', NULL, 'VIA PROVINCIALE, 2', '22040', 'BINDO DI CORTENOVA', '0341-900355', NULL, '0341-900412', NULL, NULL, 'MOBILI IN LEGNO RUSTICO', 'GRATTA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (36, NULL, 'IRILUX', '520900523', NULL, 'PIAZZETTA TORRICELLI, 8', '53036', 'POGGIBONSI', '', NULL, '', NULL, NULL, 'LAMPADARI E ILLUMINAZIONE', 'IRILUX', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (37, NULL, 'ITALMOBILI SRL', '1205840414', NULL, 'VIA F. TURATI, 10', '61032', 'FANO', '0721-854232/34', NULL, '0721-458232', NULL, NULL, 'SOGGIORNI, PARETI, SALE', 'ITALMO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (38, NULL, 'LINEAEMME SRL', '1205850413', NULL, 'VIA DEL PIANO, 63', '61022', 'TALACCHIO DI COLBORDOLO', '0721-', NULL, '', NULL, NULL, 'CUCINE COMPONIBILI', 'LINEAE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (39, NULL, 'MAIELLARO SRL', '4627080726', NULL, 'STRADA STATALE 98 KM. 80+476', '70026', 'MODUGNO', '080-5326850', NULL, '080-5326973', NULL, NULL, 'COMPLEMENTI DI ARREDAMENTO', 'MAIELL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (40, NULL, 'MATIS SUD RESINE SRL', '238480875', NULL, 'ZONA IND.LE - STRADA PELACANE, 8/10', '95121', 'CATANIA', '095-291227', NULL, '095-291227', NULL, NULL, 'MATERASSI GUANCIALI', 'MATIS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (41, NULL, 'MAZZA GEOM. FRANCESCO', '796560795', NULL, 'VIA CROTONE (PROP.RICCI)', '88063', 'CATANZARO LIDO', '0961-32298', NULL, '0961-31926', NULL, NULL, 'COMPLEMENTI DI ARREDAMENTO', 'MAZZA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (42, NULL, 'META SRL', '1783830787', NULL, 'CONTRADA MOTTA', '87040', 'CASTROLIBERO', '0984-852290', NULL, '0984-852290', NULL, NULL, 'DEPOSITO C/DISTRIZUBIONE ELETTR.INCASSO', 'META', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (43, NULL, 'MOBILEXTRA SRL', '92870518', NULL, 'LOC. VACCHERECCIA - CAVRIGLIA', '52027', 'S. GIOVANNI VALDARNO', '055-941001/698', NULL, 'FAX 055-942401', NULL, NULL, 'CAMERETTE, CAMERE, SOGGIORNI, PARETI', 'MOBILE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (44, NULL, 'MOBILMONEY SRL', '1249190412', NULL, 'CONTRADA MARTUCCI SNC', '72015', 'FASANO', '', NULL, '', NULL, NULL, 'CAMERE ECONOMICHE', 'MOBILM', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (45, NULL, 'MORFEUS SPA', '166450411', NULL, 'VIA CASINELLA, 36', '61022', 'TALACCHIO DI COLBORDOLO', '', NULL, '', NULL, NULL, 'MATERASSI E RETI', 'MORFEU', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (46, NULL, 'NANDI GIUSEPPE MOBILIFICIO', '345220412', NULL, 'VIA DEL PIANO, 36', '61022', 'TALACCHIO DI COLBORDOLO', '0721-478474', NULL, '0721-478474', NULL, NULL, 'SOGGIORNI E PARETI', 'NANDI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (47, NULL, 'NOVALINEA SPA', '1091430411', NULL, 'VIA DELLA PRODUZIONE, 54/59', '61025', 'MONTELABBATE', '', NULL, '', NULL, NULL, 'CUCINE COMPONIBILI', 'NOVALI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (48, NULL, 'PACINI E CAPPELLINI', '1310530132', NULL, 'VIA KENNEDY, 18', '22060', 'CABIATE', '031-767695', NULL, '031-767588', NULL, NULL, 'RACCOGLITORE', 'PACINI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (49, NULL, 'PASI F.LLI MOBILIFICIO SNC', '551340235', NULL, 'VIA FARFUSOLA, 10', '37050', 'BONAVICINA DI S. PIETRO DI MO', '045-7103432', NULL, '045-7103991', NULL, NULL, 'MOBILI IN STILE E ARTE POVERA', 'PASI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (50, NULL, 'POLTROMOT SRL', '1266540473', NULL, 'VIA FIRENZE, 228', '51039', 'QUARRATA', '0573-72508', NULL, '0573-738434', NULL, NULL, 'SALOTTI E DIVANI', 'POLTRO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (51, NULL, 'POLYWOOD SPA', '1266640414', NULL, 'VIA FABBRECCIA, 33 VILLA FASTIGI', '61100', 'PESARO', '0721-2851', NULL, '0721-281924', NULL, NULL, 'CAMERE', 'POLYWO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (52, NULL, 'PRESOTTO RUGGERO SPA', '66890930', NULL, 'VIA MPRET, 13', '33070', 'MARON DI BRUGNERA', '0434-623521', NULL, '0434-624685', NULL, NULL, 'CAMERETTE, CAMERE, PARETI', 'PRESOT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (53, NULL, 'PRIMULA SRL', '953930963', NULL, 'VIA VOLONTARI DEL SANGUE, 67', '20025', 'LISSONE', '039-244051', NULL, '039-2440540', NULL, NULL, 'RACCOGLITORE', 'PRIMUL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (54, NULL, 'ROSINI F.LLI FRANCO E GIULIANO', '256560475', NULL, 'VIA PIEMONTE, 15/17', '51039', 'QUARRATA', '', NULL, '', NULL, NULL, 'SALOTTI E DIVANI', 'ROSINI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (55, NULL, 'ROSSETTO ARREDAMENTI SPA', '66430935', NULL, 'VIA FRIULI, 39', '33080', 'PUJA DI PRATA', '0434-621555', NULL, '0434-621844', NULL, NULL, 'CAMERE, SALE', 'ROSSET', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (56, NULL, 'ROSSIGNOLI CAV. LUIGI', '117640235', NULL, 'VIA DOSSI, 122', '37058', 'SANGUINETTO', '0442-330677', NULL, '0442-331125', NULL, NULL, 'MOBILI IN STILE', 'ROSSIG', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (58, NULL, 'SABATO ROCCO', '341880797', NULL, 'VIA CHYUBICA SNC', '88095', 'SELLIMA MARINA', '0961-964375', NULL, '', NULL, NULL, 'ARTIGIANO COSTRUTTORE MOBILI', 'SABATO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (59, NULL, 'SELENIA SRL', '1211320419', NULL, 'VIA PANTANELLI, 3/9', '61025', 'MONTELABBATE', '', NULL, '', NULL, NULL, 'CAMERE', 'SELENI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (60, NULL, 'SILOMA SRL', '278790266', NULL, 'VIA TOTI DAL MONTE, 143', '31050', 'BARBISANO', '0438-8391', NULL, '0438-842361', NULL, NULL, 'CAMERETTE, CAMERE, PARETI', 'SILOMA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (61, NULL, 'SOLMET SPA', '627180136', NULL, 'VIA PER BULGORELLO, 5', '22070', 'VERTEMATE CON MINOPRIO', '', NULL, '', NULL, NULL, 'LETTI OTTONE, IN FERRO, ALTRO', 'SOLMET', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (62, NULL, 'SPAR MOBILI SRL', '1040140418', NULL, 'VIA DELLA PRODUZIONE SNC', '61020', 'CHIUSA DI GINESTRETO', '0721-482070', NULL, '0721-482084', NULL, NULL, 'CAMERE, SALE', 'SPAR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (63, NULL, 'TIEFFE MOBILI', '637970419', NULL, 'VIA SAN MICHELE, 62', '61020', 'MONTECCHIO DI S. ANGELO IN L.', '0721-498499', NULL, '0721-499794', NULL, NULL, 'CAMERETTE, CAMERE, PARETI', 'TIEFFE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (64, NULL, 'TOMASELLA INDUSTRIA MOBILI SAS', '66920935', NULL, 'VIA UNGARESCA, 16', '33070', 'BRUGNERA', '', NULL, '', NULL, NULL, 'CAMERE E CAMERETTE', 'TOMASE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (65, NULL, 'TRECI SALOTTI', '886270727', NULL, 'S.S.96 - CIRCONV.PER GRAVINA - ZONA IND.', '70022', 'ALTAMURA', '080-8701224', NULL, '080-8701064', NULL, NULL, 'SALOTTI E DIVANI', 'TRECI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (66, NULL, 'TSB MOBILI SPA', '933180416', NULL, 'VIA LOMBARDIA, 21', '61100', 'PESARO', '0721-453442', NULL, '0721-456692', NULL, NULL, 'CAMERE, SALE, PARETI', 'TSB', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (67, NULL, 'VALENTE F.LLI SDF', '822260139', NULL, 'VIA STAZIONE - ZONA ARTIGIANALE', '22060', 'CARIMATE', '031-791256', NULL, '031-791256', NULL, NULL, 'LETTI FERRO E OTTONE', 'VALENT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (68, NULL, 'VAMA CUCINE MALBO SRL', '1221660416', NULL, 'VIA VILLAGRANDE, 222', '61024', 'VILLAGRANDE DI MOMBAROCCIO', '0721-470316', NULL, '0721-471188', NULL, NULL, 'CUCINE COMPONIBILI', 'VAMA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (69, NULL, 'VITALFLEX 2 & C. SNC', '1950990794', NULL, 'VIA ALDO MORO', '', 'CASABONA', '0962-82732', NULL, '', NULL, NULL, 'RETI METALLO E DOGHE, MATERASSI', 'VITALF', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (70, NULL, 'WALMAR CO. MO. T. SRL', '1844610483', NULL, 'LOCALITA'' PETRICCIO, 128', '50052', 'CERTALDO', '0571-668609/58', NULL, '0571-663345', NULL, NULL, 'MOBILI IN STILE E RACCOGLITORE', 'WALMAR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (71, NULL, 'WOOL COTTON NEW', '3740770726', NULL, 'S.S.16 KM.770,300', '70052', 'BISCEGLIE', '080-3951556/475', NULL, '', NULL, NULL, 'MATERASSI, GUANCIALI, RETI', 'WOOLCO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (72, NULL, 'GOLDLINE', '', NULL, '', '', '', '', NULL, '', NULL, NULL, 'ARMADI', 'GOLDLI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (73, NULL, 'ZAMAGNA S.R.L.', '2015470400', NULL, 'VIA QUINTO BUCCI, 95', '47023', 'CESENA', '0547-324155', NULL, '0547-325525', NULL, NULL, 'RACCOGLITORE', 'ZAMAGN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (74, NULL, 'IMAR S.P.A.', '188400279', NULL, 'VIA CALLALTA, 44', '30020', 'PRAMAGGIORE', '0421-799951', NULL, '0421-799973', NULL, NULL, 'CAMERE, SALE', 'IMAR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (75, NULL, 'MARTEX S.P.A.', '73550931', NULL, 'VIA S. PELLICO, 32', '33080', 'PRATA DI PORDENONE', '', NULL, '', NULL, NULL, 'CAMERE', 'MARTEX', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (76, NULL, 'MOBILIFICIO LOMA', '975450412', NULL, 'VIA DEL PIANO, 52', '61022', 'TALACCHIO DI COLBORDOLO', '', NULL, '', NULL, NULL, 'CAMERE', 'LOMA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (77, NULL, 'ITALSTILE S.P.A.', '344820410', NULL, 'VIA DELLE INDUSTRIE, 60', '61025', 'MONTELABBATE', '', NULL, '', NULL, NULL, 'SALE, SOGGIORNI, SALOTTI, INGRESSI,PARET', 'ITALST', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (78, NULL, 'MARCONCINI ANTONIO ENZO', '308370238', NULL, 'VIA L. VECCHINI, 1', '37052', 'CASALEONE', '', NULL, '', NULL, NULL, 'MOBILI IN STILE', 'MARCON', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (79, NULL, 'MARKA S.P.A. IND. MOBILI COMP.', '753750264', NULL, 'VIA BORGO FURO, 7', '31010', 'FALZE'' DI PIAVE', '', NULL, '', NULL, NULL, 'CAMERETTE, PARETI', 'MARKA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (80, NULL, 'MOBILSYSTEM S.P.A.', '190110411', NULL, 'VIA DELL''ECONOMIA, S.N.C.', '61020', 'CHIUSA DI GINESTRETO', '0721-481180/330', NULL, '0721-481271', NULL, NULL, 'CAMERETTE, CAMERE, PARETI', 'MOBILS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (81, NULL, 'MOBILFRAM S.R.L.', '639240720', NULL, 'VIA CROCIFISSO, 2/A', '70126', 'BARI', '080-5481333', NULL, '080-5481716', NULL, NULL, 'RACCOGLITORE', 'MOBILF', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (82, NULL, 'DOIMO CITY LINE S.R.L.', '2300360266', NULL, 'VIA MONTEGRAPPA, 142', '31010', 'MOSNIGO DI MORIAGO', '0438-892944', NULL, '0438-892922', NULL, NULL, 'CAMERETTE, PARETI', 'DOIMO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (83, NULL, 'DEMAR MOBILI S.R.L.', '1042030930', NULL, 'VIA E. RIGO, 17', '33080', 'PRATA DI PORDENONE', '0434-621830', NULL, '0434-610140', NULL, NULL, 'MOBILI IN LEGNO RUSTICO', 'DEMAR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (84, NULL, 'MOREX S.P.A.', '197300262', NULL, 'VIA ASOLANA, 27', '31017', 'CRESPANO DEL GRAPPA', '', NULL, '', NULL, NULL, 'ARTICOLI IN OTTONE E VETRO', 'MOREX', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (85, NULL, 'LINEA ITALIA S.R.L. DI GIUNCHETTI F.', '1126120417', NULL, 'VIA LORENZETTI, 38', '61022', 'TALACCHIO DI COLBORDOLO', '', NULL, '', NULL, NULL, 'RACCOGLITORE', 'GIUNCH', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (86, NULL, 'NUOVA IMPERO ARREDAMENTI', '374330520', NULL, 'VIA CAMPANIA, 17', '53036', 'POGGIBONSI', '', NULL, '', NULL, NULL, 'INGRESSI', 'IMPERO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (87, NULL, 'CAMPOLONGO SALOTTIFICIO TRAPUNTIFICIO', '', NULL, 'VIA N. SAURO, 23', '', 'BATTIPAGLIA', '', NULL, '', NULL, NULL, 'SALOTTI E DIVANI C/LETTO', 'CAMPOL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (88, NULL, 'C.&G. SALOTTI S.N.C.', '670850403', NULL, 'VIA B. PARTISANI, 1 ZONA IND.LE', '47010', 'FIUMANA DI PREDAPPIO', '', NULL, '', NULL, NULL, 'SALOTTI E DIVANI', 'C.&G.', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (89, NULL, 'STYLING S.R.L. GRUPPO BONALDO', '980020283', NULL, 'VIA DELL''INDUSTRIA, 2 - ZONA IND.', '35010', 'BORGORICCO', '049-9335616', NULL, '049-9335281', NULL, NULL, 'SALOTTI, POLTRONE E DIVANI LETTI', 'STYLIN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (90, NULL, 'LA METEORA S.R.L.', '4449180720', NULL, 'VIA ISERNIA, 41', '70022', 'ALTAMURA', '080-842968', NULL, '', NULL, NULL, 'SALOTTI E DIVANI', 'METEOR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (91, NULL, 'FIMARF TV S.R.L.', '102010790', NULL, 'VIA CAPPUCCINI, 65/67/69', '88074', 'CROTONE', '', NULL, '', NULL, NULL, 'ELETTRODOMESTICI', 'FIMARF', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (92, NULL, 'EUROSPINE CASA S.R.L.', '1151290416', NULL, 'VIA PANTANELLI, 6', '61025', 'MONTELABBATE', '0721-498104', NULL, '0721-498350', NULL, NULL, 'SCARPIERE E VARIE', 'EUROSP', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (93, NULL, 'FALPA LE SEDUTE I PIANI S.R.L.', '473220655', NULL, 'VIA STRAZA S.C.N.', '84013', 'CAVA DE'' TIRRENI', '', NULL, '', NULL, NULL, 'SEDIE E TAVOLI', 'FALPA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (94, NULL, 'MERCATONE PIU'' S.R.L.', '1172670414', NULL, 'VIA DEI PIOPPI S.C.', '61100', 'PESARO', '0721-403444', NULL, '0721-403480', NULL, NULL, 'MERCATONE', 'MERCAT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (95, NULL, 'IDEA90 ARREDAMENTI S.R.L.', '1158410413', NULL, 'VIA G. ROSSA, 11', '', 'TALACCHIO DI COLBORDOLO', '0721-479357', NULL, '0721-479188', NULL, NULL, 'SOGGIORNI, PARETI, STUDIO,SALE', 'IDEA90', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (96, NULL, 'DE MARIA S.R.L.', '', NULL, '', '', 'MISTERBIANCO', '', NULL, '', NULL, NULL, 'INGRESSI FABBRICA CHIUSA', 'DEMARI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (97, NULL, 'TARBA MOBILI S.N.C.', '244540415', NULL, 'VIA LORENZETTI, 51', '61022', 'TALACCHIO DI COLBORDOLO', '0721-478311', NULL, '0721-478137', NULL, NULL, 'MOBILI IN STILE', 'TARBA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (98, NULL, 'VERTAL S.R.L.', '967350414', NULL, 'VIA DEL PIANO, 50', '61022', 'TALACCHIO DI COLBORDOLO', '0721-479076', NULL, '0721-479076', NULL, NULL, 'INGRESSI', 'VERTAL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (99, NULL, 'VIGORFLEX DI GIRONDA GEOM. FRANCESCO', '1485070799', NULL, 'S.S.106 - ZONA IND.LE', '', 'TORRE MELISSA', '', NULL, '', NULL, NULL, 'MATERASSI', 'VIGORF', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (100, NULL, 'UGOLINI SEDIA TONY S.R.L.', '1271650416', NULL, 'VIA DEL PIANO, 18/A 31', '61022', 'TALACCHIO DI COLBORDOLO', '0721-478301', NULL, '0721-479377', NULL, NULL, 'SEDIE E TAVOLI', 'UGOLIN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (101, NULL, 'FLORIDA MOBILIFICIO S.P.A.', '72400930', NULL, 'VIA E. GABBANA, 72', '33080', 'PRATA DI PORDENONE', '0434-612111', NULL, '0434-621766', NULL, NULL, 'CAMERE, SALE, SOGGIORNI, PARETI, BAR', 'FLORID', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (102, NULL, 'NOVAFLEX S.R.L.', '90670423', NULL, 'VIA MARCHE, 40', '60030', 'MONSANO', '', NULL, '', NULL, NULL, 'MATERASSI E RETI', 'NOVAFL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (103, NULL, 'ARRITAL CUCINE', '255390932', NULL, 'VIA CASUT, 56', '33074', 'CASUT DI FINTANAFREDDA', '0434-999440', NULL, '0434-999728', NULL, NULL, 'CUCINE COMPONIBILI', 'ARRITA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (104, NULL, 'PISANI ROMUALDO', '580200723', NULL, 'VIA S. MARIA, 76', '70043', 'MONOPOLI', '', NULL, '', NULL, NULL, 'AMBULANTE QUADRI SACRI E DA SALA', 'PISANI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (105, NULL, 'CAPUTO & C. S.N.C.', '1709540791', NULL, 'VIA VARIANTE EST.', '88070', 'CASABONA', '0962-82719', NULL, '', NULL, NULL, 'RETI DA LETTO', 'CAPUTO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (106, NULL, 'A.&M. MOBILI S.R.L.', '112720412', NULL, 'VIA DI VITTORIO S.N.C.', '61022', 'TALACCHIO DI COLBORDOLO', '0721-917342/218', NULL, '0721-912116', NULL, NULL, 'CAMERE E SALE', 'A.&M.', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (107, NULL, 'PIQUATTRO GRUPPO IND.LE S.R.L.', '3650860723', NULL, 'VIA APPIA ANTICA KM.13,700 Z.I.', '75100', 'ALTAMURA', '', NULL, '', NULL, NULL, 'SALOTTI E DIVANI', 'PIQUAT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (108, NULL, 'RICERCHE E DESIGN S.R.L.', '2899170654', NULL, 'VIA XXV LUGLIO, 251', '84013', 'CAVA DE'' TIRRENI', '', NULL, '', NULL, NULL, 'SEDIE E TAVOLI', 'RED', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (109, NULL, 'ZANCA S.R.L.', '1283940417', NULL, 'VIA VILLAGRANDE, 234', '61024', 'MOMBAROCCIO', '0721-470538', NULL, '0721-471191', NULL, NULL, 'CAMERE, SALE, SOGGIORNI', 'ZANCA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (110, NULL, 'CAPUCCI MOBILIFICIO S.N.C.', '932040413', NULL, 'VIA PANTANELLI, 1/1', '61025', 'MONTELABBATE', '0721-499101', NULL, '0721-499101', NULL, NULL, 'TAVOLI RUSTICI', 'CAPUCC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (111, NULL, 'BRUNO PIOMBINI S.R.L.', '2326260235', NULL, 'VIA MERLE, 80', '37080', 'ISOLA RIZZA', '045-7100677', NULL, '045-7103308', NULL, NULL, 'MOBILI D''ARTE', 'PIOMBI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (112, NULL, 'LEO SERVIZI S.A.S. DI LEO GIUSEPPE & C.', '875910796', NULL, 'VIA CARRARA, 6', '88074', 'CROTONE', '0962-21547', NULL, '0962-26751', NULL, NULL, 'CONSULENTE DEL LAVORO', 'LEO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (113, NULL, 'FRANCHI FABIO S.R.L.', '838380525', NULL, 'VIA TRENTO,13', '53036', 'POGGIBONSI', '055-8078250', NULL, '055-8078373', NULL, NULL, 'POLTRONE, SEDIA, SALOTTI RECLINABILI', 'FRANCH', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (115, NULL, 'EUROPEO S.P.A.', '1171380262', NULL, 'VIALE DELLE INDUSTRIE, 5', '31040', 'CESSALTO', '0421-327148/9', NULL, '0421-327736', NULL, NULL, 'CAMERE DA LETTO', 'EUROPE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (116, NULL, 'LORENTI ELIGIO', '119750792', NULL, 'VIA M. NICOLETTA, 259', '88074', 'CROTONE', '0962-21803', NULL, '', NULL, NULL, 'RICAMBI AUTO', 'LORENT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (117, NULL, 'SATELLITE SHOP DI BASILE PAOLO S.A.S.', '1992800795', NULL, 'VIA RUFFO, 6', '88074', 'CROTONE', '0962-26658', NULL, '', NULL, NULL, 'TV, ANETNNE PARABOLICHE ELETTRONICA', 'SATELL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (119, NULL, 'MOBILSPAZIO S.R.L. PRODUZIONE MOBILI', '903200426', NULL, 'S.S.16 KM.308,900 ASPIO', '60027', 'OSIMI', '071-7108905', NULL, '071-7108412', NULL, NULL, 'CAMERETTE CLASSICHE', 'MOBSPA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (120, NULL, 'DEA SAS DI DE ANGELIS D. & C.', '2236940264', NULL, 'PIAZZALE PISTOIA, 8', '31100', 'TREVISO', '0422-433323', NULL, '0422-436323', NULL, NULL, 'RECUPERO CREDITI E SERVIZI AZIENDALI', 'DEA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (121, NULL, 'ENEL S.P.A.', '934061003', NULL, 'VIA G. MARTINI, 3', '198', 'ROMA', '167-273211', NULL, '', NULL, NULL, '', 'ENEL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (122, NULL, 'TELECOM ITALIA S.P.A.', '', NULL, 'VIA S. DALMAZZO, 15', '', 'TORINO', '', NULL, '', NULL, NULL, 'FILIALE DI CATANZARO SIANO', 'TELECO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (124, NULL, 'PUBBLICITA'' ELENCHI TELEFONICI', '471850016', NULL, 'VIA AURELIO SAFFI, 18', '10138', 'TORINO', '0984-463115', NULL, '', NULL, NULL, '', 'SEAT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (125, NULL, 'SCOPACASA E CORDUA GOMME', '1305600791', NULL, 'VIA M. NICOLETTA,299', '88074', 'CROTONE', '0962-902142', NULL, '', NULL, NULL, '', 'SCOPAC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (126, NULL, 'ERMENEGILDO MANDICA', '124450792', NULL, 'VIA BOTTEGHELLE', '88074', 'CROTONE', '0962-22600', NULL, '', NULL, NULL, 'OFFICINA RETTIFICA MOTORI', 'MANDIC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (127, NULL, 'ELETTRODIESEL MORELLI VINCENZO', '937990794', NULL, 'VIA CAVALLACCIO', '88076', 'ISOLA DI CAPO RIZZUTO', '', NULL, '', NULL, NULL, '', 'MORELL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (128, NULL, 'LINEA MONDO GRAFICA S.A.S.', '1948920796', NULL, 'VIA PROVVIDENZA, 23', '88078', 'STRONGOLI', '0962-20943', NULL, '0962-20943', NULL, NULL, 'DI CANDIGLIOTA & C.', 'MONDO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (129, NULL, 'SABATINI ANTONIO & C. S.N.C.', '831190798', NULL, 'VIA TORINO, 93', '88074', 'CROTONE', '0962-23960', NULL, '', NULL, NULL, 'CONCESSIONARIA ESCLUSIVISTA OLIVETTI', 'SABATI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (130, NULL, 'MOBILIFICIO D''ARTE MIRANDOLA S.R.L.', '99130296', NULL, 'VIA IV NOVEMBRE, 3', '45010', 'CEREGNANO', '0425-476028', NULL, '0425-476553', NULL, NULL, 'MOBILI D''ARTE PREGIATI', 'MIRAND', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (133, NULL, 'AZIEN. U.S.S.L. N.5 PRESIDIO OSPEDALIERO', '1997410798', NULL, '', '88074', 'CROTONE', '', NULL, '', NULL, NULL, '', 'OSPEDA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (134, NULL, 'A MONDADORI EDITORI S.P.A.', '', NULL, '', '20050', 'SEGRATE', '', NULL, '', NULL, NULL, 'SETTORE ABBONAMENTI C.C. N.5231', 'MONDAD', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (135, NULL, 'I.N.P.S. CROTONE C.C.P. N. 14041883', '', NULL, '', '88074', 'CROTONE', '', NULL, '', NULL, NULL, '', 'INPS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (137, NULL, 'SAPIA ROBERTO', '', NULL, 'VIA FONDO FARINA, 216', '88074', 'CROTONE', '', NULL, '', NULL, NULL, '', 'SAPIA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (141, NULL, 'ACETO S.R.L. CONCESSIONARIA RENAULT (CS)', '1029350780', NULL, 'VIA SICILIA (DI FRONTE TRIBUNALA CS)', '', 'COSENZA', '0984-393453/54', NULL, '', NULL, NULL, '', 'ACETO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (142, NULL, 'ITALNOVA S.P.A.', '911070761', NULL, 'ZONA IND.LE BARAGIANO', '85050', 'BALVANO', '0971-993781', NULL, '0971-993786', NULL, NULL, 'CUCINE COMPONIBILI', 'ITALNO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (143, NULL, 'COMUNE CIRO'' MARINA', '', NULL, '', '', 'CIRO'' MARINA', '', NULL, '0962-31266', NULL, NULL, '', 'CIRO''', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (144, NULL, 'AMORUSO LUIGI - RADIATORI AUTOVEICOLI', '1218370797', NULL, 'VIA M. NICOLETTA, 307', '88074', 'CROTONE', '0962-29961', NULL, '0962-29961', NULL, NULL, 'TUBI OLEODINAMICI E INDUSTRIALI', 'AMORUS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (145, NULL, 'PICCINATO OLINDO E FIGLI S.A.S', '71700934', NULL, 'VIA SANTISSIMA, 41', '33070', 'BRUGNERA', '0434-624800', NULL, '0434-623004', NULL, NULL, '', 'PICCIN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (146, NULL, 'NEW LINE S.R.L.', '475530416', NULL, 'VIA DEI LAGHI', '61030', 'CALCINELLI', '0721-897492', NULL, '0721-897088', NULL, NULL, 'CAMERETTE', 'NEWLIN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (147, NULL, 'MASTERSPOT COMUNICAZIONE PUBBLI-VIDEO', '2024010791', NULL, 'VIA S. MARIA DELLE GRAZIE', '88074', 'CROTONE', '0962-27262', NULL, '0962-27262', NULL, NULL, 'DI MANCUSO GIOVANNI', 'MASTER', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (148, NULL, 'COMPLEMENTI S.R.L.', '1336610421', NULL, 'VOA DELL''ARTIGIANATO, 13', '60010', 'CASINE DI OSTRA', '071-688683', NULL, '071-688684', NULL, NULL, 'RACCOGLITORE', 'COMPLE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (149, NULL, 'CENTRO SERVIZI AURELIO CAV. BAUCKNEHT""', '1943710796', NULL, 'VIA TORINO, 63', '88074', 'CROTONE', '0962 - 24646', NULL, '0962 - 25464', NULL, NULL, 'S.R.L. DI RAGG. MAIONE A. - MARCHIO A.', 'CENSER', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (150, NULL, 'PAPANDREA S.R.L.', '1055870800', NULL, 'CONTRADA PIZZICATO', '89021', 'CINQUEFRONDI', '0966-932481/2', NULL, '0966-932010', NULL, NULL, '', 'PAPAND', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (151, NULL, 'ARREDAMENTI SAN MARCO S.R.L.', '76550524', NULL, 'VIA LIGURIA, 2-4 LOC. FOSCI', '53036', 'POGGIBONSI', '0577-934447', NULL, '0577-982351', NULL, NULL, '', 'SANMAR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (154, NULL, 'ARBOFLEX S.R.L.', '1022270787', NULL, 'C.DA CAMMARATA - ZONA IND.LE', '87012', 'CASTROVILLARI', '0981-386988/9', NULL, '0981-386985', NULL, NULL, 'RACCOGLITORE', 'ARBOFL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (157, NULL, 'CAMERA DI COMMERCIO', '', NULL, 'PIAZZA CASTELLO, 20', '88074', 'CROTONE', '0962-901108', NULL, '', NULL, NULL, '', 'CAMCOM', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (158, NULL, 'UTENSILMACCHINE DI PASQUALE ELIA', '292190790', NULL, 'VIA PIGNATARO,11', '88074', 'CROTONE', '0962-20630', NULL, '0962-20630', NULL, NULL, '', 'ELIA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (161, NULL, 'COMUNE DI ISOLA DI CAPO RIZZUTO', '', NULL, '', '88076', 'ISOLA DI CAPO RIZZUTO', '', NULL, '', NULL, NULL, '', 'ISOLA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (167, NULL, 'GIUNGATA MARIA TERESA - OTTICA', '1578390799', NULL, 'VIA POGGIOREALE, 77/BIS', '88074', 'CROTONE', '0962-23065', NULL, '', NULL, NULL, '', 'GIUNGA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (168, NULL, 'TIRRENFLEX COPERTIFICIO DEL TIRRENO', '2911020655', NULL, 'VIA GALIRI, 36', '84013', 'CAVA DEI TIRRENI', '089-441227', NULL, '089-349206', NULL, NULL, 'DI PASQUALE CRISCUOLO E FIGLIO S.N.C.', 'TIRREN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (169, NULL, 'COMBERIATI ALBERTO LEGNAMI', '759120793', NULL, 'FONDO PAPANICIARO', '88074', 'CROTONE', '0962-29463', NULL, '', NULL, NULL, '', 'COMBER', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (170, NULL, 'F.LLI LETO S.A.S. DI ROBERTO LETO', '1894150794', NULL, 'VICO CHIUSO M.NICOLETTA', '88074', 'CROTONE', '0962-26850', NULL, '', NULL, NULL, 'RICAMBI AUTO', 'LETO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (171, NULL, 'NUOVA POLTROMOT S.R.L.', '518110473', NULL, 'VIA FIRENZE, 228', '51039', 'QUARRATA', '0573-72508', NULL, '', NULL, NULL, 'SALOTTI, DIVANI, LETTI IMBOTTITI', 'NPOLTR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (173, NULL, 'LA FATTORIA S.A.S. DI FORESTA ANTONIO', '1534010796', NULL, 'VIA DEL FARO', '88076', 'ISOLA DI CAPO RIZZUTO', '', NULL, '', NULL, NULL, 'CAMPEGGIO', 'FATTOR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (174, NULL, 'DECA S.N.C. DI CARDACE MARIA & C.', '2029590797', NULL, 'FONDO PAPANICIARO - ZONA IND.LE', '88074', 'CROTONE', '0962-21093', NULL, '0962-900822', NULL, NULL, 'ELETTR. X CUCINE COMPPNIBILI E VARIE', 'DECA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (175, NULL, 'BRESCIA ALDO & C. S.N.C.', '1551860792', NULL, 'VIA NAZIONALE', '88070', 'BOTRICELLO', '0961-963310', NULL, '0961-967215', NULL, NULL, 'ARTICOLI PUBBLICITARI', 'BRESCI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (177, NULL, 'ALA ARREDAMENTI S.A.', '', NULL, 'STRADA FRIGINETTO, 51', '47031', 'SERRAVALLE', '0549-905555', NULL, '0549-905599', NULL, NULL, 'CUCINE COMPONIBILI', 'ALA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (178, NULL, 'LA CROTONESE S.N.C.', '2064630797', NULL, 'VIA Iø MAGGIO, 33, 35, 37', '88074', 'CROTONE', '0962-23872', NULL, '', NULL, NULL, 'CARROZZARIA AUTORIZZATA RENAULT', 'LACROT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (180, NULL, 'IN SALOTTO DI GIUSEPPE SARDONE', '4679030728', NULL, 'S.S. 96 - KM.1,500 SONA IND.LE', '70022', 'ALTAMURA', '080-8701278', NULL, '080-8701278', NULL, NULL, 'SALOTTI E DIVANI', 'INSALO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (181, NULL, 'RAG. LEO ANTONINO', '1544180796', NULL, 'VIA CARRARA, 6', '88074', 'CROTONE', '', NULL, '', NULL, NULL, '', 'LEO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (182, NULL, 'LUCHETTA FRANCO ELETTRAUTO', '748490794', NULL, 'VIA CUTRO, 475/477', '88074', 'CROTONE', '0962-963359', NULL, '', NULL, NULL, '', 'LUCHET', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (183, NULL, 'TUTTOCAMION S.R.L.', '1170460412', NULL, 'VIA ROMA, 105', '61020', 'MONTECCHIO DI S.ANGELO IN LIZZ', '0721-497213', NULL, '0721-497875', NULL, NULL, 'CONCESSIONARIA DAF ITALIA', 'TUTTOC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (184, NULL, 'SGA INFORMATICA S.R.L.', '12304290153', NULL, 'CORSO VITTORIO EMANUELE IIø, 23', '26900', 'LODI', '0371-594412', NULL, '0371-427647', NULL, NULL, 'GRUPPO ZUCCHETTI', 'SGA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (185, NULL, 'SOC. COOP. PUBBLIGRAFIC SUD A.R.L.', '1820160792', NULL, 'VIA T. CAMPANELLA, 42', '88074', 'CROTONE', '0962-28858', NULL, '0962-28858', NULL, NULL, '', 'PUBBLI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (187, NULL, 'CENTRO RADIOLOGICO DI DIAGNOSTICA', '1524730791', NULL, 'VIA M. NICOLETTA (VICO CHIUSO)', '88074', 'CROTONE', '0962-23971', NULL, '', NULL, NULL, 'FAMILIARI DI F.CILIBERTO & C. S.A.S.', 'FAMILI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (188, NULL, 'DELPHIN ITALIA S.R.L.', '1334400395', NULL, 'VIA XX SETTEMBRE, 171', '48015', 'CERVIA', '055-73661', NULL, '055-7366630/99', NULL, NULL, '', 'DELPHI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (189, NULL, 'GRUPPO MEIE ASSICURAZIONI', '', NULL, 'CORSO DI PORTA VIGENTINA, 9', '20122', 'MILANO', '0961-701253', NULL, '0961-701254', NULL, NULL, '', 'MEIE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (190, NULL, 'VILLANOVA MARIO & C. S.R.L.', '401900261', NULL, 'VIA BUSCHE, 51', '31020', 'SERNAGLIA DELLA BATTAGLIA', '0438-966218', NULL, '0438-966192', NULL, NULL, '', 'VILLAN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (191, NULL, 'FINSTILE S.P.A.', '', NULL, '', '', '', '', NULL, '', NULL, NULL, 'ISTITUTO FINANZIARIO', 'FINSTI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (192, NULL, 'I.N.P.S. CROTONE C.C.P. N. 174888', '', NULL, '', '', '', '', NULL, '', NULL, NULL, '', 'INPS3', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (193, NULL, 'I.N.P.S. DIREZIONE GENERALE ROMA', '', NULL, '', '', '', '', NULL, '', NULL, NULL, 'C.C.P. N.30 9O367004', 'INPS4', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (194, NULL, 'AUTOFUR S.R.L. CONCESSIONARIA RENAULT', '390240794', NULL, 'VIA PIAVE, 28-30', '88046', 'LAMEZIA TERME', '0961-754141/4', NULL, '', NULL, NULL, 'FILIALE DI CATANZARO', 'AUTOFU', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (195, NULL, 'MOBILIFICIO 2B S.R.L.', '633080411', NULL, 'VIA FONTESECCO, 66', '61027', 'VILLA FASTIGGI', '0721-282730', NULL, '0721-281657', NULL, NULL, '', '2B', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (196, NULL, 'POLETTI & C. S.P.A.', '72640931', NULL, 'VIA CIAL D''AVIAN, 10 Z.I.', '33070', 'BUDOIA', '0434-654593', NULL, '0434-654937', NULL, NULL, 'CAMERE DA LETTO', 'POLETT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (198, NULL, 'ARTARREDO S.A.S DI GOZZI MARISA & C.', '660260233', NULL, 'VIA AMERIGO VESPUCCI,4/5', '37053', 'CEREA - FRAZ.CHERUBINE', '0442-35103', NULL, '0442-35221', NULL, NULL, 'MOBILI IN STILE', 'ARTARR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (199, NULL, 'LINEALEGNO S.R.L.', '3020710269', NULL, 'VIA TINTORETTO, 34', '31056', 'RONCADE', '0422-841134', NULL, '0422-841230', NULL, NULL, 'MOBILI IN LEGNO MASSELLO', 'LINEAL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (200, NULL, 'LINEA UFFICIO DI GIAQUINTA DOMENICO', '1829590791', NULL, 'VIA MARINELLA, 16', '88074', 'CROTONE', '0962-23635', NULL, '0962-23635', NULL, NULL, 'VENDITA INGROSSO MOBILI E MACCHINE UFFI.', 'LINEAU', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (201, NULL, 'CLIENTE PRIVATO OCCASIONALE', '', NULL, '', '', '', '', NULL, '', NULL, NULL, '', 'OCCASI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (202, NULL, 'SOCCORSO STRADALE DI PERNA PASQUALE & C.', '1829430790', NULL, 'C.DA TRAFINELLO', '88074', 'CROTONE', '946026 - 946262', NULL, '', NULL, NULL, '', 'ASTRA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (204, NULL, 'VETRERIA LAMANNA DI OLIVERIO ANTONIO', '1913810790', NULL, 'VIA M. NICOLETTA, 283', '88074', 'CROTONE', '0962-23042', NULL, '0962-23042', NULL, NULL, 'TELEFONO ABITAZ.793444', 'OLIVER', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (205, NULL, 'R.C.S. RIZZOLI PERIODICI S.P.A. ABBONAM.', '', NULL, 'VIA ANGELO RIZZOLI, 2', '20132', 'MILANO', '', NULL, '', NULL, NULL, 'C.C.P. N.199208', 'RCS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (207, NULL, 'INSEGNE LUMINOSE - SCICCHITANO ANTONIO', '1948340797', NULL, 'LOCALITA'' POGGIO PUDANO, KM.245', '88074', 'CROTONE', '0962-946007', NULL, '', NULL, NULL, '', 'SCICCH', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (208, NULL, 'REBUS UFFICIO S.A.S DI SALICE F. E RIOLO', '1749560791', NULL, 'VIA ROMA, 42', '88074', 'CROTONE', '0962-26537', NULL, '0962-26734', NULL, NULL, 'REGISTRI BUFFETTI', 'REBUS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (209, NULL, 'F.LLI CORAZZINI S.P.A. INDUSTRIA MOBILI', '203020268', NULL, 'VIA A. MORO, 10', '31010', 'MOSNIGO DI MORIAGO DELLA BATT.', '0438-890089', NULL, '0438-890111', NULL, NULL, '', 'CORAZZ', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (210, NULL, 'MERCEDES BENZ FINANZIARIA S.P.A.', '1123081000', NULL, 'VIA GIULIO VINCENZO BONA, 130,132', '156', 'ROMA', '06-41595700', NULL, '06-41595279', NULL, NULL, '', 'MERFIN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (211, NULL, 'MOBILSYSTEM B S.R.L. GRUPPO BERLONI', '1367470414', NULL, 'VIA DELL''ECONOMIA S.N.C.', '61020', 'CHIUSA DI GINESTRETO', '0721-481523/19', NULL, '0721-481520', NULL, NULL, 'CAMERE, CAMERETTE, ZONA GIORNO', 'BMOBIL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (212, NULL, 'C.P. S.N.C. DI PERNECHELE BENITO & C.', '798160289', NULL, 'VIA VENETO, 110 (3^ ZONA ARTIGIANALE)', '35040', 'CASALE DI SCODOSIA', '0429-87746', NULL, '0429-87566', NULL, NULL, 'ARTE POVERA E CLASSICO', 'C.P.', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (213, NULL, 'ELMAR S.R.L.', '900760414', NULL, 'VIA G. PASTORE, 17-19', '61025', 'MONTELABBATE', '0721-490121', NULL, '0721-498829', NULL, NULL, 'CAMERETTE', 'ELMAR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (214, NULL, 'TAGLIAMENTO AUTO S.R.L.', '1656690797', NULL, 'VIA PROCESSIONE STOCCO', '88046', 'LAMEZIA TERME', '0968-462111', NULL, '0968-463193', NULL, NULL, 'CONCESSIONARIA E ASSISTENZA MERCEDES', 'TAGLIA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (215, NULL, 'WURTH S.R.L. PRODOTTI PROFESSIONALI', '125230219', NULL, 'VIA ENZENBERG, 2', '39018', 'TRLANO', '0471-900111', NULL, '0471-900444', NULL, NULL, 'MATERIALI DI CONSUMO', 'WURTH', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (216, NULL, 'MOBILIFICIO EROS BAZZONI DI BAZZONI C &', '30480529', NULL, 'VIA LAURETANA NORD, N.64', '53049', 'TORRITA DI SIENA', '0577-685181', NULL, '0577-687696', NULL, NULL, '', 'BAZZON', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (217, NULL, 'ANTINCENDIO KR DI ANGOTTI NICOLA', '2115570794', NULL, 'VIA INTERNA VERDOGNE,8', '88900', 'CROTONE', '', NULL, '', NULL, NULL, '', 'NIBA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (218, NULL, 'ILVA S.R.L.', '452650419', NULL, 'STRADA SELVA  GROSSA, 36', '61010', 'TAVULLIA', '0721-20031', NULL, '0721-200321', NULL, NULL, 'INDUSTRIA LAVORAZIONE VETRO', 'ILVA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (219, NULL, 'KEOMA SALOTTI S.R.L.', '3814070722', NULL, 'VIA MANZONI, 182', '70022', 'ALTAMURA', '0835-339128', NULL, '0835-339129', NULL, NULL, '', 'KEOMA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (220, NULL, 'ASSICURAZIONI LEVANTE', '', NULL, 'VIA XXV APRILE, 157', '88900', 'CROTONE', '0962-23909', NULL, '0962-23909', NULL, NULL, 'AGENZIA DI CROTONE 513', 'ASSLEV', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (221, NULL, 'COM.SER.CA. S.R.L.', '1699930796', NULL, 'VIA ETTORE VITALE, 4', '88100', 'CATANZARO', '0961-744991', NULL, '0961-744992', NULL, NULL, 'ESCLUSIVISTA ENNEREV', 'COMSER', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (223, NULL, 'BOTRICELGOMME DI CHIARAVALLOTTI M.', '1479130799', NULL, 'VIA NAZIONALE, 66', '', 'BOTRICELLO', '', NULL, '', NULL, NULL, '', 'BOTRIC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (224, NULL, 'FALEGNAMERIA ALDO MESITI', '1185050802', NULL, 'VIA MATTEOTTI, 49/A', '89046', 'MARINA DI GIOIOSA JONICA', '1964-416098', NULL, '0964-415086', NULL, NULL, 'MOBILI INFISSI ARREDAMENTI', 'MESITI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (225, NULL, 'NADEM S.R.L.', '673790879', NULL, 'VIA DELL''ALBERO,39', '', 'CATANIA', '095-451427', NULL, '095-451018', NULL, NULL, 'COSTRUZIONI CUCINE COMPONIBILI E ARREDI', 'NADEM', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (227, NULL, 'ENTE NAZIONALE PER LE STRADE', '2133681003', NULL, 'VIA E. DE RISO, 2', '88100', 'CATANZARO', '0961-725109', NULL, '0961-725106', NULL, NULL, 'EX A.N.A.S.', 'ENASTR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (228, NULL, 'ENTE NAZIONALE PER LE STRADE', '2133681003', NULL, 'VIA DE RISO, 2', '88100', 'CATANZARO', '0961-725109', NULL, '0961-725106', NULL, NULL, '', 'ANAS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (232, NULL, 'BIEFFE S.N.C. DI RIOLO GIANCARLO & C.', '1680460795', NULL, 'VIA ROMA, 67', '88900', 'CROTONE', '0962-21679', NULL, '0962-21679', NULL, NULL, '', 'BIEFFE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (233, NULL, 'SERAFINO MOBILI S.R.L.', '1534780745', NULL, 'S.S.7 PER GROTTAGLIE KM.2.600', '72021', 'FRANCAVILLA FONTANA', '0831-310736', NULL, '0831-310736', NULL, NULL, 'RACCOGLITORE', 'SERAFI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (235, NULL, 'LA FACE MATERIALE PER L''EDILIZIA', '1816620791', NULL, 'VIA PARCO INSITI,4', '88841', 'ISOLA DI CAPO RIZZUTO', '0962-793025', NULL, '', NULL, NULL, '', 'LAFACE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (236, NULL, 'ELETTROKROTON S.R.L.', '1981070798', NULL, 'VIA TUFOLO PAL.PIZZUTI B1', '88900', 'CROTONE', '', NULL, '', NULL, NULL, '', 'ELKROT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (237, NULL, 'VECCHIO ORIENTE - VENDITA TAPPETI PERSIA', '2142760798', NULL, 'VIA CAFALDO', '88841', 'ISOLA DI CAPO RIZZUTO', '', NULL, '', NULL, NULL, 'NI E TENDAGGI DI MUNGO FRANCESCHINA', 'ORIENT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (238, NULL, 'COMEL S.A.S. DI PANGALLO L. & C.', '1998540791', NULL, 'VIA RISORGIMENTO, 68/70', '88021', 'ROCCELLETTA DI BORGIA', '0961-391518', NULL, '0961-391518', NULL, NULL, '', 'COMEL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (239, NULL, 'CI DUE CAMERE S.R.L.', '1221410416', NULL, 'VIA MARCHE,40/A', '61020', 'MONTECCHIO - S.ANGELO IN LIZZO', '0721-498179', NULL, '0721-490742', NULL, NULL, '', 'CIDUE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (240, NULL, 'ANTONICELLI D. S.N.C.', '1554520799', NULL, 'VIA MARCONI, 309', '88048', 'LAMEZIA TERME', '0968-438510', NULL, '0968-438510', NULL, NULL, 'ELETTRODOMESTICI DA INCASSO ARISTON', 'ANTONI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (241, NULL, 'KAPPA SYSTEM S.R.L.', '1706990791', NULL, 'VIA CAPPUCCINI,31/33', '88900', 'CROTONE', '0962-62235', NULL, '0962-28730', NULL, NULL, 'APPARECCHIATURE ELETTRONICHE  PER UFFICI', 'KAPPAS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (242, NULL, 'ELECTROLUX ZANUSSI ITALIA S.P.A.', '1094820931', NULL, 'VIA GIARDINI CATTANEO, 3', '33170', 'PORDENONE', '0434-3941/51/61', NULL, '0434-395897', NULL, NULL, '', 'REX', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (243, NULL, 'GAMMA INDUSTRY S.R.L.', '1030640419', NULL, 'VIA L. FALASCONI, 92 INT.B', '61033', 'FERMIGNANO', '0722-331846', NULL, '0722-332028', NULL, NULL, '', 'GAMMA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (244, NULL, 'JENSIGI IMPORT - EXPORT S.R.L.', '', NULL, 'VIA CASARI, 5', '37050', 'BONAVICINA', '', NULL, '', NULL, NULL, '', 'JENSIG', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (245, NULL, 'KAPPA SYSTEM S.N.C.', '494450794', NULL, 'VIA XXV APRILE, 164', '88900', 'CROTONE', '', NULL, '', NULL, NULL, 'CARTOLERIA CANCELLERIA REGISTRI', 'KSYSTE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (246, NULL, 'COLFER SAS FERRAMENTA', '2029600794', NULL, 'VIA PIGNATARO, 44', '88900', 'CROTONE', '0962-25119', NULL, '0962-28085', NULL, NULL, '', 'COLFER', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (248, NULL, 'R.T.L. DI RAPA S.R.L.', '484690417', NULL, 'VIA DELLA PRODUZIONE, 76-78', '61025', 'MONTELABBATE', '0721-481144/45', NULL, '0721-481011', NULL, NULL, 'CAMERE E SALE', 'RTL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (249, NULL, 'RADIO TELE INTERNATIONAL S.R.L.', '756460796', NULL, 'VIA LIBERTA'', 48', '88900', 'CROTONE', '0962-20000', NULL, '0962-902111', NULL, NULL, '', 'RTI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (250, NULL, 'FARMO S.R.L.', '197310410', NULL, 'VIA DEL PIANO, 40', '61022', 'TALACCHIO DI COLBORDOLO', '0721-478397/489', NULL, '0721-479170', NULL, NULL, 'FABBRICA ARREDAMENTI MODERNI', 'FARMO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (251, NULL, 'MACCURO GIUSEPPE', '3627570728', NULL, 'VIA ROMA, 126', '70043', 'MONOPOLI', '0330-786399', NULL, '', NULL, NULL, 'QUADRI E QUADRI', 'MACCUR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (252, NULL, 'AUTORICAMBI IVECO', '139810790', NULL, 'VIA XXV APRILE, 197', '88900', 'CROTONE', '0962-25993', NULL, '0962-25993', NULL, NULL, 'VRENNA FRANCO', 'VRENNA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (253, NULL, 'F.LLI MUNICCHI S.P.A.', '87740510', NULL, 'VIA GIAMBATTISTA VICO, 16/18', '52100', 'AREZZO', '0575-984471', NULL, '0575-380853', NULL, NULL, 'CUCINE COMPONIBILI', 'MUNICC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (255, NULL, 'PANDOLFI FABIO & C. S.N.C.', '1252590417', NULL, 'STRADA SELVA GROSSA, 10/12', '61010', 'TAVULLIA', '0721-201070', NULL, '0721-202525', NULL, NULL, '', 'PANDOL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (257, NULL, 'GIUSEPPE PUGLIESE AG. DI RAPP.ZE', '1711250793', NULL, 'VIA  GIMIGLIANO, 162', '88100', 'CATANZARO', '0961-771852', NULL, '0961-770175', NULL, NULL, '', 'PUGLIE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (259, NULL, 'INFOR B.R. DI BRUNO LUIGI', '1820200796', NULL, 'VIA CROTONE, 59', '88841', 'ISOLA DI CAPO RIZZUTO', '0962-791418', NULL, '0962-791418', NULL, NULL, 'SOLUZIONE PER L''INFORMATICA', 'INFORB', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (260, NULL, 'ZILIO MOBILI S.R.L.', '1952790242', NULL, 'VIA LAGHI,70', '36056', 'TEZZE SUL BRENTA', '0424-560140', NULL, '0424-560534', NULL, NULL, '', 'ZILIO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (261, NULL, 'LINEA SEDIA - DI A. DI LAZZARO & C. SNC', '828080671', NULL, 'C.DA RIPOLI -VIA i SILONE,6', '64023', 'MOSCIANO S. ANGELO', '085-8071124', NULL, '085-8071704', NULL, NULL, 'SEDIE POLTRONE IMBOTTITI', 'LINEAS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (262, NULL, 'TIPOGRAFIA SANSONE', '31040793', NULL, 'VIA RUFFO,22', '88900', 'CROTONE', '0962-23529', NULL, '', NULL, NULL, '', 'SANSON', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (263, NULL, 'I.C.N. INTERNET CHANNEL NETWORK SRL', '19448140791', NULL, 'VIA GRAMSCI, 19', '20081', 'ABBIATEGRASSO', '02-9462505', NULL, '', NULL, NULL, 'PUBBLICITA'' PAGINE BLU''', 'ICN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (265, NULL, 'MOBIL RECORD SRL', '315930263', NULL, 'VIA ROMA, 315', '34043', 'FONTANELLE', '0422-8183', NULL, '0422-809199', NULL, NULL, '', 'RECORD', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (266, NULL, 'MARCHETTI', '92520428', NULL, 'VIA LAURETANA, 128', '60021', 'CAMERANO', '071-731020', NULL, '071-731442', NULL, NULL, 'METAL MOBILI S.R.L.', 'MARCHE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (267, NULL, 'STRONG MOBILI S.A.S.', '1534110794', NULL, 'VIA FRISCHIA,61', '88050', 'SELLIA MARINA', '0961-964367', NULL, '0961-964367', NULL, NULL, 'DI CONCETTA FERRAGINA & C.', 'STRONG', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (268, NULL, 'BAMAX  S.R.L.', '600710263', NULL, 'VIA CASTELLANA, 122', '31010', 'ONE'' DI FONTE', '0423-949362', NULL, '0423-949941', NULL, NULL, '', 'BAMAX', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (269, NULL, 'STILARR S.R.L.', '1233330933', NULL, 'LOC. CASUT - VIA E. CHIARADIA,11/A', '33074', 'FONTANAFREDDA', '0434-565440', NULL, '0434-565400', NULL, NULL, 'CUCINE COMPONIBILI', 'STILAR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (270, NULL, 'CONFARTIGIANSERVIZI CROTONE UNO', '2222500791', NULL, 'VIA TORINO,119/B', '88900', 'CROTONE', '', NULL, '', NULL, NULL, 'SOC. COOP. A  R.L.', 'CONFAR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (271, NULL, 'CALLESELLA S.R.L.', '2264950268', NULL, 'LOCALITA'' TALPONE''', '31030', 'CISON DI VALMARINO', '0438-975445', NULL, '0438-85833', NULL, NULL, '', 'CALLES', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (273, NULL, 'ZEN EXPORT S.R.L.', '2289440246', NULL, 'VIA TRE CASE, 69/A', '36056', 'TEZZE SUL BRENTA', '0424-539561', NULL, '0424-89677', NULL, NULL, '', 'ZEN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (274, NULL, 'ISAM S.P.A.', '173520412', NULL, 'VIA DELLE INDUSTRIE,6/10', '61040', 'S.IPPOLITO', '0721-728322', NULL, '0721-749079', NULL, NULL, '', 'ISAM', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (275, NULL, 'CA.LI.GRAFICA PALLADIO S.R.L.', '2330560240', NULL, 'VIA UDINE, 7', '36040', 'TORRI DI QUARTESOLO', '0444-380006', NULL, '0444-380778', NULL, NULL, '', 'CALIGR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (276, NULL, 'CRM DEI F.LLI DORIA S.N.C.', '1618050791', NULL, 'VIA PER CROTONE - S.S.106', '88063', 'CATANZARO LIDO', '0961-719316/10', NULL, '0961-719042', NULL, NULL, '', 'DORIA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (277, NULL, 'CICCARELLI FRANCESCO', '124860792', NULL, 'S.S.106 KM.250 LOC.PASSOVECCHIO C.P.242', '88900', 'CROTONE', '0962-930288', NULL, '0962-930098', NULL, NULL, 'PNEUMATICI - RICOSTRUZIONE', 'CICCAR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (278, NULL, 'ALBERTA PACIFIC FURNITURE SRL', '614090264', NULL, 'VIA TONIOLO,41', '31028', 'VAZZOLA', '0438-440440', NULL, '0438-441555', NULL, NULL, 'SALOTTI', 'ALBERT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (279, NULL, 'NEW RELAX SRL', '1418010417', NULL, 'VIA BRIGATA GARIBALDI S.N.C.', '61027', 'PESARO', '0721-289070', NULL, '0721-289776', NULL, NULL, '', 'NRELAX', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (280, NULL, 'MONDOFFICE S.R.L.', '1636440024', NULL, 'VIA GARIBALDI,57', '13836', 'COSSATO', '015-98914', NULL, '015-9892200', NULL, NULL, '', 'MONDOF', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (281, NULL, 'SAI SOCIETA'' ASSICURATRICE INDUSTRIALE', '', NULL, 'CATANZARO', '', '', '', NULL, '', NULL, NULL, '', 'SAI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (282, NULL, 'BERNER SPA', '2093400238', NULL, 'VIA DELL''ELETTRONICA,15', '37139', 'VERONA', '458670111', NULL, '458670134', NULL, NULL, '', 'BERNER', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (284, NULL, 'NET WORKS SERVICE SAS', '2675250407', NULL, 'VIA GIANGI,41', '47900', 'RIMINI', '0541-387016', NULL, '541389236', NULL, NULL, 'DI SCHIAPPA LUCIA & C.', 'NETWOR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (288, NULL, 'CUCINE GATTO S.P.A.', '91960427', NULL, 'VIA DIRETTSSIMA DEL CONERO,51', '60021', 'CAMERANO', '71730121', NULL, '717301298', NULL, NULL, '', 'GATTO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (289, NULL, 'CENTRO DIVANI MERIDIONALI', '2266820782', NULL, 'VIA MONTE BIANCO, 27/29', '87032', 'AMANTEA', '0982-425252', NULL, '', NULL, NULL, 'DI PERNA ANTONIO', 'CDM', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (291, NULL, 'SPANO'' SALVATORE GOMMISTA', '1772460794', NULL, 'VIA M. NICOLETTA, 185', '88900', 'CROTONE', '0962-27589', NULL, '', NULL, NULL, '', 'SPANO''', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (292, NULL, 'THEOREMA INFORMATICA', '2064570795', NULL, 'VIA V. VENETO,112', '88900', 'CROTONE', '96223772', NULL, '96221720', NULL, NULL, '', 'THEORE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (293, NULL, 'SPADACCINI SALVATORE', '3383891219', NULL, 'VIA SOLVILLACE,47', '', 'ARZANO', '', NULL, '', NULL, NULL, '', 'SPADAC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (294, NULL, 'MODULAR S.A.', 'SM05581', NULL, 'STRADA AULOCETO, 1', '47893', 'CAILUNGO', '0549-907277', NULL, '0549-907266', NULL, NULL, 'FABBRICA ARREDAMENTI', 'MODULA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (295, NULL, 'CRISTODARO GIUSEPPE', '', NULL, '', '', '', '', NULL, '', NULL, NULL, '', 'CRISTO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (296, NULL, 'MORRONE ALBERTO', '2091010799', NULL, 'VIA V.VENETO, 136', '88900', 'CROTONE', '0962-28662', NULL, '0962-28662', NULL, NULL, 'AFF.SENGREEN ITALIA', 'MORRON', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (297, NULL, 'SCALISE SALVATORE ARTICOLI SPORTIVI', '1882800798', NULL, 'VIA POGGIOREALE,9 - LOC.SAN GIORGIO', '88900', 'CROTONE', '0962-23180', NULL, '0962-23180', NULL, NULL, '', 'SCALIS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (298, NULL, 'GIMS ITALIA SNC', '1582840409', NULL, 'VIA BERNALE, 39 - ZONA IND.LE', '47100', 'FORLI''', '0543-796569', NULL, '0543-796496', NULL, NULL, 'INDUSTRIA IMBOTTITI PER L''ARREDAMENTO', 'GIMS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (299, NULL, 'GOLDEN MOBILI S.R.L.', '2146130543', NULL, 'ZONA INDUSTRIALE', '6015', 'PIERANTONIO-UMBERTIDE', '075-9414416', NULL, '075-9414417', NULL, NULL, '', 'GOLDEN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (300, NULL, 'BLIXER S.P.A. SERVIZI TELEFONICI', '12547660154', NULL, 'VIA A. FAVA,20', '20125', 'MILANO', '2674831', NULL, '267483233', NULL, NULL, '', 'BLIXER', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (301, NULL, 'RIOLO F.LLI SRL', '280180795', NULL, 'VIA VENEZIA 1/7', '88900', 'CROTONE', '0962-22126', NULL, '0962-27144', NULL, NULL, 'CONCESSIONARIA OLIVETTI', 'RIOLO1', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (303, NULL, 'S.75 S.P.A.', '733420962', NULL, 'VIA SPREAFICO,3', '', 'MONZA', '0362-52911', NULL, '0362-551022', NULL, NULL, '', 'S75', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (304, NULL, 'MONTALTO MOTORI S.R.L.', '2185270788', NULL, 'S.S.106-BIS C.DA LOSINA', '87060', 'CORIGLIANO CALABRO', '0983856755/6', NULL, '983856727', NULL, NULL, 'MERCEDES-BENZ', 'MONTAL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (305, NULL, 'CENTER CROSS S.R.L.', '2341210785', NULL, 'S.S.106 - LOC. PASSOVECCHIO', '88900', 'CROTONE', '962938268', NULL, '', NULL, NULL, '', 'CENCRO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (308, NULL, 'A.L.F. UNO S.P.A.', '1919330264', NULL, 'VIA S.PIO X, 17', '31010', 'FRANCENIGO', '434769111', NULL, '434769245', NULL, NULL, '', 'ALF', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (309, NULL, 'AUTORICAMBI PETROCCA ANNA RITA', '2100430798', NULL, 'VIA CROTONE, 142', '88841', 'ISOLA DI CAPO RIZZUTO', '3396351609', NULL, '', NULL, NULL, '', 'AUPETR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (310, NULL, 'CENTRO RELAX', '5173200725', NULL, 'VIA M. CAMPAGNA,167', '70021', 'ACQUAVIVA DELLE FONTI', '', NULL, '', NULL, NULL, 'DI LA FORTEZZA RAFFAELE', 'CENREL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (313, NULL, 'SERRAO RAPPRESENTANZE S.N.C.', '2222270791', NULL, 'VIA ETTORE VITALE,4', '88100', 'CATANZARO', '961744991', NULL, '961744992', NULL, NULL, '', 'SERRAO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (315, NULL, 'STUDIO D''ARTE LUIGI RAFFAELE', '1932290792', NULL, 'VIA RAFFAELLI, 50', '88000', 'CATANZARO', '96162865', NULL, '', NULL, NULL, '', 'LUIRAF', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (316, NULL, 'OFFOCINE MECCANICHE DE RICCARDIS S.N.C.', '2426690752', NULL, 'VIA LECCE, 142', '73048', 'NARDO''', '833871235', NULL, '833871235', NULL, NULL, 'INDUSTRIA RETI E MATERASSI', 'DERICC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (317, NULL, 'TELE2 ITALIA Spa', '12627990158', NULL, 'VIA CASSANESE, 210', '20090', 'SEGRATE', '', NULL, '', NULL, NULL, '', 'TELE2', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (318, NULL, 'ALPE CUCINE', '1153080369', NULL, 'VIA A. BOITO, 18', '41019', 'SOLIERA', '059-850180/210', NULL, '059/850155', NULL, NULL, '', 'ALPE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (319, NULL, 'VIKING OFFICE PRODUCTS', '12043580153', NULL, 'CENTRO DIREZ. MILANOFIORI PAL.E SC.3/4', '20090', 'ASSAGO', '800552288', NULL, '800661100', NULL, NULL, '', 'VIKING', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (320, NULL, 'SOFT STYLE QUARTA', '2738190756', NULL, 'VIA PROV.LE X NOVOLI KM.4', '73041', 'CARMIANO', '0832-601237', NULL, '0832-602649', NULL, NULL, 'FABBRICA MOBILI E SALOTTI', 'SOFTST', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (322, NULL, 'CARTOTECNICA DI PEDRETTI RAFFAELE', '1715130793', NULL, 'VIA V. VENETO 102', '88900', 'CROTONE', '96226810', NULL, '96226810', NULL, NULL, 'FORNITURE SCUOLA UFFICIO', 'CARTOT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (323, NULL, 'TELERADIOPRODOTTI DI ALFI'' G.', '487640799', NULL, 'VIA BOLOGNA (LARGO OSPEDALE)', '88900', 'CROTONE', '96221222', NULL, '96221225', NULL, NULL, '', 'TRP', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (325, NULL, 'FLEXILAN 2000 SRL', '7982430014', NULL, '', '', '', '011-9241110', NULL, '011-9241036', NULL, NULL, '', 'FLEXIL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (327, NULL, 'TRIPPINI S.R.L.', '2024670792', NULL, 'S.S.106 - KM.246,400', '88900', 'CROTONE', '962901200', NULL, '0962/900578', NULL, NULL, 'INGROSSO MATERIALE ELETTRICO', 'TRIPPI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (328, NULL, 'SAMAC SALOTTI SRL', '731470670', NULL, 'VIA E. MATTEI, 5', '64015', 'NERETO', '961855352', NULL, '861855714', NULL, NULL, '', 'SAMAC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (329, NULL, 'OFFICINA AUTORIZZATA REVISIONE AUTOVEICO', '321140790', NULL, 'C.DA POGGIO PUDANO', '88900', 'CROTONE', '0962-948067', NULL, '', NULL, NULL, '', 'VRENNG', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (331, NULL, 'STUDIO ENGINEERING FELICETTI S.A.S.', '2363200797', NULL, 'VIA EUROPA, 45', '88841', 'ISOLA DI CAPO RIZZUTO', '', NULL, '', NULL, NULL, '', 'FELICE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (332, NULL, 'NEW I.M.S. SPA', '1917020289', NULL, 'VIALE DELLA NAVIGAZIONE INTERNA,18', '35129', 'PADOVA', '049-8070940', NULL, '049-8072370', NULL, NULL, '', 'NEWIMS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (335, NULL, 'MATERASSIA SRL', '3363340658', NULL, 'SS.18 - KM.69, N.15', '84090', 'MONTECORVINO PUGLIANO', '0828-51146', NULL, '0828-51907', NULL, NULL, '', 'MATERA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (336, NULL, 'SIMONE MEGNA', '2466940794', NULL, 'C.DA CAPO RIZZUTO', '88841', 'ISOLA DI CAPO RIZZUTO', '', NULL, '', NULL, NULL, 'IMPIANTI ELETTRICI', 'SIMEGN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (338, NULL, 'MEDIA WORLD', '2630120166', NULL, 'VIA E. FERMI, 4', '24035', 'CURNO', '800992200', NULL, '035-202714', NULL, NULL, 'COMPRA ON LINE', 'MEDIAW', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (339, NULL, 'GIAGUARMOBILI SRL', '238920417', NULL, 'VIA ARENA, 2', '', 'S. ANGELO IN LIZZOLA', '0721-490315', NULL, '0721-490305', NULL, NULL, '', 'GIAGUA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (340, NULL, 'ELITEL SPA EX BLIXER.', '12505840152', NULL, 'VIA MECENATE,90', '20138', 'MILANO', '', NULL, '', NULL, NULL, '', 'ELITEL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (342, NULL, 'CHL SPA', '4610960488', NULL, 'VIA DI NOVOLI, 7', '50127', 'FIRENZE', '', NULL, '', NULL, NULL, '', 'CHL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (343, NULL, 'OTTICA PUNTO VISTA DI PERITI G.CARLO', '1896030796', NULL, 'VIA CUTRO, 113', '88900', 'CROTONE', '', NULL, '', NULL, NULL, '', 'OTTICA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (344, NULL, 'I.M.A. S.P.A.', '74220930', NULL, 'VIA MANZONI,27', '33080', 'PRATA DI PORDENONE', '0434-612511', NULL, '0434-621702', NULL, NULL, '', 'IMA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (347, NULL, 'BTKNET SRL', '6614801006', NULL, 'PIAZZA G. RANDACCIO,1', '195', 'ROMA', '06-39732339', NULL, '06-39734413', NULL, NULL, '', 'BTKNET', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (354, NULL, 'EDITIRIALE CROTONESE S.R.L.', '1410260796', NULL, 'Z.IND.LE - LOC. PASSOVECCHIO CAS.POST.51', '88900', 'CROTONE', '0962-938774', NULL, '0962-931091', NULL, NULL, '', 'CROTON', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (355, NULL, '4 F MOBILI S.R.L.', '3283170276', NULL, 'VIA S. PERTINI,6/B', '30020', 'ANNONE VENETO', '0422-868090', NULL, '0422-868176', NULL, NULL, '', '4FMOBI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (356, NULL, 'ASSICURAZIONE FONDIARIA-SAI', '', NULL, '', '', '', '', NULL, '', NULL, NULL, '', 'FONSAI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (357, NULL, 'STUDIO INFORMATICA S.R.L.', '1133740892', NULL, 'VIA TERACATI, 92', '96100', 'SIRACUSA', '0931-441400', NULL, '0931-442232', NULL, NULL, '', 'STINFO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (358, NULL, 'MODENESE GASTONE SAS', '28260289', NULL, 'VIA CAODALBERO, 159', '35040', 'CASALE DI SCODOSIA', '0429-879146', NULL, '0429-878354', NULL, NULL, 'DI MODENESE RENZO, FRANCESCO & C.', 'MODENE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (359, NULL, 'PACELLI TRADE SAS DI PAOLO PACELLI & C.', '959880626', NULL, 'C.DA SELVA DI SOTTO', '82035', 'SA. SALVATORE T.', '0824-947283', NULL, '0824-974040', NULL, NULL, '', 'PACELL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (363, NULL, 'CIESSE CUCINE SRL', '880060652', NULL, 'VIA LO PORTO,15', '84010', 'S. PIETRO DI SCAFATI', '081-8504333', NULL, '081-8504143', NULL, NULL, '', 'CIESSE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (364, NULL, 'RINALDI FRANCO', '420010506', NULL, 'VIA S.GIOVANNI BATTISTA, 38/40', '56024', 'MOLIN D''EGOLA', '0571/498653', NULL, '0571/498653', NULL, NULL, 'PRODUZIONE DIVANI E POLTRONE', 'RINALD', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (365, NULL, 'MICROELETTRONICA DI SCIDA GIOVANNI', '1792010793', NULL, 'CORSO MAZZINI, 9A', '88900', 'CROTNE', '0962/23532', NULL, '', NULL, NULL, 'RIPARAZIONI RADIO , TV , VCR', 'SCIDAG', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (368, NULL, 'FYRE PNEUS 2000', '2248490795', NULL, 'PIAZZA BERLINGUER, 2', '88841', 'ISOLA DI CAPO RIZZUTO', '0962-797343', NULL, '0962-797249', NULL, NULL, 'DI PARISI', 'FYREPN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (369, NULL, 'PITTELLA PASQUALE', '1805280797', NULL, 'VIALE GRAMSCI,8', '88841', 'ISOLA DI CAPO RIZZUTO', '0962-792135', NULL, '0962-792135', NULL, NULL, 'CARROZZERIA', 'PITTE1', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (370, NULL, 'PRETELLI SYSTEM SNC', '1262460411', NULL, 'VIA MARSIGLIA, 2', '61100', 'PESARO', '0721-451928', NULL, '', NULL, NULL, 'DELL''ING.PRETELLI FABRIZIO MAURO & C.', 'PRETEL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (372, NULL, 'BIPIELLE DUCATO SPA', '', NULL, 'VIA DI PIAGGIA,  2/A', '55100', 'LUCCA', '', NULL, '', NULL, NULL, '', 'DUCATO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (373, NULL, 'PRESTITEMPO FINANZIARIA', '', NULL, '', '', '', '', NULL, '', NULL, NULL, 'AGENZIA PERONE', 'PRESTI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (377, NULL, 'CAPOCASALE GIULIO', '351640792', NULL, 'VIA VITT.VENETO, 50', '88900', 'CROTONE', '0962-25728', NULL, '', NULL, NULL, 'CAV. UFF. NOTAIO', 'NOTAIO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (378, NULL, 'MESSINA ELIO', '110580792', NULL, 'VIA M. NICOLETTA,137,139', '88900', 'CROTONE', '0962-23008', NULL, '', NULL, NULL, 'RICAMBI ELETTRICI PER AUTO - ELETTRAUTO', 'MESSIN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (381, NULL, 'EFFEZETA S.R.L.', '582530309', NULL, 'VIA MANZANO, 70/1', '33040', 'PREMARIACCO', '0432-706411', NULL, '0432-706530', NULL, NULL, '', 'EFFEZE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (383, NULL, 'A.A DA FRE'' ROVILIO & C. S.R.L.', '2103800260', NULL, 'STRADA MAESTRA D''ITALIA', '31016', 'CORDIGNANO', '0438/997111', NULL, '0438/997245', NULL, NULL, '', 'DAFRE''', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (384, NULL, 'LETTI COSATTO DI STEFANO COSATTO S.R.L.', '1922370307', NULL, 'VIA PASCOLI,22', '33010', 'TAVAGNACCO', '0432-571498/079', NULL, '0432-573569', NULL, NULL, '', 'COSATT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (385, NULL, 'SALA AZZURRA DI COSIMO PAVONE', '76160738', NULL, 'S.S.100 KM.52 + 500', '74100', 'SAN BASILIO-MOTTOLA', '099-8867799', NULL, '099-8833124', NULL, NULL, '', 'SALAAZ', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (386, NULL, 'BALILLI SRL', '5092440725', NULL, 'VIA GIOIA - ZONA PIP LOTTO D/17', '70029', 'SANTERAMO IN COLLE', '', NULL, '', NULL, NULL, '', 'BALILL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (387, NULL, 'SICURTRANSPORT', '', NULL, '', '', '', '', NULL, '', NULL, NULL, '', 'SICURT', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (392, NULL, 'CAVALLARO FRANCESCO', '1812510798', NULL, 'VIA MICELI,4', '88070', 'CERENZIA', '0984-995185', NULL, '', NULL, NULL, 'RAPPRESENTANTE MOBILI', 'CAVALL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (393, NULL, 'AZIENDA SANITARIA LOCALE N.5 CROTONE', '1997410798', NULL, 'PRESIDIO OSPEDALIERO', '88900', 'CROTONE', '', NULL, '', NULL, NULL, '', 'ASL5', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (394, NULL, 'VALENTINI INDUSTRIE S.P.A.', '891770406', NULL, 'VIA RIGOLETTO,27', '47900', 'RIMINI', '0541-368888', NULL, '0541-774233', NULL, NULL, '', 'VALEN1', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (399, NULL, 'GALLERIA DEL MOBILE', '2265810792', NULL, 'VIALE DELLA REPUBBLICA, 207', '87055', 'S. GIOVANNI IN FIORE', '', NULL, '', NULL, NULL, 'DI IERARDI ROSA', 'GALMOB', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (400, NULL, 'DOIMO MATERASSI SRL', '1917120261', NULL, 'VIA SCHIAVONESCA NUOVA, 23', '31040', 'VOLPAGO DEL MONTELLO', '0423-621367', NULL, '0423-879367', NULL, NULL, '', 'DOIMO1', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (401, NULL, 'CIACCI PIERO & C. SNC', '980480420', NULL, 'VIA ANTONELLI, 49', '60010', 'BRUGNETTO DI RIPE', '071-6620374', NULL, '071-6620258', NULL, NULL, '', 'CIACCI', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (403, NULL, 'GESIM S.P.A.', '2723240244', NULL, 'VIA VASCO DE GAMA,48', '36061', 'BASSANO DEL GRAPPA', '0424-886540', NULL, '0424-567765', NULL, NULL, '', 'GESIM', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (404, NULL, 'MEDIATAG S.R.L.', '1904270798', NULL, 'LOC. SERRAMONDA VECCHIA SS-KM 12+300', '88040', 'MARCELLINARA', '0961-902211', NULL, '0961-902232', NULL, NULL, '', 'MEDIAG', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (406, NULL, 'NETWORK CENTRI COMMERCIALI spa', '3167690969', NULL, 'VIA ITALIA, 197', '20040', 'BUSNAGO', '039 682551', NULL, '039 6825537', NULL, NULL, '', 'NCCOM', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (407, NULL, 'GIERRE MOBILI SRL', '1155560418', NULL, 'VIA RISARA S.N.C.', '61025', 'MONTELABBATE', '0721481230/1071', NULL, '721482458', NULL, NULL, '', 'GIERRE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (409, NULL, 'CAMAR MOBILI SRL', '438160418', NULL, 'VIA MONTANELLI, 50', '61100', 'PESARO', '0721-281689/90', NULL, '0721-281519', NULL, NULL, '', 'CAMAR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (410, NULL, 'SINOVA S.R.L.', '1375830419', NULL, 'LOCALIT… PIAN D''ARMI, 2', '61026', 'PIANDIMELETO', '0722-721822', NULL, '0722-721850', NULL, NULL, 'INDUSTRIA MOBILI', 'SINOVA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (412, NULL, 'NEW TREND DESIGN SRL', '5050920726', NULL, 'VIA DELLE LENTICCHIE SNC - Z.IND.', '70022', 'ALTAMURA', '080-3103923', NULL, '080-3147436', NULL, NULL, '', 'NTREND', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (415, NULL, 'CI.NOVA MOBILI SRL', '', NULL, 'VIA L. EINAUDI, 17/B', '61031', 'BELLOCCHIO DI FANO', '0721-855132-3', NULL, '0721-855134', NULL, NULL, '', 'CINOVA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (416, NULL, 'EUROSEDIA S.P.A.', '419000427', NULL, 'zONA IND.LE CIAN - VIA DELL''INDUSTRIA,18', '60026', 'NUMANA', '071-7390557', NULL, '071-7390911', NULL, NULL, '', 'EUROSE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (417, NULL, 'DOLCE DORMIRE', '3996420653', NULL, 'VIA PITAGORA, 24', '84091', 'BATTIPAGLIA', '0828-300162', NULL, '0828-300162', NULL, NULL, 'MATERASSI RETI E COMPLEMENTI', 'DOLCED', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (418, NULL, 'STYLE HOUSE INTERNATIONAL SRL', '1493320202', NULL, 'VIA M. BENEDINI,18', '46010', 'MARCARIA', '0376-950521', NULL, '0376-950525', NULL, NULL, '', 'STYLEH', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (419, NULL, 'ARRISAL SRL', '1997830789', NULL, 'VIA A.LONGO, 7', '87040', 'SAN LORENZO DEL VALLO', '0981-954145', NULL, '0981-951928', NULL, NULL, 'INGROSSO ARREDI E DISTR.ELETTROD.INCASSO', 'ARRISA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (420, NULL, 'DUE B SRL INGROSSO MOBILI ED ARREDI', '1294830789', NULL, 'C.DA CAMMARATA - S. INDUSTR.LE', '87012', 'CASTROVILLARI', '0981-386988/9', NULL, '0981-386985', NULL, NULL, '', 'DUEB', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (421, NULL, 'ECO PIU'' SRL', '2614680789', NULL, 'VIA FALCONARA', '87020', 'TORTONA', '', NULL, '', NULL, NULL, '', 'ECOPIU', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (422, NULL, 'C&C CUCINE GLOBAL SRL', '2610410785', NULL, 'lOCALITA'' COTURE', '87029', 'SCALEA', '', NULL, '', NULL, NULL, '', 'C&CCUC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (423, NULL, 'GLOBAL srl', '2610410785', NULL, 'LOC. COTURA', '87029', 'SCALEA', '', NULL, '', NULL, NULL, '', 'GLOBAL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (425, NULL, 'SOFAS SRL', '2159920723', NULL, 'VIA SARAGAT, 1/3', '70029', 'SANTERAMO IN COLLE', '080-3032568', NULL, '080-3030280', NULL, NULL, '', 'SOFAS', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (426, NULL, 'ADRIA ARTIGIANATO SRL', '601020407', NULL, 'VIA DELL''INDUSTRIA,26 Z.ARTIGIANALE', '47838', 'RICCIONE', '541602118', NULL, '541606456', NULL, NULL, 'PINTDECOR', 'PINTDE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (427, NULL, 'FURLAN S.R.L.', '1791980269', NULL, 'Via A. De Gasperi, 35', '31014', 'COLLE UMBERTO', '0438-430063', NULL, '0438-430363', NULL, NULL, 'industria mobili', 'FURLAN', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (428, NULL, 'SAX ARREDAMENTI SRL', '3786240873', NULL, 'VIA DELLE ONDINE, 4', '95121', 'CATANIA', '095-7231435', NULL, '095-281833', NULL, NULL, '', 'SAX', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (429, NULL, 'SAN GIROLAMO SRL', '179440417', NULL, 'VIA CIRCONVALLAZIONE, 25/27', '61020', 'LUNANO', '0722-70108', NULL, '0722-70504', NULL, NULL, 'MOBILI E ACCESSORI X MOBILI', 'SANGIR', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (430, NULL, 'FLORIDA CUCINE', '295000939', NULL, 'VIA MARON,16', '33080', 'PRATA', '0434-601111', NULL, '0434-610149', NULL, NULL, 'INDUSTRIA MOBILOI PROFIM SPA', 'FLOCUC', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (431, NULL, 'CUCINE 2000', '4735330724', NULL, 'S.P.231 KM.79,675', '70026', 'MODUGNO', '080-5365042', NULL, '080-5323648', NULL, NULL, 'ELEMENTI PER ARREDARE', 'CU2000', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (432, NULL, 'ARREDO SUD SRL', '1307510782', NULL, 'C/DA SETTIMO', '87040', 'MONTALTO UFFUGO', '0984-934752', NULL, '', NULL, NULL, 'MOBILI ANTICHI E MODERNI', 'ARREDO', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (433, NULL, 'MIRCEA BY EUROSALOTTO PEDRINA SRL', '2192850283', NULL, 'VIA OLMO, 4', '35011', 'CAMPODARSEGO', '049-5565313', NULL, '049-9200930', NULL, NULL, '', 'MIRCEA', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (434, NULL, 'SEDAM SRL', '1931380743', NULL, 'VIALE DEL COMMERCIO SNC ZONA IND.LE', '72021', 'FRANCAVILLA FONTANA', '0831-810707', NULL, '0831-810707', NULL, NULL, 'FABBRICA SEDIE-INGROSSO MOBILI', 'SEDAM', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (436, NULL, 'MOBILIFICIO FOGLIENSE', '354260416', NULL, 'VIA ROSSINI, 13', '61020', 'BELFORTE ALL''ISAURO', '0722-721598', NULL, '0722-721885', NULL, NULL, '', 'FOGLIE', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (440, NULL, 'BOTTEGA DEL SALOTTO', '3123570875', NULL, 'VIA PROVINCIALE, 80', '95040', 'PIANO TAVOLA-BELPASSO', '', NULL, '', NULL, NULL, 'DI POMETTI GIOVANNI', 'BOTSAL', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (441, NULL, 'MAZZONI SALOTTI S.R.L.', '859000341', NULL, 'VIA LA SPEZIA,86', '43100', 'PARMA', '0521-336313', NULL, '0521-336136', NULL, NULL, '', 'MAZZON', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (442, NULL, 'MOBILI TRE SNC', '137550935', NULL, 'VIA CALLE MACCAN,31', '33080', 'VILLANOVA DI PRATA', '0434-626233', NULL, '0434-626093', NULL, NULL, 'DI MUZ LUIGI & C.', 'MOBIL3', NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (408, NULL, 'CONDONO', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (256, NULL, 'BIREX S.R.L.', '1203930936', '', 'VIAE. CHIARADIA, 11/A', '33074', 'FONTANAFREDDA', '0434-565751', '', '0434-99151', '', '', 'SCARPIERE E VARIE', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (222, NULL, 'LE ORO CREAZIONE E DISTRIBUZIONI ESCLUSIVE', '1909380782', '', 'VIA G.D.CHIESA, 36', '87063', 'CARIATI M.', '0983-968108', '', '', '', '', 'ARTICOLI CERAMICA/MARMO E LEGNO/METALLO', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (272, NULL, 'LEVA S.R.L.', '2166760757', '', 'S.S.275 MAGLIE S.M. DI LEUCA KM.12,714', '73030', 'SURANO', '0936-936743', '', '0836-936907', '', '', 'I CLASSICI DELLA BRIANZA', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (57, NULL, 'RUSSO MARIO', '4310420726', '', 'VIA TEN.COL CAMICIA, 81/A', '70043', 'MONOPOLI', '', '', '', '', '', 'SALOTTI E DIVANI ECONOMICI', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (443, NULL, 'EMMEARREDI', '05567570725', '05567570725', 'CIA CROCIFISSO, 2A', '70126', 'BARI', '0805461934', '', '0805482289', 'UFFICIOVENDITE@EMMEARREDI.COM', 'EMMEARREDI.COM', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (444, NULL, 'GLOBAL ALLIANCE ITALIA SRL', '05853170727', '', 'VIA BELLINI, 12/A', '70123', 'BARI', '0805342686', '', '0805340196', 'INFO@GAITALIA.IT', 'WWW.GAITALIA.IT', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (445, NULL, 'MAGI SRL', '02416690879', '02416690879', 'C.DA PRINCIPE', '95040', 'PIANO TAVOLA', '0957131158/160', '', '0957131149', '', '', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (446, NULL, 'M.& G. SPA PERMAFLEX', '01741471203', '04936661000', 'VIA DEL LAVORO,11', '40057', 'QUARTO INFERIORE - GRANAROLO EMILIA', '0516935500', '', '0516931799', '', '', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (447, NULL, 'TRECI CECCHINI SRL', '00111040416', '00111040416', 'VIA NAZIONALE,258', '61022', 'COLBORDOLO', '0721495030', '', '0721495687', 'TRECI@TREI.IT', 'WWW.TRECI.IT', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (448, NULL, 'INTERMOBILI SRL', '00419060678', '00419060678', 'S.S.16 ADRIATICA KM.424', '64020', 'SCERNE DI PINETO', '085946551', '', '0859462168', 'INFO@INTERMOBILI.COM', 'WWW.INTERMOBILI.COM', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (314, NULL, 'AVV. BIANCHI MASSIMILIANO', '2051610794', '', 'VIA VECCHIA CARRARA,2', '88900', 'CROTONE', '96221898', '', '962902807', '', '', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (353, NULL, 'AVV. ARTUSI RICCARDO', '2206040046', '', 'PIAZZA EUROPA, 3', '12100', 'CUNEO', '0171-695936', '', '0171-696737', '', '', 'PRATICA DEBITORE BRUNO GIUSEPPE', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (307, NULL, 'AVV. BIANCHI VINCENZO', '911710796', '', 'VIA VECCHIA CARRARA, 2', '88900', 'CROTONE', '', '', '', '', '', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (449, NULL, 'PROVA -PROVA-', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (450, NULL, 'RIPROVA', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (451, NULL, 'SDASDASDAS', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (452, NULL, 'QQQQQQQQQQQQQQQQQQQ', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (4, NULL, 'AL.TA. DI ALBERTO TASSI & C.', '759220429', '', 'VIA E.FERMI, 60', '60021', 'CAMERANO', '071-732146', '', '071-732146', '', '', 'SCARPIERE PLASTICA ANTIPIOGGIA', NULL, 1);
INSERT INTO fornitori (idfornitore, data_inserimento, nome, piva, codfisc, via, cap, citta, tel, cell, fax, email, website, note, codbarre, provincia) VALUES (234, NULL, ' VOGUE DISTRIBUZIONE SELEZIONATA OGGETTI D''ARTE', '1286930852', '', 'VIA PIAVE, 8, 10, 10/A', '93100', 'CALTANISSETTA', '0934-551189', '', '', '', '', '', NULL, 1);


--
-- Data for Name: movimento_banca; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: ordini; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (0, 0, '2007-05-28', '12:08:24.781', '', 0, '000
', '2007-09-03', NULL, -1, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (5, 4, '2008-01-11', '12:14:58.796', '', 2, '', '2008-01-10', 0, -1, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (6, 2, '2008-01-11', '14:09:18.875', '', 0, '123', '2008-01-11', 121243324, -1, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (7, 4, '2008-01-11', '14:15:42.625', '', 2, '', '2008-01-11', 0, -1, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (9, 4, '2008-01-11', '00:00:00', 'asas
', 2, '111', '2008-01-11', 11111, -1, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (8, 4, '2008-01-11', '00:00:00', '', 2, '', '2008-01-11', 11111111, -1, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (10, 2, '2008-01-11', '16:02:31.593', '', 3, '', '2008-01-11', 0, -1, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (11, 0, '2008-01-12', '02:58:32.17', '', 0, '', '2008-01-12', 2.499999999999996, -1, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (12, 0, '2008-01-12', '14:00:03.906', '', 0, '', '2008-01-12', 2583, -1, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (13, 0, '2008-01-12', '17:29:27.453', '', 0, '', '2008-01-12', 4.999999999999992, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (14, 0, '2008-01-12', '17:33:53.312', '', 0, '', '2008-01-13', 108.5, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (15, 0, '2008-01-12', '00:00:00', '', 0, '', '2008-01-15', 4.999999999999992, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (16, 4, '2008-01-12', '00:00:00', '', 2, '1', '2008-01-12', 10, 25, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (17, 0, '2008-01-13', '17:17:34.89', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (18, 0, '2008-01-13', '17:21:30.687', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (19, 0, '2008-01-13', '17:22:01.296', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (20, 0, '2008-01-13', '17:25:20.656', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (21, 0, '2008-01-13', '17:30:49.781', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (22, 0, '2008-01-13', '17:35:28.046', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (23, 0, '2008-01-13', '17:40:27.75', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (24, 0, '2008-01-13', '17:41:35.828', '', 0, '', '2008-01-13', 2.499999999999996, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (25, 0, '2008-01-13', '17:48:05.906', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (26, 0, '2008-01-13', '17:49:29.875', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (27, 0, '2008-01-13', '17:56:42.265', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (28, 0, '2008-01-13', '18:02:46.781', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (29, 0, '2008-01-13', '18:09:35.578', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (30, 0, '2008-01-13', '18:13:26.234', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (31, 0, '2008-01-13', '18:16:25.89', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (32, 0, '2008-01-13', '18:20:05.359', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (33, 0, '2008-01-13', '18:21:32.421', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (34, 0, '2008-01-13', '18:23:47.234', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (35, 0, '2008-01-13', '18:24:18.75', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (36, 0, '2008-01-13', '18:27:06.718', '', 0, '', '2008-01-13', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (37, 0, '2008-01-14', '02:06:37.625', '', 0, '', '2008-01-14', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (38, 0, '2008-01-14', '02:08:12.437', '', 0, '', '2008-01-14', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (39, 0, '2008-01-14', '02:10:37.64', '', 0, '', '2008-01-14', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (40, 0, '2008-01-14', '02:13:14.50', '', 0, '', '2008-01-14', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (42, 0, '2008-01-14', '02:19:09.125', '', 0, '', '2008-01-14', 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (43, 4, '2008-02-04', '18:18:54.812', '', 1, '1', '2008-02-04', 0, 0, 0, 0, 0, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (41, 0, '2008-01-14', '00:00:00', '', 0, '', '2008-01-14', 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, 0);
INSERT INTO ordini (idordine, idcliente, data_ordine, ora_ordine, note, tipo_documento, num_documento, data_documento, totale_documento, iva_documento, doc_emesso, doc_fiscale, ins_pn, idpagamento, idcausale, spese_incasso, spese_trasporto, data_trasp, ora_trasp, colli, peso, consegna, porto, diversa_dest, idaspetto, sconto) VALUES (1, 0, '2008-02-04', '00:00:00', '', 0, '1', '2008-02-04', 4809.5000000000036, 0, 0, 0, 0, 0, 0, 0, 0, NULL, NULL, 0, 0, NULL, NULL, NULL, 0, 0);


--
-- Data for Name: pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pagamento (idpagamento, nome) VALUES (0, 'CONTANTI');


--
-- Data for Name: provincia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO provincia (idprovincia, provincia, targa) VALUES (0, 'NESSUNA', NULL);
INSERT INTO provincia (idprovincia, provincia, targa) VALUES (1, 'MILANO', 'MI');


--
-- Data for Name: reparti; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (3, '2007-07-09', '03 POLTRONE E DIVANI', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (4, '2007-07-09', '04 MOBILI DA GIORNO/NOTTE', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (5, '2007-07-09', '05 CAMERE PER RAGAZZI', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (6, '2007-07-09', '06 MATERASSI E GUANCIALI', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (7, '2007-07-09', '07 MOBILI DA BAGNO', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (8, '2007-07-09', '08 MOBILI PER ESTERNI', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (9, '2007-07-09', '09 COMPLEMENTI D''ARREDO', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (10, '2007-07-09', '10 ARTICOLI PER L''ILLUMINAZIONE', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (11, '2007-07-09', '11 ARTICOLI DA REGALO LISTA NOZZE', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (12, '2007-07-09', '12 MOBILI DA UFFICIO/CONTRACT (COMUNITA''', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (13, '2007-07-09', '13 ARTICOLI IN LEGNO (ESCL.MOBILI E COMP', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (17, '2007-07-09', '14 ARTICOLI IN PLASTICA (ESCL.MOBILI E C', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (18, '2007-07-09', '15 ARTICOLI IN VIMINI (ESCL.MOBILI E C.', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (19, '2007-07-09', '16 ALTRO', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (1, '2008-02-28', 'TIPOLOGIA DI VENDITA', '');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (22, '2008-02-28', 'XCVXC', 'VXCVX');
INSERT INTO reparti (idreparto, data_creazione, nome, descrizione) VALUES (2, '2008-02-28', '02 MOBILI PER CUCINA (COMPRESI DI EL)', 'DSFDFSDF');


--
-- Data for Name: tipo_documento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipo_documento (iddocumento, tipo, descrizione) VALUES (1, 'FT', 'FATTURA');
INSERT INTO tipo_documento (iddocumento, tipo, descrizione) VALUES (2, 'DDT', 'DOCUMENTO DI TRASPORTO');
INSERT INTO tipo_documento (iddocumento, tipo, descrizione) VALUES (0, 'OPERAZIONI MANUALI', 'OPERAZIONI MANUALI');
INSERT INTO tipo_documento (iddocumento, tipo, descrizione) VALUES (4, 'AL BANCO', 'SCONTRINO FISCALE');
INSERT INTO tipo_documento (iddocumento, tipo, descrizione) VALUES (3, 'RIC', 'RICEVUTA FISCALE');


--
-- Data for Name: tmp_etichette; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tmp_etichette (id, codice, descrizione, prezzo, note) VALUES (1, '414255', 'CARRELLO PC SPRINT', 76.670000000000002, '');


--
-- Data for Name: tmp_etichette_fornitori; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: um; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO um (idum, nome, descrizione) VALUES (1, 'NR', 'NUMERO');
INSERT INTO um (idum, nome, descrizione) VALUES (2, 'PZ', 'PEZZI');
INSERT INTO um (idum, nome, descrizione) VALUES (3, 'CM', 'CENTIMETRI');
INSERT INTO um (idum, nome, descrizione) VALUES (4, 'CL', 'COLLI');
INSERT INTO um (idum, nome, descrizione) VALUES (5, 'KG', 'KILOGRAMMI');


--
-- Data for Name: utenti; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO utenti (idutente, nome, pwd, lettura, scrittura, esecuzione, note) VALUES (1, 'root', 'cm9vdA==', 1, 1, 1, 'Amministratore sistema Pegaso');
INSERT INTO utenti (idutente, nome, pwd, lettura, scrittura, esecuzione, note) VALUES (1, 'root', 'cm9vdA==', 1, 1, 1, 'Amministratore sistema Pegaso');
INSERT INTO utenti (idutente, nome, pwd, lettura, scrittura, esecuzione, note) VALUES (1, 'root', 'cm9vdA==', 1, 1, 1, 'Amministratore sistema Pegaso');


--
-- Name: articoli_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articoli
    ADD CONSTRAINT articoli_pkey PRIMARY KEY (idarticolo);


--
-- Name: aspetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aspetto
    ADD CONSTRAINT aspetto_pkey PRIMARY KEY (idaspetto);


--
-- Name: carichi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY carichi
    ADD CONSTRAINT carichi_pkey PRIMARY KEY (idcarico);


--
-- Name: causale_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY causale
    ADD CONSTRAINT causale_pkey PRIMARY KEY (idcausale);


--
-- Name: clienti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY clienti
    ADD CONSTRAINT clienti_pkey PRIMARY KEY (idcliente);


--
-- Name: dettaglio_carichi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dettaglio_carichi
    ADD CONSTRAINT dettaglio_carichi_pkey PRIMARY KEY (idarticolo, idcarico);


--
-- Name: dettaglio_ordini_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dettaglio_ordini
    ADD CONSTRAINT dettaglio_ordini_pkey PRIMARY KEY (idordine, idarticolo);


--
-- Name: fornitori_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fornitori
    ADD CONSTRAINT fornitori_pkey PRIMARY KEY (idfornitore);


--
-- Name: idbanca_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY banca
    ADD CONSTRAINT idbanca_pkey PRIMARY KEY (idbanca);


--
-- Name: idconto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY conto_bancario
    ADD CONSTRAINT idconto_pkey PRIMARY KEY (idconto);


--
-- Name: idmov; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY movimento_banca
    ADD CONSTRAINT idmov PRIMARY KEY (idmov);


--
-- Name: key_fornitori; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tmp_etichette_fornitori
    ADD CONSTRAINT key_fornitori PRIMARY KEY (idfornitore);


--
-- Name: ordini_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ordini
    ADD CONSTRAINT ordini_pkey PRIMARY KEY (idordine);


--
-- Name: pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pagamento
    ADD CONSTRAINT pagamento_pkey PRIMARY KEY (idpagamento);


--
-- Name: pk_documento; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_documento
    ADD CONSTRAINT pk_documento PRIMARY KEY (iddocumento);


--
-- Name: pk_idprovincia; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY provincia
    ADD CONSTRAINT pk_idprovincia PRIMARY KEY (idprovincia);


--
-- Name: reparti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reparti
    ADD CONSTRAINT reparti_pkey PRIMARY KEY (idreparto);


--
-- Name: um_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY um
    ADD CONSTRAINT um_pkey PRIMARY KEY (idum);


--
-- Name: idx_articoli1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_articoli1 ON articoli USING btree (idreparto);


--
-- Name: idx_articoli2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_articoli2 ON articoli USING btree (um);


--
-- Name: idx_articoli3; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_articoli3 ON articoli USING btree (idfornitore);


--
-- Name: idx_articoli4; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_articoli4 ON articoli USING btree (idarticolo);


--
-- Name: idx_aspetto1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_aspetto1 ON aspetto USING btree (idaspetto);


--
-- Name: idx_carichi1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_carichi1 ON carichi USING btree (idcarico);


--
-- Name: idx_carichi2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_carichi2 ON carichi USING btree (idfornitore);


--
-- Name: idx_causale1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_causale1 ON causale USING btree (idcausale);


--
-- Name: idx_clienti1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_clienti1 ON clienti USING btree (idcliente);


--
-- Name: idx_dettaglio_carichi1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dettaglio_carichi1 ON dettaglio_carichi USING btree (idcarico);


--
-- Name: idx_dettaglio_carichi2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dettaglio_carichi2 ON dettaglio_carichi USING btree (idarticolo);


--
-- Name: idx_dettaglio_ordini1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dettaglio_ordini1 ON dettaglio_ordini USING btree (idordine);


--
-- Name: idx_dettaglio_ordini2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dettaglio_ordini2 ON dettaglio_ordini USING btree (idarticolo);


--
-- Name: idx_fornitori1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_fornitori1 ON fornitori USING btree (idfornitore);


--
-- Name: idx_ordini1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_ordini1 ON ordini USING btree (idcliente);


--
-- Name: idx_ordini2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_ordini2 ON ordini USING btree (idordine);


--
-- Name: idx_pagamento1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_pagamento1 ON pagamento USING btree (idpagamento);


--
-- Name: idx_reparti1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_reparti1 ON reparti USING btree (idreparto);


--
-- Name: idx_um1; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_um1 ON um USING btree (idum);


--
-- Name: articoli_idfornitore_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articoli
    ADD CONSTRAINT articoli_idfornitore_fkey FOREIGN KEY (idfornitore) REFERENCES fornitori(idfornitore);


--
-- Name: articoli_idreparto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articoli
    ADD CONSTRAINT articoli_idreparto_fkey FOREIGN KEY (idreparto) REFERENCES reparti(idreparto);


--
-- Name: articoli_um_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articoli
    ADD CONSTRAINT articoli_um_fkey FOREIGN KEY (um) REFERENCES um(idum);


--
-- Name: carichi_idfornitore_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carichi
    ADD CONSTRAINT carichi_idfornitore_fkey FOREIGN KEY (idfornitore) REFERENCES fornitori(idfornitore);


--
-- Name: carico_documento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carichi
    ADD CONSTRAINT carico_documento FOREIGN KEY (iddocumento) REFERENCES tipo_documento(iddocumento);


--
-- Name: dettaglio_carichi_idarticolo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_carichi
    ADD CONSTRAINT dettaglio_carichi_idarticolo_fkey FOREIGN KEY (idarticolo) REFERENCES articoli(idarticolo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: dettaglio_carichi_idcarico_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_carichi
    ADD CONSTRAINT dettaglio_carichi_idcarico_fkey FOREIGN KEY (idcarico) REFERENCES carichi(idcarico) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: dettaglio_ordini_idarticolo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_ordini
    ADD CONSTRAINT dettaglio_ordini_idarticolo_fkey FOREIGN KEY (idarticolo) REFERENCES articoli(idarticolo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: dettaglio_ordini_idordine_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dettaglio_ordini
    ADD CONSTRAINT dettaglio_ordini_idordine_fkey FOREIGN KEY (idordine) REFERENCES ordini(idordine) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_idaspetto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordini
    ADD CONSTRAINT fk_idaspetto FOREIGN KEY (idaspetto) REFERENCES aspetto(idaspetto) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_idcausale; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordini
    ADD CONSTRAINT fk_idcausale FOREIGN KEY (idcausale) REFERENCES causale(idcausale) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_idpagamento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordini
    ADD CONSTRAINT fk_idpagamento FOREIGN KEY (idpagamento) REFERENCES pagamento(idpagamento) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_provincia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clienti
    ADD CONSTRAINT fk_provincia FOREIGN KEY (provincia) REFERENCES provincia(idprovincia) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fornitori_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fornitori
    ADD CONSTRAINT fornitori_fk FOREIGN KEY (provincia) REFERENCES provincia(idprovincia) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: idbanca; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conto_bancario
    ADD CONSTRAINT idbanca FOREIGN KEY (idbanca) REFERENCES banca(idbanca) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: idconto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY movimento_banca
    ADD CONSTRAINT idconto FOREIGN KEY (idconto) REFERENCES conto_bancario(idconto) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ordine_documento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordini
    ADD CONSTRAINT ordine_documento FOREIGN KEY (tipo_documento) REFERENCES tipo_documento(iddocumento);


--
-- Name: ordini_idcliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordini
    ADD CONSTRAINT ordini_idcliente_fkey FOREIGN KEY (idcliente) REFERENCES clienti(idcliente);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

