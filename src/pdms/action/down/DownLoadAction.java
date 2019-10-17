package pdms.action.down;
import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 下载用户操作手册
 * @author 程国前
 * 2014-2-26
 */
public class DownLoadAction extends ActionSupport {
	private static final long serialVersionUID = -2186514377152151277L;
	private String download;
    private String inputPath;

    public String getInputPath(){
        return inputPath;
    }

    public void setInputPath(String inputPath){
        this.inputPath = inputPath;
    }

    public String getDownload(){
        return download;
    }

    public void setDownload(String download){
        this.download = download;
    }

    public InputStream getTargetFile() throws Exception{
        return ServletActionContext.getServletContext().getResourceAsStream(inputPath+download);
    }

    public String execute(){
        return SUCCESS;
    }
}
