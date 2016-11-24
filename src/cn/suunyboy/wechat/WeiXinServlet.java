package cn.suunyboy.wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import cn.suunyboy.wechat.resp.TextMessage;
import cn.suunyboy.wechat.util.MessageUtils;
import cn.suunyboy.wechat.util.ShalUtil2;

/**
 * @Title WeiXinServlet
 * @Description cn.suunyboy.wechat
 * @author HU GUANG
 * @email 13977300942@163.com
 * @version 1.0
 * @time 2016-11-11 上午1:08:40
 */
public class WeiXinServlet extends HttpServlet {

	private static final long serialVersionUID = -1893132076757874365L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter printWriter = resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");
		if(ShalUtil2.checkSignature(signature, timestamp, nonce)){
			printWriter.write(echostr);
		}
	}     
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		req.setCharacterEncoding("utf-8");
		//resp.setCharacterEncoding("iso8859-1");
		try {
			Map<String, String> map = MessageUtils.xml2Map(req);
			//获取ToUserName
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			System.out.println("ToUserName:"+toUserName+",FromUserName:"+fromUserName+"MsgType:"+msgType);
			
			String messageXml = null;
			if(MessageUtils.RESP_MESSAGE_TYPE_TEXT.equals(msgType)){
				// 默认回复此文本消息/返回
				TextMessage textMessage = new TextMessage();
				textMessage.setToUserName(fromUserName);
				textMessage.setFromUserName(toUserName);
				textMessage.setCreateTime(System.currentTimeMillis());
				textMessage.setMsgType(msgType);
				textMessage.setContent("您发送的消息是:"+content);
				//textMessage.setMsgId(0);
				//转为xml在浏览器输出
				messageXml = MessageUtils.message2Xml(textMessage);
				messageXml = new String(messageXml.getBytes("utf-8"), "iso8859-1");
			}
			printWriter.write(messageXml);
			System.out.println("您返回的信息为:");
			messageXml = new String(messageXml.getBytes("iso8859-1"), "utf-8");
			System.out.println(messageXml);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			if(printWriter!=null)
				printWriter.close();
		}
	}
}
