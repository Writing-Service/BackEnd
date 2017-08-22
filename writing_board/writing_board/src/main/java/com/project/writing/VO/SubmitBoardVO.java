package com.project.writing.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 제출 완료된 글
public class SubmitBoardVO {
    private String idx; // 글 고유번호
    private String author ; // 글 작성자
    private Date datatime; // 글 데이터 작성날짜
    private String vote; // 글 투표 횟수
    private String content; // 글 내용
    private String author_id; // 작가 아이디
    private String write_number; // 글 작성 갯수 ( 순서 파악 )
    private String board_number; // 묶음글 번호
    private String star; // 글 START Vote (0 - false / 1 - true)

}
