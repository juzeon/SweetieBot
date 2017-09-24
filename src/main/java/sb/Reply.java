package sb;

import java.io.UnsupportedEncodingException;

import com.scienjus.smartqq.client.SmartQQClient;

import net.dongliu.requests.Requests;

public class Reply {
	Command command;
	SmartQQClient sb;
	long userId;
	long sendderId;
	String myContent;
	int type;
	Loadhe loadhe;
	public String getMyContent() {
		
		return myContent;
	}
	public Reply(Command command,SmartQQClient sb,Loadhe loadhe){
		this.command=command;
		this.sb=sb;
		this.type=command.getType();
		this.userId=command.getUserId();
		this.loadhe=loadhe;
		switch (type) {
		case 1:
			this.sendderId=command.getUserId();
			break;
		case 2:
			this.sendderId=command.getGroupId();
			break;
		case 3:
			this.sendderId=command.getDiscussId();
			break;



		default:
			break;
		}
		loadhe.eval(Reply.this);
		/*this.commandContent=command.getCommandContent();
		
		if(commandContent!=null){
			if(commandContent.indexOf(" ")>-1){
				parsedCommand=commandContent.split(" ");
			}else{
				parsedCommand=new String[1];
				parsedCommand[0]=commandContent;
			}
			this.myLastContent=myLastContent;
		}*/
	}
	void eval(){
		
		switch(command.getCommandKey()){
		case "say":
			/*if(parsedCommand.length>=2){
				if((!parsedCommand[1].equals(""))&&parsedCommand[1]!=null){
					sendMessage(sendderId, parsedCommand[1]);
					myContent=parsedCommand[1];
				}
			}*/
			if(command.getCommandArgument()!=null){
				sendMessage(sendderId, command.getCommandArgument());
				myContent=command.getCommandArgument();
			}
			break;
		case "short":
			/*if(parsedCommand.length>=2){
				if((!parsedCommand[1].equals(""))&&parsedCommand[1]!=null){
					String shorturl=Requests.get("http://www.xiaoxiangzi.com/tool/dwz.asp?domain="+parsedCommand[1]).text().getBody();
					
					sendMessage(sendderId,"缩短后的网址：" +shorturl);
					myContent=shorturl;
				}
			}*/
			if(command.getCommandArgument()!=null){
				String shorturl=Requests.get("http://www.xiaoxiangzi.com/tool/dwz.asp?domain="+command.getCommandArgument()).text().getBody();
			
				sendMessage(sendderId,"缩短后的网址：" +shorturl);
				myContent=shorturl;
			}
			break;
		case "baidu":
			/*if(parsedCommand.length>=2){
				if((!parsedCommand[1].equals(""))&&parsedCommand[1]!=null){
					String url="";
					try {
						url=java.net.URLEncoder.encode("https://lmbtfy.cn/?"+parsedCommand[1],"utf-8")+"%00%00";
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					String shorturl=Requests.get("http://www.xiaoxiangzi.com/tool/dwz.asp?domain="+url).text().getBody();
					
					sendMessage(sendderId, "参见："+shorturl);
					myContent=shorturl;
				}
			}*/
			if(command.getCommandArgument()!=null){
				String url="";
				try {
					url=java.net.URLEncoder.encode("https://lmbtfy.cn/?"+command.getCommandArgument(),"utf-8")+"%00%00";
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				String shorturl=Requests.get("http://www.xiaoxiangzi.com/tool/dwz.asp?domain="+url).text().getBody();
				
				sendMessage(sendderId, "参见："+shorturl);
				myContent=shorturl;
			}
			break;
		case "load":
			switch(command.getCommandArgument()){
				case "script":
					sendMessage(sendderId,loadhe.loadScript());
					break;
				case "group":
					sendMessage(sendderId,loadhe.loadGroup());
					break;
			}
			break;
		}
		
	}
	void sendMessage(long id,String content){
		switch (type) {
		case 1:
			sb.sendMessageToFriend(id, content+"\t");
			break;
		case 2:
			sb.sendMessageToGroup(id, content+"\t");
			break;
		case 3:
			sb.sendMessageToDiscuss(id, content+"\t");
			break;

		default:
			break;
		}
	}
}
