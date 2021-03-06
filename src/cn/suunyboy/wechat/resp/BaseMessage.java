package cn.suunyboy.wechat.resp;
/**
 * BaseMessage
 * cn.suunyboy.wechat.resp
 * author:HUGUANG
 * version:v1.0
 * time:2016-11-24 下午4:44:16
 * email:940728678@qq.com
 */
public class BaseMessage {

	//接收方账号
	private String ToUserName;
	//开发者微信号
	private String FromUserName;
	//消息创建时间
	private long CreateTime;
	//消息类型
	private String MsgType;
	//位0x0001被标志时，星标刚收到的消息
	//private int MsgId;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		this.ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
//	public int getMsgId() {
//		return MsgId;
//	}
//	public void setMsgId(int msgId) {
//		MsgId = msgId;
//	}

}
