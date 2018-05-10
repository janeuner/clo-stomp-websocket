package us.neuner.clo.client;

import javax.print.attribute.DateTimeSyntax;

public class ChatEntry {
private String playerName;
private String msg;
private DateTimeSyntax time;

public String getPlayerName(){
	return this.playerName;
}
public String getMsg(){
	return this.msg;
}
public DateTimeSyntax getTime(){
	return this.time;
}
}
