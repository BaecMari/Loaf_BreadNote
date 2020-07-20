package com.example.banananote;

public class Note {
    //생성자
    String Title;
    String CreateDate;
    String Memo;
    //String 그림

    public Note(String Title, String CreateDate, String Memo) {
        this.Title = Title;
        this.CreateDate = CreateDate;
        this.Memo = Memo;
    }

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
}
