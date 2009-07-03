package rf.upload.mapping;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import org.xml.sax.SAXException;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.ReaderConfig;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

public class XlsReader<T> {

	List<T> rows = new ArrayList<T>();
	File xlsFile;
	public  XlsReader() {
		// TODO Auto-generated constructor stub
	}
	
	public void startReading(File xlsFile, String path) throws IOException, SAXException {
		T row = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		URL urlXML = loader.getResource(path);
		InputStream inputXML = urlXML.openStream();

		
		XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
		InputStream inputXLS = new FileInputStream(xlsFile);
		Map<String,Object> beans = new HashMap<String,Object>();

		beans.put("container", this);
		beans.put("row", row );
		
		ReaderConfig.getInstance().setSkipErrors(true);
		ReaderConfig.getInstance().setUseDefaultValuesForPrimitiveTypes(true);
		XLSReadStatus readStatus = mainReader.read(inputXLS, beans);

	}
	
	public void startReadingRowInSection(File xlsFile, T sectionRow, String path) throws IOException, SAXException {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		URL urlXML = loader.getResource(path);

		InputStream inputXML = urlXML.openStream();
		XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
		InputStream inputXLS = new FileInputStream(xlsFile);
		Map<String,Object> beans = new HashMap<String,Object>();

		beans.put("row", sectionRow );
		
		ReaderConfig.getInstance().setSkipErrors(true);
		ReaderConfig.getInstance().setUseDefaultValuesForPrimitiveTypes(true);
		XLSReadStatus readStatus = mainReader.read(inputXLS, beans);

	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public File getXlsFile() {
		return xlsFile;
	}

	public void setXlsFile(File xlsFile) {
		this.xlsFile = xlsFile;
	}
}
