package com.leegeonhee.exam.board.controller;

import com.leegeonhee.exam.board.Article;
import com.leegeonhee.exam.board.Rq;
import com.leegeonhee.exam.board.Util;
import com.leegeonhee.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleController {
    private int articleLastId;

    private List<Article> articles;

    public ArticleController() {
        articleLastId = 0;
        articles = new ArrayList<>();

        makeTestData();

        if (articles.size() > 0) {
            articleLastId = articles.get(articles.size() - 1).getId();
        }
    }

    void makeTestData() {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
    }

    public void actionWrite() {
        System.out.println("== 게시물 작성 ==");

        System.out.printf("제목) ");
        String title = Container.sc.nextLine();

        System.out.printf("내용) ");
        String body = Container.sc.nextLine();

        int id = ++articleLastId;

        Article article = new Article(id, title, body);
        articles.add(article);

        System.out.printf("%d번 게시물이 생성되었습니다.\n", article.getId());
    }

    public void showList(Rq rq) {
        // 검색 시작
        List<Article> filteredArticles = articles;

        String searchKeyword = rq.getParam("searchKeyword", "");

        if (searchKeyword.length() > 0) {
            filteredArticles = new ArrayList<>();

            for (Article article : articles) {
                boolean matched = article.getTitle().contains(searchKeyword) || article.getBody().contains(searchKeyword);

                if (matched) {
                    filteredArticles.add(article);
                }
            }
        }
        // 검색 기능 끝

        String orderBy = rq.getParam("orderBy", "idDesc");
        boolean orderByIdDesc = orderBy.equals("idDesc"); // 기존로직

        List<Article> sortedArticles = filteredArticles;

        if (orderByIdDesc) {
            sortedArticles = Util.reverseList(sortedArticles);
        }

        System.out.println("== 게시물 리스트 ==");
        System.out.println("번호 | 제목");

        for (Article article : sortedArticles) {
            System.out.printf(" %d  | %s\n", article.getId(), article.getTitle());
        }
    }

    public void showDetail(Rq rq) {
        int id = rq.getIntParam("id", 0);

        if(id == 0) {
            System.out.println("id를 올바르게 입력해주세요.");
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
        System.out.printf("번호 : %d\n", article.getId());
        System.out.printf("제목 : %s\n", article.getTitle());
        System.out.printf("내용 : %s\n", article.getBody());
    }

    public void actionModify(Rq rq) {
        int id = rq.getIntParam("id", 0);

        if(id == 0) {
            System.out.println("id를 올바르게 입력해주세요.");
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
        article.setTitle(Container.sc.nextLine());

        System.out.printf("새 내용 : ");
        article.setBody(Container.sc.nextLine());

        System.out.printf("%d번 게시물이 수정되었습니다.\n", article.getId());
    }

    public void actionDelete(Rq rq) {
        int id = rq.getIntParam("id", 0);

        if(id == 0) {
            System.out.println("id를 올바르게 입력해주세요.");
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

    private Article findById(int id, List<Article> articles) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }

        return null;
    }
}