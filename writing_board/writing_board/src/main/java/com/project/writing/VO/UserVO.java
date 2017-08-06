package com.project.writing.VO;

public class UserVO {
    private int idx;         // 회원수 관리
    private String id;       // 아이디
    private String password; // 비밀번호
    private String nickname; // 닉네임


    // 해당되는 setter를 통해 값을 받고 , ArrayList를 사용하여 값을 출력한다.
    public int getIdx()
    {
        return idx;
    }
    public void setIdx(int idx) {
        this.idx = idx;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
