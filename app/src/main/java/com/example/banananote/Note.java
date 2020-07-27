package com.example.banananote;

public class Note {
    //생성자
    String Title;
    String CreateDate;
    String Memo;
    boolean isSelected; //checkbox

    //String 그림

    /*public Note(String Title, String CreateDate, String Memo) { //, boolean isSelected
        this.Title = Title;
        this.CreateDate = CreateDate;
        this.Memo = Memo;
        //this.isSelected = isSelected;
    }*/

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
