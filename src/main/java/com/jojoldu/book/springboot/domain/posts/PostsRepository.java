package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {//jpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드 자동 생성
    //주의점: Entity 클래스와 기본 Entity Repository는 함께 위치해야한다.


}
