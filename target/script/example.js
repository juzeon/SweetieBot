function onMessage(obj){
    if(obj.command=="testscript"){
        obj.send("I'm working");
    }
}