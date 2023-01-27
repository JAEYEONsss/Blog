package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {//jpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드 자동 생성
    //주의점: Entity 클래스와 기본 Entity Repository는 함께 위치해야한다.

    @Query("SELECT  p FROM Posts p order by p.id DESC ")//SpringDataJpa에서 제공하지 않는 메소드는 쿼리로 작성 가능
    List<Posts> findAllDesc();
}
