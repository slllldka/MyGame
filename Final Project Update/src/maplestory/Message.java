package maplestory;

import java.util.LinkedList;

public class Message {
	//All, Friend, Guild, Alliance, Combat
	private String type;
	private String msg;
	private long time;
	private int width;
	
	protected static int max_messages = 30;
	protected static LinkedList<Message> combat_messages = new LinkedList<Message>();
	
	public Message(String _type, String _msg, long _time, int _width) {
		type = _type;
		msg = _msg;
		time = _time;
		width = _width;
	}
	
	public String getType() {
		return type;
	}

	public String getMsg() {
		return msg;
	}

	public long getTime() {
		return time;
	}
	public int getWidth() {
		return width;
	}


	public static void addCombatMsg(Message _msg) {
		combat_messages.add(_msg);
	}
}
