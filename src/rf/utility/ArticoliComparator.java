package rf.utility;

import it.infolabs.hibernate.Articoli;

import java.util.Comparator;

public class ArticoliComparator  implements Comparator<Articoli>{

	@Override
	public int compare(Articoli o1, Articoli o2) {
		return o1.getDescrizione().compareToIgnoreCase(o2.getDescrizione());		
	}
}
