package sb;

import java.io.IOException;

import com.scienjus.smartqq.client.SmartQQClient;

public class GroupReply extends Reply{
	long groupId;
	public GroupReply(Command command, SmartQQClient sb,Loadhe loadhe) {
		super(command, sb,loadhe);
		// TODO 自动生成的构造函数存根
		this.groupId=command.getGroupId();
		if(command.getCommandContent()!=null){
			eval();
		}
	}
	void eval(){
		super.eval();
		switch(command.getCommandKey()){
		
		}
	}
}
