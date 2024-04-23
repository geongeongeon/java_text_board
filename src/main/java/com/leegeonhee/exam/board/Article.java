package com.leegeonhee.exam.board;

class Article {
    int id;
    String title;
    String body;

    Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override // 어노테이션
    public String toString() {
        return String.format("{id : %d, title : \"%s\", body : \"%s\"}", id, title, body);
    }
}