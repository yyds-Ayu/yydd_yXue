package com.baizhi;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.baizhi.dao.AdminDao;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.dao.FeedbackMapper;
import com.baizhi.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@SpringBootTest
class YzwApplicationOssTests {

    @Test
    void contextLoads() {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = "LTAI5tCYWDFxoYNDd1dSaGgQ";
            String accessKeySecret = "EiPM74uVnOP07OdDYQROcbd8hj0VhK";


            String bucketName = "yingx-yyds";//指定空间
            String objectName = "headImg/laa.png";//文件名
            String localFilePath = "D:\\assets\\img.png";//文件名的完整路径不包含bucket

    // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    // 创建PutObjectRequest对象。
    // 依次填写Bucket名称（例如examplebucket）、Object完整路径（例如exampledir/exampleobject.txt）和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
    // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,objectName, new File(localFilePath));

    // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
    // ObjectMetadata metadata = new ObjectMetadata();
    // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
    // metadata.setObjectAcl(CannedAccessControlList.Private);
    // putObjectRequest.setMetadata(metadata);

    // 上传文件。
            ossClient.putObject(putObjectRequest);

    // 关闭OSSClient。
            ossClient.shutdown();

    }
    @Test
    void sanChu(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
            String accessKeyId = "LTAI5tCYWDFxoYNDd1dSaGgQ";
            String accessKeySecret = "EiPM74uVnOP07OdDYQROcbd8hj0VhK";

    // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    // 删除存储空间。
            ossClient.deleteBucket("yingx-yyds");

    // 关闭OSSClient。
            ossClient.shutdown();
    }
    @Test
    void xiaZai(){
           /* // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
            String endpoint = "yourEndpoint";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = "yourAccessKeyId";
            String accessKeySecret = "yourAccessKeySecret";
    // 填写Bucket名称。*/
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tCYWDFxoYNDd1dSaGgQ";
        String accessKeySecret = "EiPM74uVnOP07OdDYQROcbd8hj0VhK";

        String bucketName = "yingx-yyds";
    // 填写不包含Bucket名称在内的Object完整路径，例如testfolder/exampleobject.txt。
            String objectName = "la.png";

    // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    // 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
    // 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
            ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("D:\\assets"));

    // 关闭OSSClient。
            ossClient.shutdown();

    }
}
