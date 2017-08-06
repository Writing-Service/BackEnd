package com.project.writing.VO;

import java.util.Date;

public class BoardVO {

    private String idx; // 글 고유번호
    private String author ; // 글 작성자
    private Date datatime; // 글 데이터 작성날짜
    private String vote; // 글 투표 횟수
    private String writenumber; // 글 작성 갯수 ( 7개시 막음 )
    private String content; // 글 내용
    private String author_id; // 작가 아이디

    public String getIdx() {
        return idx;
    }
    public void setIdx(String idx) {
        this.idx = idx;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Date getDatatime() {
        return datatime;
    }
    public void setDatatime(Date datatime) {
        this.datatime = datatime;
    }
    public String getVote() {
        return vote;
    }
    public void setVote(String vote) {
        this.vote = vote;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getWritenumber() {
        return writenumber;
    }
    public void setWritenumber(String writenumber) {
        this.writenumber = writenumber;
    }
    public String getAuthor_id() {
        return author_id;
    }
    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

}
