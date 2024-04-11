package com.leegeonhee.exam.board;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("== 텍스트 게시판 v 0.1 ==");
        System.out.println("프로그램 시작");

        int articleLastId = 0;

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

                Article data = new Article();
                data.storage(id, title, body);

                System.out.println(data);

                System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
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
    void storage(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
    @Override
    public String toString() {
        return String.format("id : %d, title : %s, body : %s", id, title, body);
    }
}