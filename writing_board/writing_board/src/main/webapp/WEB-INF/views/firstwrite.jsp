<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header/header.jsp" %>
<%@ page session="false" %>
<%--글 작성하는 페이지--%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta name="apple-mobile-web-app-capable" content="yes"/>
        <meta name="mobile-web-app-capable" content="yes"/>
        <title>글쓰기 확인</title>
        <link rel="stylesheet" href="/resources/app/stat/writing.css"/>
        <link rel="stylesheet" href="/resources/app/stat/layout.css"/>
        <script src="/resources/app/javascript/write.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    </head>
    <body>
        <header>
            <div class="header-layout">

                <h1 class="header__title"><a href="/logout" title="홈으로">LOG-OUT</a></h1>

                <nav class="header__tabs">
                    <a class="item" href="/firstwrite">
                        <svg class="item-icon" height="24" viewBox="0 0 24 24" width="24">
                            <path d="M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z"/>
                            <path d="M0 0h24v24H0z" fill="none"/>
                        </svg>
                        <span class="item-span">글쓰기</span>
                    </a>

                    <a class="item" href="/library">
                        <svg class="item-icon" height="24" viewBox="0 0 24 24" width="24">
                            <path d="M0 0h24v24H0z" fill="none"/>
                            <path d="M4 6H2v14c0 1.1.9 2 2 2h14v-2H4V6zm16-4H8c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2zm-1 9H9V9h10v2zm-4 4H9v-2h6v2zm4-8H9V5h10v2z"/>
                        </svg>
                        <span class="item-span">라이브러리</span>
                    </a>


                    <a class="item" href="/newsfeed">
                        <svg class="item-icon" height="24" viewBox="0 0 24 24" width="24">
                            <path d="M12 10.9c-.61 0-1.1.49-1.1 1.1s.49 1.1 1.1 1.1c.61 0 1.1-.49 1.1-1.1s-.49-1.1-1.1-1.1zM12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm2.19 12.19L6 18l3.81-8.19L18 6l-3.81 8.19z"/>
                            <path d="M0 0h24v24H0z" fill="none"/>
                        </svg>
                        <span class="item-span">뉴스피드</span>
                    </a>
                    <span class="item-span">${author}</span>
                </nav>

            </div>
        </header>
        <!-- 에디터 영역 시작 -->
        <main id="js--body" class="body-layout card-list">
        <div class="editor nocard">
            <div class="editor-layout card">
                <textarea id="js--em-editor" maxlength="700"></textarea>
            </div>
            <div class="nocard">
                <div class="editor-action card">
                    <a class="item" href="javascript:first_submit($('#js--em-editor').val())">작성완료</a>
                </div>
            </div>
        </div>
        <script src="/resources/app/stat/writing.js"></script>
    </body>
</html>
