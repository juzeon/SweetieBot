function onMessage(obj){
    var reg1 = new RegExp("\\(", "g");
    var reg2 = new RegExp("（", "g");
    var reg3 = new RegExp("\\)", "g");
    var reg4 = new RegExp("）", "g");
    var b1,b2,b3,b4;
    if(obj.content.match(reg1)==null){
        b1=0;
    }else{
        b1=obj.content.match(reg1).length;
    }
    if(obj.content.match(reg2)==null){
        b2=0;
    }else{
        b2=obj.content.match(reg2).length;
    }
    if(obj.content.match(reg3)==null){
        b3=0;
    }else{
        b3=obj.content.match(reg3).length;
    }
    if(obj.content.match(reg4)==null){
        b4=0;
    }else{
        b4=obj.content.match(reg4).length;
    }
    var printNum=b1+b2-b3-b4;
    var re="";
    for(var i=0;i<printNum;i++){
        re+="）";
    }
    if(re!=""){
        obj.send(re);
    }
}