CREATE TABLE codici_iva
(
  id bigint NOT NULL,
  codice character varying(3) NOT NULL,
  percentuale double precision NOT NULL,
  descrizione character varying(30),
  descrizione_breve character varying(15),
  CONSTRAINT pk_codici_iva PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE codici_iva OWNER TO postgres;

insert into codici_iva values (1,'20',(20.000),'Iva 20%','');

insert into codici_iva values (2,'10',(10.000),'Iva 10%','');

insert into codici_iva values (3,'4',(4.000),'Iva 4%','');

insert into codici_iva values (4,'8',(0.000),'Non imponibile art.8','Non Imp. art.8');

insert into codici_iva values (5,'2',(0.000),'Non imponibile art.2','Non Imp. art.2');

insert into codici_iva values (6,'15',(0.000),'Esente IVA art.15','Es.IVA art.15');

insert into codici_iva values (7,'50',(0.000),'Esente IVA Art. 1 Fin.2008','Es.IVA Fin2008');


-- impostiamo il campo iva su tutte le riche con codice 1 che corrisponde all'iva 20%
update articolo set iva=1;

-- aggiungiamo la fkey sulla colonna iva
ALTER TABLE articolo
  ADD CONSTRAINT articolo_iva_fkey FOREIGN KEY (iva)
      REFERENCES codici_iva (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;

-- impostiamo il campo iva su tutte le righe con codice 1 che corrisponde all'iva 20%
update carico set iva_documento=1;

ALTER TABLE carico
  ADD CONSTRAINT carico_iva_fkey FOREIGN KEY (iva_documento)
      REFERENCES codici_iva (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;


-- impostiamo il campo iva su tutte le righe con codice 1 che corrisponde all'iva 20%
update dettaglio_scarico set iva=1;

ALTER TABLE dettaglio_scarico
  ADD CONSTRAINT dettaglio_scarico_iva_fkey FOREIGN KEY (iva)
      REFERENCES codici_iva (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;


-- impostiamo il campo iva su tutte le righe con codice 1 che corrisponde all'iva 20%
update dettaglio_scarico_manuale set iva=1;

ALTER TABLE dettaglio_scarico_manuale
  ADD CONSTRAINT dettaglio_scarico_manuale_iva_fkey FOREIGN KEY (iva)
      REFERENCES codici_iva (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;


-- impostiamo il campo iva su tutte le righe con codice 1 che corrisponde all'iva 20%
update scarico set iva_documento=1;

ALTER TABLE scarico
  ADD CONSTRAINT scarico_iva_fkey FOREIGN KEY (iva_documento)
      REFERENCES codici_iva (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;

-- impostiamo il campo iva su tutte le righe con codice 1 che corrisponde all'iva 20%
update doc.dettaglio_documento set iva=1;

ALTER TABLE doc.dettaglio_documento
  ADD CONSTRAINT dettaglio_documento_iva_fkey FOREIGN KEY (iva)
      REFERENCES codici_iva (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;
      
      
DROP TABLE doc.iva CASCADE;
