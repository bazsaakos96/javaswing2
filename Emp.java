import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Emp implements Serializable {
	private int kod;
	private String nev;
	private Date szulido;
	private String lakohely;
	private int iq;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

	public Emp(int kod, String nev, Date szulido,
			String lakohely, int iq){
			this.kod = kod;
			this.nev = nev;
			this.szulido = szulido;
			this.lakohely = lakohely;
			this.iq = iq;
			}

	public int getKod() {
		return kod;
	}

	public String getNev() {
		return nev;
	}


	public Date getSzulido() {
		return szulido;
	}

	public String getLakohely() {
		return lakohely;
	}

	
	public int getIq() {
		return iq;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
