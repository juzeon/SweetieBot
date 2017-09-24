package sb;

import java.io.IOException;

import com.scienjus.smartqq.client.SmartQQClient;

public class PersonReply extends Reply{
	public PersonReply(Command command, SmartQQClient sb,Loadhe loadhe) {
		super(command, sb,loadhe);
		// TODO 自动生成的构造函数存根
		if(command.getCommandContent()!=null){
			eval();
		}
	}
	void eval(){
		super.eval();
		switch(command.getCommandKey()){
		case "close":
			sendMessage(userId,"Sweetie Bot System 关闭指令已下达");
			try {
				sb.close();
				
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			System.exit(0);
			break;
		
		}
	}
}
