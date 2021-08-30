package com.baizhi.util;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class AliyunOssUtil {

    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    private static String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private static String accessKeyId = "LTAI5tCYWDFxoYNDd1dSaGgQ";
    private static String accessKeySecret = "EiPM74uVnOP07OdDYQROcbd8hj0VhK";

    /**
     * 将文件以字节数组的形式上传至阿里云
     * 参数：
     * @param bucketName（String）  存储控件名
     * @param FileName（String）     文件名  目录名/文件名
     * @param headImg（MultipartFile）MultipartFile类型的文件
     * */
    public static void uploadfileBytes(String bucketName, String FileName, MultipartFile headImg){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 填写Byte数组。
        byte[] bytes = new byte[0];

        try {
            bytes = headImg.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, FileName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void deleteBucket(String bucketName, String fileName){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, fileName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
    /**
     * 将文件以字节数组的形式上传至阿里云
     * 参数：
     * @param bucketName（String）  存储控件名
     * @param FileName（String）     文件名  目录名/文件名
     //* @param headImg（MultipartFile）MultipartFile类型的文件
     * */
    public static void videoInt(String bucketName,String FileName) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "<yourAccessKeyId>";
        String accessKeySecret = "<yourAccessKeySecret>";
// 填写视频文件所在的Bucket名称。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
// 使用精确时间模式截取视频50s处的内容，输出为JPG格式的图片，宽度为800，高度为600。
        String style = "video/snapshot,t_50000,f_jpg,w_800,h_600";
// 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, FileName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println(signedUrl);
// 关闭OSSClient。
        ossClient.shutdown();

    }

}