package project.project;

public class feedbackClass {
    private String feedback;
    private int userID;
    private int eventID;

    public feedbackClass(){

    }
    public feedbackClass(int u,int e,String f){
        userID=u;
        eventID=e;
        feedback=f;
    }
    public int getUserID(){
        return userID;
    }
    public void setUserID(int user ){
        this.userID=user;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
