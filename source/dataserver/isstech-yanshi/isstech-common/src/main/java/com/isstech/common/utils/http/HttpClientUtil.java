package com.isstech.common.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.Resp;
import com.isstech.common.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description HttpClientUtil工具类
 * @Author yzp
 * @Date 2020/12/18 15:10
 */
@Slf4j
public class HttpClientUtil {

    public static Resp doGet(String url) {
        Map<String, String> headerMap = new HashMap<>(2);
        headerMap.put(HttpHeaders.CONTENT_TYPE, ContentType.TEXT_PLAIN.toString());
        return doGetWithHeader(url, null, headerMap);
    }

    /**
     * Get请求
     *
     * @param url
     * @param param
     * @return
     */
    public static Resp doGet(String url, Map<String, String> param) {
        Map<String, String> headerMap = new HashMap<>(2);
        headerMap.put(HttpHeaders.CONTENT_TYPE, ContentType.TEXT_PLAIN.toString());
        return doGetWithHeader(url, param, headerMap);
    }

    /**
     * Get请求
     *
     * @param url
     * @param param
     * @param headerMap
     * @return
     */
    public static Resp doGetWithHeader(String url, Map<String, String> param, Map<String, String> headerMap) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Resp result = Resp.fail();
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, URLEncoder.encode(param.get(key), "UTF-8"));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            result = getResult(response);
        } catch (Exception e) {
            log.error("http request error！", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                log.error("http request error！", e);
            }
        }
        return result;
    }

    /**
     * post请求
     *
     * @param url         请求地址
     * @param urlParam    地址栏拼接参数
     * @param json        json参数
     * @return Resp
     */
    public static Resp doPost(String url, Map<String, String> headerMap, Map<String, String> urlParam, String json) {
        CloseableHttpResponse response = null;
        Resp result = Resp.fail();
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        try {
            StringBuilder param = new StringBuilder("");
            URIBuilder builder = new URIBuilder(url);
            if (urlParam != null) {
                for (String key : urlParam.keySet()) {
                    //builder.addParameter(key, URLEncoder.encode(urlParam.get(key), "UTF-8"));
                    builder.addParameter(key, urlParam.get(key));
                }
            }
            URI uri = builder.build();
            HttpPost httpPost = new HttpPost(uri);

            //设置公共请求头
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    httpPost.addHeader(key, headerMap.get(key));
                }
            }
            // 请求参数设置
            if (!StringUtils.isEmpty(json)) {
                httpPost.setEntity(new StringEntity(json, Charsets.UTF_8));
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            result = getResult(response);
        } catch (Exception e) {
            log.error("http request error！", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                log.error("http request error！ IOException", e);
            }
        }
        return result;
    }

    public static Resp doPost(String url) {
        return doPost(url, null, null, null);
    }

    public static Resp doPost(String url, Map<String, String> urlParam) {
        return doPost(url, null, urlParam, null);
    }

    public static Resp doPost(String url, Map<String, String> headerMap, String json) {
        return doPost(url, headerMap, null, json);
    }

    /**
     * Post请求
     *
     * @param url         请求地址
     * @param json        json字符串
     * @return
     */
    public static Resp doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        Resp result = Resp.fail();
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            //设置公共请求头
            httpPost.addHeader("version", "1.0.0");
            httpPost.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            // 创建请求内容
            if (!StringUtils.isEmpty(json)) {
                httpPost.setEntity(new StringEntity(json, Charsets.UTF_8));
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            result = getResult(response);
        } catch (Exception e) {
            log.error("http request error！", e.toString().substring(0,25));
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                log.error("http request error！", e.toString().substring(0,25));
            }
        }
        return result;
    }

    /**
     * 文件上传
     *
     * @param url
     * @param file
     * @return
     */
    public static Resp doPostFile(String url, File file, Map<String, String> formParams) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        Resp result = Resp.fail();
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            //设置公共请求头
            //httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.MULTIPART_FORM_DATA.toString());
            httpPost.addHeader("version", "1.0.0");
            //FileBody imagePart = new FileBody(file);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));//设置请求的编码格式
            multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//设置浏览器兼容模式

            if (formParams != null) {
                for (String key : formParams.keySet()) {
                    multipartEntityBuilder.addTextBody(key, formParams.get(key), ContentType.parse("text/plain; charset=UTF-8"));
                }
            }
            HttpEntity httpEntity = multipartEntityBuilder.addBinaryBody("files", file).build();
            /*switch (serviceName) {
                case Constant.SERVICE_VSS:
                    httpPost.addHeader("Authorization", SaticScheduleTask.vss_token);
                    break;
                case Constant.SERVICE_FACE_BAIDU:
                    httpPost.addHeader("Authorization", SaticScheduleTask.face_token);
                    break;
                case Constant.SERVICE_VCM_HUAWEI:
                    httpPost.addHeader("Authorization", SaticScheduleTask.face_token);
                    break;
                case Constant.SERVICE_VAS_HUAWEI:
                    httpPost.addHeader("Authorization", SaticScheduleTask.vas_token);
                    break;
                default:
                    break;
            }*/
            // 创建请求内容
            httpPost.setEntity(httpEntity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            result = getResult(response);
        } catch (Exception e) {
            log.error("http request error！", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
                file.delete();
            } catch (IOException e) {
                log.error("http request error！", e);
            }
        }
        return result;
    }

    public static Resp doPut(String url, String json) {
        return doPut(url, null, null, json);
    }

    public static Resp doPutWithHeader(String url, Map<String, String> headerMap, String json) {
        return doPut(url, null, null, json);
    }

    /**
     * put请求
     *
     * @param url         请求地址
     * @param urlParam    地址栏拼接参数
     * @param json        json参数
     * @return Resp
     */
    public static Resp doPut(String url, Map<String, String> headerMap, Map<String, String> urlParam, String json) {
        CloseableHttpResponse response = null;
        Resp result = Resp.fail();
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .build();
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        try {
            StringBuilder param = new StringBuilder("");
            URIBuilder builder = new URIBuilder(url);
            if (urlParam != null) {
                for (String key : urlParam.keySet()) {
                    builder.addParameter(key, URLEncoder.encode(urlParam.get(key), "UTF-8"));
                }
            }
            URI uri = builder.build();
            HttpPut httpPut = new HttpPut(uri);
            //设置公共请求头
            httpPut.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    httpPut.addHeader(key, headerMap.get(key));
                }
            }
            // 请求参数设置
            if (!StringUtils.isEmpty(json)) {
                httpPut.setEntity(new StringEntity(json, Charsets.UTF_8));
            }
            // 执行http请求
            response = httpClient.execute(httpPut);
            // 判断返回状态是否为200
            result = getResult(response);
        } catch (Exception e) {
            log.error("http request error！", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                log.error("http request error！IOException", e);
            }
        }
        return result;
    }




    public static CloseableHttpResponse exportExcel(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        AjaxResult result = AjaxResult.error();
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            //设置公共请求头
            httpPost.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            // 创建请求内容
            if (!StringUtils.isEmpty(json)) {
                httpPost.setEntity(new StringEntity(json, Charsets.UTF_8));
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            log.error("http request error！", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                log.error("http request error！", e);
            }
        }
        return response;
    }

    private static Resp getResult(CloseableHttpResponse response) throws IOException {
        String resultString;
        Resp result;
        switch (response.getStatusLine().getStatusCode()) {
            case 200:
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
                result = dealResult(resultString);
                break;
            case 302:
                result = Resp.fail(ErrorCode.BAIDU_ERROR_302);
                break;
            case 400:
                result = Resp.fail(ErrorCode.BAIDU_ERROR_400);
                break;
            case 404:
                result = Resp.fail(ErrorCode.BAIDU_ERROR_404);
                break;
            default:
                result = Resp.result(String.valueOf(response.getStatusLine().getStatusCode()), EntityUtils.toString(response.getEntity(), "utf-8"));
                break;
        }
        return result;
    }

    private static Resp dealResult( String resultString) {
        Resp result;
        try {
            result = JSONObject.toJavaObject(JSON.parseObject(resultString), Resp.class);
        } catch (Exception ex) {
            result = Resp.fail();
            log.error("HttpClientUtil---json转换错误！", ex);
        }
        return result;
    }

}
