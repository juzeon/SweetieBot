function onMessage(obj){
    var haStr=new Array("蛤","蛙","华莱士","长者","续","+1","-1","江","膜","大新闻","负责任","负泽任","钦定","董先生","苟","蟆","虫合","虵","祂","钦点","吼","可奉告","naïve","naive","simple","图样","闷声发大财","批判","基本法","赛艇","excit","谈笑风生","人生经验","念了两句诗","念诗","黑框眼镜","1926","香港记者","西方记者");
    for(var i=0;i<haStr.length;i++){
        if(obj.content.indexOf(haStr[i])!=-1){
            obj.send("-1s");
            break;
        }
    }
    
}