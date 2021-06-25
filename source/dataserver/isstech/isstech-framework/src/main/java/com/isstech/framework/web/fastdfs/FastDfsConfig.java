package com.isstech.framework.web.fastdfs;

import lombok.extern.slf4j.Slf4j;
import org.csource.common.IniFileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description FastDfs 配置
 * @Author yzp
 * @Date 2020/12/11 15:42
 */
@Slf4j
@Component
public class FastDfsConfig {
    /**
     * fastdfs 配置文件路径
     */
    private static final String FASTDFS_CONFIG_PATH = "fastdfs-client.properties";


    /**
     * 文件服务器地址
     */
    public static String fileServerAddr;

    /**
     * FastDFS秘钥
     */
    public static String fastDFSHttpSecretKey;

    /**
     * 上传华为地址
     */
    public static String fileServerHWAddr;

    /**
     * 上传文件临时存储路径
     */
    public static String fileTempPath;


    /**
     * hw_vpn
     */
    public static String hwVpn;

    @PostConstruct
    public void redaConfig() throws IOException {
        Properties props = new Properties();
        InputStream in = IniFileReader.loadFromOsFileSystemOrClasspathAsStream(FASTDFS_CONFIG_PATH);
        if (in != null) {
            props.load(in);
        }
        fileServerAddr = props.getProperty("file_server_addr");
        fastDFSHttpSecretKey = props.getProperty("fastdfs.http_secret_key");
        fileServerHWAddr = props.getProperty("file_server_HW_addr");
        fileTempPath = props.getProperty("file_temp_path");
        hwVpn =  props.getProperty("hw_vpn");
    }

}
