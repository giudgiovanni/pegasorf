package rf.pegaso.gui.primanota;

public class PrimaNotaUtils {

	public static double getTotaleEntrateImponibile() {
		String query="select o.num_documento,c.nome,c.cognome, o.totale_documento,o.note from ordini as o,tipo_documento as d,  clienti as c where o.idordine>0 and o.idcliente=c.idcliente and o.tipo_documento=d.iddocumento order by o.data_documento desc";

		return 0;
	}

}
