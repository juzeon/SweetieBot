package sb;

public class Command {
	private long groupId,userId,discussId;
	public long getDiscussId() {
		return discussId;
	}
	public void setDiscussId(long discussId) {
		this.discussId = discussId;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getSourceContent() {
		return sourceContent;
	}
	public void setSourceContent(String sourceContent) {
		this.sourceContent = sourceContent;
		if(sourceContent.startsWith("sb#")){
			String commandContentTemp=sourceContent.substring(3);
			if(commandContentTemp==null||commandContentTemp.equals("")){
				
			}else{
				setCommandContent(commandContentTemp);
			}
		}
		
	}
	public String getCommandContent() {
		return commandContent;
	}
	private String commandKey,commandArgument;
	public String getCommandKey() {
		return commandKey;
	}
	public void setCommandKey(String commandKey) {
		this.commandKey = commandKey;
	}
	public String getCommandArgument() {
		return commandArgument;
	}
	public void setCommandArgument(String commandArgument) {
		this.commandArgument = commandArgument;
	}
	public void setCommandContent(String commandContent) {
		this.commandContent = commandContent;
		if(commandContent!=null){
			int sub=commandContent.indexOf(" ");
			if(sub<=0){
				commandKey=commandContent;
			}else{
				commandKey=commandContent.substring(0,sub);
				if(commandContent.length()>=sub+1){
					commandArgument=commandContent.substring(sub+1);
				}
				if(commandArgument.equals("")){
					commandArgument=null;
				}
			}
		}
	}
	private String sourceContent,commandContent;
	private int type;//1=私聊；2=群聊；3=讨论组；4=控制台
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Command(int type){
		this.type=type;
	}
}
