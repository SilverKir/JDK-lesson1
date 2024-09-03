package src.server;

import src.view.GetData;
import src.view.SetData;

import java.util.List;

public class MessageLog {
    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }
    public List<String> getMessageList() {
        return messageList;
    }

    private List<String> messageList;

    public void DownloadMessageLog() {
        messageList = GetData.getData(ServerConstants.LOG_PATH);
    }
    public void  SaveMessageLog(){
        SetData.setData(messageList,ServerConstants.LOG_PATH);
    }
}
