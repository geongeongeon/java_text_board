package com.leegeonhee.exam.board;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void addTestData(List<Article> dataList){
        dataList.add(new Article(1, "제목1", "내용1"));
        dataList.add(new Article(2, "제목2", "내용2"));
        dataList.add(new Article(3, "제목3", "내용3"));
        dataList.add(new Article(4, "제목4", "내용4"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int articleLastId = 0;

        Article data = null;
        List<Article> dataList = new ArrayList<>();

        addTestData(dataList);

        if(dataList.size() > 0) {
            articleLastId = dataList.get(dataList.size()-1).id;
            data = dataList.get(dataList.size()-1);
        }

        System.out.println("== 텍스트 게시판 v 0.1 ==");
        System.out.println("프로그램 시작");

        while(true) {
            System.out.printf("명령)");
            String cmd = scanner.nextLine();

            if(cmd.equals("write")) {
                System.out.println("== 게시물 작성 ==");

                int id = ++articleLastId;

                System.out.printf("제목)");
                String title = scanner.nextLine();

                System.out.printf("내용)");
                String body = scanner.nextLine();

                data = new Article(id, title, body);
                dataList.add(data);

                System.out.println(data);
                System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
            }
            else if(cmd.equals("detail")) {
                System.out.println("== 게시물 상세보기 ==");

                if(dataList.isEmpty()) {
                    System.out.println("게시물이 없습니다.");
                } else {
                    System.out.println("id : " + data.id);
                    System.out.println("title : " + data.title);
                    System.out.println("body : " + data.body);
                }
            }
            else if(cmd.equals("list")) {
                System.out.println("== 게시물 리스트 ==");
                System.out.println("id | title");

                for(int i = dataList.size()-1; i >= 0; i--){
                    Article article = dataList.get(i);
                    System.out.printf(" %d  | %s\n", article.id, article.title);
                }
            }
            else if(cmd.equals("exit")) {
                System.out.println("게시판을 종료합니다.");
                break;
            }
        }
        scanner.close();
    }
}

class Article {
    int id;
    String title;
    String body;

    public Article (int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("id : %d, title : \"%s\", body : \"%s\"", id, title, body);
    }
}