package com.leegeonhee.exam.board;

import com.leegeonhee.exam.board.container.Container;
public class App {

    public void run() {
        System.out.println("== 텍스트 게시판 v 0.1 ==");
        System.out.println("프로그램 시작");

        while (true) {
            System.out.printf("명령) ");
            String cmd = Container.sc.nextLine();

            Rq rq = new Rq(cmd);

            if (rq.getUrlPath().equals("/usr/article/write")) {
                Container.articleController.actionWrite();
            } else if (rq.getUrlPath().equals("/usr/article/list")) {
                Container.articleController.showList(rq);
            } else if (rq.getUrlPath().equals("/usr/article/detail")) {
                Container.articleController.showDetail(rq);
            } else if (rq.getUrlPath().equals("/usr/article/modify")) {
                Container.articleController.actionModify(rq);
            } else if (rq.getUrlPath().equals("/usr/article/delete")) {
                Container.articleController.actionDelete(rq);
            } else if (rq.getUrlPath().equals("/usr/member/join")) {
                Container.memberController.actionJoin();
            } else if (rq.getUrlPath().equals("/usr/member/login")) {
                Container.memberController.actionLogin();
            } else if (cmd.equals("exit")) {
                System.out.println("== 게시판을 종료합니다 ==");
                break;
            }
        }

        Container.sc.close();
    }
}