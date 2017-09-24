package sb;

import java.io.*;
import java.io.*;
import java.util.*;

import javax.script.*;

import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.Group;

public class Loadhe {
	List<String> scripts;
	List<Group> lovedGroups;
	ScriptUsedObject obj;
	ScriptEngineManager manager;
	ScriptEngine engine;
	SmartQQClient sb;
	public Loadhe(SmartQQClient sb){
		this.sb=sb;
		loadScript();
		loadGroup();
	}
	String loadScript(){
		scripts=new ArrayList<String>();
		FileUtils.mkdir("script");
		String result="";
		try {
			List<File> scriptFileList=FileUtils.listFiles("script");
			for(File scriptFile:scriptFileList){
				scripts.add(FileUtils.readFile(scriptFile.getPath()));
				result+="Loadhe: Loaded script "+scriptFile.getName()+"\r\n";
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			result+=e.getMessage()+"\r\n";
		}
		result+="Loadhe: "+scripts.size()+" scripts loaded.";
		System.out.println(result);
		return result;
	}
	String loadGroup(){
		String result="";
		File groupsFile=new File("groups.txt");
		List<String> rdGroups=new ArrayList<String>();
		lovedGroups=new ArrayList<Group>();
		if(groupsFile.exists()){
			try {
				//FileUtils.readFile(groupsFile.getPath());
				FileReader reader = new FileReader(groupsFile.getPath());
	            BufferedReader br = new BufferedReader(reader);
	            String str = null;
	            while((str = br.readLine()) != null) {
	            	if(!str.equals("")){
	            		rdGroups.add(str);
	            	}
	            }
	            br.close();
	            reader.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		List<Group> totalGroups = sb.getGroupList();
        for (Group group : totalGroups) {
            for(int i=0;i<rdGroups.size();i++){
            	if(group.getName().indexOf(rdGroups.get(i))!=-1){
            		lovedGroups.add(group);
            		result+="Loadhe: Loaded group named "+group.getName()+" id "+group.getId()+"\r\n";
            		break;
            	}
            }
        }
        result+="Loadhe: "+lovedGroups.size()+" groups loaded.";
        System.out.println(result);
        return result;
	}
	void eval(Reply reply){
		obj=new ScriptUsedObject(reply);
		for(String script:scripts){
			manager = new ScriptEngineManager();  
	        engine = manager.getEngineByExtension("js"); 
			//engine.put("obj",obj);
			try {
				engine.eval(script);
				Invocable inv = (Invocable) engine;
				inv.invokeFunction("onMessage",obj);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
	}
}
