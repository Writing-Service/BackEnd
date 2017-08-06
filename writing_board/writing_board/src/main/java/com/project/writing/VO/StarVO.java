package com.project.writing.VO;

public class StarVO {

    private String star_idx; // 글 별 갯수 기본번호
    private String board_idx ; // 글번호
    private String writenumber; // 글 번호 중에 몇 번
    private String rate; // 글 Vote (0 - false / 1 - true)

    public String getStar_idx() {
        return star_idx;
    }
    public void setStar_idx(String star_idx) {
        this.star_idx = star_idx;
    }
    public String getBoard_idx() {
        return board_idx;
    }
    public void setBoard_idx(String board_idx) {
        this.board_idx = board_idx;
    }
    public String getWritenumber() {
        return writenumber;
    }
    public void setWritenumber(String writenumber) {
        this.writenumber = writenumber;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }

}
