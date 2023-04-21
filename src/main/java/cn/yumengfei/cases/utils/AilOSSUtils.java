package cn.yumengfei.cases.utils;

import cn.yumengfei.conf.AliOSSProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component //当前类对象由Spring创建和管理
public class AilOSSUtils {

    // 注入配置参数实体类对象
    @Autowired
    private AliOSSProperties aliOSSProperties;
    /*// 阿里云OSS域名
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    // 用户身份ID
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    //用户密钥
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    //存储空间的名字
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;*/

    // 创建ClientConfiguration。ClientConfiguration是OSSClient的配置类，可配置代理、连接超时、最大连接数等参数。
//    ClientBuilderConfiguration conf = new ClientBuilderConfiguration();

    public String upload(MultipartFile multipartFile) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = multipartFile.getInputStream();

        // 避免文件覆盖
        String originalFilename = multipartFile.getOriginalFilename();

        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 上传文件到OSS
        OSS ossClient = new OSSClientBuilder().build(
                aliOSSProperties.getEndpoint(),
                aliOSSProperties.getAccessKeyId(),
                aliOSSProperties.getAccessKeySecret());

        ossClient.putObject(aliOSSProperties.getBucketName(), fileName, inputStream);

        // 文件访问路径
        String url = aliOSSProperties.getEndpoint().split("//")[0] + "//"
                + aliOSSProperties.getBucketName() + "."
                + aliOSSProperties.getEndpoint().split("//")[1] + "/"
                + fileName;

        // 关闭ossClient
        ossClient.shutdown();
        return url;



    }
}
