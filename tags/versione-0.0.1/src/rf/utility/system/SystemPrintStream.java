/**
 * 
 */
package rf.utility.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class SystemPrintStream extends PrintStream {

	static FileOutputStream fos;

	public static void setFileName(String file) {
		try {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (Exception e) {
				}
			}
			File f = new File(file);
			boolean append = f.length() < 100000;
			if (!append) {
				File bak = new File(file + ".bak");
				if (bak.exists()) {
					bak.delete();
				}
				f.renameTo(bak);
			}
			fos = new FileOutputStream(f, append);

			// redirigo lo standard output
			System.setOut(new SystemPrintStream(fos));

			// redirigo lo standard error
			System.setErr(new SystemPrintStream(fos));
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	String line = "";

	/**
	 * Constructor.
	 */
	public SystemPrintStream(OutputStream out) {
		super(out, true);
	}

	@Override
	public void print(String s) {
		if (fos != null && s != null) {
			line += s;
			if (s.endsWith("\n")) {
				super.print(line);
				line = "";
			}
		}
	}

	@Override
	public void println(String s) {
		if (s == null)
			s = "null";
		if (!s.endsWith("\n"))
			s = s + "\n";
		print(s); // avoid additional line feed
	}
}
