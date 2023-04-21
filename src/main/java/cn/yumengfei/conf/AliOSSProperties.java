package cn.yumengfei.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云相关配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
    // 区域 域名
    private String endpoint;
    // 身份id
    private String accessKeyId;
    // 身份秘钥
    private String accessKeySecret;
    // 存储空间
    private String bucketName;
}
