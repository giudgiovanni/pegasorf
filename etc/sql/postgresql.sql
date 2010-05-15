
    create table doc.acconto (
        id int8 not null,
        fattura int8,
        data date,
        totale float8,
        note varchar(255),
        primary key (id)
    );

    create table doc.bolla (
        id int8 not null,
        primary key (id)
    );

    create table doc.consegna (
        id int8 not null,
        descrizione varchar(255),
        primary key (id)
    );

    create table doc.ddt (
        id int8 not null,
        porto int8,
        causale int8,
        pagamento int8,
        consegna_a_mezzo int8,
        serie varchar(3),
        sconto float8,
        spese_trasporto float8,
        spese_incasso float8,
        riferimento varchar(255),
        colli int4,
        destinazione int8,
        primary key (id)
    );

    create table doc.dettaglio_documento (
        id int8 not null,
        iva int8,
        documento int8,
        descrizione varchar(255),
        um char(1),
        quantita float8,
        imponibile float8,
        sconto int4,
        primary key (id)
    );

    create table doc.documento (
        id int8 not null,
        cliente int8,
        data_emissione date,
        tipo_documento int2,
        note varchar(255),
        numero_documento int8,
        primary key (id)
    );

    create table doc.fattura (
        id int8 not null,
        pagamento int8,
        spese_trasporto float8,
        spese_incasso float8,
        note_pagamento varchar(255),
        serie char(1),
        sconto float8,
        banca_abi varchar(5),
        banca_cab varchar(5),
        banca_iban varchar(1),
        primary key (id)
    );

    create table doc.iva (
        id int8 not null,
        codice_iva int4,
        iva int4,
        descrizione varchar(255),
        primary key (id)
    );

    create table doc.ordine (
        id int8 not null,
        stato int8,
        pagamento int8,
        sconto int4,
        spese_trasporto float8,
        spese_incasso float8,
        annotazioni varchar(255),
        note_pagamento varchar(255),
        banca_abi varchar(5),
        banca_cab varchar(5),
        banca_iban varchar(255),
        convertito int4,
        tipo_documento varchar(3),
        primary key (id)
    );

    create table doc.porto (
        id int8 not null,
        descrizione varchar(255),
        primary key (id)
    );

    create table doc.ricevuta (
        id int8 not null,
        pagamento int8,
        spese_trasporto float8,
        spese_incasso float8,
        note_pagamento varchar(255),
        serie varchar(3),
        sconto float8,
        banca_abi varchar(5),
        banca_cab varchar(5),
        banca_iban varchar(1),
        primary key (id)
    );

    create table doc.stato (
        id int8 not null,
        descrizione varchar(255),
        primary key (id)
    );

    create table public.articoli_caricati_view (
        idarticolo int8 not null,
        idcarico int8 not null,
        codbarre varchar(40) not null,
        descrizione varchar(100) not null,
        iva int8 not null,
        um varchar(40) not null,
        qta float8 not null,
        prezzo_acquisto float8 not null,
        sconto int4 not null,
        primary key (idarticolo, idcarico, codbarre, descrizione, iva, um, qta, prezzo_acquisto, sconto)
    );

    create table public.articoli_scaricati_view (
        idordine int8 not null,
        idarticolo int8 not null,
        codbarre varchar(40) not null,
        descrizione varchar(100) not null,
        prezzo_acquisto float8 not null,
        prezzo_ingrosso float8 not null,
        prezzo_dettaglio float8 not null,
        iva int8 not null,
        um varchar(40) not null,
        qta float8 not null,
        sconto int8 not null,
        prezzo_vendita float8 not null,
        primary key (idordine, idarticolo, codbarre, descrizione, prezzo_acquisto, prezzo_ingrosso, prezzo_dettaglio, iva, um, qta, sconto, prezzo_vendita)
    );

    create table public.articolo (
        idarticolo int8 not null,
        idpannello int8,
        idreparto int8,
        um int8,
        codfornitore varchar(40),
        codbarre varchar(40),
        descrizione varchar(100) not null,
        prezzo_acquisto float8,
        iva int8,
        prezzo_dettaglio float8,
        prezzo_ingrosso float8,
        imballo varchar(50),
        peso float8,
        sconto int8,
        colore varchar(40),
        scorta_minima int8,
        note varchar(200),
        data_inserimento date,
        carico_iniziale int8,
        scorta_massima int8,
        numero_pacchetti int4,
        qta_infinita bool,
        primary key (idarticolo)
    );

    create table public.aspetto (
        idaspetto int8 not null,
        nome varchar(40) not null,
        primary key (idaspetto)
    );

    create table public.carico (
        idcarico int8 not null,
        idfornitore int8,
        iddocumento int8,
        data_carico date,
        ora_carico time,
        note varchar(200),
        num_documento varchar(255),
        data_documento date,
        totale_documento float8,
        sospeso int4,
        rif_doc int4,
        sconto int4,
        iva_documento int4,
        ins_pn int4,
        riferimento_ordine int8,
        primary key (idcarico)
    );

    create table public.categoria (
        id int8 not null,
        data_creazione date,
        descrizione varchar(255) not null,
        note varchar(255),
        primary key (id)
    );

    create table public.causale (
        idcausale int8 not null,
        nome varchar(40) not null,
        primary key (idcausale)
    );

    create table public.cliente (
        idcliente int8 not null,
        nazionalita int8,
        documento int8,
        provincia int8,
        rilasciato_da int8,
        data_inserimento date,
        nome varchar(40) not null,
        cognome varchar(40) not null,
        piva varchar(40),
        codfisc varchar(40),
        via varchar(40),
        cap varchar(40),
        citta varchar(40),
        tel varchar(40),
        cell varchar(40),
        fax varchar(40),
        email varchar(50),
        website varchar(40),
        note varchar(200),
        data_nascita date,
        num_doc varchar(255),
        rilasciato_il date,
        nato_a varchar(255),
        intestazione varchar(255),
        rilasciato_di varchar(255),
        primary key (idcliente)
    );

    create table public.dettaglio_carico (
        idarticolo int8 not null,
        idcarico int8 not null,
        qta float8 not null,
        prezzo_acquisto float8,
        sconto int4,
        primary key (idarticolo, idcarico)
    );

    create table public.dettaglio_scarico (
        idordine int8 not null,
        idarticolo int8 not null,
        qta float8 not null,
        sconto int8,
        prezzo_acquisto float8,
        prezzo_vendita float8,
        iva int4,
        primary key (idordine, idarticolo)
    );

    create table public.dettaglio_scarico_manuale (
        id int8 not null,
        ordine int8 not null,
        descrizione varchar(255) not null,
        qta float8 not null,
        sconto int8,
        prezzo_vendita float8,
        iva int4,
        primary key (id, ordine)
    );

    create table public.documento_cliente (
        id int8 not null,
        descrizione varchar(255) not null,
        abbreviazione varchar(10),
        primary key (id)
    );

    create table public.ente (
        idente int8 not null,
        descrizione varchar(255) not null,
        primary key (idente)
    );

    create table public.fornitore (
        idfornitore int8 not null,
        provincia int8,
        data_inserimento date,
        nome varchar(100) not null,
        piva varchar(40),
        codfisc varchar(40),
        via varchar(40),
        cap varchar(40),
        citta varchar(40),
        tel varchar(40),
        cell varchar(40),
        fax varchar(40),
        email varchar(40),
        website varchar(40),
        note varchar(200),
        codbarre varchar(255),
        primary key (idfornitore)
    );

    create table public.giacenza_articoli_all_view (
        idarticolo int8 not null,
        codice varchar(40) not null,
        descrizione varchar(100) not null,
        um varchar(40) not null,
        carico float8 not null,
        scarico float8 not null,
        deposito float8 not null,
        prezzo_acquisto float8 not null,
        prezzo_tot float8 not null,
        primary key (idarticolo, codice, descrizione, um, carico, scarico, deposito, prezzo_acquisto, prezzo_tot)
    );

    create table public.giacenza_articoli_view (
        idarticolo int8 not null,
        codice varchar(40) not null,
        descrizione varchar(100) not null,
        um varchar(40) not null,
        carico float8 not null,
        scarico float8 not null,
        deposito float8 not null,
        prezzo_acquisto float8 not null,
        prezzo_tot float8 not null,
        primary key (idarticolo, codice, descrizione, um, carico, scarico, deposito, prezzo_acquisto, prezzo_tot)
    );

    create table public.immagine_articolo (
        id int8 not null,
        articolo int8,
        nome varchar(255) not null,
        estensione varchar(10),
        file bytea,
        primary key (id)
    );

    create table public.nazionalita (
        idnazionalita int8 not null,
        descrizione varchar(255) not null,
        primary key (idnazionalita)
    );

    create table public.pagamento (
        idpagamento int8 not null,
        nome varchar(40) not null,
        primary key (idpagamento)
    );

    create table public.pannelli (
        idpannelli int8 not null,
        nome varchar(40),
        primary key (idpannelli)
    );

    create table public.progressivi (
        anno int4 not null,
        prog_fattura int4 not null,
        prog_ricevuta int4 not null,
        prog_scontrino int4 not null,
        prog_non_fiscale int4 not null,
        primary key (anno)
    );

    create table public.provincia (
        idprovincia int8 not null,
        provincia varchar(255) not null,
        targa varchar(2),
        primary key (idprovincia)
    );

    create table public.qta_caricate_view (
        idarticolo int8 not null,
        codbarre varchar(40) not null,
        sum float8 not null,
        primary key (idarticolo, codbarre, sum)
    );

    create table public.qta_scaricate_view (
        idarticolo int8 not null,
        codbarre varchar(40) not null,
        sum float8 not null,
        primary key (idarticolo, codbarre, sum)
    );

    create table public.reparto (
        idreparto int8 not null,
        categoria int8,
        data_creazione date,
        nome varchar(40),
        descrizione varchar(100),
        primary key (idreparto)
    );

    create table public.scarico (
        idordine int8 not null,
        tipo_documento int8,
        idaspetto int8,
        pagamento int8,
        idcausale int8,
        idpagamento int8,
        idcliente int8,
        data_ordine date,
        ora_ordine time,
        note varchar(200),
        num_documento varchar(255),
        data_documento date,
        totale_documento float8,
        iva_documento int4,
        doc_emesso int4,
        doc_fiscale int4,
        ins_pn int4,
        spese_incasso float8,
        spese_trasporto float8,
        data_trasp date,
        ora_trasp time,
        colli int4,
        peso float8,
        consegna varchar(255),
        porto varchar(255),
        diversa_dest varchar(255),
        sconto int4,
        sconto_euro float8,
        numero_fattura varchar(200),
        numero_ricevuta varchar(20),
        numero_non_fiscale varchar(20),
        riferimento_ordine int8,
        primary key (idordine)
    );

    create table public.tipo_documento (
        iddocumento int8 not null,
        tipo varchar(40) not null,
        descrizione varchar(200),
        primary key (iddocumento)
    );

    create table public.tmp_etichette (
        id int8 not null,
        codice varchar(255) not null,
        descrizione varchar(255) not null,
        prezzo float8 not null,
        note varchar(255) not null,
        primary key (id, codice, descrizione, prezzo, note)
    );

    create table public.tmp_etichette_fornitori (
        idfornitore int8 not null,
        nome varchar(255),
        via varchar(255),
        cap varchar(255),
        citta varchar(255),
        provincia varchar(255),
        primary key (idfornitore)
    );

    create table public.tmp_scelti (
        descrizione varchar(255) not null,
        qta int4 not null,
        prezzo float8 not null,
        totale float8 not null,
        primary key (descrizione, qta, prezzo, totale)
    );

    create table public.um (
        idum int8 not null,
        nome varchar(40) not null,
        descrizione varchar(100),
        primary key (idum)
    );

    alter table doc.acconto 
        add constraint FKB9D3709BE17C79BF 
        foreign key (fattura) 
        references doc.fattura;

    alter table doc.bolla 
        add constraint FK5991774437FF43C 
        foreign key (id) 
        references doc.documento;

    alter table doc.ddt 
        add constraint FK183F44B2CC309 
        foreign key (porto) 
        references doc.porto;

    alter table doc.ddt 
        add constraint FK183F46876BAB9 
        foreign key (pagamento) 
        references public.pagamento;

    alter table doc.ddt 
        add constraint FK183F4A431C4E9 
        foreign key (causale) 
        references public.causale;

    alter table doc.ddt 
        add constraint FK183F46F9756F9 
        foreign key (consegna_a_mezzo) 
        references doc.consegna;

    alter table doc.dettaglio_documento 
        add constraint FKC9584A008434DBD5 
        foreign key (iva) 
        references doc.iva;

    alter table doc.dettaglio_documento 
        add constraint FKC9584A007BBD39D5 
        foreign key (documento) 
        references doc.documento;

    alter table doc.documento 
        add constraint FK383D52B4C85DD7A1 
        foreign key (cliente) 
        references public.cliente;

    alter table doc.fattura 
        add constraint FKBFDAD709437FF43C 
        foreign key (id) 
        references doc.documento;

    alter table doc.fattura 
        add constraint FKBFDAD7096876BAB9 
        foreign key (pagamento) 
        references public.pagamento;

    alter table doc.ordine 
        add constraint FKC3DF715F4B855C23 
        foreign key (stato) 
        references doc.stato;

    alter table doc.ordine 
        add constraint FKC3DF715F437FF43C 
        foreign key (id) 
        references doc.documento;

    alter table doc.ordine 
        add constraint FKC3DF715F6876BAB9 
        foreign key (pagamento) 
        references public.pagamento;

    alter table doc.ricevuta 
        add constraint FKA36ABDC5437FF43C 
        foreign key (id) 
        references doc.documento;

    alter table doc.ricevuta 
        add constraint FKA36ABDC56876BAB9 
        foreign key (pagamento) 
        references public.pagamento;

    alter table public.articolo 
        add constraint FKB6C0DE55483DC344 
        foreign key (idreparto) 
        references public.reparto;

    alter table public.articolo 
        add constraint FKB6C0DE5573530ABE 
        foreign key (idpannello) 
        references public.pannelli;

    alter table public.articolo 
        add constraint FKB6C0DE551D09F963 
        foreign key (um) 
        references public.um;

    alter table public.carico 
        add constraint FKAE7C164166007E16 
        foreign key (idfornitore) 
        references public.fornitore;

    alter table public.carico 
        add constraint FKAE7C16417764E3C6 
        foreign key (iddocumento) 
        references public.tipo_documento;

    alter table public.cliente 
        add constraint FK334B85FA4335DC81 
        foreign key (nazionalita) 
        references public.nazionalita;

    alter table public.cliente 
        add constraint FK334B85FACA050BC7 
        foreign key (provincia) 
        references public.provincia;

    alter table public.cliente 
        add constraint FK334B85FAE615563A 
        foreign key (rilasciato_da) 
        references public.ente;

    alter table public.cliente 
        add constraint FK334B85FA1E6724D 
        foreign key (documento) 
        references public.documento_cliente;

    alter table public.dettaglio_carico 
        add constraint FK8594275616F28B8 
        foreign key (idarticolo) 
        references public.articolo;

    alter table public.dettaglio_carico 
        add constraint FK859427531CD4410 
        foreign key (idcarico) 
        references public.carico;

    alter table public.dettaglio_scarico 
        add constraint FK53B10EC0616F28B8 
        foreign key (idarticolo) 
        references public.articolo;

    alter table public.dettaglio_scarico 
        add constraint FK53B10EC0DCD97E5B 
        foreign key (idordine) 
        references public.scarico;

    alter table public.dettaglio_scarico_manuale 
        add constraint FKC27806E0978EF180 
        foreign key (ordine) 
        references public.scarico;

    alter table public.fornitore 
        add constraint FK6E203432CA050BC7 
        foreign key (provincia) 
        references public.provincia;

    alter table public.immagine_articolo 
        add constraint FK2E361B034494669D 
        foreign key (articolo) 
        references public.articolo;

    alter table public.reparto 
        add constraint FK4137F489C5EC56D3 
        foreign key (categoria) 
        references public.categoria;

    alter table public.scarico 
        add constraint FK71E8B47471754802 
        foreign key (idaspetto) 
        references public.aspetto;

    alter table public.scarico 
        add constraint FK71E8B474838D36E 
        foreign key (idcausale) 
        references public.causale;

    alter table public.scarico 
        add constraint FK71E8B474B9473F36 
        foreign key (tipo_documento) 
        references public.tipo_documento;

    alter table public.scarico 
        add constraint FK71E8B4742C64E626 
        foreign key (idcliente) 
        references public.cliente;

    alter table public.scarico 
        add constraint FK71E8B4746876BAB9 
        foreign key (pagamento) 
        references public.pagamento;

    alter table public.scarico 
        add constraint FK71E8B474E6F43BFE 
        foreign key (idpagamento) 
        references public.pagamento;
