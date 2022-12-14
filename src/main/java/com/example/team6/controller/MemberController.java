package com.example.team6.controller;

import com.example.team6.controller.request.LoginRequestDto;
import com.example.team6.controller.request.MemberRequestDto;
import com.example.team6.controller.response.ResponseDto;
import com.example.team6.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@RequiredArgsConstructor
@RestController
public class MemberController {

  private final MemberService memberService;

  //@RequiredArgsConstructor
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping(value = "/api/member/signup")
  public ResponseDto<?> signup(@RequestBody @Valid MemberRequestDto requestDto) {
    return memberService.createMember(requestDto);
  }

  @PostMapping(value = "/api/member/login")
  public ResponseDto<?> login(@RequestBody @Valid LoginRequestDto requestDto,
      HttpServletResponse response
  ) {
    return memberService.login(requestDto, response);
  }

  @PostMapping(value = "/api/auth/member/logout")
  public ResponseDto<?> logout(HttpServletRequest request) {
    return memberService.logout(request);
  }
}
