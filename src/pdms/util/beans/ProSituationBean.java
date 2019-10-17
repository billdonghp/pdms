package pdms.util.beans;
import java.io.Serializable;
/**
 * 提案现况bean
 * @author 程国前
 * 20140206
 */
public class ProSituationBean implements Serializable {
	
	public ProSituationBean(){
		
	}

	public ProSituationBean(String ptype, String pstatus) {
		super();
		this.ptype = ptype;
		this.pstatus = pstatus;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	private static final long serialVersionUID = 6387764384009580356L;
	private String ptype;
	private String pstatus;
}