package sb;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.script.*;

import com.scienjus.smartqq.callback.MessageCallback;
import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.Category;
import com.scienjus.smartqq.model.DiscussMessage;
import com.scienjus.smartqq.model.Friend;
import com.scienjus.smartqq.model.Group;
import com.scienjus.smartqq.model.GroupMessage;
import com.scienjus.smartqq.model.Message;

public class Controller {
	SmartQQClient sb;
	Random random;
	String myLastContent="";
	Loadhe loadhe;
	void dealGroup(GroupMessage message){
		/*long gid=message.getGroupId();
		String content=message.getContent();
		if(myMessage.equals(content)){
			System.out.println("sb回复成功："+content);
			return;
		}
		System.out.println(gid+" "+content);
		if(content.startsWith("sb#")){
			sb.sendMessageToGroup(gid,content);//change this
			myMessage=content;//change content
		}*/
		
	}
	void dealCommand(Command command){
		int type=command.getType();
		System.out.println("UserId:"+command.getUserId()+" 类型："+type+" 内容："+command.getSourceContent());
		/*if(sb.getQQById(command.getUserId())==new Long("3214508785")){
			System.out.println(myLastContent+" 是sb的回复");
			return;
		}*/
		if(command.getSourceContent().endsWith("\t")){
			System.out.println(command.getSourceContent()+" 是sb的回复");
			return;
		}
		switch (type) {
		case 1://私聊
			myLastContent=new PersonReply(command, sb,loadhe).getMyContent();
			break;
		case 2://群聊
			for(int i=0;i<loadhe.lovedGroups.size();i++){
				if(loadhe.lovedGroups.get(i).getId()==command.getGroupId()){
					myLastContent=new GroupReply(command, sb,loadhe).getMyContent();
					break;
				}
			}
			
		default:
			break;
		}
	}
	public Controller(){
		random=new Random();
		
		sb=new SmartQQClient(new MessageCallback() {
			
			@Override
			public void onMessage(final Message message) {
				// TODO 自动生成的方法存根
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(random.nextInt(1000));
						} catch (InterruptedException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						Command command=new Command(1);
						command.setUserId(message.getUserId());
						command.setSourceContent(message.getContent());
						dealCommand(command);
					}
				}).start();
			}
			
			@Override
			public void onGroupMessage(final GroupMessage message) {
				// TODO 自动生成的方法存根
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(random.nextInt(1000));
						} catch (InterruptedException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						Command command=new Command(2);
						command.setGroupId(message.getGroupId());
						command.setUserId(message.getUserId());
						command.setSourceContent(message.getContent());
						dealCommand(command);
					}
				}).start();
			}
			
			@Override
			public void onDiscussMessage(final DiscussMessage message) {
				// TODO 自动生成的方法存根
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(random.nextInt(1000));
						} catch (InterruptedException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						Command command=new Command(3);
						command.setDiscussId(message.getDiscussId());
						command.setUserId(message.getUserId());
						command.setSourceContent(message.getContent());
						dealCommand(command);
					}
				}).start();
			}
		});
		loadhe=new Loadhe(sb);
        //使用后调用close方法关闭，你也可以使用try-with-resource创建该对象并自动关闭
        try {
        	Scanner scan=new Scanner(System.in);
        	Command command=new Command(4);
        	command.setCommandContent(scan.nextLine());
            sb.close();//change this
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
