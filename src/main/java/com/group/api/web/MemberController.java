package com.group.api.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.api.domain.service.GroupService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = {"2. Members"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups/") //컨트롤러 기본 URL
public class MemberController {

}
