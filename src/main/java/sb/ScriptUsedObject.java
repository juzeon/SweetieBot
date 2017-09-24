package sb;

import com.scienjus.smartqq.client.SmartQQClient;

public class ScriptUsedObject {
	public long groupId,userId,discussId;
	public int type;
	public String command,content,commandKey,commandArgument;
	private SmartQQClient sb;
	private long sendderId;
	Reply reply;
	protected ScriptUsedObject(Reply reply){
		this.reply=reply;
		this.sb=reply.sb;
		this.type=reply.command.getType();
		this.discussId=reply.command.getDiscussId();
		this.userId=reply.command.getUserId();
		this.groupId=reply.command.getGroupId();
		this.command=reply.command.getCommandContent();
		this.content=reply.command.getSourceContent();
		this.commandKey=reply.command.getCommandKey();
		this.commandArgument=reply.command.getCommandArgument();
		this.sendderId=reply.sendderId;
		/*switch (reply.command.getType()) {
		case 1:
			this.sendderId=reply.command.getUserId();
			break;
		case 2:
			this.sendderId=reply.command.getGroupId();
			break;
		case 3:
			this.sendderId=reply.command.getDiscussId();
			break;
		default:
			break;
		}*/
	}
	public void send(String content){
		/*switch (type) {
		case 1:
			sb.sendMessageToFriend(sendderId, content);
			break;
		case 2:
			sb.sendMessageToGroup(sendderId, content);
			break;
		case 3:
			sb.sendMessageToDiscuss(sendderId, content);
			break;

		default:
			break;
		}
		reply.myContent=content;*/
		reply.sendMessage(sendderId, content);
	}
}
