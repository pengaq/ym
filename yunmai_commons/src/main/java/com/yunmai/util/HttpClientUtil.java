package com.yunmai.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	private static final String APPLICATION_JSON = "application/json";
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    
    private static final String CHARSET = "utf-8";
    
	private static final String CHARSET_UTF8 = "UTF-8";
	private static final String CHARSET_GBK = "GBK";
	private static final String SSL_DEFAULT_SCHEME = "https";
	private static final int SSL_DEFAULT_PORT = 443;
    
	 public static String doPost(String url, Map<String, String> headerMap,Map<String, String> bodyMap,String jsonString) throws ClientProtocolException, IOException {
		 
		 
//		 	String encoderJson = URLEncoder.encode(jo.toString(), HTTP.UTF_8);
	        
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(url);
	        if(bodyMap == null){
	        	httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
	        }
	        if(headerMap != null){
	        	for(String key : headerMap.keySet()){
	        		
	        		httpPost.addHeader(key, headerMap.get(key));
	        	}
	        }
	        if(StringUtils.isNotBlank(jsonString)){
	        	
	        	StringEntity se = new StringEntity(jsonString);
	        	se.setContentType(CONTENT_TYPE_TEXT_JSON);
	        	se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
	        	httpPost.setEntity(se);
	        }
	        
	        if(bodyMap != null){
	        	
	        	UrlEncodedFormEntity formEntity = null;
	        	formEntity = new UrlEncodedFormEntity(getParamsList(bodyMap), HTTP.UTF_8);
	        	httpPost.setEntity(formEntity);
	        }
	    	
	    	
	        HttpResponse res = httpClient.execute(httpPost);
	        String jsonStr = EntityUtils.toString(res.getEntity(), CHARSET);
	        httpClient.getConnectionManager().shutdown();
	        
	        return jsonStr;
		 
	 }
	 
	 
		
		/**
		* 将传入的键/值对参数转换为NameValuePair参数集
		* 
		* @param paramsMap
		* 参数集, 键/值对
		* @return NameValuePair参数集
		*/
		private static List<NameValuePair> getParamsList(Map<String, String> paramsMap) {
			if (paramsMap == null || paramsMap.size() == 0) {
				return null;
			}
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> map : paramsMap.entrySet()) {
				params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
			}
			return params;
		}
		
		/**
		 * HttpClient发送POST请求
		 * @param url          目标地址
		 * @param contentType  入参类型，如：text/json
		 * @param params       入参
		 * @param charset      编码
		 * @param timeout      超时时间
		 * @param heardParms   头部信息
		 * @return 
		 * 
		 * @author milk add on 2017-01-06
		 */
		public static String postNeedContentType(String url, String contentType, Map<String, Object> params, String charset, int timeout, Map<String, String> heardParms) {
			if (url == null || StringUtils.isEmpty(url)) {
				return null;
			}
			// 创建HttpClient实例
			DefaultHttpClient client = getDefaultHttpClient(charset,timeout);
			String paramsStr = JsonUtil.object2json(params);
			HttpPost httpPost = new HttpPost(url);
			StringEntity se;
			try {
				se = new StringEntity(paramsStr);
				se.setContentType(contentType);
				httpPost.setEntity(se);
				if (heardParms!=null&&heardParms.size()>0){
					Iterator<Entry<String, String>> it = heardParms.entrySet().iterator();     
					while(it.hasNext()) {     
					    Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();     
					    Object key = entry.getKey()   ;     
					    Object value = entry.getValue();     
					    httpPost.addHeader(new BasicHeader(key.toString(), value.toString()));
					}  
				}
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			// 发送请求，得到响应
			String responseStr = null;
			try {
				responseStr = client.execute(httpPost, responseHandler);
			} catch (ClientProtocolException e) {
				//throw new NetServiceException("客户端连接协议错误", e);
				e.printStackTrace();
			} catch (IOException e) {
				//throw new NetServiceException("IO操作异常", e);
				e.printStackTrace();
			} finally {
				abortConnection(httpPost, client);
			}		
			return responseStr;
		}
		
		/**
		* 获取DefaultHttpClient实例
		* 
		* @param charset
		* 参数编码集, 可空
		* @return DefaultHttpClient 对象
		*/
		private static DefaultHttpClient getDefaultHttpClient(final String charset,int timeout){
			DefaultHttpClient httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			//模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
			httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			httpclient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
			httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charset == null ? CHARSET_GBK : charset);
			httpclient.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);
			httpclient.setHttpRequestRetryHandler(requestRetryHandler);
			//HttpConnectionParams.set
			return httpclient;
		}
		// 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理
		private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
			// 自定义响应处理
			public String handleResponse(HttpResponse response)	throws ClientProtocolException, IOException {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String charset = EntityUtils.getContentCharSet(entity) == null ? CHARSET_GBK : EntityUtils.getContentCharSet(entity);
					return new String(EntityUtils.toByteArray(entity), charset);
				} else {
					return null;
				}
			}
		};
		
		// 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复
		private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
			// 自定义的恢复策略
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				// 设置恢复策略，在发生异常时候将自动重试3次
				if (executionCount >= 3) {
					// Do not retry if over max retry count
					return false;
				}
				if (exception instanceof NoHttpResponseException) {
					// Retry if the server dropped connection on us
					return true;
				}
				if (exception instanceof SSLHandshakeException) {
					// Do not retry on SSL handshake exception
					return false;
				}
				HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
				boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
				if (!idempotent) {
					// Retry if the request is considered idempotent
					return true;
				}
				return false;
			}
		};
		
		/**
		* 释放HttpClient连接
		* 
		* @param hrb
		* 请求对象
		* @param httpclient
		* 			client对象
		*/
		private static void abortConnection(final HttpRequestBase hrb, final HttpClient httpclient){
			if (hrb != null) {
				hrb.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		/**
		* Post方式提交,URL中不包含提交参数, 格式：http://www.g.cn
		* 
		* @param url
		* 提交地址
		* @param params
		* 提交参数集, 键/值对
		* @param charset
		* 参数提交编码集
		* @return 响应消息
		*/
		public static String doPost(String url, Map<String, String> params, String charset,int timeout,Map<String, String> heardParms) {
			if (url == null || StringUtils.isEmpty(url)) {
				return null;
			}
			// 创建HttpClient实例
			DefaultHttpClient httpclient = getDefaultHttpClient(charset,timeout);
			UrlEncodedFormEntity formEntity = null;
			try {
				if (charset == null || StringUtils.isEmpty(charset)) {
					if (params!=null)
					formEntity = new UrlEncodedFormEntity(getParamsList(params));
				} else {
					if (params!=null)
					formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
				}
			} catch (UnsupportedEncodingException e) {
				//throw new NetServiceException("不支持的编码集", e);
				e.printStackTrace();
			}
			HttpPost hp = new HttpPost(url); 
			hp.setEntity(formEntity);
			
			try
			{
				if (heardParms!=null&&heardParms.size()>0)
				{
					
					 Iterator   it   =   heardParms.entrySet().iterator()   ;     
					  while   (it.hasNext())     
					  {     
						  Map.Entry   entry   =   (Map.Entry)   it.next()   ;     
						  Object   key   =   entry.getKey()   ;     
						  Object   value   =   entry.getValue()   ;     
						  hp.addHeader(new BasicHeader(key.toString(), value.toString()));
					  }   
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			// 发送请求，得到响应
			String responseStr = null;
			try {
				responseStr = httpclient.execute(hp, responseHandler);
			} catch (ClientProtocolException e) {
				//throw new NetServiceException("客户端连接协议错误", e);
				e.printStackTrace();
			} catch (IOException e) {
				//throw new NetServiceException("IO操作异常", e);
				e.printStackTrace();
			} finally {
				abortConnection(hp, httpclient);
			}		
			return responseStr;
		}
}
