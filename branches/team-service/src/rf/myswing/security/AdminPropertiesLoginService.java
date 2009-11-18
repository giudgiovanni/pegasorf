package rf.myswing.security;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.jdesktop.swingx.auth.LoginService;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AdminPropertiesLoginService extends LoginService {

	private Properties props;
	BASE64Decoder dec;
	BASE64Encoder enc;
	
	
	public AdminPropertiesLoginService() throws FileNotFoundException, IOException{
		super();
		new AdminPropertiesLoginService("AdminConfig.properties");
	}
	
	public AdminPropertiesLoginService(String pathFile) throws FileNotFoundException, IOException{
		props=new Properties();
		props.load(new FileReader(pathFile));
		dec=new BASE64Decoder();
		enc=new BASE64Encoder();
	}
	
	@Override
	public boolean authenticate(String user, char[] psw, String server)
			throws Exception {
		boolean trovato=false;
		if(props.containsKey("ADMIN") || props.containsKey("admin")){
			trovato=true;
		}
		if(!trovato){
			String pass="rofrpi";
			pass=enc.encode(pass.getBytes());
			props.setProperty("ADMIN",pass );
		}
		//codifichiamo la psw passata 
		//per confrontarla con la psw cifrata all'interno
		//del file properties
		String tmp=psw.toString();
		tmp=enc.encode(tmp.getBytes());
		
		//recuperiamo la stringa cifrata all'interno del 
		//file di properties e la confrontiamo
		String tmp1=props.getProperty(user);
		//ritorniamo il valore
		return tmp1.equals(tmp);
	}

}
