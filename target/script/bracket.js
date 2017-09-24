function onMessage(obj){
    var content=obj.content.split("");
    var num=0;
    for(var i=0;i<content.length;i++){
        if(content[i]=="("||content[i]=="（"){
            num++;
        }else if(content[i]==")"||content[i]=="）"){
            if(num>=1){
                num--;
            }
        }
    }
    var re="";
    for(var i=0;i<num;i++){
        re+="）";
    }
    if(re!=""){
        obj.send(re);
    }
}