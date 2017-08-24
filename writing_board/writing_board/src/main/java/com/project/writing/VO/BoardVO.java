package com.project.writing.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


// 작성 중인 글

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {

    private String idx; // 글 고유번호
    private String author ; // 새로운 글 작성자
    private String author_id; // 새로운 글 작성자 ID
    private Date datatime; // 글 데이터 작성날짜
    private String vote; // 글 투표 횟수
    private String content; // 글 내용
    private String before_author_id ; // 그 그 전글 작가 아이디
    private String pre_author_id ; // 그 전글 작가 아이디
    private String author_save; // 임시저장
    private String write_number; // 글 작성 갯수 ( 7개시 막음 )
    private String board_number; // 묶음글 번호
    private String board_bool; // 받아온 글 작성자 ( 2 - 제출완료 / 1 - 작성완료 상태 / 0 - 미작성 or 임시저장 )
    private String board_catcher; // 글 가져온 사람 ( 글감용 )
}
