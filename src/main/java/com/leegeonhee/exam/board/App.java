package com.leegeonhee.exam.board;

import container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class App {
    int articleLastId;

    List<Article> articles;

    public App() {
        articleLastId = 0;
        articles = new ArrayList<>();
    }

    void makeTestData() {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
    }
    public void run() {
        makeTestData();

        if (articles.size() > 0) {
            articleLastId = articles.get(articles.size() - 1).id;
        }

        System.out.println("== 텍스트 게시판 v 0.1 ==");
        System.out.println("프로그램 시작");

        while (true) {
            System.out.printf("명령) ");
            String cmd = Container.sc.nextLine();

            Rq rq = new Rq(cmd);

            if (rq.getUrlPath().equals("/usr/article/write")) {
                actionUsrArticleWrite();
            } else if (rq.getUrlPath().equals("/usr/article/list")) {
                actionUsrArticleList(rq);
            } else if (rq.getUrlPath().equals("/usr/article/detail")) {
                actionUsrArticleDetail(rq);
            } else if (rq.getUrlPath().equals("/usr/article/modify")) {
                actionUsrArticleModify(rq);
            } else if (rq.getUrlPath().equals("/usr/article/delete")) {
                actionUsrArticleDelete(rq);
            } else if (cmd.equals("exit")) {
                System.out.println("== 게시판을 종료합니다 ==");
                break;
            }
        }

        Container.sc.close();
    }

    private void actionUsrArticleDelete(Rq rq) {
        Map<String, String> params = rq.getParams();

        if (params.containsKey("id") == false) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = 0;

        try {
            id = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
            System.out.println("id를 정수 형태로 입력해주세요.");
            return;
        }

        if (articles.isEmpty()) {
            System.out.println("게시물이 존재하지 않습니다.");
            return;
        }

        Article article = findById(id, articles);

        if (article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        articles.remove(article);

        System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
    }

    private void actionUsrArticleModify(Rq rq) {
        Map<String, String> params = rq.getParams();

        if (params.containsKey("id") == false) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = 0;

        try {
            id = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
            System.out.println("id를 정수 형태로 입력해주세요.");
            return;
        }

        if (articles.isEmpty()) {
            System.out.println("게시물이 존재하지 않습니다.");
            return;
        }

        Article article = findById(id, articles);

        if (article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("새 제목 : ");
        article.title = Container.sc.nextLine();

        System.out.printf("새 내용 : ");
        article.body = Container.sc.nextLine();

        System.out.printf("%d번 게시물이 수정되었습니다.\n", article.id);
    }

    private void actionUsrArticleWrite() {
        System.out.println("== 게시물 작성 ==");

        System.out.printf("제목) ");
        String title = Container.sc.nextLine();

        System.out.printf("내용) ");
        String body = Container.sc.nextLine();

        int id = ++articleLastId;

        Article article = new Article(id, title, body);
        articles.add(article);

        System.out.printf("%d번 게시물이 생성되었습니다.\n", article.id);
    }

    private void actionUsrArticleDetail(Rq rq) {
        Map<String, String> params = rq.getParams();

        if (params.containsKey("id") == false) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = 0;

        try {
            id = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
            System.out.println("id를 정수 형태로 입력해주세요.");
            return;
        }

        if (articles.isEmpty()) {
            System.out.println("게시물이 존재하지 않습니다.");
            return;
        }

        Article article = findById(id, articles);

        if (article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
            return;
        }

        System.out.println("== 게시물 상세보기 ==");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.body);

    }

    private void actionUsrArticleList(Rq rq) {
        Map<String, String> params = rq.getParams();

        // 검색 시작
        List<Article> filteredArticles = articles;

        if (params.containsKey("searchKeyword")) {
            String searchKeyword = params.get("searchKeyword");

            filteredArticles = new ArrayList<>();

            for (Article article : articles) {
                boolean matched = article.title.contains(searchKeyword) || article.body.contains(searchKeyword);

                if (matched) {
                    filteredArticles.add(article);
                }
            }
        }
        // 검색 기능 끝

        List<Article> sortedArticles = filteredArticles;

        boolean orderByIdDesc = true; // 기존로직

        if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
            orderByIdDesc = false;
        }

        if (orderByIdDesc) {
            sortedArticles = Util.reverseList(sortedArticles);
        }

        System.out.println("== 게시물 리스트 ==");
        System.out.println("번호 | 제목");

        for (Article article : sortedArticles) {
            System.out.printf(" %d  | %s\n", article.id, article.title);
        }
    }

    private Article findById(int id, List<Article> articles) {
        for (Article article : articles) {
            if (article.id == id) {
                return article;
            }
        }

        return null;
    }
}