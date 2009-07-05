package rf.utility;

public class Constant {
	
	public static final int ORDINE = 0;
	public static final int CARICO = 1;
	public static final int SCARICO = 2;
	public static final int FATTURA = 3;
	public static final int DOCUMENTO_DI_TRASPORTO = 4;
	private static final String NUM_DOC_SCARICATO_AL_BANCO = "1234";
	private static final int FORNITORE_TABACCHI = 1;
	private static final int REPARTO_TABACCHI = 3;
	private static final int UNITA_MISURA_PEZZI = 2;
	
	public static int getOrdine() {
		return ORDINE;
	}
	
	public static int getCarico() {
		return CARICO;
	}
	
	public static int getScarico() {
		return SCARICO;
	}
	
	public static int getFattura() {
		return FATTURA;
	}
	
	public static int getDocumentoDiTrasporto() {
		return DOCUMENTO_DI_TRASPORTO;
	}

	public static String getNumeroDocScaricoAlBanco() {
		return NUM_DOC_SCARICATO_AL_BANCO;
	}
	
	public static int getFornitoreTabacchi() {
		return FORNITORE_TABACCHI;
	}
	
	public static int getRepartoTabacchi() {
		return REPARTO_TABACCHI;
	}

	public static int getUnitaMisuraPezzi() {
		return UNITA_MISURA_PEZZI;
	}
}
