<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header/header.jsp" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="mobile-web-app-capable" content="yes"/>
    <title>{{page_title}}</title>
    <link rel="stylesheet" href="/resources/app/stat/layout.css"/>
    <script src="/resources/app/stat/library.js"></script>
    <link rel="stylesheet" href="/resources/app/stat/thread-card.css"/>
    <title>Title</title>
</head>
<body>

    <script>
        function a() {
        var a = window.innerWidth;
        if (a <= 360) {
        document.body.className = 'xs';
        } else if (a <= 600) {
        document.body.className = 's';
        } else {
        document.body.className = 'm';
        }
        }
        a();
        window.addEventListener('resize', a);
    </script>

    <header>
        <div class="header-layout">

            <h1 class="header__title"><a href="/" title="홈으로">Writing-Service</a></h1>

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

            </nav>

            <div class="header__user">
                <input id="user__toggle" type="checkbox" class="user__toggle hidden">

                <label class="user__toggle-label" for="user__toggle">
                    <span class="user__name">${author}</span>
                    <svg class="user__name-arrow" height="24" viewBox="0 0 24 24" width="24">
                        <path d="M7 10l5 5 5-5z"/>
                        <path d="M0 0h24v24H0z" fill="none"/>
                    </svg>
                </label>

            </div>
        </div>
    </header>

    <main id="js--body" class="body-layout card-list">
        <div class="flat-button-list nocard">
            <button class="flat-button">Hot</button>
            <button class="flat-button">New</button>
        </div>

        <div class="card-list">

            {{#articles}}

            <div id="{{id}}" class="js--card thread-card card">
                <div class="primary-header">
                    <h2 class="js--tc-author primary-text">{{author}}</h2>
                    <time class="js--tc-date sub-title">{{date}}</time>
                </div>

                <div class="js--tc-content sub-text">{{desc}}</div>
                <div class="js--tc-content-helper sub-text hidden"></div>

                <div class="action">
                    <div class="star-layout">
                        {{#star}}
                        <svg class="star__svg" act="{{act}}" rate="{{rate}}" height="24" viewBox="0 0 24 24" width="24">
                            <path class="star--true" d="M12 17.27L18.18 21l-1.64-7.03L22 9.24l-7.19-.61L12 2 9.19 8.63 2 9.24l5.46 4.73L5.82 21z"/>
                            <path class="star--false" d="M22 9.24l-7.19-.62L12 2 9.19 8.63 2 9.24l5.46 4.73L5.82 21 12 17.27 18.18 21l-1.63-7.03L22 9.24zM12 15.4l-3.76 2.27 1-4.28-3.32-2.88 4.38-.38L12 6.1l1.71 4.04 4.38.38-3.32 2.88 1 4.28L12 15.4z"/>
                            <path d="M0 0h24v24H0z" fill="none"/>
                        </svg>
                        {{/star}}
                        <i class="js--tc-star-index star__underline"></i>
                    </div>

                    <div class="vote">
                        <label>
                            <input class="js--vote-up vote__input-up hidden" type="radio" name="{{id}}" value="up" {{vote.up}}>
                            <svg class="vote__svg-up" height="24" viewBox="0 0 24 24" width="24">
                                <path d="M12 8l-6 6 1.41 1.41L12 10.83l4.59 4.58L18 14z"/>
                                <path d="M0 0h24v24H0z" fill="none"/>
                            </svg>
                        </label>

                        <span>{{vote.score}}</span>

                        <label>
                            <input class="js--vote-down vote__input-down hidden" type="radio" name="{{id}}" value="down" {{vote.down}}>
                            <svg class="vote__svg-down" height="24" viewBox="0 0 24 24" width="24">
                                <path d="M16.59 8.59L12 13.17 7.41 8.59 6 10l6 6 6-6z"/>
                                <path d="M0 0h24v24H0z" fill="none"/>
                            </svg>
                        </label>
                    </div>
                </div>


                <button class="js--tc-previous swipe-button-prev inactive">
                    <svg class="swipe-button__svg-prev" height="24" viewBox="0 0 24 24" width="24">
                        <path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z"/>
                        <path d="M0 0h24v24H0z" fill="none"/>
                    </svg>
                </button>
                <button class="js--tc-next swipe-button-next">
                    <svg class="swipe-button__svg-next" height="24" viewBox="0 0 24 24" width="24">
                        <path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z"/>
                        <path d="M0 0h24v24H0z" fill="none"/>
                    </svg>
                </button>
            </div>

            {{/articles}}

        </div>
    </main>
</body>
</html>
