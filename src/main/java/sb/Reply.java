package sb;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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
		case "emotion":
			if(command.getCommandArgument()!=null){
				String str=command.getCommandArgument();
				String source=Requests.get("http://nlp.qq.com/semantic.cgi").text().getBody();
				Matcher m=Pattern.compile("/public/wenzhi/js/semantic(.*?)\\.js").matcher(source);
				m.find();
				String jsURL="http://nlp.qq.com"+m.group();
				source=Requests.get(jsURL).text().getBody();
				m=Pattern.compile("/public/wenzhi/api/common_api(\\d+?)\\.php").matcher(source);
				m.find();
				String apiURL="http://nlp.qq.com"+m.group();
				
				HashMap<String,String> post=new HashMap<String, String>();
				post.put("api", "12");
				post.put("body_data","{\"content\":\""+str+"\"}");
				
				HashMap<String, String> header=new HashMap<String,String>();
				header.put("Referer", "http://nlp.qq.com/semantic.cgi");
				header.put("Cookie","pgv_pvi="+new Random().nextInt()+";");
				header.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
				
				String json=Requests.post(apiURL)
				.headers(header)
				.forms(post).text().getBody();
				
				HashMap<String,String> result=JSON.parseObject(json,new TypeReference<HashMap<String, String>>(){});
				int negative=(int)(Double.parseDouble((String) result.get("negative"))*100);
				int positive=(int)(Double.parseDouble((String) result.get("positive"))*100);
				
				sendMessage(sendderId, "分析“"+str+"”这句话的结果：\r\n积极度："+positive+"%\r\n消极度："+negative+"%");
			}
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
