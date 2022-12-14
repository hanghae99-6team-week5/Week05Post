package com.example.team6.domain;

import com.example.team6.controller.request.PostRequestDto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Builder
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Post extends Timestamped {

  public Post() {
  }

  public Post(String title, String content, Member member) {
    this.title = title;
    this.content = content;
    this.member = member;
  }

  public Post(Long id, String title, String content, List<Comment> comments, Member member) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.comments = comments;
    this.member = member;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column
  private Long heartNum;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;

  //////@Getter 대신 추가 부분
  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

//  public Long getHeartNum() {
//    return heartNum;
//  }

  public List<Comment> getComments() {
    return comments;
  }

  public Member getMember() {
    return member;
  }
  ////////////////////
  @JoinColumn(name = "member_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  public void update(PostRequestDto postRequestDto) {
    this.title = postRequestDto.getTitle();
    this.content = postRequestDto.getContent();
  }

  public boolean validateMember(Member member) {
    return !this.member.equals(member);
  }

}
