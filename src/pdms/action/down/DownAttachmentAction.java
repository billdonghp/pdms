package pdms.action.down;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 下载实施提案附件
 * @author 程国前
 * 20140210
 */
public class DownAttachmentAction extends ActionSupport implements Serializable {

	public String execute(){
        return SUCCESS;
	}
	public String getInputPath(){
        return inputPath;
    }

    public void setInputPath(String inputPath){
        this.inputPath = inputPath;
    }
    public void setFileName(String fileName) {
            this.fileName = fileName;
    }
    public String getFileName(){
    	return this.fileName;
    }
    public InputStream getTargetFile() throws Exception{
    	File file = new File(inputPath+fileName); 
    	InputStream is = new FileInputStream(file);  
        return is;
    }
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7025598961316326207L;
	private String fileName;
	private String inputPath;
}
