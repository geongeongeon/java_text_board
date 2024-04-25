package com.leegeonhee.exam.board.container;

import com.leegeonhee.exam.board.controller.ArticleController;
import com.leegeonhee.exam.board.controller.MemberController;

import java.util.Scanner;

public class Container {
    public static Scanner sc;
    public static ArticleController articleController;
    public static MemberController memberController;

    static {
        sc = new Scanner(System.in);
        articleController = new ArticleController();
        memberController = new MemberController();
    }
}