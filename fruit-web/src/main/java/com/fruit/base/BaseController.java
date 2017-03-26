package com.fruit.base;

import com.fruit.entity.Company;
import com.fruit.entity.Employee;
import com.fruit.entity.Operationrecords;
import com.fruit.entity.UploadPicture;
import com.fruit.service.OperationRecordsService;
import com.fruit.service.UploadPictureService;
import com.fruit.utils.CompressPicUtils;
import com.fruit.utils.ConfigProperty;
import com.fruit.utils.MyTools;
import com.fruit.utils.ParamTool;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Created by TanLiu on 2017/2/26.
 */
public class BaseController {
    //把所有的service都注入到这里

    protected Employee me = null;
    protected Company company=null;

    @Autowired
    OperationRecordsService operationrecordsService;
    @Autowired
    UploadPictureService uploadPictureService;


    //TODO修改CompanyId
    public  int getCompanyId(){
        return 1;
    }

    public Company getCompany(){
        Company myCompany=new Company("SJ","十记金柚","梅州梅县","介绍~~~~~","网址~~~~~","110112119","委托~~~","文件~~~");
        myCompany.setId(1);
        return myCompany;
    }


    @ModelAttribute
    public void init(HttpSession session, HttpServletRequest request) throws IOException {
        me=(Employee)session.getAttribute("user");
        company = (Company) session.getAttribute("company");
        if(operationrecordsService!=null&&!request.getRequestURI().equals("/tools/init")){
            String name="";
            String userName = "";
            if(me!=null){
                name=me.getName();
                userName=me.getUsername();
            }
            //保存请求记录
            Operationrecords log = new Operationrecords(request.getRemoteHost(), request.getRequestURI(),
                    ParamTool.getRequestParamsLog(request.getParameterMap()), userName, name);
           // operationrecordsService.save(log);
        }


    }

    /**保存图片到指定目录下，并返回图片名称，图片以英文分号;分割，如1.jpg;2.jpg
     * @param files
     * @return
     */
    protected String saveUpdateImages( MultipartFile[] files) {
        StringBuilder sb = new StringBuilder();
        // 获取图片
        if (files != null && files.length > 0) {
            File fParent = new File(ConfigProperty.uploadImagePath);
            if (!fParent.exists()) {
                fParent.mkdirs();// 如果父目录不存在，就自动创建目录
            }
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                if (!file.isEmpty()){
                    String oldName = file.getOriginalFilename();
                    String newName = MyTools.getRandomFileName(oldName);
                    File newFile = new File(ConfigProperty.uploadImagePath + newName);

                    try {
                        FileUtils.writeByteArrayToFile(newFile, file.getBytes());
                        sb.append(newName).append(";");
                        File smallImage = new File(ConfigProperty.uploadSmallImagePath + newName);
                        File tinyImage = new File(ConfigProperty.uploadTinyImagePath+newName);

                        new CompressPicUtils().compressPic(newFile, smallImage, 800);
                        new CompressPicUtils().compressPic(newFile, tinyImage, 320);
                        UploadPicture uploadPicture = new UploadPicture(oldName, newName);

                        uploadPictureService.save(uploadPicture);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return sb.toString();
    }

    public Employee getMe() {
        return me;
    }
}
