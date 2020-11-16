package com.group.api.web;

import com.group.api.config.JwtTokenProvider;
import com.group.api.domain.GroupTodo;
import com.group.api.domain.service.GroupTodoService;
import com.group.api.domain.service.LikeService;
import com.group.api.web.message.ErrorMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {"5. Like"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups/{groupId}/todos") //컨트롤러 기본 URL
public class LikeController {
    private final GroupTodoService todoService;
    private final LikeService likeService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/{groupTodoId}/like")
    public String onLike(@PathVariable Long groupId,@PathVariable Long groupTodoId, HttpServletRequest request){
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);
        GroupTodo groupTodo = todoService.getTodo(groupTodoId);

        likeService.addLike(groupTodo,accountId);
        return "like add success";
    }

    @DeleteMapping("/{groupTodoId}/like")
    public String cancelLike(@PathVariable Long todoId, HttpServletRequest request){
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);
        GroupTodo groupTodo = todoService.getTodo(todoId);

        likeService.removeLike(groupTodo,accountId);
        return "like cancel success";
    }

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    ErrorMessage runTimeError(RuntimeException e) {
        ErrorMessage error = new ErrorMessage();
        error.setMessage(e.getMessage());
        return error;
    }
}