package com.cyz.ob.common.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyz.basic.util.StrUtil;

@ServerEndpoint("/qrcode/login")
@Component
public class QrCodeLoginService {
	
	static Logger log=LoggerFactory.getLogger(QrCodeLoginService.class);

	private static AtomicInteger onlineCount  = new AtomicInteger(0);
	
	/**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,QrCodeLoginService> webSocketMap = new ConcurrentHashMap<>();
    
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;

        if(webSocketMap.containsKey(session.getId())){
            webSocketMap.remove(session.getId());
            webSocketMap.put(session.getId(),this);
            //加入set中
        }else{
            webSocketMap.put(session.getId(),this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        //log.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());

        try {
        	Map<String, String> datas = new HashMap<>();
        	datas.put("type", "1");
        	datas.put("data", session.getId());
            sendMessage(datas);
        } catch (IOException e) {
            log.error("用户:"+session.getId()+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
    	System.out.println(this.session == null);
    	if (this.session != null) {
    		System.out.println(this.session.getId());
    	}
		/*
		 * if(webSocketMap.containsKey(userId)){ webSocketMap.remove(userId); //从set中删除
		 * subOnlineCount(); } log.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
		 */
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:"+ session.getId() +",报文:"+message);
        //可以群发消息
        //消息保存到数据库、redis
		/*
		 * if(StrUtil.isNotEmpty(message)){ try { //解析发送的报文 JSONObject jsonObject =
		 * JSON.parseObject(message); //追加发送人(防止串改)
		 * //jsonObject.put("fromUserId",this.userId); //String
		 * toUserId=jsonObject.getString("toUserId"); //传送给对应toUserId用户的websocket
		 * if(StrUtil.isNotEmpty(toUserId)&&webSocketMap.containsKey(toUserId)){
		 * webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString()); }else{
		 * log.error("请求的userId:"+toUserId+"不在该服务器上"); //否则不在这个服务器上，发送到mysql或者redis }
		 * }catch (Exception e){ e.printStackTrace(); } }
		 */
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        //log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(Object obj) throws IOException {

        this.session.getBasicRemote().sendText(JSONObject.toJSONString(obj));
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(Object data,@PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:" + userId + "，报文:" + data);
        if(StrUtil.isNotEmpty(userId) && webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(data);
        }else{
            log.error("用户" + userId + ",不在线！");
        }
    }

    public static int getOnlineCount() {
        return onlineCount.get();
    }

    public static void addOnlineCount() {
    	QrCodeLoginService.onlineCount.incrementAndGet();
    }

    public static void subOnlineCount() {
    	QrCodeLoginService.onlineCount.decrementAndGet();
    }

}
