package com.group.api.web;

import com.group.api.domain.Group;
import com.group.api.domain.service.GroupService;
import com.group.api.web.dto.GroupDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"1. Group"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups/") //컨트롤러 기본 URL
public class GroupController {

    private final GroupService groupService;

}
