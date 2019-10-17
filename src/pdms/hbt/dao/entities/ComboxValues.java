package pdms.hbt.dao.entities;
import java.sql.Timestamp;
/**
 * 系统下拉框 entity 
 * @author 程国前
 * @time 2013-12-5
 */
public class ComboxValues implements java.io.Serializable {
	
	// Constructors
	/** default constructor */
	public ComboxValues() {
		
	}

	public ComboxValues(String code, Integer comvalue, String comview) {
		super();
		this.code = code;
		this.comvalue = comvalue;
		this.comview = comview;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getComvalue() {
		return comvalue;
	}

	public void setComvalue(Integer comvalue) {
		this.comvalue = comvalue;
	}

	public String getComview() {
		return comview;
	}

	public void setComview(String comview) {
		this.comview = comview;
	}
	public Timestamp getCreatedt() {
		return createdt;
	}
	public void setCreatedt(Timestamp createdt) {
		this.createdt = createdt;
	}

	private static final long serialVersionUID = 2014904105684563282L;
	private Integer id;				//ID
	private String 	code;			//代码
	private Integer comvalue;		//下拉表实际值
	private String 	comview;		//下拉列表显示值
	private Timestamp createdt;		//创建时间
}
