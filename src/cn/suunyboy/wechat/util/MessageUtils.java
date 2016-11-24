package cn.suunyboy.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.suunyboy.wechat.resp.TextMessage;

import com.thoughtworks.xstream.XStream;

/**
 * 消息工具类
 * @author Administrator
 *
 */
public class MessageUtils {
	
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	
	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	
	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	
	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_NEWS = "text";
	
	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	
	/**
	 * 请求消息类型：连接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	
	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	
	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	
	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	
	/**
	 * 事件类型：subscribe订阅
	 */
	public static final String EVENT_MESSAGE_TYPE_SUBSCRIBE = "subscribe";
	
	/**
	 * 事件类型：unsubscribe取消订阅
	 */
	public static final String EVENT_MESSAGE_TYPE_UNSUBSCRIBE = "unsubscribe";
	
	/**
	 * 事件类型：自定义菜单点击事件CLICK
	 */
	public static final String EVENT_TYPE_CLICK = "click";
	
	/**
	 * 解析微信发来的请求		
	 * @param request
	 * @return 将其转为map格式
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static Map<String, String> xml2Map(HttpServletRequest request) throws IOException, DocumentException{
		//解析消息转为map格式
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		//使用sax解析
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(inputStream);
		//得到根节点
		Element elementRoot = document.getRootElement();
		//根据根节点得到所有的子节点
		List<Element> elementList = elementRoot.elements();
		for(Element element : elementList){
			map.put(element.getName(), element.getText());
		}
		//释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}
	
	/**
	 * 文本消息对象转换成xml
	 * @param textMessage
	 * @return
	 */
	public static String message2Xml(TextMessage textMessage){
		XStream xStream = new XStream();
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}
}
