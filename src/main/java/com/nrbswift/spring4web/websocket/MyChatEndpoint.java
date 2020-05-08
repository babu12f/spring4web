package com.nrbswift.spring4web.websocket;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@ServerEndpoint("/chatendpoint")
public class MyChatEndpoint {

    static Set<Session> chatRoomUsers = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        chatRoomUsers.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        String userName = (String) session.getUserProperties().get("username");
        if (userName == null) {
            session.getUserProperties().put("username", message);
            session.getBasicRemote().sendText(buildJsonData("system", "you are connected as " + message));
        }
        else {
            Iterator<Session> iterator = chatRoomUsers.iterator();
            while (iterator.hasNext()) {
                iterator.next().getBasicRemote().sendText(buildJsonData(userName, message));
            }
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        chatRoomUsers.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable thr) {
    }

    private String buildJsonData(String username, String msg) {
        JsonObject jsonObject = Json.createObjectBuilder().add("message", username + " : "+msg).build();

//        StringWriter stringWriter = new StringWriter();
//
//        try {
//            JsonWriter jsonWriter = Json.createWriter(stringWriter);
//            jsonWriter.write(jsonObject);
//            return stringWriter.toString();
//        }
//        catch (Exception e) {
//            System.out.println("Exception +" +e.getMessage());
//        }
//        return stringWriter.toString();
        return jsonObject.toString();
    }
}
