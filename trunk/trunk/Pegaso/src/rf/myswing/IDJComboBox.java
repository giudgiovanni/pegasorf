package rf.myswing;


import java.awt.event.KeyListener;

import javax.swing.JComboBox;

import rf.myswing.exception.LunghezzeArrayDiverse;
import rf.utility.number.Arrays;

public class IDJComboBox extends JComboBox{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String codId[];  //  @jve:decl-index=0:
	private String descItem[];  //  @jve:decl-index=0:

	public IDJComboBox(String []id,String []descItem){
		super();
		this.codId=id;
		this.descItem=descItem;

		//carichiamo il combobox con i dati
		addItem("");
		for (String tmp : descItem) {
			addItem(tmp);
		}

	}

	public IDJComboBox(String []oggetti){
		super();
		this.codId=new String[oggetti.length];
		this.descItem=new String[oggetti.length];

		//carichiamo i dati negli array
		for(int i=0;i<oggetti.length;i++){
			String tmp[]=oggetti[i].split("-");
			//inseriamo l'id nel suo array
			//eliminando eventuali spazi bianchi
			this.codId[i]=tmp[0].trim();
			//inseriamo la descrizione nel suo array
			//eliminando eventuali spazi bianchi
			this.descItem[i]=tmp[1].trim();

		}
	}

	@Override
	public synchronized void addKeyListener(KeyListener arg0) {
		super.addKeyListener(arg0);
	}


	public void setSelectedItem(Object o){
		super.setSelectedItem(o);
	}

	public void setSelectedItemByID(int id){
		String codId=new Integer(id).toString();
		int pos=ricercaLineare(this.codId, codId);

		//se il combo contiene la prima riga vuota allora
		//facciamo pos + 1 altrimenti solo pos
		String tmp=(String)getItemAt(0);
		if(tmp==null)
			return;
		if(tmp.equals("")){
			setSelectedIndex(pos+1);
		}else setSelectedIndex(pos);
	}

	public IDJComboBox() {
		super();
		this.codId=new String[0];
		this.descItem=new String[0];
	}

	public String[] getId() {
		return codId;
	}

	public void setId(String[] id) {
		this.codId = id;
	}

	public Object[] getItem() {
		return descItem;
	}

	public void setItem(String[] descItem) {
		this.descItem = descItem;
	}

	//recupera il codice nell'array
	//utilizzando la descrizione ad esso associato
	//null in caso non trova niente
	public String getID(String item){
		//cerchiamo se l'oggetto item
		//è presente ed in caso affermativo
		//ritorniamo il codice corrispondente
		//all'item
		int pos=ricercaLineare(this.descItem, item);
		if(pos==-1)
			return null;
		return codId[pos];
	}

	//recupera la descrizione nell'array
	//utilizzando il codice ad esso associato
	//null in caso non trova niente
	public String getDescItem(String id){
		//cerchiamo se l'oggetto item
		//è presente ed in caso affermativo
		//ritorniamo il codice corrispondente
		//all'item
		int pos=ricercaLineare(this.codId, id);
		if(pos==-1)
			return null;
		return descItem[pos];
	}

	//effettua la ricerca lineare in un array di stringhe
	private int ricercaLineare(String[] oggetti, String daCercare){
		return Arrays.ricercaLineare(oggetti, daCercare);
	}



	public void caricaOggetti(Object[] items){
		for(Object o:items){
			addItem(o.toString());
		}
	}

	public void caricaIDAndOggetti(String []codId,String descItems[]) throws LunghezzeArrayDiverse{
		if(codId.length!=descItems.length)
			new LunghezzeArrayDiverse();

		if(this.codId.length==0){
			this.codId=new String[codId.length];
			this.descItem=new String[codId.length];
		}

		if(this.codId.length>0 && this.codId.length<codId.length){
			String tmp[]=Arrays.copyOf(this.codId);
			this.codId=Arrays.merge(tmp,codId);


		}
		for(int i=0;i<codId.length;i++){
			this.codId[i]=codId[i];
			this.descItem[i]=descItems[i];
			addItem(descItems[i]);
		}
	}


	// carica un combobox con tutti i dati presenti nell'array
	// eliminando tutti quelli già presenti.
	// se si passa true viene inserito come primo elemento nell'array
	// uno stringa vuota se si mette false il primo elemento visualizzato
	// sarà quello nella prima posizione della struttura dati.
	public void caricaNewValueComboBox(String []oggetti, boolean blank){
		//eliminiamo il contenuto del combobox e delle strutture dati
		removeAllItems();
		this.codId=new String[oggetti.length];
		this.descItem=new String[oggetti.length];

		if(blank)
			addItem("");

		//carichiamo i dati negli array
		for(int i=0;i<oggetti.length;i++){
			String tmp[]=oggetti[i].split("-",2);
			//inseriamo l'id nel suo array
			//eliminando eventuali spazi bianchi
			this.codId[i]=tmp[0].trim();
			//inseriamo la descrizione nel suo array
			//eliminando eventuali spazi bianchi
			this.descItem[i]=tmp[1].trim();
			addItem(this.descItem[i]);
		}
	}





	@Override
	public void removeAllItems(){
		this.codId=new String[0];
		this.descItem=new String[0];
		super.removeAllItems();
	}

	public String getIDSelectedItem(){
		Object o=getSelectedItem();
		if(o==null)
			return null;
		int pos=ricercaLineare(descItem, o.toString());
		if(pos==-1)
			return "-1";
		return codId[pos];
	}
}
